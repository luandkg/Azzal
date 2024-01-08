package libs.pesquisador.pesquisa;

public class AnaliseDePesquisa {

    public String mGrupo;
    private int mQuantidade;

    public AnaliseDePesquisa(String eGrupo, int eQuantidade) {
        mGrupo = eGrupo;
        mQuantidade = eQuantidade;
    }

    public void aumentar(int eQuantidade) {
        mQuantidade += eQuantidade;
    }

    public String getGrupo() {
        return mGrupo;
    }

    public int getQuantidade() {
        return mQuantidade;
    }

}
