package libs.OrganizadorQ2D;

import azzal.Formatos.Ponto;

import java.util.ArrayList;

public class OrganizadorQ2D {

    private ArrayList<Regiao> mRegioes;

    private int mTamanho;

    public OrganizadorQ2D(int eTamanho) {

        mRegioes = new ArrayList<Regiao>();
        mTamanho = eTamanho;

    }

    public void adicionar(int eX, int eY, Object eObjeto) {

        int mQuadrantex = (eX) / mTamanho;
        int mQuadrantey = (eY) / mTamanho;

        Regiao eRegiao = getRegiao(mQuadrantex, mQuadrantey);
        eRegiao.adicionar(eObjeto);

        //System.out.println("Adicionando " + eX + ":" + eY + " em -->> " + eRegiao.getX() + "::" + eRegiao.getY());
    }

    public void limpar() {
        mRegioes.clear();
    }

    public ArrayList<Regiao> getRegioes() {
        return mRegioes;
    }

    public Regiao getRegiao(int ex, int ey) {

        boolean enc = false;
        Regiao mRegiao = null;

        for (Regiao eRegiao : mRegioes) {

            if (eRegiao.getX() == ex && eRegiao.getY() == ey) {
                enc = true;
                mRegiao = eRegiao;
                break;
            }

        }

        if (!enc) {
            mRegiao = new Regiao(ex, ey);
            mRegioes.add(mRegiao);
        }

        return mRegiao;
    }

    public ArrayList<Regiao> getRegioesAoRedor(int ex, int ey) {

        int mQuadrantex = ex / mTamanho;
        int mQuadrantey = ey / mTamanho;

        Ponto eQuadranteJogador = new Ponto(mQuadrantex, mQuadrantey);

        ArrayList<Regiao> mAoRedor = new ArrayList<Regiao>();


        // 1 2 3
        // 4 5 6
        // 7 8 9

        mAoRedor.add(getRegiao(eQuadranteJogador.getX(), eQuadranteJogador.getY())); // 5

        //Acima
        mAoRedor.add(getRegiao(eQuadranteJogador.getX(), eQuadranteJogador.getY() - 1)); // 2

        //Esquerda
        mAoRedor.add(getRegiao(eQuadranteJogador.getX() - 1, eQuadranteJogador.getY())); // 4

        //Direita
        mAoRedor.add(getRegiao(eQuadranteJogador.getX() + 1, eQuadranteJogador.getY())); // 6

        //Abaixo
        mAoRedor.add(getRegiao(eQuadranteJogador.getX(), eQuadranteJogador.getY() + 1)); // 8

        // Pontas
        mAoRedor.add(getRegiao(eQuadranteJogador.getX() - 1, eQuadranteJogador.getY() - 1));
        mAoRedor.add(getRegiao(eQuadranteJogador.getX() + 1, eQuadranteJogador.getY() - 1));
        mAoRedor.add(getRegiao(eQuadranteJogador.getX() - 1, eQuadranteJogador.getY() + 1));
        mAoRedor.add(getRegiao(eQuadranteJogador.getX() + 1, eQuadranteJogador.getY() + 1));


        return mAoRedor;
    }

    public ArrayList<Object> getObjetosAoRedor(int ex, int ey) {

        ArrayList<Object> mAoRedor = new ArrayList<Object>();

        for (Regiao eRegiao : getRegioesAoRedor(ex, ey)) {
            mAoRedor.addAll(eRegiao.getObjetos());
        }

        return mAoRedor;
    }

}
