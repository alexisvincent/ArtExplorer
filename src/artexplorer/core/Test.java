package artexplorer.core;

import artexplorer.expressions.Expression;
import java.util.Random;

/**
 *
 * @author alexisvincent
 */
public class Test {
    
    public static void main(String args[]) {
        Expression expression = Expression.random(5, new Random());
        System.out.println(expression.evaluate(0.1, 0.3));
        System.out.println(expression.evaluate(0.7, 0.6));
        System.out.println(expression.evaluate(0.2, 0.8));
    }
    
}
