package artexplorer.ui;

import artexplorer.core.ArtExplorer;
import artexplorer.expressions.Expression;
import concurrency.ProcessingQueue;
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

    private int lastIndex, currentIndex;

    private static ArtRotator INSTANCE;

    public static ArtRotator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArtRotator();
        }
        return INSTANCE;
    }

    private ArtRotator() {
        init();
        ArtExplorer.getINSTANCE().getProcessingQueue().addJob(new ProcessingQueue.Job() {

            @Override
            public boolean doJob() {
                ArtDisplay artDisplay = new ArtDisplay(
                        Expression.random(getMaxDepth(), random),
                        Expression.random(getMaxDepth(), random),
                        Expression.random(getMaxDepth(), random),
                        getMaxDepth(),
                        getRandom());
                addArtDisplay(artDisplay);
                return true;
            }

            @Override
            public boolean mustBeRemoved() {
                return true;
            }
        });
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
            currentIndex = 0;
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
        repaint();
    }

    public void forwards() {
        if (currentIndex == 9) {
            if (artDisplays[0] != null) {
                currentIndex = 0;
            }
        } else if (artDisplays[currentIndex+1] != null) {
            currentIndex++;
        }
        
        updateDisplay();
    }

    public void backwards() {
        if (currentIndex == 0) {
            if (artDisplays[9] != null) {
                currentIndex = 9;
            }
        } else if (artDisplays[currentIndex-1] != null) {
            currentIndex--;
        }
        
        updateDisplay();
    }

}
