package libs.armazenador;


import libs.arquivos.binario.Arquivador;

public class LocalCache {

    public static long getItensEmCacheContagem(Arquivador mArquivador, long mLocalCache) {

        mArquivador.setPonteiro(mLocalCache);

        int cache_status = mArquivador.get_u8();

        if (Armazenador.IS_DEBUG) {
            System.out.println("Contar itens guardados em cache : " + mLocalCache);
            System.out.println("\t - Status = " + cache_status);
        }

        long contagem = 0;

        for (int item_do_cache = 0; item_do_cache < Armazenador.MAX_ITENS_DO_CACHE; item_do_cache++) {
            long item_do_cache_referencia = mArquivador.get_u64();

            if (item_do_cache_referencia != 0) {
                contagem += 1;
            }

        }
        if (Armazenador.IS_DEBUG) {
            System.out.println("\t - Contagem = " + contagem);
        }

        return contagem;
    }

    public static void tentar_guardar_em_cache(Arquivador mArquivador, long mLocalCache, long ponteiro_guardar) {

        mArquivador.setPonteiro(mLocalCache);

        int cache_status = mArquivador.get_u8();
        if (Armazenador.IS_DEBUG) {

            System.out.println("Guardem item em cache em : " + mLocalCache);
            System.out.println("\t - Status = " + cache_status);
        }


        for (int item_do_cache = 0; item_do_cache < Armazenador.MAX_ITENS_DO_CACHE; item_do_cache++) {

            long item_do_cache_ponteiro = mArquivador.getPonteiro();
            long item_do_cache_referencia = mArquivador.get_u64();

            if (item_do_cache_referencia == 0) {

                mArquivador.setPonteiro(item_do_cache_ponteiro);
                mArquivador.set_u64(ponteiro_guardar);

                if (Armazenador.IS_DEBUG) {
                    System.out.println("\t - Guardado = " + ponteiro_guardar);
                }

                break;
            }

        }


    }

    public static long getItemDoCacheERemove(Arquivador mArquivador, long mLocalCache) {

        mArquivador.setPonteiro(mLocalCache);

        int cache_status = mArquivador.get_u8();

        if (Armazenador.IS_DEBUG) {

            System.out.println("Procurar item em cache em : " + mLocalCache);
            System.out.println("\t - Status = " + cache_status);
        }

        long ret = 0;

        for (int item_do_cache = 0; item_do_cache < Armazenador.MAX_ITENS_DO_CACHE; item_do_cache++) {

            long item_do_cache_ponteiro = mArquivador.getPonteiro();
            long item_do_cache_referencia = mArquivador.get_u64();

            if (item_do_cache_referencia != 0) {
                mArquivador.setPonteiro(item_do_cache_ponteiro);
                mArquivador.set_u64(0);

                if (Armazenador.IS_DEBUG) {
                    System.out.println("\t - Disponivel = " + item_do_cache_referencia);
                }

                ret = item_do_cache_referencia;
                break;
            }

        }

        return ret;
    }

    public static void guardar(Arquivador mArquivador, long obter_item_do_cache, String conteudo) {

        mArquivador.setPonteiro(obter_item_do_cache);

        int item_status = mArquivador.get_u8();
        long ponteiro_guardar = mArquivador.getPonteiro();
        long ponteiro_item_dados = mArquivador.get_u64();


        if (item_status == Armazenador.ITEM_NAO_ALOCADO) {
            if (Armazenador.IS_DEBUG) {
                System.out.println("Item do cache ainda nao alocado");
            }

            ItemGuardar.guardar_em_item_nao_alocado(mArquivador, obter_item_do_cache, ponteiro_guardar, conteudo);

        } else if (item_status == Armazenador.ITEM_ALOCADO_DISPONIVEL) {
            if (Armazenador.IS_DEBUG) {
                System.out.println("Item do cache ja alocado");
            }

            ItemGuardar.guardar_em_item_ja_alocado(mArquivador, obter_item_do_cache, ponteiro_item_dados, conteudo);
        }


    }
}
