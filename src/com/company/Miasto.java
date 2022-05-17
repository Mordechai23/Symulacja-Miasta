package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Miasto {
    //miasto bedzie tworzone domyslnie lub wczytujac plik config
    private static int wymiar=20;
    private static Dzialka[][] plansza = new Dzialka[wymiar][wymiar];
    private int runda;
    private static int limitRund=4000;
    static double zageszczenie=1;
    private static final String filename="config.txt"; //config

    //poziomy relacji
    private static double plus2=5;
    private static double plus1=1;
    private static double zero0=0;
    private static double minus1=-1;
    private static double minus2=-10;

    //relacje sasiedzkie
    private static double DB=zero0;
    private static double DF=minus2;
    private static double DS=plus1;
    private static double DP=plus1;
    private static double DT=plus2*5;
    private static double DD=plus2*5;
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
        wykonajSymulacje(limitRund);
    }
    public static void inicjalizacja(){
        //tworzy plansze i populuje ją losowymi budynkami wg parametrow
        int losujWybierzBudynek=0;
        int losujTramwaj=((int) (Math.random() * (wymiar-2))+1); //wybiera jeden rzad i kolumne ktore nie są brzegowe
        double losujZageszczenie;

        //zapelnij plansze budynkami i pustymi polami
        for (int i=0;i<plansza.length;i++){
            for (int j=0; j<plansza.length; j++){

                losujWybierzBudynek =((int) (Math.random() * 4) + 1);
                losujZageszczenie=Math.random();
                if (losujZageszczenie<zageszczenie) {
                    switch (losujWybierzBudynek) {
                        case 1 -> plansza[i][j] = new Dom(i, j);
                        case 2 -> plansza[i][j] = new Biurowiec(i, j);
                        case 3 -> plansza[i][j] = new Sklep(i, j);
                        case 4 -> plansza[i][j] = new Fabryka(i, j);
                    }
                }
                else plansza[i][j]=new PustaDzialka(i,j);
            }
        }

        //wybuduj linie tramwajowe
        for (int i=0; i<plansza.length;i++){
            plansza[i][losujTramwaj]=new Tramwaj(i,losujTramwaj);
            plansza[losujTramwaj][i]=new Tramwaj(losujTramwaj,i);
        }
        odswierzPlansze();
    }
    public static void wykonajTure(Typ mojTyp){
        //wykonuje ture jednego gracza
        //podczas tury: burzy 1 budynek, buduje 1 losowego typu, ulepsza 1, obniża poziom 1go

        //zbuduj budynek na pustym polu
        boolean znalezionoPuste=false;
        for (int i=0;i<plansza.length;i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j].typ.equals(Typ.PUSTE) && !znalezionoPuste){
                    znalezionoPuste=true;
                    switch (mojTyp){
                        case DOM -> plansza[i][j]=new Dom(i,j);
                        case SKLEP -> plansza[i][j]=new Sklep(i,j);
                        case FABRYKA -> plansza[i][j]=new Fabryka(i,j);
                        case BIUROWIEC -> plansza[i][j]=new Biurowiec(i,j);
                    }
                    break;
                }
                if (znalezionoPuste) break;
            }
        }
        //jesli nie znajdzie pustego pola, to je stworzy i wybuduje tam budynek
        if (!znalezionoPuste){
            int[] wsp;
            wsp=wyburzNajgorsze();
            switch (mojTyp) {
                case DOM -> plansza[wsp[0]][wsp[1]] = new Dom(wsp[0], wsp[1]);
                case SKLEP -> plansza[wsp[0]][wsp[1]] = new Sklep(wsp[0], wsp[1]);
                case FABRYKA -> plansza[wsp[0]][wsp[1]] = new Fabryka(wsp[0], wsp[1]);
                case BIUROWIEC -> plansza[wsp[0]][wsp[1]] = new Biurowiec(wsp[0], wsp[1]);
            }
        }
        //ulepsz budynek
        for (int i=0;i<plansza.length;i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j].typ.equals(mojTyp) && plansza[i][j].zadowolenie>5){
                    plansza[i][j].podniesPoziom();
                    break;
                }
            }
        }
        //zmniejsz poziom budynku
        for (int i=0;i<plansza.length;i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (plansza[i][j].typ.equals(mojTyp) && plansza[i][j].zadowolenie<1){
                    plansza[i][j].zmniejszPoziom();
                    break;
                }
            }
        }

        odswierzPlansze();
    }
    public static void wykonajRunde(){
        //wykonuje 4 tury, po jednej dla kazdego gracza
        wykonajTure(Typ.DOM);
        wykonajTure(Typ.BIUROWIEC);
        wykonajTure(Typ.SKLEP);
        wykonajTure(Typ.FABRYKA);
    }
    public static void wykonajSymulacje(int rundy){
        //wykonuje N=limitRund rund
        Scanner scanner = new Scanner(System.in);
        System.out.println("rozpocząć symulacje?");
        scanner.next();
        for (int i=1; i<=limitRund; i++){

            wykonajRunde();
            wyswietlPlansze();
            wyswietlZadowolenie();
            System.out.println();
           // scanner.next();
        }
    }
    public void policzStatystyki(){
        int puste=0;
        int domy=0;
        int biurowce=0;
        int fabryki=0;
        int sklepy=0;
        int mieszkancy=0;
        int miejscaPracy=0;
        double zadowolenie=0;
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
    public static void wyswietlZadowolenie(){
        for (int i=0;i<plansza.length;i++) {
            System.out.println();
            for (int j = 0; j < plansza.length; j++) {
                    System.out.printf("%+03.0f ", plansza[i][j].zadowolenie);
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

        if (x-1>=0 && y-1>=0 && plansza[x-1][y-1].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x-1][y-1].poziom;
        if (x-1>=0 && y>=0 && plansza[x-1][y].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x-1][y].poziom;
        if (x-1>=0 && y+1<plansza.length && plansza[x-1][y+1].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x-1][y+1].poziom;

        if (y-1>=0 && plansza[x][y-1].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x][y-1].poziom;
        if (y+1<plansza.length && plansza[x][y+1].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x][y+1].poziom;

        if (x+1<plansza.length && y-1>=0 && plansza[x+1][y-1].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x+1][y-1].poziom;
        if (x+1<plansza.length && y>=0 && plansza[x+1][y].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x+1][y].poziom;
        if (x+1<plansza.length && y+1<plansza.length && plansza[x+1][y+1].typ.equals(mojTyp))
            ilosc=ilosc+ getPlansza()[x+1][y+1].poziom;

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
    public static void odswierzPlansze(){
        //przechodzi pętlą po planszy i dla kazdego obiektu przelicza zadowolenie, populacje
        for (int i=0;i<plansza.length;i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (!plansza[i][j].typ.equals(Typ.TRAMWAJ) && !plansza[i][j].typ.equals(Typ.PUSTE)){
                    plansza[i][j].obliczZadowolenie();
                    plansza[i][j].obliczUzytkownicy(plansza[i][j].poziom);
                }
            }
        }
    }
    public static int[] wyburzNajgorsze(){
        //znajduje budynek o najmniejszym zadowoleniu i zmienia go w pustą działkę
        //zwraca polozenie pustej dzialki

        int x=0,y=0;
        double zadowolenie=999;

        for (int i=0;i<plansza.length;i++) {
            for (int j = 0; j < plansza.length; j++) {
                if (!plansza[i][j].typ.equals(Typ.TRAMWAJ) && !plansza[i][j].typ.equals(Typ.PUSTE) && plansza[i][j].zadowolenie < zadowolenie) {
                    zadowolenie = plansza[i][j].zadowolenie;
                    x = i;
                    y = j;
                }
            }
        }

        plansza[x][y] = new PustaDzialka(x,y);
        int[] wsp = new int[2];
        wsp[0]=x;
        wsp[1]=y;
        return wsp;
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
        Miasto.plus2 = plus2;
    }
    public double getPlus1() {
        return plus1;
    }
    public void setPlus1(double plus1) {
        Miasto.plus1 = plus1;
    }
    public double getZero0() {
        return zero0;
    }
    public void setZero0(double zero0) {
        Miasto.zero0 = zero0;
    }
    public double getMinus1() {
        return minus1;
    }
    public void setMinus1(double minus1) {
        Miasto.minus1 = minus1;
    }
    public double getMinus2() {
        return minus2;
    }
    public void setMinus2(double minus2) {
        Miasto.minus2 = minus2;
    }
    public static double getDS() {
        return DS;
    }
    public void setDS(double DS) {
        Miasto.DS = DS;
    }
    public static double getDP() {
        return DP;
    }
    public void setDP(double DP) {
        Miasto.DP = DP;
    }
    public static double getDT() {
        return DT;
    }
    public void setDT(double DT) {
        Miasto.DT = DT;
    }
    public static double getDD() {
        return DD;
    }
    public void setDD(double DD) {
        Miasto.DD = DD;
    }
    public static double getBD() {
        return BD;
    }
    public void setBD(double BD) {
        Miasto.BD = BD;
    }
    public static double getBS() {
        return BS;
    }
    public void setBS(double BS) {
        Miasto.BS = BS;
    }
    public static double getBF() {
        return BF;
    }
    public void setBF(double BF) {
        Miasto.BF = BF;
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
        Miasto.FS = FS;
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