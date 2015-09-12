package br.usp.ime.mac5732.exercicio1.algoritmo;

import java.io.PrintStream;

public class PrintStreamFeedbackListener implements ProgressFeedbackListener {
  private final PrintStream printStream;

  private PrintStream getPrintStream() {
    return printStream;
  }
  
  public PrintStreamFeedbackListener(PrintStream printStream) {
    this.printStream = printStream;
  }
  
  @Override
  public void emit(String message) {
    getPrintStream().println(message);
  }
}
