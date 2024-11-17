package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.matematica.Tipo;

public class PortaoDeslizante {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private long mIndice;
    private long mSumarioPonteiro;

    private boolean mTemOutroSumario;
    private long mOutroSumarioPonteiro;

    private long PORTAO_ESPACADOR_TEM_PROXIMO;
    private long PORTAO_ESPACADOR_CONTEUDO;
    private long PORTAO_ESPACO_ANDAR_TAMANHO;

    public PortaoDeslizante(Arquivador eArquivador, Fazendario eFazendario, long eIndice, long eSumarioPonteiro) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mSumarioPonteiro = eSumarioPonteiro;
        mIndice = eIndice;


        // ESTRUTURA - PORTAO

        // ++ set_u64(indice);                        // Indice
        // ++ set_u8((byte) ARMAZEM_TIPO_ARMAZEM);    // Tipo para Armazem - Armazem Sumario
        // ++ set_u8((byte) NAO_TEM);                 // Tem outra página de Armazem Sumario
        // ++ set_u64((long) 0);                      // Ponteiro da proxima página Armazem Sumario
        // ++ set_u8((byte) NAO_TEM);


        // for (int i = 0; i < QUANTIDADE_DE_ANDARES; i++) {
        //     ++ set_u64((long) NAO_TEM);
        //  }

        // ++ set_u8((byte) ARMAZEM_FIM);

        long PORTAO_TAMANHO_INDICE = Tipo.u64;
        long PORTAO_ESPACO_PORTAO_TIPO = Tipo.u8;
        long PORTAO_ESPACO_TEM_PROXIMO = Tipo.u8;
        long PORTAO_ESPACO_PROXIMO_PONTEIRO = Tipo.u64;
        long PORTAO_ESPACO_FIM_CABECALHO = Tipo.u8;

        PORTAO_ESPACO_ANDAR_TAMANHO = Tipo.u64;

        PORTAO_ESPACADOR_TEM_PROXIMO = Tipo.SOMAR(PORTAO_TAMANHO_INDICE, PORTAO_ESPACO_PORTAO_TIPO);
        PORTAO_ESPACADOR_CONTEUDO = Tipo.SOMAR(PORTAO_TAMANHO_INDICE, PORTAO_ESPACO_PORTAO_TIPO, PORTAO_ESPACO_TEM_PROXIMO, PORTAO_ESPACO_PROXIMO_PONTEIRO, PORTAO_ESPACO_FIM_CABECALHO);

