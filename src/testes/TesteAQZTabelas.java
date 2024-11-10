package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.aqz.tabela.*;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import servicos.MundoReal;

public class TesteAQZTabelas {

    public static void init() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";

        //  AQZ.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco);
        // AQZ.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);


        AZTabelas tabelas = new AZTabelas(arquivo_banco);

        AQZTabela pessoas = tabelas.tabela_orgarnizar_e_obter("Pessoas");

        pessoas.zerar();

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

            pessoas.criar_acao_atualizavel("AU_DDM", "DDM", AZTabelaColunaTipo.TEXTO, AZTabelaAutoModificavel.TRON);

            pessoas.criar_verificador("V_Nome::QuantidadeMinimaDeLetras", "Nome", AQZTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMaior", 5));
            pessoas.criar_verificador("V_Nome::QuantidadeMaximaDeLetras", "Nome", AQZTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMenor", 100));
            pessoas.criar_verificador("V_Nome::NumerosProibidos", "Nome", AQZTabelas.CRIAR_VERIFICADOR("Texto::NaoConter", "NUMEROS"));
            pessoas.criar_verificador("V_Nome::Formato", "Nome", AQZTabelas.CRIAR_VERIFICADOR("Texto::Formato", "FRASE"));
            pessoas.criar_verificador("V_Nome::Unico", "Nome", AQZTabelas.CRIAR_VERIFICADOR("Valor::Tipo", "UNICO"));

            pessoas.criar_verificador("V_Idade", "Idade", AQZTabelas.CRIAR_VERIFICADOR("Inteiro::MaiorIgual", 0));
            pessoas.criar_verificador("V_AlturaMinimo", "Altura", AQZTabelas.CRIAR_VERIFICADOR("Real::MaiorIgual", 0));
            pessoas.criar_verificador("V_AlturaMaximo", "Altura", AQZTabelas.CRIAR_VERIFICADOR("Real::MenorIgual", 3));
            pessoas.criar_verificador("V_Status", "Status", AQZTabelas.CRIAR_VERIFICADOR_CONTEM("Texto::Existe", Lista.CRIAR("DISPONIVEL", "BLOQUEADO", "OCUPADO", "TRANSMITINDO")));

            pessoas.criar_verificador("V_Documento::DocumentoValido_Tamanho", "Documento", AQZTabelas.CRIAR_VERIFICADOR("Texto::TamanhoIgual", 18));
            pessoas.criar_verificador("V_Documento::DocumentoValido_Unico", "Documento",  AQZTabelas.CRIAR_VERIFICADOR("Valor::Tipo", "UNICO"));

        }


        pessoas.exibir_esquema();
        pessoas.exibir_dados();

        tabelas.fechar();

        //  AQZ.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco);
        //  AQZ.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);

        // AQZTabelas.EXIBIR_TABELAS_DADOS(arquivo_banco);
        //    AQZTabelas.EXIBIR_ESQUEMAS_DADOS(arquivo_banco);

    }

    public static void adicionar_dados() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";

        //  AQZ.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco);
        // AQZ.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);


        AZTabelas tabelas = new AZTabelas(arquivo_banco);

        AQZTabela pessoas = tabelas.tabela_orgarnizar_e_obter("Pessoas");

        // pessoas.limpar_dados();

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
                }
            }


            AQZTabelas.ADICIONAR_OU_EXIBIR_ERRO(pessoas, novo);

        }


        pessoas.exibir_esquema();
        pessoas.exibir_dados();

        Opcional<Entidade> op_pessoa_id14 = pessoas.procurar_um("PessoaID", 14);

        if (op_pessoa_id14.isOK()) {
            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(op_pessoa_id14.get()));

            Opcional<Entidade> buscando_diretamente = pessoas.obter_diretamente(op_pessoa_id14.get().atInt("@ID"));
            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(buscando_diretamente.get()));

        }

        Opcional<RefLinhaDaTabela> ref_pessoa_id14 = pessoas.procurar_ref("PessoaID", 14);

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

            AQZTabelas.ATUALIZAR_OU_EXIBIR_ERRO(ref_pessoa_id14.get(), e_atualizar);
        }


        Opcional<Entidade> v2_op_pessoa_id14 = pessoas.procurar_um("PessoaID", 14);
        if (v2_op_pessoa_id14.isOK()) {
            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(v2_op_pessoa_id14.get()));
        }

        tabelas.fechar();
    }
}
