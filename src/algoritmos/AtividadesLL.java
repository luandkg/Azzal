package algoritmos;

import apps.app_tozterum.TelegramTozterum;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.tempo.Data;
import libs.tronarko.Superarkos;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;
import libs.zetta.features.ZQC;
import servicos.ASSETS;

public class AtividadesLL {

    public static void VER() {

        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ASSETS.GET_PASTA("coisas/tozterum").getArquivo("Tozterum.az"), "STRAVA_ACOMPANHAMENTO_DADOS(LL)");
        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> atividade_por_tipo = ENTT.AGRUPAR(dados, "Tipo");

        for (Entidade grupo : atividade_por_tipo) {

            for (Entidade atv : grupo.getEntidades()) {
                atv.at("Tozte", Tronarko.getData(Data.toData(atv.at("Data")).getTempoLegivel()).getTextoZerado());
                atv.at("Tozte_Superarkos", Tronarko.getData(Data.toData(atv.at("Data")).getTempoLegivel()).getSuperarkosTotal());
            }

            ENTT.ORDENAR_LONG(grupo.getEntidades(), "Tozte_Superarkos");

            if (grupo.getEntidades().possuiObjetos()) {
                grupo.at("Quantidade", ENTT.CONTAGEM(grupo.getEntidades()));
                grupo.at("Primeiro", ENTT.GET_PRIMEIRO(grupo.getEntidades()).at("Tozte"));
                grupo.at("Ultimo", ENTT.GET_ULTIMO(grupo.getEntidades()).at("Tozte"));

                if (grupo.is("Tipo", "RUN") || grupo.is("Tipo", "WALK") || grupo.is("Tipo", "RIDE")) {

                    double distancia = 0.0;

                    for (Entidade atv : grupo.getEntidades()) {
                        if (atv.at("Distancia").endsWith(" km")) {
                            double valor = Double.parseDouble(atv.at("Distancia").replace(" km", ""));
                            distancia += valor;
                        }
                    }

                    grupo.at("Distancia", fmt.f2(distancia) + " Km");
                }

                int tempo = 0;
                for (Entidade atv : grupo.getEntidades()) {
                    if (Strings.contar(atv.at("Tempo"), ":") == 1) {
                        int iMinutos = Integer.parseInt(Strings.GET_ATE(atv.at("Tempo"), ":"));
                        int iSegundos = Integer.parseInt(Strings.GET_DEPOIS(atv.at("Tempo"), ":"));
                        tempo += (iMinutos * 60) + (iSegundos);
                    } else if (Strings.contar(atv.at("Tempo"), ":") == 2) {
                        int iHoras = Integer.parseInt(Strings.GET_ATE(atv.at("Tempo"), ":"));
                        String iMinutosESegundos = Strings.GET_DEPOIS(atv.at("Tempo"), ":");
                        int iMinutos = Integer.parseInt(Strings.GET_ATE(iMinutosESegundos, ":"));
                        int iSegundos = Integer.parseInt(Strings.GET_DEPOIS(iMinutosESegundos, ":"));
                        tempo += (iHoras * 60 * 60) + (iMinutos * 60) + (iSegundos);
                    }
                }
                grupo.at("Tempo", fmt.formatar_tempo(tempo));


            }

        }

        ENTT.AT_ALTERAR_NOME(atividade_por_tipo, "Tipo", "Esporte");
        ENTT.AT_ALTERAR_NOME(atividade_por_tipo, "Quantidade", "Atividades");

