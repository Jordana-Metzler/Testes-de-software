package com.example.campeonato.dao;

import com.example.campeonato.db.Database;
import com.example.campeonato.model.Atleta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtletaDAO {

    public void insert(Atleta a) {
        String sql = "INSERT INTO atleta (ID_atleta, Nome, Posicao, Federacao, Data_Nasc, Numero) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getId());
            ps.setString(2, a.getNome());
            ps.setString(3, a.getPosicao());
            ps.setString(4, a.getFederacao());
            if (a.getDataNasc() != null) {
                ps.setDate(5, Date.valueOf(a.getDataNasc()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            if (a.getNumero() != null) {
                ps.setInt(6, a.getNumero());
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir atleta: " + e.getMessage(), e);
        }
    }

    public void update(Atleta a) {
        String sql = "UPDATE atleta SET Nome=?, Posicao=?, Federacao=?, Data_Nasc=?, Numero=? WHERE ID_atleta=?";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getNome());
            ps.setString(2, a.getPosicao());
            ps.setString(3, a.getFederacao());
            if (a.getDataNasc() != null) {
                ps.setDate(4, Date.valueOf(a.getDataNasc()));
            } else {
                ps.setNull(4, Types.DATE);
            }
            if (a.getNumero() != null) {
                ps.setInt(5, a.getNumero());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            ps.setInt(6, a.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar atleta: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM atleta WHERE ID_atleta=?";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir atleta: " + e.getMessage(), e);
        }
    }

    public Atleta findById(int id) {
        String sql = "SELECT ID_atleta, Nome, Posicao, Federacao, Data_Nasc, Numero FROM atleta WHERE ID_atleta=?";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar atleta: " + e.getMessage(), e);
        }
    }

    public List<Atleta> findAll() {
        String sql = "SELECT ID_atleta, Nome, Posicao, Federacao, Data_Nasc, Numero FROM atleta ORDER BY ID_atleta";
        List<Atleta> list = new ArrayList<>();
        try (Connection con = Database.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar atletas: " + e.getMessage(), e);
        }
        return list;
    }

    private Atleta map(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID_atleta");
        String nome = rs.getString("Nome");
        String posicao = rs.getString("Posicao");
        String federacao = rs.getString("Federacao");
        Date data = rs.getDate("Data_Nasc");
        Integer numero = rs.getObject("Numero") != null ? rs.getInt("Numero") : null;
        return new Atleta(id, nome, posicao, federacao, data != null ? data.toLocalDate() : null, numero);
    }
}