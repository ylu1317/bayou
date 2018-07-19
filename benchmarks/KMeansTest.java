package edu.rice.pliny.apitrans.examples;

import ca.pjer.ekmeans.EKmeans;
import smile.clustering.KMeans;
import org.junit.Test;

public class KMeansTest {
    int n = 6; // the number of data to cluster
    int k = 3; // the number of cluster

    double[][] points = {{-10}, {-11}, {0}, {0}, {10}, {11}};
    double[][] centroids = {{-100}, {100}, {0}};

    @Test
    public void kmeans() {
        EKmeans eKmeans = new EKmeans(centroids, points);
        eKmeans.setIteration(64);
        eKmeans.setEqual(true);
        eKmeans.setDistanceFunction(EKmeans.EUCLIDEAN_DISTANCE_FUNCTION);
        eKmeans.run();
        int[] assignments = eKmeans.getAssignments();
        for(int i = 0; i < assignments.length; ++i) {
            System.out.println(assignments[i]);
        }
    }

    @Test
    public void kmeans2() {
        KMeans Kmeans = new KMeans(points, k, 64);
        int[] assignments = new int[6];
        for(int i = 0; i < assignments.length; ++i) {
            assignments[i] = Kmeans.predict(points[i]);
        }
        for(int i = 0; i < assignments.length; ++i) {
            System.out.println(assignments[i]);
        }
    }

}

