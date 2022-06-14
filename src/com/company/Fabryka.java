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
        if (sasiednieSklep==0)
            zadowolenie=zadowolenie-10;
        if (sasiednieTramwaj==0)
            zadowolenie=zadowolenie-90;
        if (sasiednieFabryka+sasiednieBiurowiec+sasiednieDom+sasiednieSklep>4)
            zadowolenie=zadowolenie-100;
        return zadowolenie;
    }
}
