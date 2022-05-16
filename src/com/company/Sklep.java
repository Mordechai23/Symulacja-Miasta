package com.company;

public class Sklep extends Budynek{
    public Sklep(int x, int y){
        typ=Typ.SKLEP;
        poziom=1;
        uzytkownicy=obliczUzytkownicy(poziom);
        wspolrzedne[0]=x;
        wspolrzedne[1]=y;
        ustawSasiadow();
    }
    int obliczUzytkownicy(int poziom) {
        return (poziom*2);
    }
    double obliczZadowolenie() {
        return (Miasto.getSB() * sasiednieBiurowiec)
                + (Miasto.getSD() * sasiednieDom)
                + (Miasto.getSF() * sasiednieFabryka)
                + (Miasto.getSS() * sasiednieSklep);
    }
}
