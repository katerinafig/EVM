package com.company;

public class Main {

    public static void main(String[] args) {
        Polynomial pol1 = new Polynomial(new int []{1,1,0},3);
        Polynomial pol2 = new Polynomial(new int []{2,0,1},3);
        System.out.println(pol1.toString());
        System.out.println(pol2.toString());
        System.out.println(pol1.addPolylomials(pol2).toString());
        System.out.println(pol1.subPolylomials(pol2).toString());
        System.out.println(pol1.multiPolylomials(pol2).toString());
        System.out.println(pol2.divPolylomials(pol1));
    }
}
