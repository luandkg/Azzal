package Azzal.Cenarios;

import Azzal.Renderizador;
import Azzal.Windows;


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

	public Windows getWindows(){return mWindows;}
	public void setWindows(Windows eWindows){ mWindows=eWindows;}

}
