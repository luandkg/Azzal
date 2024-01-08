package libs.asset;

import libs.arquivos.StringView;
import libs.arquivos.binario.Arquivador;

import java.util.ArrayList;

public class Anexado {

    private AssetContainer mAssetContainer;
    private boolean mIndexado;

    private ArrayList<Listador> mListadores;
    private ArrayList<Biblioteca> mBibliotecas;
    private ArrayList<ArquivoLink> mArquivosLink;
    private ArrayList<PastaLink> mPastasLink;

    public Anexado(AssetContainer eAssetContainer) {

        mAssetContainer = eAssetContainer;
        mIndexado = false;

        mArquivosLink = new ArrayList<ArquivoLink>();
        mPastasLink = new ArrayList<PastaLink>();

        mListadores = new ArrayList<Listador>();
        mBibliotecas = new ArrayList<Biblioteca>();

    }

    public void ler() {

        if (!mIndexado) {
            mIndexado = true;

            mArquivosLink.clear();
            mPastasLink.clear();

            mListadores.clear();
            mBibliotecas.clear();

            //   System.out.println("Iniciar Leitura Extrum");


            Arquivador fu = new Arquivador(mAssetContainer.getArquivo());

            if (mAssetContainer.temApendice()) {

                fu.setPonteiro(mAssetContainer.getExtrumPonteiro());


                byte b1 = fu.get();
                b1 = fu.get();

                while (b1 != (byte) 55) {

                    // System.out.println("B1 : " + b1);


                    if (b1 == (byte) 51) {


                        Listador eListador = new Listador(mAssetContainer, StringView.deArquivador(fu, 100));

                        //System.out.println("Dentro Listador : " + eListador.getNome());

                        b1 = fu.get();

                        // System.out.println("Dentro Listador : " + b1);

                        while (b1 != (byte) 13) {

                            if (b1 == (byte) 11) {

                                long r1 = fu.getPonteiro();
                                long eInicio = fu.get_u64();

                                long r2 = fu.getPonteiro();
                                long eFim = fu.get_u64();

                                eListador.adicionar(new Pasta(this.mAssetContainer, new Referencia(r1, r2), new AssetRef(11, eInicio, eFim)));

                                //  System.out.println("\t - Local " + eLocal);
                            }
                            b1 = fu.get();
                            if (b1 == (byte) -1) {
                                break;
                            }
                        }


                        mListadores.add(eListador);


                    } else if (b1 == (byte) 52) {

                        Biblioteca eListador = new Biblioteca(mAssetContainer, StringView.deArquivador(fu, 100));

                        // System.out.println("Dentro Biblioteca : " + eListador.getNome());

                        b1 = fu.get();

                        // System.out.println("Dentro Biblioteca : " + b1);

                        while (b1 != (byte) 13) {

                            if (b1 == (byte) 11) {
                                String eLocal = StringView.deArquivador(fu, 1024);
                                eListador.adicionar(eLocal);

                                //  System.out.println("\t - Local " + eLocal);
                            }
                            b1 = fu.get();
                            if (b1 == (byte) -1) {
                                break;
                            }
                        }

                        b1 = fu.get();

                        // System.out.println("Dentro Biblioteca : " + b1);

                        while (b1 != (byte) 13) {

                            if (b1 == (byte) 11) {
                                String eLocal = StringView.deArquivador(fu, 100);
                                eListador.adicionarExtensao(eLocal);

                                //   System.out.println("\t - Extensao " + eLocal);
                            }
                            b1 = fu.get();
                            if (b1 == (byte) -1) {
                                break;
                            }
                        }

                        mBibliotecas.add(eListador);

                    } else if (b1 == (byte) 53) {

                        String s1 = StringView.deArquivador(fu, 100);
                        String s2 = StringView.deArquivador(fu, 100);


                        ArquivoLink eArquivoLink = new ArquivoLink(mAssetContainer, s1, s2, fu.get_u64(), fu.get_u64());
                        mArquivosLink.add(eArquivoLink);


                    } else if (b1 == (byte) 54) {

                        String s1 = StringView.deArquivador(fu, 100);
                        String s2 = StringView.deArquivador(fu, 100);

                        PastaLink eArquivoLink = new PastaLink(mAssetContainer, s1, s2, fu.get_u64(), fu.get_u64());
                        mPastasLink.add(eArquivoLink);


                    }

                    b1 = fu.get();
                    if (b1 == (byte) -1) {
                        break;
                    }
                }


            }
            fu.encerrar();


        }

    }

    public void limpar() {

        ler();

        mListadores.clear();
        mBibliotecas.clear();
        mArquivosLink.clear();
        mPastasLink.clear();

    }

    public Listador criarListador(String eNome) {

        ler();

        if (!existe(eNome)) {
            Listador mListadorC = new Listador(mAssetContainer, eNome);

            mListadores.add(mListadorC);

            return mListadorC;
        } else {
            throw new IllegalArgumentException("Ja existe um recurso com esse nome : " + eNome);
        }

    }

    public Biblioteca criarBiblioteca(String eNome) {

        ler();

        if (!existe(eNome)) {
            Biblioteca mBiblioteca = new Biblioteca(mAssetContainer, eNome);

            mBibliotecas.add(mBiblioteca);

            return mBiblioteca;
        } else {
            throw new IllegalArgumentException("Ja existe um recurso com esse nome : " + eNome);
        }

    }

