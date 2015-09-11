package br.usp.ime.mac5732.exercicio1.lts.parser.json;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JSONLTSDeserializer extends JsonDeserializer <LTS> {
  private Estado safeGetEstado(LTS lts, String name) throws JsonProcessingException {
    Estado estado = lts.getEstado(name);
    if (estado == null) {
      throw new JsonParseException("Estado de nome " + name + "inexistente", JsonLocation.NA);
    }
    return estado;
  }
  
  private void addTransicao(LTS lts, JsonNode transicao) throws JsonProcessingException {
    Estado entrada = safeGetEstado(lts, transicao.get(0).asText());
    Estado saida = safeGetEstado(lts, transicao.get(2).asText());
    
    String canal = transicao.get(1).asText();
    
    if (!lts.getAlfabeto().contains(canal)) {
      throw new JsonParseException("Canal " + canal + " nao pertece ao alfabeto", JsonLocation.NA);
    }
    
    entrada.getSaidas().put(canal, saida);
    saida.getEntradas().put(canal, entrada);
    
  }
  private Set <String> getAlfabeto(JsonNode node) {
    return StreamSupport.stream(node.get("alfabeto").spliterator(), false).map(t -> t.asText()).collect(Collectors.toSet());
  }
  private Set <Estado> getEstados(JsonNode node) {
    return StreamSupport.stream(node.get("estados").spliterator(), false).map(t -> new Estado(t.asText())).collect(Collectors.toSet());
  }
  
  private LTS processTransicoes(LTS lts, JsonNode node) throws JsonProcessingException {
    for (JsonNode transicao : node.get("transicoes")) {
      addTransicao(lts, transicao);
    }
    return lts;
  }
  
  @Override
  public LTS deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    Set <String> alfabeto = getAlfabeto(node);
    Set <Estado> estado = getEstados(node);
    
    return processTransicoes(new LTS(alfabeto, estado), node);
  }
}
