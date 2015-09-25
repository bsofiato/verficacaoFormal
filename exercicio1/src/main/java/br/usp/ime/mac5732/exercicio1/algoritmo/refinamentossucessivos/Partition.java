package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

import java.util.HashSet;
import java.util.Set;

import br.usp.ime.mac5732.exercicio1.lts.Estado;

public class Partition {
	
	// Armazena as classes de equivalencia
	private Set<Splitter> ro;
	
	public Partition() {
		ro = new HashSet<Splitter>();
	}
	
	public Partition(Splitter w) {
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

	/**
	 * Checks if partitions are equivalent, by comparing saidas of partitions
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean comparePartitions(Partition p1, Partition p2) {
		Set<Set<String>> saidas1 = copySaidasFromPartition(p1);
		Set<Set<String>> saidas2 = copySaidasFromPartition(p2);
		boolean b1 = equalSetSets(saidas1, saidas2);
		return (b1);
	}
	
	/**
	 * Get the saidas of a partition
	 * @param part
	 * @return
	 */
	private static Set<Set<String>> copySaidasFromPartition(Partition part) {
		Set<Set<String>> setTrans = new HashSet<Set<String>>();
		for(Splitter spl : part.getRo()) {
			setTrans.add(spl.getSaidas());
		}
		return setTrans;
	}

	/**
	 * Check if set of set of strings are equal
	 * @param set1
	 * @param set2
	 * @return
	 */
	public static boolean equalSetSets(Set<Set<String>> set1, Set<Set<String>> set2) {
		boolean b1 = setIsContainedInSetOfSets(set1, set2);
		boolean b2 = setIsContainedInSetOfSets(set2, set1);
		
		return (b1 && b2);
	}
	
	/**
	 * Check if setContained is in set of set of string setContains
	 * @param setContained
	 * @param setContains
	 * @return
	 */
	public static boolean setIsContainedInSetOfSets(Set<Set<String>> setContained, Set<Set<String>> setContains) {
		for(Set<String> setString : setContained) {
			if(!isSetIn(setString,setContains)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if set of string is in set of sets of strings
	 * @param setString
	 * @param setSet
	 * @return
	 */
	public static boolean isSetIn(Set<String> setString, Set<Set<String>> setSet) {
		for(Set<String> setContains : setSet) {
			if(setIsEqual(setString, setContains)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if two set of strings are equal
	 * @param s0
	 * @param s1
	 * @return
	 */
	public static boolean setIsEqual(Set<String> s0, Set<String> s1) {
		boolean b1 = allStringPresent(s0, s1);
		boolean b2 = allStringPresent(s1,s0);
		
		return(b1 && b2);
	}

	/**
	 * Check if all string of set s0 are present in s1
	 * @param s0
	 * @param s1
	 * @return
	 */
	public static boolean allStringPresent(Set<String> s0, Set<String> s1) {
		for(String dc0 : s0) {
			if(!setHasString(dc0,s1)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if a set contains a string
	 * @param inStr
	 * @param set
	 * @return
	 */
	public static boolean setHasString(String inStr, Set<String> set) {
		for(String str : set) {
			if(inStr.compareTo(str)==0) {
				return true;
			}
		}
		return false;
	}

}
