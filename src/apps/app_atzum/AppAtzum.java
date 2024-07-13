package apps.app_atzum;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.QTT;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Efeitos;
import libs.luan.Lista;
import libs.luan.Par;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AppAtzum extends Cena {

    private BufferedImage mapa = null;

    private int X0 = 500;
    private int Y0 = 100;

    private int X1 = 0;
    private int Y1 = 0;


    private Cores mCores;


    private Fonte ESCRITOR_NORMAL;
    private Fonte ESCRITOR_NORMAL_VERMELHO;

    private boolean mGPS_OK = false;
    private int mGPS_PX = 0;
    private int mGPS_PY = 0;

    private String mAlturaCorrente = "";
    private String mTerraOuAgua = "";
    private String mRegiaoCorrente = "";
    private String mOceanoCorrente = "";

    private Atzum mAtzum;
    private Lista<Ponto> mCidades;

    private boolean mCidadeSelecionada = false;
    private int mCidadeSelecionadaX = 0;
    private int mCidadeSelecionadaY = 0;
    private Lista<Par<String,String>> mCidadeDescritores;

    private Clicavel mClicavel;

    @Override
    public void iniciar(Windows eWindows) {
        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(Cor.getRGB(Color.GREEN), 10);
        ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(Cor.getRGB(Color.RED), 10);

        mapa = AtzumCreator.GET_MAPA_DE_RELEVO();

        //Renderizador render2 = new Renderizador(mapa);
        //  mapa = render2.toImagemSemAlfa();

        mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

        X1 = X0 + mapa.getWidth();
        Y1 = Y0 + mapa.getHeight();

        mAtzum = new Atzum();
        mCidades = mAtzum.GET_CIDADES();

        mClicavel = new Clicavel();

        BotaoCor BTN_MAPA_RELEVO = mClicavel.criarBotaoCor(new BotaoCor(300, 30, 50, 50, mCores.getVermelho()));
        BTN_MAPA_RELEVO.setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa = AtzumCreator.GET_MAPA_DE_RELEVO();
                mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);


            }
        });

        BotaoCor BTN_MAPA_REGIOES = mClicavel.criarBotaoCor(new BotaoCor(400, 30, 50, 50, mCores.getLaranja()));
        BTN_MAPA_REGIOES.setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa = AtzumCreator.GET_MAPA_DE_REGIOES();
                mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

            }
        });

        BotaoCor BTN_MAPA_OCEANOS = mClicavel.criarBotaoCor(new BotaoCor(500, 30, 50, 50, mCores.getAzul()));
        BTN_MAPA_OCEANOS.setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa = AtzumCreator.GET_MAPA_DE_OCEANOS();
                mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

            }
        });

        BotaoCor BTN_MAPA_VEGETACAO = mClicavel.criarBotaoCor(new BotaoCor(600, 30, 50, 50, mCores.getAzul()));
        BTN_MAPA_VEGETACAO.setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa = AtzumCreator.GET_MAPA_VEGETACAO();
                mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

            }
        });

         mCidadeDescritores = new Lista<Par<String,String>>();
    }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        mGPS_OK = false;
        mAlturaCorrente = "";
        mTerraOuAgua = "";
        mRegiaoCorrente = "";
        mOceanoCorrente = "";


        if (px >= X0 && py >= Y0 && px < X1 && py < Y1) {
            mGPS_OK = true;
            mGPS_PX = (px - X0) * 2;
            mGPS_PY = (py - Y0) * 2;

            int terra_ou_agua = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("planeta.qtt"), mGPS_PX, mGPS_PY);
            if (terra_ou_agua > 0) {
                mTerraOuAgua = "TERRA";

                int regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("regioes.qtt"), mGPS_PX, mGPS_PY);
                if (regiao_corrente > 0) {
                    mRegiaoCorrente = regiao_corrente + " região";
                }

                int v2_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("regioes_v2.qtt"), mGPS_PX, mGPS_PY);
                int v2_sub_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("subregioes_v2.qtt"), mGPS_PX, mGPS_PY);

                mRegiaoCorrente=String.valueOf(v2_regiao_corrente) + " :: "+String.valueOf(v2_sub_regiao_corrente);

            } else {
                mTerraOuAgua = "ÁGUA";

                int oceano_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("oceanos.qtt"), mGPS_PX, mGPS_PY);
                if (oceano_corrente > 0) {
                    mOceanoCorrente = oceano_corrente + " - " + mAtzum.GET_OCEANO(oceano_corrente);
                }

            }

            mAlturaCorrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"), mGPS_PX, mGPS_PY) + "m";

            if (getWindows().getMouse().isClicked()) {

                mCidadeSelecionada = false;
mCidadeDescritores.limpar();

                int mais_proxima = Integer.MAX_VALUE;

                for (Ponto cidade : mCidades) {
                    int distancia = Espaco2D.distancia_entre_pontos(mGPS_PX, mGPS_PY, cidade.getX(), cidade.getY());
                    if (distancia < mais_proxima) {
                        mais_proxima = distancia;

                        mCidadeDescritores.limpar();

                        mCidadeSelecionada = true;
                        mCidadeSelecionadaX = cidade.getX();
                        mCidadeSelecionadaY = cidade.getY();

                        mCidadeDescritores.adicionar(new Par<String,String>("Nome",""));
                        mCidadeDescritores.adicionar(new Par<String,String>("Posição",cidade.getX() + " : "+cidade.getY()));

                        int v2_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("regioes_v2.qtt"), cidade.getX(), cidade.getY());
                        int v2_sub_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("subregioes_v2.qtt"), cidade.getX(), cidade.getY());

                        String cidade_regiao=String.valueOf(v2_regiao_corrente) + " :: "+String.valueOf(v2_sub_regiao_corrente);
                        String cidade_altitude = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"), cidade.getX(), cidade.getY()) + "m";


                        mCidadeDescritores.adicionar(new Par<String,String>("Região",cidade_regiao));
                        mCidadeDescritores.adicionar(new Par<String,String>("Altitude",cidade_altitude));

                        mCidadeDescritores.adicionar(new Par<String,String>("",""));
                        mCidadeDescritores.adicionar(new Par<String,String>("",""));


                      Lista<Entidade> estacoes_termicas=  ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/complexo/tronarko_estacoes.entts"));

                     // ENTT.EXIBIR_TABELA(estacoes_termicas);

                      for(Entidade estacao : ENTT.COLETAR(estacoes_termicas,"Cidade",cidade.getX() + "::"+cidade.getY())){
                          mCidadeDescritores.adicionar(new Par<String,String>("Estação",estacao.at("Estacao") + " - "+estacao.at("Inicio") + " : "+estacao.at("Fim")));
                      }


                    }
                }

            }


        }

        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());

        getWindows().getMouse().liberar();
    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());

        ESCRITOR_NORMAL.setRenderizador(g);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(g);


        g.drawImagem(X0, Y0, mapa);

        mClicavel.onDraw(g);

        if (mGPS_OK) {
            ESCRITOR_NORMAL.escreveLinha(100,50, 100, "GPS ON","");

            ESCRITOR_NORMAL.escreveLinha(120,50, 100, "X"," = "+mGPS_PX);
            ESCRITOR_NORMAL.escreveLinha(140,50, 100, "Y"," = "+mGPS_PY);

            ESCRITOR_NORMAL.escreveLinha(220,50, 150, "Local"," = "+mTerraOuAgua);


            if (mTerraOuAgua.contentEquals("TERRA")) {
                ESCRITOR_NORMAL.escreveLinha(240,50, 150, "Altitude"," = "+mAlturaCorrente);
            } else if (mTerraOuAgua.contentEquals("ÁGUA")) {
                ESCRITOR_NORMAL.escreveLinha(240,50, 150, "Profundidade"," = "+mAlturaCorrente);
            }

            if (!mRegiaoCorrente.isEmpty()) {
                ESCRITOR_NORMAL.escreveLinha(260,50, 150, "Região"," = "+mRegiaoCorrente);
            }

            if (!mOceanoCorrente.isEmpty()) {
                ESCRITOR_NORMAL.escreveLinha(260,50, 150, "Oceano"," = "+mOceanoCorrente);
            }

        } else {
            ESCRITOR_NORMAL_VERMELHO.escreva(50, 100, "GPS FAILED");
        }

        for (Ponto cidade : mCidades) {
            if (mCidadeSelecionada) {
                if (mCidadeSelecionadaX == cidade.getX() && mCidadeSelecionadaY == cidade.getY()) {

                    g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 5, mCores.getVerde());
                    g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 2, mCores.getAzul());

                } else {
                    g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 2, mCores.getAmarelo());
                }
            } else {
                g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 2, mCores.getAmarelo());
            }
        }


        if (mCidadeSelecionada) {

            int descritor_py = 400;
            for(Par<String,String> descritor : mCidadeDescritores){
                if(descritor.getChave().length()>0){
                    ESCRITOR_NORMAL.escreveLinha(descritor_py,50, 150, descritor.getChave()," = "+descritor.getValor());
                }
                descritor_py+=20;
            }

          //  ESCRITOR_NORMAL.escreva(50, 400, "Cidade = " + mCidadeSelecionadaNome);
         //   ESCRITOR_NORMAL.escreva(50, 420, "Local  = " + mCidadeSelecionadaX + " : " + mCidadeSelecionadaY);

        }


    }
}
