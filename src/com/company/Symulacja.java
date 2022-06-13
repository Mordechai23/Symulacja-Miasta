package com.company;

import java.util.Scanner;

public class Symulacja {
    public int runda=1;
    public static int limitRund=400;

    public static void main(String[] args) {
        Plansza.inicjalizacja(1,20);
        wykonajSymulacje(limitRund);
    }

    public static void wykonajTure(Typ mojTyp) {
        //wykonuje ture jednego gracza
        //podczas tury: burzy 1 budynek, buduje 1 losowego typu, ulepsza 1, obniża poziom 1go

        //zbuduj budynek na pustym polu
        boolean znalezionoPuste = false;
        for (int i = 0; i < Plansza.plansza.length; i++) {
            for (int j = 0; j < Plansza.plansza.length; j++) {
                if (Plansza.plansza[i][j].typ.equals(Typ.PUSTE) && !znalezionoPuste) {
                    znalezionoPuste = true;
                    switch (mojTyp) {
                        case DOM -> Plansza.plansza[i][j] = new Dom(i, j);
                        case SKLEP -> Plansza.plansza[i][j] = new Sklep(i, j);
                        case FABRYKA -> Plansza.plansza[i][j] = new Fabryka(i, j);
                        case BIUROWIEC -> Plansza.plansza[i][j] = new Biurowiec(i, j);
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
                case DOM -> Plansza.plansza[wsp[0]][wsp[1]] = new Dom(wsp[0], wsp[1]);
                case SKLEP -> Plansza.plansza[wsp[0]][wsp[1]] = new Sklep(wsp[0], wsp[1]);
                case FABRYKA -> Plansza.plansza[wsp[0]][wsp[1]] = new Fabryka(wsp[0], wsp[1]);
                case BIUROWIEC -> Plansza.plansza[wsp[0]][wsp[1]] = new Biurowiec(wsp[0], wsp[1]);
            }
        }
        //ulepsz budynek
        for (int i = 0; i < Plansza.plansza.length; i++) {
            for (int j = 0; j < Plansza.plansza.length; j++) {
                if (Plansza.plansza[i][j].typ.equals(mojTyp) && Plansza.plansza[i][j].zadowolenie > 5) {
                    Plansza.plansza[i][j].podniesPoziom();
                    break;
                }
            }
        }
        //zmniejsz poziom budynku
        for (int i = 0; i < Plansza.plansza.length; i++) {
            for (int j = 0; j < Plansza.plansza.length; j++) {
                if (Plansza.plansza[i][j].typ.equals(mojTyp) && Plansza.plansza[i][j].zadowolenie < 1) {
                    Plansza.plansza[i][j].zmniejszPoziom();
                    break;
                }
            }
        }
        Plansza.odswierzPlansze();
        Log.policzStatystyki();
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
            Plansza.wyswietlPlansze();
            Plansza.wyswietlZadowolenie();
            System.out.println();
            // scanner.next();
        }
        Log.zapiszDoPliku();
    }

    public static int[] wyburzNajgorsze() {
        //znajduje budynek o najmniejszym zadowoleniu i zmienia go w pustą działkę
        //zwraca polozenie pustej dzialki

        int x = 0, y = 0;
        double zadowolenie = 999;

        for (int i = 0; i < Plansza.plansza.length; i++) {
            for (int j = 0; j < Plansza.plansza.length; j++) {
                if (!Plansza.plansza[i][j].typ.equals(Typ.TRAMWAJ) && !Plansza.plansza[i][j].typ.equals(Typ.PUSTE) && Plansza.plansza[i][j].zadowolenie < zadowolenie) {
                    zadowolenie = Plansza.plansza[i][j].zadowolenie;
                    x = i;
                    y = j;
                }
            }
        }

        Plansza.plansza[x][y] = new PustaDzialka(x, y);
        int[] wsp = new int[2];
        wsp[0] = x;
        wsp[1] = y;
        return wsp;
    }
}
