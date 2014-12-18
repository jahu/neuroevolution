package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.FitnessFunction;
import pl.jacekgralak.neuroevolution.neural.core.LearningVector;
import pl.jacekgralak.neuroevolution.neural.core.NeuralNetwork;

import java.util.List;

public class NeuralFitnessFunction implements FitnessFunction<NeuralChromosome, NeuralLearningVectorTestData> {

    @Override
    public double getAdaptationValue(NeuralChromosome chromosome, NeuralLearningVectorTestData testData) {

        NeuralNetwork neuralNetwork = chromosome.getGenotype();
        List<LearningVector> learningVectors = testData.getTestData();

        double totalError = 0.0;

        for (LearningVector learningVector: learningVectors) {
            List<Double> input = learningVector.getInput();
            List<Double> targetOutput = learningVector.getOutput();
            List<Double> networkOutput = neuralNetwork.getOutput(input);

            if (targetOutput.size() != networkOutput.size()) {
                throw new RuntimeException("Network output has unexpected size!");
            } else {
                for (int i=0; i<networkOutput.size(); i++) {
                    totalError += Math.abs(networkOutput.get(i) - targetOutput.get(i));
                }
            }

        }
        // smaller total error == better adaptation
        return totalError;
    }
}
