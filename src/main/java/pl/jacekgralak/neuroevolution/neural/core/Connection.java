package pl.jacekgralak.neuroevolution.neural.core;

import java.io.Serializable;

public class Connection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6498677976052364184L;

	private Neuron neuron;
	private Weight weight;

	public Neuron getNeuron() {
		return neuron;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}
	
	public Connection(Neuron neuron, Weight weight){
		this.neuron = neuron;
		this.weight = weight;
	}
	
	public Connection(Neuron neuron){
		this.neuron = neuron;
		this.weight = new Weight();
	}
	
}
