package azzal.Cenarios;

import azzal.Renderizador;
import azzal.Windows;


public abstract class Cena {

    private String mNome;
    private Windows mWindows;

    public void setNome(String eNome) {
        this.mNome = eNome;
    }

    // Propriedades Importantes

    public String getNome() {
        return mNome;
    }

    // Metodos Importantes

    public abstract void iniciar(Windows eWindows);

    public abstract void update(double dt);

    public abstract void draw(Renderizador g);

    public Windows getWindows() {
        return mWindows;
    }

    public void setWindows(Windows eWindows) {
        mWindows = eWindows;
    }

    public int getMx() {
        return (int) getWindows().getMouse().getX();
    }

    public int getMy() {
        return (int) getWindows().getMouse().getY();
    }

    public boolean isPressionsado() {
        return getWindows().getMouse().isPressed();
    }

    public boolean isClicado() {
        return getWindows().getMouse().isClicked();
    }

}
