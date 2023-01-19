package ua.com.drabchak.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.drabchak.demo.util.EquanationSystemSolver;

import java.math.BigDecimal;

public class EquanationService {
    private final EquanationSystemSolver systemSolver;

    private static final Logger LOGGER = LoggerFactory.getLogger(EquanationService.class);

    public EquanationService() {
        this.systemSolver = new EquanationSystemSolver();
    }


    public void solve(BigDecimal left, BigDecimal right, BigDecimal step) {

        int sequenceNumber = 0;

        for (BigDecimal x = left; x.compareTo(right) <= 0; x = x.add(step), sequenceNumber++) {

            if (x.compareTo(BigDecimal.valueOf(1.4)) < 0)
                LOGGER.info("Less {}: f({}) ==> {}", sequenceNumber, x, systemSolver.firstEquanation(x.doubleValue()));

            else if (x.compareTo(BigDecimal.valueOf(1.4)) == 0)
                LOGGER.info("Equal {}: f({}) ==> {}", sequenceNumber, x, systemSolver.secondEquanation(x.doubleValue()));

            else
                LOGGER.info("Greater {}: f({}) ==> {}", sequenceNumber, x, systemSolver.thirdEquanation(x.doubleValue()));
        }
    }
}
