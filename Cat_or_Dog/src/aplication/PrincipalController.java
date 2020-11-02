package aplication;

import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import weka.classifiers.functions.MultilayerPerceptron;

public class PrincipalController {
	
	static Perceptron perceptron = new Perceptron();
	
	@FXML Label gato;
	@FXML Label cachorro;
	
	@FXML TextField hiddenLayers;
	@FXML TextField momentum;
	@FXML TextField learningRate;
	@FXML TextField trainningTime;
	
	@FXML
    public void initialize(){
		hiddenLayers.setText("a");
		momentum.setText("0.2");
		learningRate.setText("0.3");
		trainningTime.setText("500");
	}
	
	@FXML
	public void gravar() {
		perceptron.Parametros(hiddenLayers.getText(), Double.parseDouble(momentum.getText()), Double.parseDouble(learningRate.getText()), Integer.parseInt(trainningTime.getText()));
		
	}
	
	@FXML
	public void classificar() {
		double[] retorno = perceptron.montarPerceptron();
		gato.setText(Modifica(retorno[0]));
		cachorro.setText(Modifica(retorno[1]));
	}
	
	public String Modifica(double valor) {

		return String.format("%.2f", valor);
	}
}
