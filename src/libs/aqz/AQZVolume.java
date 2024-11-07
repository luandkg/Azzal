package libs.aqz;

import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Opcional;
import libs.luan.fmt;

public class AQZVolume {

    private Arquivador mArquivador;
    private int mVolumeID;
    private long mMapaInicio;
    private long mMapaFim;

    private long mDadosInicio;
    private long mDadosFim;

    private int objetos_livres = 0;
    private int objetos_ocupados = 0;
    private int objetos_raiz = 0;

    public AQZVolume(Arquivador eArquivador, int volume_id, long eMapaInicio, long eMapaFim, long eDadosInicio, long eDadosFim) {
        mArquivador = eArquivador;
        mVolumeID = volume_id;
        mMapaInicio = eMapaInicio;
        mMapaFim = eMapaFim;
        mDadosInicio = eDadosInicio;
        mDadosFim = eDadosFim;
    }

    public int getVolumeID() {
        return mVolumeID;
    }

    public long getMapaInicio() {
        return mMapaInicio;
    }

    public long getDadosInicio() {
        return mDadosInicio;
    }


    public void atualizar() {

        objetos_livres = 0;
        objetos_ocupados = 0;
        objetos_raiz = 0;


        mArquivador.setPonteiro(mMapaInicio);

        for (int mapa = 0; mapa < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; mapa++) {
            int mapa_status = mArquivador.get_u8();

            if (mapa_status == 0) {
                objetos_livres += 1;
            } else {
                objetos_ocupados += 1;
                if (mapa_status == 2) {
                    objetos_raiz += 1;
                }
            }

        }

    }


    public int getBlocosDisponiveis() {
        return objetos_livres;
    }


    public AQZVolumeBloco getUmBlocoDisponivel() {

        AQZVolumeBloco ret = null;

        mArquivador.setPonteiro(mMapaInicio);

        for (int blocoID = 0; blocoID < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; blocoID++) {
            int mapa_status = mArquivador.get_u8();

            if (mapa_status == 0) {
                ret = new AQZVolumeBloco(this, blocoID);
                break;
            }

        }

        return ret;

    }

    public void zerar() {

        objetos_livres = 0;
        objetos_ocupados = 0;
        objetos_raiz = 0;


        mArquivador.setPonteiro(mMapaInicio);

        for (int mapa = 0; mapa < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; mapa++) {
            mArquivador.set_u8((byte) 0);
        }

    }


    public Arquivador getArquivador() {
        return mArquivador;
    }

    public void arquivos_dump() {

        Lista<Entidade> arquivos = new Lista<Entidade>();

        mArquivador.setPonteiro(mMapaInicio);

        for (int blocoID = 0; blocoID < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; blocoID++) {
            int mapa_status = mArquivador.get_u8();

            if (mapa_status == 2) {

                long arquivo_ponteiro_mapa = (mMapaInicio + blocoID);
                long arquivo_ponteiro_dados = (mDadosInicio + ((long) blocoID * Matematica.KB(64)));

                Entidade arquivo = ENTT.CRIAR_EM(arquivos);

                arquivo.at("INode", arquivo_ponteiro_dados);
                arquivo.at("VID", mVolumeID);
                arquivo.at("BlocoID", blocoID);
                arquivo.at("Mapa", arquivo_ponteiro_mapa);
                arquivo.at("Dados", arquivo_ponteiro_dados);

                // fmt.print("Bloco Raiz :: {} ->> {}", arquivo_ponteiro_mapa, arquivo_ponteiro_dados);

            }

        }

        for (Entidade e_arquivo : arquivos) {

            long inode_dados = e_arquivo.atLong("Dados");

            //  fmt.print("@DEBUG p1 -->> {}",inode_dados);

            AQZArquivoInternamente arquivo_interno = new AQZArquivoInternamente(mArquivador, inode_dados);
            arquivo_interno.atualizar();

            e_arquivo.at("Nome", arquivo_interno.getNome());
            e_arquivo.at("Inodes", arquivo_interno.getInodesQuantidade());
            e_arquivo.at("Tamanho", arquivo_interno.getTamanho());
            e_arquivo.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(arquivo_interno.getTamanho()));

            //  fmt.print("@DEBUG p2 -->> {}",inode_dados);

        }


