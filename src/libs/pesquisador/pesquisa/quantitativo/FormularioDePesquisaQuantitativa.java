package libs.pesquisador.pesquisa.quantitativo;

public class FormularioDePesquisaQuantitativa {

    private String mNome;

    private ItemFormularioQuantitativo mPergunta1;
    private ItemFormularioQuantitativo mPergunta2;

    public FormularioDePesquisaQuantitativa(String eNome) {

        mNome = eNome;

        mPergunta1 = new ItemFormularioQuantitativo();
        mPergunta2 = new ItemFormularioQuantitativo();

    }

    public String getNome() {
        return mNome;
    }

    public ItemFormularioQuantitativo getItem1() {
        return mPergunta1;
    }

    public ItemFormularioQuantitativo getItem2() {
        return mPergunta2;
    }


}
