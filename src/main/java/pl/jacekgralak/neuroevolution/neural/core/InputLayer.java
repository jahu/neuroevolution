package pl.jacekgralak.neuroevolution.neural.core;

import java.util.List;

public class InputLayer extends Layer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7319233834316282303L;
	private List<Neuron> neurons;
	
	public InputLayer(int noNeurons, ActivationFunction activationFunction) {
		for (int i=0; i<noNeurons; i++) {
			addNeuron(new Neuron(activationFunction, true));
		}
		neurons = super.getNeurons();
	}

	public void setOutputs(List<Double> output) {
		if (neurons.size() != output.size()) {
			throw new RuntimeException("Wrong number of elements. There are "
					+ neurons.size() + " neurons; " + output.size()
					+ " values was provided");
		} else {
			for(int i=0; i<neurons.size(); i++) {
				neurons.get(i).setOutput(output.get(i));
			}
		}
	}
}
