package artexplorer.expressions;

import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public abstract class Expression {

    public abstract double evaluate(double x, double y);

    public abstract Expression variation(int maxDepth, Random random);

    public static Expression random(int maxDepth, Random random) {

        int rnd;

        if (maxDepth == 0) {
            rnd = random.nextInt(6);

            if (rnd <= 2) {
                return new X();
            } else if (rnd <= 4) {
                return new Y();
            } else {
                return new K(random.nextDouble() * 2 - 1);
            }
        } else {
            rnd = random.nextInt(18);

            if (rnd <= 3) {
                return new Sin(Expression.random(maxDepth - 1, random));
            } else if (rnd <= 6) {
                return new Cos(Expression.random(maxDepth - 1, random));
            } else if (rnd <= 9) {
                return new Avg(Expression.random(maxDepth - 1, random), Expression.random(maxDepth - 1, random));
            } else if (rnd <= 12) {
                return new Prod(Expression.random(maxDepth - 1, random), Expression.random(maxDepth - 1, random));
            } else if (rnd <= 14) {
                return new X();
            } else if (rnd <= 16) {
                return new Y();
            } else {
                return new K(random.nextDouble() * 2 - 1);
            }
        }
    }

    protected Expression leafVariation(Expression expression, int maxDepth, Random random) {
        if (random.nextBoolean()) {
            return Expression.random(maxDepth, random);
        } else {
            return expression;
        }
    }

    protected Expression singleArgExpression(Expression expression, Expression subExpression, int maxDepth, Random random) {
        int rnd = random.nextInt(6);

        if (rnd == 1) {
            return Expression.random(maxDepth, random);
        } else if (rnd <= 3) {
            return new Sin(subExpression.variation(maxDepth - 1, random));
        } else if (rnd == 4) {
            if (expression instanceof Sin) {
                return new Cos(subExpression);
            } else {
                return new Sin(subExpression);
            }
        } else {
            return expression;
        }
    }

    protected Expression doubleArgExpression(Expression expression, Expression leftSubExpression, Expression rightSubExpression, int maxDepth, Random random) {
        int rnd = random.nextInt(7);

        if (rnd == 1) {
            return Expression.random(maxDepth, random);
        } else if (rnd <= 3) {
            if (expression instanceof Prod) {
                return new Prod(leftSubExpression.variation(maxDepth - 1, random), rightSubExpression);
            } else {
                return new Avg(leftSubExpression.variation(maxDepth - 1, random), rightSubExpression);
            }
        } else if (rnd <= 5) {
            if (expression instanceof Prod) {
                return new Prod(leftSubExpression, rightSubExpression.variation(maxDepth - 1, random));
            } else {
                return new Avg(leftSubExpression, rightSubExpression.variation(maxDepth - 1, random));
            }
        } else {
            return expression;
        }
    }
}
