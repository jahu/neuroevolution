package pl.jacekgralak.neuroevolution.genetic.core.domain;

public interface ReproductionMethod<T extends Population> {

    T getNewGeneration(Population population);

}
