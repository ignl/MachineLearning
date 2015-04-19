package org.intelligentjava.machinelearning.decisiontree.impurity;

import static org.intelligentjava.machinelearning.decisiontree.utils.MathUtils.log2;

import java.util.List;
import java.util.stream.Collectors;

import org.intelligentjava.machinelearning.decisiontree.data.DataSample;
import org.intelligentjava.machinelearning.decisiontree.label.Label;

/**
 * Entropy calculator. -p log2 p - (1 - p)log2(1 - p) - this is the expected information, in bits, conveyed by somebody
 * telling you the class of a randomly drawn example; the purer the set of examples, the more predictable this message
 * becomes and the smaller the expected information.
 * 
 * @author Ignas
 *
 */
public class EntropyCalculationMethod implements ImpurityCalculationMethod {

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateImpurity(List<DataSample> splitData) {
        List<Label> labels = splitData.parallelStream().map(data -> data.getLabel()).distinct().collect(Collectors.toList());
        if (labels.size() > 1) {
            double p = getEmpiricalProbability(splitData, labels.get(0), labels.get(1)); // TODO fix to multiple labels
            return -1.0 * p * log2(p) - ((1.0 - p) * log2(1.0 - p));
        } else if (labels.size() == 1) {
            return 0.0; // if only one label data is pure
        } else {
            throw new IllegalStateException("This should never happen. Probably a bug.");
        }
    }

}
