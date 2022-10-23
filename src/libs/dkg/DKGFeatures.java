package libs.dkg;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DKGFeatures {

    // FEATURE 22.10.05
    public static void renomear_objetos_iguais_sequencialmente(ArrayList<DKGObjetoOuAtributo> lista) {

        String sPrimeiro = "";
        boolean isPrimeiro = true;

        int quantidade = 0;
        int igual = 0;

        for (DKGObjetoOuAtributo obj : lista) {

            if (obj.isObjeto()) {
                if (isPrimeiro) {
                    isPrimeiro = false;
                    sPrimeiro = obj.getObjeto().getNome();
                    igual += 1;
                } else {
                    if (obj.getObjeto().getNome().contentEquals(sPrimeiro)) {
                        igual += 1;
                    }
                }
                quantidade += 1;
            }

        }

        if (quantidade > 1) {
            if (quantidade == igual) {
                quantidade = 0;
                for (DKGObjetoOuAtributo obj : lista) {
                    if (obj.isObjeto()) {
                        obj.getObjeto().setNome(sPrimeiro + " {" + quantidade + "}");
                        quantidade += 1;
                    }
                }
            }
        }

    }


    // FEATURE 22.10.10
    public static DKGObjetoFuturo obter_ou_criar(DKGObjeto objeto_pai, String item, String eID, String eValor) {


        DKGObjetoFuturo ret = null;
        boolean existe = false;
        for (DKGObjeto objeto_corrente : objeto_pai.getObjetos()) {
            if (objeto_corrente.identifique(eID).getValor().contentEquals(eValor)) {
                existe = true;
                ret = new DKGObjetoFuturo(objeto_corrente, DKGObjetoFuturo.EXISTENTE);
                break;
            }
        }

        if (!existe) {
            DKGObjeto objeto_novo = objeto_pai.criarObjeto(item);
            objeto_novo.identifique(eID, eValor);
            ret = new DKGObjetoFuturo(objeto_novo, DKGObjetoFuturo.NOVO);
        }

        return ret;
    }


    // FEATURE 22.10.12
    public static ArrayList<DKGObjeto> ordendar_objetos_texto(ArrayList<DKGObjeto> objetos, String atributo) {

        Collections.sort(objetos, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {

                DKGObjeto o1 = (DKGObjeto) objeto_um;
                DKGObjeto o2 = (DKGObjeto) objeto_dois;

                String p1 = o1.identifique(atributo).getValor();
                String p2 = o2.identifique(atributo).getValor();

                return (p1.compareTo(p2));
            }
        });

        return objetos;
    }

    public static ArrayList<DKGObjeto> ordendar_objetos_i32(ArrayList<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.size();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                int vj = objetos.get(j).identifique(atributo).getInteiro(0);
                int vi = objetos.get(i).identifique(atributo).getInteiro(0);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static ArrayList<DKGObjeto> ordendar_objetos_i64(ArrayList<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.size();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                long vj = objetos.get(j).identifique(atributo).getLong(0);
                long vi = objetos.get(i).identifique(atributo).getLong(0);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static ArrayList<DKGObjeto> ordendar_objetos_f32(ArrayList<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.size();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                float vj = objetos.get(j).identifique(atributo).getFloat(0.0f);
                float vi = objetos.get(i).identifique(atributo).getFloat(0.0f);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static ArrayList<DKGObjeto> ordendar_objetos_f64(ArrayList<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.size();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                double vj = objetos.get(j).identifique(atributo).getDouble(0.0);
                double vi = objetos.get(i).identifique(atributo).getDouble(0.0);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

}

