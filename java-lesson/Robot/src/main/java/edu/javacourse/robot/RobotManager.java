package edu.javacourse.robot;

/**
 *
 * @author gafur
 */
public class RobotManager {

    public static void main(String[] args) {
        // Создаем объекта класса RobotExt - теперь с параметрами
        Robot robot = new RobotTotal(0,0);

        System.out.println(robot.getClass().getName());
    }
}
