package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.zettaquorum.Silos;

public class SuperBloco {


    private Arquivador mArquivador;
    private Silos mSilos;
    private long mPonteiro;

    public SuperBloco(Arquivador eArquivador, Silos eSilos, long ePonteiro) {
        mArquivador = eArquivador;
        mSilos = eSilos;
        mPonteiro = ePonteiro;
    }


    public void guardar(byte[] bytes) {

        Inode e_superinode = mSilos.getInode(mPonteiro);

        Lista<Long> blocos = getBlocos();

        if (blocos.getQuantidade() > 0) {

            fmt.print("-------- Tem Paginas no Superinode --------");
            for (long ptr : blocos) {
                fmt.print("\t >> {}", ptr);
            }

        }

        expandir(bytes);

    }

    public void expandir(byte[] bytes) {


        // obter ultimo ponteiro

        Inode e_superinode = mSilos.getInode(mPonteiro);

        long tamanho_escrito = getTamanhoEscrito();

        fmt.print(" :: TAMANHO ESCRITO : {}", tamanho_escrito);

        fmt.print(" :: BLOCOS EXISTENTES : {}", getBlocos().getQuantidade());

        Lista<Long> blocos = getBlocos();

        if (blocos.getQuantidade() > 0) {

            long tamanho_alocado = ((getBlocos().getQuantidade() - 1) * 1000L) * Fazendario.TAMANHO_AREA_ITEM;

            long ultimo_bloco = blocos.getUltimoValor();
            Bloco ultimo = new Bloco(mArquivador, mSilos, ultimo_bloco);

            tamanho_alocado += (ultimo.getInodesContagem()) * Fazendario.TAMANHO_AREA_ITEM;


            long tamanho_sobrou = tamanho_alocado - tamanho_escrito;
            long ultimo_ponteiro_escrever = (Fazendario.TAMANHO_AREA_ITEM - tamanho_sobrou) ;


            fmt.print(" :: ULTIMO BLOCO : {}", ultimo.getPonteiroDados());
            fmt.print(" :: INODES       : {}", ultimo.getInodesContagem());

            fmt.print(" :: ALOCADO              : {}", tamanho_alocado);
            fmt.print(" :: USADO                : {}", tamanho_escrito);
            fmt.print(" :: SOBROU               : {}", tamanho_sobrou);
            fmt.print(" :: ULTIMO PTR ESCREVER  : {}", ultimo_ponteiro_escrever);

            fmt.print(" :: ESCREVER             : {}", bytes.length);

            if (bytes.length <= tamanho_sobrou) {

                long ponteiro_ultimo_dados = ultimo.getPonteiroDados();
                long ponteiro_ultimo_bloco_ultimo_inode = ultimo.getInodes().getUltimoValor();

                Inode e_ponteiro_ultimo_bloco_ultimo_inode = mSilos.getInode(ponteiro_ultimo_bloco_ultimo_inode);

                mArquivador.setPonteiro(e_ponteiro_ultimo_bloco_ultimo_inode.ponteiro_dados_aqui + ultimo_ponteiro_escrever);
                mArquivador.set_u8_vector(bytes);

                setTamanhoEscrito(tamanho_escrito + bytes.length);

            } else {

                byte[] escrever_no_ultimo = new byte[(int) tamanho_sobrou];
                byte[] escrever_no_novo = new byte[bytes.length - (int) tamanho_sobrou];


                int i = 0;
                int o = bytes.length;

                int ultimo_i = 0;
                int novo_i = 0;

                while (i < o) {

                    if (i < tamanho_sobrou) {
                        escrever_no_ultimo[ultimo_i] = bytes[i];
                        ultimo_i += 1;
                    } else {
                        escrever_no_novo[novo_i] = bytes[i];
                        novo_i += 1;
                    }

                    i += 1;
                }

                fmt.print(">> Ultimo : {}", escrever_no_ultimo.length);
                fmt.print(">> Novo   : {}", escrever_no_novo.length);


                long ponteiro_ultimo_dados = ultimo.getPonteiroDados();
                long ponteiro_ultimo_bloco_ultimo_inode = ultimo.getInodes().getUltimoValor();

                Inode e_ponteiro_ultimo_bloco_ultimo_inode = mSilos.getInode(ponteiro_ultimo_bloco_ultimo_inode);

                mArquivador.setPonteiro(e_ponteiro_ultimo_bloco_ultimo_inode.ponteiro_dados_aqui + ultimo_ponteiro_escrever);
                mArquivador.set_u8_vector(escrever_no_ultimo);

                setTamanhoEscrito(tamanho_escrito + escrever_no_ultimo.length);

                if (ultimo.getInodesContagem() < 1000) {
                    fmt.print(">> alocando bloco de dados inode");

                    Opcional<Long> dados_inode = mSilos.obter_um();
                    long dados_inode_ptr = dados_inode.get();
                    mSilos.marcar_ocupado(dados_inode_ptr);
                    Inode e_dados_inode = mSilos.getInode(dados_inode_ptr);

                    for (Long inode_aqui : ultimo.getInodes()) {
                        fmt.print(">> Inode Antes :: {}", inode_aqui);
                    }

                    ultimo.adicionar_inode(dados_inode_ptr);

                    mArquivador.setPonteiro(e_dados_inode.ponteiro_dados_aqui);
                    mArquivador.set_u8_vector(escrever_no_novo);

                    setTamanhoEscrito(getTamanhoEscrito() + escrever_no_novo.length);


                    for (Long inode_aqui : ultimo.getInodes()) {
                        fmt.print(">> Inode Depois :: {}", inode_aqui);
                    }

                    fmt.print("## EXPLANDO BLOCO DE INODES");
                } else {
                    throw new RuntimeException("PROBLEMA COM TAMANHO");
                }


            }

        } else if (blocos.getQuantidade() == 0) {

            fmt.print(">> PRIMEIRA ALOCACAO NO ARQUIVO");

            int bytes_i = 0;
            int bytes_o = bytes.length;

            int bytes_parcela = 0;

            boolean is_primeiro = true;

            while (bytes_i < bytes_o) {

                int parcela_bytes_i = bytes_i;
                int parcela_bytes_o = parcela_bytes_i + Matematica.KB(16);

                if (parcela_bytes_o > bytes_o) {
                    parcela_bytes_o = bytes_o;
                }

                fmt.print(">> Parcelando {} - {} :: {}", bytes_parcela, parcela_bytes_i, parcela_bytes_o);

                int parcela_tamanho = parcela_bytes_o - parcela_bytes_i;
                byte[] parcela = new byte[parcela_tamanho];

                for (int parcelando = 0; parcelando < parcela_tamanho; parcelando++) {
                    parcela[parcelando] = bytes[bytes_i + parcelando];
                }

                if (is_primeiro) {
                    guardar_parcela(parcela);
                    is_primeiro = false;
                } else {
                    expandir(parcela);
                }

                bytes_i += Matematica.KB(16);
                bytes_parcela += 1;
            }


        }


    }


