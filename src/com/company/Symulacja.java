package com.company;

import java.util.Random;
import java.util.Scanner;

public class Symulacja {
    public static int runda=1;
    public static int limitRund=1600;

    public static void main(String[] args) {
        Plansza.inicjalizacja(0,20,"wczytaj");
        wykonajSymulacje(limitRund, 0);
    }

    public static void wykonajTure(Typ mojTyp) {
        //wykonuje ture jednego gracza
        //podczas tury: burzy 1 budynek, buduje 1 losowego typu, ulepsza 1, obniża poziom 1go

        Random random=new Random();
        int losujAkcje = 1 + (int)(Math.random() * ((100 - 1) + 1));

        //zbuduj budynek na pustym polu
        if (losujAkcje==1000) {
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
        }
        //wyburz najgorszy budynek i postaw tam nowy
        if (losujAkcje<20) {
            int[] wsp;
            wsp = wyburzNajgorsze();
            wybudujBudynek(mojTyp, wsp);
        }
        //wybuduj obok najlepszego
        if (losujAkcje<=60 && losujAkcje>20) {
            int[] wsp;
            wsp = znajdzNajgorszeObokNajlepszego();
            wybudujBudynek(mojTyp, wsp);
        }
        //wybuduj losowo
        if (losujAkcje>60 && losujAkcje<=100) {
            int losujX = ((int) (Math.random() * Plansza.wymiar));
            int losujY = ((int) (Math.random() * Plansza.wymiar));
            int[] wsp = new int[2];
            wsp[0]=losujX;
            wsp[1]=losujY;
            if (!Plansza.plansza[losujX][losujY].typ.equals(Typ.TRAMWAJ)) {
                wybudujBudynek(mojTyp, wsp);
            }
            else if (!Plansza.plansza[Plansza.plansza.length-1-losujX][losujY].typ.equals(Typ.TRAMWAJ)){
                wsp[0]=Plansza.plansza.length-1-losujX;
                wybudujBudynek(mojTyp, wsp);
            }
            else{
                wsp[0]=Plansza.plansza.length-1-losujX;
                wsp[1]=Plansza.plansza.length-1-losujY;
                wybudujBudynek(mojTyp, wsp);
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

    private static void wybudujBudynek(Typ mojTyp, int[] wsp) {
        switch (mojTyp) {
            case DOM -> {Plansza.plansza[wsp[0]][wsp[1]] = new Dom(wsp[0], wsp[1]);}
            case SKLEP -> {Plansza.plansza[wsp[0]][wsp[1]] = new Sklep(wsp[0], wsp[1]);}
            case FABRYKA -> {Plansza.plansza[wsp[0]][wsp[1]] = new Fabryka(wsp[0], wsp[1]);}
            case BIUROWIEC -> {Plansza.plansza[wsp[0]][wsp[1]] = new Biurowiec(wsp[0], wsp[1]);}
        }
    }

    public static void wykonajRunde() {
        //wykonuje 4 tury, po jednej dla kazdego gracza
        Typ mojTyp;
        int losujTyp = (int)((Math.random()) * 4 + 1);
//        switch (losujTyp){
//            case 0: mojTyp = Typ.DOM;
//            case 1: mojTyp = Typ.FABRYKA;
//            case 2: mojTyp = Typ.BIUROWIEC;
//            case 3: mojTyp = Typ.SKLEP;
//            default: mojTyp = Typ.DOM;
//            break;
//        }
        if (losujTyp==3){
            mojTyp=Typ.DOM;
        }
        else if (losujTyp==1){
            mojTyp=Typ.FABRYKA;
        }
        else if (losujTyp==2){
            mojTyp=Typ.SKLEP;
        }
        else {
            mojTyp=Typ.BIUROWIEC;
        }
        wykonajTure(mojTyp);
        runda++;
        Log.policzStatystyki();
    }

    public static void wykonajSymulacje(int rundy, int sleep) {
        //wykonuje N=limitRund rund
        Scanner scanner = new Scanner(System.in);
        System.out.println("rozpocząć symulacje?");
        scanner.next();
        for (int i = 1; i <= limitRund; i++) {

            wykonajRunde();
            Plansza.wyswietlPlansze();
            //Plansza.wyswietlZadowolenie();
            System.out.println();
            // scanner.next();
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.zapiszDoPliku();
    }

    public static int[] wyburzNajgorsze() {
        //znajduje 1 budynek o najmniejszym zadowoleniu i go wyburza
        //znajduje 1 budynek o najwyzszym zadowoleniu, znajduje najmniej zadowolony obok niego i go wyburza
        //zwraca polozenie pustej dzialki

        int xl = 0, yl = 0;
        double zadowolenieL = 999;
        int wyburzone=0;

        for (int i = 0; i < Plansza.plansza.length; i++) {
            for (int j = 0; j < Plansza.plansza.length; j++) {
                if (!Plansza.plansza[i][j].typ.equals(Typ.TRAMWAJ)  && Plansza.plansza[i][j].zadowolenie < zadowolenieL) {
                    zadowolenieL = Plansza.plansza[i][j].zadowolenie;
                    xl = i;
                    yl = j;
                }
                if (!Plansza.plansza[i][j].typ.equals(Typ.TRAMWAJ)  && Plansza.plansza[i][j].zadowolenie < Log.zadowolenie-20) {
                    Plansza.plansza[i][j] = new PustaDzialka(i, j);
                    wyburzone++;
                }
                if (wyburzone>4) break;
            }
        }

        Plansza.plansza[xl][yl] = new PustaDzialka(xl, yl);
        int[] wsp = new int[2];
        wsp[0] = xl;
        wsp[1] = yl;
        return wsp;
    }

    public static int[] znajdzNajgorszeObokNajlepszego(){
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
                if (!Plansza.plansza[i][j].typ.equals(Typ.TRAMWAJ) && Plansza.plansza[i][j].zadowolenie > zadowolenieH) {
                    zadowolenieH = Plansza.plansza[i][j].zadowolenie;
                    xh = i;
                    yh = j;
                }
            }
        }

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

        Plansza.plansza[xlh][ylh] = new PustaDzialka(xlh, ylh);
        int[] wsp = new int[4];
        wsp[0] = xlh;
        wsp[1] = ylh;
        return wsp;
    }
}
