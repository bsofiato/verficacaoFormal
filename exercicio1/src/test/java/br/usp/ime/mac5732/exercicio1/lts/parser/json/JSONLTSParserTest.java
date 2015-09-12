package br.usp.ime.mac5732.exercicio1.lts.parser.json;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParser;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParsingException;
import java.io.InputStream;
import java.util.Arrays;
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
  public void testParseLTSDeterministico() throws Exception {
    try (InputStream stream = getClass().getResourceAsStream("lts-deterministico.json")) {
      LTS lts = parser.parse(stream);
      
      assertEquals(2, lts.getAlfabeto().size());
      assertTrue(lts.getAlfabeto().contains("a"));
      assertTrue(lts.getAlfabeto().contains("b"));
      
      assertEquals(2, lts.getEstados().size());
      
      Estado estadoX = lts.getEstado("X");
      Estado estadoY = lts.getEstado("Y");
      
      assertEquals(2, estadoX.getSaidas().size());
      assertTrue(Arrays.asList(estadoY).containsAll(estadoX.getSaidas().get("a")));
      assertTrue(Arrays.asList(estadoX).containsAll(estadoX.getSaidas().get("b")));
        
      assertEquals(2, estadoX.getEntradas().size());
      assertTrue(Arrays.asList(estadoY).containsAll(estadoX.getEntradas().get("a")));
      assertTrue(Arrays.asList(estadoX).containsAll(estadoX.getEntradas().get("b")));
      
      assertEquals(2, estadoY.getSaidas().size());
      assertTrue(Arrays.asList(estadoX).containsAll(estadoY.getSaidas().get("a")));
      assertTrue(estadoY.getSaidas().get("b").isEmpty());
      
      assertEquals(2, estadoY.getEntradas().size());
      assertTrue(Arrays.asList(estadoX).containsAll(estadoY.getEntradas().get("a")));
      assertTrue(estadoY.getEntradas().get("b").isEmpty());
    }
  }
  
  
  @Test
  public void testParseLTSNaoDeterministico() throws Exception {
    try (InputStream stream = getClass().getResourceAsStream("lts-nao-deterministico.json")) {
      LTS lts = parser.parse(stream);
      
      assertEquals(1, lts.getAlfabeto().size());
      assertTrue(lts.getAlfabeto().contains("a"));
      
      assertEquals(3, lts.getEstados().size());
      
      Estado estadoX = lts.getEstado("X");
      Estado estadoY = lts.getEstado("Y");
      Estado estadoW = lts.getEstado("W");
      
      assertEquals(1, estadoX.getSaidas().size());
      assertTrue(Arrays.asList(estadoY, estadoW).containsAll(estadoX.getSaidas().get("a")));
        
      assertEquals(1, estadoX.getEntradas().size());
      assertTrue(Arrays.asList(estadoY, estadoW).containsAll(estadoX.getEntradas().get("a")));
      
      assertEquals(1, estadoY.getSaidas().size());
      assertTrue(Arrays.asList(estadoX).containsAll(estadoY.getSaidas().get("a")));

      assertEquals(1, estadoY.getEntradas().size());
      assertTrue(Arrays.asList(estadoX).containsAll(estadoY.getEntradas().get("a")));

      assertEquals(1, estadoW.getSaidas().size());
      assertTrue(Arrays.asList(estadoX).containsAll(estadoW.getSaidas().get("a")));

      assertEquals(1, estadoW.getEntradas().size());
      assertTrue(Arrays.asList(estadoX).containsAll(estadoW.getEntradas().get("a")));
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
