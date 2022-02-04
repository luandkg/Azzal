package AppTronarko;

import Azzal.Cenarios.Cena;
import Azzal.Cores;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Azzal.Windows;
import Letrum.Fonte;
import Letrum.FonteDupla;
import Letrum.FonteDuplaRunTime;
import Letrum.Maker.FonteRunTime;
import Tronarko.Eventos.Eventum;
import Tronarko.*;
import Tronarko.Satelites.Ceu;
import UI.Interface.Acao;
import UI.Interface.BotaoCor;
import UI.Interface.Clicavel;

import java.awt.*;
import java.util.ArrayList;

public class AppTronarko extends Cena {


    private FonteDupla mTextoGrande;

    private FonteDupla mTextoPequeno;

    private Fonte mTextoPequenoBranco;

    private Tronarko mTronarkum;
    private Eventum mEnventum;
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

    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Tronarko com Azzal");

        mCores = new Cores();

        mTextoGrande = new FonteDuplaRunTime(mCores.getPreto(), mCores.getVermelho(), 20);
        mTextoPequeno = new FonteDuplaRunTime(mCores.getPreto(), mCores.getVermelho(), 11);
        mTextoPequenoBranco = new FonteRunTime(mCores.getBranco(), 11);

        mTronarkum = new Tronarko();
        mEnventum = new Eventum();
        mCeu = new Ceu();

