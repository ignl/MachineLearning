package org.intelligentjava.machinelearning.decisiontree.data;

import java.util.HashMap;
import java.util.Map;

import org.intelligentjava.machinelearning.decisiontree.label.Label;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * Simple {@link DataSample} implementation which uses {@link HashMap} to keep data in columns.
 * 
 * @author Ignas
 *
 */
public class SimpleDataSample implements DataSample {
    
    private Map<String, Object> values = Maps.newHashMap();
    
    private String labelColumn;
    
    private SimpleDataSample(String labelColumn, Map<String, Object> values) {
        super();
        this.labelColumn = labelColumn;
        this.values = values;
    }

    private SimpleDataSample(String labelColumnName, String[] header, Object... dataValues) {
        super();
        this.labelColumn = labelColumnName;
        for (int i = 0; i < header.length; i++) {
            this.values.put(header[i], dataValues[i]);
        }
    }

    @Override
    public Object getValue(String column) {
        return values.get(column);
    }
    
    @Override
    public Label getLabel() {
        return (Label)values.get(labelColumn);
    }

    public static SimpleDataSample newSimpleDataSample(String labelColumnName, Map<String, Object> values) {
        return new SimpleDataSample(labelColumnName, values);
    }

    public static SimpleDataSample newSimpleDataSample(String labelColumnName, String[] header, Object... values) {
        Preconditions.checkArgument(header.length == values.length);
        return new SimpleDataSample(labelColumnName, header, values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SimpleDataSample [values=" + values + "]";
    }
    
}
