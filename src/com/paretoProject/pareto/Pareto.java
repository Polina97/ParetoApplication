package com.paretoProject.pareto;

import java.util.List;

public class Pareto {
	
	public static List<List<Integer>> getParetoSet(List<List<Integer>> vectorsSet){
		for(int i = 0; i < vectorsSet.size()-1; i++) {
			List<Integer> y1 = vectorsSet.get(i);
			
			for(int j = i+1; j < vectorsSet.size(); j++) {
				List<Integer> y2 = vectorsSet.get(j);
				ComparisonAnswers comparisonAnswer = compareVectors(y1, y2);
				if(comparisonAnswer == ComparisonAnswers.NOT_COMPARE) continue;
				if(comparisonAnswer == ComparisonAnswers.MORE 
						|| compareVectors(y1, y2) == ComparisonAnswers.EQUAL) {
					vectorsSet.remove(y2);
					getParetoSet(vectorsSet);
					break;
				}
				if(comparisonAnswer == ComparisonAnswers.LESS ) {
					vectorsSet.remove(y1);
					getParetoSet(vectorsSet);
					break;
				}
			}
		}
		return vectorsSet;
	}
	
	// compare two vectors
	private static ComparisonAnswers compareVectors(List<Integer> vector1, List<Integer> vector2) {
		Integer moreCouter =0, equalCounter=0, lessCounter=0;
		for(int i = 0; i < vector1.size(); i++) {
			if(vector1.get(i) > vector2.get(i)) {
				moreCouter++;
			}else if(vector1.get(i) < vector2.get(i)) {
				lessCounter++;
			}else {
				equalCounter++;
			}
		}
		
		if(vector1.size() == moreCouter+equalCounter) return ComparisonAnswers.MORE;
		else if(vector1.size() == equalCounter)return ComparisonAnswers.EQUAL;
		else if(vector1.size() == lessCounter+equalCounter) return ComparisonAnswers.LESS;
		else return ComparisonAnswers.NOT_COMPARE;
	}
}
