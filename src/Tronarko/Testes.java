package Tronarko;

import hipertestes.HiperTestador;
import hipertestes.Teste;

public class Testes {

    public static void init() {

        HiperTestador testar = new HiperTestador();

        testar.adicionar(Teste_Tronarko_Init());
        testar.adicionar(Teste_Tronarko_Toztes());
        testar.adicionar(Teste_Tronarko_Hazde());

        testar.realizar_testes();


    }


    public static Teste Teste_Tronarko_Init() {
        return new Teste("Teste_Tronarko_Init") {
            @Override
            public void executar() {

                CHECK_EQ(Tronarko.getData(27, 7, 1992).getTexto(), Tronarko.make_tozte(49, 9, 6980).getTexto());
                CHECK_EQ(Tronarko.getData(1, 1, 2022).getTexto(), Tronarko.make_tozte(49, 4, 7002).getTexto());


            }
        };
    }

    public static Teste Teste_Tronarko_Toztes() {
        return new Teste("Teste_Tronarko_Toztes") {
            @Override
            public void executar() {

                Tozte t1 = Tronarko.make_tozte(1, 1, 7000);


                Tozte t1_mais_1_superarko = t1.adicionar_Superarko(1);
                CHECK_EQ(Tronarko.make_tozte(2, 1, 7000).getTexto(), t1_mais_1_superarko.getTexto());

                Tozte t1_menos_1_superarko = t1.adicionar_Superarko(-1);
                CHECK_EQ(Tronarko.make_tozte(50, 10, 6999).getTexto(), t1_menos_1_superarko.getTexto());

                Tozte t1_mais_1_hiperarko = t1.adicionar_Hiperarko(1);
                CHECK_EQ(Tronarko.make_tozte(1, 2, 7000).getTexto(), t1_mais_1_hiperarko.getTexto());

                Tozte t1_mais_1_tronarko = t1.adicionar_Tronarko(1);
                CHECK_EQ(Tronarko.make_tozte(1, 1, 7001).getTexto(), t1_mais_1_tronarko.getTexto());

                Tozte t1_mais_50_superarkos = t1.adicionar_Superarko(50);
                CHECK_EQ(Tronarko.make_tozte(1, 2, 7000).getTexto(), t1_mais_50_superarkos.getTexto());

                Tozte t1_menos_50_superarkos = t1.adicionar_Superarko(-50);
                CHECK_EQ(Tronarko.make_tozte(1, 10, 6999).getTexto(), t1_menos_50_superarkos.getTexto());

                CHECK_EQ("DAZTO", Tronarko.make_tozte(1, 1, 7000).Hiperarko_nome());
                CHECK_EQ("HERTO", Tronarko.make_tozte(1, 2, 7000).Hiperarko_nome());
                CHECK_EQ("PURGO", Tronarko.make_tozte(1, 3, 7000).Hiperarko_nome());
                CHECK_EQ("NOPTO", Tronarko.make_tozte(1, 4, 7000).Hiperarko_nome());
                CHECK_EQ("FENCO", Tronarko.make_tozte(1, 5, 7000).Hiperarko_nome());
                CHECK_EQ("MOZTO", Tronarko.make_tozte(1, 6, 7000).Hiperarko_nome());
                CHECK_EQ("CARGO", Tronarko.make_tozte(1, 7, 7000).Hiperarko_nome());
                CHECK_EQ("RIZNO", Tronarko.make_tozte(1, 8, 7000).Hiperarko_nome());
                CHECK_EQ("SACNO", Tronarko.make_tozte(1, 9, 7000).Hiperarko_nome());
                CHECK_EQ("TORNO", Tronarko.make_tozte(1, 10, 7000).Hiperarko_nome());


                CHECK_EQ(1, Tronarko.make_tozte(1, 1, 7000).getHiperarko());
                CHECK_EQ(2, Tronarko.make_tozte(1, 2, 7000).getHiperarko());
                CHECK_EQ(3, Tronarko.make_tozte(1, 3, 7000).getHiperarko());
                CHECK_EQ(4, Tronarko.make_tozte(1, 4, 7000).getHiperarko());
                CHECK_EQ(5, Tronarko.make_tozte(1, 5, 7000).getHiperarko());
                CHECK_EQ(6, Tronarko.make_tozte(1, 6, 7000).getHiperarko());
                CHECK_EQ(7, Tronarko.make_tozte(1, 7, 7000).getHiperarko());
                CHECK_EQ(8, Tronarko.make_tozte(1, 8, 7000).getHiperarko());
                CHECK_EQ(9, Tronarko.make_tozte(1, 9, 7000).getHiperarko());
                CHECK_EQ(10, Tronarko.make_tozte(1, 10, 7000).getHiperarko());

                CHECK_TRUE(Tronarko.make_tozte(1, 1, 7000).isOk());

            }
        };
    }


