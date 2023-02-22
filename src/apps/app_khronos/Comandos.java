package apps.app_khronos;

import java.util.ArrayList;

public class Comandos {

    private ArrayList<Comando> mComandos;

    public Comandos() {
        mComandos = new ArrayList<Comando>();

        novo("d", "Descer Visualização");
        novo("s", "Subir Visualização");

        novo("vl0", "Primeira linha");
        novo("vln", "Última linha");


        novo("fmt.no_ident", "Limpar identação");

    }

    public void novo(String comando, String descricao) {
        mComandos.add(new Comando(comando, descricao));
    }


    public ArrayList<Comando> getComandos() {
        return mComandos;
    }

    public ArrayList<Comando> getSugestoes(String iniciar) {

        ArrayList<Comando> ls_sugestoes = new ArrayList<Comando>();

        if (iniciar.length()>0){
            for (Comando c : mComandos) {
                if (c.comecaCom(iniciar)) {
                    ls_sugestoes.add(c);
                }
            }

        }

        return ls_sugestoes;
    }

}