        atualizar();
    }


    public void atualizar() {

        mArquivador.setPonteiro(mSumarioPonteiro);

        long armazem_indice = mArquivador.get_u64();
        int armazem_tipo = mArquivador.get_u8();
        int armazem_tem_outra_pagina = mArquivador.get_u8();
        long armazem_outra_pagina_ponteiro = mArquivador.get_u64();
        int armazem_vazio = mArquivador.get_u8();

        if (armazem_tem_outra_pagina == Fazendario.TEM) {
            mTemOutroSumario = true;
            mOutroSumarioPonteiro = armazem_outra_pagina_ponteiro;
        }
    }

    public boolean temOutroPortao() {
        return mTemOutroSumario;
    }

    public long getOutroPortao() {
        return mOutroSumarioPonteiro;
    }

    public void setProximoPortao(long ptr) {

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_TEM_PROXIMO);
        mArquivador.set_u8((byte) Fazendario.TEM);
        mArquivador.set_u64(ptr);
    }

    public long getAndaresContagem() {

        long contagem = 0;

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {
            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public long getItensAlocadosContagem() {

        long contagem = 0;

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                // fmt.print("Andar --- {}",ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);
                long cont = andar.getItensAlocadosContagem();
                contagem += cont;
                //   fmt.print("++ {}",cont);

            }
        }

        return contagem;
    }

    public long getItensNaoAlocadosContagem() {

        long contagem = 0;

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                // fmt.print("Andar --- {}",ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);
                long cont = andar.getItensNaoAlocadosContagem();
                contagem += cont;
                //   fmt.print("++ {}",cont);

            }
        }

        return contagem;
    }

    public long getItensUtilizadosContagem() {

        long contagem = 0;

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                // fmt.print("Andar --- {}",ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);
                long cont = andar.getItensUtilizadosContagem();
                contagem += cont;
                //   fmt.print("++ {}",cont);

            }
        }

        return contagem;
    }

    public long getItensReciclaveisContagem() {

        long contagem = 0;

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                // fmt.print("Andar --- {}",ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);
                long cont = andar.getItensReciclaveisContagem();
                contagem += cont;
                //   fmt.print("++ {}",cont);

            }
        }

        return contagem;
    }


    public boolean temEspaco() {
        boolean ret = false;

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar == Fazendario.ESTA_VAZIO) {
                ret = true;
                break;
            } else if (ponteiro_andar != Fazendario.ESTA_VAZIO) {

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);

                if (andar.temEspaco()) {
                    ret = true;
                    break;
                }

            }
        }

        return ret;
    }

    public ItemAlocado item_adicionar(String texto) {

        ItemAlocado item = null;

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long local_ponteiro_andar = mArquivador.getPonteiro();

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar == Fazendario.ESTA_VAZIO) {

                long ponteiro_andar_novo = mFazendario.CRIAR_ANDAR(mIndice);

                mArquivador.setPonteiro(local_ponteiro_andar);
                mArquivador.set_u64(ponteiro_andar_novo);

                // fmt.print("\t ++ Criando Andar :: {} ->> {} ", i, ponteiro_andar_novo);


                long ponteiro_zona_de_reciclagem = mFazendario.CRIAR_ZONA_DE_RECICLAGEM(mIndice);
                //    fmt.print("\t ++ Criando ZDR :: {} ->> {} ", i, ponteiro_zona_de_reciclagem);


                ZonaDeReciclagem zdr = new ZonaDeReciclagem(mArquivador, ponteiro_zona_de_reciclagem);
                zdr.setEspacosExistentes(Fazendario.QUANTIDADE_DE_ESPACOS);
                zdr.setEspacosOcupados((long) 0);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar_novo);

                andar.setEspacosExistentes(Fazendario.QUANTIDADE_DE_ESPACOS);
                andar.setEspacosOcupados((long) 0);
                andar.setProximoEspacoVazio((long) 0);
                andar.setZonaDeReciclagem(ponteiro_zona_de_reciclagem);

                if (andar.temEspaco()) {
                    item = andar.item_adicionar(texto);
                    break;
                }

                break;
            } else if (ponteiro_andar != Fazendario.ESTA_VAZIO) {

                //  fmt.print("\t ++ Estrando no Andar :: {} ->> {} ", i, ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);

                if (andar.temEspaco()) {
                    item = andar.item_adicionar(texto);
                    break;
                }

            }
        }

        return item;
    }


    public void obter_itens_alocados(Lista<ItemAlocado> itens) {

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                // fmt.print("Andar --- {}",ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);
                andar.obter_itens_alocados(itens);
                //   fmt.print("++ {}",cont);

            }
        }

    }

    public void zerar() {

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                // fmt.print("Andar --- {}",ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);
                andar.zerar();
                //   fmt.print("++ {}",cont);

            }
        }

    }

    public void obter_itens_alocados_intervalo(Lista<ItemAlocado> itens, ContadorIntervalado intervalo) {

        mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ANDARES; i++) {

            mArquivador.setPonteiro(mSumarioPonteiro + PORTAO_ESPACADOR_CONTEUDO + (i * PORTAO_ESPACO_ANDAR_TAMANHO));

            long ponteiro_andar = mArquivador.get_u64();

            if (ponteiro_andar != Fazendario.ESTA_VAZIO) {
                // fmt.print("Andar --- {}",ponteiro_andar);

                ArmazemAndar andar = new ArmazemAndar(mArquivador, ponteiro_andar);
                andar.obter_itens_alocados_intervalo(itens, intervalo);
                //   fmt.print("++ {}",cont);

            }

            if (intervalo.ultrapassou()) {
                break;
            }
        }

    }

}
