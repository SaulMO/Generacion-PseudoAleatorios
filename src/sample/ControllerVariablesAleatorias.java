package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ControllerVariablesAleatorias implements Initializable
{
    @FXML TextField txtRangoA,txtRangoB,txtNVA,txtLambda,txtNVA2,txtE;
    @FXML Button btnNumerosA,btnNumerosA2;
    @FXML TextArea txtANumerosA,txtANumerosA2;
    float rangoA,rangoB,lambda,e;
    int numerosMetodoDUniforme,numerosMetodoDErlang;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnNumerosA.setOnAction(generarNumeros);
        btnNumerosA2.setOnAction(generarNumeros);
        txtANumerosA2.setEditable(false);
        txtANumerosA.setEditable(false);
    }

    EventHandler<ActionEvent> generarNumeros = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnNumerosA){
                if (verifyDataMétodoDUniforme())
                    generarNumerosADUniforme();
            }
            if (event.getSource() == btnNumerosA2){
                if (verifyDataMétodoDErlang())
                    generarNumerosADErlang();
            }
        }
    };

    private boolean verifyDataMétodoDUniforme(){
        boolean flag = true;
        if ( (txtRangoA.getText().isEmpty()) || (txtRangoB.getText().isEmpty()) || (txtNVA.getText().isEmpty())){
            flag = false;
            emptyDataAlert();
        }else{
            try{
                flag = true;
                rangoA = Float.parseFloat(txtRangoA.getText());
                rangoB = Float.parseFloat(txtRangoB.getText());
                numerosMetodoDUniforme = Integer.parseInt(txtNVA.getText());
            }catch (NumberFormatException e) {
                flag = false;
                incorrectDataAlert();
            }
        }
        return flag;
    }
    private boolean verifyDataMétodoDErlang(){
        boolean flag = true;
        if ( (txtLambda.getText().isEmpty()) || (txtNVA2.getText().isEmpty()) || (txtE.getText().isEmpty())){
            flag = false;
            emptyDataAlert();
        }else{
            try{
                flag = true;
                lambda = Float.parseFloat(txtLambda.getText());
                e = Float.parseFloat(txtE.getText());
                numerosMetodoDErlang = Integer.parseInt(txtNVA.getText());
            }catch (NumberFormatException e) {
                flag = false;
                incorrectDataAlert();
            }
        }
        return flag;
    }
    private void emptyDataAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error 1");
        alert.setHeaderText(null);
        alert.setContentText("Favor de llenar todas las casillas");
        alert.showAndWait();
    }
    private void incorrectDataAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error 0");
        alert.setHeaderText("DATOS INGRESADOS INCORRECTAMENTE");
        alert.setContentText("Recuerda ingresar numeros reales en semillas y enteros positivos en n...\n" +
                "No ingresar caracteres especiales o letras");
        alert.showAndWait();
    }

    private void generarNumerosADUniforme(){
        float nAleatorioTemp;
        DecimalFormat df = new DecimalFormat("#.0000");
        for (int i=0 ; i<numerosMetodoDUniforme; i++){
            nAleatorioTemp = (float) ((float) rangoA + ((rangoB-rangoA)*Math.random()));
            txtANumerosA.appendText(df.format(nAleatorioTemp)+"\n");
        }
    }
    private void generarNumerosADErlang(){
        float a,b,c,nAleatorioTemp;
        for (int i=0 ; i<numerosMetodoDErlang ; i++){
            a = 1 - (float)Math.random();
            b = 1 - (float)Math.random();
            c = 1 - (float)Math.random();
            nAleatorioTemp = (float) -Math.log(a*b*c)*(lambda/e);
            txtANumerosA2.appendText(nAleatorioTemp+"\n");
        }
    }
}