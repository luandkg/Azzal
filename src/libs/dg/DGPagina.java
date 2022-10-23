package libs.dg;

import libs.arquivos.TX;

import java.util.ArrayList;

public class DGPagina {

    private DG mDG;
    private long mPonteiroDados;

    private final int VAZIO = 0;
    private final int OCUPADO = 1;

    private long mPaginaID;

    public DGPagina(DG eDG, long ePaginaID, long ePonteiroDados) {
        mDG = eDG;
        mPaginaID = ePaginaID;
        mPonteiroDados = ePonteiroDados;
    }

    public boolean temVazio() {
        boolean ret = false;

        for (int proc_item = 0; proc_item < DG.ITENS_POR_PAGINA; proc_item++) {

            long v = mPonteiroDados + (proc_item * DG.BLOCO);

            mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_item * DG.BLOCO));

            int item_vazio = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

            // System.out.println("Slot " + proc_slot + " -->> tipo : " + slot_tipo);

            if (item_vazio == VAZIO) {
                // System.out.println("Item Vazio " + proc_item + " :: " + v);
                ret = true;
                break;
            }

        }

        return ret;
    }

    public void guardar(DGObjeto e) {

        if (e.getTamanho() < DG.BLOCO - 5) {

            TX eTX = new TX();

            boolean inserido = false;

            for (int proc_item = 0; proc_item < DG.ITENS_POR_PAGINA; proc_item++) {

                long v = mPonteiroDados + (proc_item * DG.BLOCO);

                mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_item * DG.BLOCO));

                int item_vazio = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

                // System.out.println("Verificando :: " + proc_item);

                if (item_vazio == VAZIO) {

                    long eLocalAqui = mPonteiroDados + (proc_item * DG.BLOCO);
                    e.identifique("RefPtr").setLong(eLocalAqui);

                    // System.out.println("Item inserindo " + mPaginaID + " :: " + proc_item + " ::
                    // " + v + " -->> tipo : " + e.toString().replace("\n", ""));

                    mDG.getArquivador().setPonteiro(eLocalAqui);
                    mDG.getArquivador().writeByte((byte) OCUPADO);
                    eTX.escreverFluxo(e.toString(), mDG.getArquivador());

                    inserido = true;
                    break;
                }

            }

            if (!inserido) {
                System.out.println("DG [ ERRO ] Item nao inserido :: " + mPonteiroDados);
            }

        } else {
            System.out.println("DG [ ERRO ] Objeto muito grande :: " + e.toString());
        }

    }

    public int getVaziosContagem() {

        int contando_itens = 0;

        for (int proc_item = 0; proc_item < DG.ITENS_POR_PAGINA; proc_item++) {

            mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_item * DG.BLOCO));

            int esta_vazio = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

            if (esta_vazio == VAZIO) {
                contando_itens += 1;
            }

        }

        return contando_itens;

    }

    public int contar() {

        int contando_itens = 0;

        for (int proc_item = 0; proc_item < DG.ITENS_POR_PAGINA; proc_item++) {

            mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_item * DG.BLOCO));

            int esta_vazio = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

            if (esta_vazio != VAZIO) {
                contando_itens += 1;
            }

        }

        return contando_itens;

    }

    public ArrayList<DGItem> getItens() {

        ArrayList<DGItem> ret = new ArrayList<DGItem>();

        for (int proc_item = 0; proc_item < DG.ITENS_POR_PAGINA; proc_item++) {

            mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_item * DG.BLOCO));

            int esta_vazio = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

            if (esta_vazio != VAZIO) {
                ret.add(new DGItem(mDG, mPonteiroDados + (proc_item * DG.BLOCO)));
            }

        }

        return ret;

    }

}
