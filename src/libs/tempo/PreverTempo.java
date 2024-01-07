package libs.tempo;

import libs.luan.fmt;

public class PreverTempo {

    private int mTempo;
    private int mIndex;
    private int mIndexAnterior;
    private int mQuantidade;

    private long mTempoCorrente;
    private long mTempoAnterior;

    public static final int POR_MINUTO = 60;
    public static final int POR_METADE_MINUTO = 30;

    private boolean mFoiEstimado;
    private String mEstimativaCorrente = "";

    public PreverTempo(int eQuantidade) {

        mIndex = 0;
        mIndexAnterior = 0;
        mQuantidade = eQuantidade;
        mFoiEstimado = false;
        mEstimativaCorrente = "";

        mTempoCorrente = Calendario.getTempoSegundos();
        mTempoAnterior = mTempoCorrente;

        mTempo = POR_MINUTO;
    }

    public PreverTempo(int eQuantidade, int eTempo) {

        mIndex = 0;
        mIndexAnterior = 0;
        mQuantidade = eQuantidade;
        mFoiEstimado = false;
        mEstimativaCorrente = "";

        mTempoCorrente = Calendario.getTempoSegundos();
        mTempoAnterior = mTempoCorrente;

        mTempo = eTempo;
    }

    public void aumentarIndex() {
        mIndex += 1;
    }


    public void estimar() {

        mFoiEstimado = false;

        long tempo_agora = Calendario.getTempoSegundos();
        int tempo_intervalo = (int) (tempo_agora - mTempoAnterior);


        if (tempo_intervalo >= mTempo) {
            mTempoAnterior = tempo_agora;

            int processados = mIndex - mIndexAnterior;

            int velocidade_media = processados / tempo_intervalo;

            int faltam = (mQuantidade - mIndex);
            int estimativa = 0;

            if (velocidade_media > 0) {
                estimativa = faltam / velocidade_media;
            }

            String estimativa_fmt = fmt.formatar_tempo(estimativa);

            fmt.print("Tempo = {} diff = {} vel = {} l/s Faltam = {} Estimativa = {}", tempo_intervalo, processados, velocidade_media, faltam, estimativa_fmt);

            mIndexAnterior = mIndex;
            mFoiEstimado = true;
        }
    }

    public void estimar_tempo() {

        mFoiEstimado = false;

        long tempo_agora = Calendario.getTempoSegundos();
        int tempo_intervalo = (int) (tempo_agora - mTempoAnterior);

        if (tempo_intervalo >= mTempo) {
            mTempoAnterior = tempo_agora;

            int processados = mIndex - mIndexAnterior;

            int velocidade_media = processados / tempo_intervalo;

            int faltam = (mQuantidade - mIndex);
            int estimativa = 0;

            if (velocidade_media > 0) {
                estimativa = faltam / velocidade_media;
            }

            String estimativa_fmt = fmt.formatar_tempo(estimativa);

            fmt.print("-->> Estimativa = {}", estimativa_fmt);
            mEstimativaCorrente = estimativa_fmt;

            mFoiEstimado = true;

            mIndexAnterior = mIndex;
        }
    }


    public boolean foiEstimado() {
        return mFoiEstimado;
    }

    public String getEstimativa() {
        return mEstimativaCorrente;
    }

}