package com.company;

public class Sklep extends Budynek{
    public Sklep(int x, int y){
        typ=Typ.SKLEP;
        poziom=1;

        this.x=x;
        this.y=y;
    }
    int obliczUzytkownicy(int poziom) {
        uzytkownicy= (poziom*2);
        return uzytkownicy;
    }
    double obliczZadowolenie() {
        ustawSasiadow();
        zadowolenie= (Plansza.relacjeSasiadow.get("SB") * sasiednieBiurowiec)
                + (Plansza.relacjeSasiadow.get("SD") * sasiednieDom)
                + (Plansza.relacjeSasiadow.get("SF") * sasiednieFabryka)
                + (Plansza.relacjeSasiadow.get("SS") * sasiednieSklep);
        if (sasiednieDom==0)
            zadowolenie=zadowolenie-150;
        return zadowolenie;
    }
}
