package artexplorer.ui;

import artexplorer.expressions.Expression;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;
import swing.components.AComponent;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ArtDisplay extends AComponent {

    private Artwork[][] artworks;

    public ArtDisplay(Expression expression1, Expression expression2, Expression expression3, int maxDepth, Random random) {
        super("Art Display");

        init(maxDepth);
        generateArtworks(expression1, expression2, expression3, maxDepth, random);
        updateLayout();
    }

    private void updateLayout() {
        GridBagConstraints gc = UIToolkit.getDefaultGridBagConstraints();
        for (int y = 0; y < 3; y++) {
            gc.gridy = y;
            for (int x = 0; x < 3; x++) {
                gc.gridx = x;
                this.add(artworks[y][x], gc);
            }
        }
    }
    
    private void generateArtworks(Expression expression1, Expression expression2, Expression expression3, int maxDepth, Random random) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (x==1 && y==1) {
                    artworks[y][x] = new Artwork(expression1, expression2, expression3);
                } else {
                    artworks[y][x] = new Artwork(
                            expression1.variation(maxDepth, random), 
                            expression2.variation(maxDepth, random), 
                            expression3.variation(maxDepth, random));
                }
                
            }
        }
    }

    private void init(int maxDepth) {
        this.artworks = new Artwork[3][3];
        this.setLayout(new GridBagLayout());
    }
}
