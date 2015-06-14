package test;

public class Price {
    final static Price instance = new Price(2.8);
    static double initPrice = 20;
    double curreentPrice;

    public Price(double discount) {
        curreentPrice = initPrice - discount;
    }

}
