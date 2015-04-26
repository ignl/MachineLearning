package org.intelligentjava.machinelearning.decisiontree.feature;

import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;

import org.intelligentjava.machinelearning.decisiontree.data.DataSample;

import com.google.common.collect.Lists;

/**
 * Feature interface. Each data sample either have or does not have a feature and it can be split based on that.
 * 
 * @author Ignas
 *
 */
public interface Feature {
    
    /**
     * Calculates and checks if data contains feature.
     * 
     * @param dataSample Data sample.
     * @return true if data has this feature and false otherwise.
     */
    boolean belongsTo(DataSample dataSample);

    /**
     * Split data according to if it has this feature.
     * 
     * @param data Data to by split by this feature.
     * @return Sublists of split data samples.
     */
    default List<List<DataSample>> split(List<DataSample> data) {
        List<List<DataSample>> result = Lists.newArrayList();
        // TODO:  maybe use sublist streams instead of creating new list just track indexes
        // http://stackoverflow.com/questions/22917270/how-to-get-a-range-of-items-from-stream-using-java-8-lambda
        Map<Boolean, List<DataSample>> split = data.parallelStream().collect(partitioningBy(dataSample -> belongsTo(dataSample)));
        
        // TODO fix this is ugly
        if (split.get(true).size() > 0) {
            result.add(split.get(true));
        } else {
            result.add(Lists.newArrayList());
        }
        if (split.get(false).size() > 0) {
            result.add(split.get(false));
        } else {
            result.add(Lists.newArrayList());
        }
        return result;
    }

}
