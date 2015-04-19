package org.intelligentjava.machinelearning.decisiontree;

import java.util.Arrays;

import org.intelligentjava.machinelearning.decisiontree.data.SimpleDataSample;
import org.intelligentjava.machinelearning.decisiontree.feature.Feature;
import org.intelligentjava.machinelearning.decisiontree.feature.SimpleFeature;
import org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel;
import org.junit.Assert;
import org.junit.Test;

public class DecisionTreeTrainingTest {
    
    /**
     * Test if decision tree correctly learns simple AND function.
     * Should learn tree like this:
     *              x1 = true
     *              /       \
     *            yes       No
     *            /           \
     *        x2 = true      LABEL_FALSE
     *          /    \
     *        yes     No  
     *        /         \
     *    LABEL_TRUE    LABEL_FALSE
     */         
    @Test
    public void testTrainingAndFunction() {
        DecisionTree tree = new DecisionTree();
        String[] header = {"x1", "x2", "answer"};
        
        SimpleDataSample data1 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.TRUE, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data2 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.FALSE, BooleanLabel.FALSE_LABEL);
        SimpleDataSample data3 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.TRUE, BooleanLabel.FALSE_LABEL);
        SimpleDataSample data4 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.FALSE, BooleanLabel.FALSE_LABEL);
        
        Feature feature1 = SimpleFeature.newFeature("x1", Boolean.TRUE);
        Feature feature2 = SimpleFeature.newFeature("x1", Boolean.FALSE);
        Feature feature3 = SimpleFeature.newFeature("x2", Boolean.TRUE);
        Feature feature4 = SimpleFeature.newFeature("x2", Boolean.FALSE);
        
        tree.train(Arrays.asList(data1, data2, data3, data4), Arrays.asList(feature1, feature2, feature3, feature4));
        
        Assert.assertEquals("x1 = true", tree.getRoot().getName()); // root node x1 = true split
        Assert.assertEquals(null, tree.getRoot().getLabel()); // not leaf node
        
        Assert.assertEquals("x2 = true", tree.getRoot().getChildren().get(0).getName());
        Assert.assertEquals(null, tree.getRoot().getChildren().get(0).getLabel()); // not leaf node
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(0).getChildren().get(0).getName()); // leaf
        Assert.assertEquals(BooleanLabel.TRUE_LABEL, tree.getRoot().getChildren().get(0).getChildren().get(0).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(0).getChildren().get(1).getName()); // leaf
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.getRoot().getChildren().get(0).getChildren().get(1).getLabel());
        
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getName());
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.getRoot().getChildren().get(1).getLabel());
        
    }

}
