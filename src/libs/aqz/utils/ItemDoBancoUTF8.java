package libs.aqz.utils;

import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoPrimaria;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Matematica;

import java.nio.charset.StandardCharsets;

public class ItemDoBancoUTF8 {

    private Arquivador mArquivador;
    private ParticaoPrimaria mParticaoPrimaria;

    private long mPonteiro;
    private long mPonteiroDados;

    private boolean mIsDoBanco;

    public ItemDoBancoUTF8(Arquivador eArquivador, ParticaoPrimaria eParticaoPrimaria, long ePonteiro, long ePonteiroDados) {
        mArquivador = eArquivador;
        mParticaoPrimaria = eParticaoPrimaria;
        mPonteiro = ePonteiro;
        mPonteiroDados = ePonteiroDados;
        mIsDoBanco = true;
    }

    public ItemDoBancoUTF8(Arquivador eArquivador, long ePonteiro, long ePonteiroDados) {
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


    public ParticaoPrimaria getBanco() {
        return mParticaoPrimaria;
    }





    public long getPonteiroDados(){return mPonteiroDados;}


    public Entidade toEntidadeUTF8(){
        return ENTT.PARSER_ENTIDADE( lerTextoUTF8());
    }

    public void atualizarUTF8(Entidade entidade) {
        atualizarUTF8(ENTT.TO_DOCUMENTO(entidade));
    }

    public void atualizarUTF8(String conteudo) {

        byte[] bytes = conteudo.getBytes(StandardCharsets.UTF_8);

        if (bytes.length >= (Matematica.KB(10) - 100)) {
            throw new RuntimeException("AQZ ERRO : O item é maior que 10 Kb !");
        }

        mArquivador.setPonteiro(mPonteiroDados);

        mArquivador.set_u32(bytes.length);
        mArquivador.set_u8_vector(bytes);

    }


    public String lerTextoUTF8() {
        mArquivador.setPonteiro(mPonteiroDados);

        int tam = mArquivador.get_u32();
        int contando = 0;
        int limite = Armazenador.TAMANHO_ITEM;

        byte[] bytes= new byte[tam];


        while (contando<tam && contando<limite) {

            byte valor = mArquivador.get();
            bytes[contando]=valor;

            contando += 1;

        }


        return new String(bytes,StandardCharsets.UTF_8);
    }
}

