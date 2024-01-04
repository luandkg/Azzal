package apps.app_citatte.engenharia;

import libs.azzal.geometria.Ponto;
import libs.luan.Lista;

public class AvenidaViaria {

    private int mID;
    private Lista<Ponto> mPontos;
    private Lista<Ponto> mCruzamentos;

    private String mRosaDosVentos;
    private int mAvenidaSequencia;
    private int mLoteSequencia;

    public AvenidaViaria(int eID) {
        mID = eID;
        mPontos = new Lista<Ponto>();
        mCruzamentos = new Lista<Ponto>();

        mRosaDosVentos="";
        mAvenidaSequencia=0;
        mLoteSequencia=0;
    }

    public int getID() {
        return mID;
    }

    public void adicionar(Ponto ePonto) {
        mPontos.adicionar(ePonto);
    }

    public Lista<Ponto> getPontos() {
        return mPontos;
    }

    public int getCruzamentos() {
        return mCruzamentos.getQuantidade();
    }

    public void adicionarCruzamento(Ponto eCruzamento) {
        mCruzamentos.adicionar(eCruzamento);
    }

    public Lista<Ponto> getConexoes() {
        return mCruzamentos;
    }

    public boolean isHorizontal() {
        boolean ret = false;

        if (mPontos.getQuantidade() > 0) {
            ret = true;

            int py = mPontos.get(0).getY();
            for (Ponto px : mPontos) {
                if (px.getY() != py) {
                    ret = false;
                    break;
                }
            }

        }

        return ret;
    }

    public boolean isVertical() {
        boolean ret = false;

        if (mPontos.getQuantidade() > 0) {
            ret = true;

            int px = mPontos.get(0).getX();
            for (Ponto py : mPontos) {
                if (py.getX() != px) {
                    ret = false;
                    break;
                }
            }

        }

        return ret;
    }

    public int getComprimento() {
        return mPontos.getQuantidade();
    }


    public int getX() {

        int px = getPontos().get(0).getX();


        if (isHorizontal()) {
            px = getPontos().get(0).getX();

            for (Ponto pt : getPontos()) {
                if (pt.getX() < px) {
                    px = pt.getX();
                }
            }

        }

        return px;

    }

    public int getY() {

        int py = getPontos().get(0).getY();


        if (isVertical()) {
            py = getPontos().get(0).getY();

            for (Ponto pt : getPontos()) {
                if (pt.getY() < py) {
                    py = pt.getY();
                }
            }

        }

        return py;

    }

    public void buscar_cruzamentos(AvenidaViaria av2) {

        for (Ponto pt1 : getPontos()) {
            for (Ponto pt2 : av2.getPontos()) {
                if (pt1.isIgual(pt2)) {
                    adicionarCruzamento(pt1);
                    //   System.out.println("Av(" + getID() + ") cruza com Av(" + av2.getID() + ") ->> " + pt1.toString());
                }
            }
        }

    }


    public boolean isTerminal() {

        boolean existe = true;
        Ponto ultimo = getPontos().get(mPontos.getQuantidade() - 1);

        for (Ponto conexao : getConexoes()) {
            if (conexao.isIgual(ultimo)) {
                existe = false;
                break;
            }
        }
        return existe;
    }

    public boolean isPrimaria() {

        boolean existe = true;
        Ponto ultimo = getPontos().get(0);

        for (Ponto conexao : getConexoes()) {
            if (conexao.isIgual(ultimo)) {
                existe = false;
                break;
            }
        }
        return existe;
    }

    public boolean estaEm(Ponto pt) {
        boolean existe = false;
        for (Ponto ptav : getPontos()) {
            if (ptav.isIgual(pt)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean estaEm(int ptx, int pty) {
        boolean existe = false;
        for (Ponto ptav : getPontos()) {
            if (ptav.isIgual(ptx, pty)) {
                existe = true;
                break;
            }
        }
        return existe;
    }


    public String getTipo() {

        if (isHorizontal()) {
            return "Horizontal";
        } else if (isVertical()) {
            return "Vertical";
        }

        return "Desconhecido";

    }


    public String getRosaDosVentos(){return mRosaDosVentos;}
    public void setRosaDosVentos(String v){
        mRosaDosVentos=v;
    }


    public void setAvenidaSequencia(int seq){
        mAvenidaSequencia=seq;
    }

    public int getAvenidaSequencia(){return mAvenidaSequencia;}

    public void zerarLoteSequencia(){
        mLoteSequencia=1;
    }

    public int getLoteSequencia(){return mLoteSequencia;}

    public void proximoLote(){
        mLoteSequencia+=1;
    }
}
