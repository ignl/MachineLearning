package org.intelligentjava.machinelearning.decisiontree;

import static org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel.FALSE_LABEL;
import static org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel.TRUE_LABEL;

import java.util.List;

import org.intelligentjava.machinelearning.decisiontree.data.DataSample;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class DecisionTreeGetMajorityLabelTest {
    
    // TODO fix handling of empty lists
//    @Test
//    public void testGetLabelOnEmptyList() {
//        DecisionTree tree = new DecisionTree();
//        List<DataSample> data = Lists.newArrayList();
//        Assert.assertNull(tree.getMajorityLabel(data));
//    }
    
    @Test
    public void testGetMajorityLabel() {
        DecisionTree tree = new DecisionTree();
        List<DataSample> data = Lists.newArrayList();
        data.add(new TestDataSample(null, TRUE_LABEL));
        data.add(new TestDataSample(null, FALSE_LABEL));
        data.add(new TestDataSample(null, TRUE_LABEL));
        data.add(new TestDataSample(null, FALSE_LABEL));
        data.add(new TestDataSample(null, FALSE_LABEL));
        Assert.assertEquals("false", tree.getMajorityLabel(data).getName());
    }

    @Test
    public void testGetMajorityLabelWhenEqualCounts() {
        DecisionTree tree = new DecisionTree();
        List<DataSample> data = Lists.newArrayList();
        data.add(new TestDataSample(null, TRUE_LABEL));
        data.add(new TestDataSample(null, FALSE_LABEL));
        data.add(new TestDataSample(null, TRUE_LABEL));
        data.add(new TestDataSample(null, FALSE_LABEL));
        Assert.assertEquals("false", tree.getMajorityLabel(data).getName());
    }
}
