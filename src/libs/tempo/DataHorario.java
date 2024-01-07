package libs.tempo;

public class DataHorario {

    private int mAno;
    private int mMes;
    private int mDia;

    private int mHora;
    private int mMinutos;
    private int mSegundos;

    public DataHorario(int eAno, int eMes, int eDia,int h, int m, int s){

        mAno = eAno;
        mMes = eMes;
        mDia = eDia;

        mHora = h;
        mMinutos = m;
        mSegundos = s;

    }

    public DataHorario(String eAno, String eMes, String eDia,String h, String m, String s){

        mAno = Integer.parseInt(eAno);
        mMes = Integer.parseInt(eMes);
        mDia = Integer.parseInt(eDia);

        mHora = Integer.parseInt(h);
        mMinutos = Integer.parseInt(m);
        mSegundos = Integer.parseInt(s);

    }

    public int getMes() {
        return mMes;
    }

    public int getDia() {
        return mDia;
    }

    public int getAno() {
        return mAno;
    }

    public int getHora() {
        return mHora;
    }

    public int getMinutos() {
        return mMinutos;
    }

    public int getSegundos() {
        return mSegundos;
    }


    public String toTempo(){return mAno+"_" + D(mMes)+"_"+D(mDia) +  " - " + D(mHora)+"_"+D(mMinutos)+"_"+D(mSegundos);}

    private String D(int d){
        String ds = String.valueOf(d);
        if(ds.length()==1){
            return "0"+ds;
        }
        return ds;
    }

    public void copiarDe(DataHorario entrada){

        mAno=entrada.getAno();
        mMes=entrada.getMes();
        mDia=entrada.getDia();

        mHora=entrada.getHora();
        mMinutos=entrada.getMinutos();
        mSegundos=entrada.getSegundos();

    }
}