package edu.javacourse.robot;

/**
 *
 * @author gafur
 */
public class TestVariable {
    public static void main(String[] args){
        double first = 90;
        
        TestVariable tv = new TestVariable();
        System.out.println("main method: " + first);
        tv.testMethod(first);
        
        System.out.println("main method: " + first);
    }

    private void testMethod(double first) {
        System.out.println("test method: " + first);
        first = 11;
        System.out.println("test method: " + first);
    }
}
