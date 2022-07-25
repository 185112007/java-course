package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.domain.children.AnswerChildren;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.domain.student.AnswerStudent;
import edu.javacourse.studentorder.domain.wedding.AnswerWedding;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.*;

import java.util.LinkedList;
import java.util.List;

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

        List<StudentOrder> studentOrders = readStudentOrders();
        for (StudentOrder so : studentOrders){
            System.out.println();
            checkOneOrder(so);
        }
    }

    public void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
//        AnswerWedding weddingAns = checkWedding(so);
//        AnswerChildren childrenAns = checkChildren(so);
//        AnswerStudent studentAns = checkStudent(so);

        sendMail(so);
    }

    public List<StudentOrder> readStudentOrders(){
        List<StudentOrder> studentOrders = new LinkedList<>();

        for (int i=0; i<5; i++){
            StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
            studentOrders.add(so);
        }

        return studentOrders;
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
