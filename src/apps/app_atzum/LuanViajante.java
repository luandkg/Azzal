package apps.app_atzum;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.utils.FerramentaGPS;
import libs.azzal.geometria.Ponto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.luan.Lista;
import libs.luan.fmt;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;

public class LuanViajante {


    public static void INIT() {

        String arquivo_dados_corrida = new PastaFS("/home/luan/assets").getArquivo("luan_viajante_dados.entts");
        String arquivo_viagem = new PastaFS("/home/luan/assets").getArquivo("luan_viajante.entts");


        Lista<Entidade> viagem_dados = ENTT.ABRIR(arquivo_viagem);
        Lista<Entidade> luan_correndo = ENTT.ABRIR(arquivo_dados_corrida);
        Lista<Entidade> cidades = ENTT.ABRIR(AtzumCreator.DADOS_GET_ARQUIVO("AtzumCidades.entts"));
        Lista<Entidade> viagem_chegadas_em_destino =new   Lista<Entidade>();


        ENTT.EXIBIR_TABELA(luan_correndo);

        Tozte tozte = Tronarko.getTozte().adicionar_Superarko(-35);

        for (Entidade e : luan_correndo) {
            e.at("Tozte", tozte.getTextoZerado());
            tozte = tozte.adicionar_Superarko(1);
        }

         tozte = Tronarko.getTozte().adicionar_Superarko(0);

        for (Entidade e : luan_correndo) {
            e.at("Tozte", tozte.getTextoZerado());
            tozte = tozte.adicionar_Superarko(1);
            e.at("Viajou", "NAO");
        }

          ENTT.GUARDAR(luan_correndo,arquivo_dados_corrida);
        //   ENTT.EXIBIR_TABELA(cidades);


        Lista<Entidade> corridas_ate_hoje = new Lista<Entidade>();

        Tozte tozte_hoje = Tronarko.getTozte();

        for (Entidade corrida : luan_correndo) {
            if (corrida.isDiferente("Viajou", "SIM") && corrida.is("Tipo", "RUN")) {
                if (StringTronarko.parseTozte(corrida.at("Tozte")).isMenorIgualQue(tozte_hoje)) {
                    corridas_ate_hoje.adicionar(corrida);
                }
            }
        }

        viagem_dados.limpar();

        if (ENTT.TEM(corridas_ate_hoje)) {

            double distancia = 0;

            for (Entidade e : corridas_ate_hoje) {
                distancia += Double.parseDouble(e.at("Distancia").replace(" km", ""));
            }

            fmt.print("+ Distancia : {}", fmt.f2(distancia));


            Entidade e_viagem = ENTT.GET_SEMPRE(viagem_dados, "Info", "Viajante");
            e_viagem.at("Atualizando", Tronarko.getTozte().getTextoZerado());


            if (e_viagem.isDiferente("Status", "Iniciada")) {

                Ponto luan_viajante = new Ponto(806, 1237);
                String cidade_corrente = CIDADE_TEM_NOME(luan_viajante.getX(), luan_viajante.getY(), cidades);

                fmt.print(">> Estou   : {} ", cidade_corrente);

                Lista<Entidade> proximas = CIDADES_PROXIMAS(luan_viajante.getX(), luan_viajante.getY(), cidades);
                ENTT.ATRIBUTO_ALTERAR(cidades, "Status", "");

                Entidade proxima = ENTT.GET_ALEATORIO(proximas);
                proxima.at("Status", "Destino");

                ENTT.GET_SEMPRE(cidades, "Nome", cidade_corrente).at("Status", "Origem");


                double distancia_da_viagem = Espaco2D.distancia_entre_pontos(luan_viajante.getX(), luan_viajante.getY(), proxima.atInt("X"), proxima.atInt("Y"));
                fmt.print(">> Ir para : {} = {}", proxima.at("Nome"), fmt.f2(distancia_da_viagem) + " Dz");

                e_viagem.at("Status", "Iniciada");
                e_viagem.at("Origem", cidade_corrente);
                e_viagem.at("Destino", proxima.at("Nome"));
                e_viagem.at("Distancia", distancia_da_viagem);
                e_viagem.at("Percorrido", distancia);


                ENTT.EXIBIR_TABELA_COM_TITULO(viagem_dados, "VIAGEM DADOS");

                Entidade a1 =  ENTT.GET_SEMPRE(cidades, "Nome", cidade_corrente);
                Entidade a2 =  ENTT.GET_SEMPRE(cidades, "Nome", proxima.at("Nome"));


                fmt.print("A1 : {}",a1.at("Nome"));
                fmt.print("A2 : {}",a2.at("Nome"));

                double a1_a2 = Espaco2D.distancia_entre_pontos(a1.atInt("X"),a1.atInt("Y"),a2.atInt("X"),a2.atInt("Y"));
                fmt.print("Dist : {}",fmt.f2(a1_a2));


                Lista<Ponto> rota= FerramentaGPS.criarRotaMelhorDe3(a1.atInt("X"),a1.atInt("Y"),a2.atInt("X"),a2.atInt("Y"));
                fmt.print("Rota : {} = {}",rota.getQuantidade(),rota.getUltimoValor().toString());

            }


            e_viagem.at("Percorrido", e_viagem.atDoubleOuPadrao("Percorrido", 0) + distancia);
            fmt.print("+ Percorrido : {}", fmt.f2(e_viagem.atDouble("Percorrido")));

            if ((e_viagem.atDoubleOuPadrao("Percorrido", 0.0) >= e_viagem.atDoubleOuPadrao("Distancia", 0.0))) {

                Entidade e_etapa = ENTT.GET_SEMPRE(viagem_dados, "Info", "ETP::" + ENTT.CONTAGEM(viagem_dados));
                e_etapa.at("Tozte", Tronarko.getTozte().getTextoZerado());
                e_etapa.at("Distancia", fmt.f2(distancia));
                e_etapa.at("Status", "Cheguei");
                e_etapa.at("Origem", e_viagem.at("Origem"));
                e_etapa.at("Destino", e_viagem.at("Destino"));


                Entidade cidade_atual = ENTT.GET_SEMPRE(cidades, "Nome", e_viagem.at("Destino"));

                Lista<Entidade> proximas = CIDADES_PROXIMAS(cidade_atual.atInt("X"), cidade_atual.atInt("Y"), cidades);
                ENTT.ATRIBUTO_ALTERAR(cidades, "Status", "");

                Entidade proxima = ENTT.GET_ALEATORIO(proximas);
                proxima.at("Status", "Destino");

                double distancia_da_viagem = Espaco2D.distancia_entre_pontos(cidade_atual.atInt("X"), cidade_atual.atInt("Y"), proxima.atInt("X"), proxima.atInt("Y"));
                fmt.print(">> Ir para : {} = {}", proxima.at("Nome"), fmt.f2(distancia_da_viagem) + " Dz");


                e_viagem.at("Origem", e_viagem.at("Destino"));
                e_viagem.at("Destino", proxima.at("Nome"));
                e_viagem.at("Distancia", fmt.f2(distancia_da_viagem));
                e_viagem.at("Percorrido", 0);

                e_viagem.at("ViagemStatus", "Turistando");
                e_viagem.at("PID", 1);

            } else {

                Entidade e_etapa = ENTT.GET_SEMPRE(viagem_dados, "Info", "ETP::" + ENTT.CONTAGEM(viagem_dados));
                e_etapa.at("Tozte", Tronarko.getTozte().getTextoZerado());
                e_etapa.at("Origem", e_viagem.at("Origem"));
                e_etapa.at("Destino", e_viagem.at("Destino"));
                e_etapa.at("Distancia", fmt.f2(distancia));
                e_etapa.at("Percorrido", e_viagem.atDoubleOuPadrao("Percorrido", 0.0));
                e_etapa.at("Status", "Viajando");
                e_etapa.at("Parada", e_viagem.atIntOuPadrao("PID", 0));

                e_viagem.at("ViagemStatus", "Viajando");
                e_viagem.at("PID", e_viagem.atIntOuPadrao("PID", 0) + 1);

            }

            for (Entidade e : corridas_ate_hoje) {
                e.at("Viajou", "SIM");
            }

            ENTT.GUARDAR(luan_correndo, arquivo_dados_corrida);

        }

        ENTT.EXIBIR_TABELA_COM_TITULO(viagem_dados, "VIAGEM DADOS");
        ENTT.GUARDAR(viagem_dados, arquivo_viagem);

        Entidade e_viagem = ENTT.GET_SEMPRE(viagem_dados, "Info", "Viajante");

        if(e_viagem.is("Atualizando",Tronarko.getTozte().getTextoZerado()) && e_viagem.is("ViagemStatus","Turistando") ){
            fmt.print(">> TURISTAR EM {}",e_viagem.at("Origem"));
        }







    }

