package vs1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreyScale2 {

    BufferedImage image = null;
    public GreyScale2(BufferedImage image){
        this.image = image;
    }

    public void edit(){
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y=0; y<height; y++){
            for (int x = 0; x<width; x++){

                Color c = new Color(image.getRGB(x, y));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int a = c.getAlpha();

                int gr = (r+g+b)/3;

                if (gr<180)
                    gr=0;
                else if (gr<255)
                    gr=255;


                Color newColor = new Color(gr,gr,gr,a);
                image.setRGB(x, y, newColor.getRGB());

//				System.out.println(gr+" ");
            }
        }

        try{
            File f = new File("D:/JAVA/output2.jpg");
            ImageIO.write(image, "jpg", f);

            System.out.println("Writing complete.");
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public BufferedImage getImage(){

        return image;
    }

}
