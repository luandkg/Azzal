package apps.AppTronarko;


import azzal.Cenarios.Cena;
import azzal.Cores;
import azzal.Renderizador;
import azzal.Utils.Cor;
import azzal.Windows;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import libs.Tronarko.Tronarko;
import libs.Tronarko.Tozte;
import libs.Tronarko.Hazde;
import libs.Tronarko.Superarkos;

import libs.Tronarko.Agenda.Alarme;
import libs.Tronarko.Agenda.Lembrete;
import libs.Tronarko.Utils.FluxoTemporal;

import libs.Tronarko.Utils.Ordenador;
import azzal_ui.Interface.Acao;
import azzal_ui.Interface.BotaoCor;
import azzal_ui.Interface.Clicavel;
import azzal_ui.Marcador;

public class AppAlarme extends Cena {


    private Windows mWindows;

    private Fonte TextoGrande;
    private Fonte TextoGrande_Hoje;

    private Fonte TextoPequeno;
    private Fonte TextoPequeno_Sel;

    private Fonte TextoPequeno_Hoje;
    private Fonte TextoPequeno_Hoje2;
    private Fonte TextoPequeno_MuitoGrande;

    private Tronarko TronarkoC;

    private BotaoCor BTN_DISPENSADOR;


    private Tozte mHoje;
    private Hazde mAgora;

    private int mQuantos;

    private int mTocar;

    private Alarme mAlarme;
    private Clicavel mClicavel;
    private Cores mCores;


    private int HIPERARKO_POS_X = 50;
    private int HIPERARKO_POS_Y = 280;


    private HiperarkoWidget mHiperarkoWidget;

    @Override
    public void iniciar(Windows eWindows) {

        mWindows = eWindows;
        mWindows.setTitle("Alarme");

        mCores = new Cores();

        TextoGrande = new FonteRunTime(mCores.getPreto(), 15);
        TextoGrande_Hoje = new FonteRunTime(mCores.getPreto(), 11);

        TextoPequeno = new FonteRunTime(mCores.getPreto(), 11);
        TextoPequeno_Sel = new FonteRunTime(mCores.getVermelho(), 11);

        TextoPequeno_Hoje = new FonteRunTime(mCores.getVermelho(), 11);
        TextoPequeno_Hoje2 = new FonteRunTime(mCores.getBranco(), 11);
        TextoPequeno_MuitoGrande = new FonteRunTime(mCores.getBranco(), 18);

        TronarkoC = new Tronarko();

        mClicavel = new Clicavel();

        BTN_DISPENSADOR = mClicavel.criarBotaoCorDesenharAcima(new BotaoCor(600, 480, 200, 50, new Cor(50, 90, 156)));
        BTN_DISPENSADOR.setAcao(new Acao() {
            @Override
            public void onClique() {
                mAlarme.dispensar(mHoje, mAgora);
                System.out.println("Dispensar : " + mAlarme.quandoFoiDispensado());
            }
        });


        mHoje = TronarkoC.getTozte();
        mQuantos = 0;

        mAlarme = new Alarme();

        mAlarme.marcarSimples(TronarkoC.getTozte(), TronarkoC.getHazde().adicionar_Itta(-2).getComEttonZerado());
        mAlarme.marcarSimples(TronarkoC.getTozte(), TronarkoC.getHazde().getComEttonZerado());
        mAlarme.marcarSimples(TronarkoC.getTozte(), TronarkoC.getHazde().adicionar_Itta(+1).getComEttonZerado());
        mAlarme.marcarSimples(TronarkoC.getTozte(), TronarkoC.getHazde().adicionar_Itta(+2).getComEttonZerado());
        mAlarme.marcarSimples(TronarkoC.getTozte(), TronarkoC.getHazde().adicionar_Itta(+3).getComEttonZerado());
        mAlarme.marcarSimples(TronarkoC.getTozte(), TronarkoC.getHazde().adicionar_Itta(+4).getComEttonZerado());

        mAlarme.marcarSuperarko(Superarkos.ALFA, new Hazde(2, 0, 0));
        mAlarme.marcarSuperarko(Superarkos.ALFA, new Hazde(6, 30, 0));
        mAlarme.marcarSuperarko(Superarkos.ALFA, new Hazde(8, 50, 0));

        mAlarme.marcarSuperarko(Superarkos.GAMA, new Hazde(5, 0, 0));

        mAlarme.marcarSuperarko(Superarkos.IOTA, new Hazde(6, 30, 0));
        mAlarme.marcarSuperarko(Superarkos.IOTA, new Hazde(8, 30, 0));

        // mAlarme.limparTudo();

        mAlarme.marcarSimples(FluxoTemporal.ultimo_tozte_hiperarko(mHoje), TronarkoC.getHazde().getComEttonZerado());
        mAlarme.marcarSimples(FluxoTemporal.amanha(mHoje), TronarkoC.getHazde().adicionar_Arco(+2).getComEttonZerado());

        mAlarme.marcarSimples(FluxoTemporal.futuro(mHoje, 12), TronarkoC.getHazde().adicionar_Arco(+2).getComEttonZerado());
        mAlarme.marcarSimples(FluxoTemporal.passado(mHoje, 12), TronarkoC.getHazde().adicionar_Arco(+2).getComEttonZerado());

        mTocar = 1;

        // mLigarCron = false;
        // mCronLigar = new Hazde(6, 61, 0);


        mHiperarkoWidget = new HiperarkoWidget(HIPERARKO_POS_X, HIPERARKO_POS_Y, mHoje.getHiperarko(), mHoje.getTronarko());
        mHiperarkoWidget.selecionar(TronarkoC.getTozte());
        mHiperarkoWidget.setPodeSelecionar(true);

        TextoPequeno_Sel = new FonteRunTime(mHiperarkoWidget.getCorTocando(), 11);

    }


