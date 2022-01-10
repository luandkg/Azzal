package AppAttuz;

import AppAttuz.Camadas.*;
import AppAttuz.Ferramentas.Escala;
import AppAttuz.Ferramentas.Nivelador;
import AppAttuz.Mapa.*;
import AppAttuz.Servicos.*;
import Servittor.Servittor;
import UI.Interface.Acao;
import UI.Interface.BotaoCor;
import UI.Interface.Clicavel;
import AppAttuz.Localizador.Camera;
import Azzal.Cenarios.Cena;
import Azzal.Cores;
import Azzal.Cronometro;
import Azzal.Formatos.Ponto;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Azzal.Windows;
import Imaginador.Efeitos;
import Imaginador.ImageUtils;
import Imaginador.TirarPrint;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import Tronarko.Tronarko;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AppAttuz extends Cena {

    private BufferedImage mapa = null;


    private Fonte pequeno;
    private Fonte micro;


    private Clicavel mClicavel;

    private ArrayList<Local> mMarcacoes;
    private ArrayList<Local> mLocais;
    private ArrayList<Local> mMares;

    private ArrayList<Caminho> mCaminhos;


    private Viajante EU;
    private Viagem mViagem;

    private int X0 = -20;
    private int Y0 = 100;
    private Cronometro mCron;

    int raio = 150;

    private int TEMPO_FLUXO = 500;

    private ListaDeCidades mListaDeCidades;
    private boolean printou = false;
    private ProcurarLocalizacao mProcurador;
    private Tronarko eTronarko = new Tronarko();

    private NomesEspecificos mNomear;
    private BufferedImage copia;
    private int mais = 0;

    private Nivelador mNivelador;
    private Massas mMassa;
    private Escala mRelevo;

    private Cores mCores;
    private Camera mCamera;

    private String LOCAL = "/home/luan/Imagens/Arkazz/";
    //private String LOCAL = "/home/luan/Imagens/Simples/";

    @Override
    public void iniciar(Windows eWindows) {

        mCores = new Cores();

        mapa = ImageUtils.getImagem(LOCAL + "mapa.png");


        // mapa = ImageUtils.getImagem("/home/luan/Imagens/Mapas/relevo/relevo.png");

        mapa = Efeitos.preto_branco(mapa);

        mMassa = new Massas(LOCAL, 0, 1);

        //Territorios.init(mapa);
        // Biomas.init(mapa);

        //Relevo.init(mapa);


        mClicavel = new Clicavel();

        mMarcacoes = new ArrayList<Local>();
        mLocais = new ArrayList<Local>();
        mMares = new ArrayList<Local>();

        mRelevo = EscalasPadroes.getEscalaTerrestre();


        mMarcacoes = MapaUtilitario.obterLocais(LOCAL + "dados.txt");


        mCaminhos = new ArrayList<Caminho>();

        mProcurador = new ProcurarLocalizacao();
        mCamera = new Camera();

        pequeno = new FonteRunTime(Cor.getRGB(Color.BLACK), 11);
        micro = new FonteRunTime(Cor.getRGB(Color.BLACK), 10);

        mListaDeCidades = new ListaDeCidades(pequeno, mCores);

        EU = new Viajante(1080, 221);
        mViagem = new Viagem(raio);

        mViagem.getPercurso().add(new Ponto(1080, 221));

        mCron = new Cronometro(TEMPO_FLUXO);

        mNomear = new NomesEspecificos(mCores);

        Cidades.marcar(mLocais, mMares);
        Cidades.ligar(mLocais, mCaminhos);

        BotaoCor BTN_IR = mClicavel.criarBotaoCor(new BotaoCor(500, 50, 50, 50, new Cor(26, 188, 156)));

        BTN_IR.setAcao(new Acao() {
            @Override
            protected void onClique() {
                EU.mudar();
            }
        });

        mNivelador = new Nivelador();


        int e = 1;
        int u = 0;

        for (int i = 1; i <= 10; i++) {

            BotaoCor BTN_N2 = mClicavel.criarBotaoCor(new BotaoCor(800 + (e * 50), 50 + (u * 30), 20, 20, Cor.getRGB(new Color(mRelevo.get(i)))));
            BTN_N2.setAcao(mNivelador.get(i));

            e += 1;

            if (e > 5) {
                e = 1;
                u += 1;
            }
        }


        BotaoCor BTN_ZERO = mClicavel.criarBotaoCor(new BotaoCor(800 - 100, 50, 50, 50, Cor.getRGB(new Color(100, 100, 100))));
        BTN_ZERO.setAcao(mNivelador.get(0));

        boolean CRIAR = false;

        if (CRIAR == true) {

            while (mViagem.getTempo() < 3000) {
                mViagem.viajar(EU, mLocais);

                System.out.println(mViagem.getTempo() + " de 10000");
            }

            mViagem.salvar("/home/luan/Documentos/viagem_ovkom.txt");
            mViagem.marcarCidades("/home/luan/Documentos/viagem_ovkom.txt", mLocais);

            mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7002");
            mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7003");
            mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7004");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7005");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7006");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7007");
            //  mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7008");
            // mViagem.separar("/home/luan/Documentos/viagem_ovkom.txt", "7009");

        } else {


            // mViagem.abrir("/home/luan/Documentos/viagem_ovkom.txt");
            //   mViagem.abrir("/home/luan/Documentos/t7002.txt");

        }


        System.out.println("Tronarko :: " + eTronarko.getAgora());

        //  System.out.println("Estou :: " + mProcurador.ondeEstou(EU, eTronarko.getTozte(), eTronarko.getHazde()));

        expo();


        Cidades.salvar(mLocais, LOCAL + "cidades.dkg");
        Cidades.salvar(mMares, LOCAL + "mares.dkg");


        boolean GERAR = false;
        boolean DADOS = false;


        if (GERAR) {
            Servittor.onServico("Relevo", new Relevo());
        }

        if (DADOS) {
          //  Servittor.onServico("Cartografia", new Cartografia(LOCAL));
          //  Servittor.onServico("ProximidadeDoMar", new ProximidadeDoMar(LOCAL));
            Servittor.onServico("Temperatura", new Temperatura(LOCAL));
            Servittor.onServico("Massas de Agua", new Umidade(LOCAL));
        }


        //mapa = ImageUtils.getImagem("/home/luan/Imagens/Mapas/relevo/relevo.png");
        // mapa = Efeitos.preto_branco(mapa);

        //mapa = Cartografia.aplicarLatitudes(LOCAL,mapa);

        mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

    }

    public void expo() {

        copia = ImageUtils.getCopia(mapa);

        int l = 0;
        for (Local ePonto : mLocais) {

            Renderizador gg = new Renderizador(copia);

            gg.drawRect_Pintado(ePonto.getX() * 2, ePonto.getY() * 2, 5, 5, Cor.getRGB(Color.green));

            mNomear.nomearDireto(gg, micro, l, ePonto.getNome(), ePonto.getX() * 2, ePonto.getY() * 2, 0, 0);


            l += 1;
        }

        ImageUtils.exportar(copia, LOCAL + "territorio.png");

    }

    @Override
    public void update(double dt) {

        int px = (int) getWindows().getMouse().getX();
        int py = (int) getWindows().getMouse().getY();


        mClicavel.update(dt, px, py, getWindows().getMouse().isClicked());

        getWindows().getMouse().liberar();


        mCron.atualizar();
        if (mCron.esperado()) {

            mais += 1;

            if (mais >= 100) {
                mais = 0;
            }


            Tronarko.Tron eTron = eTronarko.getTronAgora();
            eTron.internalizar_Arco(mais);

            mViagem.viajar(EU, mLocais);

            //mProcurador.ondeEstou(EU, eTron.getTozte(), eTron.getHazde());
            //System.out.println("Localizando...");

        }

        if (mClicavel.getClicado()) {

            //  mPontos.clear();
            localizar();

        }

        if (getWindows().getMouse().isMovendo()) {

            if (mNivelador.getNivel() == 0) {

                pontos_limpar();
                MapaUtilitario.salvarLocais(LOCAL + "dados.txt", mMarcacoes);

            }

        }

        for (Ponto ePercurso : mViagem.getPercurso()) {
            // mListaDeCidades.visitar(ePercurso.getX() + "::" + ePercurso.getY());
        }

    }

    public void pontos_limpar() {


        int px = (int) getWindows().getMouse().getX();
        int py = (int) getWindows().getMouse().getY();

        int rx = px - X0 - 3;
        int ry = py - Y0 - 5;

        if (getWindows().getMouse().isMovendo()) {
            rx += getWindows().getMouse().getDeltaX();
            ry += getWindows().getMouse().getDeltaY();
        }

        System.out.println("Limpando -->> " + rx + " :: " + ry);

        int comecar_x = rx - 50;
        int comecar_y = ry - 50;

        int terminar_x = rx + 50;
        int terminar_y = ry + 50;

        if (mNivelador.getNivel() == 0) {
            ArrayList<Local> remover = new ArrayList<Local>();

            for (Local r : mMarcacoes) {
                if (r.getX() > comecar_x && r.getX() < terminar_x && r.getY() > comecar_y && r.getY() < terminar_y) {
                    remover.add(r);
                }
            }

            for (Local r : remover) {
                mMarcacoes.remove(r);
            }
        }
    }

    public void localizar() {

        int px = (int) getWindows().getMouse().getX();
        int py = (int) getWindows().getMouse().getY();

        int rx = px - X0 - 3;
        int ry = py - Y0 - 5;


        if (mNivelador.getNivel() == 0) {
            pontos_limpar();
        } else {

            if (px > X0 && py > Y0) {
                //if (mMassa.isTerra(rx * 2, ry * 2)) {
                mMarcacoes.add(new Local("" + mNivelador.getNivel(), rx, ry));
                // System.out.println(" PONTO -->> " + (rx ) + "::" + (ry));
                //  }
            }


        }


        MapaUtilitario.salvarLocais(LOCAL + "dados.txt", mMarcacoes);

    }


    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getBranco());

        g.drawImagem(X0, Y0, mapa);

        mClicavel.onDraw(g);

        micro.setRenderizador(g);
        pequeno.setRenderizador(g);

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


        for (Local ePonto : mMarcacoes) {

            g.drawRect_Pintado(ePonto.getX() + X0, ePonto.getY() + Y0, 5, 5, Cor.getInt(mRelevo.get(Integer.parseInt(ePonto.getNome()))));
            micro.escreva(ePonto.getX() + X0, ePonto.getY() + Y0, (ePonto.getX() + X0) + "::" + (ePonto.getY() + Y0));


        }

        for (Caminho eCaminho : mCaminhos) {

            g.drawLinha(eCaminho.getInicio().getX() + X0 + 2, eCaminho.getInicio().getY() + Y0 + 2, eCaminho.getFim().getX() + X0 + 2, eCaminho.getFim().getY() + Y0 + 2, Cor.getRGB(Color.blue));

        }


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

        for (Local ePonto : mMares) {

            g.drawRect_Pintado(ePonto.getX() + X0, ePonto.getY() + Y0, 5, 5, mCores.getAzul());

            g.drawRect_Pintado(ePonto.getX() + X0 - 20, ePonto.getY() + Y0, micro.getLarguraDe(ePonto.getNome())-5, (micro.getAltura()*2)-2, mCores.getLaranja());
            micro.escreva(ePonto.getX() + X0 - 20, ePonto.getY() + Y0, ePonto.getNome());

        }


        mListaDeCidades.onDraw(g, mLocais);

        g.drawRect(EU.getX() + X0, EU.getY() + Y0, 5, 5, mCores.getVermelho());

        int diametro = raio * 2;


        g.drawRect((EU.getX() + X0) - raio, (EU.getY() + Y0) - raio, diametro, diametro, mCores.getAmarelo());


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

        pequeno.escreva(570, 80, "" + mNivelador.getNivel());


        g.drawLinha((EU.getX() + X0) + 2, (EU.getY() + Y0) + 2, 400 + 100, 690, mCores.getLaranja());


        g.drawImagem(400, 690, mCamera.onGravar(copia, (EU.getX() * 2) - 100, (EU.getY() * 2) - 100, EU.getX() * 2, EU.getY() * 2));

        g.drawRect(400, 690, 200, 240, mCores.getPreto());


        //  pequeno.EscreveNegrito(g, "Percurso : " + mViagem.getPercurso().size(), 750, 100);
        //    pequeno.EscreveNegrito(g, "Tempo    : " + mViagem.getTempo(), 750, 120);

        if (mNivelador.getNivel() == 0) {

            if (getWindows().getMouse().isMovendo()) {

                int px = (int) getWindows().getMouse().getX() + getWindows().getMouse().getDeltaX();
                int py = (int) getWindows().getMouse().getY() + getWindows().getMouse().getDeltaY();


                g.drawRect(px - 50, py - 50, 100, 100, mCores.getPreto());


            } else {

                int px = (int) getWindows().getMouse().getX();
                int py = (int) getWindows().getMouse().getY();

                g.drawRect(px - 50, py - 50, 100, 100, mCores.getPreto());

            }


        }


        if (!printou) {
            TirarPrint.print(getWindows(), "/home/luan/Imagens/t_7002.png");
            printou = true;
        }

    }


}
