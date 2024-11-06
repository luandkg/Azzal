package libs.armazenador;

import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;

import java.nio.charset.StandardCharsets;

public class ItemDoBancoUTF8 {

    private Arquivador mArquivador;
    private Banco mBanco;

    private long mPonteiro;
    private long mPonteiroDados;

    private boolean mIsDoBanco;

    public ItemDoBancoUTF8(Arquivador eArquivador, Banco eBanco, long ePonteiro, long ePonteiroDados) {
        mArquivador = eArquivador;
        mBanco = eBanco;
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


    public Banco getBanco() {
        return mBanco;
    }





    public long getPonteiroDados(){return mPonteiroDados;}


    public Entidade toEntidadeUTF8(){
        return ENTT.PARSER_ENTIDADE( lerTextoUTF8());
    }

    public void atualizarUTF8(Entidade entidade) {
        atualizarUTF8(ENTT.TO_DOCUMENTO(entidade));
    }

    public void atualizarUTF8(String conteudo) {
        mArquivador.setPonteiro(mPonteiroDados);


        byte[] bytes = conteudo.getBytes(StandardCharsets.UTF_8);

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

