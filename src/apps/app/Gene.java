package apps.app;

public class Gene {

    private String mCodigo;
    private String mRSID;

    private String mCromossomo;
    private String mPosicao;
    private String mAlelos;

    public Gene(String eCodigo, String eRSID,String eCromossomo, String ePosicao, String eAlelos) {
        mCodigo = eCodigo;
        mRSID = eRSID;
        mCromossomo = eCromossomo;
        mPosicao = ePosicao;
        mAlelos = eAlelos;

    }

    public String getRSID(){
        return mRSID;
    }

    public String getCromossomo() {
        return mCromossomo;
    }

    public String getPosicao() {
        return mPosicao;
    }

    public String getAlelos() {
        return mAlelos;
    }


    public static Gene parse(String codigo) {

        ParserString gene = new ParserString(codigo);

        String rsid = "";
        String cromossomo = "";
        String alelos = "";
        String posicao = "";

        int p = 0;
        String juntando = "";

        while (gene.continuar()) {
            String letra = gene.get();

            //System.out.println(i + " - " + letra);

            if (letra.contentEquals(",")) {

                if (p == 0) {
                    rsid = juntando;
                } else if (p == 1) {
                    cromossomo = juntando;
                } else if (p == 2) {
                    posicao = juntando;
                } else if (p == 3) {
                    alelos = juntando;
                }

                juntando = "";
                p += 1;
            } else {
                juntando += letra;
            }

            gene.avancar();
        }

        if(juntando.length()>0){
            alelos = juntando;
        }

        return new Gene(codigo,rsid, cromossomo, posicao, alelos);
    }
}
