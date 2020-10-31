package fr.ryfax.rge.engine.utils;

public class Tools {

    public static String intToDigit(long val) {
        if(val <= 9) return "0" + val;
        else return "" + val;
    }

    public static double round(double toRound, int n) {
        return (double) Math.round(toRound * (10^n))/(10^n);
    }

}
