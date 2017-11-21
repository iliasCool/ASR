package com.example;

public class EnvTest {
	static String MONGO_URI = System.getenv("SESSION");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(MONGO_URI);
	}

}
