package libs.aqz.extincao;


import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoEmExtincao;
import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Matematica;

import java.nio.charset.StandardCharsets;

public class ItemDoBancoEmExtincao {

    private Arquivador mArquivador;
    private ParticaoEmExtincao mParticaoEmExtincao;

    private long mPonteiro;
    private long mPonteiroDados;

    private boolean mIsDoBanco;

    public ItemDoBancoEmExtincao(Arquivador eArquivador, ParticaoEmExtincao eParticaoEmExtincao, long ePonteiro, long ePonteiroDados) {
        mArquivador = eArquivador;
        mParticaoEmExtincao = eParticaoEmExtincao;
        mPonteiro = ePonteiro;
        mPonteiroDados = ePonteiroDados;
        mIsDoBanco = true;
    }

    public ItemDoBancoEmExtincao(Arquivador eArquivador, long ePonteiro, long ePonteiroDados) {
        mArquivador = eArquivador;
        mPonteiro = ePonteiro;
        mPonteiroDados = ePonteiroDados;
        mIsDoBanco = false;
    }

    public boolean existe() {
        mArquivador.setPonteiro(mPonteiro);
        return mArquivador.get_u8() == Armazenador.ITEM_ALOCADO_OCUPADO;
    }

    public boolean isDoBanco() {
        return mIsDoBanco;
    }

    public long getPonteiro() {
        return mPonteiro;
    }


    public long getPonteiroDados() {
        return mPonteiroDados;
    }


    public ParticaoEmExtincao getBanco() {
        return mParticaoEmExtincao;
    }

    public String lerTexto() {
        mArquivador.setPonteiro(mPonteiroDados);
        TX eTX = new TX();
        return eTX.lerFluxoLimitado(mArquivador, Armazenador.TAMANHO_ITEM);
    }

    public String lerTextoLinearizado() {
        mArquivador.setPonteiro(mPonteiroDados);
        TX eTX = new TX();
        return eTX.lerFluxoLimitado(mArquivador, Armazenador.TAMANHO_ITEM).replace("\n", "");
    }

    public void atualizar(Entidade e) {
        atualizar(ENTT.TO_DOCUMENTO(e));
    }

    public void atualizar(String conteudo) {

        if (conteudo.getBytes(StandardCharsets.UTF_8).length >= (Matematica.KB(10) - 100)) {
            throw new RuntimeException("AQZ ERRO : O item é maior que 10 Kb !");
        }

        mArquivador.setPonteiro(mPonteiroDados);
        mArquivador.set_u8_array(TX.toListBytes(conteudo));
    }
}

