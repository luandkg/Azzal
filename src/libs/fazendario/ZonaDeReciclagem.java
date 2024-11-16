package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Opcional;

public class ZonaDeReciclagem {


    private Arquivador mArquivador;

    private long mPonteiroZonaDeReciclagem;

    public ZonaDeReciclagem(Arquivador eArquivador, long ePonteiroZonaDeReciclagem) {
        mArquivador = eArquivador;
        mPonteiroZonaDeReciclagem = ePonteiroZonaDeReciclagem;

        //  mArquivador.set_u8((byte) indice);                          // Indice
        //   mArquivador.set_u8((byte) ARMAZEM_TIPO_ZONA_DE_RECICLAGEM); // Tipo para Armazem - Armazem Sumario
        //   mArquivador.set_u64((long) 0);                              // Espacos Existentes
        //    mArquivador.set_u64((long) 0);                              // Espacos Ocupados
        // mArquivador.set_u8((byte) NAO_TEM);

    }

    public void setEspacosExistentes(long quantidade) {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L));
        mArquivador.set_u64(quantidade);
    }

    public void setEspacosOcupados(long quantidade) {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L + 8L));
        mArquivador.set_u64(quantidade);
    }

    public long getEspacosExistentes() {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L));
        return mArquivador.get_u64();
    }

    public long getEspacosOcupados() {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L + 8L));
        return mArquivador.get_u64();
    }

    public boolean temReciclaveis() {
        return getEspacosOcupados() > 0;
    }

    public Opcional<Long> obterItemReciclado() {

        long ocupados = getEspacosOcupados();

        if (ocupados > 0) {

            mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L + 8L + 8L + 1L + ((ocupados - 1L) * 8L)));
            long id = mArquivador.get_u64();

            ocupados -= 1;
            if (ocupados < 0) {
                ocupados = 0;
            }

            mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L + 8L));
            mArquivador.set_u64(ocupados);

            return Opcional.OK(id);
        }

        return Opcional.CANCEL();
    }

    public void adicionar(long valor) {

        long espacos = getEspacosExistentes();
        long ocupados = getEspacosOcupados();

        if (ocupados < espacos) {

            mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L + 8L + 8L + 1L + (ocupados * 8L)));
            mArquivador.set_u64(valor);

            ocupados += 1;

            mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L + 8L));
            mArquivador.set_u64(ocupados);

        } else {
            throw new RuntimeException("Não existe espaco na ZonaDeReciclagem !");
        }

    }


    public void zerar() {
        mArquivador.setPonteiro(mPonteiroZonaDeReciclagem + (1L + 1L + 8L));
        mArquivador.set_u64((long) 0);
    }
}
