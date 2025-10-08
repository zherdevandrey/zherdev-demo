```

@Service
public class BankTransferService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionHistoryRepository historyRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private AuditService auditService;
    
    @Autowired
    private ExternalBankService externalBankService;

    // ПРОБЛЕМА 1: Слишком длинная транзакция с внешними вызовами
    @Transactional
    public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        try {
            // Начало транзакции
            Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountId));
            
            Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(toAccountId));
            
            // Проверка баланса
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient funds");
            }
            
            // Снятие денег
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            accountRepository.save(fromAccount);
            
            // Зачисление денег
            toAccount.setBalance(toAccount.getBalance().add(amount));
            accountRepository.save(toAccount);
            
            // Запись в историю
            TransactionHistory history = new TransactionHistory();
            history.setFromAccount(fromAccount);
            history.setToAccount(toAccount);
            history.setAmount(amount);
            history.setTimestamp(new Date());
            historyRepository.save(history);
            
            // ПРОБЛЕМА 2: Внешний HTTP вызов внутри транзакции
            externalBankService.notifyTransfer(fromAccount.getNumber(), toAccount.getNumber(), amount);
            
            // ПРОБЛЕМА 3: Отправка email внутри транзакции
            emailService.sendTransferNotification(fromAccount.getEmail(), toAccount.getEmail(), amount);
            
            // ПРОБЛЕМА 4: Аудит внутри транзакции
            auditService.logTransfer(fromAccountId, toAccountId, amount);
            
        } catch (Exception e) {
            // ПРОБЛЕМА 5: Неправильная обработка исключений
            System.out.println("Transfer failed: " + e.getMessage());
            throw e;
        }
    }

    // ПРОБЛЕМА 6: readOnly=false по умолчанию для read-only операций
    @Transactional
    public BigDecimal getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException(accountId));
        return account.getBalance();
    }

    // ПРОБЛЕМА 7: Неправильная изоляция для финансовой операции
    @Transactional
    public void applyInterestToAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        
        for (Account account : accounts) {
            // ПРОБЛЕМА 8: N+1 запрос внутри транзакции
            BigDecimal interest = calculateInterest(account);
            account.setBalance(account.getBalance().add(interest));
            accountRepository.save(account);
            
            // ПРОБЛЕМА 9: Длительная операция в цикле
            try {
                Thread.sleep(10); // Имитация сложных вычислений
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // ПРОБЛЕМА 10: Transactional на private методе
    @Transactional
    private BigDecimal calculateInterest(Account account) {
        // Сложные вычисления...
        return account.getBalance().multiply(new BigDecimal("0.01"));
    }

    // ПРОБЛЕМА 11: Неявный rollback только для RuntimeException
    @Transactional
    public void updateAccountDetails(Long accountId, String newDetails) throws BusinessException {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException(accountId));
        
        account.setDetails(newDetails);
        accountRepository.save(account);
        
        // ПРОБЛЕМА: checked exception не вызывает rollback по умолчанию
        if (newDetails.contains("invalid")) {
            throw new BusinessException("Invalid details"); // No rollback!
        }
    }

    // ПРОБЛЕМА 12: Вложенные транзакции без указания propagation
    @Transactional
    public void bulkTransfer(List<TransferRequest> transfers) {
        for (TransferRequest transfer : transfers) {
            // ПРОБЛЕМА: REQUIRED по умолчанию - использует существующую транзакцию
            processSingleTransfer(transfer.getFromAccountId(), 
                                transfer.getToAccountId(), 
                                transfer.getAmount());
        }
    }
    
    @Transactional
    public void processSingleTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        // Если один перевод упадет - упадет вся bulk операция
        transferMoney(fromAccountId, toAccountId, amount);
    }

    // ПРОБЛЕМА 13: Transactional без указания timeout
    @Transactional
    public void generateMonthlyReports() {
        // Длительная операция которая может заблокировать connection pool
        List<Account> accounts = accountRepository.findAll();
        
        for (Account account : accounts) {
            generateReportForAccount(account); // Может занимать много времени
            historyRepository.flush(); // Принудительный flush
        }
    }
}

// ПРОБЛЕМА 14: Transactional на контроллере
@RestController
@Transactional // АНТИПАТТЕРН!
public class AccountController {
    
    @Autowired
    private BankTransferService transferService;
    
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) {
        transferService.transferMoney(request.getFromAccountId(), 
                                    request.getToAccountId(), 
                                    request.getAmount());
        return ResponseEntity.ok("Transfer successful");
    }
}

// ПРОБЛЕМА 15: Entity с бизнес-логикой
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private BigDecimal balance;
    private String email;
    
    // ПРОБЛЕМА: Бизнес-логика в Entity
    @Transactional // НЕ РАБОТАЕТ в Entity!
    public void transferTo(Account toAccount, BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        this.balance = this.balance.subtract(amount);
        toAccount.balance = toAccount.balance.add(amount);
    }
}


```