package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.matematica.Tipo;
import libs.zetta.ZettaPasta;

public class SuperBloco {


    private Arquivador mArquivador;
    private Fazendario mFazendario;
    private ZettaPasta mPasta;
    private long mPonteiro;

    public SuperBloco(Arquivador eArquivador, Fazendario eFazendario, ZettaPasta ePasta, long ePonteiro) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mPasta = ePasta;
        mPonteiro = ePonteiro;
    }


    public void guardar(byte[] bytes) {

        limpar_dados();

        expandir_em_parcelas(bytes);

    }

    public void expandir(byte[] bytes) {
        expandir_em_parcelas(bytes);
    }

    private void expandir_em_parcelas(byte[] bytes) {

        if (bytes.length <= Matematica.KB(16)) {
            fmt.print("Expandir abaixo/igual 16Kb");
            expandir_internamente(bytes);
        } else {

            fmt.print("Expandir acima de 16Kb");

            int bytes_total = bytes.length;
            int bytes_alocando = 0;

            while (bytes_alocando < bytes_total) {

                int bytes_ate = bytes_alocando + Matematica.KB(16);

                if (bytes_ate > bytes_total) {
                    bytes_ate = bytes_total;
                }


                int bytes_parcela_tamanho = bytes_ate - bytes_alocando;
                byte[] bytes_parcela = new byte[bytes_parcela_tamanho];

                int i = 0;
                while (i < bytes_parcela_tamanho) {
                    bytes_parcela[i] = bytes[bytes_alocando + i];
                    i += 1;
                }

                fmt.print("\t >> Expandir parcelado : {}", bytes_parcela.length);

                expandir_internamente(bytes_parcela);

                bytes_alocando += Matematica.KB(16);
            }


        }

    }

    public void exibir_estrutura() {

        fmt.print("------------------- EXIBIR SUPERINODE ---------------");
        fmt.print("Tamanho  : {} ->> {}", getTamanhoEscrito(),fmt.formatar_tamanho_precisao_dupla(getTamanhoEscrito()));
        fmt.print("Blocos   : {}", getBlocosContagem());

        for (long bloco : getBlocos()) {
            fmt.print("\t >> Bloco : {} --> {} inodes", bloco,new Bloco(mArquivador,mFazendario,mPasta,bloco).getInodesContagem());
        }

    }

    private void expandir_internamente(byte[] bytes) {

        exibir_estrutura();

        // obter ultimo ponteiro

        Inode e_superinode = mFazendario.getInode(mPonteiro);

        long tamanho_escrito = getTamanhoEscrito();

        //  fmt.print(" :: TAMANHO ESCRITO : {}", tamanho_escrito);

        fmt.print(" :: BLOCOS EXISTENTES : {}", getBlocos().getQuantidade());

        Lista<Long> blocos = getBlocos();

        if (blocos.getQuantidade() > 0) {

            fmt.print("\t >> Expandir já tem blocos !");

            long tamanho_alocado = ((getBlocos().getQuantidade() - 1) * 1000L) * Fazendario.TAMANHO_AREA_ITEM;

            long ultimo_bloco = blocos.getUltimoValor();
            Bloco ultimo = new Bloco(mArquivador, mFazendario, mPasta, ultimo_bloco);

            tamanho_alocado += (ultimo.getInodesContagem()) * Fazendario.TAMANHO_AREA_ITEM;


            long tamanho_sobrou = tamanho_alocado - tamanho_escrito;
            long ultimo_ponteiro_escrever = (Fazendario.TAMANHO_AREA_ITEM - tamanho_sobrou);

            fmt.print(" :: ARQUIVO TAMANHO              : {}", tamanho_escrito);

            fmt.print(" :: ULTIMO BLOCO : {}", ultimo.getPonteiroDados());
            fmt.print(" :: INODES       : {}", ultimo.getInodesContagem());

            fmt.print(" :: ALOCADO              : {}", tamanho_alocado);
            fmt.print(" :: USADO                : {}", tamanho_escrito);
            fmt.print(" :: SOBROU               : {}", tamanho_sobrou);
            fmt.print(" :: ULTIMO PTR ESCREVER  : {}", ultimo_ponteiro_escrever);

            fmt.print(" :: ESCREVER             : {}", bytes.length);

            if (bytes.length <= tamanho_sobrou) {

                fmt.print("\t >> Ultimo bloco tem espaco suficiente !");

                long ponteiro_ultimo_dados = ultimo.getPonteiroDados();
                long ponteiro_ultimo_bloco_ultimo_inode = ultimo.getInodes().getUltimoValor();

                Inode e_ponteiro_ultimo_bloco_ultimo_inode = mFazendario.getInode(ponteiro_ultimo_bloco_ultimo_inode);

                mArquivador.setPonteiro(e_ponteiro_ultimo_bloco_ultimo_inode.ponteiro_dados_aqui + ultimo_ponteiro_escrever);
                mArquivador.set_u8_vector(bytes);

                setTamanhoEscrito(tamanho_escrito + bytes.length);

            } else {

                fmt.print("\t >> Ultimo bloco não cabe tudo !");

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

                fmt.print(">> Divisão Ultimo : {}", escrever_no_ultimo.length);
                fmt.print(">> Divisão Novo   : {}", escrever_no_novo.length);

                if (escrever_no_ultimo.length > 0) {

                    fmt.print(">> OP : Escrever no ultimo !");

                    long ponteiro_ultimo_dados = ultimo.getPonteiroDados();
                    long ponteiro_ultimo_bloco_ultimo_inode = ultimo.getInodes().getUltimoValor();

                    Inode e_ponteiro_ultimo_bloco_ultimo_inode = mFazendario.getInode(ponteiro_ultimo_bloco_ultimo_inode);

                    mArquivador.setPonteiro(e_ponteiro_ultimo_bloco_ultimo_inode.ponteiro_dados_aqui + ultimo_ponteiro_escrever);
                    mArquivador.set_u8_vector(escrever_no_ultimo);

                    setTamanhoEscrito(tamanho_escrito + escrever_no_ultimo.length);

                }


                if (ultimo.getInodesContagem() < 1000) {
                    fmt.print(">> OP : Alocar novo Inode !");


                    Opcional<Long> dados_inode = mPasta.obter_um();
                    long dados_inode_ptr = dados_inode.get();
                    mFazendario.marcar_ocupado(dados_inode_ptr);
                    Inode e_dados_inode = mFazendario.getInode(dados_inode_ptr);

                    for (Long inode_aqui : ultimo.getInodes()) {
                        //       fmt.print(">> Inode Antes :: {}", inode_aqui);
                    }

                    ultimo.adicionar_inode(dados_inode_ptr);

                    mArquivador.setPonteiro(e_dados_inode.ponteiro_dados_aqui);
                    mArquivador.set_u8_vector(escrever_no_novo);

                    setTamanhoEscrito(getTamanhoEscrito() + escrever_no_novo.length);


                    for (Long inode_aqui : ultimo.getInodes()) {
                        //      fmt.print(">> Inode Depois :: {}", inode_aqui);
                    }

                    //    fmt.print("## AUMENTANDO BLOCO DE INODES");
                } else {
                    fmt.print(">> OP : Precisa de outro bloco !");

                    fmt.print(">> Blocos : {}", getBlocos().getQuantidade());

                    //    fmt.print(">> alocando novo bloco de blocos");

                    Opcional<Long> op_novo_bloco = mPasta.obter_um();
                    if (op_novo_bloco.isOK()) {

                        long ptr_novo_bloco = op_novo_bloco.get();
                        mFazendario.marcar_ocupado(ptr_novo_bloco);

                        Inode inode_bloco = mFazendario.getInode(ptr_novo_bloco);
                        mArquivador.setPonteiro(inode_bloco.ponteiro_dados_aqui);
                        mArquivador.set_u32(0);

                        adicionar_bloco(ptr_novo_bloco);

                        //    fmt.print(">> alocando bloco de dados inode");

                        Opcional<Long> op_novo_inode = mPasta.obter_um();
                        if (op_novo_inode.isOK()) {

                            long ptr_novo_inode = op_novo_inode.get();
                            mFazendario.marcar_ocupado(ptr_novo_inode);
                            Inode inode_novo_inode = mFazendario.getInode(ptr_novo_inode);

                            Bloco novo_bloco = new Bloco(mArquivador, mFazendario, mPasta, ptr_novo_bloco);
                            novo_bloco.adicionar_inode(ptr_novo_inode);

                            mArquivador.setPonteiro(inode_novo_inode.ponteiro_dados_aqui);
                            mArquivador.set_u8_vector(escrever_no_novo);

                            setTamanhoEscrito(getTamanhoEscrito() + escrever_no_novo.length);

                        } else {
                            throw new RuntimeException("PROBLEMA : FALTA ESPAÇO !");
                        }

                    } else {
                        throw new RuntimeException("PROBLEMA : FALTA ESPAÇO !");
                    }

                    //  throw new RuntimeException("PROBLEMA COM TAMANHO");
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

                //  fmt.print(">> Parcelando {} - {} :: {}", bytes_parcela, parcela_bytes_i, parcela_bytes_o);

                int parcela_tamanho = parcela_bytes_o - parcela_bytes_i;
                byte[] parcela = new byte[parcela_tamanho];

                for (int parcelando = 0; parcelando < parcela_tamanho; parcelando++) {
                    parcela[parcelando] = bytes[bytes_i + parcelando];
                }

                if (is_primeiro) {
                    guardar_primeiro_bloco(parcela);
                    is_primeiro = false;
                } else {
                    expandir_em_parcelas(parcela);
                }

                bytes_i += Matematica.KB(16);
                bytes_parcela += 1;
            }


        }


    }


    private void guardar_primeiro_bloco(byte[] bytes) {

        fmt.print(">> GUARDAR PRIMEIRO INODE");

        Lista<Long> blocos = getBlocos();

        if (blocos.getQuantidade() == 0) {

            //  fmt.print(">> alocando nova pagina em superinode");

            // Alocar nova pagina para superbloco
            Opcional<Long> op_novo_bloco = mPasta.obter_um();
            if (op_novo_bloco.isOK()) {

                long ptr_novo_bloco = op_novo_bloco.get();
                mFazendario.marcar_ocupado(ptr_novo_bloco);

                Inode inode_bloco = mFazendario.getInode(ptr_novo_bloco);
                mArquivador.setPonteiro(inode_bloco.ponteiro_dados_aqui);
                mArquivador.set_u32(0);

                adicionar_bloco(ptr_novo_bloco);
                setTamanhoEscrito(0);

                //    fmt.print(">> alocando bloco de dados inode");

                Opcional<Long> op_novo_inode = mPasta.obter_um();
                if (op_novo_inode.isOK()) {

                    long ptr_novo_inode = op_novo_inode.get();
                    mFazendario.marcar_ocupado(ptr_novo_inode);
                    Inode inode_novo_inode = mFazendario.getInode(ptr_novo_inode);

                    mArquivador.setPonteiro(inode_bloco.ponteiro_dados_aqui);
                    mArquivador.set_u32(0);

                    Bloco novo_bloco = new Bloco(mArquivador, mFazendario, mPasta, ptr_novo_bloco);
                    novo_bloco.adicionar_inode(ptr_novo_inode);


                    mArquivador.setPonteiro(inode_novo_inode.ponteiro_dados_aqui);
                    mArquivador.set_u8_vector(bytes);

                    setTamanhoEscrito(bytes.length);

                } else {
                    throw new RuntimeException("PROBLEMA : FALTA ESPAÇO !");
                }

            } else {
                throw new RuntimeException("PROBLEMA : FALTA ESPAÇO !");
            }


        }


    }


    public long getTamanhoEscrito() {

        Inode e_superinode = mFazendario.getInode(mPonteiro);
        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);
        long bytes_escritos = mArquivador.get_u64();

        return bytes_escritos;
    }

    public void setTamanhoEscrito(long tam) {

        Inode e_superinode = mFazendario.getInode(mPonteiro);
        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);
        mArquivador.set_u64(tam);

    }

    public void adicionar_bloco(long ptr) {

        Inode e_superinode = mFazendario.getInode(mPonteiro);
        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);

        long bytes_escritos = mArquivador.get_u64();
        int quantidade_de_blocos = mArquivador.get_u32();

        if (quantidade_de_blocos < 100) {

            mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui + (8L + 4L) + ((long) quantidade_de_blocos * Tipo.u64));
            mArquivador.set_u64(ptr);

            mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);
            bytes_escritos = mArquivador.get_u64();
            mArquivador.set_u32(quantidade_de_blocos + 1);

        } else {
            throw new RuntimeException("Máximo de blocos por Superbloco = 100");
        }


    }

    public int getBlocosContagem() {
        Inode e_superinode = mFazendario.getInode(mPonteiro);

        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);

        long bytes_escritos = mArquivador.get_u64();

        int quantidade_de_blocos = mArquivador.get_u32();
        return quantidade_de_blocos;
    }

    public Lista<Long> getBlocos() {

        Lista<Long> blocos_paginas = new Lista<Long>();

        Inode e_superinode = mFazendario.getInode(mPonteiro);

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

        // fmt.print("\t -- {}", arquivo_tamanho_completo);

        byte[] bytes_completos = new byte[(int) arquivo_tamanho_completo];

        //fmt.print("Mapa de Blocos");

        long dados_arquivo_inicio = 0;
        long dados_arquivo_fim = 0;

        int bytes_indice_geral = 0;

        for (Long bloco_ptr : getBlocos()) {

            Bloco bloco = new Bloco(mArquivador, mFazendario, mPasta, bloco_ptr);

            // fmt.print("\t >> {} -->> {}", bloco_ptr, bloco.getInodesContagem());

            for (Long inode : bloco.getInodes()) {

                dados_arquivo_fim += Matematica.KB(16);
                if (dados_arquivo_fim > arquivo_tamanho_completo) {
                    dados_arquivo_fim = arquivo_tamanho_completo;
                }

                long dados_arquivo_bloco_tamanho = dados_arquivo_fim - dados_arquivo_inicio;

                // fmt.print("\t\t ++ {}   :: {} : {} - {} :: {}", inode, dados_arquivo_inicio, dados_arquivo_fim, dados_arquivo_bloco_tamanho, fmt.formatar_tamanho_precisao_dupla(dados_arquivo_bloco_tamanho));

                int i = 0;
                int o = (int) (dados_arquivo_fim - dados_arquivo_inicio);


                Inode inode_corrente = mFazendario.getInode(inode);

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


    public void remover_a_si_mesmo() {

        long arquivo_tamanho_completo = getTamanhoEscrito();

        //  fmt.print("\t -- {}", arquivo_tamanho_completo);

        byte[] bytes_completos = new byte[(int) arquivo_tamanho_completo];

        //  fmt.print("Mapa de Blocos");

        long dados_arquivo_inicio = 0;
        long dados_arquivo_fim = 0;

        int bytes_indice_geral = 0;

        for (Long bloco_ptr : getBlocos()) {

            Bloco bloco = new Bloco(mArquivador, mFazendario, mPasta, bloco_ptr);

            //  fmt.print("\t >> {} -->> {}", bloco_ptr, bloco.getInodesContagem());

            for (Long inode : bloco.getInodes()) {

                dados_arquivo_fim += Matematica.KB(16);
                if (dados_arquivo_fim > arquivo_tamanho_completo) {
                    dados_arquivo_fim = arquivo_tamanho_completo;
                }

                long dados_arquivo_bloco_tamanho = dados_arquivo_fim - dados_arquivo_inicio;

                //   fmt.print("\t\t ++ {}   :: {} : {} - {} :: {}", inode, dados_arquivo_inicio, dados_arquivo_fim, dados_arquivo_bloco_tamanho, fmt.formatar_tamanho_precisao_dupla(dados_arquivo_bloco_tamanho));

                Inode inode_corrente = mFazendario.getInode(inode);

                mArquivador.setPonteiro(inode_corrente.ponteiro_eu_mesmo);
                mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);

            }

            bloco.remover();

        }

        Inode e_superinode = mFazendario.getInode(mPonteiro);

        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);

        mArquivador.set_u64((long) 0);
        mArquivador.set_u32(0);

        mArquivador.setPonteiro(e_superinode.ponteiro_eu_mesmo);
        mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);


    }

    public void limpar_dados() {

        long arquivo_tamanho_completo = getTamanhoEscrito();

        //   fmt.print("\t -- {}", arquivo_tamanho_completo);

        //    fmt.print("Mapa de Blocos");

        long dados_arquivo_inicio = 0;
        long dados_arquivo_fim = 0;

        int bytes_indice_geral = 0;

        fmt.print("Remover blocos");
        for (Long bloco_ptr : getBlocos()) {

            Bloco bloco = new Bloco(mArquivador, mFazendario, mPasta, bloco_ptr);

            fmt.print("\t >> Remover Bloco {} -->> {}", bloco_ptr, bloco.getInodesContagem());

            for (Long inode : bloco.getInodes()) {

                dados_arquivo_fim += Matematica.KB(16);
                if (dados_arquivo_fim > arquivo_tamanho_completo) {
                    dados_arquivo_fim = arquivo_tamanho_completo;
                }

                long dados_arquivo_bloco_tamanho = dados_arquivo_fim - dados_arquivo_inicio;

                fmt.print("\t\t Remover Inode ++ {}   :: {} : {} - {} :: {}", inode, dados_arquivo_inicio, dados_arquivo_fim, dados_arquivo_bloco_tamanho, fmt.formatar_tamanho_precisao_dupla(dados_arquivo_bloco_tamanho));

                Inode inode_corrente = mFazendario.getInode(inode);

                mArquivador.setPonteiro(inode_corrente.ponteiro_eu_mesmo);
                mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);

            }

            bloco.remover();

        }


        Inode e_superinode = mFazendario.getInode(mPonteiro);

        mArquivador.setPonteiro(e_superinode.ponteiro_dados_aqui);

        mArquivador.set_u64((long) 0);
        mArquivador.set_u32(0);


    }


}
