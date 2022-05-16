package com.company;

public class Fabryka extends Budynek{
    public Fabryka(int x, int y){
        typ=Typ.FABRYKA;
        poziom=1;
        uzytkownicy=obliczUzytkownicy(poziom);
        wspolrzedne[0]=x;
        wspolrzedne[1]=y;
        ustawSasiadow();
    }
    int obliczUzytkownicy(int poziom) {
        return (poziom*20)+poziom^2;
    }
    double obliczZadowolenie() {
        return (Miasto.getFB() * sasiednieBiurowiec)
                + (Miasto.getFD() * sasiednieDom)
                + (Miasto.getFF() * sasiednieFabryka)
                + (Miasto.getFS() * sasiednieSklep);
    }
}
