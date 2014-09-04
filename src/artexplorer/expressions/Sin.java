package artexplorer.expressions;

import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public class Sin extends Expression {

    private Expression expression;
    
    public Sin(Expression expression) {
        this.expression = expression;
    }
    
    @Override
    public double evaluate(double x, double y) {
        return Math.sin(Math.PI * expression.evaluate(x, y));
    }

    @Override
    public Expression variation(int maxDepth, Random random) {
        return singleArgExpression(this, expression, maxDepth, random);
    }
}
