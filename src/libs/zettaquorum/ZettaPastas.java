package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.ArmazemIndiceSumario;
import libs.fazendario.Fazendario;
import libs.fazendario.ItemAlocado;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.luan.fmt;
import libs.tronarko.Tronarko;

public class ZettaPastas {

    private Fazendario mFazendario;

    private ArmazemPrimario mSilos;

    private ArmazemPrimario mPastas;
    private ArmazemPrimario mSequencias;
    private ArmazemPrimario mIndices;

    public ZettaPastas(String eArquivo) {

        mFazendario = new Fazendario(eArquivo);

        mSilos = new ArmazemPrimario(mFazendario, "@PASTAS::SILOS");
        mPastas = new ArmazemPrimario(mFazendario, "@PASTAS::DADOS");
        mSequencias = new ArmazemPrimario(mFazendario, "@PASTAS::SEQUENCIAS");
        mIndices = new ArmazemPrimario(mFazendario, "@PASTAS::INDICES");


    }

    public void fechar() {
        mFazendario.fechar();
    }


    public ZettaPasta getPastaSempre(String colecao_nome) {

        Opcional<Entidade> op_colecao = mPastas.procurar_unico("Nome", colecao_nome);


        ZettaPasta colecao = null;
        Armazem armazem = null;

        boolean deve_zerar_sequencia = false;


        if (op_colecao.isOK()) {

            Entidade e_item = Opcional.SOME(op_colecao);

            armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("Ponteiro"));


        } else {

            boolean criada = false;


            for (ItemAlocado item_alocado : mPastas.getItensAlocados()) {
                Entidade e_item = ENTT.PARSER_ENTIDADE(item_alocado.lerTextoUTF8());
                if (e_item.is("Status", "DESTRUIDO")) {


                    armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(e_item.atLong("Ponteiro"));
                    armazem.zerar();

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

                long ptr_armazem_locatario = mFazendario.CRIAR_ARMAZEM_LOCATARIO();

                Entidade novo = new Entidade();
                novo.at("Status", "OK");
                novo.at("Nome", colecao_nome);
                novo.at("Ponteiro", ptr_armazem_locatario);
                novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
                novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

                mPastas.adicionar(novo);

                armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(ptr_armazem_locatario);
            }


        }

        Opcional<Par<ItemAlocado, Entidade>> op_sequencia = mSequencias.procurar_unico_atualizavel("Identificador", String.valueOf(armazem.getPonteiroCorrente()));
        Opcional<Par<ItemAlocado, Entidade>> op_indice = mIndices.procurar_unico_atualizavel("Identificador", String.valueOf(armazem.getPonteiroCorrente()));


        if (!op_sequencia.isOK()) {

            Entidade sequencia_novo = new Entidade();
            sequencia_novo.at("Identificador", String.valueOf(armazem.getPonteiroCorrente()));
            sequencia_novo.at("Corrente", 0);
            sequencia_novo.at("Passo", 1);
            sequencia_novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            sequencia_novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

            mSequencias.adicionar(sequencia_novo);

            op_sequencia = mSequencias.procurar_unico_atualizavel("Identificador", String.valueOf(armazem.getPonteiroCorrente()));
        }

        if (!op_indice.isOK()) {

            fmt.print(">> Criar indice sumario !");

            long ponteiro_indice = mFazendario.CRIAR_AREA_INDEXADA_SUMARIO(armazem.getPonteiroCorrente());

            ArmazemIndiceSumario indice_sumario = mFazendario.OBTER_INDICE_SUMARIO(armazem.getPonteiroCorrente(), ponteiro_indice);
            indice_sumario.zerar();


            Entidade indice_novo = new Entidade();
            indice_novo.at("Identificador", String.valueOf(armazem.getPonteiroCorrente()));
            indice_novo.at("Ponteiro", ponteiro_indice);
            indice_novo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            indice_novo.at("DDA", Tronarko.getTronAgora().getTextoZerado());

            mIndices.adicionar(indice_novo);

            op_indice = mIndices.procurar_unico_atualizavel("Identificador", String.valueOf(armazem.getPonteiroCorrente()));
        }


        if (deve_zerar_sequencia) {
            op_sequencia.get().getValor().at("Corrente", 0);
            op_sequencia.get().getChave().atualizarUTF8(ENTT.TO_DOCUMENTO(op_sequencia.get().getValor()));
        }

        ZettaSequenciador sequenciador = new ZettaSequenciador(op_sequencia.get().getChave(), op_sequencia.get().getValor());
        ArmazemIndiceSumario indice_sumario = mFazendario.OBTER_INDICE_SUMARIO(armazem.getPonteiroCorrente(), op_indice.get().getValor().atLong("Ponteiro"));

        colecao = new ZettaPasta(mFazendario.getArquivador(), mFazendario, mSilos, armazem, sequenciador, indice_sumario);

        return colecao;

    }


    public void dump() {


        Lista<Entidade> pastas = mPastas.getItens();

        for (Entidade colecao : pastas) {

            Armazem armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(colecao.atLong("Ponteiro"));

            colecao.at("Identificador", armazem.getNome());
            colecao.at("Portao", armazem.getPortao());

            colecao.at("Portoes", armazem.getPortoesContagem());
            colecao.at("Andares", armazem.getAndaresContagem());
            colecao.at("Espacos", armazem.getEspacosContagem());

            colecao.at("Alocados", armazem.getItensAlocadosContagem());
            colecao.at("NaoAlocados", armazem.getItensNaoAlocadosContagem());

            colecao.at("Reciclaveis", armazem.getItensReciclaveisContagem());
            colecao.at("Utilizados", armazem.getItensUtilizadosContagem());


        }


        Lista<Entidade> indices = mIndices.getItens();

        for (Entidade indice : indices) {

            ArmazemIndiceSumario ai_indice = mFazendario.OBTER_INDICE_SUMARIO(indice.atLong("Identificador"), indice.atLong("Ponteiro"));

            indice.at("Sumarios", ai_indice.getSumariosContagem());
            indice.at("Paginas", ai_indice.getPaginasContagem());
            indice.at("Maximo", ai_indice.getMaximo());
            indice.at("Indices", ai_indice.getIndicesContagem());


        }

        ENTT.EXIBIR_TABELA_COM_NOME(mSequencias.getItens(), "ZETTA-PASTAS : @PASTAS::SEQUENCIAS");
        ENTT.EXIBIR_TABELA_COM_NOME(indices, "ZETTA-PASTAS : @PASTAS::INDICES");
        ENTT.EXIBIR_TABELA_COM_NOME(pastas, "ZETTA-PASTAS : @PASTAS::PASTAS");

    }


    public void dump_pastas() {

        Lista<Entidade> pastas = mPastas.getItens();

        for (Entidade pasta : pastas) {

            Armazem armazem = mFazendario.OBTER_ARMAZEM_LOCATORIO(pasta.atLong("Ponteiro"));

            long tamanho = 0;

            for(ItemAlocado e_item : armazem.getItensAlocados()){

                Entidade e = ENTT.PARSER_ENTIDADE(e_item.lerTextoUTF8());

                tamanho+=e.atLong("Tamanho");
            }

            pasta.at("Tamanho",tamanho);
            pasta.at("TamanhoFormatado",fmt.formatar_tamanho_precisao_dupla(tamanho));

        }

        ENTT.EXIBIR_TABELA_COM_NOME(pastas, "ZETTA-PASTAS : @PASTAS::PASTAS");

    }


}
