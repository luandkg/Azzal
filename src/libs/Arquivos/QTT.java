package libs.Arquivos;

import libs.Arquivos.Binario.Arquivador;

public class QTT {

    public static void guardar(String eArquivo, int eLargura, int eAltura, int[] valores) {


        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo);

        TX eTX = new TX();

        arquivador.writeByte((byte) eTX.getIndice("Q"));
        arquivador.writeByte((byte) eTX.getIndice("T"));
        arquivador.writeByte((byte) eTX.getIndice("T"));
        arquivador.writeByte((byte) eTX.getIndice("0"));
        arquivador.writeByte((byte) eTX.getIndice("1"));

        arquivador.writeByte((byte) 100);

        arquivador.writeInt(eLargura);
        arquivador.writeInt(eAltura);

        arquivador.writeByte((byte) 100);

        for (int y = 0; y < eAltura; y++) {
            for (int x = 0; x < eLargura; x++) {

                int apontar = (y * eLargura) + x;
                arquivador.writeInt(valores[apontar]);

            }
        }


        arquivador.encerrar();

    }

    public static void guardar(String eArquivo, QTT eQTT) {


        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo, "rw");

        TX eTX = new TX();

        arquivador.writeByte((byte) eTX.getIndice("Q"));
        arquivador.writeByte((byte) eTX.getIndice("T"));
        arquivador.writeByte((byte) eTX.getIndice("T"));
        arquivador.writeByte((byte) eTX.getIndice("0"));
        arquivador.writeByte((byte) eTX.getIndice("1"));

        arquivador.writeByte((byte) 100);

        arquivador.writeInt(eQTT.getLargura());
        arquivador.writeInt(eQTT.getAltura());

        arquivador.writeByte((byte) 100);

        for (int y = 0; y < eQTT.getAltura(); y++) {
            for (int x = 0; x < eQTT.getLargura(); x++) {

                arquivador.writeInt(eQTT.getValor(x, y));

            }
        }


        arquivador.encerrar();

    }

    public static int pegar(String eArquivo, int x, int y) {

        int valor = 0;

        Arquivador arquivador = new Arquivador(eArquivo, "r");
        arquivador.setPonteiro(0);

        byte p1 = arquivador.readByte();
        byte p2 = arquivador.readByte();
        byte p3 = arquivador.readByte();
        byte p4 = arquivador.readByte();
        byte p5 = arquivador.readByte();

        byte z1 = arquivador.readByte();

        int largura = arquivador.readInt();
        int altura = arquivador.readInt();

        byte z2 = arquivador.readByte();

        long ePonteiroInicio = arquivador.getPonteiro();

        if (x >= 0 && y >= 0 && x < largura && y < altura) {

            int apontar = ((y * largura) + x) * 4;

            arquivador.setPonteiro(ePonteiroInicio + apontar);
            valor = arquivador.readInt();

            //  System.out.println("x : " + x + " y : " + y + " -->> pt(" + arquivador.getPonteiro() + ") = " + valor);
        }

        arquivador.encerrar();

        return valor;
    }

    public static void alterar(String eArquivo, int x, int y, int eValor) {

        Arquivador arquivador = new Arquivador(eArquivo, "rw");
        arquivador.setPonteiro(0);

        byte p1 = arquivador.readByte();
        byte p2 = arquivador.readByte();
        byte p3 = arquivador.readByte();
        byte p4 = arquivador.readByte();
        byte p5 = arquivador.readByte();

        byte z1 = arquivador.readByte();

        int largura = arquivador.readInt();
        int altura = arquivador.readInt();

        byte z2 = arquivador.readByte();

        long ePonteiroInicio = arquivador.getPonteiro();

        if (x >= 0 && y >= 0 && x < largura && y < altura) {

            int apontar = ((y * largura) + x) * 4;

            arquivador.setPonteiro(ePonteiroInicio + apontar);
            arquivador.writeInt(eValor);

        }

        arquivador.encerrar();

    }

    private int mLargura;
    private int mAltura;
    private int[] valores;

    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public void setValor(int x, int y, int valor) {
        valores[(y * mLargura) + x] = valor;
    }

    public int getValor(int x, int y) {
        return valores[(y * mLargura) + x];
    }

    public void setLargura(int eLargura) {
        mLargura = eLargura;
    }

    public void setAltura(int eAltura) {
        mAltura = eAltura;
    }

    public void criarBuffer() {
        valores = new int[mLargura * mAltura];
    }

    public static QTT getTudo(String eArquivo) {

        QTT eQTT = new QTT();

        //System.out.println("QTT - ObterTudo");

        Arquivador arquivador = new Arquivador(eArquivo, "r");
        arquivador.setPonteiro(0);

        byte p1 = arquivador.readByte();
        byte p2 = arquivador.readByte();
        byte p3 = arquivador.readByte();
        byte p4 = arquivador.readByte();
        byte p5 = arquivador.readByte();

        byte z1 = arquivador.readByte();

        int largura = arquivador.readInt();
        int altura = arquivador.readInt();

        byte z2 = arquivador.readByte();


        eQTT.setLargura(largura);
        eQTT.setAltura(altura);

        eQTT.criarBuffer();

        // System.out.println("\t - Largura :: " + largura);
        // System.out.println("\t - Altura  :: " + altura);

        long ePonteiroInicio = arquivador.getPonteiro();

        // System.out.println("\t - Inicio  :: " + ePonteiroInicio);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                int apontar = ((y * largura) + x) * 4;

                arquivador.setPonteiro(ePonteiroInicio + apontar);
                int valor = arquivador.readInt();


                eQTT.setValor(x, y, valor);

                //  System.out.println("\t - " + (ePonteiroInicio + apontar) + " :: (" + x + "," + y + ") -->> " + valor);

            }
        }


        arquivador.encerrar();

        //  System.out.println("QTT - Obtido");

        return eQTT;

    }

    public static QTT criar(int eLargura,int eAltura) {

        QTT eQTT = new QTT();

        eQTT.setLargura(eLargura);
        eQTT.setAltura(eAltura);

        eQTT.criarBuffer();

        return eQTT;
    }


}
