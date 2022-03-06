package AppAttuz.Servicos;

import AppAttuz.Camadas.Massas;
import AppAttuz.Assessorios.LatitudeCalor;

public class RecebimentoSolar {

    private final int FAIXAS_QUANTIDADE = 18;
    private LatitudeCalor[] mLatitudes;

    public RecebimentoSolar() {

        mLatitudes = new LatitudeCalor[FAIXAS_QUANTIDADE];

        mLatitudes[0] = new LatitudeCalor(-100, -450);
        mLatitudes[1] = new LatitudeCalor(-50, -350);
        mLatitudes[2] = new LatitudeCalor(0, -300);
        mLatitudes[3] = new LatitudeCalor(50, -250);
        mLatitudes[4] = new LatitudeCalor(100, -200);
        mLatitudes[5] = new LatitudeCalor(200, -150);
        mLatitudes[6] = new LatitudeCalor(300, -100);
        mLatitudes[7] = new LatitudeCalor(400, 50);
        mLatitudes[8] = new LatitudeCalor(500, 100);

        mLatitudes[9] = new LatitudeCalor(500, 200);
        mLatitudes[10] = new LatitudeCalor(400, 300);
        mLatitudes[11] = new LatitudeCalor(300, 400);
        mLatitudes[12] = new LatitudeCalor(200, 700);
        mLatitudes[13] = new LatitudeCalor(100, 700);
        mLatitudes[14] = new LatitudeCalor(50, 700);
        mLatitudes[15] = new LatitudeCalor(0, 700);
        mLatitudes[16] = new LatitudeCalor(-50, 700);
        mLatitudes[17] = new LatitudeCalor(-100, 700);

    }

    public int getFaixaDeRecebimentoSolar(Massas tectonica, int p) {
        int CARTOGRAFIA_ALTURA = tectonica.getAltura() / FAIXAS_QUANTIDADE;
        return (p / CARTOGRAFIA_ALTURA);
    }

    public int getFaixaRecebimentoSolarTamanho(Massas tectonica) {
        int tamanhoFaixa = tectonica.getAltura() / FAIXAS_QUANTIDADE;
        return tamanhoFaixa;
    }

    public int getFaixaRecebimentoSolarInicio(Massas tectonica, int eFaixa) {
        int tamanhoFaixa = tectonica.getAltura() / FAIXAS_QUANTIDADE;
        return tamanhoFaixa * eFaixa;
    }


    public int getInverno(int faixa_solar) {
        return mLatitudes[faixa_solar].getInverno();
    }

    public int getVerao(int faixa_solar) {
        return mLatitudes[faixa_solar].getVerao();
    }
}
