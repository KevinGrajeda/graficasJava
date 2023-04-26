package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.Calendar;
import java.util.Stack;

public class Ventana extends JFrame implements Runnable {

    private BufferedImage image;
    private BufferedImage fondo;
    // private Image buffer;
    private Thread hilo;

    public Ventana() {
        setTitle("ventana");
        setSize(500, 500);
        setLayout(null);

        // buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        // graPixel = (Graphics2D) buffer.createGraphics();

        fondo = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics g = fondo.getGraphics();
        g.setColor(new Color(250, 250, 250));
        g.fillRect(0, 0, 500, 500);

        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                repaint();
                hilo.sleep(20);
            } catch (InterruptedException ex) {

            }
        }
    }

    int cont = 0;

    // tarea figuras
    // d.color=Color.black;
    // d.lineaPuntoMedio(20, 120, 80, 180);
    // d.lineaBresenham(100, 150, 160, 150);
    // d.lineaBresenham(180, 180, 240, 120);
    // d.lineaBresenham(340, 150, 260, 150);

    // for (int i = 0; i < 4; i++) {
    // d.circuloPuntoMedio(60, 275, i * 10 + 2);
    // d.putPixel(60 + (3 + i * 8), 275 + (3 + i * 8), Color.black);
    // d.putPixel(60 - (3 + i * 8), 275 - (3 + i * 8), Color.black);
    // }
    // d.rectangulo(115, 250, 215, 300);
    // d.rectangulo(200, 265, 130, 285);
    // for (int i = 0; i < 4; i++) {
    // d.elipse(290, 275, i * 10 + 25, i * 10 + 2);
    // d.putPixel(290 + (27 + i * 9), 275 + (3 + i * 6), Color.black);
    // d.putPixel(290 - (27 + i * 9), 275 - (3 + i * 6), Color.black);
    // }
    // d.traslacion(cont*2,0);
    // d.escalacion(1,(float) (2+Math.sin(cont/10.0)));
    // d.rectangulo(30, 30, 50,50);
    // d.elipse(50,60,6,10);
    // d.floodFill(50,60,Color.blue);

    // d.traslacion(100,250);
    // d.escalacion(1,1);
    // d.rotacion((float) cont/30);

    // d.rectangulo(0, 0, 70,70);
    // d.elipse(30,30,10,15);
    // d.scanLineFill(35,65,Color.blue);

    // d.traslacion((int) (300+(100*Math.sin(cont/40.0))),250);
    // d.escalacion((float) (2+Math.sin(cont/10.0)),(float)
    // (2+Math.sin(cont/10.0)));
    // d.rotacion((float)cont/30);
    // d.rectangulo(-20, -20, 20,20);
    // d.linea(-20,-20,20,20);
    // d.linea(-20,20,20,-20);
    // d.floodFill(-5,0,Color.yellow);
    // d.floodFill(5,0,Color.red);

    public void update(Graphics g) {
        // g.drawImage(fondo,0,0,null);

        Image buffer = createImage(getWidth(), getHeight());

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujar d = new Dibujar(image);

        // Primitivas
        // d.rectangulo(150,120,175,170);


        // //curvas parametricas
        // d.curva1(50, 300, 60, 250, 8);
        // d.curva1(250, 300, 60, 250, 100);
        
        // d.curvaHumo(200,300,20,40);
        
        // d.curvaParametrica1(250, 150, 4, 6);
        
        // d.curvaInfinito(250, 350, 200);

        // d.curvaFlor(350, 350, 60, 60);

        // d.curvaSol(200, 200, 4, 4);


        // mallado
        // d.malla(100, 100, 300, 300, 10);


        // animaciones

        // traslacion
        d.traslacion(40+cont,40);
        d.triangulo(0,0,40,0,20,30);
        d.floodFill(10,10,Color.red);

        // rotacion centro
        // d.traslacion(300,250);
        // d.rotacion((float)cont/30);
        // d.rectangulo(-50, -50, 50,50);
        // d.floodFill(-5,0,Color.yellow);

        // rotacion punto
        // d.traslacion(250, 250);
        // d.rotacion((float)cont/20);
        // d.rectangulo(180,0,220,30);
        // d.floodFill(185,10,Color.yellow);

        // escalacion
        // d.traslacion(250,250);
        // d.escalacion((float) (2+Math.sin(cont/10.0)),(float)(2+Math.sin(cont/10.0)));
        // d.rectangulo(-20, -20, 20,20);



        
        buffer.getGraphics().drawImage(image, 0, 0, null);
        cont++;
        d.frameActual++;
        g.drawImage(buffer, 0, 0, this); // doble buffer
    }

    public void paint(Graphics g) {

        update(g);

    }

}
