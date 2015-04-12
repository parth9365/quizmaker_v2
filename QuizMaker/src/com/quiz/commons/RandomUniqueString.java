package com.quiz.commons;

import java.util.HashSet;

import org.apache.commons.lang.RandomStringUtils;

public class RandomUniqueString {
	public static void main(String[] args) {
		
		//for(int j=0;j<100; j++){
			//HashSet<String> set = new HashSet<String>();
			for(int i=0;i<100; i++){
				//set.add(RandomStringUtils.randomAlphanumeric(7));
				System.out.println(RandomStringUtils.randomAlphanumeric(7));
			}
			//System.out.println(set.size());
		//}
	}
}
