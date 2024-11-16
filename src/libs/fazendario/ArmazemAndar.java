package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Strings;
import libs.luan.fmt;

public class ArmazemAndar {

    private Arquivador mArquivador;
    private long mAndarPonteiro;

    public ArmazemAndar(Arquivador eArquivador, long eAndarPonteiro) {
        mArquivador = eArquivador;
        mAndarPonteiro = eAndarPonteiro;
        atualizar();
    }


    public void atualizar() {

        mArquivador.setPonteiro(mAndarPonteiro);

        int armazem_indice = mArquivador.get_u8();
        int armazem_tipo = mArquivador.get_u8();
        long andar_espacos_existentes = mArquivador.get_u64();
        long andar_espacos_ocupados = mArquivador.get_u64();
        long andar_proximo_espaco_vazio = mArquivador.get_u64();
        long andar_espaco_de_reciclagem = mArquivador.get_u64();

        int armazem_vazio = mArquivador.get_u8();


    }

    public void setEspacosExistentes(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L);
        mArquivador.set_u64(ptr);
    }

    public void setEspacosOcupados(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L);
        mArquivador.set_u64(ptr);
    }

    public void setProximoEspacoVazio(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L);
        mArquivador.set_u64(ptr);
    }

    public void setZonaDeReciclagem(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L);
        mArquivador.set_u64(ptr);
    }


    public long getProximoEspacoVazio() {
        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L);
        return mArquivador.get_u64();
    }

    public long getZonaDeReciclagem() {
        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L);
        return mArquivador.get_u64();
    }


    public long getItensAlocadosContagem() {
        long ret = 0;

        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_OCUPADO || tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                ret += 1;
            }
        }


        return ret;
    }

    public long getItensNaoAlocadosContagem() {
        long ret = 0;

        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO) {
                ret += 1;
            }
        }


        return ret;
    }

    public long getItensReciclaveisContagem() {
        ZonaDeReciclagem zdr = new ZonaDeReciclagem(mArquivador, getZonaDeReciclagem());

        return zdr.getEspacosOcupados();
    }

    public boolean temEspaco() {
        boolean ret = false;

        long proximo_vazio = getProximoEspacoVazio();

        if (proximo_vazio < Fazendario.QUANTIDADE_DE_ESPACOS) {
            ret = true;
        } else {

            ZonaDeReciclagem zdr = new ZonaDeReciclagem(mArquivador, getZonaDeReciclagem());

            if (zdr.temReciclaveis()) {
                return true;
            }

            mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L);

            for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

                long tem_ponteiro_espaco = mArquivador.get_u8();
                long ponteiro_espaco = mArquivador.get_u64();

                if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO || tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                    ret = true;
                    break;
                }
            }


        }


        return ret;
    }

    public void item_adicionar(String texto) {


        long proximo_vazio = getProximoEspacoVazio();
        fmt.print("\t ++ Andar :: Proximo Vazio = {} ", proximo_vazio);


        if (proximo_vazio < Fazendario.QUANTIDADE_DE_ESPACOS) {

            fmt.print("\t ++ Andar :: Adicionar utilizando proximo vazio : {}", proximo_vazio);

            long deslocar = proximo_vazio * (1L + 8L);
            mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L + deslocar);

            long ponteiro_local = mArquivador.getPonteiro();

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            item_adicionar_no_espaco(ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, texto);

            proximo_vazio += 1;
            setProximoEspacoVazio(proximo_vazio);

        } else {

            fmt.print("\t ++ Adicionar Item : Andar {} ", mAndarPonteiro);

            ZonaDeReciclagem zdr = new ZonaDeReciclagem(mArquivador, getZonaDeReciclagem());

            Opcional<Long> reciclado = zdr.obterItemReciclado();

            if (reciclado.isOK()) {

                fmt.print("\t ++ Andar :: Adicionar utilizando item reciclado !");


                long deslocar = reciclado.get() * (1L + 8L);
                mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L + deslocar);

                long ponteiro_local = mArquivador.getPonteiro();

                long tem_ponteiro_espaco = mArquivador.get_u8();
                long ponteiro_espaco = mArquivador.get_u64();

                item_adicionar_no_espaco(ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, texto);


                return;
            }


            fmt.print("\t ++ Andar :: Adicionar utilizando varredura no andar");

            mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L);

            for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

                long ponteiro_local = mArquivador.getPonteiro();

                long tem_ponteiro_espaco = mArquivador.get_u8();
                long ponteiro_espaco = mArquivador.get_u64();

                if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO || tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                    item_adicionar_no_espaco(ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, texto);
                    break;
                }

            }


        }


    }

    public void item_adicionar_no_espaco(long ponteiro_local, long tem_ponteiro_espaco, long ponteiro_espaco, String texto) {

        if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO) {

            mArquivador.ir_para_o_fim();
            long ponteiro_dados = mArquivador.getPonteiro();

            mArquivador.set_u8_em_bloco(Fazendario.TAMANHO_SETOR_ITEM, (byte) 0);

            mArquivador.setPonteiro(ponteiro_local);
            mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);
            mArquivador.set_u64(ponteiro_dados);

            mArquivador.setPonteiro(ponteiro_dados);

            byte[] bytes = Strings.GET_STRING_VIEW_BYTES(texto);
            mArquivador.set_u32(bytes.length);
            mArquivador.set_u8_vector(bytes);

            fmt.print("\t ++ Alocando novo espaco : {}", ponteiro_dados);


        } else if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {

            mArquivador.setPonteiro(ponteiro_local);
            mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);

            mArquivador.setPonteiro(ponteiro_espaco);

            byte[] bytes = Strings.GET_STRING_VIEW_BYTES(texto);

            mArquivador.set_u32(bytes.length);
            mArquivador.set_u8_vector(bytes);

            fmt.print("\t ++ Utilizando espaco existente : {}", ponteiro_espaco);


        }

    }


    public void obter_itens_alocados(Lista<ItemAlocado> itens) {

        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L);


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

            long ptr_status = mArquivador.getPonteiro();
            long tem_ponteiro_espaco = mArquivador.get_u8();

            long ptr_dados = mArquivador.getPonteiro();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_OCUPADO) {
                itens.adicionar(new ItemAlocado(mArquivador, this, i, ptr_status, ponteiro_espaco));
            }
        }


    }


    public void remover(ItemAlocado item) {

        if (!item.isRemovido()) {


            long zona_de_reciclagem = getZonaDeReciclagem();


            ZonaDeReciclagem zdr = new ZonaDeReciclagem(mArquivador, zona_de_reciclagem);
            zdr.adicionar(item.getIndiceSequencial());


            item.marcarRemovido();
            mArquivador.setPonteiro(item.getPonteiroStatus());
            mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);
        }


    }

    public void zerar() {

        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 8L + 1L);


        Lista<Long> zerar_ponteiros = new Lista<Long>();

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

            long ptr_status = mArquivador.getPonteiro();
            long tem_ponteiro_espaco = mArquivador.get_u8();

            long ptr_dados = mArquivador.getPonteiro();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_OCUPADO) {
                zerar_ponteiros.adicionar(ptr_status);
            }
        }

        for (Long ptr : zerar_ponteiros) {
            mArquivador.setPonteiro(ptr);
            mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);
        }

        ZonaDeReciclagem zdr = new ZonaDeReciclagem(mArquivador, getZonaDeReciclagem());
        zdr.zerar();

        setProximoEspacoVazio((long) 0);

    }


}
