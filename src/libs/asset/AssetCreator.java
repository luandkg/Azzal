package libs.asset;

import libs.arquivos.StringView;
import libs.arquivos.binario.Arquivador;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.GZIPOutputStream;

public class AssetCreator {

    public static final String ASSET_CONTAINER = "ASSET_CONTAINER";
    public static final String ASSET_CONTAINER_COMPRESSED = "ASSET_CONTAINER_COMPRESSED";

    public static void criar(String eArquivo, String eLocal) {
        criarGeral(eArquivo, eLocal, ASSET_CONTAINER);
    }


    public static void criarCompressed(String eArquivo, String eLocal) {
        criarGeral(eArquivo, eLocal, ASSET_CONTAINER_COMPRESSED);
    }


    private static void criarGeral(String eArquivo, String eLocal, String eCabecalho) {

        String eVersao = "1.0";

        System.out.println("");
        System.out.println("ASSET CONTAINER CREATOR");
        System.out.println("\t - Versao : " + eVersao);


        Arquivador.remover(eArquivo);

        Arquivador arquivar = new Arquivador(eArquivo);

        arquivar.limpar();

        arquivar.inicio();

        arquivar.set_u8_array(StringView.toBytes(eCabecalho, 100));
        arquivar.set_u8_array(StringView.toBytes(eVersao, 100));


        long eCriado = arquivar.getPonteiro();
        arquivar.set_u8_array(StringView.toBytes(getTempo(), 100));

        long eFinalizado = arquivar.getPonteiro();
        arquivar.set_u8_array(StringView.toBytes(getTempo(), 100));

        long eApendiceTem = arquivar.getPonteiro();

        arquivar.set_u8((byte) 0);

        long eApendicePonteiro = arquivar.getPonteiro();

        arquivar.set_u64(0);

        if (eCabecalho.contentEquals(ASSET_CONTAINER)) {

            criarPasta(arquivar, eLocal);

        } else if (eCabecalho.contentEquals(ASSET_CONTAINER_COMPRESSED)) {

            criarPastaCompressed(arquivar, eLocal);

        }


        //fu.dump();

        long eApendiceInicio = arquivar.getPonteiro();

        arquivar.set_u8((byte) 50);

        arquivar.set_u8((byte) 55);


        arquivar.setPonteiro(eApendiceTem);
        arquivar.set_u8((byte) 1);

        arquivar.setPonteiro(eApendicePonteiro);
        arquivar.set_u64(eApendiceInicio);

        arquivar.setPonteiro(eFinalizado);
        arquivar.set_u8_array(StringView.toBytes(getTempo(), 100));


        arquivar.encerrar();


    }


