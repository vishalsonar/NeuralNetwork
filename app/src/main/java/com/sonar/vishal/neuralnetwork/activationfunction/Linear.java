package com.sonar.vishal.neuralnetwork.activationfunction;

import com.sonar.vishal.neuralnetwork.interfaces.IActivationFunction;

public class Linear implements IActivationFunction {

    private double offset = 1.0;

    public Linear(double offset) {
        this.offset = offset;
    }

    @Override
    public Double calculate(Double outputBeforeActivation) {
        return offset * outputBeforeActivation;
    }
}
