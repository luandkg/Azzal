package libs.fazendario;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Opcional;

public class IndiceSumarioDeslizante {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private long mArmazemID;
    private long mPonteiro;

    public IndiceSumarioDeslizante(Arquivador eArquivador, Fazendario eFazendario, long eArmazemID, long ePonteiro) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mArmazemID = eArmazemID;
        mPonteiro = ePonteiro;
    }

    public long getPonteiro() {
        return mPonteiro;
    }

    public void setMenor(long quantidade) {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L);
        mArquivador.set_u64(quantidade);
    }

    public void setMaior(long quantidade) {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L);
        mArquivador.set_u64(quantidade);
    }

    public long getMenor() {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L);
        return mArquivador.get_u64();
    }

    public long getMaior() {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L);
        return mArquivador.get_u64();
    }

    public boolean temProximo() {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L);
        return mArquivador.get_u8() == Fazendario.TEM;
    }


    public long getProximoIndiceSumarioPagina() {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L);
        return mArquivador.get_u64();
    }

    public void marcarProximo(long proximo) {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L);
        mArquivador.set_u8((byte) Fazendario.TEM);
        mArquivador.set_u64(proximo);
    }

    public void marcarSemProximo() {
        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L);
        mArquivador.set_u8((byte) Fazendario.NAO_TEM);
        mArquivador.set_u64(0);
    }

    public void setItem(long indice, long ponteiro_dados) {

        // fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //   fmt.print("\t - Menor :: {}", getMenor());
        //  fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA

            //  fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
            //    fmt.print("\t - Menor :: {}", indo_menor);
            //   fmt.print("\t - Maior :: {}", (indo_menor + somando));

            if (indice >= indo_menor && indice < (indo_menor + somando)) {

                if (status == Fazendario.NAO_TEM) {

                    //     fmt.print("Alocando nova pagina !");

                    long ponteiro_conteudo_pagina = mFazendario.CRIAR_INDICE_PAGINA(mArmazemID);

                    mArquivador.setPonteiro(pagina_ponteiro_local);
                    mArquivador.set_u8((byte) Fazendario.TEM);
                    mArquivador.set_u64(indo_menor);
                    mArquivador.set_u64((indo_menor + somando));
                    mArquivador.set_u64(ponteiro_conteudo_pagina);

                    item_ponteiro_pagina = ponteiro_conteudo_pagina;

                    IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);
                    pagina.setMenor(indo_menor);
                    pagina.setMaior((indo_menor + somando));

                } else {
                    //   fmt.print("Utilizando  pagina : {}", item_ponteiro_pagina);
                }


            }


            VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");


            if (indice >= indo_menor && indice < (indo_menor + somando)) {

                IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);

                pagina.setItem(indice, ponteiro_dados);

                break;
            }

            indo_menor += somando;
        }


    }


    public void listar_indices(Lista<IndiceLocalizado> indices) {

        //  fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //  fmt.print("\t - Menor :: {}", getMenor());
        //  fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA


            if (status == Fazendario.TEM) {

                //  fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
                //   fmt.print("\t - Menor :: {}", indo_menor);
                //   fmt.print("\t - Maior :: {}", (indo_menor + somando));

                VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");

                IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);
                pagina.listar_indices(indices);

            }


            indo_menor += somando;
        }


    }

    public void zerar() {

        //  fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //  fmt.print("\t - Menor :: {}", getMenor());
        //  fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA


            if (status == Fazendario.TEM) {

                //    fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
                //   fmt.print("\t - Menor :: {}", indo_menor);
                //    fmt.print("\t - Maior :: {}", (indo_menor + somando));

                VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");

                IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);
                pagina.zerar();

            }


            indo_menor += somando;
        }


    }

    public long getPaginasContagem() {

        long contagem = 0;

        //  fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //  fmt.print("\t - Menor :: {}", getMenor());
        //  fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA


            if (status == Fazendario.TEM) {

                //   fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
                //    fmt.print("\t - Menor :: {}", indo_menor);
                //   fmt.print("\t - Maior :: {}", (indo_menor + somando));

                VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");

                contagem += 1;

            }


            indo_menor += somando;
        }

        return contagem;
    }


    public long getIndicesContagem() {

        long contagem = 0;

        //  fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //  fmt.print("\t - Menor :: {}", getMenor());
        //   fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA


            if (status == Fazendario.TEM) {

                //    fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
                //    fmt.print("\t - Menor :: {}", indo_menor);
                //     fmt.print("\t - Maior :: {}", (indo_menor + somando));

                VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");

                IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);

                contagem += pagina.getIndicesContagem();

            }


            indo_menor += somando;
        }

        return contagem;
    }

    public Opcional<IndiceLocalizado> procurar_indice(long indice) {

        long contagem = 0;

        //  fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //  fmt.print("\t - Menor :: {}", getMenor());
        //   fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA


            if (status == Fazendario.TEM) {

                //    fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
                //    fmt.print("\t - Menor :: {}", indo_menor);
                //     fmt.print("\t - Maior :: {}", (indo_menor + somando));

                VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");

                IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);

                if (indice >= pagina.getMenor() && indice < pagina.getMaior()) {
                    return pagina.procurar_indice(indice);
                }

            }


            indo_menor += somando;
        }

        return Opcional.CANCEL();
    }

    public Opcional<IndiceLocalizado> getIndiceMaior() {

        //  fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //  fmt.print("\t - Menor :: {}", getMenor());
        //  fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;

        Opcional<IndiceLocalizado> maior = Opcional.CANCEL();

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA


            if (status == Fazendario.TEM) {

                //  fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
                //   fmt.print("\t - Menor :: {}", indo_menor);
                //   fmt.print("\t - Maior :: {}", (indo_menor + somando));

                VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");

                IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);
                Opcional<IndiceLocalizado> maior_local = pagina.getIndiceMaior();

                if (maior_local.isOK() && maior.isOK()) {

                    if (maior_local.get().getIndice() > maior.get().getIndice()) {
                        maior = maior_local;
                    }

                } else {
                    maior = maior_local;
                }

            }


            indo_menor += somando;
        }

        return maior;
    }

    public Opcional<Boolean> remover(long indice) {

        long contagem = 0;

        //  fmt.print("IndiceSumarioDeslizante :: {}", mPonteiro);
        //  fmt.print("\t - Menor :: {}", getMenor());
        //   fmt.print("\t - Maior :: {}", getMaior());

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L + 8L);

        long sumario_ponteiro_dados = mArquivador.getPonteiro();

        long indo_menor = getMenor();
        long somando = Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS; i++) {

            mArquivador.setPonteiro(sumario_ponteiro_dados + ((long) i * (1L + 8L + 8L + 8L)));

            long pagina_ponteiro_local = mArquivador.getPonteiro();


            int status = mArquivador.get_u8();
            long item_menor = mArquivador.get_u64(); // MENOR INDICE
            long item_maior = mArquivador.get_u64(); // MAIOR INDICE
            long item_ponteiro_pagina = mArquivador.get_u64(); // PONTEIRO PAGINA


            if (status == Fazendario.TEM) {

                //    fmt.print("IndicePagina :: {} -->> {} = {} :: ({})", i, status, pagina_ponteiro_local, item_ponteiro_pagina);
                //    fmt.print("\t - Menor :: {}", indo_menor);
                //     fmt.print("\t - Maior :: {}", (indo_menor + somando));

                VERIFICADOR.DEVE_SER_VERDADEIRO(pagina_ponteiro_local > 0, "Precisa ser maior que 0");

                IndicePagina pagina = new IndicePagina(mArquivador, item_ponteiro_pagina);

                if (indice >= pagina.getMenor() && indice < pagina.getMaior()) {
                    return pagina.remover(indice);
                }

            }


            indo_menor += somando;
        }

        return Opcional.CANCEL();
    }
}
