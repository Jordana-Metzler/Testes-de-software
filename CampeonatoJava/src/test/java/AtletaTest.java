
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.example.campeonato.model.Atleta;

public class AtletaTest {

    @Test
    public void CriarAtleta() {
        Atleta a = new Atleta();
        a.setId(10);
        a.setNome("Jordana");
        a.setPosicao("Ponteira");
        a.setFederacao("Gaúcha");
        a.setDataNasc(LocalDate.of(2003, 5, 01));
        a.setNumero(8);

        Assert.assertEquals(10, a.getId());
        Assert.assertEquals("Jordana", a.getNome());
        Assert.assertEquals("Ponteira", a.getPosicao());
        Assert.assertEquals("Gaúcha", a.getFederacao());
        Assert.assertEquals(LocalDate.of(2003, 5, 01), a.getDataNasc());
        Assert.assertEquals(Integer.valueOf(8), a.getNumero());
        Assert.assertTrue(a.toString().contains("Jordana"));
    }
}