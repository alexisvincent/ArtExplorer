package artexplorer.expressions;

import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public class Avg extends Expression {
    
    private final Expression expression1, expression2;

    public Avg(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public double evaluate(double x, double y) {
        return (expression1.evaluate(x, y)+expression2.evaluate(x, y))/2;
    }

    @Override
    public Expression variation(int maxDepth, Random random) {
        return doubleArgExpression(this, expression1, expression2, maxDepth, random);
    }
    
}
