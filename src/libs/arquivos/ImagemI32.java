package libs.arquivos;

import libs.azzal.utilitarios.Cor;

public class ImagemI32 {

    private int mLargura;
    private int mAltura;
    private int[] mDados;

    public ImagemI32(int eLargura, int eAltura) {
        mLargura = eLargura;
        mAltura = eAltura;
        mDados = new int[eLargura * eAltura];
    }

    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public void set(int x, int y, int v) {
        mDados[x + (y * mLargura)] = v;
    }

    public int get(int x, int y) {
        return mDados[x + (y * mLargura)];
    }


    public Cor getRGB(int px, int py) {
        return Cor.int32_to_cor(mDados[(mLargura * py) + px]);
    }

    public int getIntPixel(int px, int py) {
        return mDados[(mLargura * py) + px];
    }


}
