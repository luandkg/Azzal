package apps.app_attuz.Arkazz;


import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_attuz.Ferramentas.Caminho;
import apps.app_attuz.Ferramentas.Local;
import azzal.geometria.Ponto;
import libs.dkg.DKG;

import java.util.ArrayList;

import libs.dkg.DKGObjeto;

public class Cidades {



    public static void salvar(ArrayList<Local> mLocais, String eArquivo) {

        DKG arquivo = new DKG();
        DKGObjeto eLocais = arquivo.unicoObjeto("Locais");

        for (Local eLocal : mLocais) {
            DKGObjeto objeto_local = eLocais.criarObjeto("Local");

            objeto_local.identifique("Nome", eLocal.getNome());
            objeto_local.identifique("X", eLocal.getX());
            objeto_local.identifique("Y", eLocal.getY());

        }

        arquivo.salvar(eArquivo);
    }

    public static void ligar(ArrayList<Local> mLocais, ArrayList<Caminho> mCaminhos) {

        // ligarCidades(mLocais, mCaminhos, 0, 48);
        //  ligarCidades(mLocais, mCaminhos, 0, 20);
        // ligarCidades(mLocais, mCaminhos, 0, 21);
        //  ligarCidades(mLocais, mCaminhos, 0, 11);
        // ligarCidades(mLocais, mCaminhos, 11, 21);
        //  ligarCidades(mLocais, mCaminhos, 0, 10);
        //  ligarCidades(mLocais, mCaminhos, 10, 7);

    }

    public static void ligarCidades(ArrayList<Local> mLocais, ArrayList<Caminho> mCaminhos, int i, int f) {

        Local li = getLocal(mLocais, i);
        Local lf = getLocal(mLocais, f);

        mCaminhos.add(new Caminho(new Ponto(li.getX(), li.getY()), new Ponto(lf.getX(), lf.getY())));

    }


    public static Local getLocal(ArrayList<Local> mLocais, int i) {

        int p = 0;
        Local ret = null;

        for (Local ePonto : mLocais) {
            if (p == i) {
                ret = ePonto;
                break;
            }
            p += 1;
        }

        return ret;
    }


    public static void zerar(ArrayList<Local> mLocais) {
        int i = 0;

        for (Local ePonto : mLocais) {
            if (ePonto.getNome().length() < 3) {
                ePonto.setNome(String.valueOf(i));
            }
            System.out.println("mLocais.add(new Local(\"" + ePonto.getNome() + "\"," + ePonto.getX() + "," + ePonto.getY() + "));");
            i += 1;
        }

    }

    public static String getNomeDe(ArrayList<Local> mLocais, int px, int py) {
        String ret = "";
        for (Local ePonto : mLocais) {
            if (ePonto.getX() == px && ePonto.getY() == py) {
                ret = ePonto.getNome();
                break;
            }
        }

        return ret;
    }


    public static String getNomeMaisProximo(ArrayList<Local> mLocais, int px, int py, int ate) {

        String ret = "";
        int menor = ate;

        for (Local ePonto : mLocais) {
            int dist = Espaco2D.distancia_entre_pontos(px, py, ePonto.getX() * 2, ePonto.getY() * 2);

            if (dist < menor) {
                menor = dist;
                ret = ePonto.getNome();
            }


        }

        return ret;
    }



    public static Local getLocalizacao(ArrayList<Local> cidades,String eNome){

        for(Local l : cidades){
            if (l.getNome().contentEquals(eNome)){
                return l;
            }
        }

        return null;
    }
}
