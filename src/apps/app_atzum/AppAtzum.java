package apps.app_atzum;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.utils.ArquivoAtzumGeral;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.PreferenciasOrganizadas;
import libs.arquivos.QTT;
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
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AppAtzum extends Cena {

    private BufferedImage mapa_pequeno = null;
    private BufferedImage mapa_grande = null;

    private int X0 = 700;
    private int Y0 = 100;

    private int X1 = 0;
    private int Y1 = 0;


    private Cores mCores;


    private Fonte ESCRITOR_NORMAL;
    private Fonte ESCRITOR_NORMAL_VERMELHO;
    private Fonte ESCRITOR_NORMAL_BRANCO;

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
    private Lista<Par<String, String>> mCidadeDescritores;

    private Clicavel mClicavel;
    private Lista<Entidade> mInformacoesDasCidades;
    private Entidade mCidade;

    private Lista<Par<String, BotaoCor>> mCamadas;
    private int mCamadaPXInicio = 50;
    private int mCamadaPX = 0;
    private String mCamada = "";

    private Lista<Par<String, BotaoCor>> mCamadasZoom;
    private int mCamadaZoomPXInicio = 1900;
    private int mCamadaZoomPX = 0;
    private String mCamadaZoom = "";


    private boolean mClimaMovendo = false;
    private int mClimaMovendoPx = 0;
    private int mClimaMovendoPy = 0;

    private boolean mClimaClicado = false;
    private int mClimaClicadoPx = 0;


    private BufferedImage mapa_drone = null;
    private boolean drone_ok = false;
    private Renderizador render_drone;

    private Unico<String> mCidadeFatoresClimaticos;
    private ArquivoAtzumGeral mArquivoAtzumGeral;

    @Override
    public void iniciar(Windows eWindows) {
        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(mCores.getVerde(), 10);
        ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(mCores.getVermelho(), 10);
        ESCRITOR_NORMAL_BRANCO = new FonteRunTime(mCores.getBranco(), 10);


        mArquivoAtzumGeral = new ArquivoAtzumGeral();

        mapa_grande = mArquivoAtzumGeral.GET_MAPA_DE_RELEVO();
        mapa_pequeno = Efeitos.reduzirMetade(Imagem.getCopia(mapa_grande));

        X1 = X0 + mapa_pequeno.getWidth();
        Y1 = Y0 + mapa_pequeno.getHeight();

        mAtzum = new Atzum();
        mCidades = Atzum.GET_CIDADES();

        mCidadeFatoresClimaticos = new Unico<String>(Strings.IGUALAVEL());

        mClicavel = new Clicavel();

        criar_camadas();
        criar_camadas_zoom();

        aplicarCamada("Relevo");
        aplicarZoomCamada("Relevo");

        mCidadeDescritores = new Lista<Par<String, String>>();

        mInformacoesDasCidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado.entts"));

        mapa_drone = Imagem.criarEmBranco(300, 300);
        render_drone = new Renderizador(mapa_drone);

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

            drone_update(false);

            int terra_ou_agua = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("planeta.qtt"), mGPS_PX, mGPS_PY);
            if (terra_ou_agua > 0) {
                mTerraOuAgua = "TERRA";

                int regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("regioes.qtt"), mGPS_PX, mGPS_PY);
                if (regiao_corrente > 0) {
                    mRegiaoCorrente = regiao_corrente + " região";
                }

                int v2_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("regioes.qtt"), mGPS_PX, mGPS_PY);
                int v2_sub_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("subregioes.qtt"), mGPS_PX, mGPS_PY);

                mRegiaoCorrente = String.valueOf(v2_regiao_corrente) + " :: " + String.valueOf(v2_sub_regiao_corrente);

            } else {
                mTerraOuAgua = "ÁGUA";

                int oceano_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("oceanos.qtt"), mGPS_PX, mGPS_PY);
                if (oceano_corrente > 0) {
                    mOceanoCorrente = oceano_corrente + " - " + mAtzum.GET_OCEANO(oceano_corrente);
                }

            }

            mAlturaCorrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"), mGPS_PX, mGPS_PY) + "m";

            if (getWindows().getMouse().isClicked()) {

                double lx = getWindows().getLocationOnScreen().getX();
                double ly = getWindows().getLocationOnScreen().getY();

                PreferenciasOrganizadas po = new PreferenciasOrganizadas(AtzumCreator.LOGS_GET_ARQUIVO("atzum.dkg"));
                po.abrirSeExistir();
                po.setDouble("Janela", "PX", lx);
                po.setDouble("Janela", "PY", ly);
                po.salvar();


                // AREA CLIMATICA


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


                        mCidade = ENTT.GET_SEMPRE(mInformacoesDasCidades, "Cidade", cidade.getX() + "::" + cidade.getY());

                        mCidadeDescritores.adicionar(new Par<String, String>("Nome", mCidade.at("Nome")));
                        mCidadeDescritores.adicionar(new Par<String, String>("Posição", cidade.getX() + " : " + cidade.getY()));

                        int v2_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("regioes.qtt"), cidade.getX(), cidade.getY());
                        int v2_sub_regiao_corrente = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("subregioes.qtt"), cidade.getX(), cidade.getY());

                        String cidade_regiao = String.valueOf(v2_regiao_corrente) + " :: " + String.valueOf(v2_sub_regiao_corrente) + " - " + mAtzum.GET_REGIAO_NOME(v2_regiao_corrente);
                        String cidade_altitude = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("relevo.qtt"), cidade.getX(), cidade.getY()) + "m";


                        mCidadeDescritores.adicionar(new Par<String, String>("Região", cidade_regiao));
                        mCidadeDescritores.adicionar(new Par<String, String>("Altitude", cidade_altitude));


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

                drone_update(false);

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


        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());

        getWindows().getMouse().liberar();
    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());

        ESCRITOR_NORMAL.setRenderizador(g);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(g);
        ESCRITOR_NORMAL_BRANCO.setRenderizador(g);

        g.drawImagem(X0, Y0, mapa_pequeno);

        mClicavel.onDraw(g);

        if (mGPS_OK) {
            ESCRITOR_NORMAL.escreveLinha(100, 50, 100, "GPS ON", "");

            ESCRITOR_NORMAL.escreveLinha(120, 50, 100, "X", " = " + mGPS_PX);
            ESCRITOR_NORMAL.escreveLinha(140, 50, 100, "Y", " = " + mGPS_PY);



            ESCRITOR_NORMAL.escreveLinha(220, 50, 150, "Local", " = " + mTerraOuAgua);
            g.drawRect_Pintado(30, 220+3, 10,10, mCores.getBranco());

            if (mTerraOuAgua.contentEquals("TERRA")) {
                ESCRITOR_NORMAL.escreveLinha(240, 50, 150, "Altitude", " = " + mAlturaCorrente);
                g.drawRect_Pintado(30, 240+3, 10,10, mCores.getBranco());
            } else if (mTerraOuAgua.contentEquals("ÁGUA")) {
                ESCRITOR_NORMAL.escreveLinha(240, 50, 150, "Profundidade", " = " + mAlturaCorrente);
                g.drawRect_Pintado(30, 240+3, 10,10, mCores.getBranco());
            }

            if (!mRegiaoCorrente.isEmpty()) {
                ESCRITOR_NORMAL.escreveLinha(260, 50, 150, "Região", " = " + mRegiaoCorrente);
                g.drawRect_Pintado(30, 260+3, 10,10, mCores.getBranco());
            }

            if (!mOceanoCorrente.isEmpty()) {
                ESCRITOR_NORMAL.escreveLinha(260, 50, 150, "Oceano", " = " + mOceanoCorrente);
                g.drawRect_Pintado(30, 260+3, 10,10, mCores.getBranco());
            }

        } else {
            ESCRITOR_NORMAL_VERMELHO.escreva(50, 100, "GPS FAILED");
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

                String fc = "";

                if (mClimaClicado && superarko_selecionado > 0) {

                    Entidade cidade_essa = ENTT.GET_SEMPRE(mInformacoesDasCidades, "Cidade", cidade.getX() + "::" + cidade.getY());

                    fc = cidade_essa.at("FC" + superarko_selecionado);

                    cidade_essa_cor = mAtzum.GET_FATOR_CLIMATICO_COR(fc);

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
                g.drawRect_Pintado(400,descritor_py,10,10,mAtzum.GET_FATOR_CLIMATICO_COR(fator_climatico.get()));
                ESCRITOR_NORMAL.escreveLinha(descritor_py-5, 400, 420,"", "" + fator_climatico.get());
                descritor_py += 20;
            }

            //  ESCRITOR_NORMAL.escreva(50, 400, "Cidade = " + mCidadeSelecionadaNome);
            //   ESCRITOR_NORMAL.escreva(50, 420, "Local  = " + mCidadeSelecionadaX + " : " + mCidadeSelecionadaY);


            int a_cada = 0;

            boolean umidade_existe_antes = false;
            Ponto umidade_antes = null;
            int umidade_antes_valor=0;


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
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 60, 3, 3, mCores.getAzul());
                }
                if (estaTempestadeChovendo) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 70, 3, 3, mCores.getAzul());
                }
                if (estaNevando) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 80, 3, 3, mCores.getCinza());
                }
                if (estaTempestadeNeve) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 90, 3, 3, mCores.getCinza());
                }
                if (estaTempestade) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 50, 3, 3, mCores.getVerde());
                }


                if (estaOndaDeCalor) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 60, 3, 3, mCores.getAmarelo());
                }
                if (estaSeca) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 70, 3, 3, mCores.getVermelho());
                }
                if (estaSecaExtrema) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 80, 3, 3, mCores.getVermelho());
                }

                if (estaVentania) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 50, 3, 3, mCores.getLaranja());
                }
                if (estaTempestadeDeVento) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 40, 3, 3, mCores.getMarrom());
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

                if(i_umidade<=30){
                    g.drawPixel(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, mCores.getVermelho());
                }else{
                    g.drawPixel(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, mCores.getAzul());
                }

                if (umidade_existe_antes) {

                    if(umidade_antes_valor<=30 && i_umidade<=30) {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getVermelho());
                    }else if(umidade_antes_valor>30 && i_umidade<=30){
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getLaranja());
                    }else if(umidade_antes_valor<=30 && i_umidade>30){
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getLaranja());
                    }else{
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getAzul());
                    }

                }

                umidade_existe_antes = true;
                umidade_antes_valor=i_umidade;
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

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 40, 70, "TMin", " = " + mCidade.at("TMin"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 150, 180, "TMax", " = " + mCidade.at("TMax"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 40, 70, "UMin", " = " + mCidade.at("UMin"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 150, 180, "UMax", " = " + mCidade.at("UMax"));

            //ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois+30, 50, 120, "TMax", " = " + mCidade.at("TMax"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 260, 360, "Hiperestação", " = " + mCidade.at("Hiperestacao"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 260, 360, "Vegetação", " = " + mCidade.at("Vegetacao"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 60, 40, 70, "UV" + superarko_selecionado, " = " + mCidade.at("UV" + superarko_selecionado));

        }


        g.drawCirculoCentralizado_Pintado(20, 915, 10, mCores.getVermelho());
        ESCRITOR_NORMAL.escreva(35, 915 - 8, mCamada);

        // marcar camada selecionada
        int camada_indo = mCamadaPXInicio;
        for (Par<String, BotaoCor> item : mCamadas) {
            if (Strings.isIgual(item.getChave(), mCamada)) {

                Cor cor_botao = g.getPixel(camada_indo + 25, 55);


                g.drawCirculoCentralizado_Pintado(camada_indo + 25 + 20, 55, 22, mCores.getBranco());
                g.drawCirculoCentralizado_Pintado(camada_indo + 25 + 20, 55, 12, cor_botao);

                break;
            }
            camada_indo += 100;
        }

        int camada_zoom_indo = mCamadaZoomPXInicio;
        for (Par<String, BotaoCor> item : mCamadasZoom) {
            if (Strings.isIgual(item.getChave(), mCamadaZoom)) {

                Cor cor_botao = g.getPixel(camada_zoom_indo + 15, 555);

                g.drawCirculoCentralizado_Pintado(camada_zoom_indo + 20, 560, 10, mCores.getBranco());
                g.drawCirculoCentralizado_Pintado(camada_zoom_indo + 20, 560, 5, cor_botao);

                break;
            }
            camada_zoom_indo += 50;
        }


        if (mCamada.contentEquals("Regiões")) {

            int py = 100;
            for (String modelo_corrente : Atzum.GET_REGIOES()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_REGIAO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }

        } else if (mCamada.contentEquals("Modelo Climático")) {

            int py = 100;
            for (String modelo_corrente : Atzum.GET_MODELO_CLIMATICO()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_MODELO_CLIMATICO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }

        } else if (mCamada.contentEquals("Modelo Vegetação")) {

            int py = 100;
            for (String modelo_corrente : Atzum.GET_MODELO_VEGETACAO()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_MODELO_VEGETACAO_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }

        } else if (mCamada.contentEquals("T1") || mCamada.contentEquals("T2")) {

            int py = 100;
            for (String modelo_corrente : Atzum.GET_MODELO_TEMPERATURA()) {

                g.drawRect_Pintado(1700, py, 25, 25, Atzum.GET_MODELO_TEMPERATURA_COR(modelo_corrente));
                ESCRITOR_NORMAL_BRANCO.escreva(1700 + 40, py + 5, modelo_corrente);

                py += 30;
            }
        }

        int temperatura_px_inicio = 50;

        if (mCidadeSelecionada && mClimaMovendo) {

            int superarko = getSuperarkoPosicao(mClimaMovendoPx);

            int info_py = temperatura_py_depois + 90;

            g.drawRect_Pintado(temperatura_px_inicio + (mClimaMovendoPx - 3), mClimaMovendoPy - 50, 5, 200, mCores.getVermelho());

            render_superarko_info(superarko, g, info_py);

        }

        if (mCidadeSelecionada && mClimaClicado) {

            int superarko = getSuperarkoPosicao(mClimaClicadoPx);

            int info_py = temperatura_py_depois + 110;

            g.drawRect(temperatura_px_inicio + (mClimaClicadoPx - 3), mClimaMovendoPy - 50, 6, 200, mCores.getBranco());

            render_superarko_info(superarko, g, info_py);

        }

        if (drone_ok) {
            g.drawImagem(1900, 600, mapa_drone);
        }

        g.drawRect(1900, 600, 300, 300, mCores.getVermelho());


    }

    public BotaoCor criarCamada(String nome, Cor eCor) {
        BotaoCor eBotao = mClicavel.criarBotaoCor(new BotaoCor(mCamadaPX, 30, 50, 50, eCor));
        mCamadas.adicionar(new Par<String, BotaoCor>(nome, eBotao));
        mCamadaPX += 100;
        return eBotao;
    }

    public void aplicarCamada(String nome) {
        for (Par<String, BotaoCor> item : mCamadas) {
            if (Strings.isIgual(item.getChave(), nome)) {
                item.getValor().clicar();
                break;
            }
        }
    }

    public BotaoCor criarCamadaZoom(String nome, Cor eCor) {
        BotaoCor eBotao = mClicavel.criarBotaoCor(new BotaoCor(mCamadaZoomPX, 550, 20, 20, eCor));
        mCamadasZoom.adicionar(new Par<String, BotaoCor>(nome, eBotao));
        mCamadaZoomPX += 50;
        return eBotao;
    }

    public void aplicarZoomCamada(String nome) {
        for (Par<String, BotaoCor> item : mCamadasZoom) {
            if (Strings.isIgual(item.getChave(), nome)) {
                item.getValor().clicar();
                break;
            }
        }
    }

    private boolean drone_ultimo_valido = false;
    private int drone_ultimo_px = 0;
    private int drone_ultimo_py = 0;

    public void drone_update(boolean ultimo) {

        if (ultimo && !drone_ultimo_valido) {
            return;
        }


        if (!ultimo) {
            drone_ultimo_valido = true;
            drone_ultimo_px = mGPS_PX;
            drone_ultimo_py = mGPS_PY;
        }

        // DRONE
        int comecar_x = mGPS_PX - 100;
        int comecar_y = mGPS_PY - 100;

        int terminar_x = mGPS_PX + 200;
        int terminar_y = mGPS_PY + 200;

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

                if (mCidadeSelecionada) {
                    if (mCidadeSelecionadaX == cidade.getX() && mCidadeSelecionadaY == cidade.getY()) {

                        render_drone.drawCirculoCentralizado_Pintado(cidade_x, cidade_y, 5, mCores.getVerde());
                        render_drone.drawCirculoCentralizado_Pintado(cidade_x, cidade_y, 2, mCores.getAzul());

                    }
                }


            }
        }


        int drone_x = mGPS_PX - comecar_x;
        int drone_y = mGPS_PY - comecar_y;

        render_drone.drawCirculoCentralizado_Pintado(drone_x, drone_y, 5, mCores.getVerde());


    }


    public void criar_camadas_zoom() {

        mCamadaZoomPX = mCamadaZoomPXInicio;
        mCamadasZoom = new Lista<Par<String, BotaoCor>>();


        criarCamadaZoom("Regiões", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_grande = AtzumCreator.GET_MAPA_DE_REGIOES();
                mCamadaZoom = "Regiões";
                drone_update(true);

            }
        });

        criarCamadaZoom("Relevo", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {


                mapa_grande = mArquivoAtzumGeral.GET_MAPA_DE_RELEVO();
                mCamadaZoom = "Relevo";
                drone_update(true);

            }
        });


        criarCamadaZoom("T1", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_grande = AtzumCreator.GET_MAPA_DE_TEMPERATURA_T1();
                mCamadaZoom = "T1";
                drone_update(true);

            }
        });

        criarCamadaZoom("T2", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_grande = AtzumCreator.GET_MAPA_DE_TEMPERATURA_T2();
                mCamadaZoom = "T2";
                drone_update(true);

            }
        });

        criarCamadaZoom("Oceanos", mCores.getAzul()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_grande = AtzumCreator.GET_MAPA_DE_OCEANOS();
                mCamadaZoom = "Oceanos";
                drone_update(true);

            }
        });

        criarCamadaZoom("Modelo Climático", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_grande = AtzumCreator.GET_MAPA_CLIMATICO();
                mCamadaZoom = "Modelo Climático";
                drone_update(true);

            }
        });

        criarCamadaZoom("Modelo Vegetação", mCores.getRosa()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_grande = AtzumCreator.GET_MAPA_VEGETACAO();
                mCamadaZoom = "Modelo Vegetação";
                drone_update(true);

            }
        });

    }

    public void criar_camadas() {

        mCamadaPX = mCamadaPXInicio;
        mCamadas = new Lista<Par<String, BotaoCor>>();

        criarCamada("Cidades", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = AtzumCreator.GET_MAPA_DE_CONTORNO();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "Cidades";

            }
        });

        criarCamada("Regiões", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = AtzumCreator.GET_MAPA_DE_REGIOES();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "Regiões";

            }
        });

        criarCamada("Relevo", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = mArquivoAtzumGeral.GET_MAPA_DE_RELEVO();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "Relevo";

            }
        });


        criarCamada("T1", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = AtzumCreator.GET_MAPA_DE_TEMPERATURA_T1();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "T1";

            }
        });

        criarCamada("T2", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = AtzumCreator.GET_MAPA_DE_TEMPERATURA_T2();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "T2";

            }
        });

        criarCamada("Oceanos", mCores.getAzul()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = AtzumCreator.GET_MAPA_DE_OCEANOS();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "Oceanos";

            }
        });

        criarCamada("Modelo Climático", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = AtzumCreator.GET_MAPA_CLIMATICO();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "Modelo Climático";

            }
        });

        criarCamada("Modelo Vegetação", mCores.getRosa()).setAcao(new Acao() {
            @Override
            public void onClique() {

                mapa_pequeno = AtzumCreator.GET_MAPA_VEGETACAO();
                mapa_pequeno = Efeitos.reduzirMetade(mapa_pequeno);
                mCamada = "Modelo Vegetação";

            }
        });

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
}
