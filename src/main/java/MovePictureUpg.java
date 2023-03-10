import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MovePictureUpg extends JFrame{

    static int width = 800, height = 600;
    static int size = 50;
    static int x = 0, y = 0;
    static JLabel label;

    public MovePictureUpg(BufferedImage image) {
        super("Move picture upgrade");
        createGUI(image);
    }

    public void createGUI(BufferedImage image){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.black);
        JPanel panel = new JPanel();
        panel.setFocusable(true);

        label = new JLabel(new ImageIcon(image));
        label.setBounds(x, y, size, size);
        add(label);

        panel.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyText(e.getKeyCode()) == "Left") {
                    x -= e.isShiftDown() ? 100 : 50;
                    if (x >= 0) {
                        label.setBounds(x, y, size, size);
                        add(label);
                    } else {
                        x = width;
                    }
                }
                if (e.getKeyText(e.getKeyCode()) == "Right") {
                    x += e.isShiftDown() ? 100 : 50;;
                    if (x <= (width - size)) {
                        label.setBounds(x, y, size, size);
                        add(label);
                    } else {
                        x = 0;
                    }
                }
                if (e.getKeyText(e.getKeyCode()) == "Up") {
                    y -= e.isShiftDown() ? 100 : 50;;
                    if (y >= 0) {
                        label.setBounds(x, y, size, size);
                        add(label);
                    } else {
                        y = height;
                    }
                }

                if (e.getKeyText(e.getKeyCode()) == "Down") {
                    y += e.isShiftDown() ? 100 : 50;;
                    if (y <= (height - size)) {
                        label.setBounds(x, y, size, size);
                        add(label);
                    } else {
                        y = 0;
                    }
                }
            }
        });
        setPreferredSize(new Dimension(width, height));
        getContentPane().add(panel);
    }

    public static void main(String[] args) throws IOException{
        BufferedImage image = ImageIO.read(new File("./image/hedgehog.jpg")).getSubimage(0, 0, size, size);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                MovePictureUpg frame = new MovePictureUpg(image);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}