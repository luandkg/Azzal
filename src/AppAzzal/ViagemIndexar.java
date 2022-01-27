package AppAzzal;

import Arquivos.BZ;
import Arquivos.TX;
import DKG.DKG;
import Tronarko.StringTronarko;
import Tronarko.Tozte;
import DKG.DKGObjeto;
import java.util.ArrayList;

public class ViagemIndexar {

    public static void indexar(String eArquivo, String eArquivoViagem) {


        DKG t2 = new DKG();
        t2.abrir(eArquivoViagem);

        System.out.println("Itens :: " + t2.unicoObjeto("Viagem").getObjetos().size());

        StringTronarko st = new StringTronarko();

        if (t2.unicoObjeto("Viagem").getObjetos().size() > 0) {


            int primeiro = Integer.parseInt(st.getTronarko(t2.unicoObjeto("Viagem").getObjetos().get(0).identifique("Tozte").getValor()));
            Tozte eTozte = new Tozte(1, 1, primeiro);

            int ii = 3501196;
            System.out.println("Comecar -->> " + (eTozte.getSuperarkosTotal() - 3501196));

            while (eTozte.getTronarko() == primeiro) {
                DKG viagem_aqui = new DKG();
                DKGObjeto va = viagem_aqui.unicoObjeto("Viagem");


                for (DKGObjeto o : t2.unicoObjeto("Viagem").getObjetos()) {

                    String o_tozte = o.identifique("Tozte").getValor();
                    String f_tozte = st.getSuperarko(o_tozte) + "/" + st.getHiperarko(o_tozte) + "/" + st.getTronarko(o_tozte);


                    if (f_tozte.contentEquals(eTozte.getTexto())) {
                        va.getObjetos().add(o);
                    }

                }


                TX eTX = new TX();

                ArrayList<Byte> bytes = eTX.toListBytes(viagem_aqui.toString());

                int tt = bytes.size() / 1024;

                int indice = (int) (eTozte.getSuperarkosTotal() - 3501196);

                if (indice >= 0 && tt <= 5) {
                    System.out.println("Pass -->> (" + indice + " ) " + eTozte.getTexto() + " :: " + va.getObjetos().size() + " objs -->> " + tt + " kb com :: " + bytes.size());
                    BZ.atribuir(eArquivo, ((int) (eTozte.getSuperarkosTotal() - 3501196)), viagem_aqui.toString());
                }


                eTozte = eTozte.adicionar_Superarko(1);
            }

        }


    }

}
