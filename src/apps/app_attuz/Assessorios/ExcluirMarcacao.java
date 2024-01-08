package apps.app_attuz.Assessorios;

import apps.app_attuz.Ferramentas.Local;

import java.util.ArrayList;

public class ExcluirMarcacao {

    public void excluir(ArrayList<Local> mMarcacoes, Escala mRelevo) {

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
