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
public class Utilitaire 
{
    int n=10;
    double a=-10;
    double b=10;
    
    public double[] pointsEquidistants(double borneG, double borneD)
    {
        double[] pts=new double[n];
        for(int i=0;i<n;i++)
            pts[i]=borneG+(borneD-borneG)*i/n;
        return pts;
    }
    
    public  double[] pointsTchebychev(double borneG,double borneD)
    {
        double[] pts=new double[n];
        for(int i=0;i<n;i++)
            pts[(n-1)-i]=(borneD-borneG)*Math.cos((2*i-1)*Math.PI/(2*n))/2+(borneD+borneG)/2;
        return pts;
    }
    public double[] imagesParF1(double[] x)
    {
        //   images des points d'interpolation par la fonction
        //   f1(x)=1/(1+x*x)
        
        double[] images=new double[n];
        for(int i=0;i<x.length;i++)
            images[i]=1/(1+(x[i]*x[i]));
        return images;
    }
    
    public  double[] imagesParF2(double[] x)
    {
        // images des points d'interpolation par la fonction
        // f2(x)=1/(1+e(-x²))
        
        double[] images=new double[n];
        for(int i=0;i<x.length;i++)
            images[i]=1/(1+Math.exp(-x[i]*x[i]));
        return images;
    }
}
