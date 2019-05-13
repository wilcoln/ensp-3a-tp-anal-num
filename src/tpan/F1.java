/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpan;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author SCrf1
 */
public class F1 extends VBox
{
    private final NumberAxis x=new NumberAxis(-10,10,10);
    private final NumberAxis y=new NumberAxis(-2,2,10);
    
    private final LineChart graphe=new LineChart(x,y);
    
    private XYChart.Series f1=new XYChart.Series();
    private XYChart.Series courbeLagrange=new XYChart.Series();
    private XYChart.Series courbeSplineLin=new XYChart.Series();
    private XYChart.Series courbeSplineQua=new XYChart.Series();
    private XYChart.Series courbeSplinCub=new XYChart.Series();
    
    private Button lpe=new Button("Lagrange points équidistants");
    private Button lpt=new Button("Lagrange points Tchebychev");
    private Button sl=new Button("Spline linéaire");
    private Button sq=new Button("Spline quadratique");
    private Button sc=new Button("Spline cubique");
    
    private Lagrange lagrange=new Lagrange();
    private SplineLineaire splineLineaire=new SplineLineaire();
    
    public F1()
    {
        x.setLabel("x");
        y.setLabel("y");
        
        dessineCourbe(f1);
        setF1();
        
        this.getChildren().add(graphe);
    }
    
    public void dessineCourbe(XYChart.Series courbe)
    {
        graphe.setCreateSymbols(false);
        graphe.getData().add(courbe);
    }
    
    public void setF1()
    {
        double i=lagrange.a;
        while(i<=lagrange.b)
        {
            f1.getData().add(new XYChart.Data(i,1/(1+i*i)));
            i+=0.01;
        }
          
    }
}
