package libs.arquivos;

import libs.dkg.DKG;
import libs.dkg.DKGObjeto;

import java.util.ArrayList;

public class Sumario {

    private String mArquivo;
    public static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    public static final int LETRAS = 27;

    public Sumario(String eArquivo) {

        mArquivo = eArquivo;

        if (BZ3.getQuantidadeMaxima(mArquivo) < 30) {
            BZ3.alocar(mArquivo, 32);
        }

    }

    public static String getLetra(int indice) {
        String letra = String.valueOf(ALFABETO.charAt(indice));
        return letra;
    }


    public DKGObjeto separar_objetos_letra(ArrayList<DKGObjeto> objetos, String atributo, String letra) {


        DKGObjeto objetos_itens = new DKGObjeto("ITENS");

        for (DKGObjeto o : objetos) {

            String valor = o.identifique(atributo).getValor();

            if (valor.length() > 0) {

                String valor_primeira_letra = String.valueOf(valor.charAt(0));

                if (letra.contentEquals(valor_primeira_letra)) {
                    objetos_itens.getObjetos().adicionar(o);
                }
            }


        }

        return objetos_itens;

    }

    public void organizar_completo(ArrayList<DKGObjeto> objetos, String atributo) {


        int indice = 0;
        while (indice < LETRAS) {

            String letra = getLetra(indice);

            DKGObjeto objetos_itens = separar_objetos_letra(objetos, atributo, letra);

            BZ3.atribuir(mArquivo, indice, objetos_itens.toDocumento());

            indice += 1;
        }


    }

    public String getItensComLetra(String proc_letra) {

        String ret = "";


        if (proc_letra.length() > 0) {

            int indice = 0;

            proc_letra = String.valueOf(proc_letra.charAt(0)).toUpperCase();

            while (indice < LETRAS) {
                String letra = String.valueOf(ALFABETO.charAt(indice));
                if (letra.contentEquals(proc_letra)) {
                    break;
                }
                indice += 1;
            }

            ret = BZ3.procurar(mArquivo, indice);

        }


        return ret;

    }

}
