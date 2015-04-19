package org.intelligentjava.machinelearning.decisiontree.impurity;

import java.util.List;
import java.util.stream.Collectors;

import org.intelligentjava.machinelearning.decisiontree.data.DataSample;
import org.intelligentjava.machinelearning.decisiontree.label.Label;

/**
 * Gini index impurity calculation. Formula 2p(1 - p) - this is the expected error if we label examples in the leaf
 * randomly: positive with probability p and negative with probability 1 - p. The probability of a false positive is
 * then p(1 - p) and the probability of a false negative (1 - p)p.
 * 
 * @author Ignas
 *
 */
public class GiniIndexImpurityCalculation implements ImpurityCalculationMethod {

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateImpurity(List<DataSample> splitData) {
        List<Label> labels = splitData.parallelStream().map(data -> data.getLabel()).distinct().collect(Collectors.toList());
        if (labels.size() > 1) {
            double p = getEmpiricalProbability(splitData, labels.get(0), labels.get(1)); // TODO fix to multiple labels
            return 2.0 * p * (1 - p);
        } else if (labels.size() == 1) {
            return 0.0; // if only one label data is pure
        } else {
            throw new IllegalStateException("This should never happen. Probably a bug.");
        }
    }

}
