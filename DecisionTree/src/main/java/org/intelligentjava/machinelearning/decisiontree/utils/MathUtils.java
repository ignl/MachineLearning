package org.intelligentjava.machinelearning.decisiontree.utils;

import net.jafama.FastMath;

public class MathUtils {

    public static double log2(double x) {
        return FastMath.log(x) / FastMath.log(2);
    }
}
