package apps.app_citatte.cidade_beta;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.imagem.Imagem;
import libs.luan.Lista;

public class BetaUnificar {


    public static void unir() {


        String local_assets = "/home/luan/assets";

        String arquivo_alfa = local_assets + "/citatte_alfa.dkg";
        String arquivo_beta = local_assets + "/citatte_beta.dkg";
        String arquivo_gamma = local_assets + "/citatte_gamma.dkg";
        String arquivo_delta = local_assets + "/citatte_delta.dkg";
        String arquivo_epsilon = local_assets + "/citatte_epsilon.dkg";

        Cores mCores = new Cores();

        Renderizador mCidade = new Renderizador(Imagem.criarEmBranco(1500, 800));
        mCidade.drawRect_Pintado(0, 0, 1500, 800, mCores.getPreto());


        CitatteBetaConstrutor cc = new CitatteBetaConstrutor();


        cc.carregar_citatte(arquivo_alfa, mCidade);
        cc.carregar_citatte(arquivo_beta, mCidade);
        cc.carregar_citatte(arquivo_gamma, mCidade);
        cc.carregar_citatte(arquivo_delta, mCidade);
        cc.carregar_citatte(arquivo_epsilon, mCidade);


        String arquivo_unica = local_assets + "/citatte_unica.dkg";

        Lista<Ponto> habitavel = new Lista<Ponto>();
        Lista<Ponto> rua = new Lista<Ponto>();
        Lista<Ponto> avenida = new Lista<Ponto>();

        cc.carregar_em(arquivo_alfa, habitavel, rua, avenida);
        cc.carregar_em(arquivo_beta, habitavel, rua, avenida);
        cc.carregar_em(arquivo_gamma, habitavel, rua, avenida);
        cc.carregar_em(arquivo_delta, habitavel, rua, avenida);
        cc.carregar_em(arquivo_epsilon, habitavel, rua, avenida);


        habitavel = cc.organizar_habitavel(habitavel, rua, avenida);

        cc.guardar_em(arquivo_unica, habitavel, rua, avenida);

        System.out.println("GUARDADO PRONTO !");

    }


}
