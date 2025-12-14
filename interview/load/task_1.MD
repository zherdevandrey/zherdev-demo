### Тестирование JAVA
#### Вопрос 1: Какие ключевые метрики JVM вы мониторите при нагрузочном тестировании и почему?
Ключевые метрики JVM:

1. Heap Memory:
    - Eden Space usage - активное создание объектов
    - Old Generation usage - долгоживущие объекты
    - GC времени - частые/long GC = проблемы

2. Garbage Collection:
    - GC pause times (Young GC, Full GC)
    - GC frequency - как часто срабатывает
    - Throughput GC - % времени вне GC

3. Threads:
    - Active threads - соответствие ожидаемой нагрузке
    - Deadlocked threads - блокировки
    - Thread states (RUNNABLE, BLOCKED, WAITING)

4. Class Loading:
    - Loaded class count
    - Unloaded class count - может указывать на утечки

#### Вопрос 2: Какие проблемы производительности типичны для JAVA-приложений?
Типичные проблемы JAVA:

1. Память:
    - Memory leaks (статические коллекции, кэши)
    - Большие объекты в молодом поколении
    - String duplication

2. Синхронизация:
    - Deadlocks на synchronized методах
    - Contention на ConcurrentHashMap
    - Blocked threads в connection pools

3. Базы данных:
    - N+1 проблема в Hibernate
    - Отсутствие индексов
    - Неэффективные JPQL запросы

4. Ввод/вывод:
    - Blocking I/O в Tomcat threads
    - Большие файлы в memory
    - Slow external service calls

#### Вопрос 3: При нагрузке приложение потребляет много CPU. Ваши действия?
Ответ:
"Пошаговый анализ:
Локализовать источник - приложение vs система vs GC

top -H посмотреть CPU по threads
jstack получить thread dump
Анализ thread dump:
Искать threads в RUNNABLE состоянии
Анализировать stack traces на предмет "hot methods"
Профилирование:
Запустить async-profiler с -d 60 -e cpu
Идентифицировать методы с наибольшим CPU usage

Возможные причины:
Бесконечные циклы
Неэффективные алгоритмы (O(n²))
Частые сериализации/десериализации
"Горячие" synchronized блоки"

### Подготовка тестового плана
Сценарии для тестирования:

Чтение постов - GET /posts
Создание нового поста - POST /posts

Комбинированный сценарий - пользователь читает посты, затем создает новый
Требования к нагрузке:
Пиковая нагрузка: 50 одновременных пользователей

Длительность теста: 1 минут

Ramp-Up период: 20 секунд

Критерии успеха:
95-й перцентиль времени ответа < 1000 мс
Уровень ошибок < 1%
Throughput > 50 запросов/секунду

GET
https://jsonplaceholder.typicode.com/posts/1
```
{
    "userId": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```

POST
https://jsonplaceholder.typicode.com/posts/1
```
{
    "userId": 1,
    "title": "SUNT AUT FACERE REPELLAT PROVIDENT OCCAECATI EXCEPTURI OPTIO REPREHENDERIT",
    "body": "QUIA ET SUSCIPIT\NSUSCIPIT RECUSANDAE CONSEQUUNTUR EXPEDITA ET CUM\NREPREHENDERIT MOLESTIAE UT UT QUAS TOTAM\NNOSTRUM RERUM EST AUTEM SUNT REM EVENIET ARCHITECTO"
}
```













Stress Test: Постепенно увеличивать нагрузку до 100 пользователей
Spike Test: Резкий скачок с 10 до 80 пользователей

Собрать метрики:
Response Time (средний, 90, 95, 99 перцентили)
Throughput (запросов/секунду)
Error Rate
Network throughput

Обязательные элементы:
Корреляция динамических параметров (если требуется)

Параметризация тестовых данных
Валидация ответов (assertions)
Обработка ошибок
HTML отчет


### SQL
В базе данных есть две таблицы:

customers
| id | name | city |
|-----|--------------|-----------|
| 1 | Иванов Иван | Москва |
| 2 | Петров Петр | Санкт-Петербург |
| 3 | Сидоров Сидор| Москва |

orders
| id | customer_id | order_date | total_amount |
|-----|--------------|------------|--------------|
| 1 | 1 | 2024-07-01 | 1500 |
| 2 | 2 | 2024-07-02 | 2000 |
| 3 | 1 | 2024-07-10 | 500 |
| 4 | 3 | 2024-07-05 | 3000 |

Задача:
Напишите SQL-запрос, который выводит для каждого клиента (его имя, город) и сумму всех его заказов. Результат должен содержать только тех клиентов, у которых есть заказы, и отсортирован по сумме заказов по убыванию.                        