package pl.jacekgralak.neuroevolution.neural.core;

import java.io.Serializable;

public interface ActivationFunction extends Serializable {

	double getValue(double x);
	double getDerivative(double x);

}
