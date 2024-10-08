package main;

import adder.AdderManager;
import numberCount.NumberCountThread;

public class Main {
    public static void main(String[] args) {
        NumberCountThread numberCountThread = new NumberCountThread();
        AdderManager adderManager = new AdderManager(numberCountThread);

        numberCountThread.start();

        try {
            numberCountThread.join();
        } catch (InterruptedException e) {
            System.out.println("joinエラー：" + e.getMessage());
        }
    }
}