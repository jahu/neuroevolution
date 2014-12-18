package pl.jacekgralak.neuroevolution.genetic.facade;

import pl.jacekgralak.neuroevolution.genetic.core.GeneticTask;
import pl.jacekgralak.neuroevolution.genetic.core.domain.*;

import java.util.List;
import java.util.Set;

public class SimpleGeneticEvaluator implements GeneticEvaluator {

    private static final long MAX_NUMBER_OF_EVOLUTION_CYCLES = 500;

    private StopCondition stopCondition = null;

    @Override
    public Chromosome evaluate(GeneticTask geneticTask, StopCondition stopCondition) {
        this.stopCondition = stopCondition;

        Population population = geneticTask.getPopulation();
        FitnessFunction fitnessFunction = geneticTask.getFitnessFunction();
        FitnessTestData fitnessTestData = geneticTask.getFitnessTestData();
        SelectionMethod selectionMethod = geneticTask.getSelectionMethod();
        ReproductionMethod reproductionMethod = geneticTask.getReproductionMethod();

        int numberOfEvolutionCycles = 0;

        while (! hasAcceptableChromosome(population, fitnessTestData) && numberOfEvolutionCycles < MAX_NUMBER_OF_EVOLUTION_CYCLES) {
            population = selectionMethod.selectStrongestPopulation(population, fitnessFunction, fitnessTestData);
            population = reproductionMethod.getNewGeneration(population);
            numberOfEvolutionCycles++;
            System.out.println(numberOfEvolutionCycles);
        }

        return selectionMethod.selectStrongestChromosome(population, fitnessFunction, fitnessTestData);
    }

    private boolean hasAcceptableChromosome(Population population, FitnessTestData fitnessTestData) {
        List<Chromosome> chromosomes = population.getChromosomes();
        for (Chromosome chromosome: chromosomes) {
            if (stopCondition.isChromosomeAcceptable(chromosome, fitnessTestData)) {
                return true;
            }
        }
        return false;
    }

}
