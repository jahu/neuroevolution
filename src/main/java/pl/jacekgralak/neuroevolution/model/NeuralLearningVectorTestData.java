package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.FitnessTestData;
import pl.jacekgralak.neuroevolution.neural.core.LearningVector;

import java.util.List;

public class NeuralLearningVectorTestData implements FitnessTestData<List<LearningVector>> {

    private List<LearningVector> learningVectors;

    public NeuralLearningVectorTestData(List<LearningVector> learningVectors) {
        this.learningVectors = learningVectors;
    }

    @Override
    public List<LearningVector> getTestData() {
        return learningVectors;
    }
}
