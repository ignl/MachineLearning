package org.intelligentjava.machinelearning.decisiontree.feature;

import org.intelligentjava.machinelearning.decisiontree.DataSample;

/**
 * @author Ignas
 *
 * @param <T> Feature data type (string, number)
 */
public class SimpleFeature<T> implements Feature {
    
    private String column; // TODO multiple columns

    private T featureValue;
    
    /**
     * Constructor.
     * 
     * @param column
     * @param featureValue
     */
    public SimpleFeature(String column, T featureValue) {
        super();
        this.column = column;
        this.featureValue = featureValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean belongsTo(DataSample dataSample) { // TODO implement other literals not only equality
        return dataSample.getValue(column).equals(featureValue);
    }

    @Override
    public String getDefinition() {
        return String.format("%s = %s", column, featureValue);
    }

}
