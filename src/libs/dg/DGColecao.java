package libs.dg;

import libs.Arquivos.TX;
import libs.Luan.Opcional;

import java.util.ArrayList;

public class DGColecao {

    private DG mDG;
    private long mPonteiroCabecalho;
    private long mPonteiroDados;

    private boolean isCached;
    private String mNome;

    public DGColecao(DG eDG, long ePonteiroCabecalho, long ePonteiroDados) {
        mDG = eDG;
        mPonteiroCabecalho = ePonteiroCabecalho;
        mPonteiroDados = ePonteiroDados;

        mNome = "";
        isCached = false;

    }

    public String getNome() {

        if (!isCached) {
            TX eTX = new TX();
            mDG.getArquivador().setPonteiro(mPonteiroCabecalho + 1);
            mNome = eTX.lerFluxoLimitado(mDG.getArquivador(), 100);
            isCached = true;
        }

        return mNome;

    }

    public int getPaginasContagem() {
        int i = 0;

        // mDG.getArquivador().setPonteiro(mPonteiroDados);

        int indo = 0;

        for (int v = 0; v < DG.PAGINAS; v++) {

            mDG.getArquivador().setPonteiro(mPonteiroDados + indo);

            long valor = mDG.getArquivador().readLong();
            if (valor > 0) {
                i += 1;
            }

            indo += 8;
            // System.out.println("PG :: " + v + " -->> " + valor);
        }

        return i;
    }

    public void adicionar(DGObjeto e) {

        System.out.println("chave :: " + e.deveConstruirChave());

        if (e.deveConstruirChave()) {
            e.identifique_comeco(e.getIndice()).setInteiro(mDG.indicePara(getNome()));
        }

        long pagina = -1;
        boolean pagina_existente = false;

        long ponteiro_ir = mPonteiroDados;

        long pagina_id = -1;

        for (int proc_pagina = 0; proc_pagina < DG.PAGINAS; proc_pagina++) {

            mDG.getArquivador().setPonteiro(ponteiro_ir);

            long valor = mDG.getArquivador().readLong();
            if (valor > 0) {

                // System.out.println("PAGINA :: " + proc_pagina + " -->> " + valor);

                DGPagina paginaCorrente = new DGPagina(mDG, proc_pagina, valor);
                if (paginaCorrente.temVazio()) {

                    // System.out.println("\t >>> TEM VAZIO");

                    pagina_id = proc_pagina;
                    pagina = valor;
                    pagina_existente = true;

                    // System.out.println("Tem pagina disponivel :: " + pagina);

                    break;
                }

                // System.out.println("\t >>> PROXIMO");

            }

            ponteiro_ir += 8;

        }

        if (pagina == -1) {

            // System.out.println("Precisa de nova pagina...");

            long p3 = mDG.getArquivador().getLength();

            mDG.expandir(p3, DG.TAMANHO_PAGINA, 0);

            long criado = 0;

            for (int proc_pagina = 0; proc_pagina < DG.PAGINAS; proc_pagina++) {

                mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_pagina * 8));

                long valor = mDG.getArquivador().readLong();
                if (valor == 0) {

                    mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_pagina * 8));
                    mDG.getArquivador().writeLong(p3);

                    // System.out.println("NOVA PAGINA :: " + proc_pagina + " -->> " + p2);
                    pagina = p3;
                    pagina_id = proc_pagina;

                    break;
                }

            }

            // pagina = criarPagina();
            pagina_existente = true;
        }

        if (pagina_existente && pagina >= 0) {

            DGPagina paginaCorrente = new DGPagina(mDG, pagina_id, pagina);
            paginaCorrente.guardar(e);

        } else {
            System.out.println("DG [ ERRO ] -->> Nao foi possivel criar uma nova PAGINA : " + this.mPonteiroCabecalho);
        }

    }

    public int getVaziosContagem() {

        int contando = 0;

        for (int proc_pagina = 0; proc_pagina < DG.PAGINAS; proc_pagina++) {

            mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_pagina * 8));

            long pagina_ref = mDG.getArquivador().readLong();
            if (pagina_ref > 0) {

                // System.out.println("PAGINA :: " + proc_pagina + " -->> " + pagina_ref);
                DGPagina paginaCorrente = new DGPagina(mDG, proc_pagina, pagina_ref);
                contando += paginaCorrente.getVaziosContagem();

            }

            // indo += 8;
        }

        return contando;
    }

    public int getItensContagem() {

        int contando = 0;

        for (int proc_pagina = 0; proc_pagina < DG.PAGINAS; proc_pagina++) {

            mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_pagina * 8));

            long slot = mDG.getArquivador().readLong();
            if (slot > 0) {

                // System.out.println("SLOT :: " + proc_slot + " -->> " + slot);
                DGPagina paginaCorrente = new DGPagina(mDG, proc_pagina, slot);
                contando += paginaCorrente.contar();

            }

            // indo += 8;
        }

        return contando;
    }

    public ArrayList<DGItem> getItens() {

        ArrayList<DGItem> ret = new ArrayList<DGItem>();

        for (int proc_pagina = 0; proc_pagina < DG.PAGINAS; proc_pagina++) {

            mDG.getArquivador().setPonteiro(mPonteiroDados + (proc_pagina * 8));

            long pagina_ref = mDG.getArquivador().readLong();

            if (pagina_ref > 0) {

                // System.out.println("SLOT :: " + proc_slot + " -->> " + slot);
                DGPagina paginaCorrente = new DGPagina(mDG, proc_pagina, pagina_ref);
                ret.addAll(paginaCorrente.getItens());

            }

            // indo += 8;
        }

        return ret;
    }

    public void mostrar_tudo() {

        for (DGItem item : getItens()) {
            System.out.println("\t\t :: " + item.getValor().replace("\n", ""));
        }

    }

}
