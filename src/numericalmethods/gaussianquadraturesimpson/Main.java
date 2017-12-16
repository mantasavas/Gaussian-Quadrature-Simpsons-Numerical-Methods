package numericalmethods.gaussianquadraturesimpson;

// Approximating integral

public class Main {

    public static void main(String[] args) {

        /* Running Gaussian Quadrature Method */

        //Initialization
        GaussianSimpsonData gaussianData = new GaussianSimpsonData();
        gaussianData.setA(4);
        gaussianData.setB(8);
        gaussianData.setEpslonPrec(0.00000000001);
        gaussianData.setN(2);

	    GaussianQuadrature gaussianQuadrature = new GaussianQuadrature(gaussianData);
        gaussianQuadrature.runGaussianAlgorithm();

    }
}
