package com.paretoProject.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.paretoProject.pareto.Pareto;

public class Facade {
	private List<List<Integer>> someMap = new ArrayList<List<Integer>>();
	
	
	public List<List<Integer>> getPareto(List<List<Integer>> YSet){
		return Pareto.getParetoSet(YSet);
	}
	
	
	

	

}
