package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Ventana extends JFrame implements Runnable {

    private BufferedImage fondo;
    private BufferedImage edificios;
    private BufferedImage arboles;
    private BufferedImage cantina;
    private BufferedImage cementerio;

    // private Image buffer;
    private Thread hilo;
    private Animador animador;
    private boolean ojos=false;

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


        dibujarFondos();
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

    public void dibujarFondos() {
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
        d.triangulo(350, 240, 360, 100, 400, 240);
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
        d.malla(237, 210, 301, 240);
        d.malla(320, 180, 331, 240);
        d.curvaHumo(22, 163, 2, 4, 0);
        edificios = new BufferedImage(getWidth() * 5, getHeight(), BufferedImage.TYPE_INT_ARGB);
        edificios.getGraphics().drawImage(edificio, 0, 0, null);
        edificios.getGraphics().drawImage(edificio, getWidth(), 0, null);
        edificios.getGraphics().drawImage(edificio, getWidth() * 2, 0, null);
        edificios.getGraphics().drawImage(edificio, getWidth() * 3, 0, null);
        edificios.getGraphics().drawImage(edificio, getWidth() * 4, 0, null);

        BufferedImage arbol = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        d = new Dibujar(arbol);

        // arboles
        d.traslacion(200, 270);
        d.color = new Color(101, 70, 40);
        d.rectangulo(-9, -49, 6, 0);
        d.color = new Color(60, 160, 50);
        d.elipse(-1, -94, 15, 59);
        d.scanLineFill(-3, -77);
        d.scanLineFill(-2, -45);
        d.scanLineFill(-5, -49);
        d.color = new Color(101, 70, 40);
        d.scanLineFill(-4, -13);

        d.traslacion(100, 265);
        d.escalacion(0.5, 0.5);
        d.color = new Color(101, 70, 40);
        d.rectangulo(-9, -49, 6, 0);
        d.color = new Color(60, 160, 50);
        d.elipse(-1, -94, 15, 59);
        d.scanLineFill(-3, -77);
        d.scanLineFill(-2, -45);
        d.scanLineFill(-5, -49);
        d.color = new Color(101, 70, 40);
        d.scanLineFill(-4, -13);

        d.traslacion(400, 260);
        d.escalacion(0.75, 0.75);
        d.color = new Color(101, 70, 40);
        d.rectangulo(-9, -49, 6, 0);
        d.color = new Color(60, 160, 50);
        d.elipse(-1, -94, 15, 59);
        d.scanLineFill(-3, -77);
        d.scanLineFill(-2, -45);
        d.scanLineFill(-5, -49);
        d.color = new Color(101, 70, 40);
        d.scanLineFill(-4, -13);

        arboles = new BufferedImage(getWidth() * 5, getHeight(), BufferedImage.TYPE_INT_ARGB);
        arboles.getGraphics().drawImage(arbol, 0, 0, null);
        arboles.getGraphics().drawImage(arbol, getWidth(), 0, null);
        arboles.getGraphics().drawImage(arbol, getWidth() * 2, 0, null);
        arboles.getGraphics().drawImage(arbol, getWidth() * 3, 0, null);
        arboles.getGraphics().drawImage(arbol, getWidth() * 4, 0, null);

        // cantina

        cantina = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        d = new Dibujar(cantina);
        d.color = new Color(168, 118, 85);
        d.scanLineFill(135, 95);
        d.color = new Color(65, 24, 4);
        d.rectangulo(52, 256, 500, 450);
        d.linea(52, 256, 112, 227);
        d.linea(112, 227, 500, 227);
        d.color = new Color(128, 63, 53);
        d.scanLineFill(253, 281);
        d.color = new Color(195, 108, 87);
        d.scanLineFill(164, 240);
        d.color = new Color(53, 27, 1);
        d.rectangulo(189, 41, 500, 227);
        d.rectangulo(211, 63, 461, 114);
        d.rectangulo(211, 146, 461, 201);
        d.color = new Color(200, 144, 124);
        d.scanLineFill(200, 129);
        d.color = new Color(91, 56, 48);
        d.scanLineFill(264, 87);
        d.scanLineFill(245, 166);
        d.color=new Color(255, 234, 33);
        d.curvaSol(100,90,2,2);

// botellas
        d.traslacion(250, 250);
        d.color = new Color(93, 58, 49);
        d.rectanguloRelleno(-27, -170, -6, -137);
        d.color = new Color(69, 145, 115);
        d.rectanguloRelleno(-26, -169, -7, -137);
        d.rectanguloRelleno(-20, -183, -13, -165);
        d.rectanguloRelleno(117, -76, 128, -50);
        d.rectanguloRelleno(121, -87, 127, -72);
        d.rectanguloRelleno(29, -88, 42, -50);
        d.color = new Color(201, 161, 23);
        d.rectanguloRelleno(68, -170, 84, -137);
        d.rectanguloRelleno(73, -180, 79, -167);
        d.rectanguloRelleno(153, -79, 166, -49);
        d.rectanguloRelleno(156, -93, 163, -76);
        d.color = new Color(165, 97, 111);
        d.rectanguloRelleno(19, -177, 36, -137);
        d.rectanguloRelleno(98, -163, 113, -138);
        d.rectanguloRelleno(103, -178, 108, -162);
        d.rectanguloRelleno(167, -173, 182, -137);


        d.color = new Color(200, 200, 200);
        d.elipse(65, -10, 32, 12);
        d.scanLineFill(72, -13);
        d.color = new Color(65, 16, 10);
        d.elipse(-70, 91, 64, 15);
        d.traslacion(0, 0);
        d.linea(116, 341, 116, 450);
        d.linea(244, 341, 244, 450);

        d.traslacion(250, 250);
        d.color = new Color(211, 142, 106);
        d.scanLineFill(-54, 123);
        d.color = new Color(241, 182, 126);
        d.scanLineFill(-62, 99);


        // cementerio
        cementerio = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        d = new Dibujar(cementerio);
        d.traslacion(250,250);
        d.color = new Color(64,143,222);
        d.scanLineFill(-80,-132);
        d.linea(-250, -16, 250, -17);
        d.color = new Color(87,118,7);
        d.linea(-250, -14, 250, -2);
        d.scanLineFill(-90,73);

        d.color = new Color(101,182,234);
        d.linea(-250, -105, 250, -86);
        d.scanLineFill(-119,-87);

        d.color = new Color(80, 160, 230);
        d.linea(-250, -179, 250, -165);
        d.scanLineFill(-102,-130);

        d.color = new Color(130,126,160);
        d.rectangulo(-137, -74, -70, 31);
        d.scanLineFill(-105,-34);
        d.scanLineFill(-108,8);
        d.color = new Color(50,70,119);
        d.linea(-136, -73, -150, -62);
        d.linea(-150, -62, -150, 17);
        d.linea(-150, 17, -138, 30);
        d.scanLineFill(-142,-58);
        d.scanLineFill(-144,8);
        d.color = new Color(189,158,184);
        d.rectanguloRelleno(-110, -55, -100, -8);
        d.rectanguloRelleno(-125, -44, -85, -34);
        d.color = new Color(77,76,94);
        d.poligono(new int[] {-250, -207, -180, -163, -125, -106, -97, -98, -250},new int[] {69, 60, 57, 68, 83, 119, 145, 167, 166});
        d.scanLineFill(-173,92);
        d.color = new Color(50,66,115);
        d.poligono(new int[] {53, 58, 58, 23, -12, -1, 37, 20, -9, 24, 56, 74, 100, 132, 113, 88, 81, 90},new int[] {8, -26, -67, -83, -76, -93, -99, -116, -129, -130, -106, -96, -113, -121, -95, -73, -31, 8});
        d.scanLineFill(72,-43);
        d.scanLineFill(50,-100);
        d.scanLineFill(77,3);



    }

    int cont = 0;

    public void update(Graphics g) {
        Image buffer = createImage(getWidth(), getHeight());

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujar d = new Dibujar(image);

        animador.setDibujar(d);
        // escena 1
        if (animador.tiempoActual > 0 && animador.tiempoActual <= 22000) {

            animador.nuevaAnimacion();
            d.traslacion(180, 350);
            castor(d);

            for (int i = 0; i < 10; i++) {
                int t = 6500 + i * 4000;
                animador.escalacion(0.85, 0.85, t, t + 1000);
                animador.escalacion(-0.85, -0.85, t + 1000, t + 2000);
            }
            d.color = new Color(30, 30, 30);
            d.traslacion(180 - 15, 350 - 165);
            d.elipse(0, 0, 5, 5);
            d.scanLineFill(0, 0);

            d.traslacion(180 + 23, 350 - 164);
            d.elipse(0, 0, 5, 5);
            d.scanLineFill(0, 0);

            //cigarro
            animador.nuevaAnimacion();
            d.traslacion(240, 250);
            d.color = new Color(220, 220, 220);
            d.rectangulo(0, 0, 40, 10);
            d.scanLineFill(10, 5);
            d.color = new Color(248, 144, 27);
            d.rectanguloRelleno(0, 0, 10, 10);
            d.color = new Color(82, 82, 82);
            d.rectanguloRelleno(35, 0, 40, 10);
            d.color = new Color(220, 220, 220);
            d.curvaHumo(280, 250, 5, 10, -cont);

            // botella
            animador.nuevaAnimacion();
            d.traslacion(60, 280);
            for (int i = 0; i < 10; i++) {
                int t = 6000 + i * 4000;
                animador.rotacion(1, t, t + 1000);
                animador.rotacion(-1, t + 1000, t + 2000);
                animador.traslacion(-10, -30, t, t + 1000);
                animador.traslacion(10, 30, t + 1000, t + 2000);
            }
            d.color = new Color(69, 145, 115);
            d.rectangulo(-15, -56, 17, -1);
            d.rectangulo(-6, -91, 8, -51);
            d.rectangulo(-8, -100, 9, -92);
            d.scanLineFill(1, -53);
            d.scanLineFill(0, -65);
            d.color = new Color(248, 144, 27);
            d.scanLineFill(0, -96);
            d.color = new Color(69, 145, 115);
            d.scanLineFill(-3, -50);
            d.scanLineFill(-10, -19);
            d.color = new Color(220, 220, 220);
            d.rectangulo(-8, -44, 12, -9);
            d.scanLineFill(1, -30);

            buffer.getGraphics().drawImage(cantina, 0, 0, null);
            buffer.getGraphics().drawImage(image, 0, 0, null);

        }
        // escena 2
        if (animador.tiempoActual > 22000 && animador.tiempoActual <= 67000) {
            ojos=true;
            animador.tiempoActual -=20000;

            // nubes
            animador.nuevaAnimacion();
            d.traslacion(50, 40);
            d.escalacion(1, 1);
            animador.traslacion(600, 0, 0, 75000);
            d.color = new Color(230, 230, 230);
            d.poligono(new int[]{-39, -34, -11, 41, 51, 54, 35, 14, 9, -2, -15, -30}, new int[]{3, 8, 10, 12, 5, -4, -17, -16, -10, -17, -19, -7});
            d.scanLineFill(22, 3);

            animador.nuevaAnimacion();
            d.traslacion(450, 85);
            d.escalacion(0.5, 1);

//            for (int i = 0; i <= 25; i++) {
//                int duracion = i * 2000;
//                int duracion2 = duracion + 2000;
//                animador.traslacion(-1500, 0, duracion, duracion2);
//                animador.traslacion(1500, 0, duracion2, duracion2);
//            }
            animador.traslacion(-700, 0, 0, 80000);
            d.color = new Color(220, 220, 220);
            d.poligono(new int[]{-39, -34, -11, 41, 51, 54, 35, 14, 9, -2, -15, -30}, new int[]{3, 8, 10, 12, 5, -4, -17, -16, -10, -17, -19, -7});
            d.scanLineFill(22, 3);

            animador.nuevaAnimacion();
            d.traslacion(200, 60);
            d.escalacion(0.75, 0.3);
            animador.traslacion(700, 0, 0, 200000);
            d.color = new Color(225, 225, 225);
            d.poligono(new int[]{-39, -34, -11, 41, 51, 54, 35, 14, 9, -2, -15, -30}, new int[]{3, 8, 10, 12, 5, -4, -17, -16, -10, -17, -19, -7});
            d.scanLineFill(22, 3);


            // lineas carreteras
            animador.nuevaAnimacion();
            d.traslacion(500, 420);
            for (int i = 0; i <= 25; i++) {
                int duracion = i * 2000;
                int duracion2 = duracion + 2000;
                animador.traslacion(-1500, 0, duracion, duracion2);
                animador.traslacion(1500, 0, duracion2, duracion2);

            }

            d.color = new Color(229, 205, 48);
            d.rectanguloRelleno(0, 0, 120, 12);


            // moto
            animador.nuevaAnimacion();
            animador.animacionSuave(true);
            animador.traslacion(-100, 0, 5000, 10000);
            animador.traslacion(100, 30, 10000, 12000);
            animador.traslacion(-70, 30, 12000, 15000);
            animador.traslacion(-100, -20, 15000, 22000);
            animador.traslacion(75, 20, 22000, 30000);
            animador.traslacion(100, -20, 30000, 35000);

            // perder control
            int[] xAnimaciones = new int[]{-30, -20, +50, -20, +70, -60, -30, +50, -20, +80, -60, +50, -16};
            for (int i = 0; i < 13; i++) {
                int duracion = 35000 + i * 750 + xAnimaciones[i];
                animador.traslacion(xAnimaciones[i] * 2, (int) (-xAnimaciones[i] * 0.3), duracion, duracion + 300);
                animador.traslacion(-xAnimaciones[i], (int) (-xAnimaciones[i] * 0.3), duracion + 300, duracion + 800);
            }
            animador.traslacion(-200, -600, 44500, 45000);

            // llanta 1
            d.color = new Color(49, 49, 49);
            d.traslacion(250, 325);
            d.elipse(0, 0, 25, 25);
            d.elipse(0, 0, 20, 20);
            d.elipse(0, 0, 16, 16);
            d.scanLineFill(-22, 0);
            d.color = new Color(234, 234, 234);
            d.scanLineFill(-18, 0);

            animador.rotacion(200, 0, 35000);
            for (int i = 0; i < 13; i++) {
                int duracion = 35000 + i * 750 + xAnimaciones[i];
                animador.rotacion(0, duracion, duracion + 300);
                animador.rotacion(10, duracion + 300, duracion + 800);
            }
            d.linea(-16, 0, 16, 0);
            d.linea(0, -16, 0, 16);
            d.linea(-13, -13, 13, 13);
            d.linea(-13, 13, 13, -13);

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
            animador.rotacion(200, 0, 35000);

            for (int i = 0; i < 13; i++) {
                int duracion = 35000 + i * 750 + xAnimaciones[i];
                animador.rotacion(0, duracion, duracion + 300);
                animador.rotacion(10, duracion + 300, duracion + 800);
            }
            d.linea(-16, 0, 16, 0);
            d.linea(0, -16, 0, 16);
            d.linea(-13, -13, 13, 13);
            d.linea(-13, 13, 13, -13);

            // moto
            animador.reiniciarRotacion();
            d.color = new Color(58, 151, 165);
            d.traslacion(45, 65);
            d.color = new Color(58, 151, 165);
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
            d.poligono(new int[]{269, 302, 302, 280, 266}, new int[]{244, 220, 205, 204, 219});
            d.scanLineFill(282, 230);
            d.color = new Color(30, 30, 30);
            d.rectanguloRelleno(199, 205, 231, 213);

            d.color = new Color(250, 250, 100);
            d.rectanguloRelleno(302, 206, 309, 220);

            // castor
            animador.traslacion(250, 205, 0, 0);
            d.escalacion(0.65, 0.65);


            for (int i = 0; i <= 30; i++) {
                if(i%2==0 &&i%3==0 ){

                    int duracion = i * 1000;
                    animador.traslacion(0, -20, duracion, duracion + 300);
                    animador.traslacion(0, 20, duracion + 300, duracion + 600);
                }

            }
            castor(d);

            animador.nuevaAnimacion();
            d.traslacion(600,350);
            animador.traslacion(-700,0, 44500, 45000);
            d.color=new Color(40,40,40);
            d.elipse(0,0,40,10);
            d.scanLineFill(0,0);

            buffer.getGraphics().drawImage(fondo, 0, 0, null);
            buffer.getGraphics().drawImage(edificios, -cont, 2, null);
            buffer.getGraphics().drawImage(arboles, (int) (-cont * 1.5), 2, null);
            buffer.getGraphics().drawImage(image, 0, 0, null);
        }
        // escena 3
        if (animador.tiempoActual > 67000 && animador.tiempoActual <= 82000) {
            animador.tiempoActual-=68000;

            animador.nuevaAnimacion();
            d.color=new Color(20,20,20);
            d.rectanguloRelleno(0,0,500,450);
            d.traslacion(250,300);

            animador.traslacion(0,50,1000,1500);
            animador.traslacion(0,-50,1500,2000);
            animador.traslacion(0,50,2500,3000);
            animador.traslacion(0,-50,3000,3500);
            animador.traslacion(0,50,4000,4500);
            animador.traslacion(0,-50,4500,5000);
            animador.traslacion(0,50,5500,6500);
            animador.traslacion(0,-50,6500,7500);
            animador.traslacion(0,-50,7500,8500);

            animador.escalacion(0.65,0.65,1000,1500);
            animador.escalacion(-0.65,-0.65,1500,2000);
            animador.escalacion(0.65,0.65,2500,3000);
            animador.escalacion(-0.65,-0.65,3000,3500);
            animador.escalacion(0.65,0.65,4000,4500);
            animador.escalacion(-0.65,-0.65,4500,5000);
            animador.escalacion(0.65,0.65,5500,6500);
            animador.escalacion(-0.65,-0.65,6500,7500);
            animador.escalacion(-0.65,-0.65,7500,8500);

            if(animador.tiempoActual<9000) {
                d.color = new Color(220, 60, 60);
            }else{
                d.color= new Color(54, 33, 23);
            }
            d.linea(0,0,-60,-86);
            d.linea(-60,-86,60,-84);
            d.linea(60,-84,0,0);
            d.scanLineFill(0,-48);
            d.elipse(-32, -108, 36, 35);
            d.elipse(36, -108, 33, -35);

            d.scanLineFill(-22,-104);
            d.scanLineFill(29,-96);

            d.rectanguloRelleno(-20,-108,20,-80);

            buffer.getGraphics().drawImage(image,0,0,null);
        }
        // escena 4
        if (animador.tiempoActual > 82000 && animador.tiempoActual <= 95000 ) {
            animador.tiempoActual-=82000;
            ojos=true;
            animador.nuevaAnimacion();
            d.traslacion(250,280);
            d.escalacion(0.75,0.75);
            animador.traslacion(50,80,0,20000);
            castor(d);

            animador.nuevaAnimacion();
            d.traslacion(350,300);
            d.escalacion(0.65,0.65);
            animador.traslacion(80,100,0,20000);
            castor(d);
            buffer.getGraphics().drawImage(cementerio,0,0,null);
            buffer.getGraphics().drawImage(image,0,0,null);
        }
        // final
        if (animador.tiempoActual > 95000) {
            d.color=new Color(220,220,220);
            d.rectanguloRelleno(0,0,500,450);
            buffer.getGraphics().drawImage(image,0,0,null);
        }
        cont++;
        g.drawImage(buffer, 0, 0, this); // doble buffer
    }


    public void paint(Graphics g) {

        update(g);

    }

    public void castor(Dibujar d) {
        d.color = new Color(193, 137, 80);
        d.elipse(1, -68, 37, 67);
        d.elipse(0, -151, 40, 43);
        d.elipse(-30, -190, 5, 9);
        d.elipse(32, -188, 4, 11);
        d.elipse(-28, -17, 16, 18);
        d.elipse(25, -17, 16, 18);
        d.scanLineFill(-4, -149);
        d.scanLineFill(-1, -116);
        d.scanLineFill(-4, -87);
        d.scanLineFill(-17, -23);
        d.scanLineFill(18, -20);
        d.scanLineFill(-35, -21);
        d.scanLineFill(31, -9);
        d.color = new Color(148, 103, 64);
        d.rectanguloRelleno(-51, -9, -25, 0);
        d.rectanguloRelleno(26, -9, 47, 0);
        d.scanLineFill(-29, -193);
        d.scanLineFill(31, -190);
        d.color = new Color(240, 240, 240);
        d.rectanguloRelleno(-6, -137, 3, -125);
        d.rectanguloRelleno(6, -137, 14, -125);
        d.color = new Color(229, 183, 123);
        d.elipse(4, -142, 16, 6);
        d.elipse(3, -69, 18, 38);
        d.scanLineFill(4, -142);
        d.scanLineFill(3, -69);
        // 34000
        if (animador.tiempoActual < 34000 || animador.tiempoActual > 72000) {

            d.color = new Color(240, 240, 240);
            d.elipse(-16, -166, 13, 12);
            d.elipse(21, -166, 11, 12);
            d.scanLineFill(-15, -167);
            d.scanLineFill(27, -164);
            if (ojos) {
                d.color = new Color(30, 30, 30);
                d.elipse(-15, -165, 5, 5);
                d.elipse(23, -164, 5, 6);
                d.scanLineFill(-15, -165);
                d.scanLineFill(23, -164);
            }

        }
        d.color = new Color(30, 30, 30);
        d.elipse(4, -148, 7, 6);
        d.scanLineFill(7, -150);
        d.scanLineFill(4, -148);
        d.color = new Color(148, 103, 64);
        d.poligono(new int[]{-42, -94, -102, -80, -66, -37, -32}, new int[]{-27, -57, -70, -94, -91, -65, -33});
        d.scanLineFill(-77, -67);
        d.color = new Color(193, 137, 80);
        d.linea(-93, -78, -91, -58);
        d.linea(-77, -93, -73, -45);
        d.linea(-58, -84, -56, -38);
        d.linea(-42, -69, -41, -31);
        d.linea(-90, -84, -60, -83);
        d.linea(-99, -70, -42, -66);
        d.linea(-90, -54, -36, -52);
        d.linea(-58, -40, -33, -39);

    }
}