    private static void criarPasta(Arquivador fu, String eLocal) {


        File dir = new File(eLocal);

        ArrayList<AssetRef> mAssetRefs = new ArrayList<AssetRef>();

        if (dir.exists()) {
            for (File eDir : dir.listFiles()) {

                if (eDir.isDirectory()) {

                    fu.set_u64(11);
                    fu.set_u8_array(StringView.toBytes(eDir.getName(), 100));

                    long eInicio = fu.getPonteiro();

                    fu.set_u64(0);

                    long eFim = fu.getPonteiro();

                    fu.set_u64(0);

                    mAssetRefs.add(new AssetRef(eDir.getAbsolutePath(), 11, eInicio, eFim));

                } else if (eDir.isFile()) {

                    fu.set_u64(12);
                    fu.set_u8_array(StringView.toBytes(eDir.getName(), 100));

                    long eInicio = fu.getPonteiro();

                    fu.set_u64(0);

                    long eFim = fu.getPonteiro();

                    fu.set_u64(0);

                    mAssetRefs.add(new AssetRef(eDir.getAbsolutePath(), 12, eInicio, eFim));

                }

                // System.out.println(" ->> " + eDir.getName() + " :: "+ eDir.isDirectory());
            }
        }

        fu.set_u64(13);

        //System.out.println(" ALOCANDO : " + eLocal);

        long ePonteiroLocal = fu.getPonteiro();

        for (AssetRef assetRefC : mAssetRefs) {

            System.out.println("\t -->> " + assetRefC.getTipo() + " : " + assetRefC.getNome().replace(eLocal + "\\", ""));

            fu.setPonteiro(ePonteiroLocal);

            if (assetRefC.getTipo() == 11) {

                long ePastaPonteiro_Inicio = fu.getPonteiro();

                criarPasta(fu, assetRefC.getNome());

                long ePastaPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(assetRefC.getInicio());
                fu.set_u64(ePastaPonteiro_Inicio);

                fu.setPonteiro(assetRefC.getFim());
                fu.set_u64(ePastaPonteiro_Fim);

            } else if (assetRefC.getTipo() == 12) {

                long eAquivoPonteiro_Inicio = fu.getPonteiro();


                try {

                    // System.out.println(":: Escrever : " + PontoC.getNome());

                    RandomAccessFile mArquivando = new RandomAccessFile(new File(assetRefC.getNome()), "r");

                    long mAquivandoIndex = 0;
                    long mAquivandoTamanho = mArquivando.length();

                    mArquivando.seek(0);

                    while (mAquivandoIndex < mAquivandoTamanho) {

                        fu.set_u8(mArquivando.readByte());

                        mAquivandoIndex += 1;
                    }

                    mArquivando.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }


                long eAquivoPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(assetRefC.getInicio());
                fu.set_u64(eAquivoPonteiro_Inicio);

                fu.setPonteiro(assetRefC.getFim());
                fu.set_u64(eAquivoPonteiro_Fim);

            }

            fu.setPonteiro(ePonteiroLocal);

        }

    }


    private static void criarPastaCompressed(Arquivador fu, String eLocal) {


        File dir = new File(eLocal);

        ArrayList<AssetRef> mAssetRefs = new ArrayList<AssetRef>();

        if (dir.exists()) {
            for (File eDir : dir.listFiles()) {

                if (eDir.isDirectory()) {

                    fu.set_u64(11);
                    fu.set_u8_array(StringView.toBytes(eDir.getName(), 100));

                    long eInicio = fu.getPonteiro();

                    fu.set_u64(0);

                    long eFim = fu.getPonteiro();

                    fu.set_u64(0);

                    mAssetRefs.add(new AssetRef(eDir.getAbsolutePath(), 11, eInicio, eFim));

                } else if (eDir.isFile()) {

                    fu.set_u64(12);
                    fu.set_u8_array(StringView.toBytes(eDir.getName(), 100));

                    long eInicio = fu.getPonteiro();

                    fu.set_u64(0);

                    long eFim = fu.getPonteiro();

                    fu.set_u64(0);

                    mAssetRefs.add(new AssetRef(eDir.getAbsolutePath(), 12, eInicio, eFim));

                }

                // System.out.println(" ->> " + eDir.getName() + " :: "+ eDir.isDirectory());
            }
        }

        fu.set_u64(13);

        //System.out.println(" ALOCANDO : " + eLocal);

        long ePonteiroLocal = fu.getPonteiro();

        for (AssetRef assetRefC : mAssetRefs) {

            System.out.println("\t -->> " + assetRefC.getTipo() + " : " + assetRefC.getNome().replace(eLocal + "\\", ""));

            fu.setPonteiro(ePonteiroLocal);

            if (assetRefC.getTipo() == 11) {

                long ePastaPonteiro_Inicio = fu.getPonteiro();

                criarPastaCompressed(fu, assetRefC.getNome());

                long ePastaPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(assetRefC.getInicio());
                fu.set_u64(ePastaPonteiro_Inicio);

                fu.setPonteiro(assetRefC.getFim());
                fu.set_u64(ePastaPonteiro_Fim);

            } else if (assetRefC.getTipo() == 12) {


                long eAquivoPonteiro_Inicio = fu.getPonteiro();


                try {

                    RandomAccessFile mArquivando = new RandomAccessFile(new File(assetRefC.getNome()), "r");

                    int mAquivandoIndex = 0;
                    int mAquivandoTamanho = (int) mArquivando.length();

                    mArquivando.seek(0);

                    byte[] mTemporario = new byte[(int) mAquivandoTamanho];

                    while (mAquivandoIndex < mAquivandoTamanho) {

                        mTemporario[mAquivandoIndex] = (mArquivando.readByte());

                        mAquivandoIndex += 1;
                    }

                    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                            gzipOutputStream.write(mTemporario);
                        }

                        byte[] mTemporario2 = byteArrayOutputStream.toByteArray();

                        mAquivandoIndex = 0;
                        mAquivandoTamanho = mTemporario2.length;
                        while (mAquivandoIndex < mAquivandoTamanho) {

                            fu.set_u8(mTemporario2[mAquivandoIndex]);
                            mAquivandoIndex += 1;
                        }


                    } catch (IOException e) {
                        throw new RuntimeException("Failed to zip content", e);
                    }


                    mArquivando.close();


                } catch (IOException e) {

                    e.printStackTrace();
                }


                long eAquivoPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(assetRefC.getInicio());
                fu.set_u64(eAquivoPonteiro_Inicio);

                fu.setPonteiro(assetRefC.getFim());
                fu.set_u64(eAquivoPonteiro_Fim);


                System.out.println("\t -->> Guardando " + assetRefC.getTipo() + " : " + assetRefC.getNome().replace(eLocal + "\\", "") + " -->> " + eAquivoPonteiro_Inicio + " :: " + eAquivoPonteiro_Fim);

            }

