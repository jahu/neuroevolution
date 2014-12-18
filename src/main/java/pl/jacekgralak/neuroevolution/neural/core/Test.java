package pl.jacekgralak.neuroevolution.neural.core;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		NeuralNetwork neuralNetwork = new NeuralNetwork(3, new int[]{2, 2, 1}, new SigmoidFunction(0.6));
		List<LearningVector> learningVectors = new ArrayList<LearningVector>();

		List<Double> input = new ArrayList<Double>();
		List<Double> output = new ArrayList<Double>();

		input.add(0.0);
		input.add(1.0);
		output.add(1.0);
		LearningVector vector = new LearningVector(input, output);
		learningVectors.add(vector);

		input = new ArrayList<Double>();
		output = new ArrayList<Double>();
		input.add(1.0);
		input.add(1.0);
		output.add(0.0);
		vector = new LearningVector(input, output);
		learningVectors.add(vector);

		input = new ArrayList<Double>();
		output = new ArrayList<Double>();
		input.add(0.0);
		input.add(0.0);
		output.add(0.0);
		vector = new LearningVector(input, output);
		learningVectors.add(vector);

		input = new ArrayList<Double>();
		output = new ArrayList<Double>();
		input.add(1.0);
		input.add(0.0);
		output.add(1.0);
		vector = new LearningVector(input, output);
		learningVectors.add(vector);


		System.out.println("1 = " + neuralNetwork.getOutput(new double[]{0.1, 0.9}));
		System.out.println("0 = " + neuralNetwork.getOutput(new double[]{0.9, 0.9}));
		System.out.println("0 = " + neuralNetwork.getOutput(new double[]{0.0, 0.1}));
		System.out.println("1 = " + neuralNetwork.getOutput(new double[]{0.9, 0.1}));

		System.out.println(neuralNetwork);

		neuralNetwork.addRandomHiddenNeuron();

		System.out.println("1 = " + neuralNetwork.getOutput(new double[]{0.1, 0.9}));
		System.out.println("0 = " + neuralNetwork.getOutput(new double[]{0.9, 0.9}));
		System.out.println("0 = " + neuralNetwork.getOutput(new double[]{0.0, 0.1}));
		System.out.println("1 = " + neuralNetwork.getOutput(new double[]{0.9, 0.1}));

		System.out.println(neuralNetwork);
	}
}
