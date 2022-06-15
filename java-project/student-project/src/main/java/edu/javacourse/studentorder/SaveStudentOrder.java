package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.StudentOrder;

public class SaveStudentOrder {
    public static void main(String[] args){
        StudentOrder so
                = new StudentOrder();

        long ans = saveStudentOrder(so);
        System.out.println(ans);
    }

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder");
        return answer;
    }

    static StudentOrder buildStudentOrder(long id){
        StudentOrder so = new StudentOrder();
        Adult husband = new Adult("Vasilyev", "Andrey", "Petrovic",null);
        husband.setGivenName("Andrey");
        so.setHusband(husband);
        so.setStudentOrderId(id);
        return so;
    }
}
