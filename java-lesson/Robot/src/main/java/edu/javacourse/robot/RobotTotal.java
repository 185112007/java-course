package edu.javacourse.robot;

public class RobotTotal extends Robot{

    // Вводим поле дляхранения пройденной дистанции
    private double totalDistance = 0;

    public RobotTotal(){
        super(0, 0);
    }
    public RobotTotal(double x, double y) {
        super(x, y);
    }

    @Override
    void forward(int distance) {
        super.forward(distance);
        totalDistance += distance;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
}
