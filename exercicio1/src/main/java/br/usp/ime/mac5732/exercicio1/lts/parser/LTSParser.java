package br.usp.ime.mac5732.exercicio1.lts.parser;

import br.usp.ime.mac5732.exercicio1.lts.LTS;
import java.io.InputStream;

public interface LTSParser {
  LTS parse(InputStream stream) throws LTSParsingException;
}
