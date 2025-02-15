package libs.zetta;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.luan.fmt;
import libs.tronarko.Tronarko;
import libs.zetta.fazendario.Armazem;
import libs.zetta.fazendario.ArmazemIndiceSumario;
import libs.zetta.fazendario.Fazendario;
import libs.zetta.fazendario.ItemAlocado;
import libs.zetta.persistencia.ArmazemPrimario;

public class ZettaMemCachedum {

    // CRIADO : 2025 02 09
    // AUTOR  : LUAN ALVES FREITAS

    private Fazendario mFazendario;

    private ArmazemPrimario mSequencias;
    private ArmazemPrimario mIndices;
    private ArmazemPrimario mColecoes;


    public ZettaMemCachedum(String eArquivo) {

        mFazendario = new Fazendario(eArquivo);

        mSequencias = new ArmazemPrimario(mFazendario, "@MEMCACHE::SEQUENCIAS");
        mIndices = new ArmazemPrimario(mFazendario, "@MEMCACHE::INDICES");
        mColecoes = new ArmazemPrimario(mFazendario, "@MEMCACHE::DADOS");


    }

    public void fechar() {
        mFazendario.fechar();
    }

    public ZettaMemCached getColecaoSempre(String colecao_nome) {

        Opcional<Entidade> op_colecao = mColecoes.procurar_unico("Nome", colecao_nome);


        ZettaMemCached colecao = null;
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

                mColecoes.adicionar(novo);

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

        colecao = new ZettaMemCached(colecao_nome,armazem, sequenciador, indice_sumario);

        return colecao;

    }

    public static String PARAMETRIZADO(String a1,String v1){
        return a1+"(" + v1 + ")";
    }

    public static String PARAMETRIZADO(String a1,String v1,String a2,String v2){
        return a1+"(" + v1 + ")->"+a2+"["+ v2+"]";
    }

    public static String PARAMETRIZADO(String a1,String v1,String a2,String v2,String a3,String v3){
        return a1+"(" + v1 + ")->"+a2+"["+ v2+"]::"+a3+"{"+v3+"}";
    }
}
