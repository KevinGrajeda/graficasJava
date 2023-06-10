package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ventana extends JFrame implements Runnable {

    // private Image buffer;
    private Thread hilo;
    private Animador animador;

    public Ventana() {
        setTitle("Si manejas no tomes");
        setSize(500, 450);
        setLayout(null);

        // buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        // graPixel = (Graphics2D) buffer.createGraphics();

//        Graphics g = fondo.getGraphics();
//        g.setColor(new Color(250, 250, 250));
//        g.fillRect(0, 0, getWidth(), getHeight());

        animador = new Animador();


        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        animador.setInicioAplicacion(System.currentTimeMillis());

        while (true) {
            try {
                repaint();
                hilo.sleep(20);
            } catch (InterruptedException ex) {

            }
        }
    }


    int cont = 0;

    public void update(Graphics g) {
        Image buffer = createImage(getWidth(), getHeight());


        // CUBOS PUNTOS FUGA
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;

        Dibujar d = new Dibujar();
        animador.setDibujar(d);

        d.resetImage(this);
        d.color = new Color(221, 237, 239);
        d.rectanguloRelleno(0, 0, getWidth(), getHeight());
        d.drawToBuffer(buffer, this);

        animador.tiempoActual += 0;
        if (animador.tiempoActual > 0 && animador.tiempoActual <= 50000) {
            animador.rotacionY3D(1.85, 1, 0, 40000);
            animador.escalacion3D(0.09, 0.24, 0, 30000);
            //animador.escalacion3D(0.34,1,40000,64000);

            animador.rotacionY3D(2.85, -1, 40000, 45000);

            d.traslacion(250, 250);
            d.setVectorProyeccion(new Coordenada3D(0, 0, -200000));
            //d.setVectorRotacion(-0.2,(float)x/60,0);
            //d.setVectorEscalacion((double)x/100,(double)x/100,(double)x/100);

            d.resetImage(this);
            d.color = new Color(60, 185, 20);
            d.plano(0, 100, 0, 8000, 8000);
            d.drawToBuffer(buffer, this);

            for (int i = -1; i < 2; i++) {

                for (int j = -1; j < 2; j++) {
                    Random random = new Random(i * 12837 + 1130 * j);
                    float hue = random.nextFloat();
                    float saturation = 0.5f;
                    float brightness = 0.7f;
                    Color colorTecho = Color.getHSBColor(hue, saturation, brightness);

                    float dx = random.nextFloat();
                    float dz = random.nextFloat();

                    d.setVectorTraslacion(-i * 500 + dx * 200, 0, j * 400 + dz * 350);
                    Coordenada3D point1 = new Coordenada3D(-100, -100, 0);
                    Coordenada3D point2 = new Coordenada3D(100, -100, 0);
                    Coordenada3D point3 = new Coordenada3D(100, 0, 100);
                    Coordenada3D point4 = new Coordenada3D(-100, 0, 100);
                    Coordenada3D point5 = new Coordenada3D(100, 0, -100);
                    Coordenada3D point6 = new Coordenada3D(-100, 0, -100);


                    // techo

                    d.color = colorTecho;
                    d.resetImage(this);
                    d.plano(point6, point5, point2, point1);
                    d.plano(point1, point2, point5, point6);
                    d.drawToBuffer(buffer, this);

                    // casa
                    d.resetImage(this);
                    d.color = new Color(225, 151, 151);
                    d.cubo(0, 35, 0, 140, 130, 140);
                    d.drawToBuffer(buffer, this);

                    // techo
                    d.color = colorTecho;
                    d.resetImage(this);
                    d.plano(point1, point2, point3, point4);
                    d.plano(point4, point3, point2, point1);
                    d.drawToBuffer(buffer, this);
                }
            }

            if (animador.tiempoActual > 45000) {
                d.resetImage(this);
                animador.nuevaAnimacion();
                d.traslacion(208, 309);
                animador.traslacion(100, 100, 45000, 50000);
                d.color = new Color(185, 20, 20);
                d.elipse(0, 0, 5, 10);
                d.scanLineFill(0, 0);
                d.color = new Color(255, 168, 115);
                d.elipse(0, -15, 5, 5);
                d.scanLineFill(0, -15);
                d.drawToBuffer(buffer, this);
            }
        }
        if (animador.tiempoActual > 50000 && animador.tiempoActual <= 53000) {

            d.resetImage(this);
            d.color = new Color(10, 10, 10);
            d.rectanguloRelleno(0, 0, getWidth(), getHeight());
            d.drawToBuffer(buffer, this);
        }
        if (animador.tiempoActual > 53000 && animador.tiempoActual <= 85000) {

            d.resetImage(this);
            animador.nuevaAnimacion();
            animador.rotX3D = -0.6;
            d.traslacion(250, 250);

            d.setVectorProyeccion(new Coordenada3D(0, 0, -500));
            d.setVectorTraslacion(0, 0, 0);
            d.color = new Color(231, 102, 56);
            animador.rotacionY3D(1.85, 5, 53000, 80000);

            d.plano(0, 0, 0, 500, 500);
            d.color = new Color(30, 250, 30);
            d.plano(0, 0, 0, 200, 300);
            d.color = new Color(255, 255, 255);
            d.plano(0, 0, 0, 200, 10);
            d.plano(0, 0, 140, 180, 10);
            d.plano(0, 0, -140, 180, 10);
            d.drawToBuffer(buffer, this);

            d.resetImage(this);
            d.plano(90, 0, 0, 10, 290);
            d.plano(-90, 0, 0, 10, 290);

            d.drawToBuffer(buffer, this);

            d.resetImage(this);
            Coordenada3D point1 = new Coordenada3D(-90, 0, 0);
            Coordenada3D point2 = new Coordenada3D(90, 0, 0);
            Coordenada3D point3 = new Coordenada3D(90, -50, 0);
            Coordenada3D point4 = new Coordenada3D(-90, -50, 0);

            d.resetImage(this);
            d.plano(point1, point2, point3, point4);
            d.plano(point4, point3, point2, point1);
            d.drawToBuffer(buffer, this);

            d.resetImage(this);
            d.color = new Color(185, 20, 20);
            d.setVectorTraslacion(Math.cos((double) cont / 9 - 0.6) * 80, -25, -140);
            d.cubo(0, 0, 0, 10, 50, 10);

            d.color = new Color(20, 59, 185);
            d.setVectorTraslacion(Math.cos((double) cont / 9 + 0.6) * 80, -25, 140);
            if (animador.tiempoActual < 80000) {
                d.cubo(0, 0, 0, 15, 50, 15);
            }else{
                d.cubo(0, 0, 0, 50, 15, 15);
            }
            d.drawToBuffer(buffer, this);

            d.resetImage(this);
            d.setVectorTraslacion(Math.cos((double) cont / 9 - 0.6) * 80, -25, -140);
            d.color = new Color(255, 168, 115);
            d.cubo(0, -30, 0, 20, 20, 20);
            d.setVectorTraslacion(Math.cos((double) cont / 9 + 0.6) * 80, -25, 140);
            d.color = new Color(255, 168, 115);
            if (animador.tiempoActual < 80000) {
                d.cubo(0, -30, 0, 20, 20, 20);
            }else{
                d.cubo(-30, -10, 0, 20, 20, 20);
            }
            d.drawToBuffer(buffer, this);

            d.resetImage(this);
            d.setVectorTraslacion(Math.cos((double) cont / 9) * 80, -50 - Math.abs(Math.cos((double) cont / 5) * -50), Math.sin((double) cont / 5) * 120);
            d.color = new Color(250, 246, 9);
            if (animador.tiempoActual < 80000)
                d.cubo(0, 0, 0, 10, 10, 10);
            d.drawToBuffer(buffer, this);

        }

        if (animador.tiempoActual > 85000 && animador.tiempoActual <= 100000) {
            animador.nuevaAnimacion();
            d.traslacion(250, 250);
            d.setVectorRotacion(0,0.2,0);
            d.setVectorProyeccion(new Coordenada3D(0, 0, -50000));
            d.setVectorEscalacion(1.5,1.5,1.5);
            d.setVectorTraslacion(0, -Math.abs(Math.sin((double)animador.tiempoActual/300)*30), 0);

            d.resetImage(this);
            d.color = new Color(185, 20, 20);
            d.cubo(0, 0, 0, 10, 50, 10);

            d.drawToBuffer(buffer,this);

            d.resetImage(this);
            d.color = new Color(255, 168, 115);
            d.cubo(0, -30, 0, 20, 20, 20);
            d.drawToBuffer(buffer, this);

            d.setVectorTraslacion(0,0,0);
            d.resetImage(this);
            d.color = new Color(140, 86, 52);
            d.cubo(0, 55, 0, 30, 60, 30);
            d.cubo(30, 64, 0, 30, 40, 30);
            d.cubo(-30, 75, 0, 30, 20, 30);
            d.color = new Color(38, 157, 30);
            d.traslacion(0,0);
            d.rectanguloRelleno(0,376,getWidth(),getHeight());
            d.drawToBuffer(buffer, this);

        }
//        System.out.println(x + "," + y);

        cont++;
        g.drawImage(buffer, 0, 0, this); // doble buffer
    }


    public void paint(Graphics g) {

        update(g);

    }

}
