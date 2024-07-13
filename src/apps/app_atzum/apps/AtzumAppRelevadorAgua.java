package apps.app_atzum.apps;

import apps.app_atzum.AtzumCreator;
import apps.app_atzum.escalas.EscalaAQ4;
import apps.app_atzum.utils.AtzumPontosInteiro;
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
import libs.luan.*;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AtzumAppRelevadorAgua extends Cena {

    private String LOCAL = "/home/luan/Imagens/atzum/";
    private BufferedImage mapa = null;

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


    private Unico<Par<Ponto, Integer>> mRelevo;

    private String ARQUIVO_RELEVO = LOCAL + "RELEVO_AGUA.dkg";

    private RefBool ACAO_LIMPANTE = new RefBool(false);
    private RefInt VALOR_CORRENTE = new RefInt(0);

    private Clicavel mClicavel;
    private BotaoCor BTN_ZERAR;


    @Override
    public void iniciar(Windows eWindows) {
        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(Cor.getRGB(Color.GREEN), 10);
        ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(Cor.getRGB(Color.RED), 10);

        mapa = AtzumCreator.GET_MAPA_DE_CONTORNO();


        Imagem.exportar(mapa, LOCAL + "atzum_organizado.png");

        Renderizador render2 = new Renderizador(mapa);
        AtzumCreator.NORMALIZAR_2_CORES_ABAIXO_DE(render2, 100, mCores.getVermelho(), mCores.getPreto());


        mapa = render2.toImagemSemAlfa();


        mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

        X1 = X0 + mapa.getWidth();
        Y1 = Y1 + mapa.getHeight();

        mRelevo = new Unico<Par<Ponto, Integer>>(AtzumPontosInteiro.PAR_PONTO_INTEGER_IGUALAVEL());
        for (Par<Ponto, Integer> p : AtzumPontosInteiro.ABRIR(ARQUIVO_RELEVO)) {
            mRelevo.item(p);
        }


        mClicavel = new Clicavel();

        BTN_ZERAR = mClicavel.criarBotaoCor(new BotaoCor(200, 50, 50, 50, mCores.getPreto()));
        BTN_ZERAR.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(true);
            }
        });

        BotaoCor BTN_0 = mClicavel.criarBotaoCor(new BotaoCor(300, 50, 50, 50, EscalaAQ4.T0));
        BTN_0.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(0);
            }
        });

        BotaoCor BTN_1 = mClicavel.criarBotaoCor(new BotaoCor(400, 50, 50, 50, EscalaAQ4.T1));
        BTN_1.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(100);
            }
        });

        BotaoCor BTN_2 = mClicavel.criarBotaoCor(new BotaoCor(500, 50, 50, 50, EscalaAQ4.T2));
        BTN_2.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(200);
            }
        });

        BotaoCor BTN_3 = mClicavel.criarBotaoCor(new BotaoCor(600, 50, 50, 50, EscalaAQ4.T3));
        BTN_3.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(300);
            }
        });

        BotaoCor BTN_4 = mClicavel.criarBotaoCor(new BotaoCor(700, 50, 50, 50, EscalaAQ4.T4));
        BTN_4.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(400);
            }
        });

        BotaoCor BTN_5 = mClicavel.criarBotaoCor(new BotaoCor(800, 50, 50, 50, EscalaAQ4.T5));
        BTN_5.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(500);
            }
        });

        BotaoCor BTN_6 = mClicavel.criarBotaoCor(new BotaoCor(900, 50, 50, 50, EscalaAQ4.T6));
        BTN_6.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(600);
            }
        });

        BotaoCor BTN_7 = mClicavel.criarBotaoCor(new BotaoCor(1000, 50, 50, 50, EscalaAQ4.T7));
        BTN_7.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(700);
            }
        });

        BotaoCor BTN_8 = mClicavel.criarBotaoCor(new BotaoCor(1100, 50, 50, 50, EscalaAQ4.T8));
        BTN_8.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(800);
            }
        });

        BotaoCor BTN_9 = mClicavel.criarBotaoCor(new BotaoCor(1200, 50, 50, 50, EscalaAQ4.T9));
        BTN_9.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(900);
            }
        });

        BotaoCor BTN_10 = mClicavel.criarBotaoCor(new BotaoCor(1300, 50, 50, 50, EscalaAQ4.T10));
        BTN_10.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(1000);
            }
        });

        BotaoCor BTN_11 = mClicavel.criarBotaoCor(new BotaoCor(1400, 50, 50, 50, EscalaAQ4.T11));
        BTN_11.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(1100);
            }
        });

        BotaoCor BTN_12 = mClicavel.criarBotaoCor(new BotaoCor(1500, 50, 50, 50, EscalaAQ4.T12));
        BTN_12.setAcao(new Acao() {
            @Override
            public void onClique() {
                ACAO_LIMPANTE.set(false);
                VALOR_CORRENTE.set(1200);
            }
        });


    }


    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        mGPS_OK = false;

        if (px >= X0 && py >= Y0 && px < X1 && py < Y1) {
            mGPS_OK = true;
            mGPS_PX = ((px - X0) * 2);
            mGPS_PY = ((py - Y0) * 2) - 5;
        }

        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());

        if (getWindows().getMouse().isClicked()) {

            if (mGPS_OK) {

                if (ACAO_LIMPANTE.get()) {

                    int lx = (mGPS_PX / 2) - 50;
                    int ly = (mGPS_PY / 2) - 50;

                    Retangulo limpar = new Retangulo(lx, ly, 100, 100);

                    Lista<Par<Ponto, Integer>> remover = new Lista<Par<Ponto, Integer>>();

                    for (Par<Ponto, Integer> entao : mRelevo) {

                        int ppx = (entao.getChave().getX() / 2);
                        int ppy = (entao.getChave().getY() / 2);

                        if (limpar.isDentro(ppx, ppy)) {
                            remover.adicionar(entao);
                        }

                    }

                    for (Par<Ponto, Integer> remove : remover) {
                        mRelevo.remover(remove);
                    }

                    AtzumPontosInteiro.SALVAR(mRelevo.toLista(), ARQUIVO_RELEVO);
                } else {
                    mRelevo.item(new Par<Ponto, Integer>(new Ponto(mGPS_PX, mGPS_PY), VALOR_CORRENTE.get()));
                    AtzumPontosInteiro.SALVAR(mRelevo.toLista(), ARQUIVO_RELEVO);
                }


            }

            getWindows().getMouse().liberar();
        }

    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());

        ESCRITOR_NORMAL.setRenderizador(g);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(g);


        g.drawImagem(X0, Y0, mapa);
        mClicavel.onDraw(g);

        g.drawRect(BTN_ZERAR.getX(), BTN_ZERAR.getY(), BTN_ZERAR.getLargura(), BTN_ZERAR.getAltura(), mCores.getBranco());


        if (mGPS_OK) {
            ESCRITOR_NORMAL.escreva(50, 50, "GPS ON");
            ESCRITOR_NORMAL.escreva(50, 70, "X = " + mGPS_PX);
            ESCRITOR_NORMAL.escreva(50, 90, "Y = " + mGPS_PY);
        } else {
            ESCRITOR_NORMAL_VERMELHO.escreva(50, 50, "GPS FAILED");
        }


        for (Par<Ponto, Integer> relevo : mRelevo) {

            Cor eCor = EscalaAQ4.GET_COR(relevo.getValor());

            g.drawCirculoCentralizado_Pintado((relevo.getChave().getX() / 2) + X0, (relevo.getChave().getY() / 2) + Y0, 2, eCor);
        }

        if (ACAO_LIMPANTE.get()) {
            if (mGPS_OK) {

                int lx = (mGPS_PX / 2) + X0;
                int ly = (mGPS_PY / 2) + Y0;


                g.drawRect(lx - 50, ly - 50, 100, 100, mCores.getBranco());
            }
        }


    }
}
