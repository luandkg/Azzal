package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.ArmazemIndiceSumario;
import libs.fazendario.ItemAlocado;
import libs.fazendario.SuperBloco;
import libs.luan.fmt;
import libs.tronarko.Tronarko;

public class ZettaArquivo {

    private ZettaPasta mPasta;
    private ItemAlocado mItem;
    private Entidade mEntidade;
    private ArmazemIndiceSumario mIndice;

    private boolean mRemovido = false;

    public ZettaArquivo(ZettaPasta ePasta,ArmazemIndiceSumario eIndice, ItemAlocado eItem, Entidade eEntidade) {
        mPasta = ePasta;
        mIndice=eIndice;
        mItem = eItem;
        mEntidade = eEntidade;
    }

    public String getNome() {
        return mEntidade.at("Nome");
    }

    public boolean isNome(String eNome) {
        return mEntidade.is("Nome", eNome);
    }

    public String getTamanhoFormatado(){
        return mEntidade.at("TamanhoFormatado");
    }

    public void atualizar_dados(byte[] bytes) {

        if (!mRemovido) {
            SuperBloco superbloco = mPasta.getSuperBloco(mEntidade);
            superbloco.limpar_dados();

            superbloco.guardar(bytes);


            mEntidade.at("Tamanho", bytes.length);
            mEntidade.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(bytes.length));

            mEntidade.at("DDA", Tronarko.getTronAgora().getTextoZerado());
            mEntidade.at("DDM", Tronarko.getTronAgora().getTextoZerado());

            mItem.atualizarUTF8(ENTT.TO_DOCUMENTO(mEntidade));
        } else {
            throw new RuntimeException("Arquivo removido !");
        }

    }

    public void expandir(byte[] bytes) {

        if (!mRemovido) {
            SuperBloco superbloco = mPasta.getSuperBloco(mEntidade);

            superbloco.expandir(bytes);

            mEntidade.at("Tamanho", superbloco.getTamanhoEscrito());
            mEntidade.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(superbloco.getTamanhoEscrito()));
            mEntidade.at("DDA", Tronarko.getTronAgora().getTextoZerado());
            mEntidade.at("DDM", Tronarko.getTronAgora().getTextoZerado());

            mItem.atualizarUTF8(ENTT.TO_DOCUMENTO(mEntidade));
        } else {
            throw new RuntimeException("Arquivo removido !");
        }

    }


    public byte[] getBytes() {
        if (!mRemovido) {

            mEntidade.at("DDL",Tronarko.getTronAgora().getTextoZerado());
            mItem.atualizarUTF8(ENTT.TO_DOCUMENTO(mEntidade));

            SuperBloco superbloco = mPasta.getSuperBloco(mEntidade);

            byte[] todos_bytes = superbloco.getBytes();
            return todos_bytes;
        } else {
            throw new RuntimeException("Arquivo removido !");
        }
    }

    public void remover() {
        if (!mRemovido) {
            mRemovido = true;

            SuperBloco superbloco = mPasta.getSuperBloco(mEntidade);
            superbloco.limpar_dados();
            superbloco.remover_a_si_mesmo();

            mIndice.remover(mEntidade.atLong("@ID"));

            mItem.remover();
        }
    }

}
