package com.example.campeonato.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    private static final String DB_URL  = "jdbc:h2:mem:Campeonato;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    private static Connection singleton;

    public static synchronized Connection getConnection() throws SQLException {
        if (singleton == null || singleton.isClosed()) {
            singleton = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            initSchema(singleton);
        }
        return singleton;
    }

    private static void initSchema(Connection con) {
        try (InputStream in = Database.class.getResourceAsStream("/schema.sql")) {
            if (in == null) {
                throw new RuntimeException("schema.sql não encontrado no classpath.");
            }
            String sql = readAll(in);
            // Split naive on ';' (ignores edge cases; fine for our simple DDL)
            for (String stmt : sql.split(";")) {
                String s = stmt.trim();
                if (!s.isEmpty()) {
                    try (Statement st = con.createStatement()) {
                        st.execute(s);
                    }
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Falha ao inicializar schema H2: " + e.getMessage(), e);
        }
    }

    private static String readAll(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }
        return sb.toString();
    }
}