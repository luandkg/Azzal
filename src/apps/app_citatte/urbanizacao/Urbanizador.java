package apps.app_citatte.urbanizacao;

import apps.app_citatte.AreaAdministravel;
import apps.app_citatte.Citatte;
import apps.app_citatte.Stringum;
import apps.app_citatte.engenharia.AvenidaViaria;
import apps.app_citatte.engenharia.RosaDosVentos;
import apps.app_citatte.transito.Rota;
import apps.app_citatte.transito.Transito;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Aleatorio;
import libs.luan.Embaralhar;
import libs.luan.Lista;
import libs.luan.fmt;

import java.util.Random;

public class Urbanizador {


    public static void ORGANIZAR_ENDERECOS(Renderizador mCidade, Lista<AvenidaViaria> avenidas, Citatte eCitatte) {

        Cores mCores = new Cores();

        RosaDosVentos rosa_dos_ventos = new RosaDosVentos(avenidas);


        int area_especial = 1;


        for (AvenidaViaria avenida : avenidas) {

            Lista<String> pontos_status = new Lista<String>();

            for (Ponto pt : avenida.getPontos()) {
                String status = rosa_dos_ventos.getStatus(pt);
                pontos_status.adicionar(status);
            }

            String resultado = Stringum.GET_MAIOR_FREQUENCIA(pontos_status);

            //    fmt.print("Avenida {} com {} pontos tem [{}] mas é {}", avenida.getID(), avenida.getComprimento(), Stringum.GET_FREQUENCIA_ANALISE(pontos_status),resultado);

            avenida.setRosaDosVentos(resultado);

        }


        for (String sentido : RosaDosVentos.GET_SENTIDOS()) {

            Lista<AvenidaViaria> avenidas_no_sentido = new Lista<AvenidaViaria>();

            int avenida_sequencia = 1;

            for (AvenidaViaria avenida : avenidas) {
                if (avenida.getRosaDosVentos().contentEquals(sentido)) {
                    avenida.setAvenidaSequencia(avenida_sequencia);
                    avenida.zerarLoteSequencia();
                    avenidas_no_sentido.adicionar(avenida);
                    avenida_sequencia += 1;
                }
            }

            fmt.print("Sentido {} com {} avenidas !", sentido, avenidas_no_sentido.getQuantidade());
        }

        int POR_AVENIDA = 1;
        int POR_CASA = 2;

        int MAPEAR_POR = POR_CASA;

        for (AreaAdministravel area : eCitatte.getAreasAdministraveis()) {

            //   String status = rosa_dos_ventos.getStatus(area.getLocalizacao());

            //    fmt.print("LOCAL = " + area.getLocalizacao().toString() + " -->> " + status);

            if (MAPEAR_POR == POR_AVENIDA) {

                String avenida = area.getNome().replace("AV(", "");
                avenida = avenida.replace(")", "");

                if (area.getNome().startsWith("AV(")) {

                    int avenida_id = Integer.parseInt(avenida);
                    for (AvenidaViaria proc_avenida : avenidas) {
                        if (proc_avenida.getID() == avenida_id) {

                            area.setNome(proc_avenida.getRosaDosVentos() + " -- RUA " + proc_avenida.getAvenidaSequencia() + " LOTE " + proc_avenida.getLoteSequencia());
                            proc_avenida.proximoLote();

                            break;
                        }
                    }
                }

            } else {

                String avenida = area.getNome().replace("AV(", "");
                avenida = avenida.replace(")", "");

                if (area.getNome().startsWith("AV(")) {

                    int avenida_id = Integer.parseInt(avenida);
                    for (AvenidaViaria proc_avenida : avenidas) {
                        if (proc_avenida.getID() == avenida_id) {

                            String status = rosa_dos_ventos.getStatus(area.getCentroLocalizacao());

                            area.setNome(status + " -- RUA " + proc_avenida.getAvenidaSequencia() + " LOTE " + proc_avenida.getLoteSequencia());
                            proc_avenida.proximoLote();

                            break;
                        }
                    }
                } else if (area.getNome().startsWith("ESPECIAL")) {


                    String cor = area.getNome().replace("ESPECIAL ", "");

                    // fmt.print("AREA :: {} ->> {}", area.getNome(), cor);

                    String status = rosa_dos_ventos.getStatus(area.getCentroLocalizacao());

                    area.setNome(status + " -- ESPECIAL " + area_especial + " TIPO " + cor);

                    area_especial += 1;


                } else {
                    // fmt.print("AREA :: {}", area.getNome());
                }

            }


        }

    }


