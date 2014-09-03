package artexplorer.expressions;

import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public class K extends Expression {

    private double k;
    
    public K(double k) {
        this.k = k;
    }
    

    @Override
    public double evaluate(double x, double y) {
        return k;
    }

    @Override
    public Expression variation(int maxDepth, Random random) {
        return leafVariation(this, maxDepth, random);
    }
    
}
