package org.intelligentjava.machinelearning.decisiontree.label;

/**
 * Simplest possible label. Simply labels data as true or false.
 * 
 * @author Ignas
 *
 */
public class BooleanLabel extends Label {
    
    /** Label. */
    private boolean label;
    
    /**
     * Constructor.
     */
    public BooleanLabel(boolean label) {
        super();
        this.label = label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.valueOf(label);
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

}
