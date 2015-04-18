package org.intelligentjava.machinelearning.decisiontree.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//        DecisionTree treeClassificator = Classificator.getDecisionTreeClassificator().useAlgorithm(ID3).usePruning(REDUCED_ERROR_PRUNING)
//                .build();
//        treeClassificator.train(exampleDataSet);
//        
//        treeClassificator.classify(data)
        
            List<Test> list = new ArrayList<>();

            list.add(new Test("1", "a"));
            list.add(new Test("1", "a"));
            list.add(new Test("2", "b"));
            list.add(new Test("3", "b"));
            list.add(new Test("3", "a"));

            Map<String, Long> counted = list.stream().collect(Collectors.groupingBy(Test::getSecond, Collectors.counting()));

            System.out.println(counted);
    }

    public static class Test {
        private String first;

        private String second;

        public Test(String first, String second) {
            super();
            this.first = first;
            this.second = second;
        }

        public String getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public void setSecond(String second) {
            this.second = second;
        }

    }
}
