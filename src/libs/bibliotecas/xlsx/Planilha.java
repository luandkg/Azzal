package libs.bibliotecas.xlsx;


import libs.luan.Lista;

public class Planilha {

    private String mTitulo;
    private Lista<PlanilhaLinha> mLinhas;

    public Planilha() {
        mTitulo = "";
        mLinhas = new Lista<PlanilhaLinha>();
    }

    public String getTitulo() {
        return mTitulo;
    }

    public void setTitulo(String t) {
        mTitulo = t;
    }


    public void adicionar(PlanilhaLinha linha) {
        mLinhas.adicionar(linha);
    }

    public Lista<PlanilhaLinha> getLinhas() {
        return mLinhas;
    }


    public int maxLinhas() {
        return mLinhas.getQuantidade();
    }

    public int maxColunas() {
        int c = 0;
        for (PlanilhaLinha l : mLinhas) {
            if (l.maxColunas() > c) {
                c = l.maxColunas();
            }
        }
        return c;
    }

}
