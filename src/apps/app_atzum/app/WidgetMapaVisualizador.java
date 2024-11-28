package apps.app_atzum.app;

import libs.azzal.Renderizador;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WidgetMapaVisualizador {

    public BufferedImage mapa_grande = null;
    public BufferedImage mapa_pequeno = null;


    private int X0 = 700;
    private int Y0 = 100;

    private int X1 = 0;
    private int Y1 = 0;

    public boolean mGPS_OK = false;
    public int mGPS_PX = 0;
    public int mGPS_PY = 0;

    public WidgetMapaVisualizador(BufferedImage eMapaGrande, BufferedImage eMapaPequeno) {

        mapa_grande = eMapaGrande;
        mapa_pequeno = eMapaPequeno;


        X1 = X0 + mapa_pequeno.getWidth();
        Y1 = Y0 + mapa_pequeno.getHeight();

    }


    public boolean isDentro(int px, int py) {

        mGPS_OK = false;

        if (px >= X0 && py >= Y0 && px < X1 && py < Y1) {

            mGPS_OK = true;
            mGPS_PX = (px - X0) * 2;
            mGPS_PY = (py - Y0) * 2;

            return true;
        }
        return false;
    }

    public int getGPS_PX() {
        return mGPS_PX;
    }

    public int getGPS_PY() {
        return mGPS_PY;
    }

    public void render(Renderizador g) {
        g.drawImagem(X0, Y0, mapa_pequeno);
    }

    public int getPosX(){
        return X0;
    }

    public int getPosY(){
        return Y0;
    }

    public boolean isGPS_ON(){
        return mGPS_OK;
    }

    public void setMapaGrande(BufferedImage eMapa){
        mapa_grande=eMapa;
    }

    public void setMapaPequeno(BufferedImage eMapa){
        mapa_pequeno=eMapa;
    }

    public void espelhar(int comecar_x,int comecar_y,int terminar_x,int terminar_y,Renderizador render_drone){

        int ady = 0;

        for (int dy = comecar_y; dy < terminar_y; dy++) {
            int adx = 0;
            for (int dx = comecar_x; dx < terminar_x; dx++) {
                if (dx > 0 && dx < mapa_grande.getWidth() && dy > 0 && dy < mapa_grande.getHeight()) {
                    render_drone.setPixelPuro(adx, ady, mapa_grande.getRGB(dx, dy));
                }
                adx += 1;
            }
            ady += 1;
        }


    }
}
