package apps.app_gg;

import apps.app_citatte.Stringum;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.dkg.DKG;
import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.dkg.DKGUtils;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tempo.Data;

import java.nio.charset.StandardCharsets;

public class GGADF2023 {

    public static void init() {


        fmt.print();
        fmt.print("---------------------- GG ADF :: 2023 -----------------------");
        fmt.print();


        String arquivo_b1 = "/home/luan/Documentos/GG/2023/TRADICIONAL_0.cache";
        String arquivo_b2 = "/home/luan/Documentos/GG/2023/TRADICIONAL_5.cache";
        String arquivo_b3 = "/home/luan/Documentos/GG/2023/CONTINUA_6.cache";

        Lista<DKGObjeto> turmas_analisadas = new Lista<>();

        Lista<DKGObjeto> alunos_todos_bimestres = new Lista<>();
        Lista<DKGObjeto> alunos_3_bimestre = new Lista<>();


        BIMESTRE(arquivo_b1, "B1", turmas_analisadas, alunos_todos_bimestres, new Lista<>());
        BIMESTRE(arquivo_b2, "B2", turmas_analisadas, alunos_todos_bimestres, new Lista<>());
        BIMESTRE(arquivo_b3, "B3", turmas_analisadas, alunos_todos_bimestres, alunos_3_bimestre);


        System.out.println("");
        System.out.println("");
        System.out.println("---------------------- ANÁLISE COMPLETA FINAL -----------------------");
        System.out.println("");
        System.out.println("Turmas : " + Stringum.EXIBIR_EM_LISTA_TIPADA(DKGFeatures.filtrar_unicos(turmas_analisadas, "Nome")));
        System.out.println("");


        for (DKGObjeto turma : turmas_analisadas) {
            System.out.println("--------------------- " + turma.identifique("Nome").getValor() + " ------------------------");

            for (DKGObjeto bimestre : turma.getObjetos()) {

                String linha = bimestre.getNome() + " - ";

                linha += " " + fmt.espacar_antes(bimestre.identifique("Alunos").getInteiro(), 3) + " alunos     ---   ";

                linha += " " + fmt.espacar_antes(bimestre.identifique("Atividades").getInteiro(), 3) + " atv";
                linha += " " + fmt.espacar_antes(bimestre.identifique("Aprovados").getInteiro(), 3) + " ap";
                linha += " " + fmt.espacar_antes(bimestre.identifique("Reprovados").getInteiro(), 3) + " rep";

                linha += " " + fmt.espacar_antes(bimestre.identifique("AP_DATA").getValor(), 12) + " ";
                linha += " " + fmt.espacar_antes(bimestre.identifique("AP_QUANTIDADE").getValor(), 3) + " ap";
                linha += " " + fmt.espacar_antes(bimestre.identifique("AP_TEMPO").getValor(), 5) + " dias";

                linha += " " + fmt.espacar_antes(bimestre.identifique("REPROVADOS_MEDIA").getValor(), 5) + " ";
                linha += " " + fmt.espacar_antes(bimestre.identifique("TURMA_MEDIA").getValor(), 5) + " ";
                linha += " " + fmt.espacar_antes(bimestre.identifique("APROVADOS_MEDIA").getValor(), 5) + " ";

                linha += " " + fmt.espacar_antes(bimestre.identifique("ESTUDO_EFETIVO").getValor(), 5) + " dias";
                linha += " " + fmt.espacar_antes(bimestre.identifique("ESTUDO_QUALITATIVO").getValor(), 5) + " dias";

                System.out.println(linha);


            }

            Lista<DKGObjeto> alunos_da_turma = DKGFeatures.filtrar(alunos_3_bimestre, "Turma", turma.identifique("Nome").getValor());

            int aprovados = 0;

            for (DKGObjeto aluno : alunos_da_turma) {

                double nota_somatorio = 0.0;
                for (DKGObjeto aluno_geral : DKGFeatures.filtrar(alunos_todos_bimestres, "ID", aluno.identifique("ID").getInteiro())) {
                    nota_somatorio += aluno_geral.identifique("Nota").getDouble();
                }

                if (nota_somatorio >= 20) {
                    aprovados += 1;
                }

            }

            System.out.println("Aprovados Geral : " + aprovados + " de " + alunos_da_turma.getQuantidade());
            System.out.println("");

            AvaliadorDiagnosticoGG.finalizar(turma);

        }

        DKGFeatures.EXIBIR_TABELA(alunos_3_bimestre);

        ANALISTICS_V2(arquivo_b3);

        fmt.print("P(306) = {}", GET_ITEM(arquivo_b3, 59));

    }

