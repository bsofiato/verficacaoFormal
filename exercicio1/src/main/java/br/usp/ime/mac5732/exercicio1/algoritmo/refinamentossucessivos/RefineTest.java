package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

public class RefineTest {

	private static final String LTS_EQUIV = "Os LTS sao equivalentes";
	private static final String LTS_NAO_EQUIV = "Os LTS nao sao equivalentes";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RefinamentosSucessivos rs = new RefinamentosSucessivos("C:\\Fabio\\lts-simplest.json");
		Partition part = rs.getPartition();
		System.out.println("Particionamento Final do LTS1:");
		part.printRo();
		
		RefinamentosSucessivos rs2 = new RefinamentosSucessivos("C:\\Fabio\\lts-simplest-diferent-states.json");
		Partition part2 = rs2.getPartition();
		System.out.println("Particionamento Final do LTS2:");
		part2.printRo();
		
		boolean b = Partition.comparePartitions(part, part2);
		if (b) {
			System.out.println(LTS_EQUIV);
		} else {
			System.out.println(LTS_NAO_EQUIV);
		}
	}

}
