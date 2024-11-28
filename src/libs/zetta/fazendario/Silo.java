package libs.zetta.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.matematica.Tipo;

public class Silo {

    private Arquivador mArquivador;
    private long mPonteiroPlantacao;

    private long PLANTACAO_LOCAL_TAMANHO;
    private long PLANTACAO_LOCAL_MAPA;

    private long mMapaInicio;
    private long mMapaFim;
    private long mMapaTamanho;
    private long mMapaQuantidade;

    public Silo(Arquivador eArquivador, long ePonteiroPlantacao) {

        mArquivador = eArquivador;
        mPonteiroPlantacao = ePonteiroPlantacao;


        //  mArquivador.set_u8((byte) NAO_TEM);
        //   mArquivador.set_u64((long) NAO_TEM); // PONTEIRO PARA MIM MESMO
        //  mArquivador.set_u64((long) NAO_TEM); //PONTEIRO PARA DADOS DE 16KB


        long PLANTACAO_ESPACO_INDICE = Tipo.u64;
        long PLANTACAO_ESPACO_TIPO = Tipo.u8;

        long PLANTACAO_ESPACO_MAPA_INICIO = Tipo.u64;
        long PLANTACAO_ESPACO_MAPA_FIM = Tipo.u64;

        long PLANTACAO_ESPACO_FIM = Tipo.u8;

        PLANTACAO_LOCAL_TAMANHO = Tipo.SOMAR(PLANTACAO_ESPACO_INDICE, PLANTACAO_ESPACO_TIPO);
        PLANTACAO_LOCAL_MAPA = Tipo.SOMAR(PLANTACAO_ESPACO_INDICE, PLANTACAO_ESPACO_TIPO, PLANTACAO_ESPACO_MAPA_INICIO, PLANTACAO_ESPACO_MAPA_FIM, PLANTACAO_ESPACO_FIM);

        long PLANTACAO_ITEM_ESPACO_STATUS = Tipo.u8;
        long PLANTACAO_ITEM_PONTEIRO_A_SI_MESMO = Tipo.u64;
        long PLANTACAO_ITEM_PONTEIRO_DADOS = Tipo.u64;

        mArquivador.setPonteiro(mPonteiroPlantacao);

        long indice = mArquivador.get_u64();
        long tipo = mArquivador.get_u8();
        mMapaInicio = mArquivador.get_u64();
        mMapaFim = mArquivador.get_u64();

        long vazio = mArquivador.get_u8();

        mMapaTamanho = mMapaFim - mMapaInicio;
        mMapaQuantidade = mMapaTamanho / (PLANTACAO_ITEM_ESPACO_STATUS + PLANTACAO_ITEM_PONTEIRO_A_SI_MESMO + PLANTACAO_ITEM_PONTEIRO_DADOS);

    }

    public long getPonteiro() {
        return mPonteiroPlantacao;
    }

    public long getMapaInicio() {
        return mMapaInicio;
    }

    public long getMapaFim() {
        return mMapaFim;
    }

    public long getMapaTamanho() {
        return mMapaTamanho;
    }

    public long getMapaQuantidade() {
        return mMapaQuantidade;
    }

    public Opcional<Long> obter_um() {
        Opcional<Long> ret = Opcional.CANCEL();

        mArquivador.setPonteiro(mPonteiroPlantacao + PLANTACAO_LOCAL_MAPA);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_AREAS_NO_SILO; i++) {

            int area_status = mArquivador.get_u8();
            long area_ponteiro = mArquivador.get_u64();
            long area_ponteiro_dados = mArquivador.get_u64();

            if (area_status == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO || area_status == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                ret = Opcional.OK(area_ponteiro);
                break;
            }


        }

        return ret;
    }

    public Lista<Long> obter_ate(int quantidade) {
        Lista<Long> obtidos = new Lista<Long>();


        mArquivador.setPonteiro(mPonteiroPlantacao + PLANTACAO_LOCAL_MAPA);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_AREAS_NO_SILO; i++) {

            int area_status = mArquivador.get_u8();
            long area_ponteiro = mArquivador.get_u64();
            long area_ponteiro_dados = mArquivador.get_u64();

            if (area_status == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO || area_status == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                obtidos.adicionar(area_ponteiro);
            }


            if (obtidos.getQuantidade() == quantidade) {
                break;
            }

        }

        return obtidos;
    }


    public void zerar() {

        mArquivador.setPonteiro(mPonteiroPlantacao + PLANTACAO_LOCAL_MAPA);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_AREAS_NO_SILO; i++) {

            long ponteiro_antes = mArquivador.getPonteiro();

            int area_status = mArquivador.get_u8();
            long area_ponteiro = mArquivador.get_u64();
            long area_ponteiro_dados = mArquivador.get_u64();

            long ponteiro_depois = mArquivador.getPonteiro();

            if (area_status == Fazendario.ESPACO_OCUPADO) {

                mArquivador.setPonteiro(ponteiro_antes);
                mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);

                mArquivador.setPonteiro(ponteiro_depois);
            }

        }

    }


    public int getQuantidade() {

        mArquivador.setPonteiro(mPonteiroPlantacao + PLANTACAO_LOCAL_MAPA);

        int contando = 0;

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_AREAS_NO_SILO; i++) {

            long ponteiro_antes = mArquivador.getPonteiro();

            int area_status = mArquivador.get_u8();
            long area_ponteiro = mArquivador.get_u64();
            long area_ponteiro_dados = mArquivador.get_u64();

            if (area_status == Fazendario.ESPACO_OCUPADO) {


            }

            contando += 1;

        }

        return contando;
    }

    public int getAlocados() {

        mArquivador.setPonteiro(mPonteiroPlantacao + PLANTACAO_LOCAL_MAPA);

        int contando = 0;

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_AREAS_NO_SILO; i++) {

            long ponteiro_antes = mArquivador.getPonteiro();

            int area_status = mArquivador.get_u8();
            long area_ponteiro = mArquivador.get_u64();
            long area_ponteiro_dados = mArquivador.get_u64();

            if (area_status == Fazendario.ESPACO_OCUPADO || area_status == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                contando += 1;
            }


        }

        return contando;
    }

    public int getOcupados() {

        mArquivador.setPonteiro(mPonteiroPlantacao + PLANTACAO_LOCAL_MAPA);

        int contando = 0;

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_AREAS_NO_SILO; i++) {

            long ponteiro_antes = mArquivador.getPonteiro();

            int area_status = mArquivador.get_u8();
            long area_ponteiro = mArquivador.get_u64();
            long area_ponteiro_dados = mArquivador.get_u64();

            if (area_status == Fazendario.ESPACO_OCUPADO) {
                contando += 1;
            }


        }

        return contando;
    }

    public int getDisponiveis() {

        mArquivador.setPonteiro(mPonteiroPlantacao + PLANTACAO_LOCAL_MAPA);

        int contando = 0;

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_AREAS_NO_SILO; i++) {

            long ponteiro_antes = mArquivador.getPonteiro();

            int area_status = mArquivador.get_u8();
            long area_ponteiro = mArquivador.get_u64();
            long area_ponteiro_dados = mArquivador.get_u64();

            if (area_status == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO || area_status == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                contando += 1;
            }


        }

        return contando;
    }


}
