1) Вопрос: Создайте immutable класс Person с полями name, age и addresses (список адресов).

```
public final class Person {
    private final String name;
    private final int age;
    private final List<String> addresses;
    
    public Person(String name, int age, List<String> addresses) {
        this.name = name;
        this.age = age;
        this.addresses = Collections.unmodifiableList(new ArrayList<>(addresses));
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getAddresses() { return addresses; }
}
```

2) Singleton 
Вопрос: Реализуйте thread-safe Singleton с ленивой инициализацией.
```
public class Singleton {
    private static volatile Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
3) String Reversal
```
public String reverse(String str) {
    if (str == null) return null;
    
    char[] chars = str.toCharArray();
    int left = 0, right = chars.length - 1;
    
    while (left < right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
        left++;
        right--;
    }
    
    return new String(chars);
}
```