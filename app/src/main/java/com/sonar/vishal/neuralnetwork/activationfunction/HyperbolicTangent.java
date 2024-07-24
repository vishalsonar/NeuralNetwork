package com.sonar.vishal.neuralnetwork.activationfunction;

import com.sonar.vishal.neuralnetwork.interfaces.IActivationFunction;

public class HyperbolicTangent implements IActivationFunction {

    private double offset = 1.0;

    public HyperbolicTangent(double offset) {
        this.offset = offset;
    }

    @Override
    public Double calculate(Double outputBeforeActivation) {
        return (1.0 - Math.exp(-offset * outputBeforeActivation)) / (1.0 + Math.exp(-offset * outputBeforeActivation));
    }
}