    public static String GET_ITEM(String eArquivo, int indice) {

        final long TAMANHO_ITEM = (500 * 1024);

        Arquivador arquivar = new Arquivador(eArquivo);
        arquivar.setPonteiro(2 + (indice * TAMANHO_ITEM));


        int tamanho = arquivar.get_u32();

        byte[] bytes = new byte[tamanho];
        int i = 0;
        int o = bytes.length;

        while (i < o) {
            bytes[i] = arquivar.get();
            i += 1;
        }

        arquivar.encerrar();

        return new String(bytes, StandardCharsets.UTF_8);
    }


    public static void ANALISTICS(String arquivo) {


        fmt.print("");
        fmt.print("-------------------- ANALISADOR ANALISTICO CACHE ------------------------");
        fmt.print("");

        boolean tem_primeiro = false;
        long menor = 0;
        long maior = 0;
        String s_menor = "";
        String s_maior = "";


        Arquivador arquivar = new Arquivador(arquivo);
        arquivar.setPonteiro(0);

        boolean comecar = false;

        long ap1 = 0;
        long p1 = 0;
        long p2 = 0;
        int qq = 0;


        long pos = 0;
        while (arquivar.getPonteiro() < arquivar.getLength()) {
            int v = Inteiro.byteToInt(arquivar.get());
            if (v != 0) {
                // System.out.println("Pos = " + pos + " -- " + v);
                if (!comecar) {
                    comecar = true;
                    ap1 = p1;
                    p1 = pos;
                }
            } else {
                if (comecar) {
                    comecar = false;
                    p2 = pos;
                    fmt.print("\t ++ {}", "Objeto " + fmt.espacar_antes(qq, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p1 - ap1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(p2 - p1), 20) + " ]");

                    long tamanho_bloco = p2 - p1;

                    if (!tem_primeiro) {
                        tem_primeiro = true;
                        menor = tamanho_bloco;
                        maior = tamanho_bloco;

                        s_menor = "Objeto " + fmt.espacar_antes(qq, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p1 - ap1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(p2 - p1), 20) + " ]";
                        s_maior = "Objeto " + fmt.espacar_antes(qq, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p1 - ap1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(p2 - p1), 20) + " ]";

                    } else {
                        if (tamanho_bloco < menor) {
                            menor = tamanho_bloco;
                            s_menor = "Objeto " + fmt.espacar_antes(qq, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p1 - ap1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(p2 - p1), 20) + " ]";
                        }
                        if (tamanho_bloco > maior) {
                            maior = tamanho_bloco;
                            s_maior = "Objeto " + fmt.espacar_antes(qq, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p1 - ap1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(p2 - p1), 20) + " ]";
                        }
                    }


                    qq += 1;
                }
            }
            pos += 1;
        }

        arquivar.encerrar();

        if (tem_primeiro) {
            fmt.print("");
            fmt.print("\t ++ Menor : {}", s_menor);
            fmt.print("\t ++ Maior : {}", s_maior);
        }

    }


