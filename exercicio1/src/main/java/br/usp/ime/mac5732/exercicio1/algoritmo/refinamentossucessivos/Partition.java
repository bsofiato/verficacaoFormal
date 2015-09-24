package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

import java.util.HashSet;
import java.util.Set;

import br.usp.ime.mac5732.exercicio1.lts.Estado;

public class Partition {
	
	private Set<Splitter> ro;
	
	public Partition() {
		ro = new HashSet<Splitter>();
	}
	
	public Partition(Splitter w) {
//		Set<Splitter> wSet = new HashSet<Splitter>();
		this.ro = new HashSet<Splitter>();
		this.ro.add(w);
	}
	
	public Partition(Set<Splitter> ro) {
		this.ro = ro;
	}
	
	public void removeClass(Splitter eqClass) {
		ro.remove(eqClass);
	}
	
	public void addClass(Splitter eqClass) {
		ro.add(eqClass);
	}
	
	public Set<Splitter> getRo() {
		return this.ro;
	}
	
	public void printRo() {
		for(Splitter splitter : ro) {
			for(Estado estado : splitter.getB()) {
				System.out.print(estado.getNome() + ", ");
			}
			System.out.println();
		}
	}

	public static boolean comparePartitions(Partition p1, Partition p2) {
		boolean b1 = p1.isContainedBy(p2);
		boolean b2 = p2.isContainedBy(p1);
		return (b1 && b2);
	}
	
	/**
	 * Check if all splitters of this partition are splitters of partition p
	 * @param p
	 * @return
	 */
	private boolean isContainedBy(Partition p) {
		for(Splitter b : ro) {
			if(!b.isInSet(p.getRo())) {
				return false;  
			}
		}
		return true;
	}
	
//	private boolean stateIsInSet(Splitter b, Set<Splitter> set) {
//		for(Splitter bSet : set) {
//			if(b.getB())
//		}
//		return false;
//	}
}
