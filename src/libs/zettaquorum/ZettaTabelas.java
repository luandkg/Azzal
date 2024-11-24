package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.ArmazemIndiceSumario;
import libs.fazendario.Fazendario;
import libs.fazendario.ItemAlocado;
import libs.luan.*;
import libs.tronarko.Tronarko;

public class ZettaTabelas {

    private Fazendario mFazendario;

    private ArmazemPrimario mSequencias;
    private ArmazemPrimario mIndices;
    private ArmazemPrimario mTabelas;


    public ZettaTabelas(String eArquivo) {

        mFazendario = new Fazendario(eArquivo);

        mSequencias = new ArmazemPrimario(mFazendario, "@TABELAS::SEQUENCIAS");
        mIndices = new ArmazemPrimario(mFazendario, "@TABELAS::INDICES");
        mTabelas = new ArmazemPrimario(mFazendario, "@TABELAS::TABELAS");


    }


    public void fechar(){
        mFazendario.fechar();
    }


    public void dump(){

        ENTT.EXIBIR_TABELA_COM_NOME(mSequencias.getItens(), "ZETTA-TABELAS : @TABELAS::SEQUENCIAS");
        ENTT.EXIBIR_TABELA_COM_NOME(mIndices.getItens(), "ZETTA-TABELAS : @TABELAS::INDICES");
        ENTT.EXIBIR_TABELA_COM_NOME(mTabelas.getItens(), "ZETTA-TABELAS : @TABELAS::TABELAS");

    }

    public ZettaTabela getTabelaSempre(String colecao_nome) {

        Opcional<Entidade> op_colecao = mTabelas.procurar_unico("Nome", colecao_nome);


        ZettaTabela colecao = null;

        Armazem armazem_esquemas = null;
        Armazem armazem_dados = null;

        boolean deve_zerar_sequencia = false;


        if (op_colecao.isOK()) {

            Entidade e_item = Opcional.SOME(op_colecao);

            armazem_esquemas = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("EsquemaPonteiro"));
            armazem_dados = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("TabelaPonteiro"));


        } else {

            boolean criada = false;


            for (ItemAlocado item_alocado : mTabelas.getItensAlocados()) {
                Entidade e_item = ENTT.PARSER_ENTIDADE(item_alocado.lerTextoUTF8());
                if (e_item.is("Status", "DESTRUIDO")) {


                    armazem_esquemas = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("EsquemaPonteiro"));
                    armazem_esquemas.zerar();

                    armazem_dados = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("TabelaPonteiro"));
                    armazem_dados.zerar();


                    e_item.at("Status", "OK");
                    e_item.at("Nome", colecao_nome);
                    e_item.at("DDC", Tronarko.getTronAgora().getTextoZerado());
                    e_item.at("DDA", Tronarko.getTronAgora().getTextoZerado());

                    item_alocado.atualizarUTF8(ENTT.TO_DOCUMENTO(e_item));

                    deve_zerar_sequencia = true;


                    criada = true;
                    break;
                }
            }


            if (!criada) {

                long ptr_armazem_esquema_locatario = mFazendario.CRIAR_ARMAZEM_LOCATARIO();
                long ptr_armazem_tabela_locatario = mFazendario.CRIAR_ARMAZEM_LOCATARIO();

                Entidade novo = new Entidade();
                novo.at("Status", "OK");
                novo.at("Nome", colecao_nome);
                novo.at("EsquemaPonteiro", ptr_armazem_esquema_locatario);
                novo.at("TabelaPonteiro", ptr_armazem_tabela_locatario);
                novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
                novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

                mTabelas.adicionar(novo);

                armazem_esquemas = mFazendario.OBTER_ARMAZEM_LOCATORIO(ptr_armazem_esquema_locatario);
                armazem_dados = mFazendario.OBTER_ARMAZEM_LOCATORIO(ptr_armazem_tabela_locatario);

            }


        }

        Opcional<Par<ItemAlocado, Entidade>> op_sequencia = mSequencias.procurar_unico_atualizavel("Identificador", String.valueOf(armazem_esquemas.getPonteiroCorrente()));
        Opcional<Par<ItemAlocado, Entidade>> op_indice = mIndices.procurar_unico_atualizavel("Identificador", String.valueOf(armazem_esquemas.getPonteiroCorrente()));


        if (!op_sequencia.isOK()) {

            Entidade sequencia_novo = new Entidade();
            sequencia_novo.at("Identificador", String.valueOf(armazem_esquemas.getPonteiroCorrente()));
            sequencia_novo.at("Corrente", 0);
            sequencia_novo.at("Passo", 1);
            sequencia_novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            sequencia_novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

            mSequencias.adicionar(sequencia_novo);

            op_sequencia = mSequencias.procurar_unico_atualizavel("Identificador", String.valueOf(armazem_esquemas.getPonteiroCorrente()));
        }

        if (!op_indice.isOK()) {

            fmt.print(">> Criar indice sumario !");

            long ponteiro_indice = mFazendario.CRIAR_AREA_INDEXADA_SUMARIO(armazem_esquemas.getPonteiroCorrente());

            ArmazemIndiceSumario indice_sumario = mFazendario.OBTER_INDICE_SUMARIO(armazem_esquemas.getPonteiroCorrente(), ponteiro_indice);
            indice_sumario.zerar();


            Entidade indice_novo = new Entidade();
            indice_novo.at("Identificador", String.valueOf(armazem_esquemas.getPonteiroCorrente()));
            indice_novo.at("Ponteiro", ponteiro_indice);
            indice_novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            indice_novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

            mIndices.adicionar(indice_novo);

            op_indice = mIndices.procurar_unico_atualizavel("Identificador", String.valueOf(armazem_esquemas.getPonteiroCorrente()));
        }


        if (deve_zerar_sequencia) {
            op_sequencia.get().getValor().at("Corrente", 0);
            op_sequencia.get().getChave().atualizarUTF8(ENTT.TO_DOCUMENTO(op_sequencia.get().getValor()));
        }

        ZettaSequenciador sequenciador = new ZettaSequenciador(op_sequencia.get().getChave(), op_sequencia.get().getValor());
        ArmazemIndiceSumario indice_sumario = mFazendario.OBTER_INDICE_SUMARIO(armazem_esquemas.getPonteiroCorrente(), op_indice.get().getValor().atLong("Ponteiro"));

        colecao = new ZettaTabela(colecao_nome,armazem_esquemas,armazem_dados, sequenciador, indice_sumario);

        return colecao;

    }


    public static Entidade CRIAR_VERIFICADOR(String eTipo, String eValor) {
        return ENTT.CRIAR("Tipo", eTipo, "Valor", eValor);
    }

    public static Entidade CRIAR_VERIFICADOR(String eTipo, int eValor) {
        return ENTT.CRIAR("Tipo", eTipo, "Valor", eValor);
    }

    public static Entidade CRIAR_VERIFICADOR_CONTEM(String eTipo, Lista<String> valores) {
        return ENTT.CRIAR("Tipo", eTipo, "Valor", Strings.LISTA_TO_TEXTO_TAB(valores));
    }



    public static void ADICIONAR_OU_EXIBIR_ERRO(ZettaTabela pessoas, Entidade novo) {
        Resultado<Boolean, String> resultado = pessoas.adicionar(novo);
        if (resultado.isErro()) {
            fmt.print(">> ERRO :: {}", resultado.getErro());
        }
    }


}
