package pl.jacekgralak.neuroevolution.genetic.facade;

import pl.jacekgralak.neuroevolution.genetic.core.GeneticTask;
import pl.jacekgralak.neuroevolution.genetic.core.domain.Chromosome;
import pl.jacekgralak.neuroevolution.genetic.core.domain.StopCondition;

public interface GeneticEvaluator {

    Chromosome evaluate(GeneticTask geneticTask, StopCondition stopCondition);

}
