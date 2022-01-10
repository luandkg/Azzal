package TG22;

public class Ficha {

    private String mTozte = "";

    private double mAltura = 0.0;
    private double mPeso = 0.0;

    public Ficha(String eTozte,double eAltura,double ePeso){
        mTozte=eTozte;
        mAltura=eAltura;
        mPeso=ePeso;
    }

    public String getTozte(){return mTozte;}
    public double getAltura(){return mAltura;}
    public double getPeso(){return mPeso;}

}
