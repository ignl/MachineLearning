package org.intelligentjava.machinelearning.decisiontree.impurity;


import java.util.Arrays;

import org.intelligentjava.machinelearning.decisiontree.data.DataSample;
import org.intelligentjava.machinelearning.decisiontree.data.SimpleDataSample;
import org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel;
import org.junit.Assert;
import org.junit.Test;

public class ImpurityCalculationMethodTest {

    @Test
    public void testGetEmpiricalProbability50_50() {
        DataSample dataSample1 = SimpleDataSample.newSimpleDataSample("a", new String[]{"a"}, BooleanLabel.TRUE_LABEL);
        DataSample dataSample2 = SimpleDataSample.newSimpleDataSample("a", new String[]{"a"}, BooleanLabel.FALSE_LABEL);
        double p = new GiniIndexImpurityCalculation().getEmpiricalProbability(Arrays.asList(dataSample1, dataSample2), BooleanLabel.TRUE_LABEL, BooleanLabel.FALSE_LABEL);
        Assert.assertEquals(0.5, p, 0.001);
    }
    
}
