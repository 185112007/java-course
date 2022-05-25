package org.company.interface_in_java8;

public interface MyInterface8{
    public void sayHello();

    default void sayHi(){
        System.out.println("Java 8 default method");
    }

    public static void sayStatic(){
        System.out.println("Java 8 static method");
    }
}
