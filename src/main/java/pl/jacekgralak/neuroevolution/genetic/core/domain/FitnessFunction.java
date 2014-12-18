package pl.jacekgralak.neuroevolution.genetic.core.domain;

public interface FitnessFunction<T extends Chromosome<?>, Y extends FitnessTestData<?>> {

    double getAdaptationValue(T chromosome, Y testData);

}
