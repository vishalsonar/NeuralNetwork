package com.sonar.vishal.neuralnetwork.learningalgorithm;

import com.sonar.vishal.neuralnetwork.network.NeuralNetwork;

public abstract class LearningAlgorithm {

    protected NeuralNetwork neuralNetwork;
    protected int maxEpochs = 100;
    protected int epoch = 0;
    protected double minOverallError = 0.001;
    protected double learningRate = 0.1;
    protected boolean printTraining;
    protected abstract void train();
    protected abstract void forward();
    protected abstract void forward(int i);
    protected abstract Double calcNewWeight(int layer, int input, int neuron);
    protected abstract Double calcNewWeight(int layer, int input, int neuron, double error);
}