    public ArquivoLink criarLinkArquivo(String eNome, Arquivo eLocal) {


        System.out.println(eLocal.getNome());

        ler();

        if (!existe(eNome)) {

            System.out.println("A : " + eLocal.getNome());

            ArquivoLink mArquivoLinkC = new ArquivoLink(mAssetContainer, eNome, eLocal.getNome(), eLocal.getInicio(), eLocal.getFim());
            mArquivosLink.add(mArquivoLinkC);

            return mArquivoLinkC;
        } else {
            throw new IllegalArgumentException("Ja existe um recurso com esse nome : " + eNome);
        }

    }

    public PastaLink criarLinkPasta(String eNome, Pasta eLocal) {


        ler();

        if (!existe(eNome)) {


            PastaLink mArquivoLinkC = new PastaLink(mAssetContainer, eNome, eLocal.getNome(), eLocal.getInicio(), eLocal.getFim());
            mPastasLink.add(mArquivoLinkC);

            return mArquivoLinkC;
        } else {
            throw new IllegalArgumentException("Ja existe um recurso com esse nome : " + eNome);
        }

    }

    public ArrayList<Listador> getListadores() {
        ler();
        return mListadores;
    }

    public ArrayList<Biblioteca> getBibliotecas() {
        ler();
        return mBibliotecas;
    }

    public ArrayList<ArquivoLink> getArquivosLink() {
        ler();
        return mArquivosLink;
    }

    public ArrayList<PastaLink> getPastasLink() {
        ler();
        return mPastasLink;
    }

    public boolean existe(String eNome) {

        ler();

        boolean ret = false;

        for (Listador mListadorC : getListadores()) {
            if (mListadorC.getNome().contentEquals(eNome)) {
                ret = true;
                break;
            }
        }

        for (Biblioteca mListadorC : getBibliotecas()) {
            if (mListadorC.getNome().contentEquals(eNome)) {
                ret = true;
                break;
            }
        }

        for (ArquivoLink mListadorC : getArquivosLink()) {
            if (mListadorC.getNome().contentEquals(eNome)) {
                ret = true;
                break;
            }
        }

        return ret;

    }


    public void salvar() {


        Arquivador fu = new Arquivador(mAssetContainer.getArquivo());

        if (mAssetContainer.temApendice()) {

            fu.setPonteiro(mAssetContainer.getExtrumPonteiro());

            fu.set_u8((byte) 50);


            for (Listador mListadorC : getListadores()) {

                fu.set_u8((byte) 51);
                fu.set_u8_array(StringView.toBytes(mListadorC.getNome(), 100));

                fu.set_u8((byte) 10);

                for (Pasta eLocal : mListadorC.getLocais()) {
                    fu.set_u8((byte) 11);
                    fu.set_u64(eLocal.getInicio());
                    fu.set_u64(eLocal.getFim());
                }

                fu.set_u8((byte) 13);

            }

            for (Biblioteca mListadorC : getBibliotecas()) {

                //System.out.println("Criando Bib " + mListadorC.getNome() + " : " + mListadorC.getLocais().size() + " : " + mListadorC.getExtensoes().size());

                fu.set_u8((byte) 52);
                fu.set_u8_array(StringView.toBytes(mListadorC.getNome(), 100));

                fu.set_u8((byte) 10);

                for (String eLocal : mListadorC.getLocais()) {
                    fu.set_u8((byte) 11);
                    fu.set_u8_array(StringView.toBytes(eLocal, 1024));
                }

                fu.set_u8((byte) 13);

                for (String eLocal : mListadorC.getExtensoes()) {
                    fu.set_u8((byte) 11);
                    fu.set_u8_array(StringView.toBytes(eLocal, 100));
                    // System.out.println("Salvando ext : " + eLocal);
                }

                fu.set_u8((byte) 13);

            }

            for (ArquivoLink mListadorC : getArquivosLink()) {

                //System.out.println("Criando Bib " + mListadorC.getNome() + " : " + mListadorC.getLocais().size() + " : " + mListadorC.getExtensoes().size());

                fu.set_u8((byte) 53);

                fu.set_u8_array(StringView.toBytes(mListadorC.getNome(), 100));
                fu.set_u8_array(StringView.toBytes(mListadorC.getArquivo().getNome(), 100));

                System.out.println("Criando Arquivo Link : " + mListadorC.getNome());


                fu.set_u64(mListadorC.getArquivo().getInicio());
                fu.set_u64(mListadorC.getArquivo().getFim());


            }

            for (PastaLink mListadorC : getPastasLink()) {

                //System.out.println("Criando Bib " + mListadorC.getNome() + " : " + mListadorC.getLocais().size() + " : " + mListadorC.getExtensoes().size());

                fu.set_u8((byte) 54);

                fu.set_u8_array(StringView.toBytes(mListadorC.getNome(), 100));
                fu.set_u8_array(StringView.toBytes(mListadorC.getPasta().getNome(), 100));

                System.out.println("Criando Pasta Link : " + mListadorC.getNome());


                fu.set_u64(mListadorC.getPasta().getInicio());
                fu.set_u64(mListadorC.getPasta().getFim());


            }

            fu.set_u8((byte) 55);


        }

        fu.encerrar();


    }


}
