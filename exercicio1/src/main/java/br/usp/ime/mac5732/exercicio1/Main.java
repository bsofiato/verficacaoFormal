package br.usp.ime.mac5732.exercicio1;

import br.usp.ime.mac5732.exercicio1.algoritmo.EquivalenceCheckinAlgorithm;
import br.usp.ime.mac5732.exercicio1.algoritmo.PrintStreamFeedbackListener;
import br.usp.ime.mac5732.exercicio1.algoritmo.onthefly.OnTheFlyBehavioralEquivalenceCheckingAlgorithm;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParser;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParsingException;
import br.usp.ime.mac5732.exercicio1.lts.parser.json.JSONLTSParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
  private static final LTSParser PARSER = new JSONLTSParser();
  
  private static Options createOptions() {
    Options options = new Options();
    options.addOption(Option.builder("algoritmo").hasArg().argName("algoritmo").desc("Algoritmo utilizado para a verificacao").required().build());
    options.addOption(Option.builder("primeiroLTS").hasArg().argName("primeiroLTS").desc("Primeiro LTS a ser verificado").required().build());
    options.addOption(Option.builder("segundoLTS").hasArg().argName("segundoLTS").desc("Segundo LTS a ser verificado").required().build());
    return options;
  }
  private static EquivalenceCheckinAlgorithm getAlgoritm(CommandLine line) throws ParseException {
    String tipoAlgoritmo = line.getOptionValues("algoritmo")[0];
    switch (tipoAlgoritmo) {
      case "1" : return new OnTheFlyBehavioralEquivalenceCheckingAlgorithm();
      case "" : return null;
      default : throw new ParseException("Algortimo desconhecido " + tipoAlgoritmo);
    }
  }
  
  private static LTS parseLTS(String fileName) throws ParseException {
    File file = new File(fileName);
    if (!file.exists()) {
      throw new ParseException("Arquivo " +  file.getAbsolutePath() + " inexistente");
    }
    try {
      try (FileInputStream stream = new FileInputStream(file)) {
        return PARSER.parse(stream);
      }
    } catch (IOException ex) {
      throw new ParseException("Arquivo " +  file.getAbsolutePath() + " inexistente");
    } catch (LTSParsingException ex) {
      ex.printStackTrace();
      throw new ParseException("Erro ao parsear arquivo " +  file.getAbsolutePath());
    }
  }
  
  public static void main (String [] args) {
    Options options = createOptions();
    try {
      CommandLineParser parser = new DefaultParser();
      CommandLine line = parser.parse(options, args);
      
      EquivalenceCheckinAlgorithm algorithm = getAlgoritm(line);
      LTS primeiroLTS = parseLTS(line.getOptionValue("primeiroLTS"));
      LTS segundoLTS = parseLTS(line.getOptionValue("segundoLTS"));
      
      if (algorithm.isEquivalent(primeiroLTS, segundoLTS, new PrintStreamFeedbackListener(System.out))) {
        System.out.println("Os LTS sao bisimilares");
      } else {
        System.out.println("Os LTS nao sao bisimilares");
      }
      
    } catch (ParseException ex) {
      ex.printStackTrace();
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp( "exercicio", options );     
    }
  }
}
