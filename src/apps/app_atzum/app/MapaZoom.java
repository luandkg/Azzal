package apps.app_atzum.app;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class MapaZoom {

    private boolean drone_ultimo_valido = false;
    private int drone_ultimo_px = 0;
    private int drone_ultimo_py = 0;

    private AppAtzum mApp;

    private BufferedImage mapa_drone = null;
    private boolean drone_ok = false;
    private Renderizador render_drone;

    private Cores mCores;

    public MapaZoom(AppAtzum eApp) {
        mApp = eApp;

        mCores = new Cores();
        mapa_drone = Imagem.criarEmBranco(300, 300);
        render_drone = new Renderizador(mapa_drone);

    }

    public void update(boolean ultimo) {

        if (ultimo && !drone_ultimo_valido) {
            return;
        }


        if (!ultimo) {
            drone_ultimo_valido = true;
            drone_ultimo_px = mApp.mGPS_PX;
            drone_ultimo_py = mApp.mGPS_PY;
        }

        // DRONE
        int comecar_x = mApp.mGPS_PX - 100;
        int comecar_y = mApp.mGPS_PY - 100;

        int terminar_x = mApp.mGPS_PX + 200;
        int terminar_y = mApp.mGPS_PY + 200;

        if (ultimo) {
            comecar_x = drone_ultimo_px - 100;
            comecar_y = drone_ultimo_py - 100;

            terminar_x = drone_ultimo_px + 200;
            terminar_y = drone_ultimo_py + 200;
        }

        drone_ok = true;
        render_drone.limpar(mCores.getBranco());

        int ady = 0;

        for (int dy = comecar_y; dy < terminar_y; dy++) {
            int adx = 0;
            for (int dx = comecar_x; dx < terminar_x; dx++) {
                if (dx > 0 && dx < mApp.mapa_grande.getWidth() && dy > 0 && dy < mApp.mapa_grande.getHeight()) {
                    render_drone.setPixelPuro(adx, ady, mApp.mapa_grande.getRGB(dx, dy));
                }
                adx += 1;
            }
            ady += 1;
        }

        for (Ponto cidade : mApp.mCidades) {
            if (cidade.getX() > comecar_x && cidade.getX() < terminar_x && cidade.getY() > comecar_y && cidade.getY() < terminar_y) {

                int cidade_x = cidade.getX() - comecar_x;
                int cidade_y = cidade.getY() - comecar_y;

                render_drone.drawCirculoCentralizado_Pintado(cidade_x, cidade_y, 3, mCores.getAmarelo());

                if (mApp.mCidadeSelecionada) {
                    if (mApp.mCidadeSelecionadaX == cidade.getX() && mApp.mCidadeSelecionadaY == cidade.getY()) {

                        render_drone.drawCirculoCentralizado_Pintado(cidade_x, cidade_y, 5, mCores.getVerde());
                        render_drone.drawCirculoCentralizado_Pintado(cidade_x, cidade_y, 2, mCores.getAzul());

                    }
                }


            }
        }


        int drone_x = mApp.mGPS_PX - comecar_x;
        int drone_y = mApp.mGPS_PY - comecar_y;

        render_drone.drawCirculoCentralizado_Pintado(drone_x, drone_y, 5, mCores.getVerde());


    }

    public void render(Renderizador g) {
        if (drone_ok) {
            g.drawImagem(1900, 600, mapa_drone);
        }
    }

}
