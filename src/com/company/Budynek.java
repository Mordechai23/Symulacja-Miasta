package com.company;

abstract class Budynek extends Dzialka{
    int uzytkownicy;
    double zadowolenie;

    //ilosc sasiadow
    int sasiedniePuste;
    int sasiednieTramwaj;
    int sasiednieDom;
    int sasiednieBiurowiec;
    int sasiednieFabryka;
    int sasiednieSklep;

    abstract int obliczUzytkownicy(int poziom);
    abstract double obliczZadowolenie();
    void ustawSasiadow(){
        sasiedniePuste=Miasto.policzWSasiedztwie(wspolrzedne[0],wspolrzedne[1], Typ.PUSTE);
        sasiednieBiurowiec=Miasto.policzWSasiedztwie(wspolrzedne[0],wspolrzedne[1], Typ.BIUROWIEC);
        sasiednieDom=Miasto.policzWSasiedztwie(wspolrzedne[0],wspolrzedne[1], Typ.DOM);
        sasiednieFabryka=Miasto.policzWSasiedztwie(wspolrzedne[0],wspolrzedne[1], Typ.FABRYKA);
        sasiednieSklep=Miasto.policzWSasiedztwie(wspolrzedne[0],wspolrzedne[1], Typ.SKLEP);
        sasiednieTramwaj=Miasto.policzWSasiedztwie(wspolrzedne[0],wspolrzedne[1], Typ.TRAMWAJ);
    }
}
