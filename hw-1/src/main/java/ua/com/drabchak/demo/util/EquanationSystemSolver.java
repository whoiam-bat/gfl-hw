package ua.com.drabchak.demo.util;

public class EquanationSystemSolver {
    private static final double A = 2.7;

    private static final double B = -0.3;

    private static final double C = 4.;

    public double firstEquanation(double x) {
        return A * Math.pow(x, 2) + B * x + C;
    }

    public double secondEquanation(double x) {
        return (A / x) + Math.sqrt((x * x) + 1);
    }

    public double thirdEquanation(double x) {
        return (A + B * x) / Math.sqrt((x * x) + 1);
    }
}