    private void guardar_parcela(byte[] bytes) {

        Inode e_superinode = mSilos.getInode(mPonteiro);

        Lista<Long> blocos = getBlocos();

        if (blocos.getQuantidade() == 0) {

            fmt.print(">> alocando nova pagina em superinode");

            // Alocar nova pagina para superbloco
            Opcional<Long> pagina_inode = mSilos.obter_um();
            if (pagina_inode.isOK()) {

                long bloco_a = pagina_inode.get();
                mSilos.marcar_ocupado(bloco_a);

                Inode e_pagina_inode = mSilos.getInode(bloco_a);
                mArquivador.setPonteiro(e_pagina_inode.ponteiro_dados_aqui);
                mArquivador.set_u32(0);

                fmt.print(">> alocando bloco de dados inode");

                Opcional<Long> dados_inode = mSilos.obter_um();
                long dados_inode_ptr = dados_inode.get();
                mSilos.marcar_ocupado(dados_inode_ptr);
                Inode e_dados_inode = mSilos.getInode(dados_inode_ptr);

                mArquivador.setPonteiro(e_pagina_inode.ponteiro_dados_aqui);
                mArquivador.set_u32(1);
                mArquivador.set_u64(dados_inode_ptr);

                mArquivador.setPonteiro(e_dados_inode.ponteiro_dados_aqui);
                mArquivador.set_u8_vector(bytes);


                mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);
                mArquivador.set_u64(bytes.length);
                mArquivador.set_u32(1);
                mArquivador.set_u64(bloco_a);

            }


        }


    }


    public long getTamanhoEscrito() {

        Inode e_superinode = mSilos.getInode(mPonteiro);
        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);
        long bytes_escritos = mArquivador.get_u64();

        return bytes_escritos;
    }

    public void setTamanhoEscrito(long tam) {

        Inode e_superinode = mSilos.getInode(mPonteiro);
        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);
        mArquivador.set_u64(tam);

    }

    public Lista<Long> getBlocos() {

        Lista<Long> blocos_paginas = new Lista<Long>();

        Inode e_superinode = mSilos.getInode(mPonteiro);

        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);

        long bytes_escritos = mArquivador.get_u64();

        int quantidade_de_blocos = mArquivador.get_u32();

        if (quantidade_de_blocos > 0) {

            for (int inode = 0; inode < quantidade_de_blocos; inode++) {
                long inode_ponteiro = mArquivador.get_u64();
                blocos_paginas.adicionar(inode_ponteiro);
            }

        }

        return blocos_paginas;
    }


    public byte[] getBytes() {

        long arquivo_tamanho_completo = getTamanhoEscrito();

        fmt.print("\t -- {}", arquivo_tamanho_completo);

        byte[] bytes_completos = new byte[(int) arquivo_tamanho_completo];

        fmt.print("Mapa de Blocos");

        long dados_arquivo_inicio = 0;
        long dados_arquivo_fim = 0;

        int bytes_indice_geral = 0;

        for (Long bloco_ptr : getBlocos()) {

            Bloco bloco = new Bloco(mArquivador, mSilos, bloco_ptr);

            fmt.print("\t >> {} -->> {}", bloco_ptr, bloco.getInodesContagem());

            for (Long inode : bloco.getInodes()) {

                dados_arquivo_fim += Matematica.KB(16);
                if (dados_arquivo_fim > arquivo_tamanho_completo) {
                    dados_arquivo_fim = arquivo_tamanho_completo;
                }

                long dados_arquivo_bloco_tamanho = dados_arquivo_fim - dados_arquivo_inicio;

                fmt.print("\t\t ++ {}   :: {} : {} - {} :: {}", inode, dados_arquivo_inicio, dados_arquivo_fim, dados_arquivo_bloco_tamanho, fmt.formatar_tamanho_precisao_dupla(dados_arquivo_bloco_tamanho));

                int i = 0;
                int o = (int) (dados_arquivo_fim - dados_arquivo_inicio);


                Inode inode_corrente = mSilos.getInode(inode);

                mArquivador.setPonteiro(inode_corrente.ponteiro_dados_aqui);

                while (i < o) {
                    bytes_completos[bytes_indice_geral] = mArquivador.get();
                    i += 1;
                    bytes_indice_geral += 1;
                }

                dados_arquivo_inicio += Matematica.KB(16);

            }

        }

        return bytes_completos;
    }


}
