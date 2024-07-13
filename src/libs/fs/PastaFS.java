package libs.fs;

import libs.luan.Lista;

import java.io.File;


public class PastaFS {
    private String mLocal;

    public PastaFS(String eLocal) {
        mLocal = eLocal;
    }

    public String getArquivo(String arquivo) {
        if (mLocal.endsWith("/")) {
            return mLocal + arquivo;
        } else {
            return mLocal + "/" + arquivo;
        }
    }

    public String getPasta(String arquivo) {
        if (mLocal.endsWith("/")) {
            return mLocal + arquivo;
        } else {
            return mLocal + "/" + arquivo;
        }
    }

    public String getLocal() {
        return mLocal;
    }

    public void verificar() {
        File pastinha = new File(mLocal);
        if (!pastinha.exists()) {
            pastinha.mkdir();
        }
    }


    public Lista<PastaFS> getPastas() {
        Lista<PastaFS> ret = new Lista<PastaFS>();

        File file = new File(mLocal);
        if (file.exists()) {
            for (File a : file.listFiles()) {
                if (a.isDirectory()) {
                    ret.adicionar(new PastaFS(a.getAbsolutePath()));
                }

            }
        }


        return ret;

    }


    public String getNome() {
        String ret = "";

        int o = mLocal.length() - 1;
        while (o > 0) {
            if (String.valueOf(mLocal.charAt(o)).contentEquals("\\")) {
                break;
            } else if (String.valueOf(mLocal.charAt(o)).contentEquals("/")) {
                break;
            }
            ret = String.valueOf(mLocal.charAt(o)) + ret;
            o -= 1;
        }

        return ret;
    }


    public Lista<Arquivo> getArquivos() {
        Lista<Arquivo> ret = new Lista<Arquivo>();

        File file = new File(mLocal);

        File[] files = file.listFiles();

        if (files != null) {
            for (File a : files) {
                if (a.isFile()) {
                    ret.adicionar(new Arquivo(a.getAbsolutePath()));
                }
            }
        }

        return ret;
    }

    public Lista<String> getArquivosCaminhos() {
        Lista<String> ret = new Lista<String>();

        File file = new File(mLocal);

        File[] files = file.listFiles();

        if (files != null) {
            for (File a : files) {
                if (a.isFile()) {
                    ret.adicionar(a.getAbsolutePath());
                }
            }
        }

        return ret;
    }



    public boolean existe() {
        return new File(mLocal).exists();
    }

    public void organizar() {
        if (!existe()) {
            new File(mLocal).mkdir();
        }
    }

    public boolean arquivo_existe(String arquivo) {
        String sa = "";

        if (mLocal.endsWith("/")) {
            sa = mLocal + arquivo;
        } else {
            sa = mLocal + "/" + arquivo;
        }

        return new File(sa).exists();
    }

    public boolean pasta_existe(String nome) {
        return new File(getArquivo(nome)).exists();
    }


    public Lista<String> getArquivosRecursivamente() {
        Lista<String> lista_de_arquivos = new Lista<String>();

        File file = new File(mLocal);

        File[] files = file.listFiles();

        if (files != null) {
            for (File a : files) {
                if (a.isFile()) {
                    lista_de_arquivos.adicionar(a.getAbsolutePath());
                } else if (a.isDirectory()) {
                    incluir_arquivos_internos(lista_de_arquivos, a.getAbsolutePath());
                }
            }
        }

        return lista_de_arquivos;
    }


    private void incluir_arquivos_internos(Lista<String> lista_de_arquivos, String pasta_caminho) {

        File file = new File(pasta_caminho);

        File[] files = file.listFiles();

        if (files != null) {
            for (File a : files) {
                if (a.isFile()) {
                    lista_de_arquivos.adicionar(a.getAbsolutePath());
                } else if (a.isDirectory()) {
                    incluir_arquivos_internos(lista_de_arquivos, a.getAbsolutePath());
                }
            }
        }

    }
}