    public static void ANALISTICS_V2(String arquivo) {

        final int TAMANHO_ITEM = (500 * 1024);

        Arquivador arquivar = new Arquivador(arquivo);
        long tama = arquivar.getLength() - 2;
        arquivar.encerrar();

        long quantidade_de_blocos = tama / TAMANHO_ITEM;


        fmt.print("");
        fmt.print("-------------------- ANALISADOR ANALISTICO CACHE ------------------------");
        fmt.print("");

        boolean tem_primeiro = false;
        long menor = 0;
        long maior = 0;
        String s_menor = "";
        String s_maior = "";

        String s_maior2 = "";
        String s_maior3 = "";
        String s_maior4 = "";

        long bloco_id = 0;


        while (bloco_id < quantidade_de_blocos) {

            Arquivador arquivar2 = new Arquivador(arquivo);
            arquivar2.setPonteiro(2 + (bloco_id * TAMANHO_ITEM));

            long p1 = arquivar2.getPonteiro();
            long p2 = arquivar2.getPonteiro() + TAMANHO_ITEM;

            int tamanho_bloco = arquivar2.get_u32();
            arquivar2.encerrar();


            String porcentagem = fmt.f2Porcentagem(tamanho_bloco, TAMANHO_ITEM);

            String item_tipo = "";

            if(tamanho_bloco>0){
                String s_item = GET_ITEM(arquivo,(int)bloco_id);

                if( s_item.startsWith("!")){
                    s_item=Strings.GET_ATE(s_item," ");
                    item_tipo=s_item;
                }else{
                    item_tipo="<<FORMATO_DESCONHECIDO>>";
                }
            }



            fmt.print("\t ++ {}", "Objeto " + fmt.espacar_antes(bloco_id, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p2 - p1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(tamanho_bloco), 20) + " ( " + fmt.espacar_antes(porcentagem,6) + " ) "+ " ] -->> "+item_tipo);


            if (!tem_primeiro) {
                tem_primeiro = true;
                menor = tamanho_bloco;
                maior = tamanho_bloco;

                s_menor = "Objeto " + fmt.espacar_antes(bloco_id, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p2 - p1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(tamanho_bloco), 20) + " ( " + fmt.espacar_antes(porcentagem,6) + " ) "+ " ]";
                s_maior = "Objeto " + fmt.espacar_antes(bloco_id, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p2 - p1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(tamanho_bloco), 20) + " ( " + fmt.espacar_antes(porcentagem,6) + " ) "+ " ]";

            } else {
                if (tamanho_bloco < menor) {
                    menor = tamanho_bloco;
                    s_menor = "Objeto " + fmt.espacar_antes(bloco_id, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p2 - p1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(tamanho_bloco), 20)+ " ( " + fmt.espacar_antes(porcentagem,6) + " ) "+ " ]";
                }
                if (tamanho_bloco > maior) {
                    maior = tamanho_bloco;

                    s_maior4=s_maior3;
                    s_maior3=s_maior2;
                    s_maior2=s_maior;

                    s_maior = "Objeto " + fmt.espacar_antes(bloco_id, 5) + " -->> [ " + fmt.espacar_antes(p1, 10) + "  " + fmt.espacar_antes(p2, 10) + "    " + fmt.espacar_antes(p2 - p1, 10) + "    -    " + fmt.espacar_antes(fmt.formatar_tamanho_precisao_dupla(tamanho_bloco), 20) + " ( " + fmt.espacar_antes(porcentagem,6) + " ) "+ " ]";
                }
            }

            bloco_id += 1;
        }


        if (tem_primeiro) {
            fmt.print("");
            fmt.print("\t ++ Menor   : {}", s_menor);
            fmt.print("\t ++ Maior 4 : {}", s_maior4);
            fmt.print("\t ++ Maior 3 : {}", s_maior3);
            fmt.print("\t ++ Maior 2 : {}", s_maior2);
            fmt.print("\t ++ Maior   : {}", s_maior);
            fmt.print("");
        }

        fmt.print("----------------------------------------------------------------------------------");

    }


