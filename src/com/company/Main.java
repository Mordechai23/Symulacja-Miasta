package com.company;

public class Main {

    public static void main(String[] args) {
//        Miasto mojeMiasto = new Miasto();
//        mojeMiasto.inicjalizacja();
//        mojeMiasto.wyswietlPlansze();

        int budRandom =(int)(Math.random()*4+1);
        for (int i=0; i<100; i++){
            System.out.println(budRandom);
            budRandom =(int)(Math.random()*4+1);
        }
    }
}
