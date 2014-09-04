package artexplorer.ui;

import artexplorer.core.ArtExplorer;
import artexplorer.expressions.Expression;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import swing.components.AComponent;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class Artwork extends AComponent {

    private final Expression expression1, expression2, expression3;
    private double[][] matrix1, matrix2, matrix3;

    private BufferedImage rgb, hsv;

    private int resolution;

    public Artwork(Expression expression1, Expression expression2, Expression expression3) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (ArtExplorer.isSaveImageMode()) {
                    try {
                        BufferedImage image;

                        if (ArtExplorer.isRgbMode()) {
                            image = rgb;
                        } else {
                            image = hsv;
                        }

                        File outputfile = new File(Long.toHexString(Double.doubleToLongBits(Math.random())) + ".png");
                        ImageIO.write(image, "png", outputfile);
                    } catch (IOException error) {
                        System.out.println("Could not save image");
                    } finally {
                        ArtExplorer.setSaveImageMode(false);
                    }
                } else {
                    ArtRotator.getInstance().addArtDisplay(new ArtDisplay(
                            expression1,
                            expression2,
                            expression3,
                            ArtRotator.getInstance().getMaxDepth(),
                            ArtRotator.getInstance().getRandom()));
                }

            }
        });

        init();
        evaluateExpressions();
        generateImages();

    }

    private void evaluateExpressions() {
        for (int y = 0; y < resolution; y++) {
            for (int x = 0; x < resolution; x++) {
                matrix1[y][x] = (1 + expression1.evaluate((double) x / resolution, (double) y / resolution)) / 2;
                matrix2[y][x] = (1 + expression2.evaluate((double) x / resolution, (double) y / resolution)) / 2;
                matrix3[y][x] = (1 + expression3.evaluate((double) x / resolution, (double) y / resolution)) / 2;
            }
        }

    }

    private void generateImages() {
        for (int y = 0; y < resolution; y++) {
            for (int x = 0; x < resolution; x++) {
                rgb.setRGB(x, y, (int) ((matrix1[y][x] * 255) * 65536 + matrix2[y][x] * 255 * 256 + matrix3[y][x] * 255));
                hsv.setRGB(x, y, Color.HSBtoRGB((int) (matrix1[y][x] * 360), (int) (matrix1[y][x] * 100), (int) (matrix1[y][x] * 100)));
            }
        }
    }

    private void init() {

        resolution = 300;

        matrix1 = new double[resolution][resolution];
        matrix2 = new double[resolution][resolution];
        matrix3 = new double[resolution][resolution];

        rgb = new BufferedImage(resolution, resolution, BufferedImage.TYPE_BYTE_INDEXED);
        hsv = new BufferedImage(resolution, resolution, BufferedImage.TYPE_BYTE_INDEXED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        UIToolkit.setANTIALIAS(false);
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

        if (ArtExplorer.isRgbMode()) {
            g2d.drawImage(rgb, null, 0, 0);
        } else {
            g2d.drawImage(hsv, null, 0, 0);
        }

        if (isHover() && ArtExplorer.isSaveImageMode()) {
            g2d.setPaint(new Color(0, 0, 0, 60));
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

}
