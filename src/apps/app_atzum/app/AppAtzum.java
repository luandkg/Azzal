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
import libs.azzal.AzzalUnico;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Par;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.mockui.Interface.Clicavel;
import libs.tronarko.Tron;
import libs.tronarko.Tronarko;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class AppAtzum extends Cena {

    public BufferedImage mapa_grande = null;
    public BufferedImage mapa_pequeno = null;

    private int X0 = 700;
    private int Y0 = 100;

    private int X1 = 0;
    private int Y1 = 0;


    private Cores mCores;


    private Fonte ESCRITOR_NORMAL;
    private Fonte ESCRITOR_NORMAL_VERMELHO;
    private Fonte ESCRITOR_NORMAL_BRANCO;
    private Fonte ESCRITOR_NORMAL_BRANCO_GRANDE;

    public boolean mGPS_OK = false;
    public int mGPS_PX = 0;
    public int mGPS_PY = 0;

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

    private Lista<Entidade> mInformacoesDasCidades;
    private Lista<Entidade> mInformacoesDasCidadesIndexadas;

    private Entidade mCidade;


    public ArquivoAtzumGeral mArquivoAtzumGeral;
    public ArquivoAtzumTronarko mArquivoAtzumTronarko;


    private GrupoDeBotoesGrandes mGrupoPrincipal;
    private GrupoDeBotoesGrandes mSubComandos;
    private GrupoDeBotoesGrandes mCamadasZoom;

    public MapaZoom mMapaZoom;
    private ClimaWidget mClima;

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

        mapa_grande = mArquivoAtzumGeral.GET_MAPA_DE_RELEVO();
        mapa_pequeno = Efeitos.reduzirMetade(Imagem.getCopia(mapa_grande));

        X1 = X0 + mapa_pequeno.getWidth();
        Y1 = Y0 + mapa_pequeno.getHeight();

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


        mGrupoPrincipal.aplicarCamada("Relevo");
        mCamadasZoom.aplicarCamada("Relevo");


        mCidadeDescritores = new Lista<Par<String, String>>();

        fmt.print(">> Carregando Tronarko : {}", mTronarko);

        Tron t1 = Tronarko.getTronAgora();

      //  mInformacoesDasCidades = mArquivoAtzumTronarko.getCidadesDadosPublicados();
        mInformacoesDasCidadesIndexadas = mArquivoAtzumTronarko.getCidadesDadosPublicadosIndicePorCidade();

        Tron t2 = Tronarko.getTronAgora();

        fmt.print("Gastou :: {}",Tronarko.TRON_DIFERENCA(t1,t2)); // 12 uz

        //ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(mInformacoesDasCidadesIndexadas, 10));


        mClima = new ClimaWidget();

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

            mMapaZoom.update(false);

            int terra_ou_agua = mArquivoAtzumGeral.GET_PLANETA(mGPS_PX, mGPS_PY);
            if (terra_ou_agua > 0) {
                mTerraOuAgua = "TERRA";

                //  int v2_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("regioes.qtt"), mGPS_PX, mGPS_PY);
                //  int v2_sub_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("subregioes.qtt"), mGPS_PX, mGPS_PY);

                int v2_regiao_corrente = mArquivoAtzumGeral.GET_REGIAO(mGPS_PX, mGPS_PY);
                int v2_sub_regiao_corrente = mArquivoAtzumGeral.GET_SUBREGIAO(mGPS_PX, mGPS_PY);

                mRegiaoCorrente = String.valueOf(v2_regiao_corrente) + " :: " + String.valueOf(v2_sub_regiao_corrente);

            } else {
                mTerraOuAgua = "ÁGUA";

                //  int oceano_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("oceanos.qtt"), mGPS_PX, mGPS_PY);
                int oceano_corrente = mArquivoAtzumGeral.GET_OCEANO(mGPS_PX, mGPS_PY);

                if (oceano_corrente > 0) {
                    mOceanoCorrente = oceano_corrente + " - " + mAtzum.GET_OCEANO(oceano_corrente);
                }

            }

            //  mAlturaCorrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"), mGPS_PX, mGPS_PY) + "m";
            mAlturaCorrente = mArquivoAtzumGeral.GET_RELEVO_ALTITUDE(mGPS_PX, mGPS_PY) + "m";

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

        g.drawImagem(X0, Y0, mapa_pequeno);

        mClicavel.onDraw(g);

        if (mGPS_OK) {
            ESCRITOR_NORMAL.escreveLinha(140, 50, 100, "GPS ON", "");

            ESCRITOR_NORMAL.escreveLinha(160, 50, 100, "X", " = " + mGPS_PX);
            ESCRITOR_NORMAL.escreveLinha(180, 50, 100, "Y", " = " + mGPS_PY);


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


    public void procurar_cidade_proxima() {


        mCidadeSelecionada = false;
        mCidadeDescritores.limpar();

        int mais_proxima = Integer.MAX_VALUE;

        for (Ponto cidade : mCidades) {
            int distancia = Espaco2D.distancia_entre_pontos(mGPS_PX, mGPS_PY, cidade.getX(), cidade.getY());
            if (distancia < mais_proxima) {
                mais_proxima = distancia;

                mCidadeSelecionada = true;
                mCidadeSelecionadaX = cidade.getX();
                mCidadeSelecionadaY = cidade.getY();

            }
        }


        if (mCidadeSelecionada) {


            Entidade mCidade  = mArquivoAtzumTronarko.GET_CIDADE_DADOS(mCidadeSelecionadaX + "::" + mCidadeSelecionadaY);

            // mCidade = ENTT.GET_SEMPRE(mInformacoesDasCidades, "Cidade", cidade.getX() + "::" + cidade.getY());
           // mCidade = ENTT.GET_SEMPRE(mInformacoesDasCidades, "CidadePos", mCidadeSelecionadaX + "::" + mCidadeSelecionadaY);

            mCidadeDescritores.adicionar(new Par<String, String>("Nome", mCidade.at("CidadeNome")));
            mCidadeDescritores.adicionar(new Par<String, String>("Posição", mCidadeSelecionadaX + " : " + mCidadeSelecionadaY));

            int v2_regiao_corrente = mArquivoAtzumGeral.GET_REGIAO(mCidadeSelecionadaX, mCidadeSelecionadaY);
            int v2_sub_regiao_corrente = mArquivoAtzumGeral.GET_SUBREGIAO(mCidadeSelecionadaX, mCidadeSelecionadaY);

            String cidade_regiao = String.valueOf(v2_regiao_corrente) + " :: " + String.valueOf(v2_sub_regiao_corrente) + " - " + mAtzum.GET_REGIAO_NOME(v2_regiao_corrente);
            String cidade_altitude = mArquivoAtzumGeral.GET_RELEVO_ALTITUDE(mCidadeSelecionadaX, mCidadeSelecionadaY) + "m";


            mCidadeDescritores.adicionar(new Par<String, String>("Região", cidade_regiao));
            mCidadeDescritores.adicionar(new Par<String, String>("Altitude", cidade_altitude));


            //  fmt.print("{}",mCidade.toTexto());

            if (mCidade.atInt("Oceano_Distancia") <= 15) {
                mCidadeDescritores.adicionar(new Par<String, String>("Tipo", "Litoranea"));
            } else {
                mCidadeDescritores.adicionar(new Par<String, String>("Tipo", "Continental"));
            }

            mCidadeDescritores.adicionar(new Par<String, String>("Oceano", mCidade.at("Oceano_Nome") + " - " + mCidade.at("Oceano_Distancia")));



            mClima.marcarCidade(mCidade);
        } else {
            mClima.retirarCidade();
        }

    }
}