    public static void BIMESTRE(String arquivo, String bimestre_nome, Lista<DKGObjeto> turmas_analisadas, Lista<DKGObjeto> alunos_todos_bimestres, Lista<DKGObjeto> alunos_desse_bimestre) {

        boolean DEBUG_ALUNOS = false;


        DKG documento_cabecalho = DKG.PARSER(GET_ITEM(arquivo, 0));

        fmt.print();
        fmt.print("------------------------- " + documento_cabecalho.unicoObjeto("Cache").unicoObjeto("Atual").identifique("PID").getValor() + "------------------------- ");
        fmt.print();

        //  fmt.print(documento_cabecalho.toDocumento());

        Data BIMESTRE_INICIO = Data.toData(documento_cabecalho.unicoObjeto("Cache").unicoObjeto("Atual").identifique("DI").getValor());
        Data BIMESTRE_FIM = Data.toData(documento_cabecalho.unicoObjeto("Cache").unicoObjeto("Atual").identifique("DO").getValor());

        Lista<Data> BIMESTRE_DATAS = Calendario.listar_datas_entre(BIMESTRE_INICIO, BIMESTRE_FIM);


        int alunos_sloter = documento_cabecalho.unicoObjeto("Cache").unicoObjeto("Atual").unicoObjeto("Sloters").identifique("Alunos").getInteiro(0);

        int versao = documento_cabecalho.unicoObjeto("Cache").unicoObjeto("Atual").identifique("Versao").getInteiro(0);
        int subversao = documento_cabecalho.unicoObjeto("Cache").unicoObjeto("Atual").identifique("Subversao").getInteiro(0);
        long tamanho = documento_cabecalho.unicoObjeto("Cache").unicoObjeto("Atual").identifique("Tamanho").getLong(0);

        fmt.print("ARQUIVO             = " + Strings.GET_REVERSO_ATE(arquivo, "/"));
        fmt.print("TAMANHO             = " + fmt.formatar_tamanho(tamanho));
        fmt.print("VERSÃO              = " + versao + "." + subversao);
        fmt.print("BIMESTRE INICIO     = " + BIMESTRE_INICIO.getTempoLegivel());
        fmt.print("BIMESTRE FIM        = " + BIMESTRE_FIM.getTempoLegivel());
        fmt.print("BIMESTRE DURACAO    = " + BIMESTRE_DATAS.getQuantidade());
        // fmt.print("SLOTER ALUNOS       = " + alunos_sloter);

        DKG documento_alunos = DKG.PARSER(GET_ITEM(arquivo, 3));
        Lista<String> turmas = DKGFeatures.filtrar_unicos(documento_alunos.unicoObjeto("Alunos").getObjetos(), "Turma");

        Ordenador.ordenar_lista_crescente(turmas, Ordenador.ORDENAR_STRING_NAO_SENSITIVA());

        fmt.print("TURMAS              = " + Stringum.EXIBIR_EM_LISTA_TIPADA(turmas));
        fmt.print();

        //fmt.print(documento_alunos.toDocumento());

        Lista<DKGObjeto> alunos = new Lista<>();


        for (DKGObjeto aluno : DKG.PARSER(GET_ITEM(arquivo, 3)).unicoObjeto("Alunos").getObjetos()) {
            DKG aluno_objeto = DKG.PARSER(GET_ITEM(arquivo, alunos_sloter + aluno.identifique("ID").getInteiro(0)));

            if (arquivo.contains("CONTINUA")) {
                aluno_objeto.unicoObjeto("Aluno").identifique("Nota").setValor(aluno_objeto.unicoObjeto("Aluno").identifique("NotaComRecuperacao").getValor());

                aluno_objeto.unicoObjeto("Aluno").unicoObjeto("Momentos").setNome("Atividades");

                for (DKGObjeto atv : aluno_objeto.unicoObjeto("Aluno").unicoObjeto("Atividades").getObjetos()) {
                    if (atv.identifique("Tipo").isValor("CONTINUA")) {
                        //atv.identifique("Data").setValor(atv.identifique("Realizada").getValor());
                    }
                }
            }

            alunos.adicionar(aluno_objeto.unicoObjeto("Aluno"));

            alunos_todos_bimestres.adicionar(aluno_objeto.unicoObjeto("Aluno"));
            alunos_desse_bimestre.adicionar(aluno_objeto.unicoObjeto("Aluno"));


        }

        for (String turma : turmas) {

            DKGObjeto turma_analisando_geral = DKGFeatures.objeto_unico(turmas_analisadas, "Turma", "Nome", turma);

            DKGObjeto turma_analisando = turma_analisando_geral.unicoObjeto(bimestre_nome);

            fmt.print("---------------------- " + turma + " -----------------------");
            Lista<DKGObjeto> alunos_da_turma = DKGFeatures.filtrar(alunos, "Turma", turma);
            fmt.print("Alunos            : " + alunos_da_turma.getQuantidade());
            fmt.print("Atividades        : " + DKGFeatures.contagem_inteiro_somar(alunos_da_turma, "Atividades"));
            fmt.print("Aprovados         : " + DKGFeatures.contagem_double_maior_igual(alunos_da_turma, "Nota", 5));
            fmt.print("Reprovados        : " + DKGFeatures.contagem_double_menor(alunos_da_turma, "Nota", 5));


            turma_analisando.identifique("Alunos", alunos_da_turma.getQuantidade());
            turma_analisando.identifique("Atividades", DKGFeatures.contagem_inteiro_somar(alunos_da_turma, "Atividades"));
            turma_analisando.identifique("Aprovados", DKGFeatures.contagem_double_maior_igual(alunos_da_turma, "Nota", 5));
            turma_analisando.identifique("Reprovados", DKGFeatures.contagem_double_menor(alunos_da_turma, "Nota", 5));

            turma_analisando.identifique("BIMESTRE_DIAS", BIMESTRE_DATAS.getQuantidade());

            int atividades_da_turma = DKGFeatures.contagem_inteiro_somar(alunos_da_turma, "Atividades");

            for (DKGObjeto aluno : alunos_da_turma) {
                //   System.out.println("\t -- " + fmt.espacar_depois(aluno.identifique("Nome").getValor(), 50) + " :: " + aluno.identifique("Nota").getValor() + " -->> " + DKGFeatures.exibir_em_lista(aluno.unicoObjeto("Tempos").getObjetos(), "Nota"));
            }

            Opcional<String> existe_aprovado = OBTER_DATA_PRIMEIRO_APROVADO(alunos_da_turma);

            if (existe_aprovado.isOK()) {


                int tempo_diff = Calendario.contar_ate(BIMESTRE_DATAS, Data.toData(existe_aprovado.get()));

                Lista<DKGObjeto> aprovados_nessa_data = OBTER_APROVADOS_NESSA_DATA(alunos_da_turma, existe_aprovado.get());
                fmt.print("Primeiro AP       : " + Data.toData(existe_aprovado.get()).getTempoLegivel() + "   +" + aprovados_nessa_data.getQuantidade() + " aprovados        - " + tempo_diff + " dias");


                turma_analisando.identifique("AP_DATA", existe_aprovado.get());
                turma_analisando.identifique("AP_QUANTIDADE", aprovados_nessa_data.getQuantidade());
                turma_analisando.identifique("AP_TEMPO", tempo_diff);


            } else {
                fmt.print("Primeiro AP       : " + "---");
            }

            Opcional<String> existe_ultimo = OBTER_DATA_ULTIMO_APROVADO(alunos_da_turma);

            if (existe_ultimo.isOK()) {


                int tempo_diff = Calendario.contar_ate(BIMESTRE_DATAS, Data.toData(existe_ultimo.get()));

                Lista<DKGObjeto> aprovados_nessa_data = OBTER_APROVADOS_NESSA_DATA(alunos_da_turma, existe_ultimo.get());
                fmt.print("Ultimo AP         : " + Data.toData(existe_ultimo.get()).getTempoLegivel() + "   +" + aprovados_nessa_data.getQuantidade() + " aprovados        - " + tempo_diff + " dias");


            } else {
                fmt.print("Ultimo AP         : " + "---");
            }


            fmt.print("Turma Média       : " + fmt.doubleNumC2(DKGFeatures.somar_double(alunos_da_turma, "Nota") / alunos_da_turma.getQuantidade()));


            Lista<DKGObjeto> alunos_da_turma_aprovados = DKGFeatures.filtrar_double_maior_igual(alunos_da_turma, "Nota", 5);
            if (alunos_da_turma_aprovados.getQuantidade() > 0) {
                fmt.print("Aprovados Média   : " + fmt.doubleNumC2(DKGFeatures.somar_double(alunos_da_turma_aprovados, "Nota") / alunos_da_turma_aprovados.getQuantidade()));
            }

            Lista<DKGObjeto> alunos_da_turma_reprovados = DKGFeatures.filtrar_double_menor(alunos_da_turma, "Nota", 5);
            if (alunos_da_turma_reprovados.getQuantidade() > 0) {
                fmt.print("Reprovados Média  : " + fmt.doubleNumC2(DKGFeatures.somar_double(alunos_da_turma_reprovados, "Nota") / alunos_da_turma_reprovados.getQuantidade()));
            }

            turma_analisando.identifique("TURMA_MEDIA", fmt.doubleNumC2(DKGFeatures.somar_double(alunos_da_turma, "Nota") / alunos_da_turma.getQuantidade()));
            turma_analisando.identifique("APROVADOS_MEDIA", fmt.doubleNumC2(DKGFeatures.somar_double(alunos_da_turma_aprovados, "Nota") / alunos_da_turma_aprovados.getQuantidade()));
            turma_analisando.identifique("REPROVADOS_MEDIA", fmt.doubleNumC2(DKGFeatures.somar_double(alunos_da_turma_reprovados, "Nota") / alunos_da_turma_reprovados.getQuantidade()));


            fmt.print("Top 3 Melhores    : " + Stringum.EXIBIR_EM_LISTA_TIPADA(DKGFeatures.filtrar_double_3_maiores_unicos(alunos_da_turma, "Nota")));
            fmt.print("Top 3 Piores      : " + Stringum.EXIBIR_EM_LISTA_TIPADA(DKGFeatures.filtrar_double_3_menores_unicos(alunos_da_turma, "Nota")));


            int c_atividades = 0;
            int dias_estudando = 0;


            Lista<DKGObjeto> participacao = new Lista<>();
            Lista<Data> datas_extra = new Lista<Data>();

            for (Data data : BIMESTRE_DATAS) {

                int atividades_da_data = 0;
                int alunos_na_data = 0;

                for (DKGObjeto aluno : alunos_da_turma) {
                    int aluno_atividades = DKGFeatures.contagem_data_igual(aluno.unicoObjeto("Atividades").getObjetos(), "Data", data);
                    Lista<String> datas_do_aluno = DKGFeatures.filtrar_unicos(aluno.unicoObjeto("Atividades").getObjetos(), "Data");
                    if (aluno_atividades > 0) {
                        alunos_na_data += 1;
                    }
                    atividades_da_data += aluno_atividades;

                    for (Data data_do_aluno : Calendario.toDatas(datas_do_aluno)) {
                        if (!Calendario.data_contem(BIMESTRE_DATAS, data_do_aluno)) {
                            Calendario.adicionar_unico(datas_extra, data_do_aluno);
                        }
                    }
                }

                if (atividades_da_data > 0) {
                    fmt.print("\t -->> Data : " + data.getTempoLegivel() + "  " + fmt.espacar_antes(alunos_na_data, 3) + " alunos  " + fmt.espacar_antes(atividades_da_data, 3) + " atv");
                    participacao.adicionar(DKGUtils.criar("Participacao", "Data", data.getTempo(), "Alunos", "" + alunos_na_data));
                    dias_estudando += 1;
                }

                c_atividades += atividades_da_data;
            }

            fmt.print("Atividades no bimestre         : " + c_atividades);
            fmt.print("Atividades fora do bimestre    : " + (atividades_da_turma - c_atividades));
            fmt.print("Dias de efetivo estudo         : " + dias_estudando);
            fmt.print("Dias extras de efetivo estudo  : " + datas_extra.getQuantidade());


            for (Data data : datas_extra) {

                int atividades_da_data = 0;
                int alunos_na_data = 0;

                for (DKGObjeto aluno : alunos_da_turma) {
                    int aluno_atividades = DKGFeatures.contagem_data_igual(aluno.unicoObjeto("Atividades").getObjetos(), "Data", data);
                    Lista<String> datas_do_aluno = DKGFeatures.filtrar_unicos(aluno.unicoObjeto("Atividades").getObjetos(), "Data");
                    if (aluno_atividades > 0) {
                        alunos_na_data += 1;
                    }
                    atividades_da_data += aluno_atividades;

                }

                if (atividades_da_data > 0) {
                    fmt.print("\t -->> Data : " + data.getTempoLegivel() + "  " + fmt.espacar_antes(alunos_na_data, 3) + " alunos  " + fmt.espacar_antes(atividades_da_data, 3) + " atv");
                    participacao.adicionar(DKGUtils.criar("Participacao", "Data", data.getTempo(), "Alunos", "" + alunos_na_data));
                    dias_estudando += 1;
                }

            }

            fmt.print("Dias de baixa participação     : " + DKGFeatures.contagem_inteiro_menor(participacao, "Alunos", alunos_da_turma.getQuantidade() / 2));
            fmt.print("Dias de alta participação      : " + DKGFeatures.contagem_inteiro_maior_igual(participacao, "Alunos", alunos_da_turma.getQuantidade() / 2));


            turma_analisando.identifique("ESTUDO_EFETIVO", participacao.getQuantidade());

            turma_analisando.identifique("ESTUDO_QUALITATIVO", DKGFeatures.contagem_inteiro_menor(participacao, "Alunos", alunos_da_turma.getQuantidade() / 2));

        }


        if (DEBUG_ALUNOS) {

            for (DKGObjeto aluno : alunos) {
                fmt.print("---------------------- " + aluno.identifique("ID").getInteiro(0) + " -----------------------");
                fmt.print("Nome         : " + aluno.identifique("Nome").getValor());
                fmt.print("Turma        : " + aluno.identifique("Turma").getValor());

                fmt.print("Visibilidade : " + aluno.identifique("Visibilidade").getValor());
                fmt.print("Tamanho      : " + fmt.formatar_tamanho(aluno.toDocumento().getBytes(StandardCharsets.UTF_8).length));

                if (aluno.identifique("Visibilidade").getValor().contentEquals("SIM")) {
                    fmt.print(aluno.toDocumento());
                }
            }

        }


    }


