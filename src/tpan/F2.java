/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpan;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 *
 * @author SCrf1
 */
public class F2 extends VBox
{
    private final NumberAxis x=new NumberAxis(-10,0,10);
    private final NumberAxis y=new NumberAxis(-50,0,50);
    
    private final LineChart graphe=new LineChart(x,y);
    
    private XYChart.Series f2=new XYChart.Series();
    private XYChart.Series courbeLagrange=new XYChart.Series();
    private XYChart.Series courbeSplineLin=new XYChart.Series();
    private XYChart.Series courbeSplineQua=new XYChart.Series();
    private XYChart.Series courbeSplinCub=new XYChart.Series();
    
    public F2()
    {
        x.setLabel("x");
        y.setLabel("y");
    }
}
