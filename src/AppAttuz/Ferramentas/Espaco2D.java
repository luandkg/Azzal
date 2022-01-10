package AppAttuz.Ferramentas;

public class Espaco2D {

    public static int distancia_entre_pontos(int x1, int y1, int x2, int y2) {
        int distancia = (int) Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        return distancia;

    }

    public static boolean isDentro(int x, int y, int largura, int altura, int px, int py) {
        boolean ret = false;


        if (px >= x && px < (x + largura)) {
            if (py >= y && py < (y + altura)) {
                ret = true;
            }

        }

        //System.out.println("PT " + px + "::" + py + "[x=" + x + ",y=" + y + "] -->> " + ret);

        return ret;
    }

}
