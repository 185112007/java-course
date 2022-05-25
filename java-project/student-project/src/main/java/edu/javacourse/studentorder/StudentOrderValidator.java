package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.*;

public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    static void checkAll(){


        while (true) {
            StudentOrder so = readStudentOrder();
            if (so == null){
                break;
            }

            AnswerCityRegister cityAnswer = checkCityRegister(so);
            if (!cityAnswer.success){
//                continue;
                break;
            }

            AnswerWedding weddingAns = checkWedding(so);
            AnswerChildren childrenAns = checkChildren(so);
            AnswerStudent studentAns = checkStudent(so);

            sendMail(so);
        }
    }

    static StudentOrder readStudentOrder(){
        return new StudentOrder();
    }

    static AnswerCityRegister checkCityRegister(StudentOrder so){
        return new CityRegisterValidator().checkCityRegister(so);
    }

    static AnswerWedding checkWedding(StudentOrder so){
        return new WeddingValidator().checkWedding(so);
    }

    static AnswerChildren checkChildren(StudentOrder so){
        return new ChildrenValidator().checkChildren(so);
    }

    static AnswerStudent checkStudent(StudentOrder so){
        return new StudentValidator().checkStudent(so);
    }

    static void sendMail(StudentOrder so){
        new MailSender().sendMail(so);
    }
}