    public static Opcional<String> OBTER_DATA_PRIMEIRO_APROVADO(Lista<DKGObjeto> alunos_da_turma) {

        boolean aprovado_existe = false;
        Data aprovado_data = null;


        for (DKGObjeto aluno : alunos_da_turma) {
            for (DKGObjeto aluno_tempo : aluno.unicoObjeto("Tempos").getObjetos()) {
                String tempo_data = aluno_tempo.identifique("Data").getValor();
                double tempo_nota = aluno_tempo.identifique("Nota").getDouble();
                if (tempo_nota >= 5.0) {

                    if (aprovado_existe) {
                        Data data_temporaria = Data.toData(tempo_data);
                        if (data_temporaria.isMenor(aprovado_data)) {
                            aprovado_data = data_temporaria;
                            //  System.out.println("Trocando Data :: " + tempo_data + " -- " + tempo_nota);
                        }

                    } else {
                        aprovado_existe = true;
                        aprovado_data = Data.toData(tempo_data);
                        //   System.out.println("Primeira Data :: " + tempo_data + " -- " + tempo_nota);
                    }

                }
            }


            if (!aprovado_existe) {
                double aluno_nota = aluno.identifique("Nota").getDouble();
                String aluno_ultima_atividade = aluno.identifique("Data").getValor();

                if (aluno_nota >= 5) {
                    aprovado_existe = true;
                    aprovado_data = Data.toData(aluno_ultima_atividade);
                    // System.out.println("Aluno Data :: " + aluno_ultima_atividade + " -- " + aluno_nota);
                }
            }

        }

        if (aprovado_existe) {
            return Opcional.OK(aprovado_data.getTempo());
        }

        return Opcional.CANCEL();
    }