    @Override
    public void update(double dt) {


        mHoje = TronarkoC.getTozte();
        mAgora = TronarkoC.getHazde();


        mClicavel.update(dt, getWindows().getMouse().getX(), getWindows().getMouse().getY(), getWindows().getMouse().isPressed());

        //System.out.println("Clicavel : " + mClicavel.getClicado()); 

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();


        mHiperarkoWidget.update(px, py, getWindows().getMouse().isClicked());


        mHoje = mHoje.adicionar_Superarko(mQuantos);

        // System.out.println("Cron Ligar : " + mCronLigar.getTexto());


        getWindows().getMouse().liberar();

    }


    @Override
    public void draw(Renderizador r) {

        r.limpar(mCores.getBranco());
        mClicavel.onDraw(r);

        //Hoje = Hoje.adicionar_Tronarko(5);

        TextoPequeno.setRenderizador(r);
        TextoPequeno_Sel.setRenderizador(r);
        TextoGrande_Hoje.setRenderizador(r);
        TextoGrande.setRenderizador(r);
        TextoPequeno_Hoje.setRenderizador(r);
        TextoPequeno_Hoje2.setRenderizador(r);
        TextoPequeno_MuitoGrande.setRenderizador(r);


        TextoPequeno_MuitoGrande.escreva(BTN_DISPENSADOR.getX()+30, BTN_DISPENSADOR.getY()+10, "DISPENSAR");


        TextoPequeno.escreva(50, 100, " -->> Hoje : " + mHoje.toString());
        TextoPequeno.escreva(50, 150, " -->> Agora : " + mAgora.toString());


        TextoGrande.escreva(600, 100, " AGENDA : " + mHoje.getSuperarko_Status().toString());


        draw_agenda(r, 610, 150);


        mHiperarkoWidget.draw_hiperarko(r, mHoje, mAlarme, mHoje.getHiperarko());


        draw_agenda_selecionado(r, 50, 500);

    }


    public void draw_agenda(Renderizador r, int px, int py) {

        for (Lembrete eLembrete : Ordenador.OrdenarLembretes(mAlarme.getLembretes(mHoje))) {

            boolean estaTocando = mAlarme.estaTocando(eLembrete, mTocar, mHoje, mAgora);

            if (estaTocando) {

                Marcador.marcar(r, px, py, 20, 5, mHiperarkoWidget.getCorTocando(), mCores.getBranco());

                TextoPequeno_Sel.escreva(px + 30, py, eLembrete.getTozte().getTexto() + " " + eLembrete.getHazde().getTextoSemUzzons());

            } else {
                TextoPequeno.escreva(px + 30, py, eLembrete.getTozte().getTexto() + " " + eLembrete.getHazde().getTextoSemUzzons());
            }

            py += 30;

        }

    }

    public void draw_agenda_selecionado(Renderizador r, int px, int py) {

        if (mHiperarkoWidget.getSelecionado().temValor()) {

            Tozte mTozteSelecionado = mHiperarkoWidget.getSelecionado().get();

            for (Lembrete lembrete : Ordenador.OrdenarLembretes(mAlarme.getLembretes(mTozteSelecionado))) {

                Cor eCor = mHiperarkoWidget.getCorEventos();
                boolean passou = false;

                if (mTozteSelecionado.isMenorQue(mHoje)) {
                    passou = true;
                } else if (mTozteSelecionado.isIgual(mHoje)) {
                    if (mAgora.isMenor(lembrete.getHazde())) {
                        passou = true;
                    }
                }

                if (passou) {
                    Marcador.marcar(r, px, py - 3, 20, 5, eCor, mCores.getBranco());
                } else {
                    r.drawRect_Pintado(px, py - 3, 20, 20, eCor);
                }

                TextoPequeno.escreva(px + 30, py, lembrete.getTozte().getTexto() + " -->> " + lembrete.getHazde().getTextoSemUzzons());

                py += 30;

            }

        }

    }
}
