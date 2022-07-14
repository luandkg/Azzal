package libs.Arquivos.Video;

public class LinhaDoTempo {

    private long mTotal;
    private long mTaxa;

    public LinhaDoTempo(long eTotal, long eTaxa) {
        mTotal = eTotal;
        mTaxa = eTaxa;
    }

    public int getPorcentagem(long mExecutado) {
        double mTaxinha = (double) mTotal / 100.0;

        double mPorcentagem = (double) mExecutado / mTaxinha;
        int iPorcento = (int) mPorcentagem;

        if (iPorcento > 100) {
            iPorcento = 100;
        }


        return iPorcento;
    }

    public String getTempoFaltante(long mExecutado) {

        long eTotal = mTotal;

        String mTempo = "";

        if (mExecutado <= eTotal) {
            long diff = eTotal - mExecutado;
            long diff_tempo = diff * mTaxa;

            long eSegundos = 0;
            while (diff_tempo >= 1000) {
                diff_tempo -= 1000;
                eSegundos += 1;
            }

            mTempo = getTempoFormatado(eSegundos);

        }

        return mTempo;
    }

    public String getTempoFormatado(long eTotalSegundos) {

        int eHoras = 0;
        int eMinutos = 0;
        long eSegundos = eTotalSegundos;


        while (eSegundos >= 60) {
            eSegundos -= 60;
            eMinutos += 1;
        }

        while (eMinutos >= 60) {
            eMinutos -= 60;
            eHoras += 1;
        }


        return eHoras + ":" + eMinutos + ":" + eSegundos;
    }


}
