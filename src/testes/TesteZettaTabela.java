package testes;

import libs.luan.Lista;
import libs.luan.fmt;
import libs.zettaquorum.*;

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



}
