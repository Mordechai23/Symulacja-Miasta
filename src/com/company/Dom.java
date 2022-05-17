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
        zadowolenie=(Miasto.getDB() * sasiednieBiurowiec)
                + (Miasto.getDD() * sasiednieDom)
                + (Miasto.getDF() * sasiednieFabryka)
                + (Miasto.getDP() * sasiedniePuste)
                + (Miasto.getDS() * sasiednieSklep)
                + (Miasto.getDT() * sasiednieTramwaj);

    }

}
