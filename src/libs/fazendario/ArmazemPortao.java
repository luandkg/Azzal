package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.DeslizadorEstrutural;
import libs.luan.Lista;
import libs.luan.fmt;

public class ArmazemPortao {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private long mArmazemIndice;
    private long mSumarioPonteiro;

    public ArmazemPortao(Arquivador eArquivador, Fazendario eFazendario, long eArmazemIndice, long eSumarioPonteiro) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mArmazemIndice = eArmazemIndice;
        mSumarioPonteiro = eSumarioPonteiro;

        //      set_u64((byte) armazem.getIndice());     // Indice
        //      set_u8((byte) ARMAZEM_TIPO_ARMAZEM);    // Tipo para Armazem - Armazem Sumario
        //      set_u8((byte) NAO_TEM);                 // Tem outra página de Armazem Sumario
        //      set_u64((long) 0);                      // Ponteiro da proxima página Armazem Sumario
        //      set_u8((byte) NAO_TEM);


    }

    public long getPortoesContagem() {
        long contagem = 0;


        DeslizadorEstrutural<PortaoDeslizante> portoes = new DeslizadorEstrutural<>(new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, mSumarioPonteiro));


        while (portoes.temProximo()) {

            PortaoDeslizante sumario_local = portoes.get();

            contagem += 1;

            if (sumario_local.temOutroPortao()) {
                portoes.setProximo(new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, sumario_local.getOutroPortao()));
            } else {
                portoes.finalizar();
            }

        }


        return contagem;
    }

    public long getAndaresContagem() {
        long contagem = 0;


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem += sumario_local.getAndaresContagem();

            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

        }


        return contagem;
    }

    public long getItensAlocadosContagem() {
        long contagem = 0;


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            // fmt.print("Sumario :: {}",local_ponteiro_sumario);
            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem += sumario_local.getItensAlocadosContagem();

            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

        }


        return contagem;
    }

    public long getItensNaoAlocadosContagem() {
        long contagem = 0;


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            // fmt.print("Sumario :: {}",local_ponteiro_sumario);
            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem += sumario_local.getItensNaoAlocadosContagem();

            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

        }


        return contagem;
    }

    public long getItensUtilizadosContagem() {
        long contagem = 0;


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            // fmt.print("Sumario :: {}",local_ponteiro_sumario);
            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem += sumario_local.getItensUtilizadosContagem();

            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

        }


        return contagem;
    }

    public long getItensReciclaveisContagem() {
        long contagem = 0;


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            // fmt.print("Sumario :: {}",local_ponteiro_sumario);
            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem += sumario_local.getItensReciclaveisContagem();

            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

        }


        return contagem;
    }

    public ItemAlocado item_adicionar(String texto) {

        ItemAlocado item = null;

        long local_ponteiro_sumario = mSumarioPonteiro;


        PortaoDeslizante portao_primario = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

        DeslizadorEstrutural<PortaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PortaoDeslizante>(new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario));

        boolean item_adicionado = false;

        while (portoes_deslizantes.temProximo()) {

            PortaoDeslizante sumario_local = portoes_deslizantes.get();


            //  fmt.print("\t ++ Adicionar Item : ArmazemPortao :: {} ->> {} ", mArmazemIndice, local_ponteiro_sumario);

            if (sumario_local.temEspaco()) {
                item = sumario_local.item_adicionar(portao_primario, texto);
                item_adicionado = true;
                break;
            }


            if (sumario_local.temOutroPortao()) {

                long local_ponteiro_sumario_anterior = local_ponteiro_sumario;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
                portoes_deslizantes.setProximo(new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario));


                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);

            } else {
                portoes_deslizantes.finalizar();
            }

        }

        if (!item_adicionado) {

            long ponteiro_sumario = mFazendario.CRIAR_PORTAO(mArmazemIndice);
            portoes_deslizantes.get().setProximoPortao(ponteiro_sumario);

            portoes_deslizantes.setProximo(new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, ponteiro_sumario));

            //fmt.print("\t ++ Alocando novo ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, ponteiro_sumario);

            if (portoes_deslizantes.get().temEspaco()) {
                item = portoes_deslizantes.get().item_adicionar(portao_primario, texto);
                item_adicionado = true;
            }

        }

        return item;
    }


    public void obter_itens_alocados(PortaoDeslizante portao_primario,Lista<ItemAlocado> itens) {


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            // fmt.print("Sumario :: {}",local_ponteiro_sumario);
            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            sumario_local.obter_itens_alocados(portao_primario,itens);


            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

        }

    }

    public void zerar() {


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            // fmt.print("Sumario :: {}",local_ponteiro_sumario);
            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            sumario_local.zerar();


            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

        }

        PortaoDeslizante portao_primario = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

        if (portao_primario.temPlantacao() && portao_primario.getPlantacao() != 0) {
            fmt.print(">> TEM PLANTACAO :: {}", portao_primario.getPlantacao());

            PlantacaoAdministrador pa = new PlantacaoAdministrador(mArquivador,mFazendario,portao_primario.getIndice(), portao_primario.getPlantacao());
            pa.zerar();

        }


    }


    public void obter_itens_alocados_intervalo(PortaoDeslizante portao_primario,Lista<ItemAlocado> itens, ContadorIntervalado intervalo) {


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            // fmt.print("Sumario :: {}",local_ponteiro_sumario);
            PortaoDeslizante sumario_local = new PortaoDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            sumario_local.obter_itens_alocados_intervalo(portao_primario,itens, intervalo);


            if (sumario_local.temOutroPortao()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroPortao();
            } else {
                sumario_lendo = false;
            }

            if (intervalo.ultrapassou()) {
                break;
            }
        }

    }
}