    public static Opcional<String> OBTER_DATA_ULTIMO_APROVADO(Lista<DKGObjeto> alunos_da_turma) {

        boolean tem_ultima_data = false;
        Data ultimo_aprovado_data = null;


        for (DKGObjeto aluno : alunos_da_turma) {

            boolean aluno_foi_aprovado = false;
            Data aluno_aprovado_data = null;

            for (DKGObjeto aluno_tempo : aluno.unicoObjeto("Tempos").getObjetos()) {
                String tempo_data = aluno_tempo.identifique("Data").getValor();
                double tempo_nota = aluno_tempo.identifique("Nota").getDouble();
                if (tempo_nota >= 5.0) {

                    if (aluno_foi_aprovado) {
                        Data data_temporaria = Data.toData(tempo_data);
                        if (data_temporaria.isMenor(aluno_aprovado_data)) {
                            aluno_aprovado_data = data_temporaria;
                            //  System.out.println("Trocando Data :: " + tempo_data + " -- " + tempo_nota);
                        }

                    } else {
                        aluno_foi_aprovado = true;
                        aluno_aprovado_data = Data.toData(tempo_data);
                        //   System.out.println("Primeira Data :: " + tempo_data + " -- " + tempo_nota);
                    }

                }
            }


            if (!aluno_foi_aprovado) {
                double aluno_nota = aluno.identifique("Nota").getDouble();
                String aluno_ultima_atividade = aluno.identifique("Data").getValor();

                if (aluno_nota >= 5) {
                    aluno_foi_aprovado = true;
                    aluno_aprovado_data = Data.toData(aluno_ultima_atividade);
                    // System.out.println("Aluno Data :: " + aluno_ultima_atividade + " -- " + aluno_nota);
                }
            }

            if (tem_ultima_data) {
                if (aluno_foi_aprovado) {
                    if (aluno_aprovado_data.isMaior(ultimo_aprovado_data)) {
                        ultimo_aprovado_data = aluno_aprovado_data;
                    }
                }
            } else {

                if (aluno_foi_aprovado) {
                    tem_ultima_data = true;
                    ultimo_aprovado_data = aluno_aprovado_data;
                }

            }


        }

        if (tem_ultima_data) {
            return Opcional.OK(ultimo_aprovado_data.getTempo());
        }

        return Opcional.CANCEL();
    }

