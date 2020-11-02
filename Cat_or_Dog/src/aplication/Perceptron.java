package aplication;

import java.io.File;
import java.util.Set;

import javafx.stage.FileChooser;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

public class Perceptron {
    // declaracoes ------------
	private String hiddenLayers = "a";
	private double momentum = 0.2;
	private double learningRate = 0.3 ;
	private int trainningTime = 500 ;
	File f;
	
	//------------------
	
	public String getHiddenLayers() {
		return hiddenLayers;
	}

	public void setHiddenLayers(String hiddenLayers) {
		this.hiddenLayers = hiddenLayers;
	}

	public double getMomentum() {
		return momentum;
	}

	public void setMomentum(double momentum) {
		this.momentum = momentum;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public int getTrainningTime() {
		return trainningTime;
	}

	public void setTrainningTime(int trainningTime) {
		this.trainningTime = trainningTime;
	}

	public Perceptron() {
	}
	
	public void Parametros(String hiddenLayers, double momentum, double learnignRate, int trainingTime) {
		setHiddenLayers(hiddenLayers);
		setMomentum(momentum);
		setLearningRate(trainingTime);
		setTrainningTime(trainingTime);
	}
	
	public File selecionarARFF() {
		 f = buscaARFF();
		 return f;
		}
	
	private File buscaARFF() {		
		 try {
			 FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new 
						   FileChooser.ExtensionFilter(
								   "Audios", "*.arff")); 	
				 fileChooser.setInitialDirectory(new File("src"));
				 File arquivo = fileChooser.showOpenDialog(null);
			 if (arquivo != null) {
			    return arquivo;
			 }
		 } catch (Exception e) {
			 System.out.println("Não está com a extenção correta");
			e.printStackTrace();			
		 }
		 return null;
	}
	
	public double[] montarPerceptron() {
		
		double[] retorno = {0,0};
		
		try {
			//*******carregar arquivo de características
			DataSource ds = new DataSource("caracteristicas.arff");
			Instances instancias = ds.getDataSet();
			instancias.setClassIndex(instancias.numAttributes()-1);
			
			// nessa parte da uma demoradinha só esperar um pouco
			
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			
			mlp.setHiddenLayers(getHiddenLayers());
			mlp.setMomentum(getMomentum());
			mlp.setLearningRate(getLearningRate());
			mlp.setTrainingTime(getTrainningTime());
			
			mlp.buildClassifier(instancias);
			
			ArffLoader arffLoader = new ArffLoader();
            arffLoader.setFile(selecionarARFF());
			Instance instance = arffLoader.getDataSet().get(0);
			instance.setDataset(instancias);
			retorno = mlp.distributionForInstance(instance);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
}
