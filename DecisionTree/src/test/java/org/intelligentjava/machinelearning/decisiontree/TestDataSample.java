package org.intelligentjava.machinelearning.decisiontree;

import org.intelligentjava.machinelearning.decisiontree.label.Label;

public class TestDataSample implements DataSample {
    
    private Object value;
    
    private Label label;
    
    public TestDataSample(Object value, Label label) {
        super();
        this.value = value;
        this.label = label;
    }

    @Override
    public Object getValue(String column) {
        return value;
    }

    @Override
    public Label getLabel() {
        return label;
    }

}
