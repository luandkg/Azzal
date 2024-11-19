package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Opcional;
import libs.matematica.Tipo;

public class ZonaDeReciclagem {


    private Arquivador mArquivador;

    private long mPonteiroZonaDeReciclagem;

    private long ZDR_TAMANHO_ESPACO;
    private long ZDR_POSICAO_ESPACOS_EXISTENTES;
    private long ZDR_POSICAO_ESPACOS_OCUPADOS;
    private long ZDR_POSICAO_ESPACOS;

    public ZonaDeReciclagem(Arquivador eArquivador, long ePonteiroZonaDeReciclagem) {
        mArquivador = eArquivador;
        mPonteiroZonaDeReciclagem = ePonteiroZonaDeReciclagem;


        //  mArquivador.set_u64(indice);                                // Indice
        //  mArquivador.set_u8((byte) ARMAZEM_TIPO_ZONA_DE_RECICLAGEM); // Tipo para Armazem - Armazem Sumario
        //  mArquivador.set_u64((long) 0);                              // Espacos Existentes
        // mArquivador.set_u64((long) 0);                              // Espacos Ocupados
        //  mArquivador.set_u8((byte) NAO_TEM);


        // for (int i = 0; i < QUANTIDADE_DE_ESPACOS; i++) {
        //     mArquivador.set_u64((long) NAO_TEM);
        //  }

        //  mArquivador.set_u8((byte) ANDAR_FIM);


        long ZDR_TAMANHO_INDICE = Tipo.u64;
        long ZDR_TAMANHO_TIPO = Tipo.u8;
        long ZDR_TAMANHO_ESPACOS_EXISTENTES = Tipo.u64;
        long ZDR_TAMANHO_ESPACOS_OCUPADOS = Tipo.u64;
        long ZDR_TAMANHO_VAZIO = Tipo.u8;

         ZDR_TAMANHO_ESPACO = Tipo.u64;

         ZDR_POSICAO_ESPACOS_EXISTENTES = Tipo.SOMAR(ZDR_TAMANHO_INDICE,ZDR_TAMANHO_TIPO);
         ZDR_POSICAO_ESPACOS_OCUPADOS = Tipo.SOMAR(ZDR_TAMANHO_INDICE,ZDR_TAMANHO_TIPO,ZDR_TAMANHO_ESPACOS_EXISTENTES);

         ZDR_POSICAO_ESPACOS = Tipo.SOMAR(ZDR_TAMANHO_INDICE,ZDR_TAMANHO_TIPO,ZDR_TAMANHO_ESPACOS_EXISTENTES,ZDR_TAMANHO_ESPACOS_OCUPADOS,ZDR_TAMANHO_VAZIO);

    }

    public void setEspacosExistentes(long quantidade) {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + ZDR_POSICAO_ESPACOS_EXISTENTES);
        mArquivador.set_u64(quantidade);
    }

    public void setEspacosOcupados(long quantidade) {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + ZDR_POSICAO_ESPACOS_OCUPADOS);
        mArquivador.set_u64(quantidade);
    }

    public long getEspacosExistentes() {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + ZDR_POSICAO_ESPACOS_EXISTENTES);
        return mArquivador.get_u64();
    }

    public long getEspacosOcupados() {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + ZDR_POSICAO_ESPACOS_OCUPADOS);
        return mArquivador.get_u64();
    }

    public boolean temReciclaveis() {
        return getEspacosOcupados() > 0;
    }

    public Opcional<Long> obterItemReciclado() {

        long ocupados = getEspacosOcupados();

        if (ocupados > 0) {

            mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (ZDR_POSICAO_ESPACOS + ((ocupados - 1L) * ZDR_TAMANHO_ESPACO)));
            long id = mArquivador.get_u64();

            ocupados -= 1;
            if (ocupados < 0) {
                ocupados = 0;
            }

            setEspacosOcupados(ocupados);

            return Opcional.OK(id);
        }

        return Opcional.CANCEL();
    }

    public void adicionar(long valor) {

        long espacos = getEspacosExistentes();
        long ocupados = getEspacosOcupados();

        if (ocupados < espacos) {

            mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (ZDR_POSICAO_ESPACOS + (ocupados * ZDR_TAMANHO_ESPACO)));
            mArquivador.set_u64(valor);

            ocupados += 1;

            setEspacosOcupados(ocupados);

        } else {
            throw new RuntimeException("Não existe espaco na ZonaDeReciclagem !");
        }

    }


    public void zerar() {
        setEspacosOcupados((long) 0);
        setEspacosExistentes(Fazendario.QUANTIDADE_DE_ESPACOS);
    }
}
