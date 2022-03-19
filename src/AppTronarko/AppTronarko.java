package AppTronarko;

import Azzal.Cenarios.Cena;
import Azzal.Cores;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Azzal.Windows;
import Documentar.AutoInt;
import Letrum.Fonte;
import Letrum.FonteDupla;
import Letrum.FonteDuplaRunTime;
import Letrum.Maker.FonteRunTime;
import Tronarko.Eventos.Eventum;
import Tronarko.*;
import Tronarko.Satelites.*;
import UI.Interface.Acao;
import UI.Interface.BotaoCor;
import UI.Interface.Clicavel;
import UI.Marcador;


import java.util.ArrayList;

public class AppTronarko extends Cena {


    private FonteDupla mTextoGrande;

    private FonteDupla mTextoPequeno;

    private Fonte mTextoPequenoBranco;

    private Tronarko mTronarkum;
    private Eventum mEventum;
    private Ceu mCeu;


    private Tozte mAtualmente;
    private Tozte mHoje;
    private Hazde mAgora;

    private int mQuantos;

    private Cores mCores;

    private Clicavel mClicavel;
    private BotaoCor BTN_MENOS;
    private BotaoCor BTN_MAIS;
    private BotaoCor BTN_HOJE;


    private Satelatizador mSatelatizadorAllux;
    private Satelatizador mSatelatizadorUnnos;
    private Satelatizador mSatelatizadorEttos;

    private HiperarkoWidget mHiperarkoWidget_01;
    private HiperarkoWidget mHiperarkoWidget_02;
    private HiperarkoWidget mHiperarkoWidget_03;
    private HiperarkoWidget mHiperarkoWidget_04;
    private HiperarkoWidget mHiperarkoWidget_05;

    private HiperarkoWidget mHiperarkoWidget_06;
    private HiperarkoWidget mHiperarkoWidget_07;
    private HiperarkoWidget mHiperarkoWidget_08;
    private HiperarkoWidget mHiperarkoWidget_09;
    private HiperarkoWidget mHiperarkoWidget_10;

    private HiperarkoWidget mHiperarkoWidgetSelecionado;
    private TronarkoImagemSignos mTronarkoImagemSignos;

    private ArrayList<TozteCor> mEventos;

    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Tronarko com Azzal");

        mCores = new Cores();

        mTextoGrande = new FonteDuplaRunTime(mCores.getPreto(), mCores.getVermelho(), 20);
        mTextoPequeno = new FonteDuplaRunTime(mCores.getPreto(), mCores.getVermelho(), 11);
        mTextoPequenoBranco = new FonteRunTime(mCores.getBranco(), 11);

        mTronarkum = new Tronarko();
        mEventum = new Eventum();
        mCeu = new Ceu();

        mAtualmente = null;
        mHoje = mTronarkum.getTozte();
        mQuantos = 0;

        mClicavel = new Clicavel();

        BTN_HOJE = mClicavel.criarBotaoCorDesenharAcima(new BotaoCor(1155 - 25, 930, 50, 50, new Cor(200, 120, 0)));
        BTN_HOJE.setVariacao(new Cor(200, 120, 0), new Cor(255, 120, 0));

        BTN_HOJE.setAcao(new Acao() {
            @Override
            public void onClique() {
                mQuantos = 0;
            }
        });

        BTN_MENOS = mClicavel.criarBotaoCor(new BotaoCor(1100, 900, 50, 100, new Cor(50, 90, 156)));
        BTN_MENOS.setVariacao(new Cor(50, 90, 156), new Cor(100, 90, 156));

        BTN_MENOS.setAcao(new Acao() {
            @Override
            public void onClique() {
                mQuantos -= 1;
            }
        });

        BTN_MAIS = mClicavel.criarBotaoCor(new BotaoCor(1155, 900, 50, 100, new Cor(26, 188, 156)));
        BTN_MAIS.setVariacao(new Cor(26, 188, 156), new Cor(100, 188, 156));

        BTN_MAIS.setAcao(new Acao() {
            @Override
            public void onClique() {
                mQuantos += 1;
            }
        });

