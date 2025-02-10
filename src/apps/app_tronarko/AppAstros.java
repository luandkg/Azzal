package apps.app_tronarko;


import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Cronometro;
import libs.luan.Lista;
import libs.luan.Par;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;
import libs.tronarko.*;
import libs.tronarko.satelites.Ceu;
import libs.tronarko.utils.AstroLocal;


public class AppAstros extends Cena {


    private Windows mWindows;

    private Fonte mTextoGrande;
    private Fonte mTextoGrande_Hoje;

    private Fonte mTextoPequeno;
    private Fonte mTextoPequeno_Sel;

    private Fonte mTextoPequeno_Hoje;
    private Fonte mTextoPequeno_Hoje2;
    private Fonte mTextoPequeno_MuitoGrande;


    private BotaoCor BTN_PROXIMO_TOZTE;


    private Tozte mHoje;

    private int mQuantos;


    private Clicavel mClicavel;
    private Cores mCores;


    private int HIPERARKO_POS_X = 50;
    private int HIPERARKO_POS_Y = 280;


    private HiperarkoWidget mHiperarkoWidget;
    private Ceu mCeu;
    private int passo;
    private int taxa = 0;
    private int pastes = 30;

    private Cronometro cron;
    private Tozte tozte_corrente;
    private Signos signo_corrente;
    private Lista<BotaoCor> mBotoes;

    @Override
    public void iniciar(Windows eWindows) {

        mWindows = eWindows;
        mWindows.setTitle("Harremplattor");

        mCores = new Cores();

        mTextoGrande = new FonteRunTime(mCores.getPreto(), 15);
        mTextoGrande_Hoje = new FonteRunTime(mCores.getPreto(), 11);

        mTextoPequeno = new FonteRunTime(mCores.getPreto(), 11);
        mTextoPequeno_Sel = new FonteRunTime(mCores.getVermelho(), 11);

        mTextoPequeno_Hoje = new FonteRunTime(mCores.getVermelho(), 11);
        mTextoPequeno_Hoje2 = new FonteRunTime(mCores.getBranco(), 11);
        mTextoPequeno_MuitoGrande = new FonteRunTime(mCores.getBranco(), 18);


        mClicavel = new Clicavel();

        BTN_PROXIMO_TOZTE = mClicavel.criarBotaoCorDesenharAcima(new BotaoCor(100, 100, 100, 100, new Cor(50, 90, 156)));
        BTN_PROXIMO_TOZTE.setAcao(new Acao() {
            @Override
            public void onClique() {
                tozte_corrente = tozte_corrente.adicionar_Superarko(1);
            }
        });

        mBotoes = new Lista<BotaoCor>();


        int signo_x = 250;
        int signo_y = 100;

        int i = 1;
        for (Signos signo : Signos.listar()) {
            mBotoes.adicionar(botao_organizado(signo_x, signo_y, signo));

            signo_x += 40;
            if (i == 5) {
                signo_y += 50;
                signo_x = 250;
            }
            i += 1;
        }


        mHoje = Tronarko.getTozte();
        mQuantos = 0;


        // mLigarCron = false;
        // mCronLigar = new Hazde(6, 61, 0);


        mHiperarkoWidget = new HiperarkoWidget(HIPERARKO_POS_X, HIPERARKO_POS_Y, mHoje.getHiperarko(), mHoje.getTronarko());
        mHiperarkoWidget.selecionar(Tronarko.getTozte());
        mHiperarkoWidget.setPodeSelecionar(true);

        mTextoPequeno_Sel = new FonteRunTime(mHiperarkoWidget.getCorTocando(), 11);

        mCeu = new Ceu();
        passo = 0;
        taxa = +1;
        cron = new Cronometro(200);


        tozte_corrente = Tronarko.getTozte();
        signo_corrente = Signos.TIGRE;

    }


    public BotaoCor botao_organizado(int ex, int ey, Signos mudar_signo) {

        BotaoCor botao = mClicavel.criarBotaoCorDesenharAcima(new BotaoCor(ex, ey, 30, 40, new Cor(50, 90, 156)));
        botao.setAcao(new Acao() {
            @Override
            public void onClique() {
                signo_corrente = mudar_signo;
            }
        });

        return botao;
    }


    @Override
    public void update(double dt) {


        mHoje = Tronarko.getTozte();


        mClicavel.update(dt, getWindows().getMouse().getX(), getWindows().getMouse().getY(), getWindows().getMouse().isPressed());

        //System.out.println("Clicavel : " + mClicavel.getClicado());

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();


        mHiperarkoWidget.update(px, py, getWindows().getMouse().isClicked());


      //  mHoje = mHoje.adicionar_Superarko(mQuantos);
        mHoje = mHoje.adicionar_Tronarko(mQuantos);

        // System.out.println("Cron Ligar : " + mCronLigar.getTexto());


        getWindows().getMouse().liberar();

        cron.esperar();
        if (cron.foiEsperado()) {
            cron.zerar();

            tozte_corrente = tozte_corrente.adicionar_Tronarko(1);


        }
    }