            fu.setPonteiro(ePonteiroLocal);

        }

    }

    private static String getTempo() {

        Calendar c = Calendar.getInstance();

        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);

        int hora = c.get(Calendar.HOUR);
        int minutos = c.get(Calendar.MINUTE);
        int segundos = c.get(Calendar.SECOND);

        return dia + "/" + mes + "/" + ano + " " + hora + ":" + minutos + ":" + segundos;

    }


    public static void criarCompressedMontado(String eArquivo, ArrayList<String> maf, ArrayList<String> mpf) {

        String eCabecalho = ASSET_CONTAINER_COMPRESSED;

        String eVersao = "1.0";

        System.out.println("");
        System.out.println("ASSET CONTAINER CREATOR");
        System.out.println("\t - Versao : " + eVersao);


        Arquivador.remover(eArquivo);

        Arquivador fu = new Arquivador(eArquivo);

        fu.limpar();

        fu.inicio();

        fu.set_u8_array(StringView.toBytes(eCabecalho, 100));

        fu.set_u8_array(StringView.toBytes(eVersao, 100));

        long eCriado = fu.getPonteiro();
        fu.set_u8_array(StringView.toBytes(getTempo(), 100));

        long eFinalizado = fu.getPonteiro();
        fu.set_u8_array(StringView.toBytes(getTempo(), 100));

        long r1 = fu.getPonteiro();
        long eApendiceTem = fu.getPonteiro();

        fu.set_u8((byte) 0);

        long r2 = fu.getPonteiro();

        long eApendicePonteiro = fu.getPonteiro();

        fu.set_u64(0);


        if (eCabecalho.contentEquals(ASSET_CONTAINER)) {

            //   criarPastaMontada(fu, maf,mpf);

        } else if (eCabecalho.contentEquals(ASSET_CONTAINER_COMPRESSED)) {

            criarPastaCompressedMontada(fu, maf, mpf);

        }


        //fu.dump();

        long eApendiceInicio = fu.getPonteiro();

        fu.set_u8((byte) 50);

        fu.set_u8((byte) 55);


        fu.setPonteiro(eApendiceTem);
        fu.set_u8((byte) 1);

        fu.setPonteiro(eApendicePonteiro);
        fu.set_u64(eApendiceInicio);

        fu.setPonteiro(eFinalizado);
        fu.set_u8_array(StringView.toBytes(getTempo(), 100));


        fu.encerrar();


    }

    private static void criarPastaCompressedMontada(Arquivador fu, ArrayList<String> maf, ArrayList<String> mpf) {


        ArrayList<AssetRef> mAssetRefs = new ArrayList<AssetRef>();

        for (String eArquivo : maf) {

            fu.set_u64(12);
            fu.set_u8_array(StringView.toBytes(eArquivo, 100));

            long eInicio = fu.getPonteiro();

            fu.set_u64(0);

            long eFim = fu.getPonteiro();

            fu.set_u64(0);

            mAssetRefs.add(new AssetRef(eArquivo, 12, eInicio, eFim));

        }

        for (String ePasta : mpf) {

            fu.set_u64(11);
            fu.set_u8_array(StringView.toBytes(ePasta, 100));

            long eInicio = fu.getPonteiro();

            fu.set_u64(0);

            long eFim = fu.getPonteiro();

            fu.set_u64(0);

            mAssetRefs.add(new AssetRef(ePasta, 11, eInicio, eFim));


        }


        fu.set_u64(13);

        //System.out.println(" ALOCANDO : " + eLocal);

        long ePonteiroLocal = fu.getPonteiro();

        for (AssetRef assetRefC : mAssetRefs) {

            System.out.println("\t -->> " + assetRefC.getTipo() + " : " + assetRefC.getNome());

            fu.setPonteiro(ePonteiroLocal);

            if (assetRefC.getTipo() == 11) {

                long ePastaPonteiro_Inicio = fu.getPonteiro();

                long ePastaPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(assetRefC.getInicio());
                fu.set_u64(ePastaPonteiro_Inicio);

                fu.setPonteiro(assetRefC.getFim());
                fu.set_u64(ePastaPonteiro_Fim);

            } else if (assetRefC.getTipo() == 12) {

                long eAquivoPonteiro_Inicio = fu.getPonteiro();


                long eAquivoPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(assetRefC.getInicio());
                fu.set_u64(eAquivoPonteiro_Inicio);

                fu.setPonteiro(assetRefC.getFim());
                fu.set_u64(eAquivoPonteiro_Fim);

            }

            fu.setPonteiro(ePonteiroLocal);

        }

    }


    public static void adicionarCompressedMontado(String eArquivo, String eLocal, ArrayList<String> maf, ArrayList<String> mpf) {

        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eArquivo);

        String eCabecalho = mAC.getCabecalho();

        //   System.out.println("\n\n ------------------- ADICIONADOR ------------------------ \n\n");
        // mAC.listarTabelaDeArquivos();
        //  mAC.listarTabelaDePastas();

        boolean mEnc = false;

        for (Pasta ePasta : mAC.getTabelaDePastas()) {
            if (ePasta.getNome().contentEquals(eLocal)) {


                if (eCabecalho.contentEquals(ASSET_CONTAINER)) {

                    //   criarPastaMontada(fu, maf,mpf);

                } else if (eCabecalho.contentEquals(ASSET_CONTAINER_COMPRESSED)) {


                    Arquivador fu = new Arquivador(eArquivo);

                    criarSubPastaCompressedMontada(fu, ePasta, maf, mpf);

                    fu.encerrar();


                }

                mEnc = true;
                break;
            }
        }

        if (!mEnc) {
            System.out.println("Pasta nao encontrada : " + eLocal);
        }

    }


    private static void criarSubPastaCompressedMontada(Arquivador fu, Pasta ePastaPai, ArrayList<String> maf, ArrayList<String> mpf) {


        fu.setPonteiro(fu.getLength());

        long ePastaInicio = fu.getPonteiro();


        ArrayList<AssetRef> mAssetRefs = new ArrayList<AssetRef>();

        for (String eArquivo : maf) {

            fu.set_u64(12);
            fu.set_u8_array(StringView.toBytes(eArquivo, 100));

            long eInicio = fu.getPonteiro();

            fu.set_u64(0);

            long eFim = fu.getPonteiro();

            fu.set_u64(0);

            mAssetRefs.add(new AssetRef(eArquivo, 12, eInicio, eFim));

        }

        for (String ePasta : mpf) {

            fu.set_u64(11);
            fu.set_u8_array(StringView.toBytes(ePasta, 100));

            long eInicio = fu.getPonteiro();

            fu.set_u64(0);

            long eFim = fu.getPonteiro();

            fu.set_u64(0);

            mAssetRefs.add(new AssetRef(ePasta, 11, eInicio, eFim));


        }


        fu.set_u64(13);

        long ePastaFim = fu.getPonteiro();


        fu.setPonteiro(ePastaPai.getReferencia().getRef1());
        fu.set_u64(ePastaInicio);

        fu.setPonteiro(ePastaPai.getReferencia().getRef2());
        fu.set_u64(ePastaFim);


    }


    public static void anexarConteudoCompressed(String eLocalArquivo, Arquivo eArquivo, String eConteudo) {


        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);


        Arquivador fu = new Arquivador(eLocalArquivo);

        fu.setPonteiro(fu.getLength());

        long eAquivoPonteiro_Inicio = fu.getPonteiro();


        byte[] mTemporario = eConteudo.getBytes(StandardCharsets.UTF_8);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                gzipOutputStream.write(mTemporario);
            }

            byte[] mTemporario2 = byteArrayOutputStream.toByteArray();

            int mAquivandoIndex = 0;
            int mAquivandoTamanho = mTemporario2.length;
            while (mAquivandoIndex < mAquivandoTamanho) {

                fu.set_u8(mTemporario2[mAquivandoIndex]);
                mAquivandoIndex += 1;
            }


        } catch (IOException e) {
            throw new RuntimeException("Failed to zip content", e);
        }

        long eAquivoPonteiro_Fim = fu.getPonteiro();


        fu.setPonteiro(eArquivo.getReferencia().getRef1());
        fu.set_u64(eAquivoPonteiro_Inicio);

        fu.setPonteiro(eArquivo.getReferencia().getRef2());
        fu.set_u64(eAquivoPonteiro_Fim);


        fu.encerrar();


    }

    public static void anexarConteudo(String eLocalArquivo, Arquivo eArquivo, String eConteudo) {


        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);


        Arquivador fu = new Arquivador(eLocalArquivo);

        fu.setPonteiro(fu.getLength());

        long eAquivoPonteiro_Inicio = fu.getPonteiro();


        byte[] mTemporario = eConteudo.getBytes(StandardCharsets.UTF_8);


        int mAquivandoIndex = 0;
        int mAquivandoTamanho = mTemporario.length;


        mAquivandoIndex = 0;
        mAquivandoTamanho = mTemporario.length;
        while (mAquivandoIndex < mAquivandoTamanho) {

            fu.set_u64(mTemporario[mAquivandoIndex]);
            mAquivandoIndex += 1;
        }


        long eAquivoPonteiro_Fim = fu.getPonteiro();


        fu.setPonteiro(eArquivo.getReferencia().getRef1());
        fu.set_u64(eAquivoPonteiro_Inicio);

        fu.setPonteiro(eArquivo.getReferencia().getRef2());
        fu.set_u64(eAquivoPonteiro_Fim);


        fu.encerrar();


    }


    public static void finalizador(String eLocalArquivo) {

        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);


        Arquivador fu = new Arquivador(eLocalArquivo);

        fu.setPonteiro(fu.getLength());

        long eApendiceInicio = fu.getPonteiro();

        fu.set_u8((byte) 50);

        fu.set_u8((byte) 55);


        fu.setPonteiro(mAC.getExtrumReferencia().getRef1());
        fu.set_u8((byte) 1);

        fu.setPonteiro(mAC.getExtrumReferencia().getRef2());
        fu.set_u64(eApendiceInicio);

        fu.setPonteiro(mAC.getDataReferencia().getRef2());
        fu.set_u8_array(StringView.toBytes(getTempo(), 100));


        fu.encerrar();


    }
}
