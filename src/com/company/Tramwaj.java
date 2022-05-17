package com.company;

public class Tramwaj extends Dzialka{
    public Tramwaj(int x, int y){
        this.x=x;
        this.y=y;
        typ=Typ.TRAMWAJ;
        poziom=5;
        zadowolenie=0;
    }
    void obliczUzytkownicy(int poziom) {
    }
    void obliczZadowolenie() {
    }

    @Override
    void ustawSasiadow() {

    }

    @Override
    void podniesPoziom() {

    }
}
