package edu.javacourse.sample.gui1;

import javax.swing.*;

public class SimpleFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        // Установим заголовок
        frame.setTitle("First Window");
        // Установим свойство - завершить приложение при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Выставим координаты и размеры
        // Верхний левый угол - первые два числа 100 и 100
        // Ширина и высота - вторы два числа 400 и 200
        frame.setBounds(100, 100, 400, 200);
        // Отобразим окно, сделав его видимым
        frame.setVisible(true);
    }
}
