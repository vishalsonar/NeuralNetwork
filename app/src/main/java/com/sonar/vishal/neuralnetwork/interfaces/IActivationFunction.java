package com.sonar.vishal.neuralnetwork.interfaces;

import com.sonar.vishal.neuralnetwork.activationfunction.HardLimitThreshold;
import com.sonar.vishal.neuralnetwork.activationfunction.HyperbolicTangent;
import com.sonar.vishal.neuralnetwork.activationfunction.Linear;
import com.sonar.vishal.neuralnetwork.activationfunction.Sigmoid;
import com.sonar.vishal.neuralnetwork.enumeration.ActivationFunction;

public interface IActivationFunction {

    Double calculate(Double outputBeforeActivation);

    static IActivationFunction getFunction(ActivationFunction name, double offset) {
        if (name.equals(ActivationFunction.STEP)) {
            return new HardLimitThreshold();
        }
        if (name.equals(ActivationFunction.LINEAR)) {
            return new Linear(offset);
        }
        if (name.equals(ActivationFunction.SIGMOD)) {
            return new Sigmoid(offset);
        }
        if (name.equals(ActivationFunction.HYPERTAN)) {
            return new HyperbolicTangent(offset);
        }
        return null;
    }
}
