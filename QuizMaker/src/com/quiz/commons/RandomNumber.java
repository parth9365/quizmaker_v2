package com.quiz.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumber {
	private List<Integer> list;
	
	public RandomNumber(int n){
		list = new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			list.add(i);
		}
	}
	
	public int getNextRandomNumber(){
		return list.remove(new Random().nextInt(list.size()));
	}
	
	public boolean isEmpty(){
		return list.size()==0?true:false;
	}
	
	public static void main(String[] args) {
		RandomNumber rn = new RandomNumber(6);
		while(!rn.isEmpty()){
			System.out.println(rn.getNextRandomNumber());
		}
	}
}
