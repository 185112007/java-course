package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.AnswerCityRegister;
import edu.javacourse.studentorder.domain.StudentOrder;

public class CityRegisterValidator {
    private String hostName;
    private int port;
    private String login;
    private String password;

    public AnswerCityRegister checkCityRegister(StudentOrder so){
        System.out.println("City register is running: " + hostName + ", " + port + ", " + login + ", " + password);
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.success = false;
        return ans;
    }
}
