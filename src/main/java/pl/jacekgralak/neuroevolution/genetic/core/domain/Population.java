package pl.jacekgralak.neuroevolution.genetic.core.domain;

import java.util.Set;

public interface Population<T extends Chromosome> {

    Set<T> getChromosomes();

    void setChromosomes(Set<T> chromosomes);

}
