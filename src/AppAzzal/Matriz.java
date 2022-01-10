package AppAzzal;

public class Matriz {

    private String[] mDados;
    private int eTam;
    private int mLinhas;
    private int mColunas;

    public Matriz(int eColunas, int eLinhas) {

        eTam = eColunas * eLinhas;
        mLinhas = eLinhas;
        mColunas = eColunas;

        mDados = new String[eTam];

        for (int i = 0; i < eTam; i++) {
            mDados[i] = " ";
        }

    }


    public int getLinhas() {
        return mLinhas;
    }

    public int getColunas() {
        return mColunas;
    }

    public void setLinha(int eY, String eTexto) {

        int i = 0;
        int o = eTexto.length();
        while (i < o) {
            String c = String.valueOf(eTexto.charAt(i));
            set(i, eY, c);
            i += 1;
        }

    }

    public void setLinha(int eX, int eY, String eTexto) {

        int i = 0;
        int o = eTexto.length();
        while (i < o) {
            String c = String.valueOf(eTexto.charAt(i));
            set(eX + i, eY, c);
            i += 1;
        }

    }


    public void set(int eX, int eY, String eTexto) {

        int i = eX + (eY * mColunas);

        if (i >= 0 && i < eTam) {
            mDados[i] = eTexto;
        }


    }


    public String get(int eX, int eY) {

        int i = eX + (eY * mColunas);
        String ret = "";

        if (i >= 0 && i < eTam) {
            ret = mDados[i];
        }

        return ret;
    }

    public void descer(String eNovaLinha) {

        for (int y = mLinhas - 1; y >= 0; y--) {

            int y2 = y - 1;

            if (y2 < mLinhas - 1) {

                for (int x = 0; x < mColunas; x++) {

                    if (y2<0){
                        set(x, y,String.valueOf( eNovaLinha.charAt(x)));
                    }else{
                        set(x, y, get(x, y2));
                    }

                 //   System.out.println("Descendo - " + x + " : " + y + " com " + x + " : " + y2);
                }

            }

        }

    }


}
