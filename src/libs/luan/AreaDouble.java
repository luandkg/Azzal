package libs.luan;

public class AreaDouble {

    private double[] mValores;
    private int mLargura;
    private int mAltura;

    public AreaDouble(int eLargura,int eAltura){

        mLargura=eLargura;
        mAltura=eAltura;
         mValores = new double[mLargura * mAltura];

    }


    public int getLargura(){return mLargura;}
public int getAltura(){return mAltura;}

    public void set(int x,int y,double valor){
        mValores[(y * mLargura) + x] = valor;
    }


    public double get(int x,int y){
        return mValores[(y *mLargura) + x];
    }

    public double[] getTudo(){return mValores;}
}
