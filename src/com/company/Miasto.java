package com.company;

import java.util.ArrayList;

public class Miasto {
    //miasto bedzie tworzone domyslnie lub wczytujac plik config

    private static int wymiar=10;
    private static Dzialka[][] plansza = new Dzialka[getWymiar()][getWymiar()]; //tablica NxN obiektów dzialka
    private int runda;
    private int limitRund;

    //poziomy relacji
    private double plus2;
    private double plus1;
    private double zero0;
    private double minus1;
    private double minus2;

    //relacje sasiedzkie
    private static double DB;
    private static double DF;
    private static double DS;
    private static double DP;
    private static double DT;
    private static double DD; //w formacie opiniujący->opiniowany
    private static double BD;
    private static double BS;
    private static double BF;
    private static double BB;
    private static double FS;
    private static double FD;
    private static double FB;
    private static double FF;
    private static double SD;
    private static double SB;
    private static double SF;
    private static double SS;

    //log statystyk:
    private ArrayList<Integer> logPuste=new ArrayList<Integer>();
    private ArrayList<Integer> logDomy=new ArrayList<Integer>();
    private ArrayList<Integer> logBiurowce=new ArrayList<Integer>();
    private ArrayList<Integer> logFabryki=new ArrayList<Integer>();
    private ArrayList<Integer> logSklepy=new ArrayList<Integer>();
    private ArrayList<Integer> logMieszkancy=new ArrayList<Integer>();
    private ArrayList<Integer> logMiejscaPracy=new ArrayList<Integer>();
    private ArrayList<Double>  logZadowolenie=new ArrayList<Double>();

    public static int getWymiar() {
        return wymiar;
    }

    public void setWymiar(int wymiar) {
        Miasto.wymiar = wymiar;
    }
    public static Dzialka[][] getPlansza() {
        return plansza;
    }
    public void setPlansza(Dzialka[][] plansza) {
        Miasto.plansza = plansza;
    }

    public static double getDB() {
        return DB;
    }
    public void setDB(double DB) {
        Miasto.DB = DB;
    }
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
    public static int policzWSasiedztwie(int x, int y, Typ mojTyp){
        int ilosc=0;

        //sasiedztwo 3x3, jak nie bedzie za wolne to sprobowac 5x5
        for (int i=x-1;i<=x+1;i++){
            for (int j=y-1;j<=y+1;y++){
                if (i<0||i> getPlansza().length||j<0||j> getPlansza().length)
                    continue;
                if (getPlansza()[i][j].typ.equals(mojTyp))
                    ilosc=ilosc+ getPlansza()[i][j].poziom;
            }
        }
        return ilosc;
    }

    public static double getDF() {
        return DF;
    }
    public void setDF(double DF) {
        this.DF = DF;
    }
    public int getRunda() {
        return runda;
    }
    public void setRunda(int runda) {
        this.runda = runda;
    }
    public int getLimitRund() {
        return limitRund;
    }
    public void setLimitRund(int limitRund) {
        this.limitRund = limitRund;
    }
    public double getPlus2() {
        return plus2;
    }
    public void setPlus2(double plus2) {
        this.plus2 = plus2;
    }
    public double getPlus1() {
        return plus1;
    }
    public void setPlus1(double plus1) {
        this.plus1 = plus1;
    }
    public double getZero0() {
        return zero0;
    }
    public void setZero0(double zero0) {
        this.zero0 = zero0;
    }
    public double getMinus1() {
        return minus1;
    }
    public void setMinus1(double minus1) {
        this.minus1 = minus1;
    }
    public double getMinus2() {
        return minus2;
    }
    public void setMinus2(double minus2) {
        this.minus2 = minus2;
    }
    public static double getDS() {
        return DS;
    }
    public void setDS(double DS) {
        this.DS = DS;
    }
    public static double getDP() {
        return DP;
    }
    public void setDP(double DP) {
        this.DP = DP;
    }
    public static double getDT() {
        return DT;
    }
    public void setDT(double DT) {
        this.DT = DT;
    }
    public static double getDD() {
        return DD;
    }
    public void setDD(double DD) {
        this.DD = DD;
    }
    public static double getBD() {
        return BD;
    }
    public void setBD(double BD) {
        this.BD = BD;
    }
    public static double getBS() {
        return BS;
    }
    public void setBS(double BS) {
        this.BS = BS;
    }
    public static double getBF() {
        return BF;
    }
    public void setBF(double BF) {
        this.BF = BF;
    }
    public static double getBB() {
        return BB;
    }
    public void setBB(double BB) {
        Miasto.BB = BB;
    }
    public static double getFS() {
        return FS;
    }
    public void setFS(double FS) {
        this.FS = FS;
    }
    public static double getFD() {
        return FD;
    }
    public void setFD(double FD) {
        this.FD = FD;
    }
    public static double getFB() {
        return FB;
    }
    public void setFB(double FB) {
        this.FB = FB;
    }
    public static double getFF() {
        return FF;
    }
    public void setFF(double FF) {
        this.FF = FF;
    }
    public static double getSD() {
        return SD;
    }
    public void setSD(double SD) {
        this.SD = SD;
    }
    public static double getSB() {
        return SB;
    }
    public void setSB(double SB) {
        this.SB = SB;
    }
    public static double getSF() {
        return SF;
    }
    public void setSF(double SF) {
        this.SF = SF;
    }
    public static double getSS() {
        return SS;
    }
    public void setSS(double SS) {
        this.SS = SS;
    }
    public ArrayList<Integer> getLogPuste() {
        return logPuste;
    }
    public void setLogPuste(ArrayList<Integer> logPuste) {
        this.logPuste = logPuste;
    }
    public ArrayList<Integer> getLogDomy() {
        return logDomy;
    }
    public void setLogDomy(ArrayList<Integer> logDomy) {
        this.logDomy = logDomy;
    }
    public ArrayList<Integer> getLogBiurowce() {
        return logBiurowce;
    }
    public void setLogBiurowce(ArrayList<Integer> logBiurowce) {
        this.logBiurowce = logBiurowce;
    }
    public ArrayList<Integer> getLogFabryki() {
        return logFabryki;
    }
    public void setLogFabryki(ArrayList<Integer> logFabryki) {
        this.logFabryki = logFabryki;
    }
    public ArrayList<Integer> getLogSklepy() {
        return logSklepy;
    }
    public void setLogSklepy(ArrayList<Integer> logSklepy) {
        this.logSklepy = logSklepy;
    }
    public ArrayList<Integer> getLogMieszkancy() {
        return logMieszkancy;
    }
    public void setLogMieszkancy(ArrayList<Integer> logMieszkancy) {
        this.logMieszkancy = logMieszkancy;
    }
    public ArrayList<Integer> getLogMiejscaPracy() {
        return logMiejscaPracy;
    }
    public void setLogMiejscaPracy(ArrayList<Integer> logMiejscaPracy) {
        this.logMiejscaPracy = logMiejscaPracy;
    }
    public ArrayList<Double> getLogZadowolenie() {
        return logZadowolenie;
    }
    public void setLogZadowolenie(ArrayList<Double> logZadowolenie) {
        this.logZadowolenie = logZadowolenie;
    }
}