package com.sonar.vishal.neuralnetwork.interfaces;

import com.sonar.vishal.neuralnetwork.activationfunction.HardLimitThreshold;
import com.sonar.vishal.neuralnetwork.activationfunction.HyperbolicTangent;
import com.sonar.vishal.neuralnetwork.activationfunction.Linear;
import com.sonar.vishal.neuralnetwork.activationfunction.Sigmoid;

public interface IActivationFunction {

    Double calculate(Double outputBeforeActivation);

    static IActivationFunction getFunction(IActivationFunction.NAME name, double offset) {
        if (name.equals(NAME.STEP)) {
            return new HardLimitThreshold();
        }
        if (name.equals(NAME.LINEAR)) {
            return new Linear(offset);
        }
        if (name.equals(NAME.SIGMOD)) {
            return new Sigmoid(offset);
        }
        if (name.equals(NAME.HYPERTAN)) {
            return new HyperbolicTangent(offset);
        }
        return null;
    }

    enum NAME {
        STEP, LINEAR, SIGMOD, HYPERTAN
    }
}
