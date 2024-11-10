package libs.aqz.utils;

import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoPrimaria;
import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Matematica;

import java.nio.charset.StandardCharsets;

public class ItemDoBancoTX {

    private Arquivador mArquivador;
    private ParticaoPrimaria mParticaoPrimaria;

    private long mPonteiro;
    private long mPonteiroDados;

    private boolean mIsDoBanco;

    public ItemDoBancoTX(Arquivador eArquivador, ParticaoPrimaria eParticaoPrimaria, long ePonteiro, long ePonteiroDados) {
        mArquivador = eArquivador;
        mParticaoPrimaria = eParticaoPrimaria;
        mPonteiro = ePonteiro;
        mPonteiroDados = ePonteiroDados;
        mIsDoBanco = true;
    }

    public ItemDoBancoTX(Arquivador eArquivador, long ePonteiro, long ePonteiroDados) {
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
        return ENTT.PARSER_ENTIDADE( lerTextoTX());
    }

    public void atualizarTX(Entidade entidade) {
        atualizarTX(ENTT.TO_DOCUMENTO(entidade));
    }

    public void atualizarTX(String conteudo) {

        Lista<Byte> bytes = TX.toListBytes(conteudo);

        if (bytes.getQuantidade() >= (Matematica.KB(10) - 100)) {
            throw new RuntimeException("AQZ ERRO : O item é maior que 10 Kb !");
        }

        mArquivador.setPonteiro(mPonteiroDados);

        mArquivador.set_u32(bytes.getQuantidade());
        mArquivador.set_u8_lista(bytes);

    }


    public String lerTextoTX() {
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


        return TX.ler_vetor(bytes);
    }
}

