package aplicacion;

public class Animador {
    Dibujar dibujar;
    long inicioAplicacion;
    long tiempoActual;
    int dx = 0, dy = 0;
    double sx = 1, sy = 1;
    double theta = 0;
    private boolean isSuave = false;

    public void traslacion(int dxDestino, int dyDestino, long inicioAnimacion, long finAnimacion) {
        if (tiempoActual < inicioAnimacion) {
            return;
        }

        if (tiempoActual <= finAnimacion) {
            long milisegundosAnimados = tiempoActual - inicioAnimacion;
            long duracionAnimacion = finAnimacion - inicioAnimacion;

            double tiempoAnimado = (double) milisegundosAnimados / duracionAnimacion;
            double porcentajeAnimacion = ease(tiempoAnimado);
            dx += dxDestino * porcentajeAnimacion;
            dy += dyDestino * porcentajeAnimacion;
        } else {
            dx += dxDestino;
            dy += dyDestino;
        }
        dibujar.traslacionAnimacion(dx, dy);
    }

    private double ease(double t) {
        if (isSuave)
            return 3 * t * t - 2 * t * t * t;
        else
            return t;
    }

    public void escalacion(double sxDestino, double syDestino, long inicioAnimacion, long finAnimacion) {
        if (tiempoActual < inicioAnimacion) {
            return;
        }

        if (tiempoActual <= finAnimacion) {
            long milisegundosAnimados = tiempoActual - inicioAnimacion;
            long duracionAnimacion = finAnimacion - inicioAnimacion;

            double tiempoAnimado = (double) milisegundosAnimados / duracionAnimacion;
            double porcentajeAnimacion = ease(tiempoAnimado);

            sx += sxDestino * porcentajeAnimacion;
            sy += syDestino * porcentajeAnimacion;
        } else {
            sx += sxDestino;
            sy += syDestino;
        }
        dibujar.escalacion((float) sx, (float) sy);
    }

    public void rotacion(double thetaDestino, long inicioAnimacion, long finAnimacion) {
        if (tiempoActual < inicioAnimacion) {
            return;
        }

        if (tiempoActual <= finAnimacion) {
            long milisegundosAnimados = tiempoActual - inicioAnimacion;
            long duracionAnimacion = finAnimacion - inicioAnimacion;
            double porcentajeAnimacion = (double) milisegundosAnimados / duracionAnimacion;

            dibujar.rotacion((float) (theta + thetaDestino * porcentajeAnimacion));
        } else {
            dibujar.rotacion((float) (theta + thetaDestino));
            theta += thetaDestino;
        }
    }

    public void setInicioAplicacion(long inicioAplicacion) {
        this.inicioAplicacion = inicioAplicacion;
    }

    public void setDibujar(Dibujar dibujar) {
        tiempoActual = System.currentTimeMillis() - inicioAplicacion;
        this.dibujar = dibujar;
        reiniciarTransformaciones();
    }

    private void reiniciarTransformaciones() {
        dx = 0;
        dy = 0;
        sx = 1;
        sy = 1;
        theta = 0;
        isSuave=false;
        dibujar.reiniciarTransformaciones();
    }
    public void reiniciarRotacion() {
        theta = 0;
        isSuave=false;
        dibujar.reiniciarRotacion();
    }

    public void nuevaAnimacion() {
        reiniciarTransformaciones();
    }

    public void animacionSuave(boolean isSuave) {
        this.isSuave = isSuave;
    }
}
