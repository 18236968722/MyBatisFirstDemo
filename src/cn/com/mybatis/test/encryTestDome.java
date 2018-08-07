package cn.com.mybatis.test;

import java.util.HashMap;
import java.util.Map;

public class encryTestDome {
	
	public static void ceshi(String aa){
		try{
		int[] arr = {1, 2, 3};
		System.out.println("第2步");
        int cc=arr[4];
        System.out.println("第3步");

		}catch(Exception e){
			
			System.out.println("第3步异常捕获");
			System.out.println(e);
		}
        
	}
	

	public static void main(String[] args) {
		
		
		Map<String,String> aaMap =new HashMap<>();
		aaMap.put("key", "aaa");
		aaMap.get("way");
		System.out.println("第1步");
		
		try{
			int[] arr = {1, 2, 3};
	        
	        
	        ceshi("ceshi");
	        int cc=arr[4];
	        System.out.println("第5步");
			
		}catch(Exception e){
			
			System.out.println("异常捕获");
			System.out.println(e);
			
		}finally {
		
		System.out.println("继续执行");
		}
		
		System.out.println("end");

	}

}