        mAtualmente = null;
        mHoje = null;
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

    }

    @Override
    public void update(double dt) {


        mHoje = mTronarkum.getTozte();
        mAgora = mTronarkum.getHazde();


        mClicavel.update(dt, getWindows().getMouse().getX(), getWindows().getMouse().getY(), getWindows().getMouse().isPressed());


        mHoje = mHoje.adicionar_Superarko(mQuantos);
        mAgora = mAgora.adicionar_Arco(mQuantos);

        if (mAtualmente == null) {
            mAtualmente = mHoje;
            olharAoRedor();
        } else {
            if (mHoje.Diferente(mAtualmente)) {
                mAtualmente = mHoje;
                olharAoRedor();
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


        ArrayList<TozteCor> mInfos = mEnventum.getToztesComCor(mHoje.getTronarko());


        int CAIXA_X = 40;
        int CAIXA_Y = 80;

        int CAIXA_ALTURA = 190;

        draw_hiperarko(r, mHoje, mInfos, 1, 0, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 3, 1, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 5, 2, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 7, 3, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 9, 4, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);

        CAIXA_X = 500;

        draw_hiperarko(r, mHoje, mInfos, 2, 0, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 4, 1, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 6, 2, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 8, 3, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);
        draw_hiperarko(r, mHoje, mInfos, 10, 4, CAIXA_X, CAIXA_Y, CAIXA_ALTURA);

        int LX = 950;
        int LY = 200;

        int ePosY = 100;

        mTextoPequeno.escreva(LX, ePosY, " -->> Hoje : " + mHoje.toString());
        mTextoPequeno.escreva(LX, ePosY + 50, " -->> Agora : " + mAgora.toString());
        mTextoPequeno.escreva(LX, ePosY + 100, " -->> Falta : " + mAgora.getTotalEttonsParaAcabarFormatado());

        mAgora.getTotalEttonsParaAcabar();


        mTextoPequeno.escreveLinha(LY - 100, LX + 300, LX + 430, " -->> Allux", mCeu.allux_getFase(mHoje).toString());
        mTextoPequeno.escreveLinha(LY - 50, LX + 300, LX + 430, " -->> Ettos", mCeu.ettos_getFase(mHoje).toString());
        mTextoPequeno.escreveLinha(LY, LX + 300, LX + 430, " -->> Unnos", mCeu.unnos_getFase(mHoje).toString());


        draw_hiperarko(r, mHoje, mInfos, mHoje.getHiperarko(), 0, LX + 50, 280, CAIXA_ALTURA);

        draw_hiperarko_progresso(r, LX + 33, 450);


        LY = 500;
        LX = 950;

        for (TozteCor tozte_info : mEnventum.getLegenda(mInfos)) {


            r.drawRect_Pintado(LX, LY, 20, 20, Cor.getRGB(tozte_info.getCor()));

            r.drawRect_Pintado(LX + 5, LY + 5, 10, 10, mCores.getBranco());


            if (tozte_info.getNome().contains("Reciclum")) {
                mTextoPequeno.escreva(LX + 30, LY, tozte_info.getNome());
                mTextoPequeno.escreva(LX + 250, LY, " -->> " + tozte_info.getComplemento());
            } else {
                mTextoPequeno.escreva(LX + 30, LY, tozte_info.getNome());
                mTextoPequeno.escreva(LX + 250, LY, " -->> " + tozte_info.getComplemento());
            }

            LY += 50;

        }

    }


    public void draw_hiperarko(Renderizador r, Tozte Hoje, ArrayList<TozteCor> mInfos, int mHiperarko, int Faixador, int CAIXA_X, int CAIXA_Y, int CAIXA_ALTURA) {

        int eTronarko = Hoje.getTronarko();

        if (Hoje.getHiperarko() == (mHiperarko)) {
            mTextoGrande.escrevaSelecionada(CAIXA_X - 10, (CAIXA_ALTURA * Faixador) + CAIXA_Y, Hiperarkos.getNumerado(mHiperarko));
        } else {
            mTextoGrande.escreva(CAIXA_X - 10, (CAIXA_ALTURA * Faixador) + CAIXA_Y, Hiperarkos.getNumerado(mHiperarko));
        }

        for (int s = 0; s < 10; s++) {

            String eMega = Superarkos.get(s + 1).getCapital();

            if ((Hoje.getTronarko() == eTronarko) && (Hoje.getHiperarko() == mHiperarko)) {

                if (eMega.contentEquals(Hoje.Superarko_capital())) {
                    mTextoPequeno.escrevaSelecionada((CAIXA_X - 10) + (s * 40),
                            ((CAIXA_ALTURA * Faixador) + 30) + CAIXA_Y, eMega);

                } else {
                    mTextoPequeno.escreva((CAIXA_X - 10) + (s * 40),
                            ((CAIXA_ALTURA * Faixador) + 30) + CAIXA_Y, eMega);

                }

            } else {

                mTextoPequeno.escreva((CAIXA_X - 10) + (s * 40),
                        ((CAIXA_ALTURA * Faixador) + 30) + CAIXA_Y, eMega);

            }

        }

        int mSuperarko = 1;

        String mAtualInfoNome = "";
        String mPassadoInfoNome = "";

        for (int m = 0; m < 5; m++) {

            boolean anteriorComFundo = false;

            for (int s = 0; s < 10; s++) {

                int QX = (CAIXA_X - 10) + (s * 40) + 5;
                int QY = (((CAIXA_ALTURA * Faixador) + 30) + ((m + 1) * 20)) + CAIXA_Y + 10;

                Tozte mTozte = new Tozte(mSuperarko, mHiperarko, eTronarko);

                Color mCor = Color.WHITE;

                boolean comFundo = false;

                for (TozteCor InfoC : mInfos) {

                    if (mTozte.Igual(InfoC.getTozte())) {
                        mCor = InfoC.getCor();
                        comFundo = true;
                        mAtualInfoNome = InfoC.getNome();
                        break;
                    }

                }

                if (comFundo) {
                    r.drawRect_Pintado(QX - 1, QY - 2, 25, 20, Cor.getRGB(mCor));
                }

                if (comFundo & anteriorComFundo) {

                    if (mPassadoInfoNome.contentEquals("Festival da Água") && mAtualInfoNome.contentEquals("Cruzada das Águas")) {
                        mPassadoInfoNome = "Cruzada das Águas";
                    }

                    if (mPassadoInfoNome.contentEquals(mAtualInfoNome)) {
                        r.drawRect_Pintado(QX - 3 - 18, QY + 5, 20, 5, Cor.getRGB(mCor));
                    }
                }

                anteriorComFundo = comFundo;
                mPassadoInfoNome = mAtualInfoNome;

                String mSuperNum = String.valueOf(mSuperarko);
                if (mSuperNum.length() == 1) {
                    mSuperNum = "0" + mSuperNum;
                }

                if (mHoje.Igual(mTozte)) {
                    mTextoPequeno.escrevaSelecionada(QX, QY, mSuperNum);
                } else {
                    mTextoPequeno.escreva(QX, QY, mSuperNum);
                }


                mSuperarko += 1;

            }

        }

    }

    public void draw_hiperarko_progresso(Renderizador r, int px, int py) {

        int mTamanho = 380;

        double eTaxa = (double) mTamanho / 50.0;
        int eCompleto = (int) (eTaxa * (double) mHoje.getSuperarko());


        r.drawRect_Pintado(px, py, 5, 20, mCores.getPreto());
        r.drawRect_Pintado(px + 7, py, 5, 20, mCores.getPreto());
        r.drawRect_Pintado(px + 12, py + 7, mTamanho, 5, mCores.getPreto());

        if (mHoje.getSuperarko() > 0 && mHoje.getSuperarko() < 25) {
            r.drawRect_Pintado(px + 12, py + 5, eCompleto, 10, mCores.getVerde());
        } else if (mHoje.getSuperarko() >= 25 && mHoje.getSuperarko() < 40) {
            r.drawRect_Pintado(px + 12, py + 5, eCompleto, 10, mCores.getAmarelo());
        } else {
            r.drawRect_Pintado(px + 12, py + 5, eCompleto, 10, mCores.getVermelho());
        }


    }

    public int getDistancia(Tozte eReferencia, Tozte eAlgumTozte) {

        int dif = 0;
        Tozte eOutro_Ref = eReferencia.getCopia();
        Tozte eOutro_AlgumTozte = eAlgumTozte.getCopia();

        if (eOutro_Ref.MaiorQue(eOutro_AlgumTozte)) {

            while (eOutro_Ref.MaiorQue(eOutro_AlgumTozte)) {
                eOutro_Ref = eOutro_Ref.adicionar_Superarko(-1);
                dif += 1;
            }

        } else if (eOutro_Ref.MenorrQue(eOutro_AlgumTozte)) {

            while (eOutro_Ref.MenorrQue(eOutro_AlgumTozte)) {
                eOutro_Ref = eOutro_Ref.adicionar_Superarko(+1);
                dif -= 1;
            }

        } else {
            dif = 0;
        }

        return dif;

    }

    public void olharAoRedor() {

        System.out.println();
        System.out.println("Hoje : " + mAtualmente.getTexto());

        Tozte mAntes = mAtualmente.adicionar_Superarko(-50);
        Tozte mDepois = mAtualmente.adicionar_Superarko(+50);

        ArrayList<TozteCor> mInfos = mEnventum.getToztesComCorEmIntervalo(mAntes, mDepois);

        for (TozteCor eTozteCor : mInfos) {
            System.out.println(" -->> " + eTozteCor.getNome() + " :: " + eTozteCor.getTozte().getTexto() + " -->> " + getDistancia(mAtualmente, eTozteCor.getTozte()));
        }


    }


}

