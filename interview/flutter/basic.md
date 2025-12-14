Основы Dart (глубокое понимание)

0. Использование DART для различных операционных систем
   - Dart VM (для разработки и серверной части): байт-код Dart или промежуточное представление выполняются в виртуальной машине с Just-In-Time (JIT) компиляцией и оптимизациями во время исполнения. Это позволяет быстрый цикл разработки, hot reload и интерактивное тестирование.
   - В мобилной разработке/ Flutter: основной путь — Ahead-of-Time (AOT) компиляция в нативный машинный код для целевой платформы (Android/iOS). В этом случае байт-код не исполняется напрямую; код компилируется в нативный код, который затем выполняется на устройстве.
   - В вебе: Dart обычно компилируется в JavaScript (через транспиляцию/конвертацию). В Web-среде байт-код Dart не запускается напрямую; вместо этого результатом является JS-код, который исполняется в браузере.

1. final vs const
Ответ:
final: Объявляет переменную, которая может быть установлена только один раз. Инициализация может происходить во время выполнения.
const: Объявляет константу времени компиляции. Её значение должно быть известно уже на этапе компиляции.

```
final a = DateTime.now(); // OK - выполняется в runtime
const b = DateTime.now(); // ОШИБКА - значение должно быть известно при компиляции

// С коллекциями:
final list1 = [1, 2, 3]; // Можно изменить содержимое: list1.add(4)
const list2 = [1, 2, 3]; // Неизменяемая коллекция: list2.add(4) - ОШИБКА

// Const конструкторы:
class Point {
  final double x, y;
  const Point(this.x, this.y); // Const constructor
}

```
2. Null Safety
Ответ:
Null Safety в Dart предотвращает ошибки доступа к null через систему типов.
? (Nullable type): String? - переменная может быть String или null
! (Null assertion operator): str! - утверждает, что значение не null. Если окажется null - выбросит исключение.
late: Откладывает инициализацию до первого использования. Если обратиться до инициализации - получим LateInitializationError.

3. Дженерики, зачем 

4. Многопоточность - как устроена, как запустить задачу асинхронно, как передаются объекты между потоками, event loop приоритеты

- Низкоуровневый api
```
import 'dart:isolate';

// Функция, которая будет выполняться в изоляте
void isolateFunction(SendPort sendPort) {
  // Отправляем сообщение обратно в основной поток
  sendPort.send('Привет из изолята!');
  
  // Выполняем тяжелые вычисления
  int result = 0;
  for (int i = 0; i < 1000000000; i++) {
    result += i;
  }
  sendPort.send('Результат: $result');
}

void main() async {
  final receivePort = ReceivePort();
  
  // Создаем изолят
  final isolate = await Isolate.spawn(isolateFunction, receivePort.sendPort);
  
  // Слушаем сообщения из изолята
  receivePort.listen((message) {
    print('Получено: $message');
    
    // Завершаем изолят после получения всех сообщений
    if (message.toString().contains('Результат')) {
      receivePort.close();
      isolate.kill();
    }
  });
}
```

- Высокоуровневый api
```
import 'dart:isolate';

// Простой API для одноразовых вычислений
void main() async {
  // Автоматическое создание и управление изолятом
  final result = await compute(_calculate, 1000000);
  print('Результат: $result');
  
  // Изолят автоматически закрывается после выполнения
}

// Функция должна быть статической или глобальной
int _calculate(int n) {
  int sum = 0;
  for (int i = 0; i < n; i++) {
    sum += i;
  }
  return sum;
}
```

```
void main() {
  print('Синхронный код');
  Future(() => print('Event Queue - Future'));
  Timer(Duration.zero, () => print('Event Queue - Timer'));
  Future.microtask(() => print('Microtask'));
  scheduleMicrotask(() => print('Microtask - scheduleMicrotask'));
  print('Синхронный код');
  Future.microtask(() => print('Microtask 2'));
}

// Вывод:
// 1. Синхронный код
// 2. Синхронный код
// 3. Microtask Queue
// 4. Microtask Queue - scheduleMicrotask
// 5. Microtask Queue - последний
// 6. Event Queue - Future
// 7. Event Queue - Timer
```
