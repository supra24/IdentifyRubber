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
    int width;
    int height;

    public Test(BufferedImage image){
        this.image = image;
        imageEnd = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    public void cos(){


        for (int y=0; y<height; y++){
            for (int x = 0; x<width; x++){
                    Arrangement(x, y);
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

    public void Arrangement (int x, int y){

        if (getColor(x,y) != 0){

            int XInception = x;
            int YInception = y;
            p = new ArrayList<>();

            for (int c=0;; c++){ // pętla zewnętrzna, sprawdzanie niżej
                int right=0;
                int left=0;

                for (right=0;; right++){ // pętla do sprawdzania po prawej stonie
                    if ((XInception+right < width) && (YInception+c < height)){
                        if (getColor(XInception+right, YInception+c) !=0){
                            p.add(new IntegerPoint(XInception+right,YInception+c));
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                for (left=0;; left++){ // pętla do sprawdzania po lewej stonie
                    if ((XInception-left >0) && (YInception+c < height)) {
                        if (getColor(XInception - left, YInception+c) != 0) {
                            p.add(new IntegerPoint(XInception + -left, YInception+c));
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                boolean edit= true;

                if (YInception+1 <height){
                    if (getColor(XInception, YInception+1) != 0){
                        YInception = YInception+1;
                        edit =false;
                    }
                }

                if (edit == true) {
                    for (int Xaxis = 0; Xaxis < right; Xaxis++) { // pętla do sprawdzania czy niżej jest jeszcze kawałek gumy po prawej stronie
                        if (YInception + 1 < height) {
                            if (getColor(XInception + Xaxis, YInception + 1) != 0) {
                                XInception = XInception+Xaxis;
                                YInception = YInception+1;
                                edit = false;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }

                if (edit == true) {
                    for (int Xaxis=0; Xaxis < left  ; Xaxis++ ) { // pętla do sprawdzania czy niżej jest jeszcze kawałęk gumy po lewej stronie
                        if (YInception+1 < height){
                            if (getColor(XInception-Xaxis, YInception+1) !=0 ){
                                XInception = XInception+Xaxis;
                                YInception = YInception+1;
                                edit = false;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }

                if (edit == true){
                    break;
                }

            }

            // edycja zdjecia po analizie
            if (p.size() <=7){
                for (int i=0; i<p.size(); i++){
                    image.setRGB(p.get(i).getX(), p.get(i).getY(), 0);
                    imageEnd.setRGB(p.get(i).getX(), p.get(i).getY(), 0);
                }
            } else {
                for (int i=0; i<p.size(); i++){
                    image.setRGB(p.get(i).getX(), p.get(i).getY(), 255);
                    imageEnd.setRGB(p.get(i).getX(), p.get(i).getY(), 255);
                }
            }

        }
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
