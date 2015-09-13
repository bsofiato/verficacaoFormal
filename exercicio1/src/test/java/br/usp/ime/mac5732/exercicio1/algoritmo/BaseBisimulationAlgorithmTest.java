package br.usp.ime.mac5732.exercicio1.algoritmo;

import br.usp.ime.mac5732.exercicio1.lts.LTS;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParser;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParsingException;
import br.usp.ime.mac5732.exercicio1.lts.parser.json.JSONLTSParser;
import java.io.InputStream;
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
  public void testSameLTS() throws Exception {
    try (InputStream lts1 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts1.json")) {
      try (InputStream lts2 = BaseBisimulationAlgorithmTest.class.getResourceAsStream("lts1.json")) {
        assertTrue(doCheck(lts1, lts2));
      }
    }
  }
}
