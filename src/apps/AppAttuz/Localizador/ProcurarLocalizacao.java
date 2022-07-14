package apps.AppAttuz.Localizador;

import apps.AppAttuz.Mapa.Viajante;
import libs.DKG.DKG;
import libs.DKG.DKGObjeto;

import java.io.File;
import libs.Tronarko.Utils.StringTronarko;
import libs.Tronarko.Tozte;
import libs.Tronarko.Hazde;

public class ProcurarLocalizacao {

    public String ondeEstou(Viajante EU, Tozte eTozte, Hazde eHazde) {


        String arqCache = "/home/luan/Documentos/Cache.txt";
        String arqTronarko = "/home/luan/Documentos/t" + eTozte.getTronarko() + ".txt";

        //String arqCache = FS.getArquivoLocal("libs.Tronarko/Cache.txt");
        //String arqTronarko = FS.getArquivoLocal("libs.Tronarko/" + "t" + eTozte.getTronarko() + ".txt");

        // if (!FS.arquivoExiste("libs.Tronarko", "t" + eTozte.getTronarko() + ".txt")) {

        if (inCache(arqCache, eTozte, eHazde)) {

            System.out.println("Cache");
            DKG eDKC = new DKG();
            eDKC.abrir(arqCache);

            return procurando(eDKC, EU, eTozte, eHazde);
        } else {

            System.out.println("All");
            DKG eDKC = new DKG();
            eDKC.abrir(arqTronarko);

            montarCache(eDKC, eTozte, arqCache);

            return procurando(eDKC, EU, eTozte, eHazde);
        }

    }

    public String procurando(DKG eDKG, Viajante EU, Tozte eTozte, Hazde eHazde) {

        DKGObjeto eViagem = eDKG.unicoObjeto("Viagem");

        int valor = (eTozte.getHiperarko() * 50 * 10 * 100) + (eTozte.getSuperarko() * 10 * 100) + (eHazde.getArco() * 100) + eHazde.getItta();

        System.out.println("V :: " + valor);

        String estou = "---";

        String primeiro = "";
        String ultimo = "";

        int i = 0;

        boolean isVisitando = false;
        String visita_comecar = "";

        boolean isViajando = false;
        String viagem_comecar = "";
        String trilha_comecar = "";
        String cidade_chegou = "";

        String momento = "";

        for (DKGObjeto ePonto : eViagem.getObjetos()) {

            int eValor = ePonto.identifique("Valor").getInteiro(0);

            //  System.out.println("T :: " + ePonto.identifique("Tozte").getValor());
            //  System.out.println("V :: " + ePonto.identifique("Valor").getValor());


            if (eValor < valor) {
                estou = ePonto.identifique("x").getValor() + ":" + ePonto.identifique("y").getValor();


                if (ePonto.existeIdentificador("Cidade")) {
                    estou += " :: Visitando " + ePonto.identifique("Cidade").getValor();

                    if (!isVisitando) {
                        visita_comecar = ePonto.identifique("Tozte").getValor();
                    }

                    isVisitando = true;
                    isViajando = false;

                    momento= ePonto.identifique("Momento").getValor();
                    cidade_chegou = ePonto.identifique("ChegouCidade").getValor();

                } else {
                    estou += " :: Viajando...";

                    if (!isViajando) {
                        viagem_comecar = ePonto.identifique("Tozte").getValor();
                    }

                    isVisitando = false;
                    isViajando = true;
                    trilha_comecar = ePonto.identifique("TrilhaComecou").getValor();

                }

                if (i == 0) {
                    primeiro = ePonto.identifique("Tozte").getValor() + " :: " + estou;
                }

                ultimo = ePonto.identifique("Tozte").getValor() + " :: " + estou;

                EU.setPos(ePonto.identifique("x").getInteiro(), ePonto.identifique("y").getInteiro());

                i += 1;

            } else {
                break;
            }


        }

        System.out.println("T1 :: " + primeiro);
        System.out.println("TO :: " + ultimo);

        if (isVisitando) {
            ViajemAcao.onVisita(cidade_chegou,visita_comecar);
        }

        if (isViajando) {
            ViajemAcao.onEscurcao(trilha_comecar, viagem_comecar);
        }

        return estou;
    }

    public boolean inCache(String arqCache, Tozte eTozte, Hazde eHazde) {
        boolean ret = false;


        int valor = (eTozte.getTronarko() * 500) + (eTozte.getHiperarko() * 50) + eTozte.getSuperarko();

        if (new File(arqCache).exists()) {

            DKG eDKC = new DKG();
            eDKC.abrir(arqCache);
            DKGObjeto eViagem = eDKC.unicoObjeto("Viagem");

            for (DKGObjeto ePonto : eViagem.getObjetos()) {
                int eValor = ePonto.identifique("TozteValor").getInteiro(0);
                if (eValor == valor) {
                    ret = true;
                    break;
                }
            }
        }

        return ret;
    }

    public void montarCache(DKG eDKG, Tozte eTozte, String eArquivo) {

        int eTozteRef_min = (eTozte.getTronarko() * 500) + (eTozte.getHiperarko() * 50) + eTozte.getSuperarko();

        int eTozteRef_max = eTozteRef_min + 10;

        DKG eCache = new DKG();
        DKGObjeto eViagemCache = eCache.unicoObjeto("Viagem");


        DKGObjeto eViagem = eDKG.unicoObjeto("Viagem");

        StringTronarko st = new StringTronarko();

        for (DKGObjeto ePonto : eViagem.getObjetos()) {

            String visita_comecar = ePonto.identifique("Tozte").getValor();

            int t = Integer.parseInt(st.getTronarko(visita_comecar));
            int h = Integer.parseInt(st.getHiperarko(visita_comecar));
            int s = Integer.parseInt(st.getSuperarko(visita_comecar));


            int valor = (t * 500) + (h * 50) + s;


            if (valor >= eTozteRef_min && valor < eTozteRef_max) {

                //System.out.println("Inc :: " + eViagemCache.getObjetos().size());

                ePonto.identifique("TozteValor", valor);

                //   System.out.println("Ref :: " + valor + " em " + eTozteRef_min);

                eViagemCache.getObjetos().add(ePonto);
            }

        }

        eCache.salvar(eArquivo);
    }


}
