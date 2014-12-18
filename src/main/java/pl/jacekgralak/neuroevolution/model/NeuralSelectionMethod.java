package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.Chromosome;
import pl.jacekgralak.neuroevolution.genetic.core.domain.SelectionMethod;

import java.util.*;

public class NeuralSelectionMethod implements SelectionMethod<NeuralPopulation, NeuralFitnessFunction, NeuralLearningVectorTestData> {

    @Override
    public NeuralPopulation selectStrongestPopulation(NeuralPopulation population, NeuralFitnessFunction fitnessFunction, NeuralLearningVectorTestData fitnessTestData) {
        Map<NeuralChromosome, Double> chromosomesAdaptation = new HashMap<>();
        for (NeuralChromosome neuralNetwork: population.getChromosomes()) {
            double error = fitnessFunction.getAdaptationValue(neuralNetwork, fitnessTestData);
            chromosomesAdaptation.put(neuralNetwork, error);
        }

        List<NeuralChromosome> chromosomes = new ArrayList<>();
        chromosomes.addAll(chromosomesAdaptation.keySet());

        chromosomes.sort(new Comparator<NeuralChromosome>() {
            @Override
            public int compare(NeuralChromosome neuralChromosome, NeuralChromosome t1) {
                Double a = chromosomesAdaptation.get(neuralChromosome);
                Double b = chromosomesAdaptation.get(t1);
                int res = a.compareTo(b);
                return a.compareTo(b);
            }
        });

        // TODO select only best chromosomes
        population.getChromosomes().clear();
        for (int i=0; i<chromosomes.size(); i++) {
            population.getChromosomes().add(chromosomes.get(i));
        }
        return population;
    }

    @Override
    public Chromosome selectStrongestChromosome(NeuralPopulation population, NeuralFitnessFunction fitnessFunction, NeuralLearningVectorTestData fitnessTestData) {
        Chromosome strongestChromosome = null;
        double minError = Double.MAX_VALUE;
        for (NeuralChromosome neuralNetwork: population.getChromosomes()) {
            double error = fitnessFunction.getAdaptationValue(neuralNetwork, fitnessTestData);
            if (error < minError) {
                minError = error;
                strongestChromosome = neuralNetwork;
            }
        }
        return strongestChromosome;
    }

}
