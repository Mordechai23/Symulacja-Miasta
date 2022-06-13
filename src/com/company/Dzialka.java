package com.company;

abstract class Dzialka {
    int poziom; //1-5
//    int[] wspolrzedne; //(x,y)
    int x, y;
    Typ typ;
    double zadowolenie;

    abstract int obliczUzytkownicy(int poziom);
    abstract double obliczZadowolenie();
    abstract void ustawSasiadow();
    abstract void podniesPoziom();
    abstract void zmniejszPoziom();
}

