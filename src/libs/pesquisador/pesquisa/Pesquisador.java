package libs.pesquisador.pesquisa;

import libs.pesquisador.pesquisa.quantitativo.FormularioDePesquisaQuantitativa;

import java.util.ArrayList;

public class Pesquisador {


    public Pesquisador() {


    }


    public FormularioDePesquisaQuantitativa getFormulario(ArrayList<FormularioDePesquisaQuantitativa> eFormularios, String eNome) {

        boolean enc = false;
        FormularioDePesquisaQuantitativa ret = null;

        for (FormularioDePesquisaQuantitativa eFormularioDePesquisa : eFormularios) {
            if (eFormularioDePesquisa.getNome().contentEquals(eNome)) {
                enc = true;
                ret = eFormularioDePesquisa;
                break;
            }
        }

        if (!enc) {
            ret = new FormularioDePesquisaQuantitativa(eNome);
            eFormularios.add(ret);
        }

        return ret;
    }

    private void analisar(ArrayList<AnaliseDePesquisa> eAnalises, String eGrupo) {

        boolean enc = false;

        for (AnaliseDePesquisa eAnalise : eAnalises) {
            if (eAnalise.getGrupo().contentEquals(eGrupo)) {
                enc = true;
                eAnalise.aumentar(1);
                break;
            }
        }

        if (!enc) {
            eAnalises.add(new AnaliseDePesquisa(eGrupo, 1));
        }

    }


    public void divulgarQuantitativo(String eNome, ArrayList<FormularioDePesquisaQuantitativa> mTempoDeOvulacao) {

        System.out.println("");
        System.out.println(eNome);
        System.out.println("");

        int eCompleto = 0;
        int eInCompleto = 0;

        ArrayList<AnaliseDePesquisa> mAnalises = new ArrayList<AnaliseDePesquisa>();

        for (FormularioDePesquisaQuantitativa eFormularioDePesquisa : mTempoDeOvulacao) {

            if (eFormularioDePesquisa.getItem1().foiRespondido() && eFormularioDePesquisa.getItem2().foiRespondido()) {

                analisar(mAnalises, "" + (eFormularioDePesquisa.getItem2().getResposta() - eFormularioDePesquisa.getItem1().getResposta()));
                // System.out.println("\t -->> " + eFormularioDePesquisa.getNome() + "    Inicio = " + eFormularioDePesquisa.getItem1().getResposta() + "    Fim = " + eFormularioDePesquisa.getItem2().getResposta());

                eCompleto += 1;
            } else {
                eInCompleto += 1;
            }

        }


        System.out.println("  -->>  TODOS        =   " + (eCompleto + eInCompleto));
        System.out.println("  -->>  COMPLETO     =   " + eCompleto);
        System.out.println("  -->>  INCOMPLETO   =   " + eInCompleto);
        System.out.println("");


        int eTotal = 0;

        for (AnaliseDePesquisa eAnalise : mAnalises) {
            eTotal += eAnalise.getQuantidade();
        }

        int eGrupoID = 1;

        for (AnaliseDePesquisa eAnalise : mAnalises) {

            String mPorcentagem = getPorcentagem(eAnalise.getQuantidade(), eTotal);

            System.out.println("\t -->> " + getCasas(eGrupoID, 2) + "   ::   " + eAnalise.getGrupo() + "    Quantidade = " + getCasas(eAnalise.getQuantidade(), 2) + "    Porcentagem = " + getFormatado(mPorcentagem, 3, 2) + " %");

            eGrupoID += 1;
        }


    }

    public String getPorcentagem(int eQuantidade, int eTotal) {
        double eTaxa = (double) eQuantidade / (double) eTotal;
        return String.valueOf(eTaxa * 100.0);
    }


    public String getCasas(int t, int casas) {
        String s = String.valueOf(t);
        while (s.length() < casas) {
            s = "0" + s;
        }
        return s;
    }


    public String getFormatado(String s, int a, int d) {

        int i = 0;
        int o = s.length();

        String mAntes = "";
        String mDepois = "";

        boolean ePontuou = false;

        while (i < o) {
            String l = String.valueOf(s.charAt(i));

            if (ePontuou) {
                if (mDepois.length() < d) {
                    mDepois += l;
                }
            } else {
                if (l.contentEquals(".")) {
                    ePontuou = true;
                } else {
                    mAntes += l;
                }
            }


            i += 1;
        }

        while (mAntes.length() < a) {
            mAntes = "0" + mAntes;
        }

        while (mDepois.length() < d) {
            mDepois += "0";
        }


        return mAntes + "." + mDepois;

    }


}
