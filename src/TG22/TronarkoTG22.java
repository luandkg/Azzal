package TG22;

import Azzal.Cores;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.FonteDupla;
import Letrum.FonteDuplaRunTime;
import Letrum.Maker.FonteRunTime;
import Luan.STTY;
import Tronarko.*;
import Tronarko.Eventos.Eventum;
import Tronarko.Utils.IntTronarko;
import Tronarko.Utils.StringTronarko;
import Tronarko.Utils.TozteCor;

import java.awt.*;
import java.util.ArrayList;

public class TronarkoTG22 {


    private FonteDupla mTextoGrande;

    private FonteDupla mTextoPequeno;

    private Fonte mTextoPequenoBranco;
    private Fonte mTextoMicroBranco;

    private Tronarko mTronarkum;
    private Eventum mEnventum;

    private Tozte mAtualmente;
    private Tozte mHoje;

    private Cores mCores;

    public TronarkoTG22() {

        mCores = new Cores();

        mTextoGrande = new FonteDuplaRunTime(mCores.getPreto(), mCores.getVermelho(), 20);
        mTextoPequeno = new FonteDuplaRunTime(mCores.getPreto(), mCores.getVermelho(), 11);
        mTextoPequenoBranco = new FonteRunTime(mCores.getBranco(), 11);
        mTextoMicroBranco = new FonteRunTime(mCores.getBranco(), 11);

        mTronarkum = new Tronarko();
        mEnventum = new Eventum();

        mAtualmente = null;
        mHoje = null;


        mHoje = mTronarkum.getTozte();
    }

