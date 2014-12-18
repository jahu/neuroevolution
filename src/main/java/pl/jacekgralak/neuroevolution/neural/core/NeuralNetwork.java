package pl.jacekgralak.neuroevolution.neural.core;

import java.io.*;
import java.util.*;

public class NeuralNetwork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8439571951180475020L;
	private List<Layer> layers;

	private ActivationFunction activationFunction;

	public NeuralNetwork(int noLayers, int[] noNeurons, ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
		layers = new ArrayList<>();

		if(noLayers<1) {
			throw new RuntimeException("Perceptron must have at least 2 layers");
		} else if (noLayers != noNeurons.length){
			throw new RuntimeException("Wrong number of layers in noNeurons array");
		} else {
			switch (noLayers) {
			case 2:
				layers.add(new InputLayer(noNeurons[0], activationFunction));
				layers.add(new OutputLayer(noNeurons[1], activationFunction));
				break;
			default:
				layers.add(new InputLayer(noNeurons[0], activationFunction));
				for(int i=1; i<noNeurons.length-1; i++){
					layers.add(new Layer(noNeurons[i], activationFunction));
				}
				layers.add(new OutputLayer(noNeurons[noNeurons.length-1], activationFunction));
				break;
			}
			
			for (int i=layers.size()-1; i>0; i--){
				layers.get(i).connectLayer(layers.get(i-1));
			}

			for (Layer layer: layers){
				layer.setBiasEnabled(true);
			}
			generateWeights();
		}	
	}
	
	public NeuralNetwork(String path){
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			NeuralNetwork tmp = (NeuralNetwork) in.readObject();
			this.layers = tmp.layers;
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Perceptron class not found in given file");
			c.printStackTrace();
			return;
		}
	}

	public void addRandomHiddenNeuron() {
		Neuron neuron = new Neuron(activationFunction);
		if (layers.size() > 2) {
			layers.get(1).addNeuron(neuron);
			neuron.addInput(layers.get(0));
		} else {
			throw new RuntimeException("Cannot add random hidden neuron as there is no hidden layer in the network");
		}
	}
	
	public void generateWeights(){
		Random rand = new Random();
		for(Layer layer: layers){
			if(!(layer instanceof InputLayer)){
				layer.generateWeights(rand);
			}
		}
	}
	

	public List<Double> getOutput(List<Double> input) {
		if(input.size() != layers.get(0).getNeurons().size()){
			throw new RuntimeException ("Wrong number of values in input. Input layer has " + layers.get(0).getNeurons().size()+" neurons.");
		}
		((InputLayer) layers.get(0)).setOutputs(input);
		for(Layer layer: layers){
			if(!(layer instanceof InputLayer)){
				layer.countOutput();
			}
		}
		return ((OutputLayer) layers.get(layers.size()-1)).getOutput();
	}
	
	public List<Double> getOutput(double[] input) {
		if(input.length != layers.get(0).getNeurons().size()){
			throw new RuntimeException ("Wrong number of values in input. Input layer has " + layers.get(0).getNeurons().size()+" neurons.");
		}
		List<Double> in = new ArrayList<Double>();
		for(int i=0;i<input.length;i++){
			in.add(input[i]);
		}
		((InputLayer) layers.get(0)).setOutputs(in);
		for(Layer layer: layers){
			if(!(layer instanceof InputLayer)){
				layer.countOutput();
			}
		}
		return ((OutputLayer)layers.get(layers.size()-1)).getOutput();
	}
	
	public void store(String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	@Override
	public String toString() {
		String output = "NeuralNetwork{layers=" + layers.size();
		for (Layer layer: layers) {
			output += ", " + layer.getNeurons().size();
		}
		output += "}";
		return output;
	}
}