        System.out.println("tron");

        mHoje = mTronarkum.getTozte();
        mAgora = mTronarkum.getHazde();

        mSatelatizadorAllux = new Satelatizador("comum");
        mSatelatizadorEttos = new Satelatizador("comum");
        mSatelatizadorUnnos = new Satelatizador("comum");

        mTronarkoImagemSignos = new TronarkoImagemSignos();

        //ExportarSequenciaLunar.exportar(mHoje, 100, "/home/luan/Imagens/tronarko_luas.png");
        // ExportarSequenciaLunar.exportar(new Tozte(1, 1, 7001), 500, "/home/luan/Imagens/tronarko_luas_iluminacao.png");
        //ExportarSequenciaLunar.exportar(new Tozte(1, 1, 7000), 500, "/home/luan/Imagens/tronarko_luas_escuridao.png");


        //MapaCelestial s = new MapaCelestial();

        //ArrayList<Tozte_Intervalo> illuminatti = s.getIluminacao().mostrar(new Tozte(1, 1, 7001), 2);
        //ArrayList<Tozte_Intervalo> onnozzatti = s.getEscuridao().mostrar(new Tozte(1, 1, 7000), 2);

        //s.mostrarOcorrencias(illuminatti);
        // s.mostrarOcorrencias(onnozzatti);

        // ObservarCeu.mostrar(new Tozte(1, 1, 7000),"ILUMINACAO", Fases.CHEIA, Fases.CHEIA, Fases.CHEIA);

        AutoInt px = new AutoInt(50);
        AutoInt py = new AutoInt(50);


        mHiperarkoWidget_01 = new HiperarkoWidget(px.get(), py.get(), 1, mHoje.getTronarko());
        mHiperarkoWidget_02 = new HiperarkoWidget(px.mais_get(450), py.get(), 2, mHoje.getTronarko());
        mHiperarkoWidget_03 = new HiperarkoWidget(px.re_init(50), py.mais_get(200), 3, mHoje.getTronarko());
        mHiperarkoWidget_04 = new HiperarkoWidget(px.mais_get(450), py.get(), 4, mHoje.getTronarko());
        mHiperarkoWidget_05 = new HiperarkoWidget(px.re_init(50), py.mais_get(200), 5, mHoje.getTronarko());

        mHiperarkoWidget_06 = new HiperarkoWidget(px.mais_get(450), py.get(), 6, mHoje.getTronarko());
        mHiperarkoWidget_07 = new HiperarkoWidget(px.re_init(50), py.mais_get(200), 7, mHoje.getTronarko());
        mHiperarkoWidget_08 = new HiperarkoWidget(px.mais_get(450), py.get(), 8, mHoje.getTronarko());
        mHiperarkoWidget_09 = new HiperarkoWidget(px.re_init(50), py.mais_get(200), 9, mHoje.getTronarko());
        mHiperarkoWidget_10 = new HiperarkoWidget(px.mais_get(450), py.get(), 10, mHoje.getTronarko());

