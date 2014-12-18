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
                double diff = chromosomesAdaptation.get(neuralChromosome) - chromosomesAdaptation.get(t1);
                if (diff < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return population;
    }

    @Override
    public Chromosome selectStrongestChromosome(NeuralPopulation population, NeuralFitnessFunction fitnessFunction, NeuralLearningVectorTestData fitnessTestData) {
        return population.getChromosomes().iterator().next();
    }

}
