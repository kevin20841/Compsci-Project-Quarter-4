package JavaFXGUI;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import backend.GifDecoder;

public class AnimatedGif extends Animation {

    public AnimatedGif( String filename, double durationMs) {
        GifDecoder d = new GifDecoder();
        d.read( filename);

        Image[] sequence = new Image[ d.getFrameCount()];
        for( int i=0; i < d.getFrameCount(); i++) {

            WritableImage wimg = null;
            BufferedImage bimg = d.getFrame(i);
            sequence[i] = SwingFXUtils.toFXImage( bimg, wimg);

        }

        super.init( sequence, durationMs);
    }

}