        ENTT.ORDENAR_INTEIRO_DECRESCENTE(atividade_por_tipo, "Atividades");
        ENTT.REMOVER_SE(atividade_por_tipo, "Esporte", "WATER SPORT");

        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo, "Esporte", "RUN", "Corrida");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo, "Esporte", "WORKOUT", "Treinos");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo, "Esporte", "SWIM", "Natação");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo, "Esporte", "WALK", "Caminhadas");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo, "Esporte", "RIDE", "Ciclismo");


        ENTT.EXIBIR_TABELA(atividade_por_tipo);

    }


    public static Lista<Entidade> ACADEMIA() {

        ZQC.EXIBIR_COLECOES_RESUMO(TelegramTozterum.GET_ARQUIVO_TOZTERUM());


        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(TelegramTozterum.GET_ARQUIVO_TOZTERUM(), "STRAVA_ACOMPANHAMENTO_DADOS(LL)");
        Lista<Entidade> academia_treinos = ENTT.COLETAR(dados, "Tipo", "WORKOUT");

        for (Entidade treino : academia_treinos) {
            treino.at("Tozte", Tronarko.getData(treino.at("Data")).getTextoZerado());

            String treino_tipos_bruto = Strings.RETIRAR_ACENTOS(treino.at("Nome").toUpperCase());

            if (treino_tipos_bruto.contains("PEITORAL") || treino_tipos_bruto.contains("PEITO") || treino_tipos_bruto.contains("CHEST")) {
                treino.at("Peito", "SIM");
            }

            if (treino_tipos_bruto.contains("OMBROS")) {
                treino.at("Ombros", "SIM");
            }

            if (treino_tipos_bruto.contains("BICEPS")) {
                treino.at("Biceps", "SIM");
            }

            if (treino_tipos_bruto.contains("TRICEPS")) {
                treino.at("Triceps", "SIM");
            }

            if (treino_tipos_bruto.contains("COSTAS") || treino_tipos_bruto.contains("BACK")) {
                treino.at("Costas", "SIM");
            }

            if (treino_tipos_bruto.contains("PERNA") || treino_tipos_bruto.contains("CAMBITOS") || treino_tipos_bruto.contains("LEDGAY") || treino_tipos_bruto.contains("PANTURRILHA")) {
                treino.at("Perna", "SIM");
            }

            if (treino_tipos_bruto.contains("ADB") || treino_tipos_bruto.contains("ABDOMINAL")) {
                treino.at("Abdominal", "SIM");
            }
        }

        ENTT.EXIBIR_TABELA(academia_treinos);


        Tozte tozte_ref = StringTronarko.PARSER_TOZTE("10/08/7004");
        Tozte tozte_hoje = Tronarko.getTozte();

        Lista<Tozte> toztes = new Lista<Tozte>();
        while (tozte_ref.isMenorIgualQue(tozte_hoje)) {
            if (tozte_ref.getSuperarko_Status() == Superarkos.OMEGA) {
                toztes.adicionar(tozte_ref.getCopia());
            }
            tozte_ref = tozte_ref.adicionar_Superarko(1);
        }

        for(Tozte tz : toztes){
            fmt.print("++ {}",tz.getTextoZerado());
            PONTUAR(tz,academia_treinos);
        }


        return academia_treinos;
    }


    public static void PONTUAR(Tozte TT ,Lista<Entidade> academia_treinos){

        Lista<Entidade> treinos_evolucao = ENTT.CRIAR_LISTA();


        if (TT.getSuperarko_Status() == Superarkos.OMEGA) {
            fmt.print("Academia Progresso por Megarko");

            Tozte desde = TT.adicionar_Superarko(-10);
            Tozte ate = TT.getCopia();

            fmt.print("Desde : {}", desde.getTextoZerado());
            fmt.print("Até   : {}", ate.getTextoZerado());

            Lista<Entidade> treinos_mega = new Lista<Entidade>();
            for (Entidade treino : academia_treinos) {
                Tozte treino_tozte = StringTronarko.PARSER_TOZTE(treino.at("Tozte"));
                if (treino_tozte.isMaiorIgualQue(desde) && treino_tozte.isMenorQue(ate)) {
                    treinos_mega.adicionar(treino);
                }
            }

            ENTT.EXIBIR_TABELA(treinos_mega);

            int pontuacao = 0;

            if(treinos_mega.getQuantidade()>=7){
                pontuacao+=10;
            }else{
                if(treinos_mega.getQuantidade()>=5){
                    pontuacao+=5;
                }
            }

            int treinou_corpo_todo=0;
            boolean fez_abdominal=false;

            if(ENTT.EXISTE(treinos_mega,"Peito","SIM")){
                pontuacao+=1;
                treinou_corpo_todo+=1;
            }
            if(ENTT.EXISTE(treinos_mega,"Costas","SIM")){
                pontuacao+=1;
                treinou_corpo_todo+=1;
            }
            if(ENTT.EXISTE(treinos_mega,"Ombros","SIM")){
                pontuacao+=1;
                treinou_corpo_todo+=1;
            }
            if(ENTT.EXISTE(treinos_mega,"Biceps","SIM")){
                pontuacao+=1;
                treinou_corpo_todo+=1;
            }
            if(ENTT.EXISTE(treinos_mega,"Triceps","SIM")){
                pontuacao+=1;
                treinou_corpo_todo+=1;
            }
            if(ENTT.EXISTE(treinos_mega,"Perna","SIM")){
                pontuacao+=1;
                treinou_corpo_todo+=1;
            }
            if(ENTT.EXISTE(treinos_mega,"Abdominal","SIM")){
                pontuacao+=2;
                fez_abdominal=true;
            }

            if(treinou_corpo_todo==6){
                pontuacao+=5;
            }

            if(treinou_corpo_todo==6 && fez_abdominal){
                pontuacao+=3;
            }

            if(pontuacao>0){
                fmt.print("Pontuação : +{} Roftz",pontuacao);

                Entidade e_evolucao = ENTT.GET_SEMPRE(treinos_evolucao,"Tozte",ate.getTextoZerado());
                e_evolucao.at("Status","Progresso");
                e_evolucao.at("Pontuacao",pontuacao);

            }else{
                fmt.print("Toma vergonha nessa cara e vai treinar porra !!!");

                Entidade e_evolucao = ENTT.GET_SEMPRE(treinos_evolucao,"Tozte",ate.getTextoZerado());
                e_evolucao.at("Status","Decepcionante");
                e_evolucao.at("Pontuacao",0);

            }


        }

        ENTT.EXIBIR_TABELA(treinos_evolucao);


        Lista<Entidade> evolucao_completa = ENTT.ABRIR(ASSETS.GET_PASTA("coisas").getArquivo("TreinoEvolucao.entts"));

        for(Entidade e : treinos_evolucao){
            Entidade e_real = ENTT.GET_SEMPRE(evolucao_completa,"Tozte",e.at("Tozte"));
            e_real.at("Status",e.at("Status"));
            e_real.at("Pontuacao",e.at("Pontuacao"));
        }

        ENTT.GUARDAR(evolucao_completa,ASSETS.GET_PASTA("coisas").getArquivo("TreinoEvolucao.entts"));


    }

    public static void VER_PONTUACOES(){

        Lista<Entidade> evolucao_completa = ENTT.ABRIR(ASSETS.GET_PASTA("coisas").getArquivo("TreinoEvolucao.entts"));

        ENTT.EXIBIR_TABELA_COM_TITULO(evolucao_completa,"PONTUAÇÃO");
    }

}
