package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.ArmazemIndiceSumario;
import libs.fazendario.Fazendario;
import libs.fazendario.ItemAlocado;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.luan.fmt;
import libs.tronarko.Tronarko;

public class ZettaQuorum {

    private Fazendario mFazendario;

    private ArmazemInterno mSequencias;
    private ArmazemInterno mIndices;
    private ArmazemInterno mColecoes;


    public ZettaQuorum(String eArquivo) {

        mFazendario = new Fazendario(eArquivo);

        mSequencias = new ArmazemInterno(mFazendario, "@COLECOES::SEQUENCIAS");
        mIndices = new ArmazemInterno(mFazendario, "@COLECOES::INDICES");
        mColecoes = new ArmazemInterno(mFazendario, "@COLECOES::DADOS");


    }

    public void fechar() {
        mFazendario.fechar();
    }


    public void dump() {

        ENTT.EXIBIR_TABELA_COM_NOME(mSequencias.getItens(), "ZETTA-QUORUM : @COLECOES::SEQUENCIAS");
        ENTT.EXIBIR_TABELA_COM_NOME(mIndices.getItens(), "ZETTA-QUORUM : @COLECOES::INDICES");
        ENTT.EXIBIR_TABELA_COM_NOME(mColecoes.getItens(), "ZETTA-QUORUM : @COLECOES::DADOS");

    }


    public ZettaColecao getColecaoSempre(String colecao_nome) {

        Opcional<Entidade> op_colecao = mColecoes.procurar_unico("Nome", colecao_nome);


        ZettaColecao colecao = null;
        Armazem armazem = null;

        boolean deve_zerar_sequencia = false;


        if (op_colecao.isOK()) {

            Entidade e_item = Opcional.SOME(op_colecao);

            armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("Ponteiro"));


        } else {

            boolean criada = false;


            for (ItemAlocado item_alocado : mColecoes.getItensAlocados()) {
                Entidade e_item = ENTT.PARSER_ENTIDADE(item_alocado.lerTextoUTF8());
                if (e_item.is("Status", "DESTRUIDO")) {


                    armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("Ponteiro"));
                    armazem.zerar();

                    e_item.at("Status", "OK");
                    e_item.at("Nome", colecao_nome);

                    item_alocado.atualizarUTF8(ENTT.TO_DOCUMENTO(e_item));

                    deve_zerar_sequencia = true;


                    criada = true;
                    break;
                }
            }


            if (!criada) {

                long ptr_armazem_locatario = mFazendario.CRIAR_ARMAZEM_LOCATARIO();

                Entidade novo = new Entidade();
                novo.at("Status", "OK");
                novo.at("Nome", colecao_nome);
                novo.at("Ponteiro", ptr_armazem_locatario);

                mColecoes.adicionar(novo);

                armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(ptr_armazem_locatario);
            }


        }

        Opcional<Par<ItemAlocado, Entidade>> op_sequencia = mSequencias.procurar_unico_atualizavel("Nome", String.valueOf(armazem.getPonteiroCorrente()));
        Opcional<Par<ItemAlocado, Entidade>> op_indice = mIndices.procurar_unico_atualizavel("Nome", String.valueOf(armazem.getPonteiroCorrente()));


        if (!op_sequencia.isOK()) {

            Entidade sequencia_novo = new Entidade();
            sequencia_novo.at("Nome", String.valueOf(armazem.getPonteiroCorrente()));
            sequencia_novo.at("Corrente", 0);
            sequencia_novo.at("Passo", 1);
            sequencia_novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            sequencia_novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

            mSequencias.adicionar(sequencia_novo);

            op_sequencia = mSequencias.procurar_unico_atualizavel("Nome", String.valueOf(armazem.getPonteiroCorrente()));
        }

        if (!op_indice.isOK()) {

            fmt.print(">> Criar indice sumario !");

            long ponteiro_indice = mFazendario.CRIAR_AREA_INDEXADA_SUMARIO(armazem.getPonteiroCorrente());

            ArmazemIndiceSumario indice_sumario = mFazendario.OBTER_INDICE_SUMARIO(armazem.getPonteiroCorrente(),ponteiro_indice);
            indice_sumario.zerar();




            Entidade indice_novo = new Entidade();
            indice_novo.at("Nome", String.valueOf(armazem.getPonteiroCorrente()));
            indice_novo.at("Ponteiro", ponteiro_indice);
            indice_novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            indice_novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

            mIndices.adicionar(indice_novo);

            op_indice = mIndices.procurar_unico_atualizavel("Nome", String.valueOf(armazem.getPonteiroCorrente()));
        }


        if (deve_zerar_sequencia) {
            op_sequencia.get().getValor().at("Corrente", 0);
            op_sequencia.get().getChave().atualizarUTF8(ENTT.TO_DOCUMENTO(op_sequencia.get().getValor()));
        }

        ZettaSequenciador sequenciador = new ZettaSequenciador(op_sequencia.get().getChave(), op_sequencia.get().getValor());
        ArmazemIndiceSumario indice_sumario = mFazendario.OBTER_INDICE_SUMARIO(armazem.getPonteiroCorrente(),op_indice.get().getValor().atLong("Ponteiro"));

        colecao = new ZettaColecao(armazem, sequenciador, indice_sumario);

        return colecao;

    }


}
