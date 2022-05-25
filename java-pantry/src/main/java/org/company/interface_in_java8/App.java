package org.company.interface_in_java8;

public class App {

    public static void main(String[] args) {
        MyInterface7 mi7 = new MyClass7();
        mi7.sayHello();

        MyInterface8 mi8 = new MyClass8();
        mi8.sayHello();
        mi8.sayHi();

        MyInterface8.sayStatic();
    }
}
