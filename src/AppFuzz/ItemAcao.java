package AppFuzz;

public class ItemAcao {

    private String mTexto;
    private boolean mDisponibilidade;

    public ItemAcao(String eTexto,boolean eDisponibilidade){
        mTexto=eTexto;
        mDisponibilidade=eDisponibilidade;
    }

    public String getTexto(){return mTexto;}
    public boolean isDisponivel(){return mDisponibilidade;}
}
