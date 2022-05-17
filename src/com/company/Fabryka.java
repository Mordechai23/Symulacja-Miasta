package com.company;

public class Fabryka extends Budynek{
    public Fabryka(int x, int y){
        typ=Typ.FABRYKA;
        poziom=1;
        this.x=x;
        this.y=y;
    }
    void obliczUzytkownicy(int poziom) {
        uzytkownicy= (poziom*20)+poziom^2;
    }
    void obliczZadowolenie() {
        ustawSasiadow();
        zadowolenie= (Miasto.getFB() * sasiednieBiurowiec)
                + (Miasto.getFD() * sasiednieDom)
                + (Miasto.getFF() * sasiednieFabryka)
                + (Miasto.getFS() * sasiednieSklep);
    }
}
