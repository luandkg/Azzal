package libs.verkuz;

public class Commit {

    private int mID;

    private String mData;
    private String mComentario;

    public Commit(int eID,String eData,String eComentario){
        mID=eID;
        mData=eData;
        mComentario=eComentario;
    }

    public int getID(){return mID;}

    public String getData(){return mData;}
    public String getComentario(){return mComentario;}

}

