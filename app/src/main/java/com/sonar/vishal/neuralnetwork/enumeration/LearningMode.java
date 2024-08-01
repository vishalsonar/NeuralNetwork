package com.sonar.vishal.neuralnetwork.enumeration;

/*
    Weight Update operation can be performed in online or batch mode.
    Online here implies that the weights are updated after every single record from the dataset.
    Batch update means that first all the record from the dataset are presented to the neural network
    before it starts updating its weight.
 */
public enum LearningMode {

    ONLINE, BATCH
}
