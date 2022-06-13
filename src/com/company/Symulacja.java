package com.company;

import java.util.Scanner;

public class Symulacja {
    public static int runda=1;
    public static int limitRund=200;

    public static void main(String[] args) {
        Plansza.inicjalizacja(1,20,"domy");
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
        //jesli nie znajdzie pustego pola, to je stworzy i wybuduje tam 2 budynki
        if (!znalezionoPuste) {
            int[] wsp;
            wsp = wyburzNajgorsze();
            switch (mojTyp) {
                case DOM -> {Plansza.plansza[wsp[0]][wsp[1]] = new Dom(wsp[0], wsp[1]); Plansza.plansza[wsp[2]][wsp[3]] = new Dom(wsp[2], wsp[3]);}
                case SKLEP -> {Plansza.plansza[wsp[0]][wsp[1]] = new Sklep(wsp[0], wsp[1]); Plansza.plansza[wsp[2]][wsp[3]] = new Sklep(wsp[2], wsp[3]);}
                case FABRYKA -> {Plansza.plansza[wsp[0]][wsp[1]] = new Fabryka(wsp[0], wsp[1]); Plansza.plansza[wsp[2]][wsp[3]] = new Fabryka(wsp[2], wsp[3]);}
                case BIUROWIEC -> {Plansza.plansza[wsp[0]][wsp[1]] = new Biurowiec(wsp[0], wsp[1]); Plansza.plansza[wsp[2]][wsp[3]] = new Biurowiec(wsp[2], wsp[3]);}
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

    }

    public static void wykonajRunde() {
        //wykonuje 4 tury, po jednej dla kazdego gracza
        wykonajTure(Typ.DOM);
        wykonajTure(Typ.BIUROWIEC);
        wykonajTure(Typ.SKLEP);
        wykonajTure(Typ.FABRYKA);
        runda++;
        Log.policzStatystyki();
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
        //znajduje 1 budynek o najmniejszym zadowoleniu i go wyburza
        //znajduje 1 budynek o najwyzszym zadowoleniu, znajduje najmniej zadowolony obok niego i go wyburza
        //zwraca polozenie pustej dzialki

        int xl = 0, yl = 0;
        int xh = 0, yh = 0;
        int xlh =0, ylh= 0;
        double zadowolenieL = 999;
        double zadowolenieH = -999;

        for (int i = 0; i < Plansza.plansza.length; i++) {
            for (int j = 0; j < Plansza.plansza.length; j++) {
                if (!Plansza.plansza[i][j].typ.equals(Typ.TRAMWAJ) && !Plansza.plansza[i][j].typ.equals(Typ.PUSTE) && Plansza.plansza[i][j].zadowolenie < zadowolenieL) {
                    zadowolenieL = Plansza.plansza[i][j].zadowolenie;
                    xl = i;
                    yl = j;
                }
                if (!Plansza.plansza[i][j].typ.equals(Typ.TRAMWAJ) && !Plansza.plansza[i][j].typ.equals(Typ.PUSTE) && Plansza.plansza[i][j].zadowolenie > zadowolenieH) {
                    zadowolenieH = Plansza.plansza[i][j].zadowolenie;
                    xh = i;
                    yh = j;
                }
            }
        }

        zadowolenieL=999;
        for (int x=xh-1;x<xh+1;x++){
            for (int y=yh-1;y<yh+1;y++){
                if (x>-1 && x<Plansza.plansza.length && y>-1 && y<Plansza.plansza.length){
                    if (!Plansza.plansza[x][y].typ.equals(Typ.TRAMWAJ) && !Plansza.plansza[x][y].typ.equals(Typ.PUSTE) && Plansza.plansza[x][y].zadowolenie < zadowolenieL) {
                        zadowolenieL = Plansza.plansza[x][y].zadowolenie;
                        xlh = x;
                        ylh = y;
                    }
                }
            }
        }

        Plansza.plansza[xl][yl] = new PustaDzialka(xl, yl);
        Plansza.plansza[xlh][ylh] = new PustaDzialka(xh, yh);
        int[] wsp = new int[4];
        wsp[0] = xl;
        wsp[1] = yl;
        wsp[2] = xlh;
        wsp[3] = ylh;
        return wsp;
    }
}
