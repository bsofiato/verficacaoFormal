package br.usp.ime.mac5732.exercicio1.algoritmo;

import br.usp.ime.mac5732.exercicio1.lts.LTS;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParser;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParsingException;
import br.usp.ime.mac5732.exercicio1.lts.parser.json.JSONLTSParser;
import java.io.InputStream;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public abstract class BaseBisimulationAlgorithmTest <AlgorithmType extends EquivalenceCheckinAlgorithm> {
  private final LTSParser parser = new JSONLTSParser();

  protected LTSParser getParser() {
    return parser;
  }

  protected abstract AlgorithmType createAlgorithm();
  
  protected boolean doCheck(InputStream lts1Resource, InputStream lts2Resource) throws LTSParsingException {
    LTS lts1 = getParser().parse(lts1Resource);
    LTS lts2 = getParser().parse(lts2Resource);
    
    return createAlgorithm().isEquivalent(lts1, lts2, new PrintStreamFeedbackListener(System.out));
  }
  
  @Test
  public void testSameStatesLTS() throws Exception {
    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-simplest.json")) {
      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-simplest.json")) {
        assertTrue(doCheck(lts1, lts2));
      }
    }
  }
  
  @Test
  public void testEquivalentDiferentStatesLTS() throws Exception {
    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-simplest.json")) {
      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-simplest-diferent-states.json")) {
        assertTrue(doCheck(lts1, lts2));
      }
    }
  }
  
  @Test
  public void testEquivalentMoreThanOneLTS() throws Exception {
    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-deterministico.json")) {
      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-deterministico.json")) {
        assertTrue(doCheck(lts1, lts2));
      }
    }
  }
  
//  @Test
//  public void testNotEquivalent() throws Exception {
//    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-simplest.json")) {
//      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-deterministico.json")) {
//        assertFalse(doCheck(lts1, lts2));
//      }
//    }
//  }
  
  @Test
  public void testEquivalentWithLTSWithCycles() throws Exception {
    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-with-cycles.json")) {
      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-with-cycles.json")) {
        assertTrue(doCheck(lts1, lts2));
      }
    }
  }
  
  @Test
  public void testEquivalentWithNonDeterministicLTS() throws Exception {
    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-nao-deterministico.json")) {
      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-nao-deterministico.json")) {
        assertTrue(doCheck(lts1, lts2));
      }
    }
  }
  
//  @Test
//  public void testNonEquivalent2() throws Exception {
//    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-simplest.json")) {
//      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts-2-simplest.json")) {
//        assertFalse(doCheck(lts1, lts2));
//      }
//    }
//  }

}
