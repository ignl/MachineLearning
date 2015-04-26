package org.intelligentjava.machinelearning.decisiontree.label;

/**
 * Label interface. Done as abstract class to force overriding of equals and hashCode methods.
 * 
 * @author Ignas
 *
 */
public abstract class Label {
    
    /**
     * Label value used to print to predictions output.
     * 
     * @return Print label
     */
    public abstract String getPrintValue();
    
    /**
     * @return Label name
     */
    public abstract String getName();
    
    /**
     * Force overriding equals.
     */
    public abstract boolean equals(final Object o);

    /**
     * Force overriding hashCode.
     */
    public abstract int hashCode();

}
