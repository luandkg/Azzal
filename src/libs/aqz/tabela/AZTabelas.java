package libs.aqz.tabela;

public class AZTabelas {

    private AZTabelasInternamente mAZTabelasInternamente;

    public AZTabelas(String eArquivo) {

        mAZTabelasInternamente = new AZTabelasInternamente(eArquivo);

    }


    public AQZTabela tabela_orgarnizar_e_obter(String nome_tabela) {
        return new AQZTabela(nome_tabela,mAZTabelasInternamente.esquemas_obter(nome_tabela),mAZTabelasInternamente.tabela_orgarnizar_e_obter(nome_tabela));
    }


    public void fechar() {
        mAZTabelasInternamente.fechar();
    }

}
