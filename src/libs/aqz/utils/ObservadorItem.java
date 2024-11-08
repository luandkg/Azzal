package libs.aqz.utils;

import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;
import libs.luan.fmt;

public class ObservadorItem {

    private String mArquivo;
    private long mPonteiroDados;
    private Entidade mEntidade;

    public ObservadorItem(String eArquivo, long ePonteiroDados, Entidade eEntidade) {
        mArquivo = eArquivo;
        mPonteiroDados = ePonteiroDados;
        mEntidade = eEntidade;
    }

    public void atualizar() {

        Lista<Byte> bytes = TX.toListBytes(ENTT.TO_DOCUMENTO(mEntidade));

        if (bytes.getQuantidade() >= (Matematica.KB(10) - 100)) {
            throw new RuntimeException("AQZ ERRO : O item é maior que 10 Kb !");
        }

        Arquivador mArquivador = new Arquivador(mArquivo);

        mArquivador.setPonteiro(mPonteiroDados);
        mArquivador.set_u8_array(bytes);

        mArquivador.encerrar();
    }


    public void exibir() {

        fmt.print(">> Arquivo       : {}", mArquivo);
        fmt.print(">> Observador em : {}", mPonteiroDados);
        fmt.print(">> Interno       : {}", Strings.LINEARIZAR(ENTT.TO_DOCUMENTO(mEntidade)));

    }


    public void at(String att_nome, String att_valor) {
        mEntidade.at(att_nome, att_valor);
    }

    public void at(String att_nome, int att_valor) {
        mEntidade.atInt(att_nome, att_valor);
    }

    public void at(String att_nome, double att_valor) {
        mEntidade.atDouble(att_nome, att_valor);
    }

    public void at(String att_nome, long att_valor) {
        mEntidade.atLong(att_nome, att_valor);
    }

    public String at(String att_nome) {
        return mEntidade.at(att_nome);
    }

    public int atInt(String att_nome) {
        return mEntidade.atInt(att_nome);
    }

    public long atLong(String att_nome) {
        return mEntidade.atLong(att_nome);
    }


    public int atIntOuPadrao(String att_nome,int valor) {
        return mEntidade.atIntOuPadrao(att_nome,valor);
    }
}
