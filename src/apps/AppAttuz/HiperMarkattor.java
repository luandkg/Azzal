package apps.AppAttuz;

import apps.AppAttuz.Assessorios.Nivelador;
import apps.AppAttuz.Mapa.Local;
import azzal_ui.Interface.Acao;

import java.util.ArrayList;

public class HiperMarkattor {

    private int X0;
    private int Y0;

    private int LARGURA;
    private int ALTURA;
    private Nivelador mNivelador;
    private ArrayList<Local> mMarcacoes;
    private boolean temAcao;
    private Acao mAcao;

    public HiperMarkattor(Nivelador eNivelador, int x, int y, int largura, int altura) {
        X0 = x;
        Y0 = y;
        LARGURA = largura;
        ALTURA = altura;
        mNivelador = eNivelador;
        mMarcacoes = new ArrayList<Local>();
        temAcao = false;
        mAcao = null;
    }

    public void setAcao(Acao eAcao) {
        temAcao = true;
        mAcao = eAcao;
    }

    public void carregarMarcadores(ArrayList<Local> eMarcacoes) {
        mMarcacoes = eMarcacoes;
    }

    public ArrayList<Local> getMarcadores() {
        return mMarcacoes;
    }

    public void localizar(int px, int py, boolean isMovendo, int em_x, int em_y) {


        if (px > X0 && py >= Y0 && px < LARGURA && py < ALTURA) {

            int rx = px - X0 - 3;
            int ry = py - Y0 - 5;


            if (mNivelador.getNivel() == 0) {
                pontos_limpar(px, py, isMovendo, em_x, em_y);
            } else {

                if (px > X0 && py > Y0) {
                    //if (mMassa.isTerra(rx * 2, ry * 2)) {
                    mMarcacoes.add(new Local("" + mNivelador.getNivel(), rx, ry));
                    // System.out.println(" PONTO -->> " + (rx ) + "::" + (ry));
                    //  }
                }


            }

            mAcao.onClique();


        }


    }

    public void pontos_limpar(int px, int py, boolean isMovendo, int em_x, int em_y) {

        int rx = px - X0 - 3;
        int ry = py - Y0 - 5;

        if (isMovendo) {
            rx += em_x;
            ry += em_y;
        }

        System.out.println("Limpando -->> " + rx + " :: " + ry);

        int comecar_x = rx - 50;
        int comecar_y = ry - 50;

        int terminar_x = rx + 50;
        int terminar_y = ry + 50;

        if (mNivelador.getNivel() == 0) {
            ArrayList<Local> remover = new ArrayList<Local>();

            for (Local r : mMarcacoes) {
                if (r.getX() > comecar_x && r.getX() < terminar_x && r.getY() > comecar_y && r.getY() < terminar_y) {
                    remover.add(r);
                }
            }

            for (Local r : remover) {
                mMarcacoes.remove(r);
            }
        }
    }


    public void movendo(int px, int py, boolean isMovendo, int em_x, int em_y) {

        if (isMovendo) {

            if (mNivelador.getNivel() == 0) {

                pontos_limpar(px, py, isMovendo, em_x, em_y);

                mAcao.onClique();


            }

        }

    }


}
