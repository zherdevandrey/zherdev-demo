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
            
            externalBankService.notifyTransfer(fromAccount.getNumber(), toAccount.getNumber(), amount);
            
            emailService.sendTransferNotification(fromAccount.getEmail(), toAccount.getEmail(), amount);
            
            auditService.logTransfer(fromAccountId, toAccountId, amount);
            
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
            throw new IoException(e);
        }
    }

    @Transactional
    public BigDecimal getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException(accountId));
        return account.getBalance();
    }

    @Transactional
    public void applyInterestToAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        
        for (Account account : accounts) {
            BigDecimal interest = calculateInterest(account);
            account.setBalance(account.getBalance().add(interest));
            accountRepository.save(account);
            
            longCalculatioMethod();
        }
    }

    @Transactional
    private BigDecimal calculateInterest(Account account) {
        // Сложные вычисления...
        return account.getBalance().multiply(new BigDecimal("0.01"));
    }

    @Transactional
    public void updateAccountDetails(Long accountId, String newDetails) throws BusinessException {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException(accountId));
        
        account.setDetails(newDetails);
        accountRepository.save(account);
        
        if (newDetails.contains("invalid")) {
            throw new BusinessException("Invalid details"); // No rollback!
        }
    }

    @Transactional
    public void bulkTransfer(List<TransferRequest> transfers) {
        for (TransferRequest transfer : transfers) {
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

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private BigDecimal balance;
    private String email;
    
    @Transactional
    public void transferTo(Account toAccount, BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        this.balance = this.balance.subtract(amount);
        toAccount.balance = toAccount.balance.add(amount);
    }
}


```