package com.company;

abstract class Dzialka {
    int poziom; //1-5
//    int[] wspolrzedne; //(x,y)
    int x, y;
    Typ typ;
    double zadowolenie;

    abstract void obliczUzytkownicy(int poziom);
    abstract void obliczZadowolenie();
    abstract void ustawSasiadow();
    abstract void podniesPoziom();
}

