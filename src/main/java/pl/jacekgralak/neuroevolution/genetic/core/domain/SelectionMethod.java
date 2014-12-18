package pl.jacekgralak.neuroevolution.genetic.core.domain;

public interface SelectionMethod<T extends Population, Y extends FitnessFunction, Z extends FitnessTestData> {

    T selectStrongestPopulation(T population, Y fitnessFunction, Z fitnessTestData);

    Chromosome selectStrongestChromosome(T population, Y fitnessFunction, Z fitnessTestData);

}