    public void draw(Renderizador r, ArrayList<Ficha> projeto, double META_ALTURA, double META_PESO) {


        r.limpar(mCores.getBranco());


        mTextoPequeno.setRenderizador(r);
        mTextoGrande.setRenderizador(r);
        mTextoPequenoBranco.setRenderizador(r);
        mTextoMicroBranco.setRenderizador(r);


        mTextoGrande.escreva(20, 20, "PROJETO TG22 - LUAN FREITAS");


        ArrayList<TozteCor> mInfos = new ArrayList<TozteCor>();

        if (projeto.size() > 0) {

            StringTronarko st = new StringTronarko();

            Tozte ultimo = st.getTozte(projeto.get(0).getTozte());
            Tozte primeiro = st.getTozte(projeto.get(projeto.size() - 1).getTozte());

            while (primeiro.isMenorIgualQue(ultimo)) {
                mInfos.add(new TozteCor("TG22", primeiro, new Color(255, 165, 23)));
                primeiro = primeiro.adicionar_Superarko(1);
            }


        }


        int CAIXA_X = 40;
        int CAIXA_Y = 90;

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

        int RX = 950;
        int RY = 90;

        double peso_primeiro = projeto.get(projeto.size() - 1).getPeso();

        mTextoGrande.escreva(RX, RY, "MEU PROJETO");

        RY += 50;

        Tronarko eTronarko = new Tronarko();

        String comecou = eTronarko.getData("03/01/2022").toString();

        mTextoPequeno.escreveLinha(RY, RX, RX + 150, "- META ", STTY.espacar_antes("" + STTY.f2zerado(META_PESO), 6) + " moz em ( " + STTY.doubleNumC2(Corpo.getNivel(META_PESO, Corpo.getAltura(META_ALTURA))) + " fuzz ) ");
        RY += 35;

        mTextoPequeno.escreveLinha(RY, RX, RX + 150, "- INIT ", STTY.espacar_antes("" + STTY.f2zerado(projeto.get(projeto.size() - 1).getPeso()), 6) + " moz em ( " + STTY.doubleNumC2(Corpo.getNivel(projeto.get(projeto.size() - 1).getPeso(), projeto.get(projeto.size() - 1).getAltura())) + " fuzz ) ");
        RY += 20;

        if (projeto.size() > 0) {

            double peso_atual = projeto.get(0).getPeso();

            mTextoPequeno.escreveLinha(RY, RX, RX + 150, "- HOJE ", STTY.espacar_antes("" + peso_atual, 6) + " moz em ( " + STTY.doubleNumC2(Corpo.getNivel(peso_atual, Corpo.getAltura(META_ALTURA))) + " fuzz ) ");
            RY += 20;

            System.out.println("");


            if (peso_atual > META_PESO) {
                mTextoPequeno.escreveLinha(RY, RX, RX + 150, "- OBJETIVO  ", "FALTA " + STTY.doubleNumC2((peso_atual - META_PESO)) + " moz !");
                RY += 20;

                double diferenca = 0.0;
                String status = "";

                if (peso_primeiro > peso_atual) {
                    diferenca = peso_primeiro - peso_atual;
                    status = "DIMINUIU";
                } else if (peso_primeiro < peso_atual) {
                    diferenca = peso_atual - peso_primeiro;
                    status = "AUMENTOU";
                }

                mTextoPequeno.escreveLinha(RY, RX, RX + 150, "- PROGRESSO ", status + " " + STTY.doubleNumC2(diferenca) + " moz !");
                RY += 20;

                mTextoPequeno.escreveLinha(RY, RX, RX + 150, "- DURAÇÃO   ", IntTronarko.getSuperarkosDiferenca(comecou, eTronarko.getTozte().toString()) + " superarkos !");
                RY += 20;

            } else {
                mTextoPequeno.escreveLinha(RY, RX, RX + 150, "- OBJETIVO ", "CONCLUIDO COM SUCESSO !");
                RY += 20;

            }


        }

        RY += 30;

        for (Ficha eFicha : projeto) {

            long superarkos = IntTronarko.getSuperarkosDiferenca(eFicha.getTozte(), mHoje.toString());

            String faixa_temporal = TG22.getComConcordancia(superarkos, "superarko atrás", "superarkos atrás");

            double peso_atual = eFicha.getPeso();

            double diferenca = 0.0;
            String status = "";

            Cor eCor = new Cor(0, 0, 0);

            if (peso_primeiro > peso_atual) {
                diferenca = peso_primeiro - peso_atual;
                status = "DIMINUIU";
                eCor = new Cor(255, 30, 60);
            } else if (peso_primeiro < peso_atual) {
                diferenca = peso_atual - peso_primeiro;
                status = "AUMENTOU";
                eCor = new Cor(120, 30, 60);
            }


            mTextoPequeno.escreva(RX, RY, eFicha.getTozte() + " :: " + faixa_temporal);

            int i_diferenca = (int) diferenca;

            int escalado = i_diferenca * 8;

            r.drawRect_Pintado(RX + 320, RY, escalado, 20, eCor);

            String inteiro = String.valueOf(i_diferenca);

            if (escalado > 15) {


                if (escalado > 50) {
                    mTextoMicroBranco.escreva(RX + 320, RY + 2, inteiro + " fuzz");
                } else {
                    mTextoMicroBranco.escreva(RX + 320, RY + 2, inteiro);
                }

                // LIGAMENTO
                r.drawRect_Pintado(RX, RY + 26, 305, 2, new Cor(255, 20, 0));
                r.drawRect_Pintado(RX + 305, RY + 8, 2, 20, new Cor(255, 20, 0));
                r.drawRect_Pintado(RX + 305, RY + 8, 15, 2, new Cor(255, 20, 0));

            }


            RY += 40;

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

                    if (mTozte.isIgual(InfoC.getTozte())) {
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

                    if (mPassadoInfoNome.contentEquals(mAtualInfoNome) && mSuperarko <= 50 && mSuperarko > 1) {
                        r.drawRect_Pintado(QX - 3 - 18, QY + 5, 20, 5, Cor.getRGB(mCor));
                    }
                }

                anteriorComFundo = comFundo;
                mPassadoInfoNome = mAtualInfoNome;

                String mSuperNum = String.valueOf(mSuperarko);
                if (mSuperNum.length() == 1) {
                    mSuperNum = "0" + mSuperNum;
                }

                if (mHoje.isIgual(mTozte)) {
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

        if (eOutro_Ref.isMaiorQue(eOutro_AlgumTozte)) {

            while (eOutro_Ref.isMaiorQue(eOutro_AlgumTozte)) {
                eOutro_Ref = eOutro_Ref.adicionar_Superarko(-1);
                dif += 1;
            }

        } else if (eOutro_Ref.isMenorQue(eOutro_AlgumTozte)) {

            while (eOutro_Ref.isMenorQue(eOutro_AlgumTozte)) {
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
