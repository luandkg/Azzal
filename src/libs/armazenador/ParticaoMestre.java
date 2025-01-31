package libs.armazenador;


import libs.aqz.utils.ItemDoBancoTX;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

import java.nio.charset.StandardCharsets;


public class ParticaoMestre {

    private Armazenador mArmazenador;
    private Arquivador mArquivador;
    private long mBancoID;
    private RefLong mLocalBanco;

    private RefLong mLocalCapitulos;
    private long mLocalCache;
    private RefLong mPaginaCorrente;
    private RefLong mLocalIndice;
    private int mTipo;
    private String mNome;


    public ParticaoMestre(Armazenador eArmazenador, Arquivador eArquivador, long banco_id, long local_banco, long local_capitulos, long local_cache, long local_corrente, long local_indice) {
        mArmazenador = eArmazenador;
        mArquivador = eArquivador;
        mBancoID = banco_id;
        mLocalBanco = new RefLong(local_banco);
        mLocalCapitulos = new RefLong(local_capitulos);
        mLocalCache = local_cache;
        mPaginaCorrente = new RefLong(local_corrente);
        mLocalIndice = new RefLong(local_indice);
        mTipo = 0;
        mNome = "";
    }


    public ParticaoMestre(String eNome, Armazenador eArmazenador, Arquivador eArquivador, long banco_id, long local_banco, long local_capitulos, long local_cache, long local_corrente, long local_indice) {
        mArmazenador = eArmazenador;
        mArquivador = eArquivador;
        mBancoID = banco_id;
        mLocalBanco = new RefLong(local_banco);
        mLocalCapitulos = new RefLong(local_capitulos);
        mLocalCache = local_cache;
        mPaginaCorrente = new RefLong(local_corrente);
        mLocalIndice = new RefLong(local_indice);
        mTipo = 1;
        mNome = eNome;
    }

    public long getID() {
        return mBancoID;
    }

    public long getLocalBanco() {
        return mLocalBanco.get();
    }

    public long getStatus() {
        return 255;
    }

    public long getLocalCapitulos() {
        return mLocalCapitulos.get();
    }

    public long getLocalCache() {
        return mLocalCache;
    }

    public long getLocalIndice() {
        return mLocalIndice.get();
    }

    public long getPaginaCorrente() {
        return mPaginaCorrente.get();
    }

    public String getNome() {

        if (mTipo == 0) {
            mArquivador.setPonteiro(mLocalBanco.get());
            mArquivador.get();

            TX eTX = new TX();
            return eTX.lerFluxoLimitado(mArquivador, 1024);
        } else {
            return mNome;
        }

    }


