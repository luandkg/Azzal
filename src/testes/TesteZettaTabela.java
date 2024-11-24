package testes;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Aleatorio;
import libs.luan.Lista;
import libs.luan.Vetor;
import libs.luan.fmt;
import libs.zettaquorum.*;
import servicos.MundoReal;

public class TesteZettaTabela {

    public static void init_tabelas() {

        fmt.print("----------------- ZETA TABELAS :: INICIANDO ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta_v2.az";

        ZettaTabelas zeta = new ZettaTabelas(arquivo_zeta);

        ZettaTabela pessoas = zeta.getTabelaSempre("Pessoas");

        if (!pessoas.temEsquema()) {

            pessoas.criar_chave_primaria("PessoaID", 10, 2);

            pessoas.criar_coluna("Nome", AZTabelaColunaTipo.TEXTO);
            pessoas.criar_coluna("Idade", AZTabelaColunaTipo.INTEIRO);
            pessoas.criar_coluna("Altura", AZTabelaColunaTipo.REAL);
            pessoas.criar_coluna("Documento", AZTabelaColunaTipo.TEXTO);
            pessoas.criar_coluna("Autenticado", AZTabelaColunaTipo.LOGICO);
            pessoas.criar_coluna("Status", AZTabelaColunaTipo.TEXTO);

            pessoas.criar_coluna_nao_obrigatoria("DDC", AZTabelaColunaTipo.TEXTO);
            pessoas.criar_coluna_nao_obrigatoria("DDM", AZTabelaColunaTipo.TEXTO);

            pessoas.criar_acao_inserivel("AI_DDC", "DDC", AZTabelaColunaTipo.TEXTO, AZTabelaAutoInserivel.TRON);
            pessoas.criar_acao_inserivel("AI_DDM", "DDM", AZTabelaColunaTipo.TEXTO, AZTabelaAutoInserivel.TRON);

            pessoas.criar_acao_atualizavel("AU_DDM", "DDM", AZTabelaColunaTipo.TEXTO, AZTabelaAutoAtualizavel.TRON);

            pessoas.criar_verificador("V_Nome::QuantidadeMinimaDeLetras", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMaior", 5));
            pessoas.criar_verificador("V_Nome::QuantidadeMaximaDeLetras", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMenor", 100));
            pessoas.criar_verificador("V_Nome::NumerosProibidos", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::NaoConter", "NUMEROS"));
            pessoas.criar_verificador("V_Nome::Formato", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Texto::Formato", "FRASE"));
            pessoas.criar_verificador("V_Nome::Unico", "Nome", ZettaTabelas.CRIAR_VERIFICADOR("Valor::Tipo", "UNICO"));

            pessoas.criar_verificador("V_Idade", "Idade", ZettaTabelas.CRIAR_VERIFICADOR("Inteiro::MaiorIgual", 0));
            pessoas.criar_verificador("V_AlturaMinimo", "Altura", ZettaTabelas.CRIAR_VERIFICADOR("Real::MaiorIgual", 0));
            pessoas.criar_verificador("V_AlturaMaximo", "Altura", ZettaTabelas.CRIAR_VERIFICADOR("Real::MenorIgual", 3));
            pessoas.criar_verificador("V_Status", "Status", ZettaTabelas.CRIAR_VERIFICADOR_CONTEM("Texto::Existe", Lista.CRIAR("DISPONIVEL", "BLOQUEADO", "OCUPADO", "TRANSMITINDO")));

            pessoas.criar_verificador("V_Documento::DocumentoValido_Tamanho", "Documento", ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoIgual", 18));
            pessoas.criar_verificador("V_Documento::DocumentoValido_Unico", "Documento", ZettaTabelas.CRIAR_VERIFICADOR("Valor::Tipo", "UNICO"));

        }

        //  tronarkum_valores.zerar();


        pessoas.exibir_esquema();
        pessoas.exibir_dados();

        zeta.dump();
        zeta.fechar();
    }


    public static void adicionar_dados() {

        fmt.print("----------------- ZETA TABELAS :: INICIANDO ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta_v2.az";

        ZettaTabelas zeta = new ZettaTabelas(arquivo_zeta);

        ZettaTabela pessoas = zeta.getTabelaSempre("Pessoas");

        pessoas.exibir_esquema();

        Lista<String> nome_repetir = ENTT.FILTRAR_UNICOS(pessoas.getItens(), "Nome");


        for (int i = 1; i <= 50; i++) {

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
                    }
                }
            }


            ZettaTabelas.ADICIONAR_OU_EXIBIR_ERRO(pessoas, novo);

        }


        pessoas.exibir_esquema();
        pessoas.exibir_dados();

        zeta.fechar();
    }

}
