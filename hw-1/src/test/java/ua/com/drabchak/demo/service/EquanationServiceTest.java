package ua.com.drabchak.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.withPrecision;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EquanationServiceTest {

    EquanationService service;

    double left;

    double right;

    int size;

    double step;

    double[] x;

    double[] y;

    @BeforeEach
    void setUp() {
        service = new EquanationService();
        left = 0.0;
        right = 2.0;
        size = 1000;
        step = 0.002;
        x = service.formUpParametersArray(left, right, step);
        y = service.formUpFunctionAnswersArray(x);
    }

    @Test
    void testComputeStepsAmount() {
        int expected = size;
        int actual = service.computeStepsAmount(left, right, step);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    void testFormUpParametersArray() {
        double expectedFirstElement = left;
        double expectedLastElement = right;
        int expectedSize = size;

        double[] actual = x;

        assertThat(actual[0]).isEqualTo(expectedFirstElement);
        assertThat(actual[actual.length - 1]).isCloseTo(expectedLastElement, withPrecision(0.01));
        assertThat(actual).hasSize(expectedSize);
    }

    @Test
    void formUpFunctionAnswersArray() {
        double expectedFirstElement = 4.0;
        double expectedLastElement = 0.939;
        int expectedSize = size;

        double[] actual = y;

        assertThat(actual[0]).isEqualTo(expectedFirstElement);
        assertThat(actual[actual.length - 1]).isCloseTo(expectedLastElement, withPrecision(0.01));
        assertThat(actual).hasSize(expectedSize);
    }

    @Test
    void testFindMinIndex() {
        int expected = 999;
        int actual = service.findMinIndex(y);

        assertEquals(expected, actual);
    }

    @Test
    void testFindMaxIndex() {
        int expected = 700;
        int actual = service.findMaxIndex(y);

        assertEquals(expected, actual);
    }

    @Test
    void testFindSum() {
        double expected = 4228.3968;
        double actual = service.findSum(y);

        assertThat(actual).isCloseTo(expected, withPrecision(0.0001));
    }

    @Test
    void findAverage() {
        double expected = 4.228;
        double actual = service.findAverage(y);

        assertThat(actual).isCloseTo(expected, withPrecision(0.001));
    }
}