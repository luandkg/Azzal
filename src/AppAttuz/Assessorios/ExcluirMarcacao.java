package AppAttuz.Assessorios;

import AppAttuz.Assessorios.Escala;
import AppAttuz.Mapa.Local;

import java.util.ArrayList;

public class ExcluirMarcacao {

    public void excluir(ArrayList<Local> mMarcacoes, Escala mRelevo){

        ArrayList<Local> remover = new ArrayList<Local>();

        for (Local ePonto : mMarcacoes) {

            boolean existe = false;

            for (int i = 1; i <= 10; i++) {
                if (ePonto.getNome().contentEquals(String.valueOf(mRelevo.get(i)))) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                remover.add(ePonto);
            }

        }
        for (Local ePonto : remover) {
            mMarcacoes.remove(ePonto);
        }


    }
}