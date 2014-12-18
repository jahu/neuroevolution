package pl.jacekgralak.neuroevolution.genetic.core;

import pl.jacekgralak.neuroevolution.genetic.core.domain.*;

public class GeneticTask {

    private Population population;
    private FitnessFunction fitnessFunction;
    private FitnessTestData fitnessTestData;
    private SelectionMethod selectionMethod;
    private ReproductionMethod reproductionMethod;

    public GeneticTask(Population population, FitnessFunction fitnessFunction, FitnessTestData fitnessTestData,
                       SelectionMethod selectionMethod, ReproductionMethod reproductionMethod) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
        this.fitnessTestData = fitnessTestData;
        this.selectionMethod = selectionMethod;
        this.reproductionMethod = reproductionMethod;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }

    public void setFitnessFunction(FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public FitnessTestData getFitnessTestData() {
        return fitnessTestData;
    }

    public void setFitnessTestData(FitnessTestData fitnessTestData) {
        this.fitnessTestData = fitnessTestData;
    }

    public SelectionMethod getSelectionMethod() {
        return selectionMethod;
    }

    public void setSelectionMethod(SelectionMethod selectionMethod) {
        this.selectionMethod = selectionMethod;
    }

    public ReproductionMethod getReproductionMethod() {
        return reproductionMethod;
    }

    public void setReproductionMethod(ReproductionMethod reproductionMethod) {
        this.reproductionMethod = reproductionMethod;
    }

    @Override
    public String toString() {
        return "GeneticTask{" +
                "population=" + population +
                ", fitnessFunction=" + fitnessFunction +
                ", fitnessTestData=" + fitnessTestData +
                ", selectionMethod=" + selectionMethod +
                ", reproductionMethod=" + reproductionMethod +
                '}';
    }
}
