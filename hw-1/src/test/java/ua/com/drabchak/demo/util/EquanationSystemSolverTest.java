package ua.com.drabchak.demo.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class EquanationSystemSolverTest {

    EquanationSystemSolver systemSolver;

    @BeforeEach
    void setUp() {
        systemSolver = new EquanationSystemSolver();
    }


    @Test
    void testFirstEquanationCorrectAnswer() {
        double expected = 4.;
        double actual = systemSolver.firstEquanation(0.0);

        assertThat(actual).isEqualTo(expected, withPrecision(0.001));
    }

    @Test
    void testFirstEquanationIncorrectAnswer() {
        double expected = 4.5;
        double actual = systemSolver.firstEquanation(0.0);

        assertThat(actual).isNotEqualTo(expected);
    }

    @Test
    void testSecondEquanationCorrectAnswer() {
        double expected = 3.649;
        double actual = systemSolver.secondEquanation(1.4);

        assertThat(actual).isEqualTo(expected, withPrecision(0.001));
    }

    @Test
    void testSecondEquanationIncorrectAnswer() {
        double expected = 1.7;
        double actual = systemSolver.secondEquanation(1.4);

        assertThat(actual).isNotEqualTo(expected);
    }

    @Test
    void testThirdEquanationCorrectAnswer() {
        double expected = 0.94;
        double actual = systemSolver.thirdEquanation(2.);

        assertThat(actual).isEqualTo(expected, withPrecision(0.001));
    }

    @Test
    void testThirdEquanationIncorrectAnswer() {
        double expected = 1.;
        double actual = systemSolver.thirdEquanation(2.);

        assertThat(actual).isNotEqualTo(expected);
    }
}
