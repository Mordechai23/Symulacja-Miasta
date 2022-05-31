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


    void ustawSasiadow(){
        sasiedniePuste= Plansza.policzWSasiedztwie(x,y, Typ.PUSTE);
        sasiednieBiurowiec= Plansza.policzWSasiedztwie(x,y, Typ.BIUROWIEC);
        sasiednieDom= Plansza.policzWSasiedztwie(x,y, Typ.DOM);
        sasiednieFabryka= Plansza.policzWSasiedztwie(x,y, Typ.FABRYKA);
        sasiednieSklep= Plansza.policzWSasiedztwie(x,y, Typ.SKLEP);
        sasiednieTramwaj= Plansza.policzWSasiedztwie(x,y, Typ.TRAMWAJ);
    }
    void podniesPoziom(){
        if (poziom<5) {
            poziom++;
            obliczUzytkownicy(poziom);
        }
    }

    void zmniejszPoziom() {
        if (poziom>=1) {
            poziom--;
            obliczUzytkownicy(poziom);
        }
    }
}
