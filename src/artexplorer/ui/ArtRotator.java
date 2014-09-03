package artexplorer.ui;

import artexplorer.expressions.Expression;
import java.awt.Graphics;
import java.util.Random;
import swing.components.ASwopPane;

/**
 *
 * @author alexisvincent
 */
public class ArtRotator extends ASwopPane {

    private ArtDisplay[] artDisplays;
    private Random random;
    private int maxDepth;

    int lastIndex, currentIndex;

    public ArtRotator() {
        init();
        addArtDisplay(new ArtDisplay(
                Expression.random(getMaxDepth(), random),
                Expression.random(getMaxDepth(), random),
                Expression.random(getMaxDepth(), random), 
                getMaxDepth(), 
                getRandom()));
    }

    private void init() {
        
        setMaxDepth(5);
        
        artDisplays = new ArtDisplay[10];
        random = new Random();
        
        //So that on first run we will have a current index of 0
        currentIndex = -1;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public Random getRandom() {
        return random;
    }
    
    

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
    
    public void addArtDisplay(ArtDisplay display) {

        if (currentIndex == 9) {
            lastIndex = 0;
        } else {
            lastIndex = ++currentIndex;
        }
        
        artDisplays[lastIndex] = display;
        updateDisplay();
    }
    
    private void updateDisplay() {
        this.removeAll();
        
        for (ArtDisplay artDisplay : artDisplays) {
            if (artDisplay != null) {
                this.addSwopable(artDisplay);
            }
        }
        
        artDisplays[currentIndex].setVisible(true);
    }
    
    public void forwards() {
        
    }
    
    public void backwards() {
        
    }

}
