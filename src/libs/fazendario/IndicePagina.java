package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.fmt;

public class IndicePagina {

    private Arquivador mArquivador;

    private long mPonteiro;

    public IndicePagina(Arquivador eArquivador, long ePonteiro) {
        mArquivador = eArquivador;
        mPonteiro = ePonteiro;
    }




    public long getMenor() {
        mArquivador.setPonteiro(mPonteiro + 8L+1L);
        return mArquivador.get_u64();
    }

    public long getMaior() {
        mArquivador.setPonteiro(mPonteiro + 8L +1L+ 8L);
        return mArquivador.get_u64();
    }

    public void setMenor(long valor) {
        mArquivador.setPonteiro(mPonteiro + 8L+1L);
        mArquivador.set_u64(valor);
    }

    public void setMaior(long valor) {
        mArquivador.setPonteiro(mPonteiro + 8L +1L+ 8L);
         mArquivador.set_u64(valor);
    }


    public void setItem(long indice, long ponteiro_dados) {

        long menor = getMenor();
        long maior = getMaior();

        long indo = menor;

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L);

        long ponteiro_dados_inicio = mPonteiro + 8L + 1L + 8L + 8L + 1L;

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA; i++) {

            mArquivador.setPonteiro(ponteiro_dados_inicio + ((long) i * (1L + 8L)));

            long ponteiro_local = mArquivador.getPonteiro();

            int local_status = mArquivador.get_u8();
            long local_ponteiro_dados = mArquivador.get_u64();


            if (indo == indice) {

                mArquivador.setPonteiro(ponteiro_local);
                mArquivador.set_u8((byte) Fazendario.TEM);
                mArquivador.set_u64(ponteiro_dados);

                fmt.print("SET :: {} ->> {}",indo,ponteiro_local);

                break;
            }

            indo += 1;
        }


    }

    public void listar_indices(Lista<IndiceLocalizado> indices) {

        long menor = getMenor();
        long maior = getMaior();

        long indo = menor;

        mArquivador.setPonteiro(mPonteiro + 8L + 1L + 8L + 8L + 1L);

        long ponteiro_dados_inicio = mPonteiro + 8L + 1L + 8L + 8L + 1L;

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA; i++) {

            mArquivador.setPonteiro(ponteiro_dados_inicio + ((long) i * (1L + 8L)));

            long ponteiro = mArquivador.getPonteiro();

            int local_status = mArquivador.get_u8();
            long local_ponteiro_dados = mArquivador.get_u64();

            fmt.print(">> Indice : {} - {}", local_status, local_ponteiro_dados);

            if (local_status == Fazendario.TEM) {


                indices.adicionar(new IndiceLocalizado(indo, local_ponteiro_dados));
            }

            indo += 1;
        }


    }

}
