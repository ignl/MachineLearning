package org.intelligentjava.machinelearning.decisiontree;

import java.util.List;

import org.intelligentjava.machinelearning.decisiontree.feature.Feature;
import org.intelligentjava.machinelearning.decisiontree.feature.SimpleFeature;
import org.intelligentjava.machinelearning.decisiontree.label.BooleanLabel;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class DecisionTreeFindBestSplitTest {
    
    @Test
    public void testSimpleSplit() {
        DecisionTree tree = new DecisionTree();
        List<DataSample> data = Lists.newArrayList();
        data.add(new TestDataSample(2, new BooleanLabel(true)));
        data.add(new TestDataSample(3, new BooleanLabel(false)));
        data.add(new TestDataSample(2, new BooleanLabel(true)));
        
        List<Feature> features = Lists.newArrayList();
        features.add(new SimpleFeature<Integer>("number", 2));
        features.add(new SimpleFeature<Integer>("number", 3));
        
        Feature bestSplit = tree.findBestSplitFeature(data, features);
        Assert.assertEquals("number = 2", bestSplit.getDefinition());
    }

//    @Test
//    public void testOddNumbersSplit() {
//        DecisionTree tree = new DecisionTree();
//        List<DataSample> data = Lists.newArrayList();
//        data.add(new TestDataSample(2, new BooleanLabel(true)));
//        data.add(new TestDataSample(3, new BooleanLabel(false)));
//        data.add(new TestDataSample(4, new BooleanLabel(true)));
//        data.add(new TestDataSample(5, new BooleanLabel(false)));
//        data.add(new TestDataSample(6, new BooleanLabel(true)));
//        
//        List<Feature> features = Lists.newArrayList();
//        features.add(new SimpleFeature<Integer>("number", 2));
//        features.add(new SimpleFeature<Integer>("number", 3));
//        features.add(new SimpleFeature<Integer>("number", 4));
//        features.add(new SimpleFeature<Integer>("number", 5));
//        
//        Feature bestSplit = tree.findBestSplitFeature(data, features);
//        Assert.assertEquals("number = 2", bestSplit.getDefinition());
//    }

}
