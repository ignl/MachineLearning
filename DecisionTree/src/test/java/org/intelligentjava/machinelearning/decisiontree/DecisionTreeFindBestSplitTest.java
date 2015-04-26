package org.intelligentjava.machinelearning.decisiontree;

import static org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel.FALSE_LABEL;
import static org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel.TRUE_LABEL;

import java.util.List;

import org.intelligentjava.machinelearning.decisiontree.data.DataSample;
import org.intelligentjava.machinelearning.decisiontree.data.SimpleDataSample;
import org.intelligentjava.machinelearning.decisiontree.feature.Feature;
import org.intelligentjava.machinelearning.decisiontree.feature.PredicateFeature;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class DecisionTreeFindBestSplitTest {
    
    @Test
    public void testBooleanSplit() {
        DecisionTree tree = new DecisionTree();
        String labelColumnName = "answer";
        
        String[] headers = {labelColumnName, "x1", "x2"};
        List<DataSample> dataSet = Lists.newArrayList();
        dataSet.add(SimpleDataSample.newSimpleDataSample(labelColumnName, headers, TRUE_LABEL, true, true));
        dataSet.add(SimpleDataSample.newSimpleDataSample(labelColumnName, headers, FALSE_LABEL, true, false));
        dataSet.add(SimpleDataSample.newSimpleDataSample(labelColumnName, headers, FALSE_LABEL, false, true));
        dataSet.add(SimpleDataSample.newSimpleDataSample(labelColumnName, headers, FALSE_LABEL, false, false));
        
        List<Feature> features = Lists.newArrayList();
        features.add(PredicateFeature.newFeature("x1", true));
        features.add(PredicateFeature.newFeature("x2", true));
        features.add(PredicateFeature.newFeature("x1", false));
        features.add(PredicateFeature.newFeature("x2", false));
        
        // test finding split
        Feature bestSplit = tree.findBestSplitFeature(dataSet, features);
        Assert.assertEquals("x1 = true", bestSplit.toString());
        
        List<List<DataSample>> split = bestSplit.split(dataSet);
        
        // test splitting data
        Assert.assertEquals(TRUE_LABEL, split.get(0).get(0).getValue(labelColumnName).get());
        Assert.assertEquals(FALSE_LABEL, split.get(0).get(1).getValue(labelColumnName).get());
        Assert.assertEquals(FALSE_LABEL, split.get(1).get(0).getValue(labelColumnName).get());
        Assert.assertEquals(FALSE_LABEL, split.get(1).get(1).getValue(labelColumnName).get());

        // next best split
        Feature newBestSplit = tree.findBestSplitFeature(split.get(0), features);
        Assert.assertEquals("x2 = true", newBestSplit.toString());

        List<List<DataSample>> newSplit = newBestSplit.split(split.get(0));
        Assert.assertEquals(TRUE_LABEL, newSplit.get(0).get(0).getValue(labelColumnName).get());
        Assert.assertEquals(FALSE_LABEL, newSplit.get(1).get(0).getValue(labelColumnName).get());
    }

}