        mHiperarkoWidgetSelecionado = new HiperarkoWidget(950, 280, 1, mHoje.getTronarko());
        mHiperarkoWidgetSelecionado.setTamanhoCaixaTitulo(20);
    }


    @Override
    public void update(double dt) {


        mHoje = mTronarkum.getTozte();
        mAgora = mTronarkum.getHazde();


        mClicavel.update(dt, getWindows().getMouse().getX(), getWindows().getMouse().getY(), getWindows().getMouse().isPressed());


        mHoje = mHoje.adicionar_Superarko(mQuantos);
        //mAgora = mAgora.adicionar_Arco(mQuantos);

        // mHoje = mHoje.adicionar_Tronarko(mQuantos);

        mEventos = mEventum.getToztesComCor(mHoje.getTronarko());


        mHiperarkoWidgetSelecionado.setHiperarko(mHoje.getHiperarko());
        mHiperarkoWidgetSelecionado.setTronarko(mHoje.getTronarko());

        if (mAtualmente == null) {
            mAtualmente = mHoje;

            Momentum eMomentum = new Momentum();
            eMomentum.olharAoRedor(mAtualmente);

        } else {
            if (mHoje.isDiferente(mAtualmente)) {
                mAtualmente = mHoje;

                Momentum eMomentum = new Momentum();
                eMomentum.olharAoRedor(mAtualmente);

            }
        }

        getWindows().getMouse().liberar();

    }

    @Override
    public void draw(Renderizador r) {


        r.limpar(mCores.getBranco());

        mClicavel.onDraw(r);


        mTextoPequeno.setRenderizador(r);
        mTextoGrande.setRenderizador(r);
        mTextoPequenoBranco.setRenderizador(r);


        mTextoPequenoBranco.escreva(BTN_MENOS.getX() + 5, BTN_MENOS.getY() + 40, "-1");
        mTextoPequenoBranco.escreva(BTN_MAIS.getX() + 25, BTN_MAIS.getY() + 40, "+1");
        mTextoPequenoBranco.escreva(BTN_HOJE.getX() + 2, BTN_HOJE.getY() + 15, "HOJE");


        mEventum.alinhar_eventos(mEventos);


        mHiperarkoWidget_01.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_02.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_03.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_04.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_05.draw_hiperarko_com_infos(r, mHoje, mEventos);


        mHiperarkoWidget_06.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_07.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_08.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_09.draw_hiperarko_com_infos(r, mHoje, mEventos);
        mHiperarkoWidget_10.draw_hiperarko_com_infos(r, mHoje, mEventos);



        mTextoPequeno.escreva(950, 100 + 0, " -->> Hoje : " + mHoje.toString());
        mTextoPequeno.escreva(950, 100 + 50, " -->> Agora : " + mAgora.toString());
        mTextoPequeno.escreva(950, 100 + 100, " -->> Falta : " + mAgora.getTotalEttonsParaAcabarFormatado());

        mAgora.getTotalEttonsParaAcabar();


        int satelites = 950 + 300;


        r.drawImagemComAlfa(satelites, 100, mSatelatizadorAllux.get(mCeu.getAllux().getFaseIntTozte(mHoje)));
        r.drawImagemComAlfa(satelites + 60, 100, mSatelatizadorEttos.get(mCeu.getEttos().getFaseIntTozte(mHoje)));
        r.drawImagemComAlfa(satelites + 120, 100, mSatelatizadorUnnos.get(mCeu.getUnnos().getFaseIntTozte(mHoje)));

        mTextoPequeno.escreva(satelites - 10, 100 + 40, "Allux");
        mTextoPequeno.escreva(satelites + 60 - 10, 100 + 40, "Ettos");
        mTextoPequeno.escreva(satelites + 120 - 10, 100 + 40, "Unnos");

        // mTextoPequeno.escreva(pAllus - 10, ePosY + 80, mCeu.getAllux().getFaseIntTozte(mHoje) + " :: " + mCeu.getAllux().getFase(mHoje).toString());


        mHiperarkoWidgetSelecionado.draw_hiperarko_com_infos(r, mHoje, mEventos);


        TriBarraDeProgresso.draw(r, 950, 450, 380, 50, mHoje.getSuperarko(), 25, 40);


        int AVISO_X = 950;
        int AVISO_Y = 500;

        for (TozteCor tozte_evento : mEventum.getLegenda(mEventos)) {

            Marcador.marcar(r, AVISO_X, AVISO_Y, 20, 5, Cor.getRGB(tozte_evento.getCor()), mCores.getBranco());

            mTextoPequeno.escreva(AVISO_X + 30, AVISO_Y, tozte_evento.getNome());
            mTextoPequeno.escreva(AVISO_X + 250, AVISO_Y, " -->> " + tozte_evento.getComplemento());

            AVISO_Y += 30;

        }


        r.drawImagemComAlfa(1380, 350, mTronarkoImagemSignos.getSigno(mHoje.getSigno()));
        mTextoPequeno.escrevaCentralizado(1380 + 32 - 2, 420, mHoje.getSigno().toString());


    }


}

