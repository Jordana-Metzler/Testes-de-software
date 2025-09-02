
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.campeonato.dao.AtletaDAO;
import com.example.campeonato.db.Database;
import com.example.campeonato.model.Atleta;

public class AtletaDAOTest {

    private AtletaDAO dao;

    @Before
    public void setup() throws SQLException {
        dao = new AtletaDAO();
        // limpa a tabela antes de cada teste
        try (Connection c = Database.getConnection(); Statement st = c.createStatement()) {
            st.execute("DELETE FROM atleta");
        }
    }

    @Test
    public void InserirEBuscarPorId() {
        Atleta a = new Atleta(1, "Joao", "Oposto", "CBV", LocalDate.of(1999,1,1), 9);
        dao.insert(a);

        Atleta achado = dao.findById(1);
        Assert.assertNotNull(achado);
        Assert.assertEquals("Joao", achado.getNome());
        Assert.assertEquals("Oposto", achado.getPosicao());
        Assert.assertEquals(Integer.valueOf(9), achado.getNumero());
    }

    @Test
    public void ListarTodos() {
        dao.insert(new Atleta(1, "A", "Lev", "CBV", null, null));
        dao.insert(new Atleta(2, "B", "Lib", "CBV", null, 5));
        List<Atleta> list = dao.findAll();
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void Atualizar() {
        dao.insert(new Atleta(3, "C", "Levantador", "CBV", null, 2));
        Atleta novo = new Atleta(3, "Carlos", "Levantador", "CBV", LocalDate.of(2001,2,2), 7);
        dao.update(novo);

        Atleta achado = dao.findById(3);
        Assert.assertEquals("Carlos", achado.getNome());
        Assert.assertEquals(LocalDate.of(2001,2,2), achado.getDataNasc());
        Assert.assertEquals(Integer.valueOf(7), achado.getNumero());
    }

    @Test
    public void Excluir() {
        dao.insert(new Atleta(4, "D", "Central", "CBV", null, null));
        dao.delete(4);
        Assert.assertNull(dao.findById(4));
    }
}