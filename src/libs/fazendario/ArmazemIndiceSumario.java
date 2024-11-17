package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.DeslizadorEstrutural;
import libs.luan.Lista;
import libs.luan.Opcional;
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

        DeslizadorEstrutural<IndiceSumarioDeslizante> indices = new DeslizadorEstrutural<>(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, mPonteiro));

        while (indices.temProximo()) {

            indices.get().zerar();

            if (indices.get().temProximo()) {
                indices.setProximo(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, indices.get().getProximoIndiceSumarioPagina()));
            } else {
                indices.finalizar();
            }

        }


    }


    public void setItem(long indice, long ponteiro_dados) {

        long mais = Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS * Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA;

        long total_menor = 0;
        long total_maior = mais;


        DeslizadorEstrutural<IndiceSumarioDeslizante> indices = new DeslizadorEstrutural<>(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, mPonteiro));
        IndiceSumarioDeslizante indice_primario = indices.get();


        indice_primario.setMenor(total_menor);
        indice_primario.setMaior(total_maior);


        long menor = indice_primario.getMenor();
        long maior = indice_primario.getMaior();

        //  fmt.print("Item :: SET ( " + indice + " : " + ponteiro_dados + " ) ->> {} :: {}", menor, maior);


        while (indices.temProximo()) {

            IndiceSumarioDeslizante indice_deslizante = indices.get();


            // fmt.print("\t ++ Indice Sumario :: " + indice_deslizante.getPonteiro() + " ->> " + indice_deslizante.getMenor() + " :: " + indice_deslizante.getMaior());


            if (indice >= indice_deslizante.getMenor() && indice < indice_deslizante.getMaior()) {

                indice_deslizante.setItem(indice, ponteiro_dados);

                break;
            }


            if (indice_deslizante.temProximo()) {
                indices.setProximo(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, indice_deslizante.getProximoIndiceSumarioPagina()));
            } else {

                long ponteiro_indice = mFazendario.CRIAR_AREA_INDEXADA_SUMARIO(mArmazemID);

                IndiceSumarioDeslizante indice_sumario = mFazendario.OBTER_INDICE_SUMARIO_DESLIZANTE(mArmazemID, ponteiro_indice);

                indice_sumario.setMenor(total_maior);
                indice_sumario.setMaior(total_maior + mais);
                indice_sumario.marcarSemProximo();

                indice_deslizante.marcarProximo(ponteiro_indice);

                indices.setProximo(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, ponteiro_indice));


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


    public long getSumariosContagem() {

        long ponteiro_local = mPonteiro;
        boolean tem_proximo = true;

        long contagem = 0;

        while (tem_proximo) {

            IndiceSumarioDeslizante indice_deslizante = new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, ponteiro_local);

            contagem += 1;

            tem_proximo = indice_deslizante.temProximo();

            if (tem_proximo) {
                ponteiro_local = indice_deslizante.getProximoIndiceSumarioPagina();
            }

        }

        return contagem;
    }

    public long getPaginasContagem() {

        long ponteiro_local = mPonteiro;
        boolean tem_proximo = true;

        long contagem = 0;

        while (tem_proximo) {

            IndiceSumarioDeslizante indice_deslizante = new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, ponteiro_local);

            contagem += indice_deslizante.getPaginasContagem();

            tem_proximo = indice_deslizante.temProximo();

            if (tem_proximo) {
                ponteiro_local = indice_deslizante.getProximoIndiceSumarioPagina();
            }

        }

        return contagem;
    }


    public long getIndicesContagem() {

        long ponteiro_local = mPonteiro;
        boolean tem_proximo = true;

        long contagem = 0;

        while (tem_proximo) {

            IndiceSumarioDeslizante indice_deslizante = new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, ponteiro_local);

            contagem += indice_deslizante.getIndicesContagem();

            tem_proximo = indice_deslizante.temProximo();

            if (tem_proximo) {
                ponteiro_local = indice_deslizante.getProximoIndiceSumarioPagina();
            }

        }

        return contagem;
    }

    public long getMaximo() {
        return getSumariosContagem() * Fazendario.QUANTIDADE_DE_INDICES_POR_PAGINA * Fazendario.QUANTIDADE_DE_PAGINAS_INDEXADAS;
    }

    public Opcional<IndiceLocalizado> procurar_indice(long indice) {

        DeslizadorEstrutural<IndiceSumarioDeslizante> indices = new DeslizadorEstrutural<>(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, mPonteiro));

        while (indices.temProximo()) {

            if (indice >= indices.get().getMenor() && indice < indices.get().getMaior()) {
                return indices.get().procurar_indice(indice);
            }

            if (indices.get().temProximo()) {
                indices.setProximo(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, indices.get().getProximoIndiceSumarioPagina()));
            } else {
                indices.finalizar();
            }

        }


        return Opcional.CANCEL();
    }

    public Opcional<IndiceLocalizado> getIndiceMaior() {

        DeslizadorEstrutural<IndiceSumarioDeslizante> indices = new DeslizadorEstrutural<>(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, mPonteiro));

        Opcional<IndiceLocalizado> maior = Opcional.CANCEL();

        while (indices.temProximo()) {

            Opcional<IndiceLocalizado> maior_local = indices.get().getIndiceMaior();

         //   fmt.print("Buscando o maior indice :: ArmazemIndiceSumario -- {}",maior_local.isOK());

            if (maior_local.isOK() && maior.isOK()) {

                if (maior_local.get().getIndice() > maior.get().getIndice()) {
                    maior = maior_local;
                }

            } else {
                if(maior_local.isOK()){
                    maior = maior_local;
                }
            }

            if (indices.get().temProximo()) {
                indices.setProximo(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, indices.get().getProximoIndiceSumarioPagina()));
            } else {
                indices.finalizar();
            }

        }

        return maior;

    }


    public Opcional<Boolean> remover(long indice) {

        DeslizadorEstrutural<IndiceSumarioDeslizante> indices = new DeslizadorEstrutural<>(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, mPonteiro));

        while (indices.temProximo()) {

            if (indice >= indices.get().getMenor() && indice < indices.get().getMaior()) {
                return indices.get().remover(indice);
            }

            if (indices.get().temProximo()) {
                indices.setProximo(new IndiceSumarioDeslizante(mArquivador, mFazendario, mArmazemID, indices.get().getProximoIndiceSumarioPagina()));
            } else {
                indices.finalizar();
            }

        }


        return Opcional.CANCEL();
    }

}
