package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Ventana extends JFrame implements Runnable {

    private BufferedImage fondo;
    private BufferedImage edificios;
    // private Image buffer;
    private Thread hilo;
    private Animador animador;

    public Ventana() {
        setTitle("ventana");
        setSize(500, 450);
        setLayout(null);

        // buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        // graPixel = (Graphics2D) buffer.createGraphics();

//        Graphics g = fondo.getGraphics();
//        g.setColor(new Color(250, 250, 250));
//        g.fillRect(0, 0, getWidth(), getHeight());

        animador = new Animador();


        dibujarFondo();
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

    public void dibujarFondo() {
        fondo = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujar d = new Dibujar(fondo);
        d.color = new Color(117, 203, 239);

        d.scanLineFill(219, 123);
        d.color = new Color(140, 199, 79);
        d.linea(0, 240, 518, 242);
        d.linea(0, 287, 500, 287);
        d.linea(0, 300, 500, 300);
        d.linea(27, 240, -8, 240);
        d.scanLineFill(223, 260);
        d.color = new Color(213, 205, 204);

        d.scanLineFill(307, 290);

        d.color = new Color(122, 116, 116);
        d.scanLineFill(165, 313);

        BufferedImage edificio = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        d = new Dibujar(edificio);

        d.color = new Color(174, 167, 166);
        d.rectangulo(103, 133, 39, 239);
        d.rectangulo(115, 189, 157, 240);

        d.color = new Color(174, 167, 166);
        d.poligono(new int[]{181, 168, 182, 184, 245, 241}, new int[]{241, 136, 136, 95, 97, 241});
        d.elipse(213, 125, 0, 28);
        d.elipse(213, 108, 33, 58);
        d.scanLineFill(217, 82);
        d.scanLineFill(207, 112);
        d.scanLineFill(205, 187);
        d.scanLineFill(81, 186);
        d.scanLineFill(136, 226);

        d.color = new Color(212, 203, 202);
        d.rectangulo(14, 194, 44, 240);
        d.rectangulo(78, 177, 116, 239);
        d.rectangulo(237, 210, 301, 240);
        d.rectangulo(320, 180, 331, 240);
        d.triangulo(350,240,360,100,400,240);
        d.scanLineFill(360, 200);
        d.scanLineFill(325, 200);
        d.scanLineFill(34, 215);
        d.scanLineFill(42, 214);
        d.scanLineFill(97, 209);
        d.scanLineFill(106, 207);
        d.scanLineFill(116, 206);
        d.scanLineFill(115, 206);
        d.scanLineFill(242, 222);
        d.scanLineFill(252, 225);
        d.rectangulo(28, 176, 18, 204);
        d.rectangulo(20, 163, 25, 177);
        d.scanLineFill(25, 185);
        d.scanLineFill(24, 172);

        d.color = new Color(174, 167, 166);
        d.malla(18, 176, 28, 204);
        d.malla(20, 163, 25, 177);
        d.malla(14, 194, 44, 240);
        d.malla(78, 177, 116, 239);
        d.curvaHumo(22, 163, 2, 4);
        edificios = new BufferedImage(getWidth() * 3, getHeight(), BufferedImage.TYPE_INT_ARGB);
        edificios.getGraphics().drawImage(edificio, 0, 0, null);
        edificios.getGraphics().drawImage(edificio, getWidth(), 0, null);
        edificios.getGraphics().drawImage(edificio, getWidth() * 2, 0, null);
    }

    int cont = 0;

    public void update(Graphics g) {
        Image buffer = createImage(getWidth(), getHeight());

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujar d = new Dibujar(image);

        animador.setDibujar(d);

        animador.nuevaAnimacion();
        d.traslacion(-60, 40);
        d.escalacion(1, 1);
        animador.traslacion(600, 0, 0, 30000);
        d.color = new Color(230, 230, 230);
        d.poligono(new int[]{-39, -34, -11, 41, 51, 54, 35, 14, 9, -2, -15, -30}, new int[]{3, 8, 10, 12, 5, -4, -17, -16, -10, -17, -19, -7});
        d.scanLineFill(22, 3);

        animador.nuevaAnimacion();
        d.traslacion(500, 45);
        d.escalacion(0.5, 1);
        animador.traslacion(-600, 0, 0, 12000);
        d.color = new Color(220, 220, 220);
        d.poligono(new int[]{-39, -34, -11, 41, 51, 54, 35, 14, 9, -2, -15, -30}, new int[]{3, 8, 10, 12, 5, -4, -17, -16, -10, -17, -19, -7});
        d.scanLineFill(22, 3);

        animador.nuevaAnimacion();
        d.traslacion(200, 60);
        d.escalacion(0.75, 0.3);
        animador.traslacion(700, 0, 0, 30000);
        d.color = new Color(225, 225, 225);
        d.poligono(new int[]{-39, -34, -11, 41, 51, 54, 35, 14, 9, -2, -15, -30}, new int[]{3, 8, 10, 12, 5, -4, -17, -16, -10, -17, -19, -7});
        d.scanLineFill(22, 3);

        animador.nuevaAnimacion();
        d.traslacion(500, 400);
        for (int i = 0; i <= 10; i++) {
            int duracion = i * 2000;
            int duracion2 = duracion + 2000;
            animador.traslacion(-800, 0, duracion, duracion2);
            animador.traslacion(800, 0, duracion2, duracion2);

        }

        d.color = new Color(229, 205, 48);
        d.rectanguloRelleno(0, 0, 70, 12);


        // moto
        animador.nuevaAnimacion();
        animador.animacionSuave(true);
        animador.traslacion(-100,0,5000,10000);
        animador.traslacion(100,30,10000,12000);
        animador.traslacion(-60,30,12000,15000);


        // llanta 1
        d.color = new Color(49, 49, 49);
        d.traslacion(250, 325);
        d.elipse(0, 0, 25, 25);
        d.elipse(0, 0, 20, 20);
        d.elipse(0, 0, 16, 16);
        d.scanLineFill(-22, 0);
        d.color = new Color(234, 234, 234);
        d.scanLineFill(-18, 0);
        animador.rotacion(200,0,30000);
        d.linea(-16, 0, 16, 0);
        d.linea(0,-16, 0, 16);
        d.linea(-13,-13, 13, 13);
        d.linea(-13, 13,13,-13);

        // llanta 2
        animador.reiniciarRotacion();
        d.color = new Color(49, 49, 49);
        d.traslacion(350, 325);
        d.elipse(0, 0, 25, 25);
        d.elipse(0, 0, 20, 20);
        d.elipse(0, 0, 16, 16);
        d.scanLineFill(-22, 0);
        d.color = new Color(234, 234, 234);
        d.scanLineFill(-18, 0);
        animador.rotacion(200,0,30000);
        d.linea(-16, 0, 16, 0);
        d.linea(0,-16, 0, 16);
        d.linea(-13,-13, 13, 13);
        d.linea(-13, 13,13,-13);

        // moto
        animador.reiniciarRotacion();
        d.color = new Color(58,151,165);
        d.traslacion(45,65);
        d.color = new Color(58,151,165);
        d.rectanguloRelleno(228, 226, 269, 262);
        d.rectanguloRelleno(247, 210, 269, 226);
        d.rectanguloRelleno(269, 204, 279, 219);
        d.rectanguloRelleno(195, 213, 231, 226);
        d.rectanguloRelleno(228, 263, 205, 254);
        d.rectanguloRelleno(270, 255, 307, 262);
        d.rectanguloRelleno(216, 223, 230, 233);
        d.rectanguloRelleno(269, 245, 289, 255);
        d.rectanguloRelleno(231, 217, 241, 225);
        d.rectanguloRelleno(221, 248, 227, 253);
        d.rectanguloRelleno(204, 254, 228, 262);
        d.rectanguloRelleno(168, 218, 196, 226);
        d.poligono(new int[] {269, 302, 302, 280, 266},new int[] {244, 220, 205, 204, 219});
        d.scanLineFill(282,230);
        d.color = new Color(30,30,30);
        d.rectanguloRelleno(199, 205, 231, 213);

        d.color = new Color(250,250,100);
        d.rectanguloRelleno(302, 206, 309, 220);


        for (int i = 0; i <= 10; i++) {
            int duracion = i * 1000;
            animador.traslacion(0,-20,duracion,duracion+300);
            animador.traslacion(0,20,duracion+300,duracion+600);

        }
        d.color = new Color(155,87,43);
        d.elipse(245, 170, 41, 38);
        d.elipse(218, 198, 16, 8);
        d.elipse(269, 199, 16, 8);
        d.elipse(245, 156, 26, 28);
        d.scanLineFill(209,196);
        d.scanLineFill(225,196);
        d.scanLineFill(265,197);
        d.scanLineFill(275,202);
        d.scanLineFill(248,194);
        d.scanLineFill(249,158);
        d.scanLineFill(243,130);
        d.color = new Color(250,250,250);
        d.elipse(232, 155, 11, 11);
        d.elipse(262, 156, 11, 11);
        d.scanLineFill(231,156);
        d.scanLineFill(259,157);
        d.color = new Color(10,10,10);
        d.elipse(235, 158, 4, 5);
        d.elipse(259, 159, 4, 4);
        d.scanLineFill(234,158);
        d.scanLineFill(259,160);
        d.elipse(244, 172, 7, 4);
        d.scanLineFill(243,170);
        d.color = new Color(155,87,43);
        d.rectanguloRelleno(222, 101, 267, 139);
        d.color = new Color(10,10,10);
        d.linea(247, 101, 267, 117);
        d.linea(235, 103, 267, 134);
        d.linea(223, 113, 241, 129);
        d.linea(223, 128, 228, 133);
        d.linea(223, 110, 232, 102);
        d.linea(225, 128, 254, 104);
        d.linea(243, 127, 266, 108);
        d.linea(259, 132, 267, 128);
        d.elipse(244, 187, 9, 6);
        d.scanLineFill(246,189);


        cont++;
        buffer.getGraphics().drawImage(fondo, 0, 0, null);
        buffer.getGraphics().drawImage(edificios, -cont, 2, null);
        buffer.getGraphics().drawImage(image, 0, 0, null);
        g.drawImage(buffer, 0, 0, this); // doble buffer
    }


    public void paint(Graphics g) {

        update(g);

    }

}
