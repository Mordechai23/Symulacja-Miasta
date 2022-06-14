package com.company;

public class PustaDzialka extends Dzialka {
    public PustaDzialka(int x, int y) {
        this.x = x;
        this.y = y;
        typ = Typ.PUSTE;
        poziom = 1;
        zadowolenie = 0;
    }

    @Override
    int obliczUzytkownicy(int poziom) {
        return 0;
    }

    @Override
    double obliczZadowolenie() {
        return -999;
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