package com.sonar.vishal.neuralnetwork.data;

import java.util.ArrayList;

public class NeuralDataSet {

    private Double[][] dataSet;
    private int[] inputColumns;
    private int[] outputColumns;
    private ArrayList<ArrayList<Double>> input;
    private ArrayList<ArrayList<Double>> output;
    private ArrayList<ArrayList<Double>> neuralNetworkOutput;

    public NeuralDataSet(Double[][] dataSet, int[] inputColumns, int[] outputColumns) {
        this.dataSet = dataSet;
        this.inputColumns = inputColumns;
        this.outputColumns = outputColumns;
    }

    public void createInputOutputArray() {
        input = new ArrayList<ArrayList<Double>>();
        output = new ArrayList<ArrayList<Double>>();
        neuralNetworkOutput = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> tempInput = new ArrayList<>();
        ArrayList<Double> tempOutput = new ArrayList<>();

        for (Double[] data : dataSet) {
            tempInput = new ArrayList<>();
            tempOutput = new ArrayList<>();

            for (int i : inputColumns) {
                tempInput.add(data[i]);
            }
            input.add(tempInput);

            for (int i : outputColumns) {
                tempOutput.add(data[i]);
            }
            output.add(tempOutput);
        }
    }

    public void printOutputArray() {
        for (int i = 0; i < input.size(); i++) {
            System.out.println("Neural Input: " + input.get(i) + ", Neural Expected Output: " + output.get(i) + ", Neural Actual Output: " + neuralNetworkOutput.get(i));
        }
    }

    public Double[][] getDataSet() {
        return dataSet;
    }

    public void setDataSet(Double[][] dataSet) {
        this.dataSet = dataSet;
    }

    public int[] getInputColumns() {
        return inputColumns;
    }

    public void setInputColumns(int[] inputColumns) {
        this.inputColumns = inputColumns;
    }

    public int[] getOutputColumns() {
        return outputColumns;
    }

    public void setOutputColumns(int[] outputColumns) {
        this.outputColumns = outputColumns;
    }

    public ArrayList<ArrayList<Double>> getInput() {
        return input;
    }

    public void setInput(ArrayList<ArrayList<Double>> input) {
        this.input = input;
    }

    public ArrayList<ArrayList<Double>> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<ArrayList<Double>> output) {
        this.output = output;
    }

    public ArrayList<ArrayList<Double>> getNeuralNetworkOutput() {
        return neuralNetworkOutput;
    }

    public void setNeuralNetworkOutput(ArrayList<ArrayList<Double>> neuralNetworkOutput) {
        this.neuralNetworkOutput = neuralNetworkOutput;
    }
}
