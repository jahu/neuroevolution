package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.Chromosome;
import pl.jacekgralak.neuroevolution.neural.core.NeuralNetwork;

public class NeuralChromosome implements Chromosome<NeuralNetwork> {

    private NeuralNetwork neuralNetwork;

    public NeuralChromosome(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    @Override
    public NeuralNetwork getGenotype() {
        return neuralNetwork;
    }
}
