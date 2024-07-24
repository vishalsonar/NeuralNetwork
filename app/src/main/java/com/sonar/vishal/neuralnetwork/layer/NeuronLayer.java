package com.sonar.vishal.neuralnetwork.layer;

import com.sonar.vishal.neuralnetwork.interfaces.IActivationFunction;
import com.sonar.vishal.neuralnetwork.neuron.Neuron;

import java.util.ArrayList;

public abstract class NeuronLayer {

    protected int numberOfNeuronsInLayer;
    protected ArrayList<Neuron> neuron;
    protected IActivationFunction activationFunction;
    protected NeuronLayer previousLayer;
    protected NeuronLayer nextLayer;
    protected ArrayList<Double> input;
    protected ArrayList<Double> output;
    protected int numberOfInputs;

    public NeuronLayer getPreviousLayer() {
        return previousLayer;
    }

    public void setPreviousLayer(NeuronLayer previousLayer) {
        this.previousLayer = previousLayer;
    }

    public NeuronLayer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(NeuronLayer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public ArrayList<Double> getInput() {
        return input;
    }

    public void setInput(ArrayList<Double> input) {
        this.input = input;
    }

    public ArrayList<Double> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<Double> output) {
        this.output = output;
    }

    public void initialize() {
        if (numberOfNeuronsInLayer > 0) {
            neuron = new ArrayList<>(numberOfNeuronsInLayer);
            for (int i = 0; i < numberOfNeuronsInLayer; i++) {
                try {
                    neuron.set(i, new Neuron(numberOfInputs, activationFunction));
                } catch (Exception exception) {
                    neuron.add(new Neuron(numberOfInputs, activationFunction));
                }
            }
        }
    }

    public void calculate() {
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            Neuron currentNeuron = neuron.get(i);
            currentNeuron.setInput(this.input).calculate();
            try {
                output.set(i, currentNeuron.getOutput());
            } catch (Exception exception) {
                output.add(currentNeuron.getOutput());
            }
        }
    }
}
