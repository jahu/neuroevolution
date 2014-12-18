package pl.jacekgralak.neuroevolution.genetic.core.domain;

public interface StopCondition<T extends Chromosome> {

    boolean isChromosomeAcceptable(T chromosome);

}
