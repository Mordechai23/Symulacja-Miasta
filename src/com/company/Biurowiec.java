package com.company;

public class Biurowiec extends Budynek {
    public Biurowiec(int x, int y){
        typ=Typ.BIUROWIEC;
        poziom=1;
        this.x=x;
        this.y=y;
    }
    void obliczUzytkownicy(int poziom) {
        uzytkownicy=(poziom+1)^2+14;
    }
    void obliczZadowolenie() {
        ustawSasiadow();
        zadowolenie= (Plansza.relacjeSasiadow.get("BB") * sasiednieBiurowiec)
                + (Plansza.relacjeSasiadow.get("BD") * sasiednieDom)
                + (Plansza.relacjeSasiadow.get("BF") * sasiednieFabryka)
                + (Plansza.relacjeSasiadow.get("BS") * sasiednieSklep);
    }
}
