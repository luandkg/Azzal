package testes;

import libs.aqz.tabela.AQZTabelas;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.zettaquorum.*;
import servicos.MundoReal;

public class TesteZettaTabela {

    public static void init_tabelas() {

        fmt.print("----------------- ZETA TABELAS :: INICIANDO ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaTabelas zeta = new ZettaTabelas(arquivo_zeta);

        ZettaTabela pessoas = zeta.getTabelaSempre("Pessoas");

        // pessoas.zerar();

        if (!pessoas.temEsquema()) {

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(pessoas.criar_chave_primaria("PessoaID", 10, 2));

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(pessoas.criar_coluna("Nome", AZTabelaColunaTipo.TEXTO));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_coluna("Idade", AZTabelaColunaTipo.INTEIRO));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_coluna("Altura", AZTabelaColunaTipo.REAL));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_coluna("Documento", AZTabelaColunaTipo.TEXTO));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_coluna("Autenticado", AZTabelaColunaTipo.LOGICO));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_coluna("Status", AZTabelaColunaTipo.TEXTO));

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(pessoas.criar_coluna_nao_obrigatoria("DDC", AZTabelaColunaTipo.TEXTO));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_coluna_nao_obrigatoria("DDM", AZTabelaColunaTipo.TEXTO));

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_acao_inserivel("AI_DDC", "DDC", AZTabelaColunaTipo.TEXTO, AZTabelaAutoInserivel.TRON));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(  pessoas.criar_acao_inserivel("AI_DDM", "DDM", AZTabelaColunaTipo.TEXTO, AZTabelaAutoInserivel.TRON));

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(  pessoas.criar_acao_atualizavel("AU_DDM", "DDM", AZTabelaColunaTipo.TEXTO, AZTabelaAutoAtualizavel.TRON));

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(  pessoas.criar_verificador("V_Nome::QuantidadeMinimaDeLetras", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMaior", 5)));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(  pessoas.criar_verificador("V_Nome::QuantidadeMaximaDeLetras", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMenor", 100)));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(  pessoas.criar_verificador("V_Nome::NumerosProibidos", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::NaoConter", "NUMEROS")));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_verificador("V_Nome::Formato", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::Formato", "FRASE")));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(  pessoas.criar_verificador("V_Nome::Unico", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Valor::Tipo", "UNICO")));

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_verificador("V_Idade", "Idade", ZettaTabelas.CRIAR_VERIFICADOR("Inteiro::MaiorIgual", 0)));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_verificador("V_AlturaMinimo", "Altura", ZettaTabelas.CRIAR_VERIFICADOR("Real::MaiorIgual", 0)));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(  pessoas.criar_verificador("V_AlturaMaximo", "Altura", ZettaTabelas.CRIAR_VERIFICADOR("Real::MenorIgual", 3)));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER( pessoas.criar_verificador("V_Status", "Status", ZettaTabelas.CRIAR_VERIFICADOR_CONTEM("Texto::Existe", Lista.CRIAR("DISPONIVEL", "BLOQUEADO", "OCUPADO", "TRANSMITINDO"))));

            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(pessoas.criar_verificador("V_Documento::DocumentoValido_Tamanho", "Documento", ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoIgual", 18)));
            ZettaTabelas.EXIBIR_ERRO_SE_OCORRER(pessoas.criar_verificador("V_Documento::DocumentoValido_Unico", "Documento", ZettaTabelas.CRIAR_VERIFICADOR("Valor::Tipo", "UNICO")));

        }

        //  tronarkum_valores.zerar();


        pessoas.exibir_esquema();
        pessoas.exibir_dados();

        zeta.dump();
        zeta.fechar();
    }


    public static void adicionar_dados() {

        fmt.print("----------------- ZETA TABELAS :: INICIANDO ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaTabelas zeta = new ZettaTabelas(arquivo_zeta);

        ZettaTabela pessoas = zeta.getTabelaSempre("Pessoas");

        pessoas.exibir_esquema();

        Lista<String> nome_repetir = ENTT.FILTRAR_UNICOS(pessoas.getItens(), "Nome");
        Lista<String> documentos_repetir = ENTT.FILTRAR_UNICOS(pessoas.getItens(), "Documento");


        for (int i = 1; i <= 300; i++) {

            fmt.print("\t ++ Adicionando pessoa : {}", i);
            Entidade novo = new Entidade();
            novo.at("Nome", MundoReal.GET_NOME_PESSOA_COMPLETO());
            novo.at("Idade", Aleatorio.aleatorio_entre(0, 100));
            novo.at("Altura", Aleatorio.aleatorio_numero_real(1, 3));
            novo.at("Autenticado", Aleatorio.escolha_um(Vetor.CRIAR("SIM", "NAO", "TALVEZ")));
            novo.at("Status", Aleatorio.escolha_um(Lista.CRIAR("DISPONIVEL", "BLOQUEADO", "OCUPADO", "TRANSMITINDO", "V2")));
            novo.at("Documento", MundoReal.GET_DOCUMENTO_NUMERICO(14));


            if (Aleatorio.aleatorio_entre(0, 100) > 50) {
                novo.at("Idade", Aleatorio.escolha_um(Vetor.CRIAR("1.50", "130in", "um")));
            } else {
                if (Aleatorio.aleatorio_entre(0, 100) > 50) {

                    if (Aleatorio.aleatorio_entre(0, 100) > 50) {
                        novo.at("Nome", fmt.repetir("a", 112));
                    } else {
                        novo.at("Nome", "a");
                    }

                    if (Aleatorio.aleatorio_entre(0, 100) > 50) {
                        novo.at("Nome", Aleatorio.escolha_um(nome_repetir));

                        if (Aleatorio.aleatorio_entre(0, 100) > 50) {
                            novo.at("Documento", Aleatorio.escolha_um(documentos_repetir));
                        }

                    }
                }
            }


            ZettaTabelas.ADICIONAR_OU_EXIBIR_ERRO(pessoas, novo);

        }


        pessoas.exibir_esquema();
      //  pessoas.exibir_dados();

        fmt.print("Quantidade :: {}",pessoas.contagem());

        zeta.fechar();
    }


    public static void ver_dados() {


        fmt.print("----------------- ZETA TABELAS :: INICIANDO ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta_v2.az";

        ZettaTabelas zeta = new ZettaTabelas(arquivo_zeta);

        ZettaTabela pessoas = zeta.getTabelaSempre("Pessoas");

        pessoas.exibir_esquema();
       // pessoas.exibir_dados();


        Opcional<RefLinhaDaTabela> ref_pessoa_id14 = pessoas.procurar("PessoaID", 14);

        if (ref_pessoa_id14.isOK()) {

            Entidade e_atualizar = ref_pessoa_id14.get().getEntidade();
            e_atualizar.at_remover("@ID");
            e_atualizar.at_remover("@PTR");

            e_atualizar.at_remover("PessoaID");

            e_atualizar.at("Nome", MundoReal.GET_NOME_PESSOA_COMPLETO());
            e_atualizar.at("Idade", Aleatorio.aleatorio_entre(0, 100));
            e_atualizar.at("Altura", Aleatorio.aleatorio_numero_real(1, 3));
            e_atualizar.at("Autenticado", Aleatorio.escolha_um(Vetor.CRIAR("SIM", "NAO", "TALVEZ")));
            e_atualizar.at("Status", Aleatorio.escolha_um(Lista.CRIAR("DISPONIVEL", "BLOQUEADO", "OCUPADO", "TRANSMITINDO", "V2")));

            if (Aleatorio.aleatorio_entre(0, 100) > 50) {
                e_atualizar.at("Idade", Aleatorio.escolha_um(Vetor.CRIAR("1.50", "130in", "um")));
            } else {
                if (Aleatorio.aleatorio_entre(0, 100) > 50) {
                    if (Aleatorio.aleatorio_entre(0, 100) > 50) {
                        e_atualizar.at("Nome", fmt.repetir("a", 112));
                    } else {
                        e_atualizar.at("Nome", "a");
                    }
                }
            }

            ZettaTabelas.ATUALIZAR_OU_EXIBIR_ERRO(ref_pessoa_id14.get(), e_atualizar);
        }


        pessoas.exibir_dados();
      // pessoas.exibir_contar_por("Status");
        pessoas.exibir_contar_por("Autenticado");


        Lista<Entidade> idade_zonas = ENTT.ZONA_ANALISAR_EM_DISPERSAO_4ZONAS(pessoas.getItens(),"Idade");

        ENTT.CALCULAR_PORCENTAGEM(idade_zonas,"Quantidade","Porcentagem");
        ENTT.EXIBIR_TABELA_COM_TITULO(idade_zonas,"INTERVALOS DE IDADES");

    }
}
