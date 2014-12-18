package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.Population;
import pl.jacekgralak.neuroevolution.neural.core.NeuralNetwork;
import pl.jacekgralak.neuroevolution.neural.core.SigmoidFunction;

import java.util.HashSet;
import java.util.Set;

public class NeuralPopulation implements Population<NeuralChromosome> {

    private Set<NeuralChromosome> chromosomes;

    public NeuralPopulation(int numberOfChromosomes) {
        chromosomes = new HashSet<>();
        for (int i=0 ;i <numberOfChromosomes; i++) {
            try {
                chromosomes.add(new NeuralChromosome(new NeuralNetwork(3, new int[]{2, 2, 1}, new SigmoidFunction(0.6))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Set<NeuralChromosome> getChromosomes() {
        return chromosomes;
    }

    @Override
    public void setChromosomes(Set<NeuralChromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

}
