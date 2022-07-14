package apps.AppKhronos;

public class Span {

    private String mTexto;
    private boolean mEspecial;

    public Span(String eTexto) {
        mTexto = eTexto;
        mEspecial=false;
    }

    public void especializar(){mEspecial=true;}

    public String getTexto() {
        return mTexto;
    }

    public boolean isEspecial(){return mEspecial;}
}
