package libs.aqz.extincao.armazenador_antigo;


import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoEmExtincao;
import libs.arquivos.binario.Arquivador;
import libs.luan.RefLong;

import java.util.ArrayList;

public class SumarioEmExtincao {

    private Arquivador mArquivador;
    private long mLocalCapitulos;

    public SumarioEmExtincao(Arquivador eArquivador, long eLocalCapitulos) {
        mArquivador = eArquivador;
        mLocalCapitulos = eLocalCapitulos;
    }

    public ArrayList<Long> getCapitulosUtilizados() {
        mArquivador.setPonteiro(mLocalCapitulos);

        int capitulo_status = mArquivador.get_u8();

        // System.out.println("Contar capitulos guardados em : " + mLocalCapitulos);
        // System.out.println("\t - Status = " + st);

        ArrayList<Long> capitulos = new ArrayList<Long>();

        for (int capitulo = 0; capitulo < Armazenador.MAX_CAPITULOS; capitulo++) {
            long cap_local = mArquivador.get_u64();

            if (cap_local != 0) {
                capitulos.add(cap_local);
            }

        }

        return capitulos;
    }

    public long getCapitulosUtilizadosContagem() {
        long contando = 0;


        mArquivador.setPonteiro(mLocalCapitulos);

        int st = mArquivador.get_u8();

        // System.out.println("Contar capitulos guardados em : " + mLocalCapitulos);
        // System.out.println("\t - Status = " + st);

        for (int capitulo = 0; capitulo < Armazenador.MAX_CAPITULOS; capitulo++) {
            long cap_local = mArquivador.get_u64();

            if (cap_local != 0) {
                contando += 1;
            }

        }
        return contando;
    }

    public static void realizar_contagem_de_paginas_alocados_no_capitulo(Arquivador mArquivador, ParticaoEmExtincao mParticaoEmExtincao, long capitulo_ponteiro, RefLong contador) {

        for (Long pagina_ponteiro : PaginadorEmExtincao.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro)) {
            PaginaEmExtincao pg = new PaginaEmExtincao(mArquivador, mParticaoEmExtincao, pagina_ponteiro);
            contador.set(contador.get() + pg.contagemAlocados());
        }


    }

    public static void realizar_contagem_de_paginas_utilizadas_no_capitulo(Arquivador mArquivador, ParticaoEmExtincao mParticaoEmExtincao, long capitulo_ponteiro, RefLong contador) {

        for (Long pagina_ponteiro : PaginadorEmExtincao.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro)) {
            PaginaEmExtincao pg = new PaginaEmExtincao(mArquivador, mParticaoEmExtincao, pagina_ponteiro);
            contador.set(contador.get() + pg.contagemUsados());
        }


    }

    public static void realizar_contagem_de_paginas_todos_no_capitulo(Arquivador mArquivador, ParticaoEmExtincao mParticaoEmExtincao, long capitulo_ponteiro, RefLong contador) {

        for (Long pagina_ponteiro : PaginadorEmExtincao.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro)) {
            PaginaEmExtincao pg = new PaginaEmExtincao(mArquivador, mParticaoEmExtincao, pagina_ponteiro);
            contador.set(contador.get() + pg.contagemTodos());
        }


    }




}
