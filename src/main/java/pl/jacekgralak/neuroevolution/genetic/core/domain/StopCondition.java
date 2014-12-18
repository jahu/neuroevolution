package pl.jacekgralak.neuroevolution.genetic.core.domain;

public interface StopCondition<T extends Chromosome, Y extends FitnessTestData> {

    boolean isChromosomeAcceptable(T chromosome, Y testData);

}
