package org.ekg.front.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.ekg.front.dto.responses.EkgDTO;
import org.ekg.front.utils.Requests;
import org.ekg.front.utils.URLGenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EKG implements Initializable {
    @FXML
    private LineChart<Number, Number> chart1;
    @FXML
    private LineChart<Number, Number> chart2;
    @FXML
    private LineChart<Number, Number> chart3;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final NumberAxis x1Axis = new NumberAxis();

        final NumberAxis y1Axis = new NumberAxis(-1, 1, 0.01);
        final NumberAxis x2Axis = new NumberAxis(0, 5000, 1);
        final NumberAxis y2Axis = new NumberAxis(-1, 1, 0.01);
        final NumberAxis x3Axis = new NumberAxis(0, 5000, 1);
        final NumberAxis y3Axis = new NumberAxis(-1, 1, 0.01);


        try {
            System.out.println(URLGenerator.getEKGBySurveyURL());
            List<EkgDTO> ekgs = Requests.getEKGRequest(URLGenerator.getEKGBySurveyURL());
            List<EkgDTO> ekgs1 = new ArrayList<>();
            List<EkgDTO> ekgs2 = new ArrayList<>();
            List<EkgDTO> ekgs3 = new ArrayList<>();
            assert ekgs != null;
            for(EkgDTO ekg: ekgs){
                switch (ekg.getType()){
                    case "AVF":
                        ekgs1.add(ekg);
                        break;
                    case "AVL":
                        ekgs2.add(ekg);
                        break;
                    case "AVR":
                        ekgs3.add(ekg);
                        break;
                }
            }
            XYChart.Series<Number,Number> series1 = new XYChart.Series<>();

            for (EkgDTO ekgDTO: ekgs1){
                series1.getData().add(new XYChart.Data<>(ekgDTO.getTick(), ekgDTO.getValue()));
            }

            chart1.getData().add(series1);

            XYChart.Series series2 = new XYChart.Series<>();
            for (EkgDTO ekg: ekgs2){
                series2.getData().add(new XYChart.Data<>(String.valueOf(ekg.getTick()), ekg.getValue()));
            }
            chart2.getData().add(series2);
            XYChart.Series series3 = new XYChart.Series<>();
            for (EkgDTO ekg: ekgs3){
                series3.getData().add(new XYChart.Data<>(ekg.getTick(), ekg.getValue()));
            }
            chart3.getData().add(series3);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
