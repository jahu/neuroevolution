package pl.jacekgralak.neuroevolution.neural.core;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer extends Layer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3773442729091072066L;
	private List<Neuron> neurons;
	
	public OutputLayer(int noNeurons, ActivationFunction activationFunction) {
		super(noNeurons, activationFunction);
		neurons = super.getNeurons();
	}
	
	public List<Double> getOutput(){
		List<Double> output = new ArrayList<>();
		for (Neuron neuron: neurons){
			output.add(neuron.getOutput());
		}
		return output;
	}

}
