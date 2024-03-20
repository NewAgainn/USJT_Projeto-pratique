package utilidades;

import java.time.LocalDateTime; // Importa a classe LocalDateTime
import java.time.format.DateTimeFormatter; // Importa a classe DateTimeFormatter

public class DataHoraUtil {

    public static String hora() {
        LocalDateTime agora = LocalDateTime.now();  // Obtém a data e hora atual do sistema

        // Cria um formatador com o padrão de hora desejado
        DateTimeFormatter hora_formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Aplica o formatador à hora atual para converter para o formato especificado
        String hora = agora.format(hora_formatador);

        return hora;

    }

    public static String data() {
        LocalDateTime agora = LocalDateTime.now();  // Obtém a data e hora atual do sistema

        // Cria um formatador com o padrão de data desejado
        DateTimeFormatter data_formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Aplica o formatador à data atual para converter para o formato especificado
        String data = agora.format(data_formatador);

        return data;

    }

    public static String ano() {
        LocalDateTime agora = LocalDateTime.now();  // Obtém a data e hora atual do sistema
        
        // Cria um formatador com o padrão de data desejado
        DateTimeFormatter data_formatador = DateTimeFormatter.ofPattern("yyyy");

        // Aplica o formatador à data atual para converter para o formato especificado
        String ano = agora.format(data_formatador);

        return ano;
    }

}
