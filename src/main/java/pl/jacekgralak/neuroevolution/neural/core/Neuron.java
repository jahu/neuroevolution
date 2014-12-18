package pl.jacekgralak.neuroevolution.neural.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3547720722250191190L;

	private ActivationFunction activationFunction;
	
	private double output;
	
	private List<Connection> input;
	private boolean firstLayer;

	private double bias;
	private boolean biasEnabled = true;

	public Neuron(ActivationFunction activationFunction){
		this.activationFunction = activationFunction;
		output = 0.0;
		input = new ArrayList<Connection>();
		bias = 0.0;
		this.firstLayer = false;
	}
	
	public Neuron(ActivationFunction activationFunction, boolean isInputLayer){
		this.activationFunction = activationFunction;
		output = 0.0;
		input = new ArrayList<Connection>();
		bias = 0.0;
		this.firstLayer = false;
		firstLayer = isInputLayer;
	}
	
	public void addInput(Neuron neuron){
		input.add(new Connection(neuron));
	}
	
	public void addInput(Layer layer){
		for(Neuron neuron: layer.getNeurons()){
			addInput(neuron);
		}
	}

	public boolean isBiasEnabled() {
		return biasEnabled;
	}

	public void setBiasEnabled(boolean biasEnabled) {
		this.biasEnabled = biasEnabled;
	}
	
	public void genearateWeights(Random rand){
		for (Connection conn: input){
			conn.getWeight().setValue(rand.nextDouble());
		}
		bias = rand.nextDouble();
	}
	
	public void countOutput(){
		if (firstLayer) {
			return;
		} else {
			output = 0.0;
			for (Connection conn : input) {
				output += conn.getNeuron().getOutput() * conn.getWeight().getValue();
			}
			if (biasEnabled) {
				output += bias;
			}
			output = activationFunction.getValue(output);
		}
	}
	
	public double getOutput(){
		return output;
	}
	
	public void setOutput(double output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "Neuron{" +
				"activationFunction=" + activationFunction +
				", output=" + output +
				", input=" + input +
				", bias=" + bias +
				'}';
	}
}
