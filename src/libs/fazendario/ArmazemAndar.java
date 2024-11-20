package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.matematica.Tipo;

public class ArmazemAndar {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private long mAndarPonteiro;

    private long ANDAR_POSICAO_ESPACOS;
    private long ANDAR_POSICAO_ESPACOS_EXISTENTES;
    private long ANDAR_POSICAO_ESPACOS_OCUPADOS;
    private long ANDAR_POSICAO_PROXIMO_VAZIO;
    private long ANDAR_POSICAO_AREA_DE_RECICLAGEM;

    private long ANDAR_TAMANHO_ESPACO;

    public ArmazemAndar(Arquivador eArquivador, Fazendario eFazendario, long eAndarPonteiro) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mAndarPonteiro = eAndarPonteiro;


        //  mArquivador.set_u64(indice);                        // Indice
        //     mArquivador.set_u8((byte) ARMAZEM_TIPO_ANDAR);     // Tipo para Armazem - Armazem Sumario
        //    mArquivador.set_u64((long) 0);                      // Espacos Existentes
        //    mArquivador.set_u64((long) 0);                      // Espacos Ocupados
        //     mArquivador.set_u64((long) 0);                      // Proximo espaco vazio
        //    mArquivador.set_u64((long) 0);                      // Area de Reciclagem de Espacos
        //     mArquivador.set_u8((byte) NAO_TEM);


        //   for (int i = 0; i < QUANTIDADE_DE_ESPACOS; i++) {
        //        mArquivador.set_u8((byte) NAO_TEM);
        //        mArquivador.set_u64((long) NAO_TEM);
        //    }

        //    mArquivador.set_u8((byte) ANDAR_FIM);

        long ANDAR_TAMANHO_INDICE = Tipo.u64;
        long ANDAR_TAMANHO_TIPO = Tipo.u8;
        long ANDAR_TAMANHO_ESPACOS_EXISTENTES = Tipo.u64;
        long ANDAR_TAMANHO_ESPACOS_OCUPADOS = Tipo.u64;
        long ANDAR_TAMANHO_PROXIMO_ESPACO_VAZIO = Tipo.u64;
        long ANDAR_TAMANHO_PONTEIRO_AREA_DE_RECICLAGEM = Tipo.u64;
        long ANDAR_TAMANHO_VAZIO = Tipo.u8;

        long ANDAR_TAMANHO_ESPACO_STATUS = Tipo.u8;
        long ANDAR_TAMANHO_ESPACO_PONTEIRO = Tipo.u64;

        ANDAR_TAMANHO_ESPACO = Tipo.SOMAR(ANDAR_TAMANHO_ESPACO_STATUS, ANDAR_TAMANHO_ESPACO_PONTEIRO);

        ANDAR_POSICAO_ESPACOS = Tipo.SOMAR(ANDAR_TAMANHO_INDICE, ANDAR_TAMANHO_TIPO, ANDAR_TAMANHO_ESPACOS_EXISTENTES, ANDAR_TAMANHO_ESPACOS_OCUPADOS, ANDAR_TAMANHO_PROXIMO_ESPACO_VAZIO, ANDAR_TAMANHO_PONTEIRO_AREA_DE_RECICLAGEM, ANDAR_TAMANHO_VAZIO);
        ANDAR_POSICAO_ESPACOS_EXISTENTES = Tipo.SOMAR(ANDAR_TAMANHO_INDICE, ANDAR_TAMANHO_TIPO);
        ANDAR_POSICAO_ESPACOS_OCUPADOS = Tipo.SOMAR(ANDAR_TAMANHO_INDICE, ANDAR_TAMANHO_TIPO, ANDAR_TAMANHO_ESPACOS_EXISTENTES);
        ANDAR_POSICAO_PROXIMO_VAZIO = Tipo.SOMAR(ANDAR_TAMANHO_INDICE, ANDAR_TAMANHO_TIPO, ANDAR_TAMANHO_ESPACOS_EXISTENTES, ANDAR_TAMANHO_ESPACOS_OCUPADOS);
        ANDAR_POSICAO_AREA_DE_RECICLAGEM = Tipo.SOMAR(ANDAR_TAMANHO_INDICE, ANDAR_TAMANHO_TIPO, ANDAR_TAMANHO_ESPACOS_EXISTENTES, ANDAR_TAMANHO_ESPACOS_OCUPADOS, ANDAR_TAMANHO_PROXIMO_ESPACO_VAZIO);