    public static Lista<AreaTipada> classificar_areas(Citatte mCitatte) {

        Lista<AreaTipada> ret = new Lista<AreaTipada>();


        Lista<AreaAdministravel> areas = new Lista<AreaAdministravel>();

        Lista<AreaAdministravel> areas_publicas = new Lista<AreaAdministravel>();
        Lista<AreaAdministravel> areas_privadas = new Lista<AreaAdministravel>();

        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {
            if (area.getNome().contains("ESPECIAL")) {
            } else {
                areas.adicionar(area);
            }
        }

        Embaralhar.emabaralhe(areas);


        int publica_porcentagem = 5;
        int publica_quantidade = (areas.getQuantidade() / 100) * publica_porcentagem;

        while (publica_quantidade > 0) {

            if (areas.getQuantidade() > 0) {
                areas_publicas.adicionar(areas.get(0));
                areas.remover_indice(0);
            }

            publica_quantidade -= 1;
        }

        areas_privadas.adicionar_varios(areas);


        for (AreaAdministravel privada : areas_privadas) {
            ret.adicionar(new AreaTipada(AreaTipada.AREA_PRIVADA, privada.getNome(), privada.getArea()));
        }

        for (AreaAdministravel publica : areas_publicas) {
            ret.adicionar(new AreaTipada(AreaTipada.AREA_PUBLICA, publica.getNome(), publica.getArea()));
        }

        return ret;
    }


    public static int contagem_privadas(Lista<AreaTipada> areas_tipadas) {
        int contando = 0;

        for (AreaTipada area : areas_tipadas) {
            if (area.isPrivada()) {
                contando += 1;
            }
        }

        return contando;
    }

    public static int contagem_publicas(Lista<AreaTipada> areas_tipadas) {
        int contando = 0;

        for (AreaTipada area : areas_tipadas) {
            if (area.isPublica()) {
                contando += 1;
            }
        }

        return contando;
    }


    public static int contagem_comercial(Lista<AreaTipada> areas_tipadas) {
        int contando = 0;

        for (AreaTipada area : areas_tipadas) {
            if (area.isComercial()) {
                contando += 1;
            }
        }

        return contando;
    }


    public static int contagem_residencial(Lista<AreaTipada> areas_tipadas) {
        int contando = 0;

        for (AreaTipada area : areas_tipadas) {
            if (area.isResidencial()) {
                contando += 1;
            }
        }

        return contando;
    }

    public static boolean isPrivada(Lista<AreaTipada> areas_tipadas, String eNome) {
        boolean resp = false;

        for (AreaTipada area : areas_tipadas) {
            if (area.getNome().contentEquals(eNome)) {
                if (area.isPrivada()) {
                    resp = true;
                }
                break;
            }

        }

        return resp;
    }


    public static boolean isPublica(Lista<AreaTipada> residencias, String eNome) {
        boolean resp = false;

        for (AreaTipada area : residencias) {
            if (area.getNome().contentEquals(eNome)) {
                if (area.isPublica()) {
                    resp = true;
                }
                break;
            }

        }

        return resp;
    }


    public static boolean isComercial(Lista<AreaTipada> areas_tipadas, String eNome) {
        boolean resp = false;

        for (AreaTipada area : areas_tipadas) {
            if (area.getNome().contentEquals(eNome)) {
                if (area.isComercial()) {
                    resp = true;
                }
                break;
            }

        }

        return resp;
    }


    public static boolean isResidencial(Lista<AreaTipada> areas_tipadas, String eNome) {
        boolean resp = false;

        for (AreaTipada area : areas_tipadas) {
            if (area.getNome().contentEquals(eNome)) {
                if (area.isResidencial()) {
                    resp = true;
                }
                break;
            }

        }

        return resp;
    }


    public static Lista<AvenidaViaria> classificar_areas_privadas(Citatte mCitatte, Lista<AreaTipada> areas_tipadas) {


        for (AreaTipada area : areas_tipadas) {
            if (area.isPrivada()) {
                area.setSubTipo(AreaTipada.PRIVADA_RESIDENCIAL);
            }
        }


        Lista<AvenidaViaria> avenidas_selecionadas = criar_avenidas_comerciais_v2(mCitatte);


        for (AvenidaViaria av : avenidas_selecionadas) {


            Lista<AreaAdministravel> areas = mCitatte.getAreasProximasDaAvenida(av, 20);

            for (AreaAdministravel area : areas) {

                for (AreaTipada area_proc : areas_tipadas) {
                    if (area_proc.getNome().contentEquals(area.getNome())) {
                        if (area_proc.isPrivada()) {
                            area_proc.setSubTipo(AreaTipada.PRIVADA_COMERCIAL);
                        }
                        break;
                    }

                }

            }

        }

        return avenidas_selecionadas;

    }


