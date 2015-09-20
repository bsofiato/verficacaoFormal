package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

import java.util.Iterator;
import java.util.Set;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import br.usp.ime.mac5732.exercicio1.lts.LTS;

public class RefinamentosSucessivos {
	

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
	
	private LTS lts;
	
	private void refinementStep (Set<Estado> B, Set<Set<Estado>>W) {
		Set<String> A = lts.getAlfabeto();
//	 	Step 1. Remove the element B from W.
		W.remove(B);
//		For each a in A, perform the following two steps:
	    for (String a : A){
//	 Step 2. Compute the set I={x1|Ex Ep ^ x1 = x && Ta-1[B] != 0).
	        Set<Estado> I;
//	 TODO Copy the elements of B into a temporary set B’. 
	        Set <Estado> B_ = B;
//	 For each state p in Ta[B]
		    for (Estado p : B_){
//		    	if p pertence Ta-1[B]
		    }
//	 move this p into a new class. (The elements of a same class are moved into the same new
//	 class.) Make each new class point to its associated old class. During the scan of B’,
//	 compute the map infob.
	        
	     }
			
		
	}
	
}
