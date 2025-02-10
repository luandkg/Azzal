package apps.app;

import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.bibliotecas.JSON;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tronarko.Tronarko;

public class AppPrevisaoDoTempo {

    public static Lista<Entidade> init() {

        Lista<Entidade> ents = new Lista<Entidade>();

        Lista<String> current = new Lista<>();
        current.adicionar(
                "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m");

        String s_currente = "";
        for (String dado : current) {
            s_currente += dado + ",";
        }

        Lista<String> dados = new Lista<>();
        dados.adicionar(
                "temperature_2m,relative_humidity_2m,windspeed_10m,dew_point_2m,apparent_temperature,precipitation_probability,precipitation,rain,showers,snowfall,snow_depth,weather_code,pressure_msl,surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,visibility,evapotranspiration,et0_fao_evapotranspiration,vapour_pressure_deficit");

        String s_dados = "";
        for (String dado : dados) {
            s_dados += dado + ",";
        }

        Lista<String> daily = new Lista<>();
        daily.adicionar(
                "weather_code,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,wind_direction_10m_dominant,shortwave_radiation_sum,et0_fao_evapotranspiration");
        String s_daily = "";
        for (String dado : daily) {
            s_daily += dado + ",";
        }

        Opcional<String> previsao_do_tempo = Internet.GET_PAGINA_HTML_TIMEOUT(
                "https://api.open-meteo.com/v1/forecast?latitude=-15.793889&longitude=-47.882778&current_weather=true&current="
                        + s_currente + "&hourly=" + s_dados + "&daily=" + s_daily);

        if (previsao_do_tempo.isOK()) {

            for (String frase : Strings.dividir_blocos(previsao_do_tempo.get(), 100)) {
                // fmt.println(frase);
            }

            DKG documento = JSON.JSON_TO_DKG(previsao_do_tempo.get());

            // fmt.println(documento.toDocumento());

            DKGObjeto raiz = documento.unicoObjeto("JSON");
            DKGObjeto temp = documento.unicoObjeto("JSON").unicoObjeto("current_weather");
            DKGObjeto unidades = documento.unicoObjeto("JSON").unicoObjeto("current_weather_units");

            String geral_data_hora = Calendario.DATA_HORA_TO_BRASIL(temp.identifique("time").getValor());

            String geral_data = Calendario.GMT_DATA(geral_data_hora);
            String geral_horario = Calendario.GMT_HORA_MINUTO(geral_data_hora);

            System.out.println("Data              : " + geral_data);
            System.out.println("Horário           : " + geral_horario);
            System.out.println("Temperatura       : " + temp.identifique("temperature").getValor()
                    + unidades.identifique("temperature").getValor());
            System.out.println("Elevação          : " + raiz.identifique("elevation").getValor() + "m");
            System.out.println("Vento Direção     : " + temp.identifique("winddirection").getValor()
                    + unidades.identifique("winddirection").getValor());
            System.out.println("Vento Velocidade  : " + temp.identifique("windspeed").getValor()
                    + unidades.identifique("windspeed").getValor());

            for (DKGObjeto ob : documento.unicoObjeto("JSON").unicoObjeto("hourly").getObjetos()) {
                // System.out.println("@@ " + ob.getNome());
            }

            DKGObjeto por_hora = documento.unicoObjeto("JSON").unicoObjeto("hourly");

            DKGObjeto por_hora_unidades = documento.unicoObjeto("JSON").unicoObjeto("hourly_units");
            DKGObjeto por_hora_tempo = por_hora.unicoObjeto("time");
            DKGObjeto por_hora_temp = por_hora.unicoObjeto("temperature_2m");
            DKGObjeto por_hora_humidade = por_hora.unicoObjeto("relative_humidity_2m");
            DKGObjeto por_hora_vento = por_hora.unicoObjeto("windspeed_10m");
            DKGObjeto por_hora_chuva = por_hora.unicoObjeto("rain");

            String por_hora_unidades_temp = por_hora_unidades.identifique("temperature_2m").getValor();
            String por_hora_unidades_humidade = por_hora_unidades.identifique("relativehumidity_2m").getValor();
            String por_hora_unidades_vento = por_hora_unidades.identifique("windspeed_10m").getValor();
            String por_hora_unidades_chuva = por_hora_unidades.identifique("rain").getValor();

            int index = 0;

            for (DKGObjeto data_hora : por_hora_tempo.getObjetos()) {

                String item_temp = por_hora_temp.getObjetos().get(index).getNome() + por_hora_unidades_temp;
                String item_humidade = por_hora_humidade.getObjetos().get(index).getNome() + por_hora_unidades_humidade;
                String item_vento = por_hora_vento.getObjetos().get(index).getNome() + por_hora_unidades_vento;
                String item_chuva = por_hora_chuva.getObjetos().get(index).getNome() + por_hora_unidades_chuva;

                String data_horario = Calendario.DATA_HORA_TO_BRASIL(data_hora.getNome());

                String data = Data.organizarData(Calendario.GMT_DATA(data_horario).replace("-", "_"));
                String horario = Calendario.GMT_HORA_MINUTO(data_horario);

                // System.out.println("\t -->> " + data + " " + horario + " " + item_temp + " "
                // + item_humidade + " " + fmt.espacar_depois(item_vento, 8) + " " +
                // item_chuva);
                index += 1;
            }

            // HexDumper.dump(documento.toDocumento());

            DKGObjeto raiz2 = documento.unicoObjeto("JSON").unicoObjeto("hourly");

            int max = 0;
            for (DKGObjeto obj : raiz2.getObjetos()) {
                // fmt.print("\t ++ {} :: {}", obj.getNome(), obj.getObjetos().size());
                max = obj.getObjetos().getQuantidade();
            }


            for (int item = 0; item < max; item++) {
                Entidade e = new Entidade();
                ents.adicionar(e);
                for (DKGObjeto obj : raiz2.getObjetos()) {

                    String valor = "";
                    if (obj.getNome().contentEquals("time")) {
                        valor = Calendario.DATA_HORA_TO_BRASIL(obj.getObjetos().get(item).getNome());
                        valor = valor.replace("T", " ");

                    } else {
                        valor = obj.getObjetos().get(item).getNome()
                                + por_hora_unidades.identifique(obj.getNome()).getValor();
                    }

                    e.at(obj.getNome(), valor);
                }
            }

        }


        return ents;
    }

