package com.company;

public class Dom extends Budynek{
    public Dom(int x, int y){
        typ=Typ.DOM;
        poziom=1;
        this.x=x;
        this.y=y;
    }
    int obliczUzytkownicy(int poziom){uzytkownicy= poziom^2+2;
        return uzytkownicy;
    }
    double obliczZadowolenie() {
        ustawSasiadow();
        zadowolenie=(Plansza.relacjeSasiadow.get("DB") * sasiednieBiurowiec)
                + (Plansza.relacjeSasiadow.get("DD") * sasiednieDom)
                + (Plansza.relacjeSasiadow.get("DF") * sasiednieFabryka)
                + (Plansza.relacjeSasiadow.get("DP") * sasiedniePuste)
                + (Plansza.relacjeSasiadow.get("DS") * sasiednieSklep)
                + (Plansza.relacjeSasiadow.get("DT") * sasiednieTramwaj);
        if (sasiednieSklep==0)
            zadowolenie=zadowolenie-100;
        if (sasiednieFabryka==0 || sasiednieBiurowiec==0)
            zadowolenie=zadowolenie-50;
        return zadowolenie;
    }

}
