package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Plansza {

    //miasto bedzie tworzone domyslnie lub wczytujac plik config
    public static int wymiar;
    public static Dzialka[][] plansza;
    public static double zageszczenie;
    public static String filename; //config

    //poziomy relacji
    public static HashMap<String, Integer> poziomyRelacji = new HashMap<String, Integer>();
    //relacje sasiedzkie
    public static HashMap<String, Integer> relacjeSasiadow = new HashMap<String, Integer>();
    //log statystyk:
    public static HashMap<String,ArrayList<Integer>> logger = new HashMap<>();

    //metody
    public static void inicjalizacja(double zag, int wym, String tryb) {

        //inicjalizuje pola
        zageszczenie=zag;
        wymiar=wym;
        plansza = new Dzialka[wym][wym];
        String filename = "config.txt";

        poziomyRelacji.put("plus2", 10);
        poziomyRelacji.put("plus1", 3);
        poziomyRelacji.put("zero0", 0);
        poziomyRelacji.put("minus1", -1);
        poziomyRelacji.put("minus2", -3);

        relacjeSasiadow.put("DB",poziomyRelacji.get("zero0"));
        relacjeSasiadow.put("DF",poziomyRelacji.get("minus2"));
        relacjeSasiadow.put("DS",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("DP",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("DT",poziomyRelacji.get("plus2")*2);
        relacjeSasiadow.put("DD",poziomyRelacji.get("plus2")*2);
        relacjeSasiadow.put("BD",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("BS",poziomyRelacji.get("zero0"));
        relacjeSasiadow.put("BF",poziomyRelacji.get("minus1"));
        relacjeSasiadow.put("BB",poziomyRelacji.get("minus1"));
        relacjeSasiadow.put("FS",poziomyRelacji.get("plus2"));
        relacjeSasiadow.put("FD",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("FB",poziomyRelacji.get("minus1"));
        relacjeSasiadow.put("FF",poziomyRelacji.get("minus2"));
        relacjeSasiadow.put("SD",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("SB",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("SF",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("SS",poziomyRelacji.get("minus2"));

        //tworzy plansze i populuje ją losowymi budynkami wg parametrow
        int losujWybierzBudynek = 0;
        int losujTramwaj = ((int) (Math.random() * (wymiar - 2)) + 1); //wybiera jeden rzad i kolumne ktore nie są brzegowe
        double losujZageszczenie;


        if (tryb.equals("domy")||tryb.equals("sklepy")||tryb.equals("biurowce")||tryb.equals("fabryki")) {
            //zapelnij plansze samymi budynkami jednego typu
            for (int i = 0; i < plansza.length; i++) {
                for (int j = 0; j < plansza.length; j++) {

                    losujWybierzBudynek = ((int) (Math.random() * 4) + 1);
                    losujZageszczenie = Math.random();
                    if (losujZageszczenie < zageszczenie) {
                        switch (tryb) {
                            case "domy" -> plansza[i][j] = new Dom(i, j);
                            case "biurowce" -> plansza[i][j] = new Biurowiec(i, j);
                            case "sklepy" -> plansza[i][j] = new Sklep(i, j);
                            case "fabryki" -> plansza[i][j] = new Fabryka(i, j);
                        }
                    } else plansza[i][j] = new PustaDzialka(i, j);
                }
            }

            //wybuduj linie tramwajowe
            for (int i = 0; i < plansza.length; i++) {
                plansza[i][losujTramwaj] = new Tramwaj(i, losujTramwaj);
                plansza[losujTramwaj][i] = new Tramwaj(losujTramwaj, i);
            }
        }
        else if (tryb.equals("wczytaj")){
            //wczytuje gotowa plansze
            try {
                File myObj = new File("plansza_init.csv");
                Scanner myReader = new Scanner(myObj);
                myReader.useDelimiter(",");
                for (int i = 0; i < plansza.length; i++) {
                    for (int j = 0; j < plansza.length; j++) {
                        switch (myReader.next()){
                            case "d" -> plansza[i][j] = new Dom(i, j);
                            case "b" -> plansza[i][j] = new Biurowiec(i, j);
                            case "s" -> plansza[i][j] = new Sklep(i, j);
                            case "f" -> plansza[i][j] = new Fabryka(i, j);
                        }
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            //wybuduj linie tramwajowe
            for (int i = 0; i < plansza.length; i++) {
                plansza[i][losujTramwaj] = new Tramwaj(i, losujTramwaj);
                plansza[losujTramwaj][i] = new Tramwaj(losujTramwaj, i);
            }
        }
        else  {
            //zapelnij plansze losowymi budynkami i pustymi polami
            for (int i = 0; i < plansza.length; i++) {
                for (int j = 0; j < plansza.length; j++) {

                    losujWybierzBudynek = ((int) (Math.random() * 4) + 1);
                    losujZageszczenie = Math.random();
                    if (losujZageszczenie < zageszczenie) {
                        switch (losujWybierzBudynek) {
                            case 1 -> plansza[i][j] = new Dom(i, j);
                            case 2 -> plansza[i][j] = new Biurowiec(i, j);
                            case 3 -> plansza[i][j] = new Sklep(i, j);
                            case 4 -> plansza[i][j] = new Fabryka(i, j);
                        }
                    } else plansza[i][j] = new PustaDzialka(i, j);
                }
            }

            //wybuduj linie tramwajowe
            for (int i = 0; i < plansza.length; i++) {
                plansza[i][losujTramwaj] = new Tramwaj(i, losujTramwaj);
                plansza[losujTramwaj][i] = new Tramwaj(losujTramwaj, i);
            }
        }

        odswierzPlansze();
        Log.policzStatystyki();
    }

    public static void wyswietlPlansze() {
        for (int i = 0; i < plansza.length; i++) {
            System.out.println();
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j].typ.equals(Typ.TRAMWAJ))
                    System.out.print("H\t");
                if (plansza[i][j].typ.equals(Typ.DOM))
                    System.out.print("\uD83C\uDFE0\t");
                if (plansza[i][j].typ.equals(Typ.SKLEP))
                    System.out.print("\uD83D\uDED2\t");
                if (plansza[i][j].typ.equals(Typ.BIUROWIEC))
                    System.out.print("\uD83D\uDCBC\t");
                if (plansza[i][j].typ.equals(Typ.FABRYKA))
                    System.out.print("\uD83C\uDFED\t");
                if (plansza[i][j].typ.equals(Typ.PUSTE))
                    System.out.print(".\t");
            }
        }
        Log.wypiszStatystyki();
    }

    public static void wyswietlZadowolenie() {
        for (int i = 0; i < plansza.length; i++) {
            System.out.println();
            for (int j = 0; j < plansza.length; j++) {
                System.out.printf("%+04.0f ", plansza[i][j].zadowolenie);
            }
        }
    }

    public static int policzWSasiedztwie(int x, int y, Typ mojTyp) {
        int ilosc = 0;

        if (x - 1 >= 0 && y - 1 >= 0 && plansza[x - 1][y - 1].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x - 1][y - 1].poziom;
        if (x - 1 >= 0 && y >= 0 && plansza[x - 1][y].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x - 1][y].poziom;
        if (x - 1 >= 0 && y + 1 < plansza.length && plansza[x - 1][y + 1].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x - 1][y + 1].poziom;

        if (y - 1 >= 0 && plansza[x][y - 1].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x][y - 1].poziom;
        if (y + 1 < plansza.length && plansza[x][y + 1].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x][y + 1].poziom;

        if (x + 1 < plansza.length && y - 1 >= 0 && plansza[x + 1][y - 1].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x + 1][y - 1].poziom;
        if (x + 1 < plansza.length && y >= 0 && plansza[x + 1][y].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x + 1][y].poziom;
        if (x + 1 < plansza.length && y + 1 < plansza.length && plansza[x + 1][y + 1].typ.equals(mojTyp))
            ilosc = ilosc + plansza[x + 1][y + 1].poziom;

        //sasiedztwo 3x3, jak nie bedzie za wolne to sprobowac 5x5
//        for (int i=x-1;i<=x+1;i++){
//            for (int j=y-1;j<=y+1;y++){
//                if (i<0||i>= getPlansza().length||j<0||j>= getPlansza().length)
//                    continue;
//                if (getPlansza()[i][j].typ.equals(mojTyp))
//                    ilosc=ilosc+ getPlansza()[i][j].poziom;
//            }
//        }
        return ilosc;
    }

    public static void odswierzPlansze() {
        //przechodzi pętlą po planszy i dla kazdego obiektu przelicza zadowolenie, populacje
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (!plansza[i][j].typ.equals(Typ.TRAMWAJ) && !plansza[i][j].typ.equals(Typ.PUSTE)) {
                    plansza[i][j].obliczZadowolenie();
                    plansza[i][j].obliczUzytkownicy(plansza[i][j].poziom);
                }
            }
        }
    }
}