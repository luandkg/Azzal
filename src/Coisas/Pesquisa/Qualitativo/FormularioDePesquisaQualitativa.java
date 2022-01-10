package Coisas.Pesquisa.Qualitativo;

public class FormularioDePesquisaQualitativa {

    private String mNome;

    private ItemFormularioQualitativo mPergunta1;
    private ItemFormularioQualitativo mPergunta2;

    public FormularioDePesquisaQualitativa(String eNome) {

        mNome = eNome;

        mPergunta1 = new ItemFormularioQualitativo();
        mPergunta2 = new ItemFormularioQualitativo();

    }

    public String getNome() {
        return mNome;
    }

    public ItemFormularioQualitativo getItem1() {
        return mPergunta1;
    }

    public ItemFormularioQualitativo getItem2() {
        return mPergunta2;
    }

}