    public static void EXIBIR_TUDO(Lista<Entidade> entidades) {
        ENTT.EXIBIR_TABELA(entidades);
    }

    public static Lista<Entidade> toEntidades() {

        Lista<Entidade> ents = new Lista<Entidade>();

        Lista<String> current = new Lista<>();
        current.adicionar(
                "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m");

        String s_currente = "";
        for (String dado : current) {
            s_currente += dado + ",";
        }

        Lista<String> dados = new Lista<>();
        dados.adicionar(
                "temperature_2m,relative_humidity_2m,windspeed_10m,dew_point_2m,apparent_temperature,precipitation_probability,precipitation,rain,showers,snowfall,snow_depth,weather_code,pressure_msl,surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,visibility,evapotranspiration,et0_fao_evapotranspiration,vapour_pressure_deficit");

        String s_dados = "";
        for (String dado : dados) {
            s_dados += dado + ",";
        }

        Lista<String> daily = new Lista<>();
        daily.adicionar(
                "weather_code,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,wind_direction_10m_dominant,shortwave_radiation_sum,et0_fao_evapotranspiration");
        String s_daily = "";
        for (String dado : daily) {
            s_daily += dado + ",";
        }

        Opcional<String> previsao_do_tempo = Internet.GET_PAGINA_HTML_TIMEOUT(
                "https://api.open-meteo.com/v1/forecast?latitude=-15.793889&longitude=-47.882778&current_weather=true&current="
                        + s_currente + "&hourly=" + s_dados + "&daily=" + s_daily);

        if (previsao_do_tempo.isOK()) {

            DKG documento = JSON.JSON_TO_DKG(previsao_do_tempo.get());

            // fmt.println(documento.toDocumento());

            DKGObjeto raiz = documento.unicoObjeto("JSON");
            DKGObjeto temp = documento.unicoObjeto("JSON").unicoObjeto("current_weather");
            DKGObjeto unidades = documento.unicoObjeto("JSON").unicoObjeto("current_weather_units");

            String geral_data_hora = Calendario.DATA_HORA_TO_BRASIL(temp.identifique("time").getValor());

            String geral_data = Calendario.GMT_DATA(geral_data_hora);
            String geral_horario = Calendario.GMT_HORA_MINUTO(geral_data_hora);

            // System.out.println("Data : " + geral_data);
            // System.out.println("Horário : " + geral_horario);
            // System.out.println("Temperatura : " +
            // temp.identifique("temperature").getValor() +
            // unidades.identifique("temperature").getValor());
            // System.out.println("Elevação : " + raiz.identifique("elevation").getValor() +
            // "m");
            // System.out.println("Vento Direção : " +
            // temp.identifique("winddirection").getValor() +
            // unidades.identifique("winddirection").getValor());
            // System.out.println("Vento Velocidade : " +
            // temp.identifique("windspeed").getValor() +
            // unidades.identifique("windspeed").getValor());

            DKGObjeto por_hora = documento.unicoObjeto("JSON").unicoObjeto("hourly");

            DKGObjeto por_hora_unidades = documento.unicoObjeto("JSON").unicoObjeto("hourly_units");
            DKGObjeto por_hora_tempo = por_hora.unicoObjeto("time");
            DKGObjeto por_hora_temp = por_hora.unicoObjeto("temperature_2m");
            DKGObjeto por_hora_humidade = por_hora.unicoObjeto("relative_humidity_2m");
            DKGObjeto por_hora_vento = por_hora.unicoObjeto("windspeed_10m");
            DKGObjeto por_hora_chuva = por_hora.unicoObjeto("rain");

            String por_hora_unidades_temp = por_hora_unidades.identifique("temperature_2m").getValor();
            String por_hora_unidades_humidade = por_hora_unidades.identifique("relativehumidity_2m").getValor();
            String por_hora_unidades_vento = por_hora_unidades.identifique("windspeed_10m").getValor();
            String por_hora_unidades_chuva = por_hora_unidades.identifique("rain").getValor();

            int index = 0;

            for (DKGObjeto data_hora : por_hora_tempo.getObjetos()) {

                String item_temp = por_hora_temp.getObjetos().get(index).getNome() + por_hora_unidades_temp;
                String item_humidade = por_hora_humidade.getObjetos().get(index).getNome() + por_hora_unidades_humidade;
                String item_vento = por_hora_vento.getObjetos().get(index).getNome() + por_hora_unidades_vento;
                String item_chuva = por_hora_chuva.getObjetos().get(index).getNome() + por_hora_unidades_chuva;

                String data_horario = Calendario.DATA_HORA_TO_BRASIL(data_hora.getNome());

                String data = Data.organizarData(Calendario.GMT_DATA(data_horario).replace("-", "_"));
                String horario = Calendario.GMT_HORA_MINUTO(data_horario);

                // System.out.println("\t -->> " + data + " " + horario + " " + item_temp + " "
                // + item_humidade + " " + fmt.espacar_depois(item_vento, 8) + " " +
                // item_chuva);
                index += 1;
            }

            // HexDumper.dump(documento.toDocumento());

            DKGObjeto raiz2 = documento.unicoObjeto("JSON").unicoObjeto("hourly");

            int max = 0;
            for (DKGObjeto obj : raiz2.getObjetos()) {
                // fmt.print("\t ++ {} :: {}", obj.getNome(), obj.getObjetos().size());
                max = obj.getObjetos().getQuantidade();
            }


            for (int item = 0; item < max; item++) {
                Entidade e = new Entidade();
                ents.adicionar(e);
                for (DKGObjeto obj : raiz2.getObjetos()) {

                    String valor = "";
                    if (obj.getNome().contentEquals("time")) {
                        valor = Calendario.DATA_HORA_TO_BRASIL(obj.getObjetos().get(item).getNome());
                        valor = valor.replace("T", " ");

                    } else {
                        valor = obj.getObjetos().get(item).getNome()
                                + por_hora_unidades.identifique(obj.getNome()).getValor();
                    }

                    e.at(obj.getNome(), valor);
                }
            }

        }


        return ents;
    }

    public static Lista<Entidade> toEntidadesTronarko() {
        Lista<Entidade> ents = toEntidades();

        for (Entidade e : ents) {
            String tempo = e.at("time");

            String data = Calendario.GET_DATA_DE_AMDHM(tempo);
            String hora_min = Calendario.GET_HORAMIN_DE_AMDHM(tempo);

            String tozte = Tronarko.getData(data).getTextoZerado();
            String hazde = Tronarko.getHora(hora_min + ":00").getTextoSemUzzonZerado();

            e.at("time", tozte + " " + hazde);

            String temperatura = e.at("temperature_2m");
            if (temperatura.endsWith("°C")) {
                temperatura = temperatura.replace("°C", "");
                double d_temperatura = Double.parseDouble(temperatura);
                d_temperatura = (d_temperatura / 3.0);

                temperatura = fmt.doubleNumC2(d_temperatura) + " Auz";
                e.at("temperature_2m", temperatura);
            }
        }

        return ents;
    }
}