    public static Lista<DKGObjeto> OBTER_APROVADOS_NESSA_DATA(Lista<DKGObjeto> alunos_da_turma, String data_procurada) {

        Lista<DKGObjeto> ret = new Lista<DKGObjeto>();


        for (DKGObjeto aluno : alunos_da_turma) {

            boolean escolhido = false;
            boolean aprovado_antes = false;

            for (DKGObjeto aluno_tempo : aluno.unicoObjeto("Tempos").getObjetos()) {
                String tempo_data = aluno_tempo.identifique("Data").getValor();
                double tempo_nota = aluno_tempo.identifique("Nota").getDouble();
                if (tempo_nota >= 5.0) {
                    if (!aprovado_antes) {
                        if (Data.toData(tempo_data).isIgual(Data.toData(data_procurada))) {
                            escolhido = true;
                            // System.out.println("Escolhendo "+aluno.identifique("Nome").getValor()+ " em "+tempo_data);
                            break;
                        }
                    }
                    aprovado_antes = true;
                    break;
                }
            }


            if (!escolhido) {
                double aluno_nota = aluno.identifique("Nota").getDouble();
                String aluno_ultima_atividade = aluno.identifique("Data").getValor();

                if (aluno_nota >= 5.0 && !aprovado_antes) {
                    if (Data.toData(aluno_ultima_atividade).isIgual(Data.toData(data_procurada))) {
                        escolhido = true;
                    }
                }
            }

            if (escolhido) {
                ret.adicionar(aluno);
                // System.out.println("AP -- " + aluno.identifique("Nome").getValor() + " ----- " + aluno.identifique("Nota").getDouble());
            }

        }


        return ret;
    }

}
