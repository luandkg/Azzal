package libs.arquivos;

import libs.arquivos.binario.Arquivador;

public class QTTDouble {

    private int mLargura;
    private int mAltura;
    private double[] valores;

    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public void setValor(int x, int y, double valor) {
        valores[(y * mLargura) + x] = valor;
    }

    public double getValor(int x, int y) {
        return valores[(y * mLargura) + x];
    }

    public void setLargura(int eLargura) {
        mLargura = eLargura;
    }

    public void setAltura(int eAltura) {
        mAltura = eAltura;
    }

    public void criarBuffer() {
        valores = new double[mLargura * mAltura];
    }



    public static void guardar(String eArquivo, int eLargura, int eAltura, double[] valores) {


        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo);

        TX eTX = new TX();

        arquivador.set_u8((byte) eTX.getIndice("Q"));
        arquivador.set_u8((byte) eTX.getIndice("T"));
        arquivador.set_u8((byte) eTX.getIndice("T"));
        arquivador.set_u8((byte) eTX.getIndice("D"));
        arquivador.set_u8((byte) eTX.getIndice("1"));

        arquivador.set_u8((byte) 100);

        arquivador.set_u32(eLargura);
        arquivador.set_u32(eAltura);

        arquivador.set_u8((byte) 100);

        for (int y = 0; y < eAltura; y++) {
            for (int x = 0; x < eLargura; x++) {

                int apontar = (y * eLargura) + x;
                arquivador.set_double(valores[apontar]);

            }
        }


        arquivador.encerrar();

    }

    public static QTTDouble getTudo(String eArquivo) {

        QTTDouble eQTT = new QTTDouble();

        //System.out.println("QTT - ObterTudo");

        Arquivador arquivador = new Arquivador(eArquivo, "r");
        arquivador.setPonteiro(0);

        byte p1 = arquivador.get();
        byte p2 = arquivador.get();
        byte p3 = arquivador.get();
        byte p4 = arquivador.get();
        byte p5 = arquivador.get();

        byte z1 = arquivador.get();

        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();

        byte z2 = arquivador.get();


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
                double valor = arquivador.get_double();


                eQTT.setValor(x, y, valor);

                //  System.out.println("\t - " + (ePonteiroInicio + apontar) + " :: (" + x + "," + y + ") -->> " + valor);

            }
        }


        arquivador.encerrar();

        //  System.out.println("QTT - Obtido");

        return eQTT;

    }

    public static QTTDouble criar(int eLargura, int eAltura) {

        QTTDouble eQTT = new QTTDouble();

        eQTT.setLargura(eLargura);
        eQTT.setAltura(eAltura);

        eQTT.criarBuffer();

        return eQTT;
    }

    public static void alocar(String arquivo,int eLargura, int eAltura) {
        QTTDouble dados = QTTDouble.criar(eLargura,eAltura);
        QTTDouble.guardar(arquivo,dados);
    }


    public static void guardar(String eArquivo, QTTDouble eQTT) {


        Arquivador.remover(eArquivo);


        Arquivador arquivador = new Arquivador(eArquivo, "rw");

        TX eTX = new TX();

        arquivador.set_u8((byte) eTX.getIndice("Q"));
        arquivador.set_u8((byte) eTX.getIndice("T"));
        arquivador.set_u8((byte) eTX.getIndice("T"));
        arquivador.set_u8((byte) eTX.getIndice("D"));
        arquivador.set_u8((byte) eTX.getIndice("1"));

        arquivador.set_u8((byte) 100);

        arquivador.set_u32(eQTT.getLargura());
        arquivador.set_u32(eQTT.getAltura());

        arquivador.set_u8((byte) 100);

        for (int y = 0; y < eQTT.getAltura(); y++) {
            for (int x = 0; x < eQTT.getLargura(); x++) {

                arquivador.set_double(eQTT.getValor(x, y));

            }
        }


        arquivador.encerrar();

    }

}
