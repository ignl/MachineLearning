package org.intelligentjava.machinelearning.decisiontree;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import org.intelligentjava.machinelearning.decisiontree.data.DataSample;
import org.intelligentjava.machinelearning.decisiontree.feature.Feature;
import org.intelligentjava.machinelearning.decisiontree.impurity.GiniIndexImpurityCalculation;
import org.intelligentjava.machinelearning.decisiontree.impurity.ImpurityCalculationMethod;
import org.intelligentjava.machinelearning.decisiontree.label.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decision tree implementation.
 * 
 * @author Ignas
 *
 */
public class DecisionTree {

    private static final String LEAF_NODE_NAME = "Leaf";

    /** Logger. */
    private Logger log = LoggerFactory.getLogger(DecisionTree.class);

    /** Root node. */
    private Node root;

    /** Impurity calculation method. */
    private ImpurityCalculationMethod impurityCalculationMethod = new GiniIndexImpurityCalculation();

    /**
     * When data is considered homogeneous and node becomes leaf and is labeled. If it is equal 1.0 then absolutely
     * all data must be of the same label that node would be considered a leaf.
     */
    private double homogenityPercentage = 0.95;

    /**
     * Get root.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Trains tree on training data for provided features.
     * 
     * @param trainingData
     *            List of training data samples.
     * @param features
     *            List of possible features.
     */
    public void train(List<DataSample> trainingData, List<Feature> features) {
        root = growTree(trainingData, features);
    }

    /**
     * Grow tree during training by splitting data recusively on best feature.
     * 
     * @param trainingData
     *            List of training data samples.
     * @param features
     *            List of possible features.
     * 
     * @return Node after split. For a first invocation it returns tree root node.
     */
    protected Node growTree(List<DataSample> trainingData, List<Feature> features) {

        Label currentNodeLabel = null;
        if ((currentNodeLabel = getLabel(trainingData)) != null) { // if dataset already homogeneous enough (has label assigned) make this node a leaf
            return Node.newLeafNode(LEAF_NODE_NAME, currentNodeLabel);
        }

        Feature bestSplit = findBestSplitFeature(trainingData, features); // get best set of literals
        log.debug("Best split found: {}", bestSplit.getDefinition());
        List<List<DataSample>> splitData = bestSplit.split(trainingData);

        Node node = Node.newNode(bestSplit.getDefinition());
        for (List<DataSample> subsetTrainingData : splitData) { // add children to current node according to split
            if (subsetTrainingData != null && !subsetTrainingData.isEmpty()) {
                node.addChild(growTree(subsetTrainingData, features));
            } else {
                // if subset data is empty add a leaf with label calculated from initial data
                node.addChild(Node.newLeafNode(LEAF_NODE_NAME, getLabel(trainingData))); 
            }
        }

        return node;
    }

    // public Label classify(DataSample dataSample) {
    // Node node = root;
    // while (!node.isLeaf()) {
    // for (Node child : node.getChildren()) {
    // if (hasFeature(child, dataSample)) {
    // node = child;
    // break;
    // }
    // }
    // }
    // return node.getLabel();
    // }
    //
    // private boolean hasFeature(Node node, DataSample dataSample) {
    // dataSample.contain
    // }

    /**
     * Finds best feature to split on which is the one whose split results in lowest impurity measure.
     */
    protected Feature findBestSplitFeature(List<DataSample> data, List<Feature> features) {
        double currentImpurity = 1;
        Feature bestSplitFeature = null; // rename split to feature

        for (Feature feature : features) {
            List<List<DataSample>> splitData = feature.split(data);
            // totalSplitImpurity = sum(singleLeafImpurities) / nbOfLeafs
            // in other words splitImpurity is average of leaf impurities
            double calculatedSplitImpurity = splitData.parallelStream().mapToDouble(list -> impurityCalculationMethod.calculateImpurity(list)).average().getAsDouble();
            if (calculatedSplitImpurity < currentImpurity) {
                currentImpurity = calculatedSplitImpurity;
                bestSplitFeature = feature;
            }
        }

        return bestSplitFeature;
    }

    /**
     * Returns Label if data is homogeneous.
     */
    protected Label getLabel(List<DataSample> data) {
        Map<Label, Long> labelCount = data.parallelStream().collect(groupingBy(DataSample::getLabel, counting()));
        long totalCount = data.size();
        for (Label label : labelCount.keySet()) {
            long nbOfLabels = labelCount.get(label);
            if (((double) nbOfLabels / (double) totalCount) >= homogenityPercentage) {
                return label;
            }
        }
        return null;
    }
}
