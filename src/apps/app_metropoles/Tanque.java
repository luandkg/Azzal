package apps.app_metropoles;

import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

public class Tanque {

    private int mCapacidade;
    private int mVolume;
    private Ponto mLocalizacao;


    private int mFaixas;
    private int mAltura;

    private int mFaixasVolume;
    private int mAlturaVolume;

    private boolean mStatus;
    private EtiquetaCores mEtiquetaCores;

    public Tanque(int eX, int eY, int eCapacidade, int eVolume) {

        mLocalizacao = new Ponto(eX, eY);
        mVolume = eVolume;
        mCapacidade = eCapacidade;

        mFaixas = mCapacidade / 100;
        int sTotal = mFaixas * 100;
        if (sTotal < mCapacidade) {
            mFaixas += 1;
        }

        mAltura = mFaixas * 50;

        //   System.out.println("a = " + mAltura);

        organizarVolume();

        mStatus = false;
        mEtiquetaCores = new EtiquetaCores();

    }

    public void abrir() {
        mStatus = true;
    }

    public void fechar() {
        mStatus = false;
    }

    public boolean isAberto() {
        return mStatus;
    }

    public int retirar(int eQuantidade) {
        int eRetirado = 0;


        if (mVolume > eQuantidade) {
            eRetirado = eQuantidade;
            mVolume = mVolume - eQuantidade;
        } else {
            eRetirado = mVolume;
            mVolume = 0;
        }

        organizarVolume();

        //  System.out.println("Volume :: " + mVolume);

        return eRetirado;
    }


    public void colocar(int eVolume) {
        mVolume = mVolume + eVolume;
        organizarVolume();
    }

    public void mudarVolume(int eVolume) {

        mVolume = eVolume;
        organizarVolume();
    }

    public void organizarVolume() {

        double taxa = (double) mAltura / (double) mCapacidade;

        mAlturaVolume = (int) ((double) mVolume * taxa);

        //    System.out.println("vol = " + mAlturaVolume);

    }

    public void conectar(Pressao ePressao, Cano eCano) {

        if (this.isAberto()) {

            if (ePressao == Pressao.Saindo) {

                if (this.getVolume() > 0) {

                   // System.out.println(" -->> PASS");

                  //  System.out.println("\t - Volume Antes :: " + this.getVolume());

                    int eVazao = eCano.getVazao();

                    int eQuantidade = this.retirar(eVazao);

                    int eSobrou = eCano.entrar(eQuantidade);
                    if (eSobrou > 0) {
                        this.colocar(eSobrou);
                    }


                   // System.out.println("\t - Entrando :: " + eQuantidade);
                 //   System.out.println("\t - Voltando :: " + eSobrou);

                   // System.out.println("\t - Volume Depois :: " + this.getVolume());

                }


            } else if (ePressao == Pressao.Entrando) {


                if (eCano.getEixos().get(eCano.getEixos().size() - 1).isCheio()) {

                    if (mVolume < mCapacidade) {

                        int eVazao = eCano.getEixos().get(eCano.getEixos().size() - 1).getQuantidade();
                        int eVolumeMais = getVolume() + eVazao;


                        int voltar = eVolumeMais - getCapacidade();

                        if (voltar <= 0) {
                            eCano.getEixos().get(eCano.getEixos().size() - 1).esvaziar();
                            mVolume+=eVazao;
                        }else{
                            eCano.getEixos().get(eCano.getEixos().size() - 1).encher(voltar);
                            mVolume=mCapacidade;
                        }

                        organizarVolume();

                    }

                }


            }

        }
    }




    public int getVolume() {
        return mVolume;
    }

    public int getCapacidade() {
        return mCapacidade;
    }

    public void render(Renderizador mRenderizador) {


        mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX(), (mLocalizacao.getY() - mAlturaVolume), 50, mAlturaVolume), mEtiquetaCores.getAgua());

        mRenderizador.drawRect(new Retangulo(mLocalizacao.getX(), mLocalizacao.getY() - mAltura, 50, mAltura), new Cor(255, 50, 0));

    }

}