    public long getCapitulosContagem() {
        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());
        return eSumario.getCapitulosUtilizadosContagem();
    }

    public long getPaginasTotalContagem() {
        long contando = getCapitulosContagem() * (Armazenador.MAX_PAGINAS);
        return contando;
    }

    public long getPaginasContagem() {

        Sumario sumario = new Sumario(mArquivador, mLocalCapitulos.get());

        long contando = 0;

        for (Long capitulando : sumario.getCapitulosUtilizados()) {
            contando += Paginador.getPaginasUtilizadasContagem(mArquivador, capitulando);

        }

        return contando;
    }

    public long getItensTotalContagem() {

        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());

        RefLong contando = new RefLong(0);

        for (Long capitulo_ponteiro : eSumario.getCapitulosUtilizados()) {
            Sumario.realizar_contagem_de_paginas_todos_no_capitulo(mArquivador, this, capitulo_ponteiro, contando);

        }

        return contando.get();
    }

    public long getItensContagem() {

        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());

        RefLong contando = new RefLong(0);

        for (Long capitulo_ponteiro : eSumario.getCapitulosUtilizados()) {
            Sumario.realizar_contagem_de_paginas_utilizadas_no_capitulo(mArquivador, this, capitulo_ponteiro, contando);

        }

        return contando.get();
    }

    public long getItensAlocadosContagem() {

        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());

        RefLong contando = new RefLong(0);

        for (Long capitulo_ponteiro : eSumario.getCapitulosUtilizados()) {
            Sumario.realizar_contagem_de_paginas_alocados_no_capitulo(mArquivador, this, capitulo_ponteiro, contando);

        }

        return contando.get();
    }


    public Lista<ItemDoBancoUTF8> getItensUTF8() {

        Lista<ItemDoBancoUTF8> itens = new Lista<ItemDoBancoUTF8>();

        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());


        for (Long capitulo_ponteiro : eSumario.getCapitulosUtilizados()) {

            Lista<Long> pags = Paginador.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro);

            for (Long pag : pags) {

                PaginaMestre pg = new PaginaMestre(mArquivador, this, pag);
                itens.adicionar_varios(pg.getItensUTF8());
            }


        }

        return itens;
    }

    public Lista<ItemDoBancoTX> getItensTX() {

        Lista<ItemDoBancoTX> itens = new Lista<ItemDoBancoTX>();

        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());


        for (Long capitulo_ponteiro : eSumario.getCapitulosUtilizados()) {

            Lista<Long> pags = Paginador.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro);

            for (Long pag : pags) {

                PaginaMestre pg = new PaginaMestre(mArquivador, this, pag);
                itens.adicionar_varios(pg.getItensTX());
            }


        }

        return itens;
    }





    public long adicionar(String conteudo) {

        if (conteudo.getBytes(StandardCharsets.UTF_8).length >= (Matematica.KB(10) - 100)) {
            throw new RuntimeException("AQZ ERRO : O item é maior que 10 Kb !");
        }

        long endereco = 0;

        // TENTAR USAR CACHE DO BANCO
        long itens_em_cache = LocalCache.getItensEmCacheContagem(mArquivador, this.getLocalCache());

        if (itens_em_cache > 0) {

            if (Armazenador.IS_DEBUG) {
                System.out.println("Existem itens disponiveis em cache = " + itens_em_cache);
            }

            long obter_item_do_cache = LocalCache.getItemDoCacheERemove(mArquivador, this.getLocalCache());

            if (Armazenador.IS_DEBUG) {
                System.out.println("Utilizar item do cache = " + obter_item_do_cache);
            }

            if (obter_item_do_cache > 0) {
                LocalCache.guardar(mArquivador, obter_item_do_cache, conteudo);
                endereco = obter_item_do_cache;
                return endereco;
            }

        }

        // TENTAR USAR CACHE GLOBAL

        long itens_em_cache_global = LocalCache.getItensEmCacheContagem(mArquivador, mArmazenador.getGlobalCache());

        if (itens_em_cache_global > 0) {

            if (Armazenador.IS_DEBUG) {
                System.out.println("Existem itens disponiveis em cache global = " + itens_em_cache_global);
            }

            long obter_item_do_cache_global = LocalCache.getItemDoCacheERemove(mArquivador, mArmazenador.getGlobalCache());

            if (Armazenador.IS_DEBUG) {
                System.out.println("Utilizar item do cache global = " + obter_item_do_cache_global);
            }

            if (obter_item_do_cache_global > 0) {
                LocalCache.guardar(mArquivador, obter_item_do_cache_global, conteudo);
                endereco = obter_item_do_cache_global;
                return endereco;
            }

        }


        // TENTAR USAR ITEM DA PAGINA ATUAL
        mArquivador.setPonteiro(mPaginaCorrente.get());

        int pagina_status = mArquivador.get_u8();

        //  System.out.println("Pagina Status = " + pagina_status);

        PaginaMestre pagina_atual = new PaginaMestre(mArquivador, this, mPaginaCorrente.get());

        if (Armazenador.IS_DEBUG) {
            System.out.println("!INFO - PAGINA{" + mPaginaCorrente + "} -->> " + pagina_atual.contagemUsados() + " : " + pagina_atual.contagemTodos());
        }


        if (pagina_atual.temDisponivel()) {
            endereco = pagina_atual.adicionar(conteudo);
        } else {

            // TROCAR DE PAGINA

            if (Armazenador.IS_DEBUG) {
                System.out.println("!ERRO - SEM ESPAÇO NA PAGINA");
                System.out.println("!INFO - PROCURANDO NOVA PAGINA");
            }

            Paginador.trocar_de_pagina(mArquivador, this, mLocalBanco, mLocalCapitulos, mPaginaCorrente);

            // USAR NOVA PAGINA
            PaginaMestre pagina_trocada = new PaginaMestre(mArquivador, this, mPaginaCorrente.get());

            if (pagina_trocada.temDisponivel()) {
                endereco = pagina_trocada.adicionar(conteudo);
            } else {
                if (Armazenador.IS_DEBUG) {
                    System.out.println("!ERRO - SEM ESPAÇO NA PAGINA");
                }

            }
        }

        //  System.out.println("\t - Contagem T = " + pg.contagemTodos());
        // System.out.println("\t - Contagem Z = " + pg.contagemZerados());

        return endereco;
    }


    public void remover(ItemDoBancoTX item) {

        mArquivador.setPonteiro(item.getPonteiro());
        mArquivador.set_u8((byte) 2);


        if (item.isDoBanco()) {
            LocalCache.tentar_guardar_em_cache(mArquivador, item.getBanco().getLocalCache(), item.getPonteiro());
        } else {
            LocalCache.tentar_guardar_em_cache(mArquivador, mArmazenador.getGlobalCache(), item.getPonteiro());
        }


    }


    public void remover(ItemDoBancoUTF8 item) {

        mArquivador.setPonteiro(item.getPonteiro());
        mArquivador.set_u8((byte) 2);


        if (item.isDoBanco()) {
            LocalCache.tentar_guardar_em_cache(mArquivador, item.getBanco().getLocalCache(), item.getPonteiro());
        } else {
            LocalCache.tentar_guardar_em_cache(mArquivador, mArmazenador.getGlobalCache(), item.getPonteiro());
        }


    }


    public void set(long chave, long endereco) {

        //    fmt.print("AQZ STATUS LI - {} ",mLocalIndice.get());

        new Indexador(mArquivador, mLocalIndice.get()).set(chave, endereco);
    }

    public Opcional<RefLong> get(long chave) {
        return new Indexador(mArquivador, mLocalIndice.get()).get(chave);
    }

    public long getIndicePaginasContagem() {
        return new Indexador(mArquivador, mLocalIndice.get()).getIndicePaginasContagem();
    }

    public long getIndiceItensTotalContagem() {
        return new Indexador(mArquivador, mLocalIndice.get()).getIndiceItensTotalContagem();
    }

    public long getIndiceItensUtilizadoContagem() {
        return new Indexador(mArquivador, mLocalIndice.get()).getIndiceItensUtilizadoContagem();
    }

    public long getItensEmCacheContagem() {
        return LocalCache.getItensEmCacheContagem(mArquivador, mLocalCache);
    }


    public long getTamanhoCheio() {
        return getItensTotalContagem() * (10 * 1024);
    }

    public long getTamanho() {
        return getItensAlocadosContagem() * (10 * 1024);
    }

    public long getTamanhoUtilizado() {
        return getItensContagem() * (10 * 1024);
    }

    public int getUsabilidade() {
        double total = getItensTotalContagem();
        double usados = getItensContagem();
        return (int) ((usados / total) * 100.0);
    }

    public int getDisponibilidade() {
        double total = getItensTotalContagem();
        double usados = getItensContagem();
        return 100 - (int) ((usados / total) * 100.0);
    }


    public long getIndiceTamanhoCheio() {
        return getIndicePaginasContagem() * (1L + 8L + 8L + (Armazenador.MAX_ITENS_DO_INDICE * 8L));
    }

    public long getIndiceTamanhoUtilizado() {
        return getIndicePaginasContagem() * (1L + 8L + 8L) + (getIndiceItensUtilizadoContagem() * 8L);
    }


    public int getIndiceUsabilidade() {
        double total = getIndiceTamanhoCheio();
        double usados = getIndiceTamanhoUtilizado();
        return (int) ((usados / total) * 100.0);
    }

    public int getIndiceDisponibilidade() {
        double total = getIndiceTamanhoCheio();
        double usados = getIndiceTamanhoUtilizado();
        return 100 - (int) ((usados / total) * 100.0);
    }


    public void limpar() {

        //  System.out.println("Preparar para remover :: " + getItens().getQuantidade());
        int r = 0;

        for (ItemDoBancoTX item : getItensTX()) {
            remover(item);
            //     System.out.println("Removendo " + r);
            r += 1;
        }

    }

    public long adicionarUTF8(Entidade e) {
        return adicionarUTF8(ENTT.TO_DOCUMENTO(e));
    }


    public long adicionarUTF8(String conteudo) {

        if (conteudo.getBytes(StandardCharsets.UTF_8).length >= (Matematica.KB(10) - 100)) {
            throw new RuntimeException("AQZ ERRO : O item é maior que 10 Kb !");
        }

        long endereco = 0;

        // TENTAR USAR CACHE DO BANCO
        long itens_em_cache = LocalCache.getItensEmCacheContagem(mArquivador, this.getLocalCache());

        if (itens_em_cache > 0) {

            if (Armazenador.IS_DEBUG) {
                System.out.println("Existem itens disponiveis em cache = " + itens_em_cache);
            }

            long obter_item_do_cache = LocalCache.getItemDoCacheERemove(mArquivador, this.getLocalCache());

            if (Armazenador.IS_DEBUG) {
                System.out.println("Utilizar item do cache = " + obter_item_do_cache);
            }

            if (obter_item_do_cache > 0) {
                LocalCache.guardarUTF8(mArquivador, obter_item_do_cache, conteudo);
                endereco = obter_item_do_cache;
                return endereco;
            }

        }

        // TENTAR USAR CACHE GLOBAL

        long itens_em_cache_global = LocalCache.getItensEmCacheContagem(mArquivador, mArmazenador.getGlobalCache());

        if (itens_em_cache_global > 0) {

            if (Armazenador.IS_DEBUG) {
                System.out.println("Existem itens disponiveis em cache global = " + itens_em_cache_global);
            }

            long obter_item_do_cache_global = LocalCache.getItemDoCacheERemove(mArquivador, mArmazenador.getGlobalCache());

            if (Armazenador.IS_DEBUG) {
                System.out.println("Utilizar item do cache global = " + obter_item_do_cache_global);
            }

            if (obter_item_do_cache_global > 0) {
                LocalCache.guardarUTF8(mArquivador, obter_item_do_cache_global, conteudo);
                endereco = obter_item_do_cache_global;
                return endereco;
            }

        }


        // TENTAR USAR ITEM DA PAGINA ATUAL
        mArquivador.setPonteiro(mPaginaCorrente.get());

        int pagina_status = mArquivador.get_u8();

        //  System.out.println("Pagina Status = " + pagina_status);

        PaginaMestre pagina_atual = new PaginaMestre(mArquivador, this, mPaginaCorrente.get());

        if (Armazenador.IS_DEBUG) {
            System.out.println("!INFO - PAGINA{" + mPaginaCorrente + "} -->> " + pagina_atual.contagemUsados() + " : " + pagina_atual.contagemTodos());
        }


        if (pagina_atual.temDisponivel()) {
            endereco = pagina_atual.adicionarUTF8(conteudo);
        } else {

            // TROCAR DE PAGINA

            if (Armazenador.IS_DEBUG) {
                System.out.println("!ERRO - SEM ESPAÇO NA PAGINA");
                System.out.println("!INFO - PROCURANDO NOVA PAGINA");
            }

            Paginador.trocar_de_pagina(mArquivador, this, mLocalBanco, mLocalCapitulos, mPaginaCorrente);

            // USAR NOVA PAGINA
            PaginaMestre pagina_trocada = new PaginaMestre(mArquivador, this, mPaginaCorrente.get());

            if (pagina_trocada.temDisponivel()) {
                endereco = pagina_trocada.adicionarUTF8(conteudo);
            } else {
                if (Armazenador.IS_DEBUG) {
                    System.out.println("!ERRO - SEM ESPAÇO NA PAGINA");
                }

            }
        }

        //  System.out.println("\t - Contagem T = " + pg.contagemTodos());
        // System.out.println("\t - Contagem Z = " + pg.contagemZerados());

        return endereco;
    }


    public void limpar_profundamente() {


        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());


        for (Long capitulo_ponteiro : eSumario.getCapitulosUtilizados()) {

            Lista<Long> pags = Paginador.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro);

            fmt.print("CAP :: {}", capitulo_ponteiro);
            for (Long pag : pags) {

                PaginaMestre pg = new PaginaMestre(mArquivador, this, pag);

                for (Long item : pg.getAlocados()) {
                    fmt.print("\t++ Alocado :: {}", item);
                }


            }


        }

    }

    public void exibir_dump() {


        Sumario eSumario = new Sumario(mArquivador, mLocalCapitulos.get());


        for (Long capitulo_ponteiro : eSumario.getCapitulosUtilizados()) {

            Lista<Long> pags = Paginador.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro);

            fmt.print("CAPITULO :: {}", capitulo_ponteiro);
            for (Long pag : pags) {

                PaginaMestre pg = new PaginaMestre(mArquivador, this, pag);

                fmt.print("\t PAGINA :: {}", pg.getPonteiro());

                for (Long item : pg.getAlocados()) {
                    fmt.print("\t\t++ Alocado :: {}", item);
                }


            }


        }

    }


    public void limpar_com_long(String log) {

        //  System.out.println("Preparar para remover :: " + getItens().getQuantidade());
        int r = 0;

        Lista<ItemDoBancoTX> itens = getItensTX();
        int total = itens.getQuantidade();

        for (ItemDoBancoTX item : itens) {
            remover(item);
            System.out.println(log + " " + r + " de " + total);
            r += 1;
        }

    }


    public long adicionarTX(Entidade e) {
        return adicionarTX(ENTT.TO_DOCUMENTO(e));
    }

    public long adicionarTX(String conteudo) {

        if (conteudo.getBytes(StandardCharsets.UTF_8).length >= (Matematica.KB(10) - 100)) {
            throw new RuntimeException("AQZ ERRO : O item é maior que 10 Kb !");
        }

        long endereco = 0;

        // TENTAR USAR CACHE DO BANCO
        long itens_em_cache = LocalCache.getItensEmCacheContagem(mArquivador, this.getLocalCache());

        if (itens_em_cache > 0) {

            if (Armazenador.IS_DEBUG) {
                System.out.println("Existem itens disponiveis em cache = " + itens_em_cache);
            }

            long obter_item_do_cache = LocalCache.getItemDoCacheERemove(mArquivador, this.getLocalCache());

            if (Armazenador.IS_DEBUG) {
                System.out.println("Utilizar item do cache = " + obter_item_do_cache);
            }

            if (obter_item_do_cache > 0) {
                LocalCache.guardarTX(mArquivador, obter_item_do_cache, conteudo);
                endereco = obter_item_do_cache;
                return endereco;
            }

        }

        // TENTAR USAR CACHE GLOBAL

        long itens_em_cache_global = LocalCache.getItensEmCacheContagem(mArquivador, mArmazenador.getGlobalCache());

        if (itens_em_cache_global > 0) {

            if (Armazenador.IS_DEBUG) {
                System.out.println("Existem itens disponiveis em cache global = " + itens_em_cache_global);
            }

            long obter_item_do_cache_global = LocalCache.getItemDoCacheERemove(mArquivador, mArmazenador.getGlobalCache());

            if (Armazenador.IS_DEBUG) {
                System.out.println("Utilizar item do cache global = " + obter_item_do_cache_global);
            }

            if (obter_item_do_cache_global > 0) {
                LocalCache.guardarTX(mArquivador, obter_item_do_cache_global, conteudo);
                endereco = obter_item_do_cache_global;
                return endereco;
            }

        }


        // TENTAR USAR ITEM DA PAGINA ATUAL
        mArquivador.setPonteiro(mPaginaCorrente.get());

        int pagina_status = mArquivador.get_u8();

        //  System.out.println("Pagina Status = " + pagina_status);

        PaginaMestre pagina_atual = new PaginaMestre(mArquivador, this, mPaginaCorrente.get());

        if (Armazenador.IS_DEBUG) {
            System.out.println("!INFO - PAGINA{" + mPaginaCorrente + "} -->> " + pagina_atual.contagemUsados() + " : " + pagina_atual.contagemTodos());
        }


        if (pagina_atual.temDisponivel()) {
            endereco = pagina_atual.adicionarTX(conteudo);
        } else {

            // TROCAR DE PAGINA

            if (Armazenador.IS_DEBUG) {
                System.out.println("!ERRO - SEM ESPAÇO NA PAGINA");
                System.out.println("!INFO - PROCURANDO NOVA PAGINA");
            }

            Paginador.trocar_de_pagina(mArquivador, this, mLocalBanco, mLocalCapitulos, mPaginaCorrente);

            // USAR NOVA PAGINA
            PaginaMestre pagina_trocada = new PaginaMestre(mArquivador, this, mPaginaCorrente.get());

            if (pagina_trocada.temDisponivel()) {
                endereco = pagina_trocada.adicionarTX(conteudo);
            } else {
                if (Armazenador.IS_DEBUG) {
                    System.out.println("!ERRO - SEM ESPAÇO NA PAGINA");
                }

            }
        }

        //  System.out.println("\t - Contagem T = " + pg.contagemTodos());
        // System.out.println("\t - Contagem Z = " + pg.contagemZerados());

        return endereco;
    }


}
