package libs.onterraria;

public class Terraria {


    public final Ponto LATIDUDE_MIN = new Ponto("-15.711145");
    public final Ponto LATIDUDE_MAX = new Ponto("-15.886068");
    public final Ponto LONTITUDE_MIN = new Ponto("-47.735948");
    public final Ponto LONTITUDE_MAX = new Ponto("-47.956851");

    public final int LARGURA = 1000;
    public final int ALTURA = 600;

    public final int ESPECTRO_LARGURA = LATIDUDE_MAX.getResto() - LATIDUDE_MIN.getResto();
    public final int ESPECTRO_ALTURA = LONTITUDE_MAX.getResto() - LONTITUDE_MIN.getResto();

    public boolean isDentro(String eLatitude, String eLongitude) {

        boolean mRetorno = false;

        Ponto p1 = new Ponto(eLatitude);
        Ponto p2 = new Ponto(eLongitude);

        if (p1.isValido() && p2.isValido()) {


        }

        return mRetorno;
    }

    public int getLatidude(Ponto ePonto) {
        int n = 0;

        if (ePonto.isValido()) {
            if (ePonto.getSinal() == LATIDUDE_MIN.getSinal() || ePonto.getSinal() == LATIDUDE_MAX.getSinal()) {

                if (ePonto.getIndice() >= LATIDUDE_MIN.getIndice() && ePonto.getIndice() <= LATIDUDE_MAX.getIndice()) {

                    boolean validade = false;

                    if (ePonto.getIndice() == LATIDUDE_MIN.getIndice()) {
                        if (ePonto.getResto() >= LATIDUDE_MIN.getResto()) {
                            validade = true;
                        }
                    } else if (ePonto.getIndice() == LATIDUDE_MAX.getIndice()) {
                        if (ePonto.getResto() <= LATIDUDE_MAX.getResto()) {
                            validade = true;
                        }
                    } else {
                        validade = true;
                    }

                    if (validade) {

                        int dif = ePonto.getResto() - LATIDUDE_MIN.getResto();

                        double tax = (double) (dif) / (double) ESPECTRO_LARGURA;
                        int real = (int) ((1.0-tax )* LARGURA);

                       // System.out.println("Aqui : " + ePonto.getResto());
                        //System.out.println("Min : " + LATIDUDE_MIN.getResto());
                        //System.out.println("Dif : " + dif);
                       // System.out.println("Tax : " + tax);
                        //System.out.println("Tax : " + real);

                        n=real;
                    }

                }

            }
        }


        return n;
    }


    public int getLongitude(Ponto ePonto) {
        int n = 0;

        if (ePonto.isValido()) {
            if (ePonto.getSinal() == LONTITUDE_MIN.getSinal() || ePonto.getSinal() == LONTITUDE_MAX.getSinal()) {

                if (ePonto.getIndice() >= LONTITUDE_MIN.getIndice() && ePonto.getIndice() <= LONTITUDE_MAX.getIndice()) {

                    boolean validade = false;

                    if (ePonto.getIndice() == LONTITUDE_MIN.getIndice()) {
                        if (ePonto.getResto() >= LONTITUDE_MIN.getResto()) {
                            validade = true;
                        }
                    } else if (ePonto.getIndice() == LONTITUDE_MAX.getIndice()) {
                        if (ePonto.getResto() <= LONTITUDE_MAX.getResto()) {
                            validade = true;
                        }
                    } else {
                        validade = true;
                    }

                    if (validade) {

                        int dif = ePonto.getResto() - LONTITUDE_MIN.getResto();

                        double tax = (double) (dif) / (double) ESPECTRO_ALTURA;
                        int real = (int) (tax * ALTURA);

                       // System.out.println("Aqui : " + ePonto.getResto());
                       // System.out.println("Min : " + LONTITUDE_MIN.getResto());
                       // System.out.println("Dif : " + dif);
                      //  System.out.println("Tax : " + tax);
                       // System.out.println("Tax : " + real);

                        n=real;
                    }

                }

            }
        }


        return n;
    }
}
