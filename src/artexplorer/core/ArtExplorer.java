package artexplorer.core;

import applicationFramework.core.Application;
import applicationFramework.ui.GUI;
import artexplorer.ui.ArtRotator;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import swing.components.AButton;
import swing.components.AColor;
import swing.components.AComponent;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ArtExplorer extends Application {

    static {
        INSTANCE = new ArtExplorer();
    }

    private ArtExplorer() {
        super();
    }

    public static Application getINSTANCE() {
        return INSTANCE;
    }

    @Override
    protected void initGUI() {
        //Init Gui
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                gui = new GUI("Art Explorer").setDimensions(new Dimension(900, 700)).setGoodDefaults();
                gui.getFrame().setContentPane(new ApplicationPane());

                //gui.setFullScreen(true);
                gui.show();
                //gui.enableDoubleBuffering(true);
            }
        });
    }

    public static void main(String[] args) {
        getINSTANCE();
    }

    private class ApplicationPane extends AComponent {

        private ArtRotator artRotator;
        private AButton forwardButton, backwardButton, saveImageButton, switchArtTypeButton;

        public ApplicationPane() {
            super();
            setBackground(AColor.DarkGrey);

            init();
        }

        private void init() {
            artRotator = new ArtRotator();
            forwardButton = new AButton(">");
            backwardButton = new AButton("<");
            saveImageButton = new AButton("Save Image");
            switchArtTypeButton = new AButton("To Other");
            
            forwardButton.setPreferredSize(new Dimension(0, 50));
            
            forwardButton.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    artRotator.forwards();
                }
            });
            
            backwardButton.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    artRotator.backwards();
                }
            });

            initLayout();
        }

        private void initLayout() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gc = UIToolkit.getDefaultGridBagConstraints();

            gc.gridwidth = 4;
            this.add(artRotator, gc);
            
            gc.gridy = 1;
            gc.gridwidth = 1;
            gc.weighty = 0;
            
            this.add(backwardButton, gc);

            gc.gridx++;
            this.add(saveImageButton, gc);
            
            gc.gridx++;
            this.add(switchArtTypeButton, gc);
            
            gc.gridx++;
            this.add(forwardButton, gc);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
            g2d.setPaint(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
        
    }
}
