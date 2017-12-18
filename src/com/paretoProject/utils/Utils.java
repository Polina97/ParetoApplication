package com.paretoProject.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Utils {
	
	public static List<List<Integer>> convertToListOfLists(String listString){
		//[["1","4"],["2","3"],["4","4"],["3","5"]]
		Gson gson = new Gson();
		ListWrapper arrStrings = gson.fromJson(listString, ListWrapper.class);
		System.out.println(arrStrings.paretoSet);
		
		return convertToListInteger(arrStrings.paretoSet);
	}
	
	public static List<List<Integer>> convertToListInteger (List<List<String>> listStr){
		List<List<Integer>> newListInt = new ArrayList<List<Integer>>();
		for(List<String> listS : listStr){
			List<Integer> listInt = new ArrayList<Integer>();
			for(String s : listS){
				listInt.add(Integer.valueOf(s));
			}
			newListInt.add(listInt);
		}
		return newListInt;
	}
	
	public class ListWrapper{
		public List<List<String>> paretoSet;
	}

}
