package numericalmethods.gaussianquadraturesimpson;


public class GaussianSimpsonData {
    // Interval beginning of Integral
    private double a;
    // Interval ending of Integral
    private double b;
    // N-th Gaussian row
    private double N;
    // Precision epsilon
    private double epslonPrec;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }


    public double getEpslonPrec() {
        return epslonPrec;
    }

    public void setEpslonPrec(double epslonPrec) {
        this.epslonPrec = epslonPrec;
    }

}
