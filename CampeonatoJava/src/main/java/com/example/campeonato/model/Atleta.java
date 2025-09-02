package com.example.campeonato.model;

import java.time.LocalDate;

public class Atleta {
    private int id;            
    private String nome;      
    private String posicao;     
    private String federacao;   
    private LocalDate dataNasc; 
    private Integer numero;     

    public Atleta() {}

    public Atleta(int id, String nome, String posicao, String federacao, LocalDate dataNasc, Integer numero) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.federacao = federacao;
        this.dataNasc = dataNasc;
        this.numero = numero;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }

    public String getFederacao() { return federacao; }
    public void setFederacao(String federacao) { this.federacao = federacao; }

    public LocalDate getDataNasc() { return dataNasc; }
    public void setDataNasc(LocalDate dataNasc) { this.dataNasc = dataNasc; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    @Override
    public String toString() {
        return "Atleta { " +
                " id = " + id +
                ", nome = '" + nome + '\'' +
                ", posicao = '" + posicao + '\'' +
                ", federacao = '" + federacao + '\'' +
                ", data de nascimento = " + dataNasc +
                ", número de jogo = " + numero +
                '}';
    }
}