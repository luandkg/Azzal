package apps.app_azzal;

import libs.azzal.Mouse;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

public class QuadranteColorido {

    private int mx;
    private int my;
    private int regua_y;

    private int escala;
    private int sel_r;
    private int sel_g;

    public QuadranteColorido(int ex, int ey) {
        mx = ex;
        my = ey;
        regua_y = ey + 255 + 30;

        escala = 0;
        sel_r = 0;
        sel_g = 0;
    }

    public int getVermelho() {
        return sel_r;
    }

    public int getVerde() {
        return sel_g;
    }

    public int getAzul() {
        return escala;
    }

    public void update(Mouse eMouse) {

        if (eMouse.isClicked()) {

            if (eMouse.getX() > mx && (eMouse.getX() < mx + 255)) {
                if (eMouse.getY() > my && (eMouse.getY() < my + 255)) {
                    sel_g = eMouse.getX() - mx;
                    sel_r = eMouse.getY() - my;
                }
            }

            if (eMouse.getX() > mx && (eMouse.getX() < mx + 255)) {
                if (eMouse.getY() > regua_y && (eMouse.getY() < regua_y + 30)) {
                    escala = 255 - (eMouse.getX() - mx);
                }
            }

        }


    }

    public void render(Renderizador r) {

        int QUAD = 255;

        for (int y = 0; y < QUAD; y++) {
            for (int x = 0; x < QUAD; x++) {

                int cor = ((255 & 0xFF) << 24) |
                        ((y & 0xFF) << 16) |
                        ((x & 0xFF) << 8) |
                        ((escala & 0xFF) << 0);

                r.drawPixelBruto(mx + x, my + y, cor);
            }
        }


        for (int y = 0; y < 30; y++) {
            int inverso = 255;
            for (int x = 0; x < QUAD; x++) {
                r.drawPixel(mx + x, regua_y + y, new Cor(inverso, inverso, inverso));
                inverso -= 1;
            }
        }

        //  r.drawRect(mx ,regua_y,255,30,new Cor(0,0,0));

        r.drawRect_Pintado(mx + (255-escala) - 5, regua_y + 10, 10, 10, new Cor(getVermelho(), getVerde(), getAzul()));

    }


}
