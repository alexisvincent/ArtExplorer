package artexplorer.expressions;

import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public class Cos extends Expression {

    private final Expression expression;

    public Cos(Expression expression) {
        this.expression = expression;
    }
    
    @Override
    public double evaluate(double x, double y) {
        return Math.cos(Math.PI * expression.evaluate(x, y));
    }

    @Override
    public Expression variation(int maxDepth, Random random) {
        return singleArgExpression(this, expression, maxDepth, random);
    }
    
}
