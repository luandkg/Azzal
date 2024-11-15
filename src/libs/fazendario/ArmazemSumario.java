package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.fmt;

public class ArmazemSumario {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private int mArmazemIndice;
    private long mSumarioPonteiro;

    public ArmazemSumario(Arquivador eArquivador, Fazendario eFazendario, int eArmazemIndice, long eSumarioPonteiro) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mArmazemIndice = eArmazemIndice;
        mSumarioPonteiro = eSumarioPonteiro;

        //      set_u8((byte) armazem.getIndice());     // Indice
        //      set_u8((byte) ARMAZEM_TIPO_ARMAZEM);    // Tipo para Armazem - Armazem Sumario
        //      set_u8((byte) NAO_TEM);                 // Tem outra página de Armazem Sumario
        //      set_u64((long) 0);                      // Ponteiro da proxima página Armazem Sumario
        //      set_u8((byte) NAO_TEM);


    }

    public long getSumariosContagem() {
        long contagem = 0;


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            SumarioDeslizante sumario_local = new SumarioDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem +=1;

            if (sumario_local.temOutroSumario()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroSumario();
            } else {
                sumario_lendo = false;
            }

        }


        return contagem;
    }

    public long getAndaresContagem() {
        long contagem = 0;


        long local_ponteiro_sumario = mSumarioPonteiro;

        boolean sumario_lendo = true;

        while (sumario_lendo) {

            SumarioDeslizante sumario_local = new SumarioDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem += sumario_local.getAndaresContagem();

            if (sumario_local.temOutroSumario()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroSumario();
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
            SumarioDeslizante sumario_local = new SumarioDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            contagem += sumario_local.getItensAlocadosContagem();

            if (sumario_local.temOutroSumario()) {
                sumario_lendo = true;
                local_ponteiro_sumario = sumario_local.getOutroSumario();
            } else {
                sumario_lendo = false;
            }

        }


        return contagem;
    }

    public void item_adicionar(String texto) {


        long local_ponteiro_sumario = mSumarioPonteiro;


        SumarioDeslizante sumario_local = new SumarioDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);


        boolean sumario_lendo = true;
        boolean item_adicionado = false;

        while (sumario_lendo) {

            sumario_local = new SumarioDeslizante(mArquivador, mFazendario, mArmazemIndice, local_ponteiro_sumario);

            fmt.print("\t ++ Adicionar Item : ArmazemSumario :: {} ->> {} ", mArmazemIndice, local_ponteiro_sumario);

            if (sumario_local.temEspaco()) {
                sumario_local.item_adicionar(texto);
                item_adicionado = true;
                break;
            }


            if (sumario_local.temOutroSumario()) {
                sumario_lendo = true;

                long local_ponteiro_sumario_anterior = local_ponteiro_sumario;
                local_ponteiro_sumario = sumario_local.getOutroSumario();

                fmt.print("\t ++ Mudando de Sumario :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);

            } else {
                sumario_lendo = false;
            }

        }

        if (!item_adicionado) {

            long local_ponteiro_sumario_anterior=local_ponteiro_sumario;

            long ponteiro_sumario = mFazendario.criar_sumario(mArmazemIndice);
            sumario_local.setProximoSumario(ponteiro_sumario);

            sumario_local = new SumarioDeslizante(mArquivador, mFazendario, mArmazemIndice, ponteiro_sumario);

            fmt.print("\t ++ Alocando novo Sumario :: {} ->> {} ", local_ponteiro_sumario_anterior, ponteiro_sumario);

            if (sumario_local.temEspaco()) {
                sumario_local.item_adicionar(texto);
                item_adicionado = true;
            }

        }

    }


}
