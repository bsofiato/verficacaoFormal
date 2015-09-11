package br.usp.ime.mac5732.exercicio1.lts.parser.json;

import br.usp.ime.mac5732.exercicio1.lts.LTS;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParser;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParsingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.io.InputStream;

public class JSONLTSParser implements LTSParser {
  private static ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(LTS.class, new JSONLTSDeserializer());
    
    mapper.registerModule(module);
    
    return mapper;
  }
  private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

  @Override
  public LTS parse(InputStream stream) throws LTSParsingException {
    try {
      return OBJECT_MAPPER.readValue(stream, LTS.class);
    } catch (IOException ex) {
      throw new LTSParsingException(ex);
    }
  }
}
