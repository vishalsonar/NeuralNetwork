package com.sonar.vishal.neuralnetwork.network;

import com.sonar.vishal.neuralnetwork.activationfunction.Linear;
import com.sonar.vishal.neuralnetwork.enumeration.ActivationFunction;
import com.sonar.vishal.neuralnetwork.interfaces.IActivationFunction;
import com.sonar.vishal.neuralnetwork.layer.HiddenLayer;
import com.sonar.vishal.neuralnetwork.layer.InputLayer;
import com.sonar.vishal.neuralnetwork.layer.NeuronLayer;
import com.sonar.vishal.neuralnetwork.layer.OutputLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class NeuralNetwork {

    private InputLayer inputLayer;
    private OutputLayer outputLayer;
    private ArrayList<Double> input;
    private ArrayList<Double> output;
    private int numberOfHiddenLayers;

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

    public NeuralNetwork(int numberOfInputs, int numberOfOutputs, IActivationFunction outputActivationFunction) {
        this(numberOfInputs, numberOfOutputs, new int[]{}, new IActivationFunction[]{}, outputActivationFunction);
    }

    public NeuralNetwork(int numberOfInputs, int numberOfOutputs, int[] numberOfHiddenNeurons, IActivationFunction[] hiddenActivationFunction, IActivationFunction outputActivationFunction) {
        this.input = new ArrayList<>(numberOfInputs);
        this.output = new ArrayList<>(numberOfOutputs);
        this.numberOfHiddenLayers = numberOfHiddenNeurons.length;
        this.inputLayer = new InputLayer(numberOfInputs);
        if (numberOfHiddenLayers == 0) {
            this.outputLayer = new OutputLayer(numberOfOutputs, outputActivationFunction, numberOfInputs);
        } else {
            this.outputLayer = new OutputLayer(numberOfOutputs, outputActivationFunction, numberOfHiddenNeurons[this.numberOfHiddenLayers - 1]);
        }

        HiddenLayer hiddenLayer = null;
        HiddenLayer previousHiddenLayer = null;
        if (numberOfHiddenLayers == 0) {
            this.inputLayer.setNextLayer(this.outputLayer);
            this.outputLayer.setPreviousLayer(this.inputLayer);
        }
        for (int i = 1; i <= numberOfHiddenLayers; i++) {
            hiddenLayer = new HiddenLayer(numberOfHiddenNeurons[i - 1], hiddenActivationFunction[i - 1], numberOfInputs);
            if (i == 1) {
                previousHiddenLayer = hiddenLayer;
                this.inputLayer.setNextLayer(hiddenLayer);
                if (i == numberOfHiddenLayers) {
                    this.outputLayer.setPreviousLayer(hiddenLayer);
                }
                hiddenLayer.setNextLayer(this.outputLayer);
                hiddenLayer.setPreviousLayer(this.inputLayer);
                continue;
            }
            previousHiddenLayer.setNextLayer(hiddenLayer);
            hiddenLayer.setPreviousLayer(previousHiddenLayer);
            if (i == numberOfHiddenLayers) {
                this.outputLayer.setPreviousLayer(hiddenLayer);
                hiddenLayer.setNextLayer(this.outputLayer);
                continue;
            }
            previousHiddenLayer = hiddenLayer;
        }
    }

    public void calculate() {
        inputLayer.setInput(this.input);
        if (numberOfHiddenLayers == 0) {
            this.outputLayer.setInput(this.input);
            this.outputLayer.calculate();
            this.output = this.outputLayer.getOutput();
        }

        NeuronLayer currentLayer = null;
        for (int i = 1; i <= numberOfHiddenLayers + 1; i++) {
            if (i == 1) {
                currentLayer = this.inputLayer.getNextLayer();
                currentLayer.setInput(currentLayer.getPreviousLayer().getInput());
                currentLayer.calculate();
                if (i == numberOfHiddenLayers) {
                    output = currentLayer.getOutput();
                }
                continue;
            }
            currentLayer = currentLayer.getNextLayer();
            currentLayer.setInput(currentLayer.getPreviousLayer().getOutput());
            currentLayer.calculate();
            if (i > numberOfHiddenLayers) {
                output = currentLayer.getOutput();
            }
        }
    }

    public static void main(String[] a) {
        int[] numberOfNeurons = {3};
        IActivationFunction[] hiddenActivationFunction = {IActivationFunction.getFunction(ActivationFunction.SIGMOD, 1.0)};
        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 1, numberOfNeurons, hiddenActivationFunction, new Linear(1.0));

        neuralNetwork.setInput(new ArrayList<Double>(Arrays.asList(1.5, 0.5)));
        neuralNetwork.calculate();
        neuralNetwork.getOutput().forEach(System.out::println);
    }
}
