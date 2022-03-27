package AppAttuz;

import AppAttuz.Ferramentas.GPS;
import AppAttuz.Mapa.Local;
import AppAttuz.Politicamente.Cidades;
import AppAttuz.Politicamente.EquipamentosDeEngenharia;
import Azzal.Formatos.Ponto;
import DKG.DKG;
import DKG.DKGObjeto;
import DKG.CadaObjeto;
import Luan.Opcional;
import Luan.RefString;
import Luan.fmt;
import Tronarko.Tozte;
import Tronarko.Tronarko;
import Tronarko.Hazde;

import java.util.ArrayList;

public class ViagemCompleta {

    public static void motrarCidades() {

        String arq_desorganizada = "/home/luan/Documentos/viagem_desorganizada.txt";


        DKG eDKC = new DKG();
        eDKC.abrir(arq_desorganizada);

        DKGObjeto viagem = eDKC.unicoObjeto("Viagem");

        RefString cidade_anterior = new RefString();

        viagem.paraCadaObjeto(new CadaObjeto() {
            @Override
            public void dentro(DKGObjeto objeto) {

                boolean isCidade = objeto.id_existe("Cidade");

                if (isCidade) {

                    String cidade = objeto.id_valor("Cidade");
                    String tozte_corrente = objeto.id_valor("Tozte");

                    if (cidade_anterior.isDiferente(cidade)) {
                        System.out.println(tozte_corrente + " ->> " + cidade);
                        cidade_anterior.set(cidade);
                    }

                } else {
                    cidade_anterior.set("");
                }

            }
        });


    }

    public static ArrayList<String> getCidades() {

        String arq_desorganizada = "/home/luan/Documentos/viagem_desorganizada.txt";


        DKG eDKC = new DKG();
        eDKC.abrir(arq_desorganizada);

        DKGObjeto viagem = eDKC.unicoObjeto("Viagem");

        RefString cidade_anterior = new RefString();

        ArrayList<String> cidades = new ArrayList<String>();

        viagem.paraCadaObjeto(new CadaObjeto() {
            @Override
            public void dentro(DKGObjeto objeto) {

                boolean isCidade = objeto.id_existe("Cidade");

                if (isCidade) {

                    String cidade = objeto.id_valor("Cidade");
                    String tozte_corrente = objeto.id_valor("Tozte");

                    if (cidade_anterior.isDiferente(cidade)) {
                        System.out.println(tozte_corrente + " ->> " + cidade);
                        cidade_anterior.set(cidade);
                        cidades.add(cidade);
                    }

                } else {
                    cidade_anterior.set("");
                }

            }
        });

        return cidades;
    }

    public static void guardar(String eArquivo) {

        ArrayList<String> cidades = getCidades();

        DKG eDKC = new DKG();

        DKGObjeto viagem = eDKC.unicoObjeto("Viagem");

        for (String cidade : cidades) {
            viagem.criarObjeto("Cidade").identifique("Nome", cidade);
        }

        eDKC.salvar(eArquivo);

    }

    public static ArrayList<String> getCidadesDoArquivo(String eArquivo) {

        ArrayList<String> cidades = new ArrayList<String>();

        DKG eDKC = new DKG();
        eDKC.abrir(eArquivo);

        DKGObjeto viagem = eDKC.unicoObjeto("Viagem");
        for (DKGObjeto cidade : viagem.getObjetos()) {
            cidades.add(cidade.id_valor("Nome"));
        }

        return cidades;
    }

    public static void remontar_GuiaDeViagem() {

        ArrayList<String> cidades = new ArrayList<String>();

        // guardar("/home/luan/Documentos/viagem_cidades.txt");
        cidades = getCidadesDoArquivo("/home/luan/Documentos/viagem_cidades.txt");

        Tronarko Tronarkum = new Tronarko();

        Tozte tozte_corrente = Tronarkum.getData("01/01/2022");
        Hazde hazde_corrente = new Hazde(0, 0, 0);

        ArrayList<Local> cidades_locais = Cidades.getCidades();

        Opcional<Local> mapa_pos_anterior = new Opcional<>();

        for (String cidade : cidades) {

            Local mapa_pos = Cidades.getLocalizacao(cidades_locais, cidade);

            String faixa_temporal = tozte_corrente + " - " + hazde_corrente.getTextoSemUzzonZerado();

            if (mapa_pos != null) {

                String s_mapa_pos = mapa_pos.getX() + "::" + mapa_pos.getY();
                String s_distancia = "";

                if (mapa_pos_anterior.temValor()) {

                    int distancia = EquipamentosDeEngenharia.distancia(mapa_pos_anterior.get().getX(), mapa_pos_anterior.get().getY(), mapa_pos.getX(), mapa_pos.getY());

                    ArrayList<Ponto> rota = GPS.criarRota(mapa_pos_anterior.get().getX(), mapa_pos_anterior.get().getY(), mapa_pos.getX(), mapa_pos.getY());

                    int rota_distancia = Unidades.Pontos_to_Stgz(rota.size());
                    int rota_distancia_km = Unidades.Stgz_to_Km(rota_distancia);

                    s_distancia = String.valueOf(distancia) + " Stgz rota = " + rota_distancia + " Stgz" + "   -->> " + rota_distancia_km + " Km";

                }

                fmt.print(" -->> {dir30} :: {dir15} {dir25} -- {dir20}", cidade, s_mapa_pos, s_distancia, faixa_temporal);

                mapa_pos_anterior.set(mapa_pos);
            }

        }


    }

}
