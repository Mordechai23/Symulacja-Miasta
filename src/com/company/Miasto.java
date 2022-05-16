package com.company;

import java.util.ArrayList;

public class Miasto {
    //miasto bedzie tworzone domyslnie lub wczytujac plik config

    int wymiar=10;
    Dzialka[][] Plansza = new Dzialka[wymiar][wymiar]; //tablica NxN obiektów dzialka
    int runda;
    int limitRund;

    //poziomy relacji
    double plus2, plus1, poziom0, minus1, minus2;

    //relacje sasiedzkie
    double  DB, DF, DS, DP, DT, //w formacie opiniujący->opiniowany
            BD, BS, BF,
            FS, FD, FB,
            SD, SB, SF;

    //log statystyk:
    ArrayList<Integer> logPuste=new ArrayList<Integer>();
    ArrayList<Integer> logDomy=new ArrayList<Integer>();
    ArrayList<Integer> logBiurowce=new ArrayList<Integer>();
    ArrayList<Integer> logFabryki=new ArrayList<Integer>();
    ArrayList<Integer> logSklepy=new ArrayList<Integer>();
    ArrayList<Integer> logMieszkancy=new ArrayList<Integer>();
    ArrayList<Integer> logMiejscaPracy=new ArrayList<Integer>();
    ArrayList<Double>  logZadowolenie=new ArrayList<Double>();

    public void Inicjalizacja(){
        //tworzy plansze i populuje ją losowymi budynkami wg parametrow
    }
    public void wykonajTure(){
        //wykonuje ture jednego gracza
        //podczas tury: burzy 1 budynek, buduje 1 losowego typu, ulepsza 1, obniża poziom 1go
    }
    public void wykonajRunde(){
        //wykonuje 4 tury, po jednej dla kazdego gracza
    }
    public void wykonajSymulacje(){
        //wykonuje N=limitRund rund
    }
    public void policzStatystyki(){
        int puste;
        int domy;
        int biurowce;
        int fabryki;
        int sklepy;
        int mieszkancy;
        int miejscaPracy;
        double zadowolenie;
    }
    public void wyswietlPlansze(){

    }
    public void zapiszDoPliku(){

    }
    public void wczytajConfig(){

    }
}
