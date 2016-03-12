package vs1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener {

    JMenuBar menubar;
    JMenu file, edit;
    JMenuItem sellect;
    JMenuItem greyScale, greyScale2, all, test;
    JLabel label ;
    JPanel panel;
    BufferedImage image = null;
    BufferedImage imageChange = null;
    String sname;

    public Main() {
        super("Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setAlwaysOnTop(true);
        setResizable(true);
        setVisible(true);
//		setExtendedState(Frame.MAXIMIZED_BOTH);
        setSize(800, 600);

        menubar = new JMenuBar();
        setJMenuBar(menubar);

        file = new JMenu("File");
        menubar.add(file);
        sellect = new JMenuItem("Sellect");
        sellect.addActionListener(this);
        file.add(sellect);

        edit = new JMenu("Edit");
        menubar.add(edit);
        greyScale = new JMenuItem("Grey Scale");
        greyScale2 = new JMenuItem("Grey Scale 2");
        all = new JMenuItem("All");
        test = new JMenuItem("Test");
        greyScale.addActionListener(this);
        greyScale2.addActionListener(this);
        all.addActionListener(this);
        test.addActionListener(this);
        edit.add(greyScale);
        edit.add(greyScale2);
        edit.add(all);
        edit.add(test);

//		label.setBounds(50, 0, 1280, 720);
//		add(label);

        panel = new MyPanel();
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sellect){

            file = null;
            JFileChooser fc = new JFileChooser();
            File f = new File("D:/JAVA/Z_image_input");
            fc.setCurrentDirectory(f);
            fc.doLayout();
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                sname = file.getAbsolutePath();

                try {
                    image = ImageIO.read(file);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            label = new JLabel(" ", new ImageIcon(image), JLabel.CENTER);
            panel.add(label);
            panel.revalidate();

        } else if (e.getSource() == greyScale){
            GreyScale gr = new GreyScale(image);
            gr.edit();
            imageChange = gr.getImage();
            image = imageChange;
            panel.repaint();

        } else if (e.getSource() == greyScale2){
            GreyScale2 gr2 = new GreyScale2(imageChange);
            gr2.edit();
            imageChange = gr2.getImage();
            image = imageChange;
            panel.repaint();

        }else if (e.getSource() == all){


        }else if (e.getSource() == test){
            Test test = new Test(imageChange);
            test.cos();
            imageChange = test.getImage();
            image = imageChange;
            panel.repaint();
        }

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Main();
            }
        });
    }

}
