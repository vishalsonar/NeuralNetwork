package com.sonar.vishal.neuralnetwork.learningalgorithm;

import com.sonar.vishal.neuralnetwork.activationfunction.HyperbolicTangent;
import com.sonar.vishal.neuralnetwork.data.NeuralDataSet;
import com.sonar.vishal.neuralnetwork.enumeration.ErrorMeasurement;
import com.sonar.vishal.neuralnetwork.enumeration.LearningMode;
import com.sonar.vishal.neuralnetwork.network.NeuralNetwork;

import java.util.ArrayList;
import java.util.Arrays;

/*
    Delta Rule algorithm updates the weight according to the cost function.
    @see <a href="https://en.wikipedia.org/wiki/Delta_rule">Delta Rule</a>
 */
public class DeltaRule extends LearningAlgorithm {

    public ArrayList<ArrayList<Double>> error;
    public ArrayList<Double> generalError;
    public ArrayList<Double> overallError;
    public double overallGeneralError;
    public double degreeGeneralError = 2.0;
    public double degreeOverallError = 0.0;
    public ErrorMeasurement generalErrorMeasurement = ErrorMeasurement.SQUARE_ERROR;
    public ErrorMeasurement overallErrorMeasurement = ErrorMeasurement.MSE;
    public int currentRecord;
    public ArrayList<ArrayList<ArrayList<Double>>> newWeights;
    public LearningMode learningMode = LearningMode.ONLINE;
    public NeuralDataSet neuralDataSet;

    @Override
    protected void train() {
        if (learningMode.equals(LearningMode.BATCH)) {
            epoch = 0;

            // All data presented to the neural network.
            forward();
            while (epoch < maxEpochs && overallGeneralError > minOverallError) {
                epoch++;
                for (int j = 0; j < neuralNetwork.getOutput().size(); j++) {
                    for (int i = 0; i <= neuralNetwork.getInput().size(); i++) {
                        // calculate new weigths.
                    }
                }

                // after all weight are calculated, they are applied
                // applyNewWeights();

                // the error are updated with the new weights, All data presented to the neural network.
                forward();
            }
        }
        if (learningMode.equals(LearningMode.ONLINE)) {
            epoch = 0;
            int k = 0;
            currentRecord = 0;

            // Present K-th record to neural network.
            forward(k);
            while (epoch < maxEpochs && overallGeneralError > minOverallError) {
                epoch++;
                for (int j = 0; j < neuralNetwork.getOutput().size(); j++) {
                    for (int i = 0; i <= neuralNetwork.getInput().size(); i++) {
                        // calculate new weigths.
                    }
                }

                // after all weight are calculated, they are applied
                // applyNewWeights();
                currentRecord = ++k;

                // check k-th record as last row. If last row reset count to zero.

                // the error are updated with the new weights, Present K-th record to neural network.
                forward(k);
            }
        }
    }

    @Override
    protected void forward() {
        neuralDataSet.getInput().forEach(input -> {
            neuralNetwork.setInput(input);
            neuralNetwork.calculate();
            neuralDataSet.getNeuralNetworkOutput().add(neuralNetwork.getOutput());
        });
    }

    @Override
    protected void forward(int i) {
        neuralNetwork.setInput(neuralDataSet.getInput().get(i));
        neuralNetwork.calculate();
        neuralDataSet.getNeuralNetworkOutput().set(i, neuralNetwork.getOutput());
    }

    @Override
    protected Double calcNewWeight(int layer, int input, int neuron) {
        return null;
    }

    @Override
    protected Double calcNewWeight(int layer, int input, int neuron, double error) {
        return null;
    }

    public static void main(String[] a) {
        DeltaRule deltaRule = new DeltaRule();
        Double[][] dataSet = {
                {1.2, 0.11 * 1.2},
                {0.3, 0.11 * 0.3},
                {-0.5, 0.11 * -0.5},
                {-2.3, 0.11 * -2.3},
                {1.7, 0.11 * 1.7}
        };

        NeuralDataSet neuralDataSet = new NeuralDataSet(dataSet, new int[]{0}, new int[]{1});
        NeuralNetwork neuralNetwork = new NeuralNetwork(1, 1, new HyperbolicTangent(0.85));

        // Test Network.
        neuralNetwork.setInput(new ArrayList<Double>(Arrays.asList(1.2)));
        neuralNetwork.calculate();
        neuralNetwork.getOutput().forEach(System.out::println);

        // Configure Learning Delta Rule
        deltaRule.neuralNetwork = neuralNetwork;
        deltaRule.printTraining = true;
        deltaRule.learningRate = 0.3;
        deltaRule.maxEpochs = 1000;
        deltaRule.minOverallError = 0.00001;
        deltaRule.neuralDataSet = neuralDataSet;

        // Default output of network
        deltaRule.neuralDataSet.createInputOutputArray();
        deltaRule.forward();
        deltaRule.neuralDataSet.printOutputArray();

        // Beginning Training
        deltaRule.train();
    }
}
