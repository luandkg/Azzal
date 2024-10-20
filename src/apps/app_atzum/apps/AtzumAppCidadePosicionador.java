package apps.app_atzum.apps;

import apps.app_atzum.utils.AtzumCidades;
import apps.app_atzum.AtzumCreator;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.RefBool;
import libs.luan.RefInt;
import libs.luan.Unico;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AtzumAppCidadePosicionador extends Cena {

    private String LOCAL = "/home/luan/Imagens/atzum/";
    private BufferedImage mapa = null;
    private BufferedImage mapa_grande = null;

    private BufferedImage mapa_drone = null;
    private boolean drone_ok = false;
    private Renderizador render_drone;

    private BufferedImage mapa_super_drone = null;
    private boolean super_drone_ok = false;
    private Renderizador render_super_drone;


    private int X0 = 300;
    private int Y0 = 100;

    private int X1 = 0;
    private int Y1 = 0;


    private Cores mCores;


    private Fonte ESCRITOR_NORMAL;
    private Fonte ESCRITOR_NORMAL_VERMELHO;

    private boolean mGPS_OK = false;
    private int mGPS_PX = 0;
    private int mGPS_PY = 0;


    private Unico<Ponto> mCidades;

    private String ARQUIVO_CIDADES = LOCAL + "CIDADES.dkg";

    private Clicavel mClicavel;
    private BotaoCor BTN_ZERAR;

    private RefBool ACAO_LIMPANTE = new RefBool(false);
    private RefInt LIMPADOR = new RefInt(50);

    @Override
    public void iniciar(Windows eWindows) {
        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(Cor.getRGB(Color.GREEN), 10);
        ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(Cor.getRGB(Color.RED), 10);

        mapa = AtzumCreator.GET_MAPA_DE_RELEVO();

        // mapa = Imagem.girar(mapa);
        //  mapa = Imagem.espelhar_verticalmente(mapa);
        //  mapa = Imagem.espelhar_horizontalmente(mapa);

        //  Imagem.exportar(mapa,LOCAL + "atzum_organizado.png");

        Renderizador render2 = new Renderizador(mapa);


        Cores mCores = new Cores();

      //  AtzumCreator.NORMALIZAR_2_CORES_ABAIXO_DE(render2, 100, mCores.getVermelho(), mCores.getPreto());

        mapa = render2.toImagemSemAlfa();
        mapa_grande = Imagem.getCopia(mapa);


        mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

        X1 = X0 + mapa.getWidth();
        Y1 = Y0 + mapa.getHeight();

        mCidades = new Unico<Ponto>(Ponto.IGUAL());
        for (Ponto p : AtzumCidades.ABRIR_LOCAIS(ARQUIVO_CIDADES)) {
            mCidades.item(p);
        }

        Renderizador grande = new Renderizador(mapa_grande);
        for (Ponto cidade : mCidades) {
            grande.drawCirculoCentralizado_Pintado((cidade.getX()), (cidade.getY()), 3, mCores.getAmarelo());
        }
        mapa_grande = grande.toImagemSemAlfa();

        mapa_drone = Imagem.criarEmBranco(300, 300);
        render_drone = new Renderizador(mapa_drone);

        mapa_super_drone = Imagem.criarEmBranco(300, 300);
        render_super_drone = new Renderizador(mapa_super_drone);


        mClicavel = new Clicavel();

        BTN_ZERAR = mClicavel.criarBotaoCor(new BotaoCor(200, 50, 50, 50, mCores.getPreto()));
        BTN_ZERAR.setAcao(new Acao() {
            @Override
            public void onClique() {

                if (ACAO_LIMPANTE.get()) {

                    if (LIMPADOR.get() == 50) {
                        LIMPADOR.set(100);
                    } else if (LIMPADOR.get() == 100) {
                        LIMPADOR.set(50);
                    }

                }

                if (!ACAO_LIMPANTE.get()) {
                    ACAO_LIMPANTE.set(true);
                }

            }
        });

        BotaoCor BTN_MARCADOR = mClicavel.criarBotaoCor(new BotaoCor(300, 50, 50, 50, mCores.getAmarelo()));
        BTN_MARCADOR.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
            }
        });

    }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());

        mGPS_OK = false;
        drone_ok = false;
        super_drone_ok=false;

        if (px >= X0 && py >= Y0 && px < X1 && py < Y1) {
            mGPS_OK = true;
            mGPS_PX = ((px - X0) * 2);
            mGPS_PY = ((py - Y0) * 2) - 5;
        }


        if (getWindows().getMouse().isClicked()) {

            if (mGPS_OK) {

                if (ACAO_LIMPANTE.get()) {

                    int limpador_tamanho = LIMPADOR.get();

                    int lx = (mGPS_PX / 2) - (limpador_tamanho / 2);
                    int ly = (mGPS_PY / 2) - (limpador_tamanho / 2);

                    Retangulo limpar = new Retangulo(lx, ly, limpador_tamanho, limpador_tamanho);

                    Lista<Ponto> remover = new Lista<Ponto>();

                    for (Ponto entao : mCidades) {

                        int ppx = (entao.getX() / 2);
                        int ppy = (entao.getY() / 2);

                        if (limpar.isDentro(ppx, ppy)) {
                            remover.adicionar(entao);
                        }

                    }

                    mCidades.remover_varios(remover);

                    AtzumCidades.SALVAR_LOCAIS(mCidades.toLista(), ARQUIVO_CIDADES);
                } else {
                    mCidades.item(new Ponto(mGPS_PX, mGPS_PY));
                    AtzumCidades.SALVAR_LOCAIS(mCidades.toLista(), ARQUIVO_CIDADES);
                }


            }

            getWindows().getMouse().liberar();
        }


        if (mGPS_OK) {

            // DRONE
            int comecar_x = mGPS_PX - 100;
            int comecar_y = mGPS_PY - 100;

            int terminar_x = mGPS_PX + 200;
            int terminar_y = mGPS_PY + 200;

            drone_ok = true;
            render_drone.limpar(mCores.getBranco());

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

            for (Ponto cidade : mCidades) {
                if (cidade.getX() > comecar_x && cidade.getX() < terminar_x && cidade.getY() > comecar_y && cidade.getY() < terminar_y) {

                    int cidade_x = cidade.getX() - comecar_x;
                    int cidade_y = cidade.getY() - comecar_y;

                    render_drone.drawCirculoCentralizado_Pintado(cidade_x, cidade_y, 3, mCores.getAmarelo());

                }
            }


            int drone_x = mGPS_PX - comecar_x;
            int drone_y = mGPS_PY - comecar_y;

            render_drone.drawCirculoCentralizado_Pintado(drone_x, drone_y, 5, mCores.getVerde());


            // SUPER DRONE
            super_drone_ok = true;
            render_super_drone.limpar(mCores.getBranco());

            int super_comecar_x = mGPS_PX - 50;
            int super_comecar_y = mGPS_PY - 50;

            int super_terminar_x = mGPS_PX + 100;
            int super_terminar_y = mGPS_PY + 100;

            int super_ady = 0;

            for (int dy = super_comecar_y; dy < super_terminar_y; dy++) {
                int super_adx = 0;
                for (int dx = super_comecar_x; dx < super_terminar_x; dx++) {
                    if (dx > 0 && dx < mapa_grande.getWidth() && dy > 0 && dy < mapa_grande.getHeight()) {

                        render_super_drone.setPixelPuro(super_adx, super_ady, mapa_grande.getRGB(dx, dy));
                        render_super_drone.setPixelPuro(super_adx+1, super_ady, mapa_grande.getRGB(dx, dy));
                        render_super_drone.setPixelPuro(super_adx, super_ady+1, mapa_grande.getRGB(dx, dy));
                        render_super_drone.setPixelPuro(super_adx+1, super_ady+1, mapa_grande.getRGB(dx, dy));

                    }
                    super_adx += 2;
                }
                super_ady += 2;
            }

            for (Ponto cidade : mCidades) {
                if (cidade.getX() > super_comecar_x && cidade.getX() < super_terminar_x && cidade.getY() > super_comecar_y && cidade.getY() < super_terminar_y) {

                    int cidade_x = cidade.getX() - super_comecar_x;
                    int cidade_y = cidade.getY() - super_comecar_y;

                    render_super_drone.drawCirculoCentralizado_Pintado(cidade_x*2, cidade_y*2, 3, mCores.getAmarelo());

                }
            }

            int super_drone_x = mGPS_PX - super_comecar_x;
            int super_drone_y = mGPS_PY - super_comecar_y;

            render_super_drone.drawCirculoCentralizado_Pintado(super_drone_x*2, super_drone_y*2, 5, mCores.getVerde());


        }


    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());

        ESCRITOR_NORMAL.setRenderizador(g);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(g);

        mClicavel.onDraw(g);

        g.drawImagem(X0, Y0, mapa);

        if (mGPS_OK) {
            ESCRITOR_NORMAL.escreva(100, 100, "GPS ON");
            ESCRITOR_NORMAL.escreva(100, 120, "X = " + mGPS_PX);
            ESCRITOR_NORMAL.escreva(100, 140, "Y = " + mGPS_PY);
        } else {
            ESCRITOR_NORMAL_VERMELHO.escreva(100, 100, "GPS FAILED");
        }


        for (Ponto cidade : mCidades) {
            g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 2, mCores.getAmarelo());
        }


        if (drone_ok) {
            g.drawImagem(100, 600, mapa_drone);
        }
        g.drawRect(100, 600, 300, 300, mCores.getVermelho());



        if (super_drone_ok) {
            g.drawImagem(100, 200, mapa_super_drone);
        }
        g.drawRect(100, 200, 300, 300, mCores.getVermelho());


        g.drawRect(BTN_ZERAR.getX(), BTN_ZERAR.getY(), BTN_ZERAR.getLargura(), BTN_ZERAR.getAltura(), mCores.getBranco());

        if (ACAO_LIMPANTE.get()) {
            if (mGPS_OK) {

                int lx = (mGPS_PX / 2) + X0;
                int ly = (mGPS_PY / 2) + Y0;

                int limpador_tamanho = LIMPADOR.get();

                g.drawRect(lx - (limpador_tamanho / 2), ly - (limpador_tamanho / 2), limpador_tamanho, limpador_tamanho, mCores.getBranco());
            }
        }


    }
}
