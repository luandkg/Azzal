package libs.asset;

import libs.arquivos.StringView;
import libs.arquivos.binario.Arquivador;

import java.util.ArrayList;

public class Biblioteca {

    private String mNome;
    private AssetContainer mAssetContainer;
    private ArrayList<Arquivo> mArquivos;
    private boolean mIndexado;

    private ArrayList<String> mLocais;
    private ArrayList<String> mExtensoes;

    public Biblioteca(AssetContainer eAssetContainer, String eNome) {

        mNome = eNome;
        mAssetContainer = eAssetContainer;
        mArquivos = new ArrayList<Arquivo>();
        mIndexado = false;

        mLocais = new ArrayList<String>();
        mExtensoes = new ArrayList<String>();

    }


    public String getNome() {
        return mNome;
    }


    public void adicionar(String eLocal) {
        if (!mLocais.contains(eLocal)) {
            mLocais.add(eLocal);
        }
    }

    public void adicionarExtensao(String eExtensao) {
        if (!mExtensoes.contains(eExtensao)) {
            mExtensoes.add(eExtensao);
        }
    }

    public ArrayList<String> getLocais() {
        return mLocais;
    }

    public ArrayList<String> getExtensoes() {
        return mExtensoes;
    }

    public ArrayList<Arquivo> getArquivos() {

        if (!mIndexado) {
            mIndexado = true;
            abrir();
        }

        return mArquivos;
    }

    private void abrir() {

        mArquivos.clear();

        for (String eLocal : getLocais()) {

            if (eLocal.contentEquals("\\")) {

                for (Arquivo eArquivo : mAssetContainer.getArquivos()) {
                    boolean incluir = false;
                    for (String eExt : mExtensoes) {
                        if (eArquivo.getNome().endsWith(eExt)) {
                            incluir = true;
                        }
                    }
                    if (incluir) {
                        mArquivos.add(eArquivo);
                    }
                }
                for (Pasta ePasta : mAssetContainer.getPastas()) {
                    abrirLocal(ePasta);
                }

            } else if (eLocal.contentEquals("/")) {

                for (Arquivo eArquivo : mAssetContainer.getArquivos()) {
                    boolean incluir = false;
                    for (String eExt : mExtensoes) {
                        if (eArquivo.getNome().endsWith(eExt)) {
                            incluir = true;
                        }
                    }
                    if (incluir) {
                        mArquivos.add(eArquivo);
                    }
                }
                for (Pasta ePasta : mAssetContainer.getPastas()) {
                    abrirLocal(ePasta);
                }
            } else {
                Pasta ePasta = mAssetContainer.getPastaCaminho(eLocal);

                abrirLocal(ePasta);
            }


        }
    }

    private void abrirLocal(Pasta ePasta) {


        Arquivador fu = new Arquivador(mAssetContainer.getArquivo());

        fu.setPonteiro(ePasta.getInicio());

        int v = 0;

        while (v != 13) {
            v = (int) fu.get();

            if (v == 11) {

                String s1 = StringView.deArquivador(fu, 100);

                long r1 = fu.getPonteiro();
                long l2 = fu.get_u64();

                long r2 = fu.getPonteiro();

                long l3 = fu.get_u64();

                abrirLocal(new Pasta(mAssetContainer, new Referencia(r1, r2), new AssetRef(s1, 11, l2, l3)));


            } else if (v == 12) {

                String s1 = StringView.deArquivador(fu, 100);

                long r1 = fu.getPonteiro();
                long l2 = fu.get_u64();

                long r2 = fu.getPonteiro();
                long l3 = fu.get_u64();

                Arquivo eArquivo = new Arquivo(mAssetContainer, new Referencia(r1, r2), new AssetRef(s1, 12, l2, l3));

                boolean incluir = false;
                for (String eExt : mExtensoes) {
                    if (eArquivo.getNome().endsWith(eExt)) {
                        incluir = true;
                    }
                }
                if (incluir) {
                    mArquivos.add(eArquivo);
                }


            }
        }

        fu.encerrar();


    }
}
