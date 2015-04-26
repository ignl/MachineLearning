package org.intelligentjava.machinelearning.decisiontree.label;

/**
 * Simplest possible label. Simply labels data as true or false.
 * 
 * @author Ignas
 *
 */
public class BooleanLabel extends Label {
    
    public static final Label TRUE_LABEL = BooleanLabel.newLabel(true);

    public static final Label FALSE_LABEL = BooleanLabel.newLabel(false);
    
    /** Label. */
    private boolean label;
    
    /**
     * Constructor.
     */
    private BooleanLabel(boolean label) {
        super();
        this.label = label;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrintValue() {
        return label ? "1" : "0";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.valueOf(label);
    }
    
    /**
     * Static factory method.
     */
    public static Label newLabel(Boolean label) {
        return new BooleanLabel(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (label ? 1231 : 1237);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BooleanLabel other = (BooleanLabel) obj;
        if (label != other.label)
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BooleanLabel [label=" + label + "]";
    }

}
