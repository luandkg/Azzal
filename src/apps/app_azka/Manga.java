package apps.app_azka;

import java.awt.image.BufferedImage;

import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Strings;

public class Manga {

    private String mArquivo;
    private String mArquivoNome;
    private int mArquivoNomeSequencial;

    private boolean mAberto;

    private int mPaginas = 0;
    private BufferedImage mCapa = null;
    private boolean mTemCapa = false;

    public Manga(String arquivo) {
        mArquivo = arquivo;
        mArquivoNome = Strings.GET_REVERSO_ATE(mArquivo, "/");
        mArquivoNomeSequencial = Integer.parseInt(mArquivoNome.replace(".hq", ""));
        mAberto = false;
    }

    public String getArquivo() {
        return mArquivo;
    }

    public String getArquivoNome() {
        return mArquivoNome;
    }

    public int getArquivoNomeSequencial() {
        return mArquivoNomeSequencial;
    }

    public void abrir() {
        mAberto = true;
        mPaginas = 0;

        for (DSItem item : DS.ler_todos(mArquivo)) {
            if (item.getNome().endsWith(".im")) {
                mPaginas += 1;

                if (mPaginas == 1) {
                    mCapa = Imagem.GET_IMAGEM(item.getBytes());
                    mTemCapa = true;
                }

            }
        }

    }

    public boolean isAberto() {
        return mAberto;
    }

    public int getPaginas() {
        return mPaginas;
    }

    public boolean temCapa() {
        return mTemCapa;
    }

    public BufferedImage getCapa() {
        return mCapa;
    }


    public static Lista<Manga> ORDENAR_POR_NOME(Lista<Manga> entradas) {

        int n = entradas.getQuantidade();
        Manga temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).getArquivoNome().compareTo(entradas.get(j).getArquivoNome()) > 0) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
        return entradas;
    }

    public static Lista<Manga> ORDENAR_POR_NUMERO(Lista<Manga> entradas) {

        int n = entradas.getQuantidade();
        Manga temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).getArquivoNomeSequencial() > entradas.get(j).getArquivoNomeSequencial()) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
        return entradas;
    }

}
