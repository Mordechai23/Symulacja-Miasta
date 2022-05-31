package com.company;

import java.util.ArrayList;

public class Log {
    ArrayList<Integer> logPuste             = new ArrayList<Integer>();
    ArrayList<Integer> logDomy              = new ArrayList<Integer>();
    ArrayList<Integer> logBiurowce          = new ArrayList<Integer>();
    ArrayList<Integer> logFabryki           = new ArrayList<Integer>();
    ArrayList<Integer> logSklepy            = new ArrayList<Integer>();
    ArrayList<Integer> logMieszkancy        = new ArrayList<Integer>();
    ArrayList<Integer> logMiejscaPracy      = new ArrayList<Integer>();
    ArrayList<Double>  logZadowolenie       = new ArrayList<Double>();

    public void policzStatystyki() {
        int puste = 0;
        int domy = 0;
        int biurowce = 0;
        int fabryki = 0;
        int sklepy = 0;
        int mieszkancy = 0;
        int miejscaPracy = 0;
        double zadowolenie = 0;
    }

    public void zapiszDoPliku() {

    }
}
