package com.company;

abstract class Budynek extends Dzialka{
    int uzytkownicy;

    //ilosc sasiadow
    int sasiedniePuste;
    int sasiednieTramwaj;
    int sasiednieDom;
    int sasiednieBiurowiec;
    int sasiednieFabryka;
    int sasiednieSklep;

    abstract int obliczUzytkownicy(int poziom);
    abstract double obliczZadowolenie();
    void ustawSasiadow(){
        sasiedniePuste=Miasto.policzWSasiedztwie(x,y, Typ.PUSTE);
        sasiednieBiurowiec=Miasto.policzWSasiedztwie(x,y, Typ.BIUROWIEC);
        sasiednieDom=Miasto.policzWSasiedztwie(x,y, Typ.DOM);
        sasiednieFabryka=Miasto.policzWSasiedztwie(x,y, Typ.FABRYKA);
        sasiednieSklep=Miasto.policzWSasiedztwie(x,y, Typ.SKLEP);
        sasiednieTramwaj=Miasto.policzWSasiedztwie(x,y, Typ.TRAMWAJ);
    }
    void podniesPoziom(){
        if (poziom<5) {
            poziom++;
            obliczUzytkownicy(poziom);
        }
    }
}
