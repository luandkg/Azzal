package algoritmos.carzane;

public class Movimento {


    public static final int ValorAlterar=1;
    public static final int SeValorIgual=2;
    public static final int XAlterar=3;
    public static final int YAlterar=4;
    public static final int ValorDefinir=5;

    private int mMovimentoTipo = 0;
    private int mValor = 0;

    private int mComandos = 0;

    private int mMovimentoTipo2 = 0;
    private int mValor2 = 0;

    public Movimento(int eMovimentoTipo,int eValor){
        mComandos=1;
        mMovimentoTipo=eMovimentoTipo;
        mValor=eValor;
    }

    public Movimento(int eMovimentoTipo,int eValor,int eMovimentoTipo2,int eValor2){
        mComandos=2;
        mMovimentoTipo=eMovimentoTipo;
        mValor=eValor;
        mMovimentoTipo2=eMovimentoTipo2;
        mValor2=eValor2;
    }

    public int getComandos(){return mComandos;}


    public void aplicar(Ponto ponto){

        if(getComandos()==1) {

            if(mMovimentoTipo==ValorAlterar) {
                ponto.setValor(ponto.getValor() + mValor);
            }else             if(mMovimentoTipo==ValorDefinir){
                ponto.setValor( mValor);
            }

        }else         if(getComandos()==2){

            if(mMovimentoTipo==SeValorIgual) {
                if(ponto.getValor()==mValor){

                    if(mMovimentoTipo2==XAlterar) {
                        ponto.setX(ponto.getX() + mValor2);
                    }else             if(mMovimentoTipo2==YAlterar){
                        ponto.setY(ponto.getY() + mValor2);
                    }else             if(mMovimentoTipo2==ValorDefinir){
                        ponto.setValor( mValor2);
                    }

                }
            }


            }

    }
}
