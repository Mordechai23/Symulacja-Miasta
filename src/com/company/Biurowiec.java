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
        zadowolenie= (Miasto.getBB() * sasiednieBiurowiec)
                + (Miasto.getBD() * sasiednieDom)
                + (Miasto.getBF() * sasiednieFabryka)
                + (Miasto.getBS() * sasiednieSklep);
    }
}
