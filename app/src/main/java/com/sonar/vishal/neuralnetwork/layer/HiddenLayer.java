package com.sonar.vishal.neuralnetwork.layer;

import com.sonar.vishal.neuralnetwork.interfaces.IActivationFunction;

import java.util.ArrayList;

public class HiddenLayer extends NeuronLayer {

    public HiddenLayer(int numberOfNeurons, IActivationFunction activationFunction, int numberOfInputs) {
        this.numberOfInputs = numberOfInputs;
        this.activationFunction = activationFunction;
        this.numberOfNeuronsInLayer = numberOfNeurons;
        this.output = new ArrayList<>(numberOfNeurons);
        initialize();
    }
}
