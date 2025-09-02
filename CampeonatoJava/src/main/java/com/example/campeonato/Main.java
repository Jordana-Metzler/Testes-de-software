package com.example.campeonato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.example.campeonato.dao.AtletaDAO;
import com.example.campeonato.model.Atleta;


public class Main {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AtletaDAO dao = new AtletaDAO();

        System.out.println("=== CRUD Atleta ===");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Listar atletas");
            System.out.println("2) Inserir atleta");
            System.out.println("3) Atualizar atleta");
            System.out.println("4) Excluir atleta");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            String op = sc.nextLine().trim();

            try {
                switch (op) {
                    case "1" -> listar(dao);
                    case "2" -> inserir(sc, dao);
                    case "3" -> atualizar(sc, dao);
                    case "4" -> excluir(sc, dao);
                    case "0" -> {
                        System.out.println("Encerrando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void listar(AtletaDAO dao) {
        List<Atleta> atletas = dao.findAll();
        if (atletas.isEmpty()) {
            System.out.println("Nenhum atleta cadastrado.");
        } else {
            atletas.forEach(System.out::println);
        }
    }

    private static void inserir(Scanner sc, AtletaDAO dao) {
        Atleta a = lerAtleta(sc, true);
        dao.insert(a);
        System.out.println("Inserido com sucesso!");
    }

    private static void atualizar(Scanner sc, AtletaDAO dao) {
        System.out.print("ID do atleta a atualizar: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        Atleta existente = dao.findById(id);
        if (existente == null) {
            System.out.println("Atleta não encontrado.");
            return;
        }
        Atleta novo = lerAtleta(sc, false);
        novo.setId(id);
        dao.update(novo);
        System.out.println("Atualizado com sucesso!");
    }

    private static void excluir(Scanner sc, AtletaDAO dao) {
        System.out.print("ID do atleta a excluir: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        dao.delete(id);
        System.out.println("Excluído com sucesso!");
    }

 
       private static Atleta lerAtleta(Scanner sc, boolean askId) {
        Atleta a = new Atleta();
        if (askId) {
            System.out.print("ID_atleta (int): ");
            a.setId(Integer.parseInt(sc.nextLine().trim()));
        }
        System.out.print("Nome: ");
        a.setNome(sc.nextLine().trim());

        System.out.print("Posição: ");
        a.setPosicao(sc.nextLine().trim());

        System.out.print("Federação: ");
        a.setFederacao(sc.nextLine().trim());

        System.out.print("Data de nascimento (yyyy-MM-dd): ");
        String sdata = sc.nextLine().trim();
        if (!sdata.isEmpty()) {
            try {
                a.setDataNasc(LocalDate.parse(sdata, DTF));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Data inválida. Use yyyy-MM-dd.");
            }
        }

        System.out.print("Número de jogo: ");
        String snum = sc.nextLine().trim();
        if (!snum.isEmpty()) {
            a.setNumero(Integer.parseInt(snum));
        } else {
            a.setNumero(null);
        }
        return a;
    }
}