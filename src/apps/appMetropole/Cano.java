package apps.appMetropole;

import azzal.geometria.Ponto;
import azzal.geometria.Quadrado;
import azzal.geometria.Retangulo;
import azzal.Renderizador;
import azzal.utilitarios.Cor;

import java.util.ArrayList;

public class Cano {

    private ArrayList<EixoCano> mEixos;
    private Ponto mLocalizacao;
    private int mTamanho;
    private Sentido mSentido;
    private Direcao mDirecao;
    private int mVazao;
    private EtiquetaCores mEtiquetaCores;
    private boolean entrou;

    public Cano(int eX, int eY, int eTamanho, Sentido eSentido, Direcao eDirecao, int eVazao) {

        mLocalizacao = new Ponto(eX, eY);
        mEixos = new ArrayList<EixoCano>();
        mTamanho = eTamanho;

        mSentido = eSentido;
        mDirecao = eDirecao;
        mVazao = eVazao;
        mEtiquetaCores = new EtiquetaCores();
        entrou = false;
    }


    public ArrayList<EixoCano> getEixos() {
        return mEixos;
    }

    public Ponto getLocalizacao() {
        return mLocalizacao;
    }

    public int getVazao() {
        return mVazao;
    }


    public void adicionarEixosEquilibrados(int eTam) {

        int eComeco = 0;


        if (mDirecao == Direcao.Frente) {

            if (mSentido == Sentido.Horizontal) {
                eComeco = mLocalizacao.getX();
            } else {
                eComeco = mLocalizacao.getY();
            }

            int eAtual = eComeco;
            int eFim = eComeco + mTamanho;
            while (eAtual < eFim) {
                mEixos.add(new EixoCano(eAtual - eComeco));
                eAtual += eTam;
            }
        } else {


            if (mSentido == Sentido.Horizontal) {
                eComeco = mLocalizacao.getX() + mTamanho;
            } else {
                eComeco = mLocalizacao.getY() + mTamanho;
            }

            int eAtual = eComeco;
            int eFim = eComeco - mTamanho;


            while (eAtual > eFim) {
                mEixos.add(new EixoCano(eComeco - eAtual));
                eAtual -= eTam;
                System.out.println(eAtual);
            }

        }


    }

    public int entrar(int eQuantidade) {

        int adicionar = 0;
        int sobrou = eQuantidade;

        // if (mDirecao == Direcao.Frente) {
        if (mEixos.size() > 0) {
            if (mEixos.get(0).isVazio()) {

                adicionar = 0;
                sobrou = 0;

                if (eQuantidade >= mVazao) {
                    sobrou = eQuantidade - mVazao;
                    adicionar = mVazao;
                } else {
                    adicionar = eQuantidade;
                }


                mEixos.get(0).encher(adicionar);
            }
        }
        // } else {

        //  if (mEixos.size() > 0) {
        //     if (mEixos.get(mEixos.size() - 1).isVazio()) {

        //       adicionar = 0;
        //     sobrou = 0;

        //     if (eQuantidade >= mVazao) {
        //         sobrou = eQuantidade - mVazao;
        //         adicionar = mVazao;
        //    } else {
        //         adicionar = eQuantidade;
        //      }


        //     mEixos.get(mEixos.size() - 1).encher(adicionar);
        // }
        //  }

        //  }


        entrou = true;

        return sobrou;
    }


    public void passar() {

        // if (mDirecao == Direcao.Frente) {
        int e = mEixos.size() - 1;

        while (e > 0) {

            EixoCano antes = mEixos.get(e - 1);
            EixoCano depois = mEixos.get(e);

            if (antes.isCheio() && depois.isVazio()) {
                depois.encher(antes.getQuantidade());
                antes.esvaziar();
            }

            e -= 1;
        }

        //  }


    }


    public void render(Renderizador mRenderizador) {


        if (mSentido == Sentido.Horizontal) {
            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX(), mLocalizacao.getY(), mTamanho, 10), new Cor(255, 50, 0));

            for (EixoCano eEixo : mEixos) {

                if (eEixo.isVazio()) {
                    if (mDirecao == Direcao.Frente) {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX() + eEixo.getPos(), mLocalizacao.getY(), 10), new Cor(255, 200, 0));
                    } else {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX() + mTamanho - eEixo.getPos(), mLocalizacao.getY(), 10), new Cor(255, 200, 0));
                    }
                } else {

                    if (mDirecao == Direcao.Frente) {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX() + eEixo.getPos(), mLocalizacao.getY(), 10), mEtiquetaCores.getAgua());
                    } else {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX() + mTamanho - eEixo.getPos(), mLocalizacao.getY(), 10), mEtiquetaCores.getAgua());
                    }

                }
            }


        } else {
            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX(), mLocalizacao.getY(), 10, mTamanho), new Cor(255, 50, 0));


            for (EixoCano eEixo : mEixos) {


                if (eEixo.isVazio()) {
                    if (mDirecao == Direcao.Frente) {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX(), mLocalizacao.getY() + eEixo.getPos(), 10), new Cor(255, 200, 0));
                    } else {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX(), mLocalizacao.getY() + mTamanho - eEixo.getPos(), 10), new Cor(255, 200, 0));
                    }
                } else {

                    if (mDirecao == Direcao.Frente) {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX(), mLocalizacao.getY() + eEixo.getPos(), 10), mEtiquetaCores.getAgua());
                    } else {
                        mRenderizador.drawRect_Pintado(new Quadrado(mLocalizacao.getX(), mLocalizacao.getY() + mTamanho - eEixo.getPos(), 10), mEtiquetaCores.getAgua());
                    }

                }
            }


        }

    }


    public void conectarCano(Cano eOutro) {

        EixoCano antes = this.getEixos().get(this.getEixos().size() - 1);
        EixoCano depois = eOutro.getEixos().get(0);

        if (antes.isCheio() && depois.isVazio()) {
            depois.encher(antes.getQuantidade());
            antes.esvaziar();
        }

    }

}
