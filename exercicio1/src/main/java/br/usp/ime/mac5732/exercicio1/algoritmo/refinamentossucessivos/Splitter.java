package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

import java.util.Set;

import br.usp.ime.mac5732.exercicio1.lts.Estado;

public class Splitter {

	public static final int EQUIVALENT_CLASS = 0;
	public static final int COMPOUND_SPLITTER_ROOT = 1;
	public static final int COMPOUND_SPLITTER_LEAF = 2;
	public static final int COMPOUND_SPLITTER_FULL = 3;
	
	private Set<Estado> B;
	private Splitter[] leaves;
	private Splitter root;
	private int classification;

	public Splitter() {
		setRoot(null);
		setLeaves(null,null);
		new Splitter(null);
	}
	
	public Splitter(Set<Estado> B) {
		this.B = B;
		classification = EQUIVALENT_CLASS;
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
	
	
	public boolean isInSet(Set<Splitter> splSet) {
		for(Splitter spl : splSet) {
			if(equalSets(B,spl.getB())) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean equalSets(Set<Estado> set1, Set<Estado> set2) {
		boolean b1 = hasAllEstados(set1, set2); //verifies if set2 contains all estados of set1
		boolean b2 = hasAllEstados(set2, set1); //verifies if set1 contains all estados of set2
		
		return (b1 && b2);
	}
	
	/**
	 * Verifies if setContains has all states of setContained
	 * @param setContained
	 * @param setContains
	 * @return true if setContains has all estados of setContained
	 */
	private static boolean hasAllEstados(Set<Estado> setContained, Set<Estado> setContains) {
		for(Estado estado : setContained) {
			if(!setHasEstado(estado,setContains)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean setHasEstado(Estado estadoContained, Set<Estado> setContains) {
		for(Estado estadoContains : setContains) {
			if(estadoContained.getNome().compareTo(estadoContains.getNome())==0) {
				return true;
			}
		}
		return false;
	}
}
