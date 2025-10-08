1) Задача: Дан список сотрудников. Сгруппировать по department и найти среднюю зарплату в каждом department.

```
public class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;
    // constructor, getters
}

List<Employee> employees = Arrays.asList(
    new Employee("John", "IT", 5000, 25),
    new Employee("Jane", "HR", 4000, 30),
    new Employee("Bob", "IT", 6000, 35),
    new Employee("Alice", "HR", 4500, 28)
);

Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));
```

2) Работа со строками
```
List<String> sentences = Arrays.asList(
    "Hello world",
    "Java stream API",
    "Hello Java programming"
);

// Все уникальные слова
Set<String> uniqueWords = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .collect(Collectors.toSet());

// Самое длинное слово
Optional<String> longestWord = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .max(Comparator.comparingInt(String::length));

// Количество слов длиннее 5 символов
long countLongWords = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .filter(word -> word.length() > 5)
    .count();
```