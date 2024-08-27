package main;

import adder.AdderManager;
import numberCount.NumberCountThread;

public class Main {
    public static void main(String[] args) {
        NumberCountThread numberCountThread = new NumberCountThread();
        AdderManager adderManager = new AdderManager(numberCountThread);
        Buyer buyer = new Buyer(numberCountThread, adderManager);

        numberCountThread.start();

        while(!numberCountThread.getIsStopRunning()) {
            System.out.println("「add」 or 「buy」");
            String operation = new java.util.Scanner(System.in).nextLine();
            if (operation.equals("add")) {
                adderManager.manualAdd();
            } else if (operation.equals("buy")) {
                buyer.buy();
            }
        }

        try {
            numberCountThread.join();
        } catch (InterruptedException e) {
            System.out.println("joinエラー：" + e.getMessage());
        }
    }
}