package testes;

import Eventos_da_Cidade_Console_Basico.Eventos;
import Eventos_da_Cidade_Console_Basico.Usuario;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import utilidades.Calculo_idade;
import utilidades.DataHoraUtil;

public class interacao {

    private static Scanner scanner = new Scanner(System.in); // Cria uma instância de Scanner

    // Código para o cadastro de usuario
    public static void cadastro_usuario() {
        System.out.println("Cadastro de Usuários");

        // Nome
        System.out.print("Digite o nome de usuário: ");
        String nome = scanner.nextLine();

        // Data de Nascimento
        System.out.print("Digite a data de nascimento abaixo\nQual o dia: ");
        String dia = scanner.nextLine();
        System.out.print("Qual o mês: ");
        String mes = scanner.nextLine();
        System.out.print("Qual o ano: ");
        String ano = scanner.nextLine();
        String dataNascimentoCalculo = ano + "-" + mes + "-" + dia; //para o calculo de idade precisa ser no formato "yyyy-MM-dd"
        String dataNascimento = dia + "/" + mes + "/" + ano;

        int idade = Calculo_idade.idade(dataNascimentoCalculo);

        // Sexo
        System.out.print("Digite o sexo Masculino ou Feminino: ");
        char sexo = scanner.next().toUpperCase().charAt(0);

        Usuario usuario = new Usuario(nome, idade, dataNascimento, sexo);

        System.out.println("Nome: " + usuario.getNome());
        System.out.println("idade: " + usuario.getIdade() + " Anos");
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        System.out.println("Sexo: " + usuario.getSexo());

    }

// Código para o cadastro de evento
    public static void cadastro_evento() {

        System.out.println("Cadastro de Evento");

        // Data e Hora
        System.out.print("Digite a data e hora do Evento. Ex: 01/02/2024 09:45: ");
        String data_hora = scanner.nextLine();

        // Nome do evento
        System.out.print("Informe o Nome do Evento: ");
        String nome_evento = scanner.nextLine();

        // Endereço
        System.out.print("Informe o endereço, nome da Rua e número: ");
        String endereco = scanner.nextLine();

        // Categoria ...
        System.out.print("Informe a categoria desse evento: ");
        String categoria = scanner.nextLine();

        // Descrição ...
        System.out.println("Digite uma breve descrição do evento: ");
        String descricao = scanner.nextLine();

        Eventos evento = new Eventos(
                nome_evento,
                endereco,
                categoria,
                data_hora,
                descricao);

        System.out.println("\nResumo:\n");

        System.out.println(evento.getData_hora() + " - " + evento.getNome_evento());
        System.out.println("Local: " + evento.getEndereco());
        System.out.println("Tipo de Evento: " + evento.getCategoria());
        System.out.println("Descrição: " + evento.getDescricao());

        System.out.print("\nDigite 1 para Confirmar, 2 para Corrigir e 3 para Cancelar: ");
        String opcao = scanner.nextLine();

        if (opcao.equals("1")) {
            System.out.println("\nEvento Salvo!\n");
            // Guarda os dados no Events.data
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.data", true))) {
                writer.write(evento.getData_hora() + " - " + evento.getNome_evento() + "\n");
                writer.write("Local: " + evento.getEndereco() + "\n");
                writer.write("Tipo de Evento: " + evento.getCategoria() + "\n");
                writer.write("Descrição: " + evento.getDescricao() + "\n");
                writer.write("\n-----------\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            menu();
        } else if (opcao.equals("2")) {
            System.out.print("\n");
            cadastro_evento();
        } else {
            System.out.println("\nCadastro de evento Cancelado!\n");
            System.out.println("\n--------------------\n\n");
            saudacao();
            menu();
        }
    }

    // Código para o menu de cadastro de evento e visualizar todos os eventos
    public static void menu() {
        System.out.println("Menu:");
        System.out.println("1 - Cadastrar Evento    2 - Visualizar todos os Eventos");
        System.out.println("3 - Excluir Evento");
        String menu = scanner.nextLine();
        if (menu.equals("1")) {
            cadastro_evento();
        } else if (menu.equals("2")) {
            // Mostrar lista dos eventos
            Lista_eventos.todos_eventos();
            menu();
        } else if (menu.equals("3")) {
            deletar_evento();
            System.out.println("Evento Excluido com Sucusso!\n");
            menu();
        } else {
            System.out.println("\nOpção Inválida selecione a apenas 1, 2 ou 3\n");
            menu();
        }
    }

    // Saudação do começo do programa
    public static void saudacao() {
        LocalTime agora = LocalTime.now();

        if (agora.isBefore(LocalTime.of(6, 0))) {
            System.out.print("Boa madrugada,");
        } else if (agora.isBefore(LocalTime.of(12, 0))) {
            System.out.print("Bom dia,");
        } else if (agora.isBefore(LocalTime.of(18, 0))) {
            System.out.print("Boa tarde,");
        } else {
            System.out.print("Boa noite,");
        }

        System.out.println(" são " + DataHoraUtil.hora() + " do dia " + DataHoraUtil.data());
        System.out.println("\nEsses são os próximos eventos cadastrados:\n");

        // Mostrar alguns eventos cadastrados
        Lista_eventos.eventos_inicial();
        System.out.println();

    }

    public static void deletar_evento() {
        System.out.print("\nQual é o nome do Evento que deseja excluir\nDigite: ");
        String excluir = scanner.nextLine();

        // Regras de validação para o nome do evento
        if (excluir.length() < 3) {
            System.out.println("Operação Cancelada!");
            System.out.println("O nome do evento deve ter pelo menos 3 caracteres.\n\n");
            menu();
        }

        // Cria objetos File para representar os arquivos de eventos e temporário
        File arquivoAtual = new File("events.data");
        File arquivoTemporario = new File("events_temp.data");

        // Tenta abrir o arquivo de eventos para leitura e o arquivo temporário para escrita
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoAtual)); BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemporario))) {

            String linha_atual;
            boolean isEventoEncontrado = false; // "is" é não é obrigatório é só uma convenção para variáveis booleanas
            boolean isDentroDoEvento = false;
            boolean isUltimaLinhaExcluida = false;

            // Lê o arquivo de eventos linha por linha
            while ((linha_atual = reader.readLine()) != null) {
                // Verifica se a linha atual contém o nome do evento a ser excluído
                if (linha_atual.contains(excluir) && !isDentroDoEvento) {
                    isEventoEncontrado = true; // Marca que o evento foi encontrado
                    isDentroDoEvento = true; // Marca que está dentro do bloco do evento
                    continue; // Pula as linhas do evento a ser excluído
                }

                // Verifica se a linha atual é o separador que marca o fim de um evento
                if (linha_atual.equals("-----------")) {
                    if (isEventoEncontrado) {
                        // Se o evento a ser excluído foi encontrado, reseta as marcações
                        isEventoEncontrado = false;
                        isUltimaLinhaExcluida = true; // Marca que a linha do separador foi excluída
                        continue;
                    }
                    // Marca que saiu do bloco do evento
                    isDentroDoEvento = false;
                }

                // Se não estiver dentro do bloco do evento a ser excluído, escreve a linha no arquivo temporário
                if (!isEventoEncontrado) {
                    // Se a última linha excluída foi um separador e a linha atual está vazia, pula a linha
                    if (isUltimaLinhaExcluida && linha_atual.trim().isEmpty()) {
                        isUltimaLinhaExcluida = false; // Reseta a marcação da última linha excluída
                        continue;
                    }
                    // Escreve a linha atual no arquivo temporário
                    writer.write(linha_atual + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            // Imprime o rastreamento de pilha se ocorrer uma exceção de E/S
            e.printStackTrace();

        }

        // Após a leitura e escrita, tenta excluir o arquivo original e renomear o temporário para o original
        if (!arquivoAtual.delete()) {
            System.out.println("Não foi possível deletar o arquivo original");
            return;
        }

        if (!arquivoTemporario.renameTo(arquivoAtual)) {
            System.out.println("Não foi possível renomear o arquivo temporário");
        }
    }

    public class Lista_eventos {

        public static void eventos_inicial() {
            // Define o caminho para o arquivo de eventos
            Path caminho = Paths.get("events.data");
            // Cria um formatador para as datas com base no padrão usado no arquivo
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            try {
                // Lê todas as linhas do arquivo
                List<String> linhas = Files.readAllLines(caminho);
                // Lista para armazenar as datas dos eventos
                List<LocalDateTime> datas = new ArrayList<>();
                // Mapa para associar cada data ao seu evento correspondente
                Map<LocalDateTime, String> eventosMap = new HashMap<>();

                // Itera sobre cada linha do arquivo
                for (String linha : linhas) {
                    // Verifica se a linha contém a marcação de um evento
                    if (linha.contains(" - ")) {
                        // Divide a linha em data e nome do evento
                        String[] partes = linha.split(" - ");
                        // Converte a string de data para um objeto LocalDateTime
                        LocalDateTime data = LocalDateTime.parse(partes[0], formatter);
                        // Armazena o nome do evento
                        String nomeEvento = partes[1];
                        // Adiciona a data à lista de datas
                        datas.add(data);
                        // Associa a data ao nome do evento no mapa
                        eventosMap.put(data, nomeEvento);
                    }
                }

                // Ordena a lista de datas
                Collections.sort(datas);

                // Determina o número de eventos a serem impressos (no caso o máximo é definido como 3)
                int numeroDeEventos = Math.min(datas.size(), 3);
                // Imprime os eventos ordenados
                for (int i = 0; i < numeroDeEventos; i++) {
                    // Obtém a data do evento
                    LocalDateTime data = datas.get(i);
                    // Imprime a data e o nome do evento formatados
                    System.out.println(formatter.format(data) + " - " + eventosMap.get(data));
                }
            } catch (IOException e) {
                // Imprime o rastreamento de pilha se houver uma exceção de E/S
                e.printStackTrace();
            }
        }

        public static void todos_eventos() {
            // "2 - Visualizar todos os Eventos"...
            System.out.println("\nTodos os Eventos:\n"); //

            try {
                Files.lines(Paths.get("events.data")).forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
