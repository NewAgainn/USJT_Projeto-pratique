package utilidades;

import java.time.LocalDate;
import java.time.Period;

public class Calculo_idade {

    public static int idade(String dataNascimentoTexto) {

        // Obtém a data atual
        LocalDate dataAtual = LocalDate.now();

         // Converte a String da data de nascimento para um objeto LocalDate
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoTexto);

        // Calcula a diferença entre as datas
        Period periodo = Period.between(dataNascimento, dataAtual);

        // Imprime o resultado
        return periodo.getYears();
    }

}
