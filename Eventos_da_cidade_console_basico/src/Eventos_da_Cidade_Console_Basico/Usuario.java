package Eventos_da_Cidade_Console_Basico;

public class Usuario {

    // Atributos privados para encapsulamento
    private String nome; // Nome do usuário
    private int idade; // Idade do usuário
    private String dataNascimento; // Data de nascimento no formato DD-MM-YYYY
    private char sexo; // Sexo do usuário, 'M' para masculino, 'F' para feminino

    // Construtor da classe Usuario
    // O construtor permite a criação de um objeto Usuario com atributos definidos
    public Usuario(String nome, int idade, String dataNascimento, char sexo) {
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    // Métodos getters e setters para cada atributo
    // Os métodos públicos permitem o acesso controlado aos atributos encapsulados
    // Retorna o nome do usuário
    public String getNome() {
        return nome;
    }

    // Define o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna a idade do usuário
    public int getIdade() {
        return idade;
    }

    // Define a idade do usuário
    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Retorna a data de nascimento do usuário
    public String getDataNascimento() {
        return dataNascimento;
    }

    // Define a data de nascimento do usuário
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Retorna o sexo do usuário
    public char getSexo() {
        return sexo;
    }

    // Define o sexo do usuário
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void cadastro_usuario() {

    }

}
