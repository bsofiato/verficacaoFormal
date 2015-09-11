package br.usp.ime.mac5732.exercicio1.lts.parser;

public class LTSParsingException extends Exception {

  public LTSParsingException(Throwable cause) {
    super(cause);
  }

  public LTSParsingException(String message) {
    super(message);
  }

  public LTSParsingException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
