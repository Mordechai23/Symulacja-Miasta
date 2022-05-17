package com.company;

public class PustaDzialka extends Dzialka{
    public PustaDzialka(int x, int y){
        this.x=x;
        this.y=y;
        typ=Typ.PUSTE;
        poziom=1;
        zadowolenie=0;
    }
    void obliczUzytkownicy(int poziom) {}
    void obliczZadowolenie() {

    }

    @Override
    void ustawSasiadow() {

    }

    @Override
    void podniesPoziom() {

    }
}
