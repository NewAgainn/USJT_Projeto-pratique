package Eventos_da_Cidade_Console_Basico;

public class Eventos {

    private String nome_evento, endereco, categoria, data_hora, descricao;

    public Eventos(String nome_evento, String endereco, String categoria, String data_hora, String descricao) {
        this.nome_evento = nome_evento;
        this.endereco = endereco;
        this.categoria = categoria;
        this.data_hora = data_hora;
        this.descricao = descricao;
    }

    public String getNome_evento() {
        return nome_evento;
    }

    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
