package AppAttuz.Localizador;


import DKG.DKG;
import DKG.DKGObjeto;
import Tronarko.StringTronarko;
import Tronarko.Tozte;
import Tronarko.Hazde;

public class GuiaDeViagem {

    public static void passei(Tozte eTozte, Hazde eHazde) {

        String arqTronarko = "/home/luan/Documentos/viagem_teste.txt";


        System.out.println("All");
        DKG eDKC = new DKG();
        eDKC.abrir(arqTronarko);

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
                System.out.println("\t -->> Hoje   :: " + fim.identifique("Tozte").getValor()  );

            }

        }

    }

    public static void organizar() {

        String arqTronarko = "/home/luan/Documentos/t7002.txt";


        System.out.println("All");
        DKG eDKC = new DKG();
        eDKC.abrir(arqTronarko);


        String cidade_anterior = "";
        String trilha_comecou = "";

        StringTronarko st = new StringTronarko();

        for (DKGObjeto ePonto : eDKC.unicoObjeto("Viagem").getObjetos()) {

            String visita_comecar = ePonto.identifique("Tozte").getValor();

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

            } else {

                ePonto.identifique("Saiu", cidade_anterior);

                if (trilha_comecou.length() == 0) {

                    trilha_comecou = ePonto.identifique("Tozte").getValor();

                    ePonto.identifique("TrilhaComecou").setValor(trilha_comecou);
                }else{
                    ePonto.identifique("TrilhaComecou").setValor(trilha_comecou);
                }

            }


        }

        eDKC.salvar("/home/luan/Documentos/viagem_teste.txt");

    }

}

