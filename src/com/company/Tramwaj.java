package com.company;

public class Tramwaj extends Dzialka{
    public Tramwaj(int x, int y){
        this.x=x;
        this.y=y;
        typ=Typ.TRAMWAJ;
        poziom=5;
        zadowolenie=0;
    }
    int obliczUzytkownicy(int poziom) {
        return poziom;
    }

    @Override
    double obliczZadowolenie() {
        return 0;
    }

    @Override
    void ustawSasiadow() {

    }

    @Override
    void podniesPoziom() {

    }

    @Override
    void zmniejszPoziom() {

    }
}
