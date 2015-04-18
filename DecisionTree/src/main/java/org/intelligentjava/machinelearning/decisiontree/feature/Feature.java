package org.intelligentjava.machinelearning.decisiontree.feature;

import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;

import org.intelligentjava.machinelearning.decisiontree.DataSample;

import com.google.common.collect.Lists;

/**
 * Feature interface. Each data sample either have or does not have a feature and it can be split based on that.
 * 
 * @author Ignas
 *
 */
public interface Feature {
    
    /**
     * Returns string representation of feature. For example:
     * x > 2,
     * color = 'green',
     * hasWings = true
     * 
     * @return Feature definition.
     */
    String getDefinition();
    
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
        Map<Boolean, List<DataSample>> spilt = data.parallelStream().collect(partitioningBy(dataSample -> belongsTo(dataSample)));
        result.add(spilt.get(true));
        result.add(spilt.get(false));
        return result;
    }
    
}
