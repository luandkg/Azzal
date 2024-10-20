package apps.app_atzum.utils;

import apps.app_atzum.Atzum;
import apps.app_atzum.servicos.ServicoMassasDeAr;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.Portugues;
import libs.luan.fmt;

public class MassaDeAr {

    private String mInfo;
    private Lista<Ponto> mPercurso;
    private Cor mCor;
    private String mNome;
    private String mTipo;
private int mVelocidade;
private int mIndice;
private boolean isReverso;

private Lista<Ponto> mMassa;

    public MassaDeAr(String eNome, String eTipo,int eSuperarko,int eVelocidade){
        mNome=eNome;
        mInfo=eNome;
mTipo=eTipo;

        Atzum atzum = new Atzum();

        if(eTipo.contentEquals("FRIO")){
            mCor=atzum.getMassaDeArFria();
        }else{
            mCor=atzum.getMassaDeArQuente();
        }

        mMassa = new Lista<Ponto>();
        mPercurso = new Lista<Ponto>();

        mMassa = ServicoMassasDeAr.GET_MASSA_AREA(mNome);
        mPercurso= ServicoMassasDeAr.GET_MASSA_PERCURSO(mNome);


        fmt.print("Massa : {}",mNome);
        fmt.print("\t ++ Tam :: {}",mPercurso.getQuantidade());

        Lista<Ponto> novo_percurso = new Lista<Ponto>();

        double taxa = (double)mPercurso.getQuantidade() / 500.0;
        fmt.print("\t ++ Taxa :: {}",fmt.f2(taxa));

        int i = 0;
        double percorrendo =0.0;

        while(i<500){
int ponto_corrente = (int) (percorrendo);


            novo_percurso.adicionar(mPercurso.get(ponto_corrente));

            percorrendo+=taxa;
            i+=1;
        }

        fmt.print("\t ++ Tam :: {}",novo_percurso.getQuantidade());

        mPercurso=novo_percurso;

        mVelocidade=eVelocidade;
        mIndice=eSuperarko;
        isReverso=false;
        if(mVelocidade<0){
            isReverso=true;
            mVelocidade=mVelocidade*(-1);
        }
    }


    public MassaDeAr(String eInfo,String eNome, String eTipo,int eSuperarko,int eVelocidade){
        mNome=eNome;
        mInfo=eInfo;
        mTipo=eTipo;

        Atzum atzum = new Atzum();

        if(eTipo.contentEquals("FRIO")){
            mCor=atzum.getMassaDeArFria();
        }else{
            mCor=atzum.getMassaDeArQuente();
        }

        mMassa = new Lista<Ponto>();
        mPercurso = new Lista<Ponto>();

        mMassa = ServicoMassasDeAr.GET_MASSA_AREA(mNome);
        mPercurso= ServicoMassasDeAr.GET_MASSA_PERCURSO(mNome);



        Lista<Ponto> novo_percurso = new Lista<Ponto>();

        double taxa = (double)mPercurso.getQuantidade() / 500.0;

        int i = 0;
        double percorrendo =0.0;

        while(i<500){
            int ponto_corrente = (int) (percorrendo);


            novo_percurso.adicionar(mPercurso.get(ponto_corrente));

            percorrendo+=taxa;
            i+=1;
        }


        mPercurso=novo_percurso;

        mVelocidade=eVelocidade;
        mIndice=eSuperarko;
        isReverso=false;
        if(mVelocidade<0){
            isReverso=true;
            mVelocidade=mVelocidade*(-1);
        }



        fmt.print("Massa : {}",mNome);
        fmt.print("\t ++ Percurso v1      :: {}",mPercurso.getQuantidade());
        fmt.print("\t ++ Percurso v2      :: {}",novo_percurso.getQuantidade());
        fmt.print("\t ++ Taxa             :: {}",fmt.f2(taxa));
        fmt.print("\t ++ Massa            :: {}",mMassa.getQuantidade());
        fmt.print("\t ++ Tipo             :: {}", Portugues.VALIDAR(eTipo.contentEquals("FRIO"),"FRIA","QUENTE"));
        fmt.print("\t ++ Velocidade       :: {}", Portugues.VALIDAR(isReverso,"REVERSO","ADIANTE"));

    }

    public String getInfo(){return mInfo;}

    public boolean isFrio(){
        return mTipo.contentEquals("FRIO");
    }

    public boolean isQuente(){
        return mTipo.contentEquals("QUENTE");
    }

    public Lista<Ponto> getPercurso(){return mPercurso;}

    public Cor getCor(){return mCor;}


    public void proximo(){
        mIndice+=mVelocidade;
        if(mIndice>=500){
            mIndice=0;
        }
        if(mIndice<0){
            mIndice=499;
        }
    }

    public Ponto getCorrente(){
        if(mIndice>=500){
            mIndice=0;
        }
        if(mIndice<0){
            mIndice=499;
        }
       return mPercurso.get(mIndice);
    }

    public int getIndice(){return mIndice;}

    public Lista<Ponto> getMassa(){return mMassa;}
}
