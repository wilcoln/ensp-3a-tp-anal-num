package controllers;

import classes.Lagrange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import classes.MyGraph;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MainAppController implements Initializable {

    @FXML
    private LineChart<Double, Double> lineGraph;

    @FXML
    private AreaChart<Double, Double> areaGraph;

    @FXML
    private Button lineGraphButton;

    @FXML
    private Button areaGraphButton;

    @FXML
    private Button fBtn;

    @FXML
    private Button gBtn;

    @FXML
    private TextField nbF;

    @FXML
    private TextField nbG;


    @FXML
    private Button clearButton;

    private MyGraph mathsGraph;
    private MyGraph areaMathsGraph;
//Interpolation points
    private static int NBP_10 = 0;
    private static int NBP_25 = 1;
    private static int NBP_33 = 2;
    private static int NBP_50 = 3;
    private static int NBP_100 = 4;

    private double[][] tabs = new double[5][];

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
       tabs[NBP_10] = new double[10];
       tabs[NBP_25] = new double[25];
       tabs[NBP_33] = new double[33];
       tabs[NBP_50] = new double[50];
       tabs[NBP_100] = new double[100];

        // remplissage de NBP_10
        for (int i = 0; i < 10; i++) {
            tabs[NBP_10][i] = -10 + (20*i)/9.0;
        }
        // remplissage de NBP_25
        for (int i = 0; i < 25 ; i++) {
            tabs[NBP_25][i] = -10 + (20*i)/24.0;
        }
        // remplissage de NBP_33
        for (int i = 0; i < 33 ; i++) {
            tabs[NBP_33][i] = -10 + (20*i)/32.0;
        }
        // remplissage de NBP_50
        for (int i = 0; i < 50 ; i++) {
            tabs[NBP_50][i] = -10 + (20*i)/49.0;
        }
        // remplissage de NBP_100
        for (int i = 0; i < 100 ; i++) {
            tabs[NBP_100][i] = -10 + (20*i)/99.0;
        }
        mathsGraph = new MyGraph(lineGraph, 10);
        areaMathsGraph = new MyGraph(areaGraph, 10);
    }

    @FXML
    private void handleLineGraphButtonAction(final ActionEvent event) {
        lineGraph.setVisible(true);
        areaGraph.setVisible(false);
    }

    @FXML
    private void handleAreaGraphButtonAction(final ActionEvent event) {
        areaGraph.setVisible(true);
        lineGraph.setVisible(false);
    }

    @FXML
    private void handleFBtnAction(final ActionEvent event) {
        plotLine(x -> f(x));
    }

    private void plotLine(Function<Double, Double> function) {
        if (lineGraph.isVisible()) {
            mathsGraph.plotLine(function);
        } else {
            areaMathsGraph.plotLine(function);
        }
    }

    @FXML
    private void handleGBtnAction(final ActionEvent event) {
        plotLine(x -> g(x));
    }

    @FXML
    private void handleLefBtnAction(final ActionEvent event) {
        Lagrange lg = new Lagrange();
        lg.n = (nbF.getText().isEmpty())? 10: Integer.parseInt(nbF.getText());
        lg.setEquidistant();
        lg.y = lg.imagesParF1(lg.x);
        plotLine(lg::g1);
    }
    @FXML
    private void handleLtfBtnAction(final ActionEvent event) {
        Lagrange lg = new Lagrange();
        lg.n = (nbF.getText().isEmpty())? 10: Integer.parseInt(nbF.getText());
        lg.setTchebychev();
        lg.y = lg.imagesParF1(lg.x);
        plotLine(lg::p);
    }

    @FXML
    private void handleLegBtnAction(final ActionEvent event) {
        Lagrange lg = new Lagrange();
        lg.n = (nbF.getText().isEmpty())? 10: Integer.parseInt(nbF.getText());
        lg.setEquidistant();
        lg.y = lg.imagesParF2(lg.x);
        plotLine(lg::p);
    }
    @FXML
    private void handleLtgBtnAction(final ActionEvent event) {
        Lagrange lg = new Lagrange();
        lg.n = (nbF.getText().isEmpty())? 10: Integer.parseInt(nbF.getText());
        lg.setTchebychev();
        lg.y = lg.imagesParF2(lg.x);
        plotLine(lg::p);
    }

    @FXML
    private void handleSlfBtnAction(final ActionEvent event) {
        plotLine(x -> {
            int tabs_ind = 0;
            int i = 9;
            int nb = (nbF.getText().isEmpty())? 10: Integer.parseInt(nbF.getText());
            switch (nb){
                case 10:
                    tabs_ind = NBP_10;
                    i = 9;
                    break;
                case 33:
                    tabs_ind = NBP_33;
                    i = 32;
                    break;
                case 25:
                    tabs_ind = NBP_25;
                    i = 24;
                    break;
                case 50:
                    tabs_ind = NBP_50;
                    i = 49;
                    break;
                case 100:
                    tabs_ind = NBP_100;
                    i = 99;
                    break;
                    
            }
            while(x < tabs[tabs_ind][i]){
                i--;
            }
            double x_im1 = tabs[tabs_ind][i];
            double x_i = tabs[tabs_ind][i+1];
            return (1/(x_im1 - x_i))*(f(x_im1)*(x - x_i) - f(x_i)*(x - x_im1));
        });
    }
    @FXML
    private void handleSqfBtnAction(final ActionEvent event) {
        int nb = (nbF.getText().isEmpty())? 10: Integer.parseInt(nbF.getText());
        double[] d = new double[nb];
        final int tabs_ind;
        final int last_ind;
        switch (nb){
            case 10:
                tabs_ind = NBP_10;
                last_ind = 9;
                break;
            case 33:
                tabs_ind = NBP_33;
                last_ind = 32;
                break;
            case 25:
                tabs_ind = NBP_25;
                last_ind = 24;
                break;
            case 50:
                tabs_ind = NBP_50;
                last_ind = 49;
                break;
            case 100:
                tabs_ind = NBP_100;
                last_ind = 99;
                break;
            default:
                tabs_ind = NBP_10;
                last_ind = 9;
                break;
        }
        calculate_di_f(d, tabs_ind);
        plotLine(x -> {
            int i = last_ind;
            while(x < tabs[tabs_ind][i]) {
                i--;
            }
            double x_i = tabs[tabs_ind][i];
            double x_ip1 = tabs[tabs_ind][i+1];
            return d[i]*(x - x_i) + (d[i+1] - d[i])*Math.pow((x - x_i),2)/(2*(x_ip1 - x_i)) + f(x_i);
        });
    }

    private void calculate_di_f(double d[], int tabs_ind) {
        d[0] = 0;
        double x[] = tabs[tabs_ind];
        for (int i = 1; i < d.length ; i++) {
            d[i] = 2*(f(x[i]) - f(x[i-1]))/(x[i] - x[i-1]) - d[i-1];
        }
    }
    private void calculate_di_g(double d[], int tabs_ind) {
        d[0] = 0;
        double x[] = tabs[tabs_ind];
        for (int i = 1; i < d.length ; i++) {
            d[i] = 2*(g(x[i]) - g(x[i-1]))/(x[i] - x[i-1]) - d[i-1];
        }
    }

    @FXML
    private void handleScfBtnAction(final ActionEvent event) {
        //TODO: Plot the cubique spline interpoloation function of f, get nbf
        Lagrange l=new Lagrange();
        l.n=10;
        l.x=l.pointsEquidistants(-10, 10);
        plotLine(x -> l.g1(x));
    }

    @FXML
    private void handleSlgBtnAction(final ActionEvent event) {
        plotLine(x -> {
            int nb = (nbG.getText().isEmpty())? 10: Integer.parseInt(nbG.getText());
            int tabs_ind = 0;
            int i = 9;
            switch (nb){
                case 10:
                    tabs_ind = NBP_10;
                    break;
                case 33:
                    tabs_ind = NBP_33;
                    i = 32;
                    break;
                case 25:
                    tabs_ind = NBP_25;
                    i = 24;
                    break;
                case 50:
                    tabs_ind = NBP_50;
                    i = 49;
                    break;
                case 100:
                    tabs_ind = NBP_100;
                    i = 99;
                    break;

            }
            while(x < tabs[tabs_ind][i]){
                i--;
            }
            double x_im1 = tabs[tabs_ind][i];
            double x_i = tabs[tabs_ind][i+1];
            return (1/(x_im1 - x_i))*(g(x_im1)*(x - x_i) - g(x_i)*(x - x_im1));
        });
    }
    @FXML
    private void handleSqgBtnAction(final ActionEvent event) {
        int nb = (nbG.getText().isEmpty())? 10: Integer.parseInt(nbG.getText());
        double[] d = new double[nb];
        final int tabs_ind;
        final int last_ind;
        switch (nb){
            case 10:
                tabs_ind = NBP_10;
                last_ind = 9;
                break;
            case 33:
                tabs_ind = NBP_33;
                last_ind = 32;
                break;
            case 25:
                tabs_ind = NBP_25;
                last_ind = 24;
                break;
            case 50:
                tabs_ind = NBP_50;
                last_ind = 49;
                break;
            case 100:
                tabs_ind = NBP_100;
                last_ind = 99;
                break;
            default:
                tabs_ind = NBP_10;
                last_ind = 9;
                break;
        }
        calculate_di_g(d, tabs_ind);
        plotLine(x -> {
            int i = last_ind;
            while(x < tabs[tabs_ind][i]) {
                i--;
            }
            double x_i = tabs[tabs_ind][i];
            double x_ip1 = tabs[tabs_ind][i+1];
            return d[i]*(x - x_i) + (d[i+1] - d[i])*Math.pow((x - x_i),2)/(2*(x_ip1 - x_i)) + g(x_i);
        });
    }

    @FXML
    private void handleScgBtnAction(final ActionEvent event) {
        //TODO: Plot the cubique spline interpoloation function of g get nbg

        plotLine(x -> Math.pow(x, 2));
    }
    @FXML
    private void handleClearButtonAction(final ActionEvent event) {
        clear();
    }

    private void clear() {
        if (lineGraph.isVisible()) {
            mathsGraph.clear();
        } else {
            areaMathsGraph.clear();
        }
    }
    private double g(double x){
        return 1/(1 + Math.expm1(Math.pow(x,2)));
    }
    private double f(double x){
        return 1/(1 + Math.pow(x,2));
    }

}
