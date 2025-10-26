package apps.app_tozterum.stravamentos;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tempo.Data;
import libs.tronarko.Tozte;
import libs.tronarko.utils.StringTronarko;

public class StravaAnaliticamente {

    public static double TRONARKO_GET_DISTANCIA(Lista<Entidade> strava_objetos, int tronarko, int hiperarko) {

        double distancia = 0;

        for (Entidade strava : strava_objetos) {
            Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
            if (eTozte.getTronarko() == tronarko && eTozte.getHiperarko() == hiperarko) {

                String s_distancia = strava.at("Distancia").replace(" km", "");
                //  fmt.print("++ {} ->> {}",  strava.at("Distancia"),s_distancia);

                distancia += Double.parseDouble(s_distancia);
                //  fmt.print("++ {} ->> {}", tempo,ponto_aqui);

            }
        }


        return distancia;
    }

    public static double TRONARKO_MEGA_GET_DISTANCIA(Lista<Entidade> strava_objetos, int tronarko, int hiperarko, int megaarko) {

        double distancia = 0;

        for (Entidade strava : strava_objetos) {
            Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
            if (eTozte.getTronarko() == tronarko && eTozte.getHiperarko() == hiperarko && eTozte.getMegarko() == megaarko) {

                String s_distancia = strava.at("Distancia").replace(" km", "");
                //  fmt.print("++ {} ->> {}",  strava.at("Distancia"),s_distancia);

                distancia += Double.parseDouble(s_distancia);
                //  fmt.print("++ {} ->> {}", tempo,ponto_aqui);

            }
        }


        return distancia;
    }



    public static int TRONARKO_GET_TREINOS(Lista<Entidade> strava_objetos, int tronarko, int hiperarko) {

        int treinos = 0;

        for (Entidade strava : strava_objetos) {
            Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
            if (eTozte.getTronarko() == tronarko && eTozte.getHiperarko() == hiperarko) {
                treinos+=1;
            }
        }


        return treinos;
    }

    public static int TRONARKO_MEGA_GET_TREINOS(Lista<Entidade> strava_objetos, int tronarko, int hiperarko, int megaarko) {

        int treinos = 0;

        for (Entidade strava : strava_objetos) {
            Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
            if (eTozte.getTronarko() == tronarko && eTozte.getHiperarko() == hiperarko && eTozte.getMegarko() == megaarko) {

                //  fmt.print("++ {} ->> {}",  strava.at("Distancia"),s_distancia);

                treinos+=1;
                //  fmt.print("++ {} ->> {}", tempo,ponto_aqui);

            }
        }


        return treinos;
    }



    public static Lista<Entidade> GET_TOZTES_HIPERARKO(Lista<Entidade> strava_objetos, int eTronarko, int eHiperarko) {

        Lista<Entidade> ret = new Lista<Entidade>();

        for (Entidade strava : strava_objetos) {
            Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
            if (eTozte.getTronarko() == eTronarko && eTozte.getHiperarko() == eHiperarko) {
                ret.adicionar(strava);
            }
        }

        return ret;
    }


    public static Lista<Par<RefInt, RefInt>> GET_HIPERARKOS(Lista<Entidade> strava_objetos) {

        Unico<String> unicos = new Unico<String>(Strings.IGUALAVEL());
        Lista<Par<RefInt, RefInt>> ret = new Lista<Par<RefInt, RefInt>>();

        for (Entidade strava : strava_objetos) {
            Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));

            String tronarko_hiperarko = eTozte.getTronarko() + "_" + eTozte.getHiperarko();

            if (unicos.item(tronarko_hiperarko)) {
                ret.adicionar(new Par<RefInt, RefInt>(new RefInt(eTozte.getTronarko()), new RefInt(eTozte.getHiperarko())));
            }

        }

        return ret;
    }


    public static int PONTUAR_DISTANCIA(Lista<Entidade> strava_objetos, int ano, int mes) {

        int pontos = 0;

        for (Entidade strava : ENTT.COLETAR(strava_objetos, "Tipo", "CORRIDA")) {
            Data data = Data.toData(strava.at("Data"));
            if (data.getAno() == ano && data.getMes() == mes) {

                String distancia = strava.at("Distancia").trim();

                int ponto_aqui = 0;

                if (distancia.contains("km")) {
                    distancia = distancia.replace(" km", "");
                    if (distancia.length() > 0) {
                        double valor = Double.parseDouble(distancia) * 1000.0;
                        ponto_aqui += (int) valor;
                    }
                }

                pontos += (ponto_aqui);
                //  fmt.print("++ {} ->> {}", tempo,ponto_aqui);

            }
        }


        return pontos;
    }

    public static int PONTUAR_ELEVACAO(Lista<Entidade> strava_objetos, int ano, int mes) {

        int pontos = 0;

        for (Entidade strava : ENTT.COLETAR(strava_objetos, "Tipo", "CORRIDA")) {
            Data data = Data.toData(strava.at("Data"));
            if (data.getAno() == ano && data.getMes() == mes) {

                String distancia = strava.at("Elevacao").trim();

                int ponto_aqui = 0;

                if (distancia.contains("m")) {
                    distancia = distancia.replace(" m", "");
                    if (distancia.length() > 0) {
                        int valor = Integer.parseInt(distancia);
                        ponto_aqui += (int) valor;
                    }
                }

                pontos += (ponto_aqui);
                //  fmt.print("++ {} ->> {}", tempo,ponto_aqui);

            }
        }


        return pontos;
    }


}
