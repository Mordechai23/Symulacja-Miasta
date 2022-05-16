package com.company;

public class Dom extends Budynek{
    public Dom(int x, int y){
        typ=Typ.DOM;
        poziom=1;
        uzytkownicy=obliczUzytkownicy(poziom);
        wspolrzedne[0]=x;
        wspolrzedne[1]=y;
        ustawSasiadow();
        obliczZadowolenie();
    }
    int obliczUzytkownicy(int poziom){return poziom^2+2;
    }
    double obliczZadowolenie() {
        zadowolenie=(Miasto.getDB() * sasiednieBiurowiec)
                + (Miasto.getDD() * sasiednieDom)
                + (Miasto.getDF() * sasiednieFabryka)
                + (Miasto.getDP() * sasiedniePuste)
                + (Miasto.getDS() * sasiednieSklep)
                + (Miasto.getDT() * sasiednieTramwaj);
        return zadowolenie;
    }

}
