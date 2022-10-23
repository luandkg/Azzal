package apps.app_attuz;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.MapaUtilitario;
import apps.app_attuz.Assessorios.Nivelador;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Ferramentas.Caminho;
import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Widgets.ListaDeCidades;
import apps.app_attuz.Widgets.NomesEspecificos;
import apps.app_attuz.Regiao.Regiao;
import apps.app_attuz.Regiao.Regionalizador;
import apps.app_attuz.Servicos.*;
import apps.app_attuz.Viagem.RealizarViagem;
import apps.app_attuz.Viagem.Viagem;
import apps.app_attuz.Viagem.Viajante;
import apps.app_attuz.Widgets.*;
import libs.arquivos.PreferenciasOrganizadas;
import libs.arquivos.QTT;
import azzal.*;
import mockui.Interface.Acao;
import mockui.Interface.BotaoCor;
import mockui.Interface.Clicavel;
import apps.app_attuz.Localizador.DroneCamera;
import azzal.cenarios.Cena;
import azzal.geometria.Ponto;
import azzal.utilitarios.Cor;
import libs.Imaginador.Efeitos;
import libs.Imaginador.ImageUtils;
import libs.Imaginador.TirarPrint;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import libs.tronarko.Tronarko;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AppAttuz extends Cena {

    private BufferedImage mapa = null;

    private Fonte pequeno;
    private Fonte micro;
    private Fonte hipermicro;


    private Clicavel mClicavel;

    private ArrayList<Local> mLocais;
    private ArrayList<Local> mMares;

    private ArrayList<Caminho> mCaminhos;

    private Viajante EU;
    private Viagem mViagem;

    private int X0 = 400;
    private int Y0 = 130;

    private int raio = 150;


    private ListaDeCidades mListaDeCidades;
    private boolean printou = false;
    private Tronarko eTronarko = new Tronarko();

    private NomesEspecificos mNomear;
    private BufferedImage mImagemDrone;

    private Nivelador mNivelador;
    private Escala mRelevo;

    private Cores mCores;
    private DroneCamera mDroneCamera;

    private String LOCAL = "/home/luan/Imagens/Arkazz/";
    private boolean mostrarCidades = true;


    private int mPosicaoX = 0;
    private int mPosicaoY = 0;
    private String mValorSelecionado = "";
    private String mLocalSelecionado = "";
    private String mRegiaoSelecionada = "";
    private RealizarViagem mRealizarViagem;
    private HiperMarkattor mHiperMarkattor;

    private Preferencias mPreferencias;
    private Escolhettor mEscolhettor;
    private Distanciador mDistanciador;

    private BufferedImage marcador = ImageUtils.getImagem("/home/luan/Imagens/icones_mapa/marcador_32.png");

    private Oceanografia mOceanografia;
    private Regionalizador mRegionalizador;
    private OpcionadorObrigatorio mOpcionadorObrigatorio;
    private boolean tem_posicao;

    @Override
    public void iniciar(Windows eWindows) {

        //simplificar();

        mCores = new Cores();

        mapa = ImageUtils.getImagem(LOCAL + "build/politicamente.png");
        mImagemDrone = ImageUtils.getCopia(mapa);


        mClicavel = new Clicavel();

        mLocais = new ArrayList<Local>();
        mMares = new ArrayList<Local>();

        mRelevo = EscalasPadroes.getEscalaTerrestre();


        mCaminhos = new ArrayList<Caminho>();

        mDroneCamera = new DroneCamera(mCores);

        pequeno = new FonteRunTime(Cor.getRGB(Color.BLACK), 11);
        micro = new FonteRunTime(Cor.getRGB(Color.BLACK), 10);
        hipermicro = new FonteRunTime(Cor.getRGB(Color.BLACK), 7);

        mListaDeCidades = new ListaDeCidades(pequeno, mCores);

        EU = new Viajante(1080, 221);
        mViagem = new Viagem(raio);

        mViagem.getPercurso().add(new Ponto(1080, 221));


        mNomear = new NomesEspecificos(mCores);

        Arkazz eArkazz = new Arkazz();
        mLocais = eArkazz.getCidades();
        mMares = eArkazz.getOceanos();


        mOceanografia = new Oceanografia(mMares);
        mOpcionadorObrigatorio = new OpcionadorObrigatorio(600, 20);

        mOpcionadorObrigatorio.setCabecalho("TIPO DE MAPA : ");

        Cor cor_normal = new Cor(255, 200, 200);
        Cor cor_selecionado = new Cor(255, 0, 0);

        mOpcionadorObrigatorio.adicionar("REGIÃO", cor_normal, cor_selecionado);
        mOpcionadorObrigatorio.adicionar("RELEVO", cor_normal, cor_selecionado);
        mOpcionadorObrigatorio.adicionar("RELEVO TERRA", cor_normal, cor_selecionado);
        mOpcionadorObrigatorio.adicionar("RELEVO OCEANO", cor_normal, cor_selecionado);

        boolean criar_eu = false;

        if (criar_eu) {

            BotaoCor BTN_IR = mClicavel.criarBotaoCor(new BotaoCor(500, 50, 50, 50, new Cor(26, 188, 156)));

            BTN_IR.setAcao(new Acao() {
                @Override
                public void onClique() {
                    EU.mudar();
                }
            });

        }


        mNivelador = new Nivelador();

        mEscolhettor = new Escolhettor(2500, 900, mNivelador, mClicavel, mRelevo);


        mViagem.abrir("/home/luan/Documentos/t7002.txt");


        System.out.println("libs.Tronarko :: " + Tronarko.getAgora());

        //  System.out.println("Estou :: " + mProcurador.ondeEstou(EU, eTronarko.getTozte(), eTronarko.getHazde()));

        // droneOrganizar(mImagemDrone);

        // expo();


        mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);


        mPreferencias = new Preferencias(LOCAL);

        // GuiaDeViagem.organizar();
        // GuiaDeViagem.passei(eTronarko.getTozte(),eTronarko.getHazde());

        mRealizarViagem = new RealizarViagem();

        mHiperMarkattor = new HiperMarkattor(mNivelador, X0, Y0, (mapa.getWidth() + X0), (mapa.getHeight() + Y0));

        mHiperMarkattor.carregarMarcadores(MapaUtilitario.obterLocais(LOCAL + "dados.txt"));

        mHiperMarkattor.setAcao(new Acao() {
            @Override
            public void onClique() {
                MapaUtilitario.salvarLocais(LOCAL + "dados.txt", mHiperMarkattor.getMarcadores());
            }
        });

        mDistanciador = new Distanciador(mClicavel);

        mRegionalizador = new Regionalizador(LOCAL);


        PreferenciasOrganizadas pref = new PreferenciasOrganizadas(LOCAL + "conf.po");
        pref.abrirSeExistir();

        mOpcionadorObrigatorio.setSelecionado(pref.getOpcao("Mapa", "Modo"));

        alterar_mapa();

        tem_posicao = false;

    }


    public void simplificar() {


        // SE SIMPLES

        LOCAL = "/home/luan/Imagens/Simples/";

        X0 = 500;
        Y0 = 200;

        mostrarCidades = false;

    }

    public void droneOrganizar(int ox, int oy, BufferedImage eImagemDrone) {

        Renderizador gg = new Renderizador(eImagemDrone);

        int l = 0;
        for (Local ePonto : mLocais) {

            int rx = (ePonto.getX() * 2) - ox;
            int ry = (ePonto.getY() * 2) - oy;

            gg.drawRect_Pintado(rx, ry, 5, 5, Cor.getRGB(Color.green));

            mNomear.nomearDireto(gg, micro, l, ePonto.getNome(), rx, ry, 0, 0);


            l += 1;
        }

        //  ImageUtils.exportar(copia, LOCAL + "territorio.png");

    }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();


        mClicavel.update(dt, px, py, getWindows().getMouse().isClicked());

        mOpcionadorObrigatorio.update(dt, px, py, getWindows().getMouse().isClicked());

        if (mOpcionadorObrigatorio.isAlterado()) {

            PreferenciasOrganizadas pref = new PreferenciasOrganizadas(LOCAL + "conf.po");
            pref.abrirSeExistir();

            pref.setOpcao("Mapa", "Modo", mOpcionadorObrigatorio.getSelecionado());
            pref.salvar();

            alterar_mapa();

        }

        mPosicaoX = 0;
        mPosicaoY = 0;
        mValorSelecionado = "";

        tem_posicao = false;

        if (px > X0 && py >= Y0 && px < (mapa.getWidth() + X0) && py < (mapa.getHeight() + Y0)) {

            tem_posicao = true;

            int agoraX = (px - X0) * 2;
            int agoraY = (py - Y0) * 2;


            if (mPosicaoX != agoraX || mPosicaoY != agoraY) {
                mPosicaoX = agoraX;
                mPosicaoY = agoraY;

                int altitude = QTT.pegar(LOCAL + "dados/relevo.qtt", mPosicaoX, mPosicaoY);

                mValorSelecionado = String.valueOf(altitude);

                if (altitude >= 0) {
                    mLocalSelecionado = "Continente";
                    mRegiaoSelecionada = mRegionalizador.getRegiao(mPosicaoX / 2, mPosicaoY / 2);
                } else {
                    mLocalSelecionado = "Oceano";
                    mRegiaoSelecionada = mOceanografia.getOceanoProximo(mPosicaoX / 2, mPosicaoY / 2);
                }


            }

            if (mDistanciador.isSelecionando()) {
                if (getWindows().getMouse().isClicked()) {
                    mDistanciador.marcar(agoraX, agoraY);
                }
            }


        }


       // mRealizarViagem.viajar(eTronarko, mViagem, EU, mLocais);


        if (mClicavel.getClicado()) {


            mHiperMarkattor.localizar(px, py, getWindows().getMouse().isMovendo(), getWindows().getMouse().getDeltaX(), getWindows().getMouse().getDeltaY());

            if (getWindows().getMouse().isClicked()) {
                mPreferencias.onClique(px, py);
            }

        }

        mHiperMarkattor.movendo(px, py, getWindows().getMouse().isMovendo(), getWindows().getMouse().getDeltaX(), getWindows().getMouse().getDeltaY());


        for (Ponto ePercurso : mViagem.getPercurso()) {
            // mListaDeCidades.visitar(ePercurso.getX() + "::" + ePercurso.getY());
        }

        getWindows().getMouse().liberar();

    }


    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getBranco());

        g.drawImagem(X0, Y0, mapa);

        mClicavel.onDraw(g);

        micro.setRenderizador(g);
        pequeno.setRenderizador(g);
        hipermicro.setRenderizador(g);

        if (tem_posicao) {

            pequeno.escreva(2500, 800, " X = " + mPosicaoX + " Y = " + mPosicaoY);
            pequeno.escreva(2550, 820, " ALTITUDE : " + mValorSelecionado + " m");
            pequeno.escreva(2550, 840, " LOCAL : " + mLocalSelecionado);
            pequeno.escreva(2550, 860, " REGIAO : " + mRegiaoSelecionada);


        }


        if (mPreferencias.mMostrarMarcadores.getValor()) {

            for (Local ePonto : mHiperMarkattor.getMarcadores()) {
                g.drawRect_Pintado(ePonto.getX() + X0, ePonto.getY() + Y0, 5, 5, Cor.getInt(mRelevo.get(Integer.parseInt(ePonto.getNome()))));
                //    micro.escreva(ePonto.getX() + X0, ePonto.getY() + Y0, (ePonto.getX() + X0) + "::" + (ePonto.getY() + Y0));
            }

        }


        for (Caminho eCaminho : mCaminhos) {

            g.drawLinha(eCaminho.getInicio().getX() + X0 + 2, eCaminho.getInicio().getY() + Y0 + 2, eCaminho.getFim().getX() + X0 + 2, eCaminho.getFim().getY() + Y0 + 2, Cor.getRGB(Color.blue));

        }


        if (mPreferencias.mMostrarPontosCidades.getValor()) {
            for (Local ePonto : mLocais) {

                int cx = ePonto.getX() + X0 - 3;
                int cy = ePonto.getY() + Y0 - 3;

                ComplexoRender.sinalizar(g, cx, cy, 6, mCores.getBranco(), mCores.getVerde());

            }
        }


        if (mPreferencias.mMostrarCidades.getValor()) {
            int l = 0;

            for (Local ePonto : mLocais) {

                g.drawRect_Pintado(ePonto.getX() + X0, ePonto.getY() + Y0, 5, 5, mCores.getVerde());

                mNomear.nomear(g, micro, l, ePonto, X0, Y0);

                if (EU.estouPensando()) {

                    if (mViagem.getProximos().contains(ePonto)) {

                        g.drawRect((ePonto.getX() + X0) - 15, (ePonto.getY() + Y0) - 15, 30, 30, mCores.getAmarelo());

                        g.drawRect(ePonto.getX() + X0, ePonto.getY() + Y0, 5, 5, mCores.getAzul());

                    }

                }


                l += 1;

            }


        }

        if (mPreferencias.mMostrarMares.getValor()) {

            for (Local ePonto : mMares) {

                g.drawRect_Pintado(ePonto.getX() + X0, ePonto.getY() + Y0, 5, 5, mCores.getAzul());

                g.drawRect_Pintado(ePonto.getX() + X0 - 20, ePonto.getY() + Y0, micro.getLarguraDe(ePonto.getNome()) + 2, (micro.getAltura() * 2) - 2, mCores.getLaranja());
                micro.escreva(ePonto.getX() + X0 - 20, ePonto.getY() + Y0, ePonto.getNome());

            }

        }

        if (mPreferencias.mMostrarEU.getValor()) {


            int uu = mViagem.getPercurso().size();
            int ui = 0;

            for (Ponto ePonto : mViagem.getPercurso()) {
                ui += 1;

                g.drawRect_Pintado(ePonto.getX() + X0, ePonto.getY() + Y0, 2, 2, mCores.getLaranja());

                if (ui >= uu) {
                    //  g.setColor(Color.blue);
                    // g.fillRect(ePonto.getX() + X0, ePonto.getY() + Y0, 10, 10);
                }
            }

        }


        mListaDeCidades.onDraw(g, mLocais);

        //  g.drawRect(EU.getX() + X0, EU.getY() + Y0, 5, 5, mCores.getVermelho());

        ComplexoRender.sinalizar(g, EU.getX() + X0 - 3, EU.getY() + Y0 - 3, 6, mCores.getBranco(), mCores.getVermelho());
        ComplexoRender.sinalizar(g, EU.getX() + X0 - 4, EU.getY() + Y0 - 4, 8, mCores.getBranco(), mCores.getVermelho());


        if (mostrarCidades) {
            int diametro = raio * 2;
            g.drawRect((EU.getX() + X0) - raio, (EU.getY() + Y0) - raio, diametro, diametro, mCores.getAmarelo());
        }

        if (EU.getAtividade() == EU.getDormindo()) {
            //   pequeno.EscreveNegrito(g, EU.getX() + "::" + EU.getY() + " - " + "<" + EU.getTempo() + "> " + EU.getAtividade().toString(), 570, 80);
        } else {
            //  pequeno.EscreveNegrito(g, EU.getX() + "::" + EU.getY() + " - " + EU.getAtividade().toString(), 570, 80);

            if (EU.getAtividade() == EU.getIndo()) {

                //   pequeno.EscreveNegrito(g, EU.getRealizado() + " de " + EU.getRotaTamanho(), 570, 95);

                //  pequeno.EscreveNegrito(g, (EU.getRealizado() / 4) + " de " + (EU.getRotaTamanho() / 4), 570, 110);
                // pequeno.EscreveNegrito(g, EU.getIndoPara(), 570, 130);

            }

        }

        mEscolhettor.drawSelecionado(g, pequeno);


        if (mPreferencias.mMostrarCidades.getValor()) {

            g.drawLinha((EU.getX() + X0) + 2, (EU.getY() + Y0) + 2, 400 + 100, 690, mCores.getLaranja());

            int rx = (EU.getX() * 2) - 100;
            int ry = (EU.getY() * 2) - 100;

            BufferedImage aqui_drone = mDroneCamera.onGravar(mImagemDrone, rx, ry, EU.getX() * 2, EU.getY() * 2, mLocais);

            // droneOrganizar(rx ,ry,aqui_drone);

            g.drawImagem(400, 690, aqui_drone);

            g.drawRect(400, 690, 200, 240, mCores.getPreto());

        }

        //  pequeno.EscreveNegrito(g, "Percurso : " + mViagem.getPercurso().size(), 750, 100);
        //    pequeno.EscreveNegrito(g, "Tempo    : " + mViagem.getTempo(), 750, 120);

        if (mNivelador.getNivel() == 0) {

            if (getWindows().getMouse().isMovendo()) {

                int px = getWindows().getMouse().getMovendoX();
                int py = getWindows().getMouse().getMovendoY();


                g.drawRect(px - 50, py - 50, 100, 100, mCores.getPreto());


            } else {

                int px = getWindows().getMouse().getX();
                int py = getWindows().getMouse().getY();

                g.drawRect(px - 50, py - 50, 100, 100, mCores.getPreto());

            }


        }

        mPreferencias.panielConfiguracoes(g);

        mDistanciador.draw(g, mLocais);

        if (mDistanciador.temP1()) {
            g.drawImagemComAlfa((mDistanciador.getP1().getX() / 2) + X0 - 15, (mDistanciador.getP1().getY() / 2) + Y0 - 30, marcador);
        }

        if (mDistanciador.temP2()) {
            g.drawImagemComAlfa((mDistanciador.getP2().getX() / 2) + X0 - 15, (mDistanciador.getP2().getY() / 2) + Y0 - 30, marcador);
        }

        mOpcionadorObrigatorio.draw(g);

        int py = 50;

        for (Regiao eRegiao : mRegionalizador.getRegioes()) {

            g.drawRect_Pintado(2830, py, 20, 20, eRegiao.getCor());
            pequeno.escreva(2860, py, eRegiao.getNome());

            py += 30;

        }


        pequeno.escreva(2500, 970, "Luan Freitas - AZZAL UI :: MAPA ATTUZ 1.0");


        if (!printou) {
            TirarPrint.print(getWindows(), "/home/luan/Imagens/t_7002.png");
            printou = true;
        }

    }

    public void alterar_mapa() {

        System.out.println("Alterado :: " + mOpcionadorObrigatorio.getSelecionado());

        if (mOpcionadorObrigatorio.getSelecionado().contentEquals("REGIÃO")) {

            mapa = ImageUtils.getImagem(LOCAL + "build/politicamente.png");
            mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

            //mapa = apenas_regiao(mapa, "Skor");

        } else if (mOpcionadorObrigatorio.getSelecionado().contentEquals("RELEVO")) {

            mapa = ImageUtils.getImagem(LOCAL + "build/relevo.png");
            mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

        } else if (mOpcionadorObrigatorio.getSelecionado().contentEquals("RELEVO TERRA")) {

            mapa = ImageUtils.getImagem(LOCAL + "build/terra.png");
            mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

        } else if (mOpcionadorObrigatorio.getSelecionado().contentEquals("RELEVO OCEANO")) {

            mapa = ImageUtils.getImagem(LOCAL + "build/agua.png");
            mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

        }

    }

    public static BufferedImage apenas_regiao(BufferedImage origem, String regiao) {

        String LOCAL = "/home/luan/Imagens/Arkazz/";

        Regionalizador r = new Regionalizador(LOCAL);
        Massas m = MassasDados.getTerraAgua(LOCAL);


        int valor = 0;

        for (Regiao rr : r.getRegioes()) {
            if (rr.getNome().contentEquals(regiao)) {
                valor = rr.getCor().getValor();
                break;
            }
        }

        BufferedImage copia = ImageUtils.getCopia(origem);
        for (int x = 0; x < copia.getWidth(); x++) {
            for (int y = 0; y < copia.getHeight(); y++) {


                int vc = copia.getRGB(x, y);
                if (vc == valor) {

                } else {

                    vc = Color.WHITE.getRGB();

                    copia.setRGB(x, y, vc);
                }


            }
        }

        return copia;
    }
}
