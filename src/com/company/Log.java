package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {
    static ArrayList<Integer> logPuste             = new ArrayList<Integer>();
    static ArrayList<Integer> logDomy              = new ArrayList<Integer>();
    static ArrayList<Integer> logBiurowce          = new ArrayList<Integer>();
    static ArrayList<Integer> logFabryki           = new ArrayList<Integer>();
    static ArrayList<Integer> logSklepy            = new ArrayList<Integer>();
    static ArrayList<Integer> logMieszkancy        = new ArrayList<Integer>();
    static ArrayList<Integer> logMiejscaPracy      = new ArrayList<Integer>();
    static ArrayList<Double>  logZadowolenie       = new ArrayList<Double>();
    static ArrayList<Integer> logBudynki           = new ArrayList<>();

    public static void inicjalizacja(){
        //stworz
    }
    public static void policzStatystyki() {
        int puste = 0;
        int domy = 0;
        int biurowce = 0;
        int fabryki = 0;
        int sklepy = 0;
        int mieszkancy = 0;
        int miejscaPracy = 0;
        double zadowolenie = 0;
        int budynki=0;

        for (int i = 0; i < Plansza.plansza.length; i++) {
            for (int j = 0; j < Plansza.plansza.length; j++) {
                switch (Plansza.plansza[i][j].typ) {
                    case DOM -> {domy++; mieszkancy=mieszkancy+Plansza.plansza[i][j].obliczUzytkownicy(Plansza.plansza[i][j].poziom);}
                    case SKLEP -> {sklepy++; miejscaPracy=miejscaPracy+Plansza.plansza[i][j].obliczUzytkownicy(Plansza.plansza[i][j].poziom);}
                    case FABRYKA -> {fabryki++; miejscaPracy=miejscaPracy+Plansza.plansza[i][j].obliczUzytkownicy(Plansza.plansza[i][j].poziom);}
                    case BIUROWIEC -> {biurowce++; miejscaPracy=miejscaPracy+Plansza.plansza[i][j].obliczUzytkownicy(Plansza.plansza[i][j].poziom);}
                    case PUSTE -> puste++;
                }
                if (!Plansza.plansza[i][j].typ.equals(Typ.TRAMWAJ) && !Plansza.plansza[i][j].typ.equals(Typ.PUSTE)) {
                    zadowolenie=zadowolenie+Plansza.plansza[i][j].obliczZadowolenie();
                }
            }
        }
        zadowolenie=zadowolenie/(domy+biurowce+fabryki+sklepy); //srednie zadowolenie

        logBiurowce.add(biurowce);
        logDomy.add(domy);
        logFabryki.add(fabryki);
        logPuste.add(puste);
        logSklepy.add(sklepy);
        logMiejscaPracy.add(miejscaPracy);
        logMieszkancy.add(mieszkancy);
        logZadowolenie.add(zadowolenie);
        logBudynki.add(biurowce+domy+fabryki+sklepy);
    }
    public static void zapiszDoPliku(){
        try {
            FileWriter myWriter = new FileWriter("log_miasta.csv");
            myWriter.write("Runda,Budynki,Domy,Biurowce,Sklepy,Fabryki,Puste,Mieszkancy,Miejsca_pracy,zadowolenie\n");
            for (int i=0;i<logBudynki.size();i++){
                myWriter.write(i+",");
                myWriter.write(logBudynki.get(i)+",");
                myWriter.write(logDomy.get(i)+",");
                myWriter.write(logBiurowce.get(i)+",");
                myWriter.write(logSklepy.get(i)+",");
                myWriter.write(logFabryki.get(i)+",");
                myWriter.write(logPuste.get(i)+",");
                myWriter.write(logMieszkancy.get(i)+",");
                myWriter.write(logMiejscaPracy.get(i)+",");
                myWriter.write(logZadowolenie.get(i)+",\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
