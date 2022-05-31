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
        zadowolenie= (Plansza.relacjeSasiadow.get("SB") * sasiednieBiurowiec)
                + (Plansza.relacjeSasiadow.get("SD") * sasiednieDom)
                + (Plansza.relacjeSasiadow.get("SF") * sasiednieFabryka)
                + (Plansza.relacjeSasiadow.get("SS") * sasiednieSklep);
    }
}
