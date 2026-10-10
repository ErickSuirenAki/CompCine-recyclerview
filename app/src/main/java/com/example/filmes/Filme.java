package com.example.filmes;

public class Filme {

    private String titulo;
    private String genero;
    private String ano;
    private String descricao;
    private String imagem;

    public Filme(String titulo, String genero,
                 String ano, String descricao,
                 String imagem) {

        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getAno() {
        return ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagem() {
        return imagem;
    }
}
