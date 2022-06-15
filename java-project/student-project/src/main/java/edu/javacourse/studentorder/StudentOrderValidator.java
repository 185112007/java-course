package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.*;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterValidator;
    private WeddingValidator weddingValidator;
    private ChildrenValidator childrenValidator;
    private StudentValidator studentValidator;
    private MailSender mailSender;

    public StudentOrderValidator(){
        cityRegisterValidator = new CityRegisterValidator();
        weddingValidator = new WeddingValidator();
        childrenValidator = new ChildrenValidator();
        studentValidator = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll(){

        StudentOrder[] soArray = readStudentOrders();
        for (StudentOrder so : soArray){
            System.out.println();
            checkOneOrder(so);
        }
    }

    public void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
        AnswerWedding weddingAns = checkWedding(so);
        AnswerChildren childrenAns = checkChildren(so);
        AnswerStudent studentAns = checkStudent(so);

        sendMail(so);
    }

    public StudentOrder[] readStudentOrders(){
        StudentOrder[] soArray = new StudentOrder[3];

        for (int i=0; i<soArray.length; i++){
            StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
            soArray[i] = so;
        }

        return soArray;
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so){
        return cityRegisterValidator.checkCityRegister(so);
    }

    public AnswerWedding checkWedding(StudentOrder so){
        return weddingValidator.checkWedding(so);
    }

    public AnswerChildren checkChildren(StudentOrder so){
        return childrenValidator.checkChildren(so);
    }

    public AnswerStudent checkStudent(StudentOrder so){
        return studentValidator.checkStudent(so);
    }

    public void sendMail(StudentOrder so){
        mailSender.sendMail(so);
    }
}
