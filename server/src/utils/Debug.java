package utils;

public class Debug {
	public static void print(Object obj){
		System.out.println(obj);
	}
	
	public static void printf(String format, Object... args){
		System.out.printf(format, args);
	}
}
