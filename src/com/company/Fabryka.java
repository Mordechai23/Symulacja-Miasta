package com.company;

public class Fabryka extends Budynek{
    public Fabryka(int x, int y){
        typ=Typ.FABRYKA;
        poziom=1;
        this.x=x;
        this.y=y;
    }
    int obliczUzytkownicy(int poziom) {
        uzytkownicy= (poziom*20)+poziom^2;
        return uzytkownicy;
    }
    double obliczZadowolenie() {
        ustawSasiadow();
        zadowolenie= (Plansza.relacjeSasiadow.get("FB") * sasiednieBiurowiec)
                + (Plansza.relacjeSasiadow.get("FD") * sasiednieDom)
                + (Plansza.relacjeSasiadow.get("FF") * sasiednieFabryka)
                + (Plansza.relacjeSasiadow.get("FS") * sasiednieSklep);
        if (sasiednieDom==0)
            zadowolenie=zadowolenie-150;
        return zadowolenie;
    }
}
