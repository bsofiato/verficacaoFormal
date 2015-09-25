package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

import java.util.HashSet;
import java.util.Set;

import br.usp.ime.mac5732.exercicio1.lts.Estado;

public class Splitter {

	public static final int EQUIVALATION_CLASS = 0; //simple splitter
	public static final int COMPOUND_SPLITTER_ROOT = 1; //compound splitter
	public static final int COMPOUND_SPLITTER_LEAF = 2; //compound splitter
	public static final int COMPOUND_SPLITTER_FULL = 3; //compound splitter
	
	private Set<Estado> B;
	private Splitter[] leaves;
	private Splitter root;
	private int classification;
	private Set<String> saidas;

	public Splitter() {
		root = null;
		leaves = null;
		new Splitter(null);
	}
	
	public Splitter(Set<Estado> B) {
		saidas = null;
		this.B = B;
		classification = EQUIVALATION_CLASS;
		root = null;
		leaves = null;
	}
	
	
	public Splitter[] getLeaves() {
		return leaves;
	}

	public void setLeaves(Splitter leaf1, Splitter leaf2) {
		this.leaves = new Splitter[2];
		leaves[0] = leaf1;
		leaves[1] = leaf2;
		if(root == null) {
			classification = COMPOUND_SPLITTER_ROOT;
		} else {
			classification = COMPOUND_SPLITTER_FULL;
		}
	}

	public Splitter getRoot() {
		return root;
	}

	public void setRoot(Splitter root) {
		this.root = root;
		if (leaves==null) {
			classification = COMPOUND_SPLITTER_LEAF;
		} else {
			classification = COMPOUND_SPLITTER_FULL;
		}
	}
	
	public Set<Estado> getB() {
		return this.B;
	}
	
	public int getClassification() {
		return this.classification;
	}
	
	public void setClassification(int classif) {
		this.classification = classif;
	}
	
	public void printB() {
		for(Estado estado : B) {
			System.out.print(estado.getNome() + ", ");
		}
		System.out.println();
	}
	
	
	private void updateSaidas() {
		if(saidas==null) {
			saidas = new HashSet<String>();
		}
		for(Estado estado : B) {
			for(String str : estado.getSaidas().keySet()) {
				if(estado.getSaidas().get(str).size()>0) {
					saidas.add(str);
				}
			}
		}

	}
	
	public Set<String> getSaidas() {
		updateSaidas();
		return saidas;
	}
}