        atualizar();
    }


    public void atualizar() {

        mArquivador.setPonteiro(mAndarPonteiro);

        long armazem_indice = mArquivador.get_u64();
        int armazem_tipo = mArquivador.get_u8();
        long andar_espacos_existentes = mArquivador.get_u64();
        long andar_espacos_ocupados = mArquivador.get_u64();
        long andar_proximo_espaco_vazio = mArquivador.get_u64();
        long andar_espaco_de_reciclagem = mArquivador.get_u64();

        int armazem_vazio = mArquivador.get_u8();


    }

    public void setEspacosExistentes(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS_EXISTENTES);
        mArquivador.set_u64(ptr);
    }

    public void setEspacosOcupados(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS_OCUPADOS);
        mArquivador.set_u64(ptr);
    }

    public void setProximoEspacoVazio(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_PROXIMO_VAZIO);
        mArquivador.set_u64(ptr);
    }

    public void setZonaDeReciclagem(long ptr) {
        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_AREA_DE_RECICLAGEM);
        mArquivador.set_u64(ptr);
    }


    public long getProximoEspacoVazio() {
        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_PROXIMO_VAZIO);
        return mArquivador.get_u64();
    }

    public long getZonaDeReciclagem() {
        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_AREA_DE_RECICLAGEM);
        return mArquivador.get_u64();
    }


    public long getItensAlocadosContagem() {
        long ret = 0;

        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);

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

        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO) {
                ret += 1;
            }
        }


        return ret;
    }

    public long getItensUtilizadosContagem() {
        long ret = 0;

        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_OCUPADO) {
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

            mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);

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

    public ItemAlocado item_adicionar(PortaoDeslizante portao_primario, String texto) {


        long proximo_vazio = getProximoEspacoVazio();
        //    fmt.print("\t ++ Andar :: Proximo Vazio = {} ", proximo_vazio);


        if (proximo_vazio < Fazendario.QUANTIDADE_DE_ESPACOS) {

            //  fmt.print("\t ++ Andar :: Adicionar utilizando proximo vazio : {}", proximo_vazio);

            long deslocar = proximo_vazio * ANDAR_TAMANHO_ESPACO;
            mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS + deslocar);

            long indice_espaco = proximo_vazio;
            long ponteiro_local = mArquivador.getPonteiro();

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            long ponteiro_dados_final = item_adicionar_no_espaco(portao_primario, ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, texto);

            proximo_vazio += 1;
            setProximoEspacoVazio(proximo_vazio);

            return new ItemAlocado(mArquivador, this, (int) indice_espaco, ponteiro_local, ponteiro_dados_final);
        } else {

            //  fmt.print("\t ++ Adicionar Item : Andar {} ", mAndarPonteiro);

            ZonaDeReciclagem zdr = new ZonaDeReciclagem(mArquivador, getZonaDeReciclagem());

            Opcional<Long> reciclado = zdr.obterItemReciclado();

            if (reciclado.isOK()) {

                //   fmt.print("\t ++ Andar :: Adicionar utilizando item reciclado !");


                long deslocar = reciclado.get() * ANDAR_TAMANHO_ESPACO;
                mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS + deslocar);

                long indice_espaco = reciclado.get();
                long ponteiro_local = mArquivador.getPonteiro();

                long tem_ponteiro_espaco = mArquivador.get_u8();
                long ponteiro_espaco = mArquivador.get_u64();

                long ponteiro_dados_final = item_adicionar_no_espaco(portao_primario, ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, texto);

                return new ItemAlocado(mArquivador, this, (int) indice_espaco, ponteiro_local, ponteiro_dados_final);


            }


            //   fmt.print("\t ++ Andar :: Adicionar utilizando varredura no andar");

            mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);

            for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

                long ponteiro_local = mArquivador.getPonteiro();

                long tem_ponteiro_espaco = mArquivador.get_u8();
                long ponteiro_espaco = mArquivador.get_u64();

                if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO || tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                    long ponteiro_dados_final = item_adicionar_no_espaco(portao_primario, ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, texto);

                    return new ItemAlocado(mArquivador, this, (int) i, ponteiro_local, ponteiro_dados_final);

                }

            }


        }

        return null;
    }

    public long item_adicionar_no_espaco(PortaoDeslizante portao_primario, long ponteiro_local, long tem_ponteiro_espaco, long ponteiro_espaco, String texto) {


        long ponteiro_dados_retornar = ponteiro_espaco;

        if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO) {

            mArquivador.ir_para_o_fim();
            long ponteiro_dados = mArquivador.getPonteiro();

            mArquivador.set_u8_em_bloco(Fazendario.TAMANHO_SETOR_ITEM, (byte) 0);

            mArquivador.setPonteiro(ponteiro_local);
            mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);
            mArquivador.set_u64(ponteiro_dados);


            //   fmt.print("\t ++ Alocando novo espaco : {}", ponteiro_dados);

            ponteiro_dados_retornar = item_adicionar_em_espaco_alocado(portao_primario, ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, ponteiro_dados, texto);


        } else if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {

            mArquivador.setPonteiro(ponteiro_local);
            mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);

            //     fmt.print("\t ++ Utilizando espaco existente : {}", ponteiro_espaco);

            ponteiro_dados_retornar = item_adicionar_em_espaco_alocado(portao_primario, ponteiro_local, tem_ponteiro_espaco, ponteiro_espaco, ponteiro_espaco, texto);


        }

        return ponteiro_dados_retornar;
    }


    public long item_adicionar_em_espaco_alocado(PortaoDeslizante portao_primario, long ponteiro_local, long tem_ponteiro_espaco, long ponteiro_espaco, long ponteiro_dados, String texto) {

        byte[] bytes = Strings.GET_STRING_VIEW_BYTES(texto);

        boolean objeto_maior = bytes.length + 10 >= Fazendario.TAMANHO_SETOR_ITEM;

        boolean plantacao_ja_alocado = false;
        long plantacao_ponteiro_alocado = 0;


        if (objeto_maior) {
            if (portao_primario.temPlantacao() && portao_primario.getPlantacao() != 0) {
                fmt.print(">> TEM PLANTACAO :: {}", portao_primario.getPlantacao());

                plantacao_ja_alocado = true;
                plantacao_ponteiro_alocado = portao_primario.getPlantacao();

            } else {
                fmt.print(">> NAO TEM PLANTACAO :: {}", portao_primario.getPlantacao());

                long ponteiro_plantacao = mFazendario.CRIAR_PLANTACAO(portao_primario.getIndice());
                portao_primario.setPlantacao(ponteiro_plantacao);

                plantacao_ja_alocado = true;
                plantacao_ponteiro_alocado = ponteiro_plantacao;

            }
        }

        // VERIFICADOR.MENOR_OU_IGUAL(bytes.length + 10, Fazendario.TAMANHO_SETOR_ITEM);


        if (objeto_maior) {


            if (plantacao_ja_alocado) {


                PlantacaoAdministrador pa = new PlantacaoAdministrador(mArquivador, mFazendario, portao_primario.getIndice(), plantacao_ponteiro_alocado);

                Lista<Long> blocos_alocados = pa.adicionar(bytes);

                mArquivador.setPonteiro(ponteiro_dados);
                mArquivador.set_u8((byte) Fazendario.OBJETO_GRANDE);
                mArquivador.set_u32(bytes.length);
                mArquivador.set_u32(blocos_alocados.getQuantidade());

                for (Long bloco : blocos_alocados) {
                    mArquivador.set_u64(bloco);
                }


            } else {
                throw new RuntimeException("Problemaq na alocação de objetos grandes !");
            }


        } else {
            mArquivador.setPonteiro(ponteiro_dados);
            mArquivador.set_u8((byte) Fazendario.OBJETO_PEQUENO);
            mArquivador.set_u32(bytes.length);
            mArquivador.set_u8_vector(bytes);
        }

        return ponteiro_dados;
    }


    public void obter_itens_alocados(Lista<ItemAlocado> itens) {

        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);


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


            mArquivador.setPonteiro(item.getPonteiroDados());

            int item_tipo = mArquivador.get_u8();

            if (item_tipo == Fazendario.OBJETO_GRANDE) {

                int bytes_quantidade = mArquivador.get_u32();
                int blocos = mArquivador.get_u32();

                Lista<Long> blocos_alocados = new Lista<Long>();
                for (int b = 0; b < blocos; b++) {
                    long bloco_ref = mArquivador.get_u64();

                    fmt.print("\t -- BlocoRef :: {}", bloco_ref);
                    blocos_alocados.adicionar(bloco_ref);

                }

                for (Long bloco : blocos_alocados) {

                    mArquivador.setPonteiro(bloco);
                    mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);

                }

            }


            item.marcarRemovido();
            mArquivador.setPonteiro(item.getPonteiroStatus());
            mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);
        }


    }

    public void zerar() {

        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);


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


    public void obter_itens_alocados_intervalo(Lista<ItemAlocado> itens, ContadorIntervalado intervalo) {

        mArquivador.setPonteiro(mAndarPonteiro + ANDAR_POSICAO_ESPACOS);


        for (int i = 0; i < Fazendario.QUANTIDADE_DE_ESPACOS; i++) {

            long ptr_status = mArquivador.getPonteiro();
            long tem_ponteiro_espaco = mArquivador.get_u8();

            long ptr_dados = mArquivador.getPonteiro();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_OCUPADO) {

                if (intervalo.isDentro()) {
                    itens.adicionar(new ItemAlocado(mArquivador, this, i, ptr_status, ponteiro_espaco));
                }

                intervalo.mais();
            }

            if (intervalo.ultrapassou()) {
                break;
            }
        }


    }


}
