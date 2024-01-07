package libs.rho_benchmark;

import libs.luan.Texto;

public class RhoLog {

    private String mConteudo;

    public RhoLog() {
        mConteudo = "";
    }

    public void adicionar(String log) {
        mConteudo += log + "\n";
    }

    public void titulo(String eTitulo) {
        mConteudo += "\n";
        mConteudo += "---------------------- " + eTitulo + " ----------------------" + "\n";
        mConteudo += "\n";
    }

    public void salvar(String eArquivo) {
        Texto.arquivo_escrever(eArquivo, mConteudo);
    }


}
