package AssetContainer;

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

        try {

            RandomAccessFile raf = new RandomAccessFile(new File(eArquivo), "rw");

            FileBinary fu = new FileBinary(raf);

            fu.limpar();

            fu.inicio();

            fu.writeString(eCabecalho);

            fu.writeString(eVersao);

            long eCriado = fu.getPonteiro();
            fu.writeString(getTempo());

            long eFinalizado = fu.getPonteiro();
            fu.writeString(getTempo());

            long eApendiceTem = fu.getPonteiro();

            fu.writeByte((byte) 0);

            long eApendicePonteiro = fu.getPonteiro();

            fu.writeLong(0);

            if (eCabecalho.contentEquals(ASSET_CONTAINER)) {

                criarPasta(fu, eLocal);

            } else if (eCabecalho.contentEquals(ASSET_CONTAINER_COMPRESSED)) {

                criarPastaCompressed(fu, eLocal);

            }


            //fu.dump();

            long eApendiceInicio = fu.getPonteiro();

            fu.writeByte((byte) 50);

            fu.writeByte((byte) 55);


            raf.seek(eApendiceTem);
            fu.writeByte((byte) 1);

            raf.seek(eApendicePonteiro);
            raf.writeLong(eApendiceInicio);

            raf.seek(eFinalizado);
            fu.writeString(getTempo());


            raf.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    private static void criarPasta(FileBinary fu, String eLocal) {


        File dir = new File(eLocal);

        ArrayList<Ponto> mPontos = new ArrayList<Ponto>();

        if (dir.exists()) {
            for (File eDir : dir.listFiles()) {

                if (eDir.isDirectory()) {

                    fu.writeLong(11);
                    fu.writeString(eDir.getName());

                    long eInicio = fu.getPonteiro();

                    fu.writeLong(0);

                    long eFim = fu.getPonteiro();

                    fu.writeLong(0);

                    mPontos.add(new Ponto(eDir.getAbsolutePath(), 11, eInicio, eFim));

                } else if (eDir.isFile()) {

                    fu.writeLong(12);
                    fu.writeString(eDir.getName());

                    long eInicio = fu.getPonteiro();

                    fu.writeLong(0);

                    long eFim = fu.getPonteiro();

                    fu.writeLong(0);

                    mPontos.add(new Ponto(eDir.getAbsolutePath(), 12, eInicio, eFim));

                }

                // System.out.println(" ->> " + eDir.getName() + " :: "+ eDir.isDirectory());
            }
        }

        fu.writeLong(13);

        //System.out.println(" ALOCANDO : " + eLocal);

        long ePonteiroLocal = fu.getPonteiro();

        for (Ponto PontoC : mPontos) {

            System.out.println("\t -->> " + PontoC.getTipo() + " : " + PontoC.getNome().replace(eLocal + "\\", ""));

            fu.setPonteiro(ePonteiroLocal);

            if (PontoC.getTipo() == 11) {

                long ePastaPonteiro_Inicio = fu.getPonteiro();

                criarPasta(fu, PontoC.getNome());

                long ePastaPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(PontoC.getInicio());
                fu.writeLong(ePastaPonteiro_Inicio);

                fu.setPonteiro(PontoC.getFim());
                fu.writeLong(ePastaPonteiro_Fim);

            } else if (PontoC.getTipo() == 12) {

                long eAquivoPonteiro_Inicio = fu.getPonteiro();


                try {

                    RandomAccessFile mArquivando = new RandomAccessFile(new File(PontoC.getNome()), "r");

                    long mAquivandoIndex = 0;
                    long mAquivandoTamanho = mArquivando.length();

                    mArquivando.seek(0);

                    while (mAquivandoIndex < mAquivandoTamanho) {

                        fu.writeByte(mArquivando.readByte());

                        mAquivandoIndex += 1;
                    }

                    mArquivando.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }


                long eAquivoPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(PontoC.getInicio());
                fu.writeLong(eAquivoPonteiro_Inicio);

                fu.setPonteiro(PontoC.getFim());
                fu.writeLong(eAquivoPonteiro_Fim);

            }

            fu.setPonteiro(ePonteiroLocal);

        }

    }


    private static void criarPastaCompressed(FileBinary fu, String eLocal) {


        File dir = new File(eLocal);

        ArrayList<Ponto> mPontos = new ArrayList<Ponto>();

        if (dir.exists()) {
            for (File eDir : dir.listFiles()) {

                if (eDir.isDirectory()) {

                    fu.writeLong(11);
                    fu.writeString(eDir.getName());

                    long eInicio = fu.getPonteiro();

                    fu.writeLong(0);

                    long eFim = fu.getPonteiro();

                    fu.writeLong(0);

                    mPontos.add(new Ponto(eDir.getAbsolutePath(), 11, eInicio, eFim));

                } else if (eDir.isFile()) {

                    fu.writeLong(12);
                    fu.writeString(eDir.getName());

                    long eInicio = fu.getPonteiro();

                    fu.writeLong(0);

                    long eFim = fu.getPonteiro();

                    fu.writeLong(0);

                    mPontos.add(new Ponto(eDir.getAbsolutePath(), 12, eInicio, eFim));

                }

                // System.out.println(" ->> " + eDir.getName() + " :: "+ eDir.isDirectory());
            }
        }

        fu.writeLong(13);

        //System.out.println(" ALOCANDO : " + eLocal);

        long ePonteiroLocal = fu.getPonteiro();

        for (Ponto PontoC : mPontos) {

            System.out.println("\t -->> " + PontoC.getTipo() + " : " + PontoC.getNome().replace(eLocal + "\\", ""));

            fu.setPonteiro(ePonteiroLocal);

            if (PontoC.getTipo() == 11) {

                long ePastaPonteiro_Inicio = fu.getPonteiro();

                criarPastaCompressed(fu, PontoC.getNome());

                long ePastaPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(PontoC.getInicio());
                fu.writeLong(ePastaPonteiro_Inicio);

                fu.setPonteiro(PontoC.getFim());
                fu.writeLong(ePastaPonteiro_Fim);

            } else if (PontoC.getTipo() == 12) {

                long eAquivoPonteiro_Inicio = fu.getPonteiro();


                try {

                    RandomAccessFile mArquivando = new RandomAccessFile(new File(PontoC.getNome()), "r");

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

                            fu.writeByte(mTemporario2[mAquivandoIndex]);
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

                fu.setPonteiro(PontoC.getInicio());
                fu.writeLong(eAquivoPonteiro_Inicio);

                fu.setPonteiro(PontoC.getFim());
                fu.writeLong(eAquivoPonteiro_Fim);

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

        try {

            RandomAccessFile raf = new RandomAccessFile(new File(eArquivo), "rw");

            FileBinary fu = new FileBinary(raf);

            fu.limpar();

            fu.inicio();

            fu.writeString(eCabecalho);

            fu.writeString(eVersao);

            long eCriado = fu.getPonteiro();
            fu.writeString(getTempo());

            long eFinalizado = fu.getPonteiro();
            fu.writeString(getTempo());

            long r1 = fu.getPonteiro();
            long eApendiceTem = fu.getPonteiro();

            fu.writeByte((byte) 0);

            long r2 = fu.getPonteiro();

            long eApendicePonteiro = fu.getPonteiro();

            fu.writeLong(0);


            if (eCabecalho.contentEquals(ASSET_CONTAINER)) {

                //   criarPastaMontada(fu, maf,mpf);

            } else if (eCabecalho.contentEquals(ASSET_CONTAINER_COMPRESSED)) {

                criarPastaCompressedMontada(fu, maf, mpf);

            }


            //fu.dump();

            long eApendiceInicio = fu.getPonteiro();

            fu.writeByte((byte) 50);

            fu.writeByte((byte) 55);


            raf.seek(eApendiceTem);
            fu.writeByte((byte) 1);

            raf.seek(eApendicePonteiro);
            raf.writeLong(eApendiceInicio);

            raf.seek(eFinalizado);
            fu.writeString(getTempo());


            raf.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private static void criarPastaCompressedMontada(FileBinary fu, ArrayList<String> maf, ArrayList<String> mpf) {


        ArrayList<Ponto> mPontos = new ArrayList<Ponto>();

        for (String eArquivo : maf) {

            fu.writeLong(12);
            fu.writeString(eArquivo);

            long eInicio = fu.getPonteiro();

            fu.writeLong(0);

            long eFim = fu.getPonteiro();

            fu.writeLong(0);

            mPontos.add(new Ponto(eArquivo, 12, eInicio, eFim));

        }

        for (String ePasta : mpf) {

            fu.writeLong(11);
            fu.writeString(ePasta);

            long eInicio = fu.getPonteiro();

            fu.writeLong(0);

            long eFim = fu.getPonteiro();

            fu.writeLong(0);

            mPontos.add(new Ponto(ePasta, 11, eInicio, eFim));


        }


        fu.writeLong(13);

        //System.out.println(" ALOCANDO : " + eLocal);

        long ePonteiroLocal = fu.getPonteiro();

        for (Ponto PontoC : mPontos) {

            System.out.println("\t -->> " + PontoC.getTipo() + " : " + PontoC.getNome());

            fu.setPonteiro(ePonteiroLocal);

            if (PontoC.getTipo() == 11) {

                long ePastaPonteiro_Inicio = fu.getPonteiro();

                long ePastaPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(PontoC.getInicio());
                fu.writeLong(ePastaPonteiro_Inicio);

                fu.setPonteiro(PontoC.getFim());
                fu.writeLong(ePastaPonteiro_Fim);

            } else if (PontoC.getTipo() == 12) {

                long eAquivoPonteiro_Inicio = fu.getPonteiro();


                long eAquivoPonteiro_Fim = fu.getPonteiro();

                ePonteiroLocal = fu.getPonteiro();

                fu.setPonteiro(PontoC.getInicio());
                fu.writeLong(eAquivoPonteiro_Inicio);

                fu.setPonteiro(PontoC.getFim());
                fu.writeLong(eAquivoPonteiro_Fim);

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


                    try {

                        RandomAccessFile raf = new RandomAccessFile(new File(eArquivo), "rw");

                        FileBinary fu = new FileBinary(raf);

                        criarSubPastaCompressedMontada(fu, ePasta, maf, mpf);

                        raf.close();

                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                }

                mEnc = true;
                break;
            }
        }

        if (!mEnc) {
            System.out.println("Pasta nao encontrada : " + eLocal);
        }

    }


    private static void criarSubPastaCompressedMontada(FileBinary fu, Pasta ePastaPai, ArrayList<String> maf, ArrayList<String> mpf) {


        fu.setPonteiro(fu.getLength());

        long ePastaInicio = fu.getPonteiro();


        ArrayList<Ponto> mPontos = new ArrayList<Ponto>();

        for (String eArquivo : maf) {

            fu.writeLong(12);
            fu.writeString(eArquivo);

            long eInicio = fu.getPonteiro();

            fu.writeLong(0);

            long eFim = fu.getPonteiro();

            fu.writeLong(0);

            mPontos.add(new Ponto(eArquivo, 12, eInicio, eFim));

        }

        for (String ePasta : mpf) {

            fu.writeLong(11);
            fu.writeString(ePasta);

            long eInicio = fu.getPonteiro();

            fu.writeLong(0);

            long eFim = fu.getPonteiro();

            fu.writeLong(0);

            mPontos.add(new Ponto(ePasta, 11, eInicio, eFim));


        }


        fu.writeLong(13);

        long ePastaFim = fu.getPonteiro();


        fu.setPonteiro(ePastaPai.getReferencia().getRef1());
        fu.writeLong(ePastaInicio);

        fu.setPonteiro(ePastaPai.getReferencia().getRef2());
        fu.writeLong(ePastaFim);


    }


    public static void anexarConteudoCompressed(String eLocalArquivo, Arquivo eArquivo, String eConteudo) {


        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);

        try {

            RandomAccessFile raf = new RandomAccessFile(new File(eLocalArquivo), "rw");

            FileBinary fu = new FileBinary(raf);

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

                    fu.writeByte(mTemporario2[mAquivandoIndex]);
                    mAquivandoIndex += 1;
                }


            } catch (IOException e) {
                throw new RuntimeException("Failed to zip content", e);
            }

            long eAquivoPonteiro_Fim = fu.getPonteiro();


            fu.setPonteiro(eArquivo.getReferencia().getRef1());
            fu.writeLong(eAquivoPonteiro_Inicio);

            fu.setPonteiro(eArquivo.getReferencia().getRef2());
            fu.writeLong(eAquivoPonteiro_Fim);


            raf.close();

        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public static void anexarConteudo(String eLocalArquivo, Arquivo eArquivo, String eConteudo) {


        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);

        try {

            RandomAccessFile raf = new RandomAccessFile(new File(eLocalArquivo), "rw");

            FileBinary fu = new FileBinary(raf);

            fu.setPonteiro(fu.getLength());

            long eAquivoPonteiro_Inicio = fu.getPonteiro();


            byte[] mTemporario = eConteudo.getBytes(StandardCharsets.UTF_8);


            int mAquivandoIndex = 0;
            int mAquivandoTamanho = mTemporario.length;


            mAquivandoIndex = 0;
            mAquivandoTamanho = mTemporario.length;
            while (mAquivandoIndex < mAquivandoTamanho) {

                fu.writeByte(mTemporario[mAquivandoIndex]);
                mAquivandoIndex += 1;
            }


            long eAquivoPonteiro_Fim = fu.getPonteiro();


            fu.setPonteiro(eArquivo.getReferencia().getRef1());
            fu.writeLong(eAquivoPonteiro_Inicio);

            fu.setPonteiro(eArquivo.getReferencia().getRef2());
            fu.writeLong(eAquivoPonteiro_Fim);


            raf.close();

        } catch (IOException e) {

            e.printStackTrace();
        }


    }


    public static void finalizador(String eLocalArquivo) {

        AssetContainer mAC = new AssetContainer();
        mAC.abrir(eLocalArquivo);

        try {

            RandomAccessFile raf = new RandomAccessFile(new File(eLocalArquivo), "rw");

            FileBinary fu = new FileBinary(raf);

            fu.setPonteiro(fu.getLength());

            long eApendiceInicio = fu.getPonteiro();

            fu.writeByte((byte) 50);

            fu.writeByte((byte) 55);


            raf.seek(mAC.getExtrumReferencia().getRef1());
            fu.writeByte((byte) 1);

            raf.seek(mAC.getExtrumReferencia().getRef2());
            raf.writeLong(eApendiceInicio);

            raf.seek(mAC.getDataReferencia().getRef2());
            fu.writeString(getTempo());


            raf.close();

        } catch (IOException e) {

            e.printStackTrace();
        }


    }
}
