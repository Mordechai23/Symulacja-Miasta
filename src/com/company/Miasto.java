package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Miasto {
    //miasto bedzie tworzone domyslnie lub wczytujac plik config
    private static int wymiar=10;
    private static Dzialka[][] plansza = new Dzialka[wymiar][wymiar];
    private int runda;
    private int limitRund=100;
    static double zageszczenie=0.3;
    private static final String filename="config.txt"; //config

    //poziomy relacji
    private static double plus2=1;
    private static double plus1=0.7;
    private static double zero0=0;
    private static double minus1=-0.7;
    private static double minus2=-1;

    //relacje sasiedzkie
    private static double DB=zero0;
    private static double DF=minus2;
    private static double DS=plus1;
    private static double DP=plus1;
    private static double DT=plus2;
    private static double DD=plus2;
    private static double BD=plus1;
    private static double BS=zero0;
    private static double BF=minus1;
    private static double BB=minus1;
    private static double FS=plus2;
    private static double FD=plus1;
    private static double FB=minus1;
    private static double FF=minus2;
    private static double SD=plus1;
    private static double SB=plus1;
    private static double SF=plus1;
    private static double SS=minus2;

    //log statystyk:
    private ArrayList<Integer> logPuste=new ArrayList<Integer>();
    private ArrayList<Integer> logDomy=new ArrayList<Integer>();
    private ArrayList<Integer> logBiurowce=new ArrayList<Integer>();
    private ArrayList<Integer> logFabryki=new ArrayList<Integer>();
    private ArrayList<Integer> logSklepy=new ArrayList<Integer>();
    private ArrayList<Integer> logMieszkancy=new ArrayList<Integer>();
    private ArrayList<Integer> logMiejscaPracy=new ArrayList<Integer>();
    private ArrayList<Double>  logZadowolenie=new ArrayList<Double>();

    //metody
    public static void main(String[] args){
        inicjalizacja();
        wyswietlPlansze();
    }
    public static void inicjalizacja(){
        //tworzy plansze i populuje ją losowymi budynkami wg parametrow
        int budRandom=0;
        int tramKolumna=((int) (Math.random() * 8)+1);
        double zagRandom;

        //zapelnij plansze budynkami i pustymi polami
        for (int i=0;i<plansza.length;i++){
            for (int j=0; j<plansza.length; j++){

                budRandom =((int) (Math.random() * 4) + 1);
                zagRandom=Math.random();
                if (zagRandom<zageszczenie) {
                    switch (budRandom){
                        case 1: plansza[i][j]=new Dom(i,j); break;
                        case 2: plansza[i][j]=new Biurowiec(i,j); break;
                        case 3: plansza[i][j]=new Sklep(i,j); break;
                        case 4: plansza[i][j]=new Fabryka(i,j); break;
                    }
                }
                else plansza[i][j]=new PustaDzialka(i,j);
            }
        }

        //wybuduj linie tramwajowe
        for (int i=0; i<plansza.length;i++){
            plansza[i][tramKolumna]=new Tramwaj(i,tramKolumna);
            plansza[tramKolumna][i]=new Tramwaj(tramKolumna,i);
        }

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
    public static void wyswietlPlansze(){
        for (int i=0;i<plansza.length;i++) {
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
    public void zapiszDoPliku(){

    }
    public void wczytajConfig(){
        System.out.println("Wczytywanie danych z pliku "+filename+"...");
        try {
            File myObj = new File(filename);
            Scanner fileScanner = new Scanner(myObj);
            while (fileScanner.hasNextLine()) {
                String line=fileScanner.nextLine();

                Scanner lineScanner=new Scanner(line);
                lineScanner.useDelimiter(" ");
                ArrayList<Integer> maskLine=new ArrayList<>();
                while (lineScanner.hasNext()){
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




    //gettery i settery
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
    public static double getDF() {
        return DF;
    }
    public void setDF(double DF) {
        Miasto.DF = DF;
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