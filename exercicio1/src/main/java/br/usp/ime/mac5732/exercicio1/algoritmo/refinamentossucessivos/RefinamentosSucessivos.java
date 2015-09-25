package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import br.usp.ime.mac5732.exercicio1.lts.parser.LTSParser;
import br.usp.ime.mac5732.exercicio1.lts.parser.json.JSONLTSParser;

public class RefinamentosSucessivos {
	
	private Partition ro;
	private LTS labelTransSystem;
	private Set<Splitter> W;
	
	public RefinamentosSucessivos(String jsonFile) {
		//Parse JSON to LTS
		try {
			labelTransSystem = lerJSON(jsonFile);
			labelTransSystem.getEstados();

			Splitter eqClass = new Splitter(labelTransSystem.getEstados());
			ro = new Partition(eqClass);
			W = new HashSet<Splitter>();
			W.add(eqClass);
			computeRefinements();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void computeRefinements() {
		while(W.size()>0) {
			Splitter B = null;
			for(Splitter bTmp : W) {
				B = bTmp;
				break;
			}
			refinementStep(B);
		}
	}
	
//	 For each a in A, perform the following two steps:
//	 Step 2. Compute the set I={x,13xEp A x, = x n T;‘[B] # 0).
//	 Copy the elements of B into a temporary set B’. For each state p in Ta[B] move
//	 this p into a new class. (The elements of a same class are moved into the same new
//	 class.) Make each new class point to its associated old class. During the scan of B’,
//	 compute the map infob.

//	Step 3. Update p and W. After Step 2, each old class X contains the elements
//	X - Ta[B]. For each X1, in I perform the following statements:
//	If X = X1 (this is performed in O(1) time by the comparison between the numbers
//	of the elements of the old and new classes) make X point to X1
//	For the case X != X1, make each element of the new class point to X1 by scanning
//	X1, add X1 to p and update W in the following manner: If X is in W, then add
//	X1 to W. If X is a leaf of a compound splitter, it is replaced by a subtree whose
//	root is the new node X12 and whose leaves are X and X1 : make X12 point to X
//	and X1 and make X and X1 point back to X12. (This is performed in O(1) time
//	since the old class points to its father.) If X is not in W and X is not a leaf then
//	create a new node X12 as previously and add it to W.
	
	private void refinementStep (Splitter splitterB) {
//	 Step 1. Remove the element B from W.
		W.remove(splitterB);
		Set<Estado> intersec;
		Set<Estado> diff;
		Splitter[] arrayB = null;
		if(splitterB.getClassification()==Splitter.EQUIVALATION_CLASS) {
			arrayB = new Splitter[1];
			arrayB[0] = splitterB;
		} else {
			if(splitterB.getLeaves()==null) {
				return;
			}
			arrayB = splitterB.getLeaves();
			if(arrayB.length!=2) {
				return;
			}
		}
		
		for(Splitter B : arrayB) {
			// loop for all actions
			for(String action : labelTransSystem.getAlfabeto()) {
				Set<Estado> taInv = opTaInverse(B, action);
				Partition roTmp = new Partition();
				for(Splitter sp : ro.getRo()){
					roTmp.addClass(sp);
				}
				
				// for all X of ro
				for(Splitter sp : roTmp.getRo()){
					intersec = new HashSet<Estado>(); //states that have the transition
					diff = new HashSet<Estado>(); // states that dont have the transition
					for(Estado estado : sp.getB()) {
						if(taInv.contains(estado)) {
							intersec.add(estado);
						} else {
							diff.add(estado);
						}
					}
					
					//if some further partition was made, include new compound splitter in W
					if(intersec.size()!=0 && diff.size()!=0) {
						Splitter B1 = new Splitter(intersec);
						B1.setRoot(B);
						Splitter B2 = new Splitter(diff);
						B2.setRoot(B);
						B.setLeaves(B1, B2);
						W.add(B);
						ro.removeClass(sp);
						ro.addClass(B1);
						ro.addClass(B2);
					}
				}
				
			}
		}
	}
	
	private Set<Estado> opTaInverse(Splitter B, String a) {
		Set<Estado> chegam = new HashSet<Estado>();
		for(Estado estado : B.getB()) {
			if(estado.getEntradas().containsKey(a)) {
				for(Estado estadoChega : estado.getEntradas().get(a)) {
					chegam.add(estadoChega);
				}
			}
		}
		return chegam;
	}
	
	public LTS lerJSON(String arquivo) throws Exception {
		   LTSParser parser = new JSONLTSParser();
		   File file = new File(arquivo);
		   LTS lts = parser.parse(new FileInputStream(file));
		   return lts;
	}
	
	public Partition getPartition() {
		return ro;
	}
	
}