    public static Lista<AvenidaViaria> criar_avenidas_comerciais_v1(Citatte mCitatte) {

        int maior_avenida = 0;

        for (AvenidaViaria av : mCitatte.getAvenidas()) {
            if (av.getComprimento() > maior_avenida) {
                maior_avenida = av.getComprimento();
            }
        }


        fmt.print("Maior Avenida :: {}", maior_avenida);

        Lista<AvenidaViaria> avenidas_candidatas = new Lista<AvenidaViaria>();
        int avenida_maior_metade = maior_avenida / 2;

        for (AvenidaViaria av : mCitatte.getAvenidas()) {
            if (av.getComprimento() > avenida_maior_metade) {

                if (av.isPrimaria() || av.isTerminal()) {

                } else {
                    avenidas_candidatas.adicionar(av);
                }

            }
        }

        fmt.print("Av Candidatas :: {}", avenidas_candidatas.getQuantidade());


        Embaralhar.emabaralhe(avenidas_candidatas);


        Random sorte = new Random();

        int seleciondas = (avenidas_candidatas.getQuantidade() / 3) + (sorte.nextInt(avenidas_candidatas.getQuantidade() / 4));


        Lista<AvenidaViaria> avenidas_selecionadas = new Lista<AvenidaViaria>();


        while (seleciondas > 0 && avenidas_candidatas.getQuantidade() > 0) {

            avenidas_selecionadas.adicionar(avenidas_candidatas.get(0));
            avenidas_candidatas.remover_indice(0);

            seleciondas -= 1;
        }

        fmt.print("Av Comerciais :: {}", avenidas_selecionadas.getQuantidade());

        return avenidas_selecionadas;
    }


    public static Lista<AvenidaViaria> criar_avenidas_comerciais_v2(Citatte mCitatte) {


        Lista<AvenidaViaria> avenidas = Lista.EMBARALHAR(Lista.TIRAR_COPIA(mCitatte.getAvenidas()));
        Lista<AvenidaViaria> avenidas_v2 = Lista.TIRAR_COPIA(avenidas);

        AvenidaViaria av1 = Aleatorio.escolha_um(avenidas_v2);
        avenidas_v2.remover(av1);

        AvenidaViaria av2 = Aleatorio.escolha_um(avenidas_v2);
        avenidas_v2.remover(av2);

        AvenidaViaria av3 = Aleatorio.escolha_um(avenidas_v2);
        avenidas_v2.remover(av3);

        fmt.print("Av1 :: {}", av1.getID());
        fmt.print("Av2 :: {}", av2.getID());
        fmt.print("Av3 :: {}", av3.getID());


        Ponto origem = av1.getPontos().get(0);
        Ponto destino = av2.getPontos().get(0);

        Rota rota = Transito.criar_rota_melhor_de_100_inteligente(mCitatte.getAvenidas(), origem, destino);

        fmt.print("AV COMERCIAL PARTE 1 :: {}", rota.getAvenidas().getQuantidade());

        Lista<AvenidaViaria> avenidas_selecionadas = Lista.TIRAR_COPIA(rota.getAvenidas());

        origem = av2.getPontos().get(av2.getPontos().getQuantidade() - 1);
        destino = av3.getPontos().get(0);

        Rota rota2 = Transito.criar_rota_melhor_de_100_inteligente(mCitatte.getAvenidas(), origem, destino);
        avenidas_selecionadas.adicionar_varios(rota2.getAvenidas());

        fmt.print("AV COMERCIAL PARTE 2 :: {}", rota2.getAvenidas().getQuantidade());

        return avenidas_selecionadas;
    }


    public static void toArquivo(Lista<AvenidaViaria> av_comerciais, Lista<AreaTipada> areas_tipadas, String arquivo) {

        DKG documento = new DKG();
        DKGObjeto raiz_urbana = documento.unicoObjeto("Urbanizacao");
        DKGObjeto raiz_avenidas_comerciais = raiz_urbana.unicoObjeto("AvenidasComerciais");
        DKGObjeto raiz_areas = raiz_urbana.unicoObjeto("Areas");


        for (AvenidaViaria av : av_comerciais) {
            DKGObjeto obj_av = raiz_avenidas_comerciais.criarObjeto("AvenidaComercial");
            obj_av.identifique("ID").setInteiro(av.getID());
        }

        for (AreaTipada area : areas_tipadas) {
            DKGObjeto obj_av = raiz_areas.criarObjeto("Area");

            obj_av.identifique("X").setInteiro(area.getArea().getX());
            obj_av.identifique("Y").setInteiro(area.getArea().getY());

            obj_av.identifique("Largura").setInteiro(area.getArea().getLargura());
            obj_av.identifique("Comprimento").setInteiro(area.getArea().getAltura());

            obj_av.identifique("Nome").setValor(area.getNome());
            obj_av.identifique("Tipo").setInteiro(area.getTipo());
            obj_av.identifique("SubTipo").setInteiro(area.getSubTipo());
        }

        documento.salvar(arquivo);

    }

}
