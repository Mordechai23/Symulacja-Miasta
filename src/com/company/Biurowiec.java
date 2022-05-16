package com.company;

public class Biurowiec extends Budynek {
    public Biurowiec(int x, int y){
        typ=Typ.BIUROWIEC;
        poziom=1;
        uzytkownicy=obliczUzytkownicy(poziom);
        wspolrzedne[0]=x;
        wspolrzedne[1]=y;
        ustawSasiadow();
    }
    int obliczUzytkownicy(int poziom) {
        return (poziom+1)^2+14;
    }
    double obliczZadowolenie() {
        return (Miasto.getBB() * sasiednieBiurowiec)
                + (Miasto.getBD() * sasiednieDom)
                + (Miasto.getBF() * sasiednieFabryka)
                + (Miasto.getBS() * sasiednieSklep);
    }
}
