package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML private TextField txtSeedA, txtSeedB, txtN;
    @FXML private Button btnStart,btnPruebas;
    @FXML private TextArea txtAreaNumbers;
    @FXML private Label lblPruebaIndependencia,lblPruebaUniformidad;
    private float seedA, seedB;
    private int n; //Muestra
    float tempA;
    float tempB;
    float nPseudoA;
    //VARIABLES PRUEBA_DE_UNIFORMIDAD "CHI CUADRADA" (BONDAD-DE-AJUSTE)
        int m;
        float[][] Oi; //FrecuenciaObservada
        float Ei; //FrecuenciaEsperadaa
        float estadPruebaUniformidad;
        float valorCriticoChiCuadrada;
    //VARIABLES PRUEBA_DE_INDEPENDENCIA (CORRIDAS-OBSERVADAS)
        short cObs;
        float muCObs;
        float desvE;
        float estadPruebaIndependecia;
        float nPseudoTemp;
        //FINALIZACIÓN DECLARACION VARIABLES (PRUEBA_DE_INPENDENCIA)

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event);
        btnPruebas.setOnAction(event);
        btnPruebas.setDisable(true);
        txtAreaNumbers.setEditable(false);
    }

    //Evento para manejar el boton
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            txtAreaNumbers.setText("");
            if (event.getSource() == btnStart)
            {
                if (verifyData()){
                    cObs = 0;
                    inicializarVariablesPruebaUniformidad();
                    generarNumeros(n,seedA,seedB);
                    printEPrueba();
                }
            }
            if (event.getSource() == btnPruebas){
                realizarPruebaIndependencia();
                realizarPruebaUniformidad();
            }
        }
    };
    //Método para comprobar que todos los datos fueron ingresados correctamente
    private boolean verifyData(){
        boolean flag = true;
        if ( (txtSeedA.getText().isEmpty()) || (txtSeedB.getText().isEmpty()) || (txtN.getText().isEmpty())){
            flag = false;
            emptyDataAlert();
        }else{
            try{
                flag = true;
                seedA = Float.parseFloat(txtSeedA.getText());
                seedB = Float.parseFloat(txtSeedB.getText());
                n = Integer.parseInt(txtN.getText());
            }catch (NumberFormatException e) {
                flag = false;
                incorrectDataAlert();
            }
        }
        return flag;
    }
    //Método para mandar alerta de que los datos fueron ingresados incorrectamente
    private void incorrectDataAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error 0");
        alert.setHeaderText("DATOS INGRESADOS INCORRECTAMENTE");
        alert.setContentText("Recuerda ingresar numeros reales en semillas y enteros positivos en n...\n" +
                "No ingresar caracteres especiales o letras");
        alert.showAndWait();
    }
    //Método para mandar alerta de que los cajas de texto estan vacias
    private void emptyDataAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error 1");
        alert.setHeaderText(null);
        alert.setContentText("Favor de llenar todas las casillas");
        alert.showAndWait();
    }
    //Algoritmo para generar números aleatorios
    private void generarNumeros(int n,double semillaA,double semillaB){
        nPseudoA = 0;
        DecimalFormat df = new DecimalFormat("#.0000");
        char menor_mayor = 'N';//VARIABLE(PRUEBA_DE_INPENDENCIA)
        char menor_mayorTemp;//VARIABLE(PRUEBA_DE_INPENDENCIA)

        for (int i=1; i<=n; i++) {
            tempA = (float) Math.abs(Math.sin(semillaA));
            tempB = (float) Math.abs(Math.sin(semillaB));
            menor_mayorTemp = menor_mayor;
            nPseudoTemp = nPseudoA;
            nPseudoA = (float) Math.sqrt((tempA)*(tempB));
            if (i>=2){
                if (nPseudoA<nPseudoTemp)
                    menor_mayor = 'm';
                else
                    menor_mayor = 'M';
            }
            if (!(menor_mayorTemp == 'N')){
                if (!(menor_mayor == menor_mayorTemp)){
                    cObs++;
                }
            }
            aumentarOi();
            semillaA = Float.parseFloat(String.valueOf(tempA).substring(4,6));
            semillaB = Float.parseFloat(String.valueOf(tempB).substring(4,6));
            txtAreaNumbers.appendText(df.format(nPseudoA)+"\n");
        }
        muCObs = (float) ((2*n)-1)/3;
        desvE = (float) Math.sqrt(((16*n)-29)/90);
        estadPruebaIndependecia = Math.abs((cObs-muCObs)/desvE);
        btnPruebas.setDisable(false);
    }

    private void inicializarVariablesPruebaUniformidad(){
        float separacion; //Variable que ayuda a crear el número de clases
        m = (int) Math.sqrt(n);
        Ei = (float) n/m;
        separacion = (float) 1 / m;
        Oi = new float[m+1][2]; //se realizo una matriz con ayuda para añadir intervalos y la frecuencia observada
        Oi[0][0] = 0;
        for (int i=1;i<m;i++){
            Oi[i][0] = (separacion*(i));
        }
        Oi[m][0] = 1;
    }
    private void aumentarOi(){
        for (int i=1;i<=m;i++){
            if ((nPseudoA<Oi[i][0]) && (nPseudoA>=Oi[i-1][0]))
                Oi[i][1] = Oi[i][1]+1;
        }
    }
    private void realizarPruebaUniformidad(){
        ChiCuadradaNVLAcep95 chiCuadradaNVLAcep95 = new ChiCuadradaNVLAcep95();
        for (int i=1;i<=m;i++){
            estadPruebaUniformidad = estadPruebaUniformidad + (((Ei-Oi[i][1])*(Ei-Oi[i][1]))/Ei);
        }
        valorCriticoChiCuadrada = chiCuadradaNVLAcep95.getEstPrueba(m);
        if (estadPruebaUniformidad<valorCriticoChiCuadrada)
            lblPruebaUniformidad.setText("Prueba de Uniformidad :  \n"+
                    "\nEstadistico Prueba = "+estadPruebaUniformidad+
                    "\nValor Crítico = "+valorCriticoChiCuadrada+"\nLos Números ri son uniformes");
        else
            lblPruebaUniformidad.setText("Prueba de Uniformidad :  \n"+
                    "\nm = "+m+
                    "\nEstadistico Prueba = "+estadPruebaUniformidad+
                    "\nValor Crítico = "+valorCriticoChiCuadrada+"\nLos Números ri no son uniformes");
    }
    private void realizarPruebaIndependencia(){
        if (estadPruebaIndependecia<1.96)
            lblPruebaIndependencia.setText("Prueba de Independencia :  \n"+
                    "Co = "+cObs+
                    "\nμCo = "+muCObs+
                    "\nσ = "+desvE+
                    "\nZ = "+estadPruebaIndependecia+
                    "\nV Crítico = 1.96"+"\nLos números ri son independientes");
        else
            lblPruebaIndependencia.setText("Prueba de Independencia :  \n"+
                    "Co = "+cObs+
                    "\nμCo = "+muCObs+
                    "\nσ = "+desvE+
                    "\nZ = "+estadPruebaIndependecia+
                    "\nV Crítico = 1.96"+"\nLos números ri no son independientes");

    }
    private void printEPrueba(){
        for (int i=1 ; i<m ; i++){
            System.out.println(Oi[i][0]+"\t\t"+Oi[i][1]);
        }
    }
}