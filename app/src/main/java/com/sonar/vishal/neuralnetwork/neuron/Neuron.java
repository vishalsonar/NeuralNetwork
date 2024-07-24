package com.sonar.vishal.neuralnetwork.neuron;

import com.sonar.vishal.neuralnetwork.interfaces.IActivationFunction;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Neuron {

    private ArrayList<Double> weight;
    private ArrayList<Double> input;
    private Double output;
    private int numberOfInputs = 0;
    private Double bias = 1.0;
    private IActivationFunction activationFunction;

    public ArrayList<Double> getWeight() {
        return weight;
    }

    public Neuron setWeight(ArrayList<Double> weight) {
        this.weight = weight;
        return this;
    }

    public ArrayList<Double> getInput() {
        return input;
    }

    public Neuron setInput(ArrayList<Double> input) {
        this.input = input;
        return this;
    }

    public Double getOutput() {
        return output;
    }

    public Neuron setOutput(Double output) {
        this.output = output;
        return this;
    }

    public Neuron(int numberOfInputs, IActivationFunction activationFunction) {
        this.numberOfInputs = numberOfInputs;
        this.activationFunction = activationFunction;
        weight = new ArrayList<>(numberOfInputs + 1);
        input = new ArrayList<>(numberOfInputs);

        for (int i = 0; i <= numberOfInputs; i++) {
            double weight = RandomGenerator.getDefault().nextDouble();
            try {
                this.weight.set(i, weight);
            } catch (Exception exception) {
                this.weight.add(weight);
            }
        }
    }

    public void calculate() {
        Double outputBeforeActivation = 0.0;
        if (numberOfInputs > 0) {
            if (input != null && weight != null) {
                for (int i = 0; i <= numberOfInputs; i++) {
                    outputBeforeActivation += (i == numberOfInputs ? bias : input.get(i)) * weight.get(i);
                }
            }
        }
        output = activationFunction.calculate(outputBeforeActivation);
    }
}
