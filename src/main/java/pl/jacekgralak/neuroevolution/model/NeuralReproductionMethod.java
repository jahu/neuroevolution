package pl.jacekgralak.neuroevolution.model;

import pl.jacekgralak.neuroevolution.genetic.core.domain.Population;
import pl.jacekgralak.neuroevolution.genetic.core.domain.ReproductionMethod;

public class NeuralReproductionMethod implements ReproductionMethod<NeuralPopulation> {

    @Override
    public NeuralPopulation getNewGeneration(Population population) {
        return (NeuralPopulation) population;
    }
}
