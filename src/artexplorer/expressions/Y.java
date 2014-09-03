package artexplorer.expressions;

import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public class Y extends Expression {

    @Override
    public double evaluate(double x, double y) {
        return y;
    }

    @Override
    public Expression variation(int maxDepth, Random random) {
        return leafVariation(this, maxDepth, random);
    }
    
}
