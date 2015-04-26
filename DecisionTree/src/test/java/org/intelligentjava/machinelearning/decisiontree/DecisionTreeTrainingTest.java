package org.intelligentjava.machinelearning.decisiontree;

import java.util.Arrays;

import org.intelligentjava.machinelearning.decisiontree.data.SimpleDataSample;
import org.intelligentjava.machinelearning.decisiontree.feature.Feature;
import org.intelligentjava.machinelearning.decisiontree.feature.P;
import org.intelligentjava.machinelearning.decisiontree.feature.PredicateFeature;
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
        
        Feature feature1 = PredicateFeature.newFeature("x1", Boolean.TRUE);
        Feature feature2 = PredicateFeature.newFeature("x1", Boolean.FALSE);
        Feature feature3 = PredicateFeature.newFeature("x2", Boolean.TRUE);
        Feature feature4 = PredicateFeature.newFeature("x2", Boolean.FALSE);
        
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
    
    
    /**
     * Test if decision tree correctly learns simple OR function.
     * Should learn tree like this:
     *              x1 = true
     *              /       \
     *            yes       No
     *            /           \
     *        LABEL_TRUE     x2 = true
     *                        /    \
     *                       yes     No  
     *                      /         \
     *                 LABEL_TRUE    LABEL_FALSE
     */ 
    @Test
    public void testTrainingORFunction() {
        DecisionTree tree = new DecisionTree();
        String[] header = {"x1", "x2", "answer"};
        
        SimpleDataSample data1 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.TRUE, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data2 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.FALSE, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data3 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.TRUE, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data4 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.FALSE, BooleanLabel.FALSE_LABEL);
        
        Feature feature1 = PredicateFeature.newFeature("x1", Boolean.TRUE);
        Feature feature2 = PredicateFeature.newFeature("x1", Boolean.FALSE);
        Feature feature3 = PredicateFeature.newFeature("x2", Boolean.TRUE);
        Feature feature4 = PredicateFeature.newFeature("x2", Boolean.FALSE);
        
        tree.train(Arrays.asList(data1, data2, data3, data4), Arrays.asList(feature1, feature2, feature3, feature4));
        
        Assert.assertEquals("x1 = true", tree.getRoot().getName()); // root node x1 = true split
        Assert.assertEquals(null, tree.getRoot().getLabel()); // not leaf node
        
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(0).getName());
        Assert.assertEquals(BooleanLabel.TRUE_LABEL, tree.getRoot().getChildren().get(0).getLabel());
        
        Assert.assertEquals("x2 = true", tree.getRoot().getChildren().get(1).getName());
        Assert.assertEquals(null, tree.getRoot().getChildren().get(1).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getChildren().get(0).getName()); // leaf
        Assert.assertEquals(BooleanLabel.TRUE_LABEL, tree.getRoot().getChildren().get(1).getChildren().get(0).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getChildren().get(1).getName()); // leaf
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.getRoot().getChildren().get(1).getChildren().get(1).getLabel());
        
    }
    
    
    /**
     * Test if decision tree correctly learns simple XOR function.
     * Should learn tree like this:
     *              x1 = true
     *              /       \
     *            yes       No
     *            /           \
     *        x2 = true        x2 = true
     *         /    \              /    \
     *       yes     No          yes     No  
     *      /         \          /         \
     * LABEL_FALSE LABEL_TRUE  LABEL_TRUE LABEL_FALSE
     */ 
    @Test
    public void testTrainingXORFunction() {
        DecisionTree tree = new DecisionTree();
        String[] header = {"x1", "x2", "answer"};
        
        SimpleDataSample data1 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.TRUE, BooleanLabel.FALSE_LABEL);
        SimpleDataSample data2 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.FALSE, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data3 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.TRUE, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data4 = SimpleDataSample.newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.FALSE, BooleanLabel.FALSE_LABEL);
        
        Feature feature1 = PredicateFeature.newFeature("x1", Boolean.TRUE);
        Feature feature2 = PredicateFeature.newFeature("x1", Boolean.FALSE);
        Feature feature3 = PredicateFeature.newFeature("x2", Boolean.TRUE);
        Feature feature4 = PredicateFeature.newFeature("x2", Boolean.FALSE);
        
        tree.train(Arrays.asList(data1, data2, data3, data4), Arrays.asList(feature1, feature2, feature3, feature4));
        
        Assert.assertEquals("x1 = true", tree.getRoot().getName()); // root node x1 = true split
        Assert.assertEquals(null, tree.getRoot().getLabel()); // not leaf node
        
        Assert.assertEquals("x2 = true", tree.getRoot().getChildren().get(0).getName());
        Assert.assertEquals(null, tree.getRoot().getChildren().get(0).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getChildren().get(0).getName()); // leaf
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.getRoot().getChildren().get(0).getChildren().get(0).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getChildren().get(0).getName()); // leaf
        Assert.assertEquals(BooleanLabel.TRUE_LABEL, tree.getRoot().getChildren().get(0).getChildren().get(1).getLabel());
        
        Assert.assertEquals("x2 = true", tree.getRoot().getChildren().get(1).getName());
        Assert.assertEquals(null, tree.getRoot().getChildren().get(1).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getChildren().get(0).getName()); // leaf
        Assert.assertEquals(BooleanLabel.TRUE_LABEL, tree.getRoot().getChildren().get(1).getChildren().get(0).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getChildren().get(1).getName()); // leaf
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.getRoot().getChildren().get(1).getChildren().get(1).getLabel());
        
    }
    
    @Test
    public void testLearnSimpleMoreLessFeature() {
        DecisionTree tree = new DecisionTree();
        String[] header = {"x1", "answer"};
        
        SimpleDataSample data1 = SimpleDataSample.newSimpleDataSample("answer", header, 1, BooleanLabel.FALSE_LABEL);
        SimpleDataSample data2 = SimpleDataSample.newSimpleDataSample("answer", header, 2, BooleanLabel.FALSE_LABEL);
        SimpleDataSample data3 = SimpleDataSample.newSimpleDataSample("answer", header, 3, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data4 = SimpleDataSample.newSimpleDataSample("answer", header, 4, BooleanLabel.TRUE_LABEL);
        
        Feature feature1 = PredicateFeature.newFeature("x1", P.moreThan(0), "> 0"); // TODO think of a way to avoid duplicating string representation
        Feature feature2 = PredicateFeature.newFeature("x1", P.moreThan(1), "> 1");
        Feature feature3 = PredicateFeature.newFeature("x1", P.moreThan(2), "> 2");
        
        tree.train(Arrays.asList(data1, data2, data3, data4), Arrays.asList(feature1, feature2, feature3));
        
        Assert.assertEquals("x1 > 2", tree.getRoot().getName()); // root node x1 = true split
        Assert.assertEquals(null, tree.getRoot().getLabel()); // not leaf node
        
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(0).getName()); // leaf
        Assert.assertEquals(BooleanLabel.TRUE_LABEL, tree.getRoot().getChildren().get(0).getLabel());
        Assert.assertEquals("Leaf", tree.getRoot().getChildren().get(1).getName()); // leaf
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.getRoot().getChildren().get(1).getLabel());
        
        
    }

}