    public static Teste Teste_Tronarko_Hazde() {
        return new Teste("Teste_Tronarko_Hazde") {
            @Override
            public void executar() {


                CHECK_EQ(Tronarko.getHora(0, 0, 0).getTexto(), Tronarko.make_hazde(0, 0, 0).getTexto());
                CHECK_EQ(Tronarko.getHora(12, 0, 0).getTexto(), Tronarko.make_hazde(5, 0, 0).getTexto());
                CHECK_EQ(Tronarko.getHora(24, 0, 0).getTexto(), Tronarko.make_hazde(10, 0, 0).getTexto());

                Hazde h1 = Tronarko.make_hazde(0, 0, 0);

                Hazde h2 = h1.adicionar_Arco(1);
                CHECK_EQ(Tronarko.make_hazde(1, 0, 0).getTexto(), h2.getTexto());

                Hazde h3 = h1.adicionar_Itta(150);
                CHECK_EQ(Tronarko.make_hazde(1, 50, 0).getTexto(), h3.getTexto());

                Hazde h4 = h1.adicionar_Itta(800);
                CHECK_EQ(Tronarko.make_hazde(8, 0, 0).getTexto(), h4.getTexto());

                CHECK_EQ("UD", Tronarko.make_hazde(0, 0, 0).getPeriarko_Valor());
                CHECK_EQ("UD", Tronarko.make_hazde(1, 0, 0).getPeriarko_Valor());
                CHECK_EQ("AD", Tronarko.make_hazde(2, 0, 0).getPeriarko_Valor());
                CHECK_EQ("AD", Tronarko.make_hazde(3, 0, 0).getPeriarko_Valor());
                CHECK_EQ("AD", Tronarko.make_hazde(4, 0, 0).getPeriarko_Valor());
                CHECK_EQ("ED", Tronarko.make_hazde(5, 0, 0).getPeriarko_Valor());
                CHECK_EQ("ED", Tronarko.make_hazde(6, 0, 0).getPeriarko_Valor());
                CHECK_EQ("ED", Tronarko.make_hazde(7, 0, 0).getPeriarko_Valor());
                CHECK_EQ("OD", Tronarko.make_hazde(8, 0, 0).getPeriarko_Valor());
                CHECK_EQ("OD", Tronarko.make_hazde(9, 0, 0).getPeriarko_Valor());

                CHECK_EQ("OZZ", Tronarko.make_hazde(0, 0, 0).getModarko_Valor());
                CHECK_EQ("OZZ", Tronarko.make_hazde(1, 0, 0).getModarko_Valor());
                CHECK_EQ("AZZ", Tronarko.make_hazde(2, 0, 0).getModarko_Valor());
                CHECK_EQ("AZZ", Tronarko.make_hazde(3, 0, 0).getModarko_Valor());
                CHECK_EQ("AZZ", Tronarko.make_hazde(4, 0, 0).getModarko_Valor());
                CHECK_EQ("AZZ", Tronarko.make_hazde(5, 0, 0).getModarko_Valor());
                CHECK_EQ("AZZ", Tronarko.make_hazde(6, 0, 0).getModarko_Valor());
                CHECK_EQ("AZZ", Tronarko.make_hazde(7, 0, 0).getModarko_Valor());
                CHECK_EQ("OZZ", Tronarko.make_hazde(8, 0, 0).getModarko_Valor());
                CHECK_EQ("OZZ", Tronarko.make_hazde(9, 0, 0).getModarko_Valor());

            }
        };
    }


}
