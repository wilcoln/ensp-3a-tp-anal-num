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
public class SplineLineaire extends Utilitaire
{
    
    double[] x= new double[n];
    double[] f= new double[n];
    
    public double splineLinF1(double X)
    {
        //f1(x)=1/(1+x*x)
        double s=0.0;
        int i=0;
        
        x=pointsEquidistants(a, b);
        f=imagesParF1(x);
        
        //Recherche de l'intervalle contenant x
        while(X>x[i] && i<n)
            i++;
        s=(X-x[i+1])*f[i]/(x[i]-x[i+1])+(X-x[i])*f[i+1]/(x[i+1]-x[i]);
        
        return s;
    }
    public double splineLinF2(double X)
    {
        //f2(x)=1/(1+e(-x²))
        double s=0.0;
        int i=0;
        
        x=pointsEquidistants(a, b);
        f=imagesParF2(x);
        
        //Recherche de l'intervalle contenant x
        while(X>x[i] && i<n)
            i++;
        s=(X-x[i+1])*f[i]/(x[i]-x[i+1])+(X-x[i])*f[i+1]/(x[i+1]-x[i]);
        
        return s;
    }
    
}
