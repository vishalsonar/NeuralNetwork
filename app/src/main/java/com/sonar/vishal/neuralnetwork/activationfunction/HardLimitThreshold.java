package com.sonar.vishal.neuralnetwork.activationfunction;

import com.sonar.vishal.neuralnetwork.interfaces.IActivationFunction;

public class HardLimitThreshold implements IActivationFunction {

    @Override
    public Double calculate(Double outputBeforeActivation) {
        return outputBeforeActivation >= 0 ? 1.0 : 0;
    }
}
