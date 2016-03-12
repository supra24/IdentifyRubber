package vs1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreyScale {

    Color colorBlack = new Color(0, 0, 0);
    BufferedImage image = null;
    public GreyScale(BufferedImage image){
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

                Color newColor = new Color(gr,gr,gr,a);
                image.setRGB(x, y, newColor.getRGB());

//				System.out.println(gr+" ");
            }
        }

//		for (int y=0; y<height; y++){
//			image.setRGB(0, y, colorBlack.getRGB());
//			image.setRGB(width-1, y, colorBlack.getRGB());
//			image.setRGB(1, y, colorBlack.getRGB());
//			image.setRGB(width-2, y, colorBlack.getRGB());
//			image.setRGB(2, y, colorBlack.getRGB());
//			image.setRGB(width-3, y, colorBlack.getRGB());
//		}
//		for (int x=0; x<width; x++){
//			image.setRGB(x, 0, colorBlack.getRGB());
//			image.setRGB(x, height-1, colorBlack.getRGB());
//			image.setRGB(x, 1, colorBlack.getRGB());
//			image.setRGB(x, height-2, colorBlack.getRGB());
//			image.setRGB(x, 2, colorBlack.getRGB());
//			image.setRGB(x, height-3, colorBlack.getRGB());
//		}

        try{
            File f = new File("D:/JAVA/output1.jpg");
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
