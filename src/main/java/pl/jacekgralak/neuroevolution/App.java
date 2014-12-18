package pl.jacekgralak.neuroevolution;

import pl.jacekgralak.neuroevolution.genetic.core.GeneticTask;
import pl.jacekgralak.neuroevolution.genetic.core.domain.*;
import pl.jacekgralak.neuroevolution.genetic.facade.GeneticEvaluator;
import pl.jacekgralak.neuroevolution.genetic.facade.SimpleGeneticEvaluator;
import pl.jacekgralak.neuroevolution.model.*;
import pl.jacekgralak.neuroevolution.neural.core.LearningVector;
import pl.jacekgralak.neuroevolution.neural.core.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class App
{
    private static final int NUMBER_OF_CHROMOSOMES = 10;

    public static void main( String[] args )
    {
        Population neuralPopulation = generateInitialPopulation();
        FitnessFunction neuralFitnessFunction = new NeuralFitnessFunction();
        FitnessTestData neuralTestData = generateTestData();
        SelectionMethod neuralSelectionMethod = new NeuralSelectionMethod();
        ReproductionMethod neuralReproductionMethod = new NeuralReproductionMethod();

        GeneticTask geneticTask = new GeneticTask(neuralPopulation, neuralFitnessFunction,
                neuralTestData, neuralSelectionMethod, neuralReproductionMethod);

        StopCondition stopCondition = new NeuralStopCondition();

        GeneticEvaluator geneticEvaluator = new SimpleGeneticEvaluator();
        NeuralChromosome solution = (NeuralChromosome) geneticEvaluator.evaluate(geneticTask, stopCondition);
        NeuralNetwork neuralNetwork = solution.getGenotype();

        System.out.println("1 = " + neuralNetwork.getOutput(new double[]{0.1, 0.9}));
        System.out.println("0 = " + neuralNetwork.getOutput(new double[]{0.9, 0.9}));
        System.out.println("0 = " + neuralNetwork.getOutput(new double[]{0.0, 0.1}));
        System.out.println("1 = " + neuralNetwork.getOutput(new double[]{0.9, 0.1}));

        System.out.println(solution.getGenotype());
    }

    private static FitnessTestData generateTestData() {
        List<LearningVector> learningVectors = new ArrayList<>();

        List<Double> input = new ArrayList<>();
        List<Double> output = new ArrayList<>();

        input.add(0.0);
        input.add(1.0);
        output.add(1.0);
        LearningVector vector = new LearningVector(input, output);
        learningVectors.add(vector);

        input = new ArrayList<>();
        output = new ArrayList<>();
        input.add(1.0);
        input.add(1.0);
        output.add(0.0);
        vector = new LearningVector(input, output);
        learningVectors.add(vector);

        input = new ArrayList<>();
        output = new ArrayList<>();
        input.add(0.0);
        input.add(0.0);
        output.add(0.0);
        vector = new LearningVector(input, output);
        learningVectors.add(vector);

        input = new ArrayList<>();
        output = new ArrayList<>();
        input.add(1.0);
        input.add(0.0);
        output.add(1.0);
        vector = new LearningVector(input, output);
        learningVectors.add(vector);

        return new NeuralLearningVectorTestData(learningVectors);
    }

    private static Population generateInitialPopulation() {
        NeuralPopulation neuralPopulation = new NeuralPopulation(NUMBER_OF_CHROMOSOMES);
        return neuralPopulation;
    }
}
