/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpan;

/**
 *
 * @author SCrf1
 */
public class Lagrange extends Utilitaire
{
    
    double[] x;
    double[] y;
    
    public void setEquidistant()
    {
        x=pointsEquidistants(a, b);
    }
    public void setTchebychev()
    {
        x=pointsTchebychev(a, b);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
    
    
    private void calculDifferenceDivisees(double[][] tab)
    {
        /*
            tab représente le tableau des différences divisées
            x représente les points d'interpolation
        */
        for(int i=1;i<n;i++)
        {
            for(int j=1;j<=i;j++)
                tab[i][j]=(tab[i][j-1]-tab[i-1][j-1])/(x[i]-x[j]);
        }
    }
    
    public double[] calculCoeffPIL()
    {
        /*
            y représente les images des points d'interpolation par une fonction
        */
        double[] coefficients=new double[n];
        double[][] diffDiv=new double[n][n];
        for(int i=0;i<n;i++)
            diffDiv[i][0]=y[i];
        calculDifferenceDivisees(diffDiv);
        for(int i=0;i<n;i++)
            coefficients[i]=diffDiv[i][i];
        return coefficients;
    }
    
    
    
    
    
    //Méthode calculant la valeur du polynome d'interpolation de Lagrange en un point
    public double p(double X)
    {
        /*
            X représente le point en lequel on souhaite calculer la valeur du PIL
        */
        double[] a=calculCoeffPIL();
        double t=1.0;
        double y=a[0];
        for(int i=0;i<x.length;i++)
        {
            t*=X-x[i];
            y+=a[i]*t;
        }
        return y;
    }
    
//    
//    public double pil(double X)
//    {
//        
//    }
}
