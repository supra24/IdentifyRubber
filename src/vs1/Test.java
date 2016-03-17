package vs1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Test {

    BufferedImage image = null;
    BufferedImage imageEnd = null;
    ArrayList<IntegerPoint> p ;
    Color colorBlack = new Color(0, 0, 0);


    public Test(BufferedImage image){
        this.image = image;
    }

    public void cos(){
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y=0; y<height; y++){
            for (int x = 0; x<width; x++){
                if (getColor(x, y)>=200){
                    Arrangement(x, y);

                }
            }
        }

        try{
            File f = new File("F:/JAVA/workIntelli/IdentifyRubber/Z_image_output/output3.jpg");
            ImageIO.write(image, "jpg", f);
            System.out.println("Writing complete.");
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public int Arrangement (int x, int y){

        int pixel=1;
        int i=0, j=0;
        int pomoc=0, pomoc2=0;
        int czyMaDalejSprawdzac=1;


        for (i=0; czyMaDalejSprawdzac<2 ;i++){
            pomoc = 0;
            p = new ArrayList<>();
            for (j=0; pomoc<2 ;j++){
                if ((getColor(x+j, y+i)>200) && (pomoc==0)){
                    p.add(new IntegerPoint(x+j, y+i));
                    pixel++;
                } else if ((getColor(x+j, y+i)<100) && (pomoc==0)) {
                    pomoc=1;
                    j=1;
                }

                if ((getColor(x-j, y+i)>200) && (pomoc==1)){
                    pixel++;
                    p.add(new IntegerPoint(x-j, y+i));
                }else if ( (getColor(x-j, y+i)<100) && (pomoc==1) ){
                    pomoc =2;
                }
            }

            pomoc2=0;
            for (int k=0; k<p.size();k++){
                if (getColor(p.get(k).getX(), p.get(k).getY()+1)>250){
                    pomoc2++;
                    x = p.get(k).getX();
                }
            }
            if (pomoc2==0){
                czyMaDalejSprawdzac=2;
            }
        }

        if (p.size()<7){
            for(int k=0; k<p.size(); k++){
                image.setRGB(p.get(k).getX(), p.get(k).getY(), colorBlack.getRGB());
            }
        }

        return pixel;
    }

    public int getColor(int x, int y){
        int gr=0;
        if ((x>(image.getWidth()-1)) || (y>(image.getHeight()-1)) || (x<0) || (y<0))
            gr = colorBlack.getRGB();
        else {
            Color c = new Color(image.getRGB(x, y));
            int r = c.getRed();
            int g = c.getGreen();
            int b = c.getBlue();
            int a = c.getAlpha();
            gr = (r+g+b)/3;
        }
        return gr;
    }

    public BufferedImage getImage(){

        return image;
    }

}
