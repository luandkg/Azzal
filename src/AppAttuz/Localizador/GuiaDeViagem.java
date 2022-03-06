package AppAttuz.Localizador;


import Arquivos.BZZ;
import DKG.DKG;
import DKG.DKGObjeto;
import Tronarko.StringTronarko;
import Tronarko.Tozte;
import Tronarko.Hazde;

import java.util.ArrayList;

public class GuiaDeViagem {

    public static void unir(String eArquivoViagem) {

        ArrayList<String> arquivos = new ArrayList<String>();

        arquivos.add("/home/luan/Documentos/g/t7002.txt");
        arquivos.add("/home/luan/Documentos/g/t7003.txt");
        arquivos.add("/home/luan/Documentos/g/t7004.txt");

        DKG viagem = new DKG();
        DKGObjeto va = viagem.unicoObjeto("Viagem");


        for (String eArquivo : arquivos) {

            DKG viagemCorrente = new DKG();
            viagemCorrente.abrir(eArquivo);
            DKGObjeto vaCorrente = viagemCorrente.unicoObjeto("Viagem");

            va.getObjetos().addAll(vaCorrente.getObjetos());
        }


        viagem.salvar(eArquivoViagem);
    }

    public static void passei(String eArquivo, Tozte eTozte, Hazde eHazde) {


        System.out.println("All");
        DKG eDKC = new DKG();
        eDKC.abrir(eArquivo);

        int eTozteRef_min = (eTozte.getTronarko() * 500) + (eTozte.getHiperarko() * 50) + eTozte.getSuperarko();

        String cidade_anterior = "";

        StringTronarko st = new StringTronarko();

        boolean enc_fim = false;
        DKGObjeto fim = null;


        for (DKGObjeto ePonto : eDKC.unicoObjeto("Viagem").getObjetos()) {

            String visita_comecar = ePonto.identifique("Tozte").getValor();

            int t = Integer.parseInt(st.getTronarko(visita_comecar));
            int h = Integer.parseInt(st.getHiperarko(visita_comecar));
            int s = Integer.parseInt(st.getSuperarko(visita_comecar));


            int valor = (t * 500) + (h * 50) + s;


            if (valor <= eTozteRef_min) {

                //System.out.println("Inc :: " + eViagemCache.getObjetos().size());

                ePonto.identifique("TozteValor", valor);

                //   System.out.println("Ref :: " + valor + " em " + eTozteRef_min);

                if (ePonto.existeIdentificador("Cidade")) {

                    if (!cidade_anterior.contentEquals(ePonto.identifique("Cidade").getValor())) {

                        cidade_anterior = ePonto.identifique("Cidade").getValor();
                        System.out.println("\t -->> Passando em :: " + ePonto.identifique("Tozte").getValor() + " :: " + ePonto.identifique("Cidade").getValor());

                    }


                } else {

                }

                if (valor == eTozteRef_min) {
                    enc_fim = true;
                    fim = ePonto;
                }


            }

        }

        if (enc_fim) {

            if (fim.existeIdentificador("Cidade")) {

                System.out.println("\t -->> Hoje :: " + fim.identifique("Tozte").getValor() + " :: " + fim.identifique("Cidade").getValor());

            } else {

                System.out.println("\t -->> Viagem :: " + fim.identifique("TrilhaComecou").getValor() + " :: saiu de " + fim.identifique("Saiu").getValor());
                System.out.println("\t -->> Hoje   :: " + fim.identifique("Tozte").getValor());

            }

        }

    }

    public static void organizar(String entrada, String saida) {


        System.out.println(">> Organizar");

        DKG eDKC = new DKG();
        eDKC.abrir(entrada);


        String cidade_anterior = "";
        String trilha_comecou = "";
        String chegou_cidade = "";

        StringTronarko st = new StringTronarko();

        int org = 0;


        for (DKGObjeto ePonto : eDKC.unicoObjeto("Viagem").getObjetos()) {

            String visita_comecar = ePonto.identifique("Tozte").getValor();

            if (org<100){
                org=0;
                System.out.println("\t -->> Organizando :: " + visita_comecar);
            }

            org+=1;

            int t = Integer.parseInt(st.getTronarko(visita_comecar));
            int h = Integer.parseInt(st.getHiperarko(visita_comecar));
            int s = Integer.parseInt(st.getSuperarko(visita_comecar));

            int valor = (t * 500) + (h * 50) + s;

            //System.out.println("Inc :: " + eViagemCache.getObjetos().size());

            ePonto.identifique("TozteValor", valor);

            //   System.out.println("Ref :: " + valor + " em " + eTozteRef_min);

            if (ePonto.existeIdentificador("Cidade")) {

                if (!cidade_anterior.contentEquals(ePonto.identifique("Cidade").getValor())) {
                    cidade_anterior = ePonto.identifique("Cidade").getValor();
                }

                trilha_comecou = "";

                if (chegou_cidade.length()==0){
                    chegou_cidade=visita_comecar;
                }

                ePonto.identifique("ChegouCidade", chegou_cidade);

            } else {

                chegou_cidade="";

                ePonto.identifique("Saiu", cidade_anterior);

                if (trilha_comecou.length() == 0) {

                    trilha_comecou = ePonto.identifique("Tozte").getValor();

                    ePonto.identifique("TrilhaComecou").setValor(trilha_comecou);
                } else {
                    ePonto.identifique("TrilhaComecou").setValor(trilha_comecou);
                }

            }


        }

        eDKC.salvar(saida);

    }



}

