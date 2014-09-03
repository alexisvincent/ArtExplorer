package artexplorer.ui;

import artexplorer.expressions.Expression;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import swing.components.AComponent;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class Artwork extends AComponent{
    
    private Expression expression1, expression2, expression3;
    private double[][] matrix1, matrix2, matrix3;
    
    private BufferedImage rgb, hsv;
    
    private int resolution;
    
    public Artwork(Expression expression1, Expression expression2, Expression expression3) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        
        init();
        evaluateExpressions();
        generateImages();
    }
    
    private void evaluateExpressions() {
        for (int y = 0; y < resolution; y++) {
            for (int x = 0; x < resolution; x++) {
                matrix1[y][x] = expression1.evaluate(x/resolution, y/resolution);
                matrix2[y][x] = expression2.evaluate(x/resolution, y/resolution);
                matrix3[y][x] = expression3.evaluate(x/resolution, y/resolution);
                
                //System.out.println(matrix3[y][x]);
            }
        }
    }
    
    private void generateImages() {
        Color color;
        for (int y = 0; y < resolution; y++) {
            for (int x = 0; x < resolution; x++) {
                color = new Color((int)matrix1[y][x], (int)matrix2[y][x], (int)matrix3[y][x]);
                rgb.setRGB(x, y, color.getRGB());
            }
        }
        System.out.println("lol");
    }
    
    private void init() {
        
        resolution = 500;
        
        matrix1 = new double[resolution][resolution];
        matrix2 = new double[resolution][resolution];
        matrix3 = new double[resolution][resolution];
        
        rgb = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
        hsv = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
        
        g2d.drawImage(rgb, null, 0, 0);
    }
    
    

}
