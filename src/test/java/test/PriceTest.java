package test;

public class PriceTest {
    public static void main(String args[]) {
        System.out.println(Price.instance.curreentPrice);
        Price p = new Price(2.8);
        System.out.println(p.curreentPrice);
    }
}