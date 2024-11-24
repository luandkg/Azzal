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
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.mockui.Interface.Clicavel;

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
    private Entidade mCidade;




    private boolean mClimaMovendo = false;
    private int mClimaMovendoPx = 0;
    private int mClimaMovendoPy = 0;

    private boolean mClimaClicado = false;
    private int mClimaClicadoPx = 0;



    private Unico<String> mCidadeFatoresClimaticos;

    public ArquivoAtzumGeral mArquivoAtzumGeral;
    public ArquivoAtzumTronarko mArquivoAtzumTronarko;



    private GrupoDeBotoesGrandes mGrupoPrincipal;
    private GrupoDeBotoesGrandes mSubComandos;
    private GrupoDeBotoesGrandes mCamadasZoom;

    public MapaZoom mMapaZoom;

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

        mCidadeFatoresClimaticos = new Unico<String>(Strings.IGUALAVEL());

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

        mMapaZoom=new MapaZoom(this);

        mClicavel = new Clicavel();


        AtzumBotoesPrincipais.criar_camadas(mGrupoPrincipal, mSubComandos,this);
        AtzumBotoesPrincipais.criar_camadas_zoom(mCamadasZoom,this);



        mGrupoPrincipal.aplicarCamada("Relevo");
        mCamadasZoom.aplicarCamada("Relevo");


        mCidadeDescritores = new Lista<Par<String, String>>();

        fmt.print(">> Carregando Tronarko : {}", mTronarko);
        mInformacoesDasCidades = mArquivoAtzumTronarko.getCidadesDadosPublicados();


        ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(mInformacoesDasCidades, 10));


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

        // if (getWindows().getMouse().isClicked()) {

        if (mCidadeSelecionada) {

            int temperatura_px_inicio = 50;
            int temperatura_py_inicio = 500;

            Retangulo ret = new Retangulo(temperatura_px_inicio, temperatura_py_inicio, 500, 200);
            if (ret.isDentro(px, py)) {
                mClimaMovendo = true;
                mClimaMovendoPx = px - temperatura_px_inicio;
                mClimaMovendoPy = temperatura_py_inicio + 50;

                if (getWindows().getMouse().isClicked()) {
                    mClimaClicado = true;
                    mClimaClicadoPx = px - temperatura_px_inicio;
                }

            }

        }


        //   }

        mGrupoPrincipal.update(dt, px, py, getWindows().getMouse().isPressed());
        mCamadasZoom.update(dt, px, py, getWindows().getMouse().isPressed());
        mSubComandos.update(dt, px, py, getWindows().getMouse().isPressed());


        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());

        getWindows().getMouse().liberar();
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

        int superarko_selecionado = 0;

        if (mClimaClicado) {
            superarko_selecionado = (mClimaClicadoPx + 1);
            if (superarko_selecionado < 1) {
                superarko_selecionado = 1;
            }
            if (superarko_selecionado > 500) {
                superarko_selecionado = 500;
            }
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

                if (mSubComandos.getQuantidade()>0) {
                    cidade_essa_cor = mCores.getPreto();
                }

                // fmt.print("{} ->> {} :: {}",fc,cidade.toString(),cidade_essa_cor.toString());

                g.drawCirculoCentralizado_Pintado((cidade.getX() / 2) + X0, (cidade.getY() / 2) + Y0, 2, cidade_essa_cor);

            }


        }


        int temperatura_px_original = 50;
        int temperatura_px = temperatura_px_original;

        int temperatura_py = 500;
        int temperatura_py_centro = temperatura_py + 100;

        int temperatura_py_centro_acima = temperatura_py_centro - 20;
        int temperatura_py_centro_abaixo = temperatura_py_centro + 20;

        int temperatura_py_depois = temperatura_py + 220;

        ESCRITOR_NORMAL.escreva(temperatura_px_original, temperatura_py - 65, "MAPA CLIMÁTICO");
        g.drawRect_Pintado(temperatura_px_original, temperatura_py, 500, 200, mCores.getPreto());
        g.drawRect(temperatura_px_original - 10, temperatura_py - 30, 500 + 20, 200 + 60, mCores.getBranco());

        if (mCidadeSelecionada) {

            int descritor_py = 300;
            for (Par<String, String> descritor : mCidadeDescritores) {
                if (!descritor.getChave().isEmpty()) {
                    ESCRITOR_NORMAL.escreveLinha(descritor_py, 40, 150, descritor.getChave(), " = " + descritor.getValor());
                }
                descritor_py += 20;
            }


            descritor_py = 300;

            ESCRITOR_NORMAL.escreva(400, descritor_py, "Fatores Climáticos");
            descritor_py += 30;
            for (Indexado<String> fator_climatico : Indexamento.indexe(mCidadeFatoresClimaticos.toLista())) {
                g.drawRect_Pintado(400, descritor_py, 10, 10, mAtzum.GET_FATOR_CLIMATICO_COR(fator_climatico.get()));
                ESCRITOR_NORMAL.escreveLinha(descritor_py - 5, 400, 420, "", "" + fator_climatico.get());
                descritor_py += 20;
            }

            //  ESCRITOR_NORMAL.escreva(50, 400, "Cidade = " + mCidadeSelecionadaNome);
            //   ESCRITOR_NORMAL.escreva(50, 420, "Local  = " + mCidadeSelecionadaX + " : " + mCidadeSelecionadaY);


            int a_cada = 0;

            boolean umidade_existe_antes = false;
            Ponto umidade_antes = null;
            int umidade_antes_valor = 0;


            for (int s = 1; s <= 500; s++) {

                double temperatura = mCidade.atDouble("T" + s);
                double umidade = mCidade.atDouble("U" + s);

                boolean estaChovendo = mCidade.is("FC" + s, "CHUVA");
                boolean estaTempestadeChovendo = mCidade.is("FC" + s, "TEMPESTADE_CHUVA");
                boolean estaNevando = mCidade.is("FC" + s, "NEVE");
                boolean estaTempestadeNeve = mCidade.is("FC" + s, "TEMPESTADE_NEVE");

                boolean estaOndaDeCalor = mCidade.is("FC" + s, "ONDA_DE_CALOR");
                boolean estaSeca = mCidade.is("FC" + s, "SECA");
                boolean estaSecaExtrema = mCidade.is("FC" + s, "SECA_EXTREMA");
                boolean estaVentania = mCidade.is("FC" + s, "VENTANIA");
                boolean estaTempestadeDeVento = mCidade.is("FC" + s, "TEMPESTADE_VENTO");

                boolean estaTempestade = false;

                if (mCidade.is("FC" + s, "TEMPESTADE_CHUVA")) {
                    estaTempestade = true;
                } else if (mCidade.is("FC" + s, "TEMPESTADE_NEVE")) {
                    estaTempestade = true;
                }

                Cor temp_cor = mCores.getVerde();

                if (temperatura <= 10) {
                    temp_cor = mCores.getAzul();
                }
                if (temperatura >= 30) {
                    temp_cor = mCores.getVermelho();
                }

                // if(a_cada==3) {
                if (estaChovendo) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 60, 3, 3, Atzum.COR_CHUVA);
                }
                if (estaTempestadeChovendo) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 70, 3, 3, Atzum.COR_TEMPESTADE_CHUVA);
                }
                if (estaNevando) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 80, 3, 3, Atzum.COR_NEVE);
                }
                if (estaTempestadeNeve) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 90, 3, 3, Atzum.COR_TEMPESTADE_NEVE);
                }

                if (estaTempestade) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 50, 3, 3, Atzum.COR_TEMPESTADE_VENTO);
                }


                if (estaOndaDeCalor) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 60, 3, 3, Atzum.COR_ONDA_DE_CALOR);
                }
                if (estaSeca) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 70, 3, 3, Atzum.COR_SECA);
                }
                if (estaSecaExtrema) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 80, 3, 3, Atzum.COR_SECA_EXTREMA);
                }

                if (estaVentania) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 50, 3, 3, Atzum.COR_VENTANIA);
                }
                if (estaTempestadeDeVento) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 40, 3, 3, Atzum.COR_TEMPESTADE_VENTO);
                }
                // }


                if (temperatura > 0) {
                    int temperatura_modulo = (int) temperatura;

                    if (a_cada == 3) {
                        g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - temperatura_modulo, 1, temperatura_modulo, temp_cor);
                    }

                } else {
                    int temperatura_modulo = ((int) temperatura) * (-1);

                    if (a_cada == 3) {
                        g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo, 1, temperatura_modulo, temp_cor);
                    }
                }

                int i_umidade = (int) umidade;
                //   g.drawRect_Pintado(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, 3, 3, mCores.getAzul());

                if (i_umidade <= 30) {
                    g.drawPixel(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, mCores.getVermelho());
                } else {
                    g.drawPixel(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, mCores.getAzul());
                }

                if (umidade_existe_antes) {

                    if (umidade_antes_valor <= 30 && i_umidade <= 30) {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getVermelho());
                    } else if (umidade_antes_valor > 30 && i_umidade <= 30) {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getLaranja());
                    } else if (umidade_antes_valor <= 30 && i_umidade > 30) {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getLaranja());
                    } else {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getAzul());
                    }

                }

                umidade_existe_antes = true;
                umidade_antes_valor = i_umidade;
                umidade_antes = new Ponto(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2);


                if (temperatura >= 30) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 4, 1, 9, mCores.getVermelho());
                }
                if (temperatura <= -5) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 4, 1, 9, mCores.getAzul());
                }

                if (temperatura > -5 && temperatura < 30) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 4, 1, 9, mCores.getVerde());
                }

                temperatura_px += 1;
                a_cada += 1;
                if (a_cada == 5) {
                    a_cada = 0;
                }
            }

            g.drawRect_Pintado(temperatura_px_original, temperatura_py_centro - 1, 500, 2, mCores.getBranco());

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 40, 70, "TMin", " = " + mCidade.at("tMin"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 150, 180, "TMax", " = " + mCidade.at("tMax"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 40, 70, "UMin", " = " + mCidade.at("uMin"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 150, 180, "UMax", " = " + mCidade.at("uMax"));

            //ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois+30, 50, 120, "TMax", " = " + mCidade.at("TMax"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 260, 360, "Hiperestação", " = " + mCidade.at("Hiperestacao"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 260, 360, "Vegetação", " = " + mCidade.at("Vegetacao"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 60, 40, 70, "UV" + superarko_selecionado, " = " + mCidade.at("UV" + superarko_selecionado));

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

                g.drawRect_Pintado(1700, py, 25, 25, mAtzum.GET_FATOR_CLIMATICO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }
        }

        int temperatura_px_inicio = 50;

        if (mCidadeSelecionada && mClimaMovendo) {

            int superarko = getSuperarkoPosicao(mClimaMovendoPx);

            int info_py = temperatura_py_depois + 90;

            g.drawRect_Pintado(temperatura_px_inicio + (mClimaMovendoPx - 3), mClimaMovendoPy - 50, 5, 200, mCores.getVermelho());

            g.drawRect_Pintado(20, info_py + 3, 10, 10, mCores.getVermelho());

            render_superarko_info(superarko, g, info_py);

        }

        if (mCidadeSelecionada && mClimaClicado) {

            int superarko = getSuperarkoPosicao(mClimaClicadoPx);

            int info_py = temperatura_py_depois + 110;

            g.drawRect(temperatura_px_inicio + (mClimaClicadoPx - 3), mClimaMovendoPy - 50, 6, 200, mCores.getBranco());

            g.drawRect_Pintado(20, info_py + 3, 10, 10, mCores.getBranco());

            render_superarko_info(superarko, g, info_py);

        }

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


    public int getSuperarkoPosicao(int ePosicaoX) {
        int superarko = (ePosicaoX + 1);
        if (superarko < 1) {
            superarko = 1;
        }
        if (superarko > 500) {
            superarko = 500;
        }
        return superarko;
    }

    public void render_superarko_info(int superarko, Renderizador g, int pos_y) {

        ESCRITOR_NORMAL.escreveLinha(pos_y, 40, 120, "Superarko", " = " + superarko);

        double temperatura = mCidade.atDouble("T" + superarko);
        double umidade = mCidade.atDouble("U" + superarko);
        String fator_climatico = mCidade.at("FC" + superarko);

        ESCRITOR_NORMAL.escreva(220, pos_y, "T = " + fmt.f2(temperatura));
        ESCRITOR_NORMAL.escreva(320, pos_y, "U = " + fmt.f2(umidade));
        ESCRITOR_NORMAL.escreva(420, pos_y, "C = " + fator_climatico);

    }





    public void procurar_cidade_proxima() {


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


                // mCidade = ENTT.GET_SEMPRE(mInformacoesDasCidades, "Cidade", cidade.getX() + "::" + cidade.getY());
                mCidade = ENTT.GET_SEMPRE(mInformacoesDasCidades, "CidadePos", cidade.getX() + "::" + cidade.getY());

                mCidadeDescritores.adicionar(new Par<String, String>("Nome", mCidade.at("CidadeNome")));
                mCidadeDescritores.adicionar(new Par<String, String>("Posição", cidade.getX() + " : " + cidade.getY()));

                int v2_regiao_corrente = mArquivoAtzumGeral.GET_REGIAO(cidade.getX(), cidade.getY());
                int v2_sub_regiao_corrente = mArquivoAtzumGeral.GET_SUBREGIAO(cidade.getX(), cidade.getY());

                String cidade_regiao = String.valueOf(v2_regiao_corrente) + " :: " + String.valueOf(v2_sub_regiao_corrente) + " - " + mAtzum.GET_REGIAO_NOME(v2_regiao_corrente);
                String cidade_altitude = mArquivoAtzumGeral.GET_RELEVO_ALTITUDE(cidade.getX(), cidade.getY()) + "m";


                mCidadeDescritores.adicionar(new Par<String, String>("Região", cidade_regiao));
                mCidadeDescritores.adicionar(new Par<String, String>("Altitude", cidade_altitude));


                //  fmt.print("{}",mCidade.toTexto());

                if (mCidade.atInt("Oceano_Distancia") <= 15) {
                    mCidadeDescritores.adicionar(new Par<String, String>("Tipo", "Litoranea"));
                } else {
                    mCidadeDescritores.adicionar(new Par<String, String>("Tipo", "Continental"));
                }

                mCidadeDescritores.adicionar(new Par<String, String>("Oceano", mCidade.at("Oceano_Nome") + " - " + mCidade.at("Oceano_Distancia")));


                mCidadeFatoresClimaticos.limpar();

                for (int s = 1; s <= 500; s++) {
                    if (Strings.isValida(mCidade.at("FC" + s))) {
                        mCidadeFatoresClimaticos.item(mCidade.at("FC" + s));
                    }
                }


            }
        }

    }
}
