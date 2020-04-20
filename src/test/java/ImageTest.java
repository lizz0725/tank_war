
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTest {


    @Test
    void name() {
        try {
            ClassLoader classLoader = ImageTest.class.getClassLoader();
            BufferedImage image2 = ImageIO.read(classLoader.getResourceAsStream("images/bulletD.gif"));
            Assertions.assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
