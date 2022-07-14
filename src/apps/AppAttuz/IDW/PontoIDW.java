package apps.AppAttuz.IDW;

public class PontoIDW {

    private int mx;
    private int my;
    private int distancia;
    private int valor;

    public PontoIDW(int ex, int ey, int edistancia, int evalor) {
        mx = ex;
        my = ey;
        distancia = edistancia;
        valor = evalor;
    }

    public int getX() {
        return mx;
    }

    public int getY() {
        return my;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getValor() {
        return valor;
    }

    public void setDistancia(int d) {
        distancia = d;
    }

    public boolean isDiferente(int x, int y) {
        boolean ret = true;

        if (x == mx && y == my) {
            ret = false;
        }
        return ret;
    }

    public boolean isIgual(int x, int y) {
        boolean ret = false;

        if (x == mx && y == my) {
            ret = true;
        }
        return ret;
    }
}
