package pl.jacekgralak.neuroevolution.genetic.core.domain;

import java.util.List;

public interface Population<T extends Chromosome> {

    List<T> getChromosomes();

    void setChromosomes(List<T> chromosomes);

}
