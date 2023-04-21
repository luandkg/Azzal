package libs.azzal.utilitarios;

public class TransformadorDeCor {

    private Cor mCor;



    private boolean mVermelho_Mudar;
    private int mVermelho_Tempo;
    private int mVermelho_Contador;
    private int mVermelho_Valor;
    private boolean mVermelho_Modo;



    private boolean mVerde_Mudar;
    private int mVerde_Tempo;
    private int mVerde_Contador;
    private int mVerde_Valor;
    private boolean mVerde_Modo;


    private boolean mAzul_Mudar;
    private int mAzul_Tempo;
    private int mAzul_Contador;
    private int mAzul_Valor;
    private boolean mAzul_Modo;

    public TransformadorDeCor(Cor eCor) {

        mCor = eCor;
        mVermelho_Mudar=false;
        mVerde_Mudar=false;
        mAzul_Mudar=false;

    }

    public TransformadorDeCor(int eVermelho,int eVerde,int eAzul) {

        mCor = new Cor(eVermelho,eVerde,eAzul);
        mVermelho_Mudar=false;
        mVerde_Mudar=false;
        mAzul_Mudar=false;

    }

    public void mudarVermelho(int eTempo, int eValor){

        mVermelho_Mudar=true;
        mVermelho_Tempo=eTempo;
        mVermelho_Contador=0;
        mVermelho_Valor=eValor;
        mVermelho_Modo=true;

        if (mVermelho_Valor<0){
            mVermelho_Modo=false;
            mVermelho_Valor=(-1) * mVermelho_Valor;
        }

    }

    public void mudarVerde(int eTempo, int eValor){

        mVerde_Mudar=true;
        mVerde_Tempo=eTempo;
        mVerde_Contador=0;
        mVerde_Valor=eValor;
        mVerde_Modo=true;

        if (mVerde_Valor<0){
            mVerde_Modo=false;
            mVerde_Valor=(-1) * mVerde_Valor;
        }

    }

    public void mudarAzul(int eTempo, int eValor){

        mAzul_Mudar=true;
        mAzul_Tempo=eTempo;
        mAzul_Contador=0;
        mAzul_Valor=eValor;
        mAzul_Modo=true;

        if (mAzul_Valor<0){
            mAzul_Modo=false;
            mAzul_Valor=(-1) * mAzul_Valor;
        }

    }

    public void atualizar(){


        // MUDAR VERMELHO
        if (mVermelho_Mudar){

            if (mVermelho_Contador >= mVermelho_Tempo) {

                mVermelho_Contador = 0;

                if (mVermelho_Modo){
                    mCor = mCor.aumentarVermelho(mVermelho_Valor);
                }else{
                    mCor = mCor.reduzirVermelho(mVermelho_Valor);
                }

                if (mCor.getRed()>=255){
                    mVermelho_Valor=mVermelho_Valor*(-1);
                }else if (mCor.getRed()<=0){
                    mVermelho_Valor=mVermelho_Valor*(-1);
                }

          //      System.out.println(mCor.toString());
            }

            mVermelho_Contador+=1;

        }


        // MUDAR VERDE
        if (mVerde_Mudar){

            if (mVerde_Contador >= mVerde_Tempo) {

                mVerde_Contador = 0;

                if (mVerde_Modo){
                    mCor = mCor.aumentarVerde(mVerde_Valor);
                }else{
                    mCor = mCor.reduzirVerde(mVerde_Valor);
                }

                if (mCor.getGreen()>=255){
                    mVerde_Valor=mVerde_Valor*(-1);
                }else if (mCor.getGreen()<=0){
                    mVerde_Valor=mVerde_Valor*(-1);
                }

           //     System.out.println(mCor.toString());
            }

            mVerde_Contador+=1;

        }


        // MUDAR AZUL
        if (mAzul_Mudar){

            if (mAzul_Contador >= mAzul_Tempo) {

                mAzul_Contador = 0;

                if (mAzul_Modo){
                    mCor = mCor.aumentarAzul(mAzul_Valor);
                }else{
                    mCor = mCor.reduzirAzul(mAzul_Valor);
                }

                if (mCor.getBlue()>=255){
                    mAzul_Valor=mAzul_Valor*(-1);
                }else if (mCor.getBlue()<=0){
                    mAzul_Valor=mAzul_Valor*(-1);
                }

                //System.out.println(mCor.toString());
            }

            mAzul_Contador+=1;

        }




    }



    public Cor getCor(){return mCor;}


}
