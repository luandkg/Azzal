package apps.app_palkum;

import apps.app_humanidade.Idioma;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Aleatorio;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.Unico;

public class PessoasCriadorDeNomes {

    private Idioma mIdioma;
    private Lista<Entidade> mSobrenomes1;
    private Lista<Entidade> mSobrenomes2;
    private Unico<String> sobrenomes_validos;

    private Lista<String> entre;

    public PessoasCriadorDeNomes(Idioma eIdioma,Lista<Entidade> sobrenomes_1, Lista<Entidade> sobrenomes_2) {

        mIdioma=eIdioma;
        mSobrenomes1 = sobrenomes_1;
        mSobrenomes2 = sobrenomes_2;

        sobrenomes_validos = new Unico<String>(Strings.IGUALAVEL());
      //  entre = Lista.CRIAR("et", "voz", "duc", "maz","uq","gi");

        sobrenomes_validos.item_varios(ENTT.VALORES_DE(mSobrenomes1, "Sobrenome"));
        sobrenomes_validos.item_varios(ENTT.VALORES_DE(mSobrenomes2, "Sobrenome"));

    }

    public String get() {

        String s = "";
        int v = Aleatorio.aleatorio(150);


        if (v > 50) {
            if (v > 120) {
                // + " " + Aleatorio.escolha_um(entre)  + " " + Aleatorio.escolha_um(sobrenomes_validos);
                s = mIdioma.getUnico(sobrenomes_validos) + " " + Aleatorio.escolha_um(mSobrenomes1).at("Sobrenome") + " " + Aleatorio.escolha_um(mSobrenomes2).at("Sobrenome") ;
            } else {
                s = mIdioma.getUnico(sobrenomes_validos)+ " " + Aleatorio.escolha_um(mSobrenomes1).at("Sobrenome") + " " + Aleatorio.escolha_um(mSobrenomes2).at("Sobrenome");
            }
        } else {
            s = mIdioma.getUnico(sobrenomes_validos)+ " " + Aleatorio.escolha_um(mSobrenomes2).at("Sobrenome") + " " + Aleatorio.escolha_um(mSobrenomes1).at("Sobrenome");
        }

        return s;
    }

}
