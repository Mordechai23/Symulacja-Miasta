package com.company;

public class Dom extends Budynek{
    public Dom(int x, int y){
        typ=Typ.DOM;
        poziom=1;
        this.x=x;
        this.y=y;
    }
    void obliczUzytkownicy(int poziom){uzytkownicy= poziom^2+2;
    }
    void obliczZadowolenie() {
        ustawSasiadow();
        zadowolenie=(Plansza.relacjeSasiadow.get("DB") * sasiednieBiurowiec)
                + (Plansza.relacjeSasiadow.get("DD") * sasiednieDom)
                + (Plansza.relacjeSasiadow.get("DF") * sasiednieFabryka)
                + (Plansza.relacjeSasiadow.get("DP") * sasiedniePuste)
                + (Plansza.relacjeSasiadow.get("DS") * sasiednieSklep)
                + (Plansza.relacjeSasiadow.get("DT") * sasiednieTramwaj);

    }

}
