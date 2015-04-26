package org.intelligentjava.machinelearning.decisiontree;

import static org.intelligentjava.machinelearning.decisiontree.data.SimpleDataSample.newClassificationDataSample;
import static org.intelligentjava.machinelearning.decisiontree.data.SimpleDataSample.newSimpleDataSample;
import static org.intelligentjava.machinelearning.decisiontree.feature.PredicateFeature.newFeature;

import java.util.Arrays;

import org.intelligentjava.machinelearning.decisiontree.data.SimpleDataSample;
import org.intelligentjava.machinelearning.decisiontree.feature.Feature;
import org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test classify function which finds path in decision tree to leaf node.
 * 
 * @author Ignas
 *
 */
public class DecisionTreeTestClassify {
    
    @Test
    public void testClassify() {
        
        // train AND function on decision tree
        DecisionTree tree = new DecisionTree();
        String[] header = {"x1", "x2", "answer"};
        
        SimpleDataSample data1 = newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.TRUE, BooleanLabel.TRUE_LABEL);
        SimpleDataSample data2 = newSimpleDataSample("answer", header, Boolean.TRUE, Boolean.FALSE, BooleanLabel.FALSE_LABEL);
        SimpleDataSample data3 = newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.TRUE, BooleanLabel.FALSE_LABEL);
        SimpleDataSample data4 = newSimpleDataSample("answer", header, Boolean.FALSE, Boolean.FALSE, BooleanLabel.FALSE_LABEL);
        
        Feature feature1 = newFeature("x1", Boolean.TRUE);
        Feature feature2 = newFeature("x1", Boolean.FALSE);
        Feature feature3 = newFeature("x2", Boolean.TRUE);
        Feature feature4 = newFeature("x2", Boolean.FALSE);
        
        tree.train(Arrays.asList(data1, data2, data3, data4), Arrays.asList(feature1, feature2, feature3, feature4));
        
        // now check classify
        String[] classificationHeader = {"x1", "x2"};
        Assert.assertEquals(BooleanLabel.TRUE_LABEL, tree.classify(newClassificationDataSample(classificationHeader, Boolean.TRUE, Boolean.TRUE)));
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.classify(newClassificationDataSample(classificationHeader, Boolean.TRUE, Boolean.FALSE)));
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.classify(newClassificationDataSample(classificationHeader, Boolean.FALSE, Boolean.TRUE)));
        Assert.assertEquals(BooleanLabel.FALSE_LABEL, tree.classify(newClassificationDataSample(classificationHeader, Boolean.FALSE, Boolean.FALSE)));
    }

}
