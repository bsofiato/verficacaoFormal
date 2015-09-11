package br.usp.ime.mac5732.exercicio1.lts.parser.json;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParser;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParsingException;
import java.io.InputStream;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSONLTSParserTest {
  private LTSParser parser;
  
  @BeforeMethod
  public void setUp() {
    this.parser = new JSONLTSParser();
  }
  
  @Test
  public void testParseLTS1() throws Exception {
    try (InputStream stream = getClass().getResourceAsStream("lts1.json")) {
      LTS lts = parser.parse(stream);
      
      assertEquals(2, lts.getAlfabeto().size());
      assertTrue(lts.getAlfabeto().contains("a"));
      assertTrue(lts.getAlfabeto().contains("b"));
      
      assertEquals(2, lts.getEstados().size());
      
      Estado estadoX = lts.getEstado("X");
      Estado estadoY = lts.getEstado("Y");
      
      assertEquals(2, estadoX.getSaidas().size());
      assertEquals(estadoY, estadoX.getSaidas().get("a"));
      assertEquals(estadoX, estadoX.getSaidas().get("b"));
      
      assertEquals(2, estadoX.getEntradas().size());
      assertEquals(estadoY, estadoX.getEntradas().get("a"));
      assertEquals(estadoX, estadoX.getEntradas().get("b"));
      
      assertEquals(1, estadoY.getSaidas().size());
      assertEquals(estadoX, estadoY.getSaidas().get("a"));
      
      assertEquals(1, estadoY.getEntradas().size());
      assertEquals(estadoX, estadoY.getEntradas().get("a"));


    }
  }

  @Test(expectedExceptions = LTSParsingException.class)
  public void testParseLTSEstadoDesconhecido() throws Exception {
    try (InputStream stream = getClass().getResourceAsStream("lts-estado-desconhecido.json")) {
      parser.parse(stream);
    }
  }
  
  @Test(expectedExceptions = LTSParsingException.class)
  public void testParseLTSTransicaoDesconhecida() throws Exception {
    try (InputStream stream = getClass().getResourceAsStream("lts-transicao-desconhecida.json")) {
      parser.parse(stream);
    }
  }
}
