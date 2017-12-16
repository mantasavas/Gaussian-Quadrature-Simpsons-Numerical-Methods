package numericalmethods.gaussianquadraturesimpson;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GaussianQuadrature {

    // Known constants for integral in interval [-1, -1]

    /*
    public static final double c1 = 0.5556;
    public static final double c2 = 0.8889;
    public static final double c3 = 0.5556;

    public static final double x1 = -0.7746;
    public static final double x2 = 0;
    public static final double x3 = 0.7746;
    */

    public static final double c1 = 1;
    public static final double c2 = 1;

    public static final double x1 = -0.577350269;
    public static final double x2 = 0.577350269;

    // Data about gaussian parameters like: precision, interval [a,b] etc.
    private GaussianSimpsonData data;

    // Array list because it's more effective in performance when vector. Vector is synchronized that means thread safe. On the other hand array list doesn't have that feature.
    private List<Double> integralResults;

    // Intervals number, sub intervals
    private List<Double> mIntervals;

    // Interval length
    private List<Double> hValues;

    // Runge values of approximation
    private List<Double> rungeValues;

    private double coefSubs;
    private double coefAddit;

    public GaussianQuadrature(GaussianSimpsonData data){
        this.data = data;
        integralResults = new ArrayList<>();
        mIntervals = new ArrayList<>();
        hValues = new ArrayList<>();
        rungeValues = new ArrayList<>();
    }


    // Main algorithm entry point
    public void runGaussianAlgorithm()
    {
        double h;
        double newA;
        double currentS;
        double beforeS = 0;
        double approximation;
        double p = data.getN() * 2;

        for(int x = 0; x < 100; x++)
        {
            h = (data.getB() - data.getA()) / Math.pow(2, x);
            newA = data.getA();
            currentS = 0;

            for(int y = 0; y < Math.pow(2, x); y++)
            {

                calculateIntegralIntervalCoeficientes(newA, newA + h);
                currentS += calculateIntegralWithWeights();
                newA += h;
            }
            System.out.println("Iteration: " + x + " Result: " + currentS);

            mIntervals.add(Math.pow(2, x));
            integralResults.add(currentS);
            hValues.add(h);

            // Calculating Runges value
            if(x != 0) {
                approximation = Math.abs(currentS - beforeS) / (Math.pow(2, p) - 1);
                rungeValues.add(approximation);
                System.out.println("Approximation is: " + approximation);
                if (approximation <= data.getEpslonPrec()) {
                    System.out.println("Desired precision has been reached. Stopping iterations");
                    break;
                }

            }
            beforeS = currentS;
        }

        printResults();

    }

    // Converts any integral interval from [a to b] to [-1 to 1], in order to be able to use 2 point specified Gaussian Quadratic weights
    public void calculateIntegralIntervalCoeficientes(double a, double b)
    {
        this.coefAddit = (a + b) / 2;
        this.coefSubs = (b - a) / 2;
    }

    // Calculates integral of function from [-1 to 1] interval
    public double calculateIntegralWithWeights()
    {
        double funcX1 = functionFirstExponent(x1 * coefSubs + coefAddit);
        double funcX2 = functionFirstExponent(x2 * coefSubs + coefAddit);
        //double funcX3 = functionFirstExponent(x3 * coefSubs + coefAddit);

        return coefSubs * (c1 * funcX1 + c2 * funcX2/* + c3 * funcX3*/);
    }

    // Printing integral calculation results
    public void printResults()
    {
        System.out.println("\n------------------------------");
        System.out.println("User data");
        System.out.println("---------");
        System.out.println("Interval: [ " + data.getA() + " , " + data.getB() + " ]");
        System.out.println("Precision: " + data.getEpslonPrec());
        System.out.println("Gaussian Quadrature N-th: " + data.getN());
        System.out.println("------------------------------");
        System.out.println("\n\n                                                 -------------------------");
        System.out.println("                                                 Gaussian Quadratic Method");

        System.out.println("============================================================================================================================");
        System.out.format("%-15s %-25s %-28s %-25s %-25s", "Iteration", "Interval Number (m)", "Interval Length (h)", "Runge Value", "Result (Approximation)");
        System.out.println("\n============================================================================================================================");

        for(int x = 0; x < integralResults.size(); x++)
        {
            if(x == 0)
                System.out.format("\n%-15s %-25.0f %-28.15f %-25s %-25.20f", x, mIntervals.get(x), hValues.get(x), "-", integralResults.get(x));
            else
                System.out.format("\n%-15s %-25.0f %-28.15f %-25.15f %-25.20f", x, mIntervals.get(x), hValues.get(x), rungeValues.get(x - 1), integralResults.get(x));
        }

        System.out.println("\n\nYour result: " + integralResults.get(integralResults.size() - 1));
    }





    /* Functions For Testing */

    // Function of integral 5xe^-2x (dx) [0.1, 1.3]
    /*
    public double functionFirstExponent(double x)
    {
        // https://www.symbolab.com/solver/definite-integral-calculator/%5Cint_%7B0.1%7D%5E%7B1.3%7D%205xe%5E%7B-2x%7Ddx
        // 0.89387
        return 5 * x * Math.pow(Math.E, -2 * x);
    }
    */

    // My Function of integral 1 / (SQRT(x) - 1) [4, 8]

    public double functionFirstExponent(double x)
    {
        // https://www.symbolab.com/solver/definite-integral-calculator/%5Cint_%7B4%7D%5E%7B8%7D%20%5Cfrac%7B1%7D%7B%5Csqrt%7Bx%7D-1%7Ddx
        // 2.86377
        return 1 / (Math.sqrt(x) - 1);
    }

}

