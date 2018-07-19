package edu.rice.pliny.apitrans.examples;

import ca.pjer.ekmeans.EKmeans;
import jsat.classifiers.DataPoint;
import jsat.classifiers.neuralnetwork.BackPropagationNet;
import jsat.io.CSV;
import jsat.regression.RegressionDataSet;
import jsat.regression.RidgeRegression;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.api.NeuralNetwork;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.activations.impl.ActivationTanH;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import smile.data.AttributeDataset;
import smile.data.NominalAttribute;
import smile.data.NumericAttribute;
import smile.data.parser.DelimitedTextParser;

import static org.junit.Assert.assertEquals;

public class ML {
    String regress_train = "code_completion/src/test/resources/apitrans/regression/regress.csv";
    String classificiation_train = "code_completion/src/test/resources/apitrans/regression/classification.csv";

    @Test
    public void linreg() throws ParseException, IOException {
        Path train_file = Paths.get(regress_train);
        Set<Integer> features = new HashSet<>();
        features.add(1);
        features.add(2);
        double lambda = 1.0;
        RegressionDataSet train_data = CSV.readR(0, train_file, 0, features);
        RidgeRegression model = new RidgeRegression(lambda);
        model.train(train_data);

        DataPoint dp = train_data.getDataPoint(0);
        double result = model.regress(dp);
        System.out.println(result);
        assertEquals(result > 0.0, true);
    }

    @Test
    public void linreg2() throws ParseException, IOException {
        String y_label = "y";
        int y_index = 0;
        String delim = ",";
        int train_data_size = 5;
        double[][] train_data_x = new double[train_data_size][];
        double[] train_data_y = new double[train_data_size];

        NumericAttribute y_attr = new NumericAttribute(y_label);
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setResponseIndex(y_attr,y_index);
        parser.setDelimiter(delim);
        AttributeDataset train_data = parser.parse(regress_train);
        double[][] x = train_data.toArray(train_data_x);
        double[] y = train_data.toArray(train_data_y);
        double lambda = 1.0;

        smile.regression.RidgeRegression.Trainer ridge_trainer = new smile.regression.RidgeRegression.Trainer(lambda);
        smile.regression.RidgeRegression model = ridge_trainer.train(x, y);

        double[] foo = x[0];
        double result = model.predict(foo);
        System.out.println(result);
        assertEquals(result > 0.0, true);
    }

    @Test
    public void classification1() throws ParseException, IOException {
        Path train_file = Paths.get(classificiation_train);
        Set<Integer> features = new HashSet<>();
        features.add(1);
        features.add(2);
        double lambda = 1.0;
        RegressionDataSet train_data = CSV.readR(0, train_file, 0, features);
        BackPropagationNet model = new BackPropagationNet();
        model.train(train_data);

        DataPoint dp = train_data.getDataPoint(0);
        double zero_prop = model.classify(dp).getProb(0);
        int label;
        if(zero_prop > 0.5) {
            label = 1;
        } else {
            label = 0;
        }
        assertEquals(1, label);
    }

    @Test
    public void classification2() throws ParseException, IOException {
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setResponseIndex(new NominalAttribute("y"), 0);
        parser.setDelimiter(",");
        AttributeDataset train_data = parser.parse(classificiation_train);

        double[][] x = train_data.toArray(new double[train_data.size()][]);
        int[] y = train_data.toArray(new int[train_data.size()]);

        smile.classification.NeuralNetwork.Trainer model_traininer = new smile.classification.NeuralNetwork.Trainer(smile.classification.NeuralNetwork.ErrorFunction.CROSS_ENTROPY, 2, 10, 1);
        smile.classification.NeuralNetwork model = model_traininer.train(x, y);

        int label = model.predict(x[0]);
        assertEquals(0, label);
    }

    /*
    @Test
    public void classification2() throws ParseException, IOException, InterruptedException {
        File train_file = new File(classificiation_train);
        RecordReader reader = new CSVRecordReader();
        reader.initialize(new FileSplit(train_file));
        DataSetIterator iterator = new RecordReaderDataSetIterator(reader, 1);
        DataSet all_data = iterator.next();

        NeuralNetConfiguration.Builder conf_builder = new NeuralNetConfiguration.Builder();
        conf_builder.iterations(1);
        conf_builder.weightInit(WeightInit.XAVIER);
        conf_builder.activation(new ActivationTanH());
        conf_builder.learningRate(0.01);
        conf_builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);

        NeuralNetConfiguration.ListBuilder list_builder = conf_builder.list();

        DenseLayer.Builder in_layer_builder = new DenseLayer.Builder();
        in_layer_builder.nIn(2);
        in_layer_builder.nOut(10);
        DenseLayer in_layer = in_layer_builder.build();

        DenseLayer.Builder hidden_layer_builder = new DenseLayer.Builder();
        in_layer_builder.nIn(10);
        in_layer_builder.nOut(10);
        DenseLayer hidden_layer = hidden_layer_builder.build();

        DenseLayer.Builder out_layer_builder = new DenseLayer.Builder();
        in_layer_builder.nIn(10);
        in_layer_builder.nOut(1);
        in_layer_builder.activation(Activation.SOFTMAX);
        DenseLayer out_layer = out_layer_builder.build();

        list_builder.layer(0, in_layer);
        list_builder.layer(1, hidden_layer);
        list_builder.layer(1, hidden_layer);
        list_builder.backprop(true);
        list_builder.pretrain(false);
        MultiLayerConfiguration conf = list_builder.build();
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.fit(all_data);

        List<String> result = model.predict(all_data.get(0));
        System.out.println(result);
    }
    */
}