        ENTT.EXIBIR_TABELA_COM_NOME(arquivos, "@ARQUIVOS");

    }

    public Opcional<Entidade> procurar_arquivo(String proc_arquivo_nome) {

        Lista<Entidade> arquivos = new Lista<Entidade>();

        mArquivador.setPonteiro(mMapaInicio);

        for (int blocoID = 0; blocoID < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; blocoID++) {
            int mapa_status = mArquivador.get_u8();

            if (mapa_status == 2) {

                long arquivo_ponteiro_mapa = (mMapaInicio + blocoID);
                long arquivo_ponteiro_dados = (mDadosInicio + ((long) blocoID * Matematica.KB(64)));

                Entidade arquivo = ENTT.CRIAR_EM(arquivos);

                arquivo.at("INode", arquivo_ponteiro_dados);
                arquivo.at("VID", mVolumeID);
                arquivo.at("BlocoID", blocoID);
                arquivo.at("Mapa", arquivo_ponteiro_mapa);
                arquivo.at("Dados", arquivo_ponteiro_dados);

                // fmt.print("Bloco Raiz :: {} ->> {}", arquivo_ponteiro_mapa, arquivo_ponteiro_dados);

            }

        }

        for (Entidade e_arquivo : arquivos) {

            long inode_dados = e_arquivo.atLong("Dados");

            AQZArquivoInternamente arquivo_interno = new AQZArquivoInternamente(mArquivador, inode_dados);
            arquivo_interno.atualizar_nome();

            if (arquivo_interno.getNome().contentEquals(proc_arquivo_nome)) {
                arquivo_interno.atualizar();

                e_arquivo.at("Nome", arquivo_interno.getNome());
                e_arquivo.at("Inodes", arquivo_interno.getInodesQuantidade());
                e_arquivo.at("Tamanho", arquivo_interno.getTamanho());
                e_arquivo.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(arquivo_interno.getTamanho()));

                return Opcional.OK(e_arquivo);
            }


        }


        return Opcional.CANCEL();
    }

    public Opcional<AQZArquivoExternamente> procurar_arquivo_externamente(String proc_arquivo_nome) {

        Lista<Entidade> arquivos = new Lista<Entidade>();

        mArquivador.setPonteiro(mMapaInicio);

        for (int blocoID = 0; blocoID < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; blocoID++) {
            int mapa_status = mArquivador.get_u8();

            if (mapa_status == 2) {

                long arquivo_ponteiro_mapa = (mMapaInicio + blocoID);
                long arquivo_ponteiro_dados = (mDadosInicio + ((long) blocoID * Matematica.KB(64)));

                Entidade arquivo = ENTT.CRIAR_EM(arquivos);

                arquivo.at("INode", arquivo_ponteiro_dados);
                arquivo.at("VID", mVolumeID);
                arquivo.at("BlocoID", blocoID);
                arquivo.at("Mapa", arquivo_ponteiro_mapa);
                arquivo.at("Dados", arquivo_ponteiro_dados);

                // fmt.print("Bloco Raiz :: {} ->> {}", arquivo_ponteiro_mapa, arquivo_ponteiro_dados);

            }

        }

        for (Entidade e_arquivo : arquivos) {

            long inode_dados = e_arquivo.atLong("Dados");

            AQZArquivoInternamente arquivo_interno = new AQZArquivoInternamente(mArquivador, inode_dados);
            arquivo_interno.atualizar_nome();

            if (arquivo_interno.getNome().contentEquals(proc_arquivo_nome)) {
                AQZArquivoExternamente aq = new AQZArquivoExternamente(mArquivador.getArquivo(), inode_dados);
                aq.atualizar();
                return Opcional.OK(aq);
            }


        }


        return Opcional.CANCEL();
    }

    public void analisar_integridade() {

        Lista<Entidade> arquivos = new Lista<Entidade>();

        mArquivador.setPonteiro(mMapaInicio);

        for (int blocoID = 0; blocoID < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; blocoID++) {
            int mapa_status = mArquivador.get_u8();

            if (mapa_status == 2) {

                long arquivo_ponteiro_mapa = (mMapaInicio + blocoID);
                long arquivo_ponteiro_dados = (mDadosInicio + ((long) blocoID * Matematica.KB(64)));

                Entidade arquivo = ENTT.CRIAR_EM(arquivos);

                arquivo.at("INode", arquivo_ponteiro_dados);
                arquivo.at("VID", mVolumeID);
                arquivo.at("BlocoID", blocoID);
                arquivo.at("Mapa", arquivo_ponteiro_mapa);
                arquivo.at("Dados", arquivo_ponteiro_dados);

                // fmt.print("Bloco Raiz :: {} ->> {}", arquivo_ponteiro_mapa, arquivo_ponteiro_dados);

            }

        }

        for (Entidade e_arquivo : arquivos) {

            long inode_dados = e_arquivo.atLong("Dados");

            //  fmt.print("@DEBUG p1 -->> {}",inode_dados);

            AQZArquivoInternamente arquivo_interno = new AQZArquivoInternamente(mArquivador, inode_dados);
            boolean is_integro = arquivo_interno.verificar_integridade();

            e_arquivo.at("Nome", arquivo_interno.getNome());

            if (is_integro) {
                e_arquivo.at("Integridade", "OK");
            } else {
                e_arquivo.at("Integridade", "CORROMPIDO");
            }

            //  fmt.print("@DEBUG p2 -->> {}",inode_dados);

        }


        ENTT.EXIBIR_TABELA_COM_NOME(ENTT.COLETAR(arquivos,"Integridade","CORROMPIDO"), "@ARQUIVOS");

    }

    public Lista<Entidade> getCorrompidos() {

        Lista<Entidade> arquivos = new Lista<Entidade>();

        mArquivador.setPonteiro(mMapaInicio);

        for (int blocoID = 0; blocoID < AZVolumeInternamente.VOLUME_BLOCOS_QUANTIDADE; blocoID++) {
            int mapa_status = mArquivador.get_u8();

            if (mapa_status == 2) {

                long arquivo_ponteiro_mapa = (mMapaInicio + blocoID);
                long arquivo_ponteiro_dados = (mDadosInicio + ((long) blocoID * Matematica.KB(64)));

                Entidade arquivo = ENTT.CRIAR_EM(arquivos);

                arquivo.at("INode", arquivo_ponteiro_dados);
                arquivo.at("VID", mVolumeID);
                arquivo.at("BlocoID", blocoID);
                arquivo.at("Mapa", arquivo_ponteiro_mapa);
                arquivo.at("Dados", arquivo_ponteiro_dados);

                // fmt.print("Bloco Raiz :: {} ->> {}", arquivo_ponteiro_mapa, arquivo_ponteiro_dados);

            }

        }

        for (Entidade e_arquivo : arquivos) {

            long inode_dados = e_arquivo.atLong("Dados");

            //  fmt.print("@DEBUG p1 -->> {}",inode_dados);

            AQZArquivoInternamente arquivo_interno = new AQZArquivoInternamente(mArquivador, inode_dados);
            boolean is_integro = arquivo_interno.verificar_integridade();

            e_arquivo.at("Nome", arquivo_interno.getNome());

            if (is_integro) {
                e_arquivo.at("Integridade", "OK");
            } else {
                e_arquivo.at("Integridade", "CORROMPIDO");
            }

            //  fmt.print("@DEBUG p2 -->> {}",inode_dados);

        }


        return ENTT.COLETAR(arquivos,"Integridade","CORROMPIDO");

    }

}
