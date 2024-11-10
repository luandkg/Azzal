package libs.armazenador;


import libs.arquivos.binario.Arquivador;
import libs.luan.RefLong;

import java.util.ArrayList;

public class Sumario {

    private Arquivador mArquivador;
    private long mLocalCapitulos;

    public Sumario(Arquivador eArquivador, long eLocalCapitulos) {
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
                capitulos.add(new Long(cap_local));
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

    public static void realizar_contagem_de_paginas_alocados_no_capitulo(Arquivador mArquivador, ParticaoPrimaria mParticaoPrimaria, long capitulo_ponteiro, RefLong contador) {

        for (Long pagina_ponteiro : Paginador.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro)) {
            Pagina pg = new Pagina(mArquivador, mParticaoPrimaria, pagina_ponteiro);
            contador.set(contador.get() + pg.contagemAlocados());
        }


    }

    public static void realizar_contagem_de_paginas_utilizadas_no_capitulo(Arquivador mArquivador, ParticaoPrimaria mParticaoPrimaria, long capitulo_ponteiro, RefLong contador) {

        for (Long pagina_ponteiro : Paginador.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro)) {
            Pagina pg = new Pagina(mArquivador, mParticaoPrimaria, pagina_ponteiro);
            contador.set(contador.get() + pg.contagemUsados());
        }


    }

    public static void realizar_contagem_de_paginas_todos_no_capitulo(Arquivador mArquivador, ParticaoPrimaria mParticaoPrimaria, long capitulo_ponteiro, RefLong contador) {

        for (Long pagina_ponteiro : Paginador.getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro)) {
            Pagina pg = new Pagina(mArquivador, mParticaoPrimaria, pagina_ponteiro);
            contador.set(contador.get() + pg.contagemTodos());
        }


    }
}
