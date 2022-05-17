package com.company;

public class Sklep extends Budynek{
    public Sklep(int x, int y){
        typ=Typ.SKLEP;
        poziom=1;

        this.x=x;
        this.y=y;
    }
    void obliczUzytkownicy(int poziom) {
        uzytkownicy= (poziom*2);
    }
    void obliczZadowolenie() {
        ustawSasiadow();
        zadowolenie= (Miasto.getSB() * sasiednieBiurowiec)
                + (Miasto.getSD() * sasiednieDom)
                + (Miasto.getSF() * sasiednieFabryka)
                + (Miasto.getSS() * sasiednieSklep);
    }
}