    public static String CIDADE_TEM_NOME(int px, int py, Lista<Entidade> cidades) {
        String ret = "";

        for (Entidade e : cidades) {
            if (e.atInt("X") == px && e.atInt("Y") == py) {
                ret = e.at("Nome");
                break;
            }
        }

        return ret;
    }

    public static Lista<Entidade> CIDADES_PROXIMAS(int px, int py, Lista<Entidade> cidades) {

        for (Entidade e : cidades) {
            e.at("Distancia", Espaco2D.distancia_entre_pontos(px, py, e.atInt("X"), e.atInt("Y")));
        }

        ENTT.ORDENAR_DOUBLE(cidades, "Distancia");

        Lista<Entidade> proximas = new Lista<Entidade>();

        for (Entidade e : cidades) {
            if (e.atInt("X") != px || e.atInt("Y") != py) {

                proximas.adicionar(e);
                if (proximas.getQuantidade() >= 10) {
                    break;
                }

            }


        }


        return proximas;
    }

    public static Lista<Entidade> CIDADES_PROXIMAS_INCLUINDO(int px, int py, Lista<Entidade> cidades) {

        for (Entidade e : cidades) {
            e.at("Distancia", Espaco2D.distancia_entre_pontos(px, py, e.atInt("X"), e.atInt("Y")));
        }

        ENTT.ORDENAR_DOUBLE(cidades, "Distancia");

        Lista<Entidade> proximas = new Lista<Entidade>();

        for (Entidade e : cidades) {
            proximas.adicionar(e);
            if (proximas.getQuantidade() >= 10) {
                break;
            }
        }


        return proximas;
    }

}
