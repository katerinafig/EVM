package com.company;


import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedList;

public class Polynomial {
    private int [] arrayPolynomial ;
    private int size;
    private int mod;
    public Polynomial(int size, int mod){
        this.size=size;
        this.arrayPolynomial = new int[size];
        this.mod=mod;
    }
    public Polynomial(int [] arrayPolynomial, int mod)
    {
        this.arrayPolynomial = arrayPolynomial;
        this.size = arrayPolynomial.length;
        this.mod=mod;
    }
    public int[] getArrayPolynomial() {
        return arrayPolynomial;
    }

    public int getSize() {
        return size;
    }
    public int getElementPolynomialOnNumber(int number){
        return arrayPolynomial[number];
    }
    public Polynomial addPolylomials(Polynomial polynomialAdded) {
        Polynomial polynomialResult;
        if (size == polynomialAdded.size) {
            polynomialResult = new Polynomial(size,mod);
            for (int i=0;i<size;i++){
                polynomialResult.arrayPolynomial[i] = (arrayPolynomial[i]+polynomialAdded.arrayPolynomial[i])%mod;
            }

        } else if (size > polynomialAdded.getSize()) {
            polynomialResult = new Polynomial(size,mod);
            for (int i=0;i<size;i++){
                if(i<polynomialAdded.size)
                    polynomialResult.arrayPolynomial[i] = (arrayPolynomial[i]+polynomialAdded.arrayPolynomial[i])%mod;
                else polynomialResult.arrayPolynomial[i] = arrayPolynomial[i];
            }
        }
        else {
            polynomialResult = new Polynomial(polynomialAdded.size,mod);
            for (int i=0;i<polynomialResult.size;i++){
                if(i<size)
                    polynomialResult.arrayPolynomial[i] = (arrayPolynomial[i]+polynomialAdded.arrayPolynomial[i])%mod;
                else polynomialResult.arrayPolynomial[i] =polynomialAdded.getElementPolynomialOnNumber(i);
            }
        }
        return polynomialResult;
    }
    public Polynomial subPolylomials(Polynomial polynomialSubtrahend) {
        Polynomial polynomialResult;
        if (size == polynomialSubtrahend.size) {
            polynomialResult = new Polynomial(size,mod);
            for (int i=0;i<size;i++){
                polynomialResult.arrayPolynomial[i] = (arrayPolynomial[i]-polynomialSubtrahend.arrayPolynomial[i])%mod;
            }

        } else if (size > polynomialSubtrahend.getSize()) {
            polynomialResult = new Polynomial(size, mod);
            for (int i=0;i<size;i++){
                if(i<polynomialSubtrahend.size)
                    polynomialResult.arrayPolynomial[i] = (arrayPolynomial[i]-polynomialSubtrahend.arrayPolynomial[i])%mod;
                else polynomialResult.arrayPolynomial[i] = arrayPolynomial[i];
            }

        }
        else {
            polynomialResult = new Polynomial(polynomialSubtrahend.size, mod);
            for (int i=0;i<polynomialResult.size;i++){
                if(i<size)
                    polynomialResult.arrayPolynomial[i] = (arrayPolynomial[i]-polynomialSubtrahend.arrayPolynomial[i])%mod;
                else polynomialResult.arrayPolynomial[i] = - polynomialSubtrahend.arrayPolynomial[i];
            }
        }
        return polynomialResult;
    }
    public Polynomial multiPolylomials(Polynomial polynomialMyltiplied) {
        Polynomial polynomialResult;
        polynomialResult = new Polynomial(size+polynomialMyltiplied.size,mod);
        for (int i=0;i<size;i++){
            for (int j=0;j<polynomialMyltiplied.size;j++) {
                polynomialResult.arrayPolynomial[ i+j] += arrayPolynomial[i] * polynomialMyltiplied.arrayPolynomial[j];
            }
        }
        for (int j=0;j<polynomialResult.size;j++) {
            polynomialResult.arrayPolynomial[j] = polynomialResult.arrayPolynomial[j]%mod;
        }
        return polynomialResult;
    }
    public Polynomial divPolylomials(Polynomial polynomialDiv) {
        Polynomial polynomialResult=new Polynomial(size-polynomialDiv.size+1,mod);
        Polynomial polynomialTemp;
        int x;
        Polynomial polynomialResidue;
        Polynomial polynomialFirst = this;
        while (polynomialFirst.size>=polynomialDiv.size) {
            polynomialTemp = new Polynomial(polynomialFirst.size - polynomialDiv.size+1, mod);
            x=0;
            while (((polynomialDiv.arrayPolynomial[polynomialDiv.size - 1] * x) % mod != polynomialFirst.arrayPolynomial[polynomialFirst.size - 1])&&x<mod){
                    x++;
                }
            polynomialResult.arrayPolynomial[polynomialFirst.size - polynomialDiv.size] = x;
            polynomialTemp.arrayPolynomial[polynomialFirst.size - polynomialDiv.size] = x;
            polynomialResidue = polynomialTemp.multiPolylomials(polynomialDiv);
            polynomialFirst = polynomialFirst.subPolylomials(polynomialResidue);
        }
        return polynomialResult;
    }
     @Override
    public String toString(){
        StringBuilder out = new StringBuilder();
        for(int i=size-1;i>0;i--) {
            if(arrayPolynomial[i]!=0) {
                if (arrayPolynomial[i - 1] > 0)
                    out.append(
                    arrayPolynomial[i] + "x^" + i + "+");
                else out.append(arrayPolynomial[i] + "x^" + i);
            }
            else if (arrayPolynomial[i - 1] > 0&&arrayPolynomial[size-1]!=0) out.append("+");
        }
        //out.replace(out.length()-4,out.length(),"x");
        if(arrayPolynomial[0]==0) out.append(" ");
        else if(arrayPolynomial[0]>0) out.append(arrayPolynomial[0]).append(" ");
        else out .append(arrayPolynomial[0]).append(" ");
        return out.toString();

    }

}
