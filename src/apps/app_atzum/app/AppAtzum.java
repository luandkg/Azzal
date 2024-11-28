package apps.app_atzum.app;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.utils.ArquivoAtzumGeral;
import apps.app_atzum.utils.ArquivoAtzumTronarko;
import apps.app_atzum.utils.IntervaloDeValorColorido;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.PreferenciasOrganizadas;
import libs.arquivos.dsvideo.DSVideo;
import libs.azzal.AzzalUnico;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Cronometro;
import libs.entt.Entidade;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.mockui.Interface.Clicavel;
import libs.tronarko.Tron;
import libs.tronarko.Tronarko;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class AppAtzum extends Cena {


    public static void INICIAR() {

        PreferenciasOrganizadas po = new PreferenciasOrganizadas(AtzumCreator.LOGS_GET_ARQUIVO("atzum.dkg"));
        if (po.abrirSeExistir()) {

            int px = (int) po.getDouble("Janela", "PX");
            int py = (int) po.getDouble("Janela", "PY");

            AzzalUnico.unico_posicionado("Mapa Atzum", 2300, 950, new AppAtzum(), px, py);
        } else {
            AzzalUnico.unico("Mapa Atzum", 2300, 950, new AppAtzum());
        }

    }



    private Cores mCores;


    private Fonte ESCRITOR_NORMAL;
    private Fonte ESCRITOR_NORMAL_VERMELHO;
    private Fonte ESCRITOR_NORMAL_BRANCO;
    private Fonte ESCRITOR_NORMAL_BRANCO_GRANDE;



    private String mAlturaCorrente = "";
    private String mTerraOuAgua = "";
    private String mRegiaoCorrente = "";
    private String mOceanoCorrente = "";

    private Atzum mAtzum;
    public Lista<Ponto> mCidades;

    public boolean mCidadeSelecionada = false;
    public int mCidadeSelecionadaX = 0;
    public int mCidadeSelecionadaY = 0;
    private Lista<Par<String, String>> mCidadeDescritores;

    private Clicavel mClicavel;

    private Lista<Entidade> mInformacoesDasCidadesIndexadas;


    public ArquivoAtzumGeral mArquivoAtzumGeral;
    public ArquivoAtzumTronarko mArquivoAtzumTronarko;


    private GrupoDeBotoesGrandes mGrupoPrincipal;
    private GrupoDeBotoesGrandes mSubComandos;
    private GrupoDeBotoesGrandes mCamadasZoom;

    public MapaZoom mMapaZoom;
    private ClimaWidget mClima;

    public VideoEmExecucao mVideoEmExecucao;

    public WidgetMapaVisualizador mWidgetMapaVisualizador;


    @Override
    public void iniciar(Windows eWindows) {
        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(mCores.getVerde(), 10);
        ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(mCores.getVermelho(), 10);
        ESCRITOR_NORMAL_BRANCO = new FonteRunTime(mCores.getBranco(), 10);
        ESCRITOR_NORMAL_BRANCO_GRANDE = new FonteRunTime(mCores.getBranco(), 30);

        String mTronarko = "7000";

        mArquivoAtzumGeral = new ArquivoAtzumGeral();
        mArquivoAtzumTronarko = new ArquivoAtzumTronarko(mTronarko);


     mWidgetMapaVisualizador = new WidgetMapaVisualizador(mArquivoAtzumGeral.GET_MAPA_DE_RELEVO(),Efeitos.reduzirMetade(Imagem.getCopia(mArquivoAtzumGeral.GET_MAPA_DE_RELEVO())));


        mAtzum = new Atzum();
        mCidades = Atzum.GET_CIDADES();


        mGrupoPrincipal = new GrupoDeBotoesGrandes(50, 30);
        mGrupoPrincipal.setSinalizador(BotaoSinalizador.LATERAL_DIREITA);
        mGrupoPrincipal.setAfastamentoX(150);

        mSubComandos = new GrupoDeBotoesGrandes(800, 850);
        mSubComandos.setSinalizador(BotaoSinalizador.ACIMA_DIRETA);
        mSubComandos.setAfastamentoX(100);

        mCamadasZoom = new GrupoDeBotoesGrandes(1900, 550);
        mCamadasZoom.setSinalizador(BotaoSinalizador.LATERAL_DIREITA);
        mCamadasZoom.setAfastamentoX(50);
        mCamadasZoom.setTamanho(20);
        mCamadasZoom.exibirTexto(false);

        mMapaZoom = new MapaZoom(this);

        mClicavel = new Clicavel();


        AtzumBotoesPrincipais.criar_camadas(mGrupoPrincipal, mSubComandos, this);
        AtzumBotoesPrincipais.criar_camadas_zoom(mCamadasZoom, this);

        mClima = new ClimaWidget();
        mVideoEmExecucao = new VideoEmExecucao();

        mGrupoPrincipal.aplicarCamada("Relevo");
        mCamadasZoom.aplicarCamada("Relevo");


        mCidadeDescritores = new Lista<Par<String, String>>();

        fmt.print(">> Carregando Tronarko : {}", mTronarko);

        Tron t1 = Tronarko.getTronAgora();

        //  mInformacoesDasCidades = mArquivoAtzumTronarko.getCidadesDadosPublicados();
        mInformacoesDasCidadesIndexadas = mArquivoAtzumTronarko.getCidadesDadosPublicadosIndicePorCidade();

        Tron t2 = Tronarko.getTronAgora();

        fmt.print("Gastou :: {}", Tronarko.TRON_DIFERENCA(t1, t2)); // 12 uz

        //ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(mInformacoesDasCidadesIndexadas, 10));



    }





    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        mAlturaCorrente = "";
        mTerraOuAgua = "";
        mRegiaoCorrente = "";
        mOceanoCorrente = "";

        if(mVideoEmExecucao.isExecutando()){
            mVideoEmExecucao.update();
        }



        if (mWidgetMapaVisualizador.isDentro(px,py)) {


            mMapaZoom.update(false);

            int terra_ou_agua = mArquivoAtzumGeral.GET_PLANETA(mWidgetMapaVisualizador.getGPS_PX(), mWidgetMapaVisualizador.getGPS_PY());

            mTerraOuAgua = Portugues.VALIDAR(terra_ou_agua>0,"TERRA","ÁGUA");

            if (terra_ou_agua > 0) {

                int regiao_corrente = mArquivoAtzumGeral.GET_REGIAO(mWidgetMapaVisualizador.getGPS_PX(), mWidgetMapaVisualizador.getGPS_PY());
                int sub_regiao_corrente = mArquivoAtzumGeral.GET_SUBREGIAO(mWidgetMapaVisualizador.getGPS_PX(), mWidgetMapaVisualizador.getGPS_PY());

                mRegiaoCorrente = String.valueOf(regiao_corrente) + " :: " + String.valueOf(sub_regiao_corrente);

            } else {

                int oceano_corrente = mArquivoAtzumGeral.GET_OCEANO(mWidgetMapaVisualizador.getGPS_PX(), mWidgetMapaVisualizador.getGPS_PY());

                if (oceano_corrente > 0) {
                    mOceanoCorrente = oceano_corrente + " - " + mAtzum.GET_OCEANO(oceano_corrente);
                }

            }

            mAlturaCorrente = mArquivoAtzumGeral.GET_RELEVO_ALTITUDE(mWidgetMapaVisualizador.getGPS_PX(), mWidgetMapaVisualizador.getGPS_PY()) + "m";

            if (getWindows().getMouse().isClicked()) {

                double lx = getWindows().getLocationOnScreen().getX();
                double ly = getWindows().getLocationOnScreen().getY();

                PreferenciasOrganizadas po = new PreferenciasOrganizadas(AtzumCreator.LOGS_GET_ARQUIVO("atzum.dkg"));
                po.abrirSeExistir();
                po.setDouble("Janela", "PX", lx);
                po.setDouble("Janela", "PY", ly);
                po.salvar();


                // AREA CLIMATICA


                if (Strings.isIgual(mTerraOuAgua, "TERRA")) {
                    procurar_cidade_proxima();
                }

                mMapaZoom.update(false);

            }


        }


        if (getWindows().getTeclado().foiPressionado(KeyEvent.VK_ESCAPE)) {
            mCidadeSelecionada = false;
            mClima.retirarCidade();
        }


        // if (getWindows().getMouse().isClicked()) {

        if (mCidadeSelecionada) {
            mClima.update(px, py, getWindows().getMouse().isClicked());
        }


        //   }

        mGrupoPrincipal.update(dt, px, py, getWindows().getMouse().isPressed());
        mCamadasZoom.update(dt, px, py, getWindows().getMouse().isPressed());
        mSubComandos.update(dt, px, py, getWindows().getMouse().isPressed());


        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());


        getWindows().getMouse().liberar();
        getWindows().getTeclado().limpar();

    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());

        ESCRITOR_NORMAL.setRenderizador(g);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(g);
        ESCRITOR_NORMAL_BRANCO.setRenderizador(g);
        ESCRITOR_NORMAL_BRANCO_GRANDE.setRenderizador(g);

        mWidgetMapaVisualizador.render(g);


        if (mVideoEmExecucao.isExibindo()) {

            g.drawImagem(mWidgetMapaVisualizador.getPosX(), mWidgetMapaVisualizador.getPosY(), mVideoEmExecucao.getImagemCorrente());

            int info_px = mWidgetMapaVisualizador.getPosX();
            int info_py = mWidgetMapaVisualizador.getPosY();


            ESCRITOR_NORMAL_BRANCO_GRANDE.escreva(info_px - 130, info_py + 100, mVideoEmExecucao.getLargura() + " vs " + mVideoEmExecucao.getAltura());
            ESCRITOR_NORMAL_BRANCO_GRANDE.escreva(info_px - 130, info_py + 150, mVideoEmExecucao.getFrameCorrente() + " frames de " + mVideoEmExecucao.getVideoQuadrosTotal());
            ESCRITOR_NORMAL_BRANCO_GRANDE.escreva(info_px - 130, info_py + 200, mVideoEmExecucao.getVideoDuracao());

            if (mVideoEmExecucao.getFrameCorrente() > 0 && mVideoEmExecucao.getFrameCorrente() <= 500) {
                if (mClima.temCidade()) {
                    mClima.marcarSuperarko(mVideoEmExecucao.getFrameCorrente());
                }
            }

            mVideoEmExecucao.verificarFim();


        }


        mClicavel.onDraw(g);

        if (mWidgetMapaVisualizador.isGPS_ON()) {
            ESCRITOR_NORMAL.escreveLinha(140, 50, 100, "GPS ON", "");

            ESCRITOR_NORMAL.escreveLinha(160, 50, 100, "X", " = " + mWidgetMapaVisualizador.getGPS_PX());
            ESCRITOR_NORMAL.escreveLinha(180, 50, 100, "Y", " = " + mWidgetMapaVisualizador.getGPS_PY());


            ESCRITOR_NORMAL.escreveLinha(220, 50, 150, "Local", " = " + mTerraOuAgua);
            g.drawRect_Pintado(30, 220 + 3, 10, 10, mCores.getBranco());

            if (mTerraOuAgua.contentEquals("TERRA")) {
                ESCRITOR_NORMAL.escreveLinha(240, 50, 150, "Altitude", " = " + mAlturaCorrente);
                g.drawRect_Pintado(30, 240 + 3, 10, 10, mCores.getBranco());
            } else if (mTerraOuAgua.contentEquals("ÁGUA")) {
                ESCRITOR_NORMAL.escreveLinha(240, 50, 150, "Profundidade", " = " + mAlturaCorrente);
                g.drawRect_Pintado(30, 240 + 3, 10, 10, mCores.getBranco());
            }

            if (!mRegiaoCorrente.isEmpty()) {
                ESCRITOR_NORMAL.escreveLinha(260, 50, 150, "Região", " = " + mRegiaoCorrente);
                g.drawRect_Pintado(30, 260 + 3, 10, 10, mCores.getBranco());
            }

            if (!mOceanoCorrente.isEmpty()) {
                ESCRITOR_NORMAL.escreveLinha(260, 50, 150, "Oceano", " = " + mOceanoCorrente);
                g.drawRect_Pintado(30, 260 + 3, 10, 10, mCores.getBranco());
            }

        } else {
            ESCRITOR_NORMAL_VERMELHO.escreva(50, 140, "GPS FAILED");
        }


        int X0 =mWidgetMapaVisualizador.getPosX();
        int Y0 =mWidgetMapaVisualizador.getPosY();


        for (Ponto cidade : mCidades) {

            boolean cidade_selecionada = false;

            if (mCidadeSelecionada) {
                if (mCidadeSelecionadaX == cidade.getX() && mCidadeSelecionadaY == cidade.getY()) {
                    cidade_selecionada = true;
                }
            }

            if (cidade_selecionada) {
                g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 5, mCores.getVerde());
                g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 2, mCores.getAzul());
            } else {

                Cor cidade_essa_cor = mCores.getBranco();

                if (mSubComandos.getQuantidade() > 0) {
                    cidade_essa_cor = mCores.getPreto();
                }

                // fmt.print("{} ->> {} :: {}",fc,cidade.toString(),cidade_essa_cor.toString());

                g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 2, cidade_essa_cor);

            }


        }


        g.drawCirculoCentralizado_Pintado(20, 915, 10, mCores.getVermelho());
        ESCRITOR_NORMAL.escreva(35, 915 - 8, mGrupoPrincipal.getSelecionado());

        mGrupoPrincipal.render(g, ESCRITOR_NORMAL_BRANCO);
        mCamadasZoom.render(g, ESCRITOR_NORMAL_BRANCO);
        mSubComandos.render(g, ESCRITOR_NORMAL_BRANCO);


        if (mGrupoPrincipal.getSelecionado().contentEquals("Regiões")) {

            int py = 100;
            for (String modelo_corrente : Atzum.GET_REGIOES()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_REGIAO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }

        } else if (mGrupoPrincipal.getSelecionado().contentEquals("Modelo Climático")) {

            int py = 100;
            for (String modelo_corrente : Atzum.GET_MODELO_CLIMATICO()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_MODELO_CLIMATICO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }

        } else if (mGrupoPrincipal.getSelecionado().contentEquals("Modelo Vegetação")) {

            int py = 100;
            for (String modelo_corrente : Atzum.GET_MODELO_VEGETACAO()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_MODELO_VEGETACAO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }

        } else if (mGrupoPrincipal.getSelecionado().contentEquals("Umidade")) {

            int py = 100;
            for (IntervaloDeValorColorido modelo_corrente : Atzum.GET_UMIDADE_INTERVALOS_COLORIDOS()) {

                g.drawRect_Pintado(1700, py, 25, 25, modelo_corrente.getCor());
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente.getMinimo() + " a " + modelo_corrente.getMaximo());

                py += 30;
            }
        } else if (mGrupoPrincipal.getSelecionado().contentEquals("Temperatura")) {

            int py = 100;
            for (IntervaloDeValorColorido modelo_corrente : Atzum.GET_TEMPERATURA_INTERVALOS_COLORIDOS()) {

                g.drawRect_Pintado(1700, py, 25, 25, modelo_corrente.getCor());
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente.getMinimo() + " a " + modelo_corrente.getMaximo());

                py += 30;
            }
        } else if (mGrupoPrincipal.getSelecionado().contentEquals("MassaDeAr")) {

            int py = 100;
            for (String modelo_corrente : mAtzum.GET_MASSA_DE_AR_TIPOS()) {

                g.drawRect_Pintado(1700, py, 25, 25, mAtzum.GET_MASSA_DE_AR_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }
        } else if (mGrupoPrincipal.getSelecionado().contentEquals("FatorClimatico")) {

            int py = 100;
            for (String modelo_corrente : mAtzum.GET_FATORES_CLIMATICOS()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_FATOR_CLIMATICO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }
        }

        int descritor_py = 300;

        if (mCidadeSelecionada) {

            for (Par<String, String> descritor : mCidadeDescritores) {
                if (!descritor.getChave().isEmpty()) {
                    ESCRITOR_NORMAL.escreveLinha(descritor_py, 40, 150, descritor.getChave(), " = " + descritor.getValor());
                }
                descritor_py += 20;
            }

        }

        mClima.render(g, descritor_py);

        mMapaZoom.render(g);


        g.drawRect(1900, 600, 300, 300, mCores.getVermelho());


    }




    public void procurar_cidade_proxima() {


        mCidadeSelecionada = false;
        mCidadeDescritores.limpar();

        int mais_proxima = Integer.MAX_VALUE;

        for (Ponto cidade : mCidades) {
            int distancia = Espaco2D.distancia_entre_pontos(mWidgetMapaVisualizador.getGPS_PX(), mWidgetMapaVisualizador.getGPS_PY(), cidade.getX(), cidade.getY());
            if (distancia < mais_proxima) {
                mais_proxima = distancia;

                mCidadeSelecionada = true;
                mCidadeSelecionadaX = cidade.getX();
                mCidadeSelecionadaY = cidade.getY();

            }
        }


        if (mCidadeSelecionada) {


            Entidade mCidade = mArquivoAtzumTronarko.GET_CIDADE_DADOS(mCidadeSelecionadaX + "::" + mCidadeSelecionadaY);

            // mCidade = ENTT.GET_SEMPRE(mInformacoesDasCidades, "Cidade", cidade.getX() + "::" + cidade.getY());
            // mCidade = ENTT.GET_SEMPRE(mInformacoesDasCidades, "CidadePos", mCidadeSelecionadaX + "::" + mCidadeSelecionadaY);

            mCidadeDescritores.adicionar(new Par<String, String>("Nome", mCidade.at("CidadeNome")));
            mCidadeDescritores.adicionar(new Par<String, String>("Posição", mCidadeSelecionadaX + " : " + mCidadeSelecionadaY));

            int regiao_corrente = mArquivoAtzumGeral.GET_REGIAO(mCidadeSelecionadaX, mCidadeSelecionadaY);
            int sub_regiao_corrente = mArquivoAtzumGeral.GET_SUBREGIAO(mCidadeSelecionadaX, mCidadeSelecionadaY);

            String cidade_regiao = String.valueOf(regiao_corrente) + " :: " + String.valueOf(sub_regiao_corrente) + " - " + mAtzum.GET_REGIAO_NOME(regiao_corrente);
            String cidade_altitude = mArquivoAtzumGeral.GET_RELEVO_ALTITUDE(mCidadeSelecionadaX, mCidadeSelecionadaY) + "m";


            mCidadeDescritores.adicionar(new Par<String, String>("Região", cidade_regiao));
            mCidadeDescritores.adicionar(new Par<String, String>("Altitude", cidade_altitude));


            mCidadeDescritores.adicionar(new Par<String, String>("Tipo", Portugues.VALIDAR(mCidade.atInt("Oceano_Distancia") <= 15,"Litoranea","Continental")));
            mCidadeDescritores.adicionar(new Par<String, String>("Oceano", mCidade.at("Oceano_Nome") + " - " + mCidade.at("Oceano_Distancia")));


            mClima.marcarCidade(mCidade);
        } else {
            mClima.retirarCidade();
        }

    }


}
