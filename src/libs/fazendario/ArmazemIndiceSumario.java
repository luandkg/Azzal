package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.fmt;

public class ArmazemIndiceSumario {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private long mPonteiro;
    private long mArmazemID;

    public ArmazemIndiceSumario(Arquivador eArquivador, Fazendario eFazendario, long eArmazemID, long ePonteiro) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mPonteiro = ePonteiro;
        mArmazemID = eArmazemID;
    }


    public void zerar() {

        long maior = Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS * Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;

        fmt.print("Maior = " + maior);

        IndiceSumarioDeslizante indice_deslizante = new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, mPonteiro);

        indice_deslizante.setMenor(0);
        indice_deslizante.setMaior(maior);
    }


    public void setItem(long indice, long ponteiro_dados) {

        IndiceSumarioDeslizante indice_primario = new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, mPonteiro);

        long menor = indice_primario.getMenor();
        long maior = indice_primario.getMaior();

        fmt.print("Item :: SET ( " + indice + " : " + ponteiro_dados + " ) ->> {} :: {}", menor, maior);

        long ponteiro_local = mPonteiro;
        boolean tem_proximo = true;


        long mais = Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS * Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;

        long total_menor = 0;
        long total_maior = mais;

        while (tem_proximo) {

            IndiceSumarioDeslizante indice_deslizante = new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, ponteiro_local);



            fmt.print("\t ++ Indice Sumario :: " + indice_deslizante.getPonteiro() + " ->> " + indice_deslizante.getMenor() + " :: " + indice_deslizante.getMaior());


            if (indice >= indice_deslizante.getMenor() && indice < indice_deslizante.getMaior()) {

                indice_deslizante.setItem(indice, ponteiro_dados);

                break;
            }


            // IndicePagina :: 1 -->> 0 = 290328 :: (-4421410033682612224)
            // IndicePagina :: 1 -->> 255 = 290328 :: (414371)

            tem_proximo = indice_deslizante.temProximo();

            if (tem_proximo) {
                ponteiro_local = indice_deslizante.getProximoIndiceSumarioPagina();
            } else {

                long ponteiro_indice = mFazendario.CRIAR_AREA_INDEXADA_SUMARIO(mArmazemID);

                IndiceSumarioDeslizante indice_sumario = mFazendario.OBTER_INDICE_SUMARIO_DESLIZANTE(mArmazemID, ponteiro_indice);

                indice_sumario.setMenor(total_maior);
                indice_sumario.setMaior(total_maior + mais);
                indice_sumario.marcarSemProximo();

                indice_deslizante.marcarProximo(ponteiro_indice);

                tem_proximo = true;
                ponteiro_local = ponteiro_indice;

            }

            total_menor += mais;
            total_maior += mais;

        }


    }


    public Lista<IndiceLocalizado> getIndices() {

        long ponteiro_local = mPonteiro;
        boolean tem_proximo = true;

        Lista<IndiceLocalizado> indices = new Lista<IndiceLocalizado>();

        while (tem_proximo) {

            IndiceSumarioDeslizante indice_deslizante = new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, ponteiro_local);

            indice_deslizante.listar_indices(indices);

            tem_proximo = indice_deslizante.temProximo();

            if (tem_proximo) {
                ponteiro_local = indice_deslizante.getProximoIndiceSumarioPagina();
            }

        }

        return indices;
    }

}
