package libs.OLLT;

public class TextoDocumento {

	private String mTexto;

	public TextoDocumento() {
		mTexto = "";
	}

	public void AdicionarLinha(String eLinha) {
		mTexto += eLinha + "\n";
	}

	public void adicionarLinha(String eLinha) {
		mTexto += eLinha + "\n";
	}

	public void Adicionar(String eMais) {
		mTexto += eMais;
	}
	public void adicionar(String eMais) {
		mTexto += eMais;
	}
	public String toString() {
		return mTexto;
	}
}
