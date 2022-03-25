package Tronarko.Eventos;

import Tronarko.Tozte;
import Tronarko.TozteCor;

import java.util.ArrayList;

public class Momentum {

    public void olharAoRedor(Tozte mAtualmente) {

        System.out.println();
        System.out.println("Hoje : " + mAtualmente.getTexto());

        Tozte mAntes = mAtualmente.adicionar_Superarko(-100);
        Tozte mDepois = mAtualmente.adicionar_Superarko(+100);

        Eventum mEventum = new Eventum();

        ArrayList<TozteCor> mInfos = mEventum.getToztesComCorEmIntervalo(mAntes, mDepois);

        for (TozteCor eTozteCor : mInfos) {

            String nome = eTozteCor.getNome() + " :: " + eTozteCor.getTozte().getTexto();
            int distancia = getDistancia(mAtualmente, eTozteCor.getTozte());

            System.out.println(" -->> " + nome + " -->> " + distancia);
        }


    }

    public static int getDistancia(Tozte eReferencia, Tozte eAlgumTozte) {

        int valor = 0;

        Tozte eOutro_Ref = eReferencia.getCopia();
        Tozte eOutro_AlgumTozte = eAlgumTozte.getCopia();

        if (eOutro_Ref.isMaiorQue(eOutro_AlgumTozte)) {

            while (eOutro_Ref.isMaiorQue(eOutro_AlgumTozte)) {
                eOutro_Ref = eOutro_Ref.adicionar_Superarko(-1);
                valor -= 1;
            }

        } else if (eOutro_Ref.isMenorQue(eOutro_AlgumTozte)) {

            while (eOutro_Ref.isMenorQue(eOutro_AlgumTozte)) {
                eOutro_Ref = eOutro_Ref.adicionar_Superarko(+1);
                valor += 1;
            }

        }

        return valor;

    }


}
