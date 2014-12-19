package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.Chromosome;
import pl.jacekgralak.neuroevolution.genetic.core.domain.SelectionMethod;

import java.util.*;

public class NeuralSelectionMethod implements SelectionMethod<NeuralPopulation, NeuralFitnessFunction, NeuralLearningVectorTestData> {

    @Override
    public NeuralPopulation selectStrongestPopulation(NeuralPopulation population, NeuralFitnessFunction fitnessFunction, NeuralLearningVectorTestData fitnessTestData) {
        List<NeuralChromosomeError> chromosomeErrors = new ArrayList<>();
        for (NeuralChromosome neuralNetwork: population.getChromosomes()) {
            double error = fitnessFunction.getAdaptationValue(neuralNetwork, fitnessTestData);
            chromosomeErrors.add(new NeuralChromosomeError(error, neuralNetwork));
        }

        population.getChromosomes().clear();
        Collections.sort(chromosomeErrors);
        for (int i=0; i<(chromosomeErrors.size() / 2); i++) {
            population.getChromosomes().add(chromosomeErrors.get(i).getNeuralChromosome());
            population.getChromosomes().add(chromosomeErrors.get(i).getNeuralChromosome());
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

    class NeuralChromosomeError implements Comparable<NeuralChromosomeError> {
        private NeuralChromosome neuralChromosome;
        private double error;

        public NeuralChromosomeError(double error, NeuralChromosome neuralChromosome) {
            this.error = error;
            this.neuralChromosome = neuralChromosome;
        }

        public NeuralChromosome getNeuralChromosome() {
            return neuralChromosome;
        }

        public double getError() {
            return error;
        }

        @Override
        public int compareTo(NeuralChromosomeError neuralChromosomeError) {
            Double a = this.getError();
            Double b = neuralChromosomeError.getError();
            int res = a.compareTo(b);
            return a.compareTo(b);
        }
    }

}
