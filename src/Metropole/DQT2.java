package Metropole;

import Azzal.Formatos.Ponto;
import Azzal.Formatos.Retangulo;
import Azzal.Renderizador;
import Azzal.Utils.Cor;

import java.util.Random;

public class DQT2 {

    private Ponto mLocalizacao;
    private boolean mStatus;
    private boolean mStatusEsquerda;
    private boolean mStatusDireita;
    private int mEscolhido;
    private int mEmissaoEsquerda;
    private int mEmissaoDireita;
    private Modo mModo;
    private boolean temPronto;
    private int mRetirarTudo;

    public DQT2(int eX, int eY, Modo eModo) {

        mLocalizacao = new Ponto(eX, eY);
        mStatus = false;
        mStatusEsquerda = false;
        mStatusDireita = false;
        mEscolhido = 0;
        mEmissaoEsquerda = 0;
        mEmissaoDireita = 0;
        mModo = eModo;
        temPronto = false;
        mRetirarTudo = 0;
    }

    public void abrir() {
        mStatus = true;
    }

    public void fechar() {
        mStatus = false;
    }

    public boolean isAberto() {
        return mStatus == true;
    }

    public boolean isFechado() {
        return mStatus == false;
    }


    public void abrirEsquerda() {
        mStatusEsquerda = true;
    }

    public void fecharEsquerda() {
        mStatusEsquerda = false;
    }

    public boolean isAbertoEsquerda() {
        return mStatusEsquerda == true;
    }

    public boolean isFechadoEsquerda() {
        return mStatusEsquerda == false;
    }

    public void abrirDireita() {
        mStatusDireita = true;
    }

    public void fecharDireita() {
        mStatusDireita = false;
    }

    public boolean isAbertoDireita() {
        return mStatusDireita == true;
    }

    public boolean isFechadoDireita() {
        return mStatusDireita == false;
    }


    public void render(Renderizador mRenderizador) {


        mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX(), mLocalizacao.getY(), 20, 80), new Cor(255, 50, 150));

        if (mModo == Modo.Abaixo) {

            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() - 70, mLocalizacao.getY() + 80, 140, 20), new Cor(255, 50, 150));

            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() - 70, mLocalizacao.getY() + 80, 20, 80), new Cor(255, 50, 150));

            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 20 + 50, mLocalizacao.getY() + 80, 20, 80), new Cor(255, 50, 150));

            if (isAberto()) {
                mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 5, mLocalizacao.getY() + 5, 10, 10), new Cor(255, 255, 255));
            }


            if (isAbertoEsquerda()) {
                mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() - 70 + 5, mLocalizacao.getY() + 160 - 15, 10, 10), new Cor(255, 255, 255));
            }

            if (isAbertoDireita()) {
                mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 20 + 55, mLocalizacao.getY() + 160 - 15, 10, 10), new Cor(255, 255, 255));
            }

        } else if (mModo == Modo.Acima) {


            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() - 70, mLocalizacao.getY() - 20, 140, 20), new Cor(255, 50, 150));

            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() - 70, mLocalizacao.getY() - 80, 20, 80), new Cor(255, 50, 150));

            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 20 + 50, mLocalizacao.getY() - 80, 20, 80), new Cor(255, 50, 150));

            if (isAberto()) {
                mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 5, mLocalizacao.getY() + 65, 10, 10), new Cor(255, 255, 255));
            }


            if (isAbertoEsquerda()) {
                mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() - 70 + 5, mLocalizacao.getY() - 60 - 15, 10, 10), new Cor(255, 255, 255));
            }

            if (isAbertoDireita()) {
                mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 20 + 55, mLocalizacao.getY() - 60 - 15, 10, 10), new Cor(255, 255, 255));
            }

        }


    }


    public int getEmissaoEsquerda() {
        return mEmissaoEsquerda;
    }

    public int getEmissaoDireita() {
        return mEmissaoDireita;
    }

    public void conectar(Pressao ePressao, Cano eCano) {

        mEmissaoEsquerda = 0;
        mEmissaoDireita = 0;

        if (this.isAberto()) {

            if (ePressao == Pressao.Saindo) {


            } else if (ePressao == Pressao.Entrando) {


                if (eCano.getEixos().get(eCano.getEixos().size() - 1).isCheio()) {

                    if (mEscolhido == 0) {

                        Random eSorte = new Random();
                        int num = eSorte.nextInt(100);
                        if (num >= 50) {
                            mEscolhido = +1;
                        } else {
                            mEscolhido = -1;
                        }
                    } else {
                        mEscolhido = mEscolhido * (-1);
                    }


                    if (mEscolhido == -1) {
                        if (isAbertoEsquerda()) {
                            mEmissaoEsquerda = eCano.getEixos().get(eCano.getEixos().size() - 1).getQuantidade();
                            eCano.getEixos().get(eCano.getEixos().size() - 1).esvaziar();
                        }

                    } else {
                        if (isAbertoDireita()) {
                            mEmissaoDireita = eCano.getEixos().get(eCano.getEixos().size() - 1).getQuantidade();
                            eCano.getEixos().get(eCano.getEixos().size() - 1).esvaziar();

                        }

                    }


                }


            }

        }
    }

    public void conectarSaindo(Tanque T1, Tanque T2, Cano eCano) {

        mEmissaoEsquerda = 0;
        mEmissaoDireita = 0;

        int eVazao = eCano.getVazao();


        if (isAberto() && temPronto == false) {

            if (mEscolhido == 0) {

                Random eSorte = new Random();
                int num = eSorte.nextInt(100);
                if (num >= 50) {
                    mEscolhido = +1;
                } else {
                    mEscolhido = -1;
                }
            } else {
                mEscolhido = mEscolhido * (-1);
            }

            int esquerdaRetirar = 0;
            int esquerdaRetirarMais = 0;
            int direitaRetirar = 0;
            int direitaRetirarMais = 0;

            if (mEscolhido == -1) {
                if (isAbertoEsquerda()) {
                    if (T1.getVolume() >= eVazao) {
                        esquerdaRetirar = T1.retirar(eVazao);
                    }
                }
                if (esquerdaRetirar < eVazao) {
                    int mais = eVazao - esquerdaRetirar;
                    if (isAbertoDireita()) {
                        if (T2.getVolume() >= mais) {
                            esquerdaRetirarMais = T2.retirar(eVazao);
                        }
                    }
                }
            } else {

                if (isAbertoDireita()) {
                    if (T2.getVolume() >= eVazao) {
                        direitaRetirar = T2.retirar(eVazao);
                    }
                }
                if (direitaRetirar < eVazao) {
                    int mais = eVazao - direitaRetirar;
                    if (isAbertoEsquerda()) {
                        if (T1.getVolume() >= mais) {
                            direitaRetirarMais = T1.retirar(eVazao);
                        }
                    }
                }

            }

            int somar = esquerdaRetirar + esquerdaRetirarMais + direitaRetirar + direitaRetirarMais;
            if (somar > 0) {
                mRetirarTudo = somar;
                temPronto = true;
                System.out.println("Retirar :: " + somar + " em " + mEscolhido);
            }

        }


    }

    public int retirarTudo() {
        temPronto = false;
        return mRetirarTudo;
    }

    public boolean temPronto() {
        return temPronto;
    }

    public void deixarParado(int eValor) {
        mRetirarTudo=eValor;
        temPronto = true;
    }

}
