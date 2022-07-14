package apps.app_khronos;

public class Comando {

    private String mComando;
    private String mDescricao;

    public Comando(String eComando, String eDescricao) {
        mComando = eComando;
        mDescricao = eDescricao;
    }

    public String getComando() {
        return mComando;
    }

    public String getDescricao() {
        return mDescricao;
    }

    public boolean isIgual(String c) {
        return mComando.contentEquals(c);
    }
    public boolean comecaCom(String c) {
        return mComando.startsWith(c);
    }

}
