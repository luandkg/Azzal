package libs.matematica;

public class Geometria {


    public static ArcoFloat toArco(int eInicio, int eFim, int eTamanho) {

        float tc = (float) eInicio / (float) eTamanho;
        float tt = (float) (eFim) / (float) eTamanho;

        double itc = (tc * 100.0f);
        double itt = (tt * 100.0f);

        float ang_i = Matematica.getAnguloInt((int) itc);
        float ang_f = Matematica.getAnguloInt((int) itt);

        return new ArcoFloat(ang_i, ang_f);
    }
}

