package libs.tempo;

public class Data {

    private int mAno;
    private int mMes;
    private int mDia;
    private DiaSemanal mDiaSemanal;

    public Data(int eAno, int eMes, int eDia, DiaSemanal eDiaSemanal) {
        mAno = eAno;
        mMes = eMes;
        mDia = eDia;
        mDiaSemanal = eDiaSemanal;
    }

    public Data(int eAno, int eMes, int eDia) {
        mAno = eAno;
        mMes = eMes;
        mDia = eDia;
        mDiaSemanal = DiaSemanal.Domingo;
    }

    public DiaSemanal getDiaSemanal() {
        return mDiaSemanal;
    }

    public int getMes() {
        return mMes;
    }

    public int getDia() {
        return mDia;
    }

    public int getAno() {
        return mAno;
    }

    public String toDia() {
        String v = "";

        if (mDiaSemanal == DiaSemanal.Segunda) {
            v = "SEGUNDA";
        } else if (mDiaSemanal == DiaSemanal.Terca) {
            v = "TERCA";
        } else if (mDiaSemanal == DiaSemanal.Quarta) {
            v = "QUARTA";
        } else if (mDiaSemanal == DiaSemanal.Quinta) {
            v = "QUINTA";
        } else if (mDiaSemanal == DiaSemanal.Sexta) {
            v = "SEXTA";
        }

        return v;
    }

    public String getTempo() {


        String sAno = String.valueOf(mAno);
        String sMes = String.valueOf(mMes);
        String sDia = String.valueOf(mDia);

        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }

        return sAno + "_" + sMes + "_" + sDia;
    }

    public String getTempoLegivel() {


        String sAno = String.valueOf(mAno);
        String sMes = String.valueOf(mMes);
        String sDia = String.valueOf(mDia);

        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }

        return sDia + "/" + sMes + "/" + sAno;
    }


    public String getDiaMes() {


        String sAno = String.valueOf(mAno);
        String sMes = String.valueOf(mMes);
        String sDia = String.valueOf(mDia);

        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }

        return sDia + " do " + sMes;
    }

    public String getDiaMesLegivel() {

        String sMes = String.valueOf(mMes);
        String sDia = String.valueOf(mDia);

        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }

        return sDia + " do " + Calendario.getMesPrefixo(sMes);
    }

    public String getFluxo() {

        String sDia = String.valueOf(mDia);
        String sMes = String.valueOf(mMes);

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }
        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        return sDia + "/" + sMes + "/" + mAno;
    }

    public String getFluxoSemAno() {

        String sDia = String.valueOf(mDia);
        String sMes = String.valueOf(mMes);

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }
        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        return sDia + "/" + sMes;
    }


    public String getTitulo() {

        String sDia = String.valueOf(mDia);
        String sMes = String.valueOf(mMes);

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }
        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        return sDia + "/" + sMes + "/" + mAno + " :: " + mDiaSemanal.toString();
    }

    public static String organizarData(String data) {

        if (data.length() == 10) {

            String dia = String.valueOf(data.charAt(8)) + String.valueOf(data.charAt(9));
            String mes = String.valueOf(data.charAt(5)) + String.valueOf(data.charAt(6));
            String ano = String.valueOf(data.charAt(0)) + String.valueOf(data.charAt(1)) + String.valueOf(data.charAt(2)) + String.valueOf(data.charAt(3));

            data = dia + "/" + mes + "/" + ano;

        }
        return data;
    }

    public boolean isIgual(String sData) {
        return getFluxo().contentEquals(sData);
    }

    public boolean isIgual(Data eData) {
        if (mDia == eData.getDia() && mMes == eData.getMes() && mAno == eData.getAno()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMenor(Data outra) {
        boolean ret = false;

        if (mAno < outra.getAno()) {
            ret = true;
        } else if (mAno == outra.getAno()) {

            if (mMes < outra.getMes()) {
                ret = true;
            } else if (mMes == outra.getMes()) {

                if (mDia < outra.getDia()) {
                    ret = true;
                }

            }

        }

        return ret;
    }

    public boolean isMaior(Data outra) {
        boolean ret = false;

        if (mAno > outra.getAno()) {
            ret = true;
        } else if (mAno == outra.getAno()) {

            if (mMes > outra.getMes()) {
                ret = true;
            } else if (mMes == outra.getMes()) {

                if (mDia > outra.getDia()) {
                    ret = true;
                }

            }

        }

        return ret;
    }

    public boolean isMenorOuIgual(Data outra) {
        boolean ret = isIgual(outra);

        if (!ret) {
            ret = isMenor(outra);
        }

        return ret;
    }


    public static Data toData(String data) {

        Data ret = new Data(0, 0, 0, DiaSemanal.Domingo);

        if (data.length() == 10 && data.contains("_")) {

            if (String.valueOf(data.charAt(2)).contentEquals("_")) {

                // System.out.println("Tentar Forma 1.1 -->> " + data);

                String dia = String.valueOf(data.charAt(0)) + String.valueOf(data.charAt(1));
                String mes = String.valueOf(data.charAt(3)) + String.valueOf(data.charAt(4));
                String ano = String.valueOf(data.charAt(6)) + String.valueOf(data.charAt(7)) + String.valueOf(data.charAt(8)) + String.valueOf(data.charAt(9));

                ret = new Data(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), DiaSemanal.Domingo);

            } else if (String.valueOf(data.charAt(4)).contentEquals("_")) {

                //  System.out.println("Tentar Forma 1.2 -->> " + data);

                String dia = String.valueOf(data.charAt(8)) + String.valueOf(data.charAt(9));
                String mes = String.valueOf(data.charAt(5)) + String.valueOf(data.charAt(6));
                String ano = String.valueOf(data.charAt(0)) + String.valueOf(data.charAt(1)) + String.valueOf(data.charAt(2)) + String.valueOf(data.charAt(3));

                ret = new Data(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), DiaSemanal.Domingo);

            }

        } else if (data.length() == 10 && data.contains("/")) {


            if (String.valueOf(data.charAt(2)).contentEquals("/")) {

                //System.out.println("Tentar Forma 2.1 -->> " + data);

                String dia = String.valueOf(data.charAt(0)) + String.valueOf(data.charAt(1));
                String mes = String.valueOf(data.charAt(3)) + String.valueOf(data.charAt(4));
                String ano = String.valueOf(data.charAt(6)) + String.valueOf(data.charAt(7)) + String.valueOf(data.charAt(8)) + String.valueOf(data.charAt(9));

                ret = new Data(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), DiaSemanal.Domingo);

            } else if (String.valueOf(data.charAt(4)).contentEquals("/")) {

                // System.out.println("Tentar Forma 2.2 -->> " + data);

                String dia = String.valueOf(data.charAt(8)) + String.valueOf(data.charAt(9));
                String mes = String.valueOf(data.charAt(5)) + String.valueOf(data.charAt(6));
                String ano = String.valueOf(data.charAt(0)) + String.valueOf(data.charAt(1)) + String.valueOf(data.charAt(2)) + String.valueOf(data.charAt(3));

                ret = new Data(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), DiaSemanal.Domingo);

            }


        }
        return ret;
    }


    public Data getCopia(){
        return new Data(mAno,mMes,mDia,mDiaSemanal);
    }

}
