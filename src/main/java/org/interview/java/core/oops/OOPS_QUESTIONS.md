
---

## 🟢 Easy (Basics)
### **1. What are the four pillars of OOPS in Java? Explain briefly.**

- **Inheritance** → Enables a class to acquire the properties and methods of another class, promoting code reuse.
- **Encapsulation** → Binding of data (fields) and behavior (methods) into a single unit (class).
- **Data Hiding** → Using **access modifiers** (`private`, `protected`, etc.) to restrict unauthorized access.
- **Abstraction** → Exposing only relevant details and hiding the implementation (via abstract classes or interfaces).

---

### **2. Difference between class and object in Java.**

- **Class**: A blueprint/template that defines structure & behavior (fields + methods).
- **Object**: A runtime instance of a class, occupying memory and having its own state.

---

### **3. What is encapsulation? Give a real-world example.**

- **Encapsulation** = Binding variables and methods in a single unit, controlling access via getters/setters.
- **Real-world example** → A **Car class**:
    - Private fields: `fuelLevel`, `engineStatus`.
    - Public methods: `startEngine()`, `applyBrakes()`.
    - The driver doesn’t directly manipulate engine internals; instead, controlled methods are used.

---

### **4. Explain abstraction with a practical example.**

- **Abstraction** = Hiding implementation and exposing only behavior.

Example:
```java
abstract class Payment {
    abstract void processPayment(double amount);

    void printReceipt() {
        System.out.println("Receipt printed.");
    }
}

class CreditCardPayment extends Payment {
    @Override
    void processPayment(double amount) {
        System.out.println("Processing credit card payment: " + amount);
    }
}
```



5. What is **inheritance**? Can constructors be inherited?
- Inheritance = Acquiring fields/methods of a parent class. 
- Constructors are not inherited, but the subclass can explicitly call the parent constructor via super()
6. Difference between **method overloading** and **method overriding**.
- Overloading means you are creating a separate implementation of a method based on the arguments and it's type, where as overriding means writing a seperate implementation for the method under the same name where the functionality differ 
- Overloading happens at compile time where as overriding happens at the runtime
- Overloading = resolved at compile-time, Overriding = resolved at runtime (dynamic dispatch).
7. Can you override a **static method** in Java? Why or why not?
- Static methods belong to class, not instance, hence cannot be overridden, only hidden.
- If a subclass defines the same static method, it’s method hiding, not overriding.
8. What is **polymorphism**? Explain compile-time vs runtime polymorphism.'
- Polymorphism = One name, many forms (method behavior depends on context).
- Compile-time polymorphism → Method Overloading (resolved by compiler).
- Runtime polymorphism → Method Overriding (resolved at runtime via dynamic method dispatch).
9. Difference between **abstract class** and **interface**.
-   Abstract class → Can have both abstract & concrete methods, instance variables, constructors. Represents “is-a” relationship.- for example, Payment is a abstract class which contains 2 methods, processPayment and printReceipt, all the classes extending Payment has to use these two methods considing if both are abstract or not. 
- Pure abstraction (until Java 7), supports default & static methods (Java 8+). Represents “has-a/can-do” relationship.
10. What is **data hiding**? How is it achieved in Java?
- Data hiding means, blocking of accessing the class data directly with the help of access specifiers, for example a private method of the class cannot be access directly and can only be access with a getter or setter, which  gives another level of control.


---



---

## 🔴 Expert (Deep/Tricky)
21. Why is **diamond problem** an issue in multiple inheritance? How does Java solve it?
22. What happens when you call an **overridable method** from a constructor?
23. How do **sealed classes** (Java 17+) enhance OOPS design?
24. Explain **fragile base class problem** in inheritance.
25. Can an interface extend another interface? Can an interface extend a class?
26. How does the **Liskov Substitution Principle (LSP)** apply to Java inheritance?
27. Why are arrays **covariant** but generics **invariant** in Java?
28. Can a subclass catch a broader checked exception than the parent method? Why not?
29. Explain how **polymorphism + generics** work together (with PECS: Producer Extends, Consumer Super).
30. In a real system, when would you prefer **abstraction with interface** over **abstract class**? Give examples.

---
