package libs.tempo;

public class Data {

    private int mAno;
    private int mMes;
    private int mDia;
    private DiaSemanal mDiaSemanal;
    private int mSemana;

    public Data(int eAno, int eMes, int eDia, DiaSemanal eDiaSemanal) {
        mAno = eAno;
        mMes = eMes;
        mDia = eDia;
        mDiaSemanal = eDiaSemanal;
        mSemana = 0;
    }

    public Data(int eAno, int eMes, int eDia) {
        mAno = eAno;
        mMes = eMes;
        mDia = eDia;
        mDiaSemanal = DiaSemanal.Domingo;
        mSemana = 0;
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


    public Data getCopia() {
        return new Data(mAno, mMes, mDia, mDiaSemanal);
    }

    public void setDiaSemanal(DiaSemanal ds) {
        mDiaSemanal = ds;
    }

    public int getSemana() {
        return mSemana;
    }

    public void setSemana(int s) {
        mSemana = s;
    }


    public String getMesZerado() {
        String m = String.valueOf(mMes);
        if (m.length() == 1) {
            m = "0" + m;
        }
        return m;
    }

    public String getDiaZerado() {
        String m = String.valueOf(mDia);
        if (m.length() == 1) {
            m = "0" + m;
        }
        return m;
    }

    public int getAfastadoDoInicioDeDomingo() {
        int coluna_dia = 0;
        if (this.getDiaSemanal() == DiaSemanal.Domingo) {
            coluna_dia = 0;
        } else if (this.getDiaSemanal() == DiaSemanal.Segunda) {
            coluna_dia = 1;
        } else if (this.getDiaSemanal() == DiaSemanal.Terca) {
            coluna_dia = 2;
        } else if (this.getDiaSemanal() == DiaSemanal.Quarta) {
            coluna_dia = 3;
        } else if (this.getDiaSemanal() == DiaSemanal.Quinta) {
            coluna_dia = 4;
        } else if (this.getDiaSemanal() == DiaSemanal.Sexta) {
            coluna_dia = 5;
        } else if (this.getDiaSemanal() == DiaSemanal.Sabado) {
            coluna_dia = 6;
        } else {
            coluna_dia = 7;
        }

        return coluna_dia;
    }

    public String getDiaSemanalLegivel() {
        String ret = "";

        if (this.getDiaSemanal() == DiaSemanal.Domingo) {
            ret = "Domingo";
        } else if (this.getDiaSemanal() == DiaSemanal.Segunda) {
            ret = "Segunda";
        } else if (this.getDiaSemanal() == DiaSemanal.Terca) {
            ret = "Terça";
        } else if (this.getDiaSemanal() == DiaSemanal.Quarta) {
            ret = "Quarta";
        } else if (this.getDiaSemanal() == DiaSemanal.Quinta) {
            ret = "Quinta";
        } else if (this.getDiaSemanal() == DiaSemanal.Sexta) {
            ret = "Sexta";
        } else if (this.getDiaSemanal() == DiaSemanal.Sabado) {
            ret = "Sábado";
        }

        return ret;
    }

    public boolean isIgualAnoEMes(int eAno, int eMes) {
        if (this.getAno() == eAno) {
            if (this.getMes() == eMes) {
                return true;
            }
        }
        return false;
    }

    public String getTempoInverso() {

        String sDia = String.valueOf(mDia);
        String sMes = String.valueOf(mMes);

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }
        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        return this.getAno() + "_" + sMes + "_" + sDia;

    }


    public String getTempoLegivelComDiaDaSemana() {


        String sAno = String.valueOf(mAno);
        String sMes = String.valueOf(mMes);
        String sDia = String.valueOf(mDia);

        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }

        return sDia + "/" + sMes + "/" + sAno + " - " + mDiaSemanal.toString();
    }

    public boolean isDiferente(Data eData) {
        if (mDia == eData.getDia() && mMes == eData.getMes() && mAno == eData.getAno()) {
            return false;
        } else {
            return true;
        }
    }


    public boolean isMaiorOuIgual(Data outra) {
        boolean ret = isIgual(outra);

        if (!ret) {
            ret = isMaior(outra);
        }

        return ret;
    }

}
