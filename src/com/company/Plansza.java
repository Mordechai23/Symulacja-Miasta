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
    public int runda;
    public static int limitRund;
    public static double zageszczenie;
    public static String filename; //config

    //poziomy relacji
    public HashMap<String, Integer> poziomyRelacji = new HashMap<String, Integer>();

    //relacje sasiedzkie
    public static HashMap<String, Integer> relacjeSasiadow = new HashMap<String, Integer>();

    //log statystyk:
    public HashMap<String,ArrayList<Integer>> logger = new HashMap<>();

    //KONSTRUKTOR
    public Plansza(double zag, int limitR, int wym) {
        zageszczenie=zag;
        limitRund=limitR;
        wymiar=wym;
        Dzialka[][] plansza = new Dzialka[wym][wym];
        String filename = "config.txt";

        poziomyRelacji.put("plus2", 5);
        poziomyRelacji.put("plus1", 1);
        poziomyRelacji.put("zero0", 0);
        poziomyRelacji.put("minus1", -1);
        poziomyRelacji.put("minus2", -8);

        relacjeSasiadow.put("DB",poziomyRelacji.get("zero0"));
        relacjeSasiadow.put("DF",poziomyRelacji.get("minus22"));
        relacjeSasiadow.put("DS",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("DP",poziomyRelacji.get("plus1"));
        relacjeSasiadow.put("DT",poziomyRelacji.get("plus2")*5);
        relacjeSasiadow.put("DD",poziomyRelacji.get("plus2")*5);
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

        ArrayList<Integer> logPuste          = new ArrayList<Integer>();
        ArrayList<Integer> logDomy           = new ArrayList<Integer>();
        ArrayList<Integer> logBiurowce          = new ArrayList<Integer>();
        ArrayList<Integer> logFabryki           = new ArrayList<Integer>();
        ArrayList<Integer> logSklepy        = new ArrayList<Integer>();
        ArrayList<Integer> logMieszkancy        = new ArrayList<Integer>();
        ArrayList<Integer> logMiejscaPracy      = new ArrayList<Integer>();

        ArrayList<Double>  logZadowolenie       = new ArrayList<Double>(); //poza mapą!!!

        logger.put("logPuste",logPuste);
        logger.put("logDomy",logDomy);
        logger.put("logBiurowce",logBiurowce);
        logger.put("logFabryki",logFabryki);
        logger.put("logSklepy",logSklepy);
        logger.put("logMieszkancy",logMieszkancy);
        logger.put("logMiejscaPracy",logMiejscaPracy);
    }

    //metody
    public static void main(String[] args) {
        inicjalizacja();
        wykonajSymulacje(limitRund);
    }

    public static void inicjalizacja() {
        //tworzy plansze i populuje ją losowymi budynkami wg parametrow
        int losujWybierzBudynek = 0;
        int losujTramwaj = ((int) (Math.random() * (wymiar - 2)) + 1); //wybiera jeden rzad i kolumne ktore nie są brzegowe
        double losujZageszczenie;

        //zapelnij plansze budynkami i pustymi polami
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
        odswierzPlansze();
    }

    public static void wykonajTure(Typ mojTyp) {
        //wykonuje ture jednego gracza
        //podczas tury: burzy 1 budynek, buduje 1 losowego typu, ulepsza 1, obniża poziom 1go

        //zbuduj budynek na pustym polu
        boolean znalezionoPuste = false;
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j].typ.equals(Typ.PUSTE) && !znalezionoPuste) {
                    znalezionoPuste = true;
                    switch (mojTyp) {
                        case DOM -> plansza[i][j] = new Dom(i, j);
                        case SKLEP -> plansza[i][j] = new Sklep(i, j);
                        case FABRYKA -> plansza[i][j] = new Fabryka(i, j);
                        case BIUROWIEC -> plansza[i][j] = new Biurowiec(i, j);
                    }
                    break;
                }
                if (znalezionoPuste) break;
            }
        }
        //jesli nie znajdzie pustego pola, to je stworzy i wybuduje tam budynek
        if (!znalezionoPuste) {
            int[] wsp;
            wsp = wyburzNajgorsze();
            switch (mojTyp) {
                case DOM -> plansza[wsp[0]][wsp[1]] = new Dom(wsp[0], wsp[1]);
                case SKLEP -> plansza[wsp[0]][wsp[1]] = new Sklep(wsp[0], wsp[1]);
                case FABRYKA -> plansza[wsp[0]][wsp[1]] = new Fabryka(wsp[0], wsp[1]);
                case BIUROWIEC -> plansza[wsp[0]][wsp[1]] = new Biurowiec(wsp[0], wsp[1]);
            }
        }
        //ulepsz budynek
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j].typ.equals(mojTyp) && plansza[i][j].zadowolenie > 5) {
                    plansza[i][j].podniesPoziom();
                    break;
                }
            }
        }
        //zmniejsz poziom budynku
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j].typ.equals(mojTyp) && plansza[i][j].zadowolenie < 1) {
                    plansza[i][j].zmniejszPoziom();
                    break;
                }
            }
        }

        odswierzPlansze();
    }

    public static void wykonajRunde() {
        //wykonuje 4 tury, po jednej dla kazdego gracza
        wykonajTure(Typ.DOM);
        wykonajTure(Typ.BIUROWIEC);
        wykonajTure(Typ.SKLEP);
        wykonajTure(Typ.FABRYKA);
    }

    public static void wykonajSymulacje(int rundy) {
        //wykonuje N=limitRund rund
        Scanner scanner = new Scanner(System.in);
        System.out.println("rozpocząć symulacje?");
        scanner.next();
        for (int i = 1; i <= limitRund; i++) {

            wykonajRunde();
            wyswietlPlansze();
            wyswietlZadowolenie();
            System.out.println();
            // scanner.next();
        }
    }

    public void policzStatystyki() {
        int puste = 0;
        int domy = 0;
        int biurowce = 0;
        int fabryki = 0;
        int sklepy = 0;
        int mieszkancy = 0;
        int miejscaPracy = 0;
        double zadowolenie = 0;
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
    }

    public static void wyswietlZadowolenie() {
        for (int i = 0; i < plansza.length; i++) {
            System.out.println();
            for (int j = 0; j < plansza.length; j++) {
                System.out.printf("%+03.0f ", plansza[i][j].zadowolenie);
            }
        }
    }

    public void zapiszDoPliku() {

    }

    public void wczytajConfig() {
        System.out.println("Wczytywanie danych z pliku " + filename + "...");
        try {
            File myObj = new File(filename);
            Scanner fileScanner = new Scanner(myObj);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(" ");
                ArrayList<Integer> maskLine = new ArrayList<>();
                while (lineScanner.hasNext()) {
                    maskLine.add(lineScanner.nextInt());
                }
                lineScanner.close();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wystąpił błąd.");
            e.printStackTrace();
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

    public static int[] wyburzNajgorsze() {
        //znajduje budynek o najmniejszym zadowoleniu i zmienia go w pustą działkę
        //zwraca polozenie pustej dzialki

        int x = 0, y = 0;
        double zadowolenie = 999;

        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (!plansza[i][j].typ.equals(Typ.TRAMWAJ) && !plansza[i][j].typ.equals(Typ.PUSTE) && plansza[i][j].zadowolenie < zadowolenie) {
                    zadowolenie = plansza[i][j].zadowolenie;
                    x = i;
                    y = j;
                }
            }
        }

        plansza[x][y] = new PustaDzialka(x, y);
        int[] wsp = new int[2];
        wsp[0] = x;
        wsp[1] = y;
        return wsp;
    }
}