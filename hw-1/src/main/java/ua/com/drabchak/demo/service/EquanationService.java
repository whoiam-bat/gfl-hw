package ua.com.drabchak.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.drabchak.demo.util.EquanationSystemSolver;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class EquanationService {
    private final EquanationSystemSolver systemSolver;

    private static final double EPS = 1e-9;

    private static final Logger LOGGER = LoggerFactory.getLogger(EquanationService.class);

    public EquanationService() {
        this.systemSolver = new EquanationSystemSolver();
    }


    public int computeStepsAmount(double left, double right, double step) {
        return Integer.parseInt(String.valueOf(DoubleStream.iterate(left, i -> i <= right, i -> i + step).count()));
    }

    public double[] formUpParametersArray(double left, double right, double step) {
        return DoubleStream.iterate(left, i -> i <= right, i -> i + step).toArray();
    }

    public double[] formUpFunctionAnswersArray(double[] x) {
        return DoubleStream.of(x).map(
                it -> {
                    if (it < 1.4 + EPS) {
                        LOGGER.info("f({}) ==> {}", it, systemSolver.firstEquanation(it));
                        return systemSolver.firstEquanation(it);
                    } else if (it == 1.4 + EPS) {
                        LOGGER.info("f({}) ==> {}", it, systemSolver.secondEquanation(it));
                        return systemSolver.secondEquanation(it);
                    } else {
                        LOGGER.info("f({}) ==> {}", it, systemSolver.thirdEquanation(it));
                        return systemSolver.thirdEquanation(it);
                    }
                }
        ).toArray();
    }

    public int findMinIndex(double[] y) {
        double min = DoubleStream.of(y).min().getAsDouble();

        return IntStream.range(0, y.length)
                .filter(i -> y[i] == min)
                .findFirst()
                .orElse(-1);
    }

    public int findMaxIndex(double[] y) {
        double max = DoubleStream.of(y).max().getAsDouble();

        return IntStream.range(0, y.length)
                .filter(i -> y[i] == max)
                .findFirst()
                .orElse(-1);
    }

    public double findSum(double[] y) {
        return DoubleStream.of(y).sum();
    }

    public double findAverage(double[] y) {
        return DoubleStream.of(y).average().getAsDouble();
    }

    private void solve(double left, double right, double step) {
        double[] x = formUpParametersArray(left, right, step);
        formUpFunctionAnswersArray(x);
    }

    private void showMin(double[] y) {
        int index = findMinIndex(y);

        LOGGER.info("MIN[{}] = {}", index, y[index]);
    }

    private void showMax(double[] y) {
        int index = findMaxIndex(y);

        LOGGER.info("MAX[{}] = {}", index, y[index]);
    }

    private void showAvg(double[] y) {
        LOGGER.info("AVERAGE = {}", findAverage(y));
    }

    private void showSum(double[] y) {
        LOGGER.info("SUM = {}", findSum(y));
    }

    public void run(double left, double right, double step) {
        double[] x = formUpParametersArray(0.0, 2.0, 0.002);
        double[] y = formUpFunctionAnswersArray(x);

        solve(left, right, step);

        showSum(y);
        showMax(y);
        showMin(y);
        showAvg(y);
    }

}
