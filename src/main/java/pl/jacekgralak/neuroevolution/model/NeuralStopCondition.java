package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.StopCondition;
import pl.jacekgralak.neuroevolution.neural.core.LearningVector;
import pl.jacekgralak.neuroevolution.neural.core.NeuralNetwork;

import java.util.List;

public class NeuralStopCondition implements StopCondition<NeuralChromosome, NeuralLearningVectorTestData> {

    private static final double MIN_ACCEPTABLE_ERROR = 0.1;

    @Override
    public boolean isChromosomeAcceptable(NeuralChromosome chromosome, NeuralLearningVectorTestData testData) {

        // TODO duplication of fitness function
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

        if (totalError <= MIN_ACCEPTABLE_ERROR) {
            return true;
        } else {
            return false;
        }
    }
}
