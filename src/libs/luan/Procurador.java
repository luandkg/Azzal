package libs.luan;

import libs.arquivos.DocumentoTexto;

public class Procurador<T> {


    private IgualdadeTiposDiferentes<String, T> mIgualdade;

    public Procurador(IgualdadeTiposDiferentes<String, T> eIgualdade) {
        mIgualdade = eIgualdade;
    }


    public Opcional<T> procure(String procure, Lista<T> eLista) {

        for (T item : eLista) {
            if (mIgualdade.isIgual(procure,item)) {
                return Opcional.OK(item);
            }
        }

        return Opcional.CANCEL();
    }

}
