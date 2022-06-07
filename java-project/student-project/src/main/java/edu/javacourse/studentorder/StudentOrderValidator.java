package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.*;

public class StudentOrderValidator {

    /*
    Properties
     */
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

    public StudentOrder readStudentOrder(){
        return new StudentOrder();
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
