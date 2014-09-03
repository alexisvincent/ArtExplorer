package artexplorer.expressions;

import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public class X extends Expression {

    @Override
    public double evaluate(double x, double y) {
        return x;
    }

    @Override
    public Expression variation(int maxDepth, Random random) {
        return leafVariation(this, maxDepth, random);
    }
    
}