    @Override
    public void draw(Renderizador r) {

        r.limpar(mCores.getBranco());


        mTextoPequeno.setRenderizador(r);
        mTextoPequeno_Sel.setRenderizador(r);
        mTextoGrande_Hoje.setRenderizador(r);
        mTextoGrande.setRenderizador(r);
        mTextoPequeno_Hoje.setRenderizador(r);
        mTextoPequeno_Hoje2.setRenderizador(r);
        mTextoPequeno_MuitoGrande.setRenderizador(r);


        for (BotaoCor botao : mBotoes) {
            botao.setCor(new Cor(50, 90, 156));
        }

        mBotoes.get(signo_corrente.getValor() - 1).setCor(new Cor(255, 100, 100));

        mClicavel.onDraw(r);

        int i = 1;
        for (BotaoCor botao : mBotoes) {
            mTextoPequeno.escreva(botao.getX() + 7, botao.getY() + 12, String.valueOf(Signos.get(i).toString().charAt(0)));
            i += 1;
        }

        mTextoPequeno.escreva(BTN_PROXIMO_TOZTE.getX() - 20, BTN_PROXIMO_TOZTE.getY() + 110, "PROXIMO TOZTE");


        int raio = 300;

        r.drawCirculoCentralizado(800, 500, raio, mCores.getPreto());
        r.drawRect_Pintado(800, 500, 10, 10, mCores.getPreto());


        //   r.drawLinha(800, 500, 800 + raio, 500, mCores.getVermelho());


        //  mTextoPequeno.escreva(50, 150,   mCeu.getAllux().getInfo(Tronarko.getTozte()).toString());
        //  mTextoPequeno.escreva(50, 200,   mCeu.getEttos().getInfo(Tronarko.getTozte()).toString());
        //  mTextoPequeno.escreva(50, 250,   mCeu.getUnnos().getInfo(Tronarko.getTozte()).toString());

        long harrem = (long) tozte_corrente.getMegarkoDoTronarko() + ((long) tozte_corrente.getTronarko() * 50);


        mTextoPequeno.escreva(50, 300, " -->> Tozte   : " + tozte_corrente.toString());
        mTextoPequeno.escreva(50, 340, " -->> Mega    : " + tozte_corrente.getMegarkoDoTronarko());
        mTextoPequeno.escreva(50, 380, " -->> Harrem  : " + harrem);
        mTextoPequeno.escreva(50, 420, " -->> Signo  : " + signo_corrente.toString());

        mTextoPequeno.escreva(50, 460, " -->> Luan  : " + Tronarko.getData(27, 7, 1992).toString() + " -> " + Tronarko.getData(27, 7, 1992).getSigno().toString());


        for (Astro astro : Harremplattor.getAstros(tozte_corrente)) {


            // mTextoPequeno.escreva(50, 250, " -->> Verde  : " + astro.getX1() + "::" + astro.getX2());


            r.drawCirculoCentralizado_Pintado(astro.getPosX(), astro.getPosY(), 5, astro.getCor());
            r.drawCirculoCentralizado(astro.getPosX(), astro.getPosY(), 8, astro.getCor());

        }

        for (AstroLocal eixo : Harremplattor.getEixos(signo_corrente,tozte_corrente)) {

            r.drawCirculoCentralizado_Pintado(eixo.getX(), eixo.getY(), 5, mCores.getPreto());
            r.drawCirculoCentralizado(eixo.getX(), eixo.getY(), 8, mCores.getPreto());
            r.drawCirculoCentralizado(eixo.getX(), eixo.getY(), 10, mCores.getPreto());
            r.drawCirculoCentralizado(eixo.getX(), eixo.getY(), 12, mCores.getPreto());

        }

        AstroLocal e1 = Harremplattor.getEixos(signo_corrente,tozte_corrente).get(0);
        AstroLocal e2 = Harremplattor.getEixos(signo_corrente,tozte_corrente).get(1);
        AstroLocal e3 = Harremplattor.getEixos(signo_corrente,tozte_corrente).get(2);


        r.drawLinha(e1.getX(), e1.getY(), e2.getX(), e2.getY(), mCores.getPreto());
        r.drawLinha(e2.getX(), e2.getY(), e3.getX(), e3.getY(), mCores.getPreto());
        r.drawLinha(e3.getX(), e3.getY(), e1.getX(), e1.getY(), mCores.getPreto());


        //   mTextoPequeno.escreva(50, 400, " -->> Tranquilidade  : " + normalizar(mLocais, "A", "Vermelho"));
        //  mTextoPequeno.escreva(50, 420, " -->> Desejo  : " + inverso_normalizar(mLocais, "Amarelo", "Vermelho"));
        //  mTextoPequeno.escreva(50, 440, " -->> Força  : " + inverso_normalizar(mLocais, "B", "Azul"));

        int pos_y = 500;

        for (Par<String, String> item : Harremplattor.get(signo_corrente, tozte_corrente)) {
            mTextoPequeno.escreveLinha(pos_y, 50, 220, " -->> " + item.getChave(), "  : " + item.getValor());
            pos_y += 20;
        }


    }

    public void mult() {

        Tozte t = new Tozte(1, 1, 7004);

        for (int hip = 1; hip <= 50; hip++) {
            System.out.println("Tozte ( " + t.getTextoZerado() + " ) -->> " + Harremplattor.get_item(signo_corrente, t, "Sentimento"));
            t = t.adicionar_Superarko(10);
        }
        System.out.println("---------------------------");


    }


}
