package cn.com.mybatis.test;

import java.util.HashMap;
import java.util.Map;

public class encryTestDome {
	
	public static void ceshi(String aa){
		try{
		int[] arr = {1, 2, 3};
		System.out.println("��2��");
        int cc=arr[4];
        System.out.println("��3��");

		}catch(Exception e){
			
			System.out.println("��3���쳣����");
			System.out.println(e);
		}
        
	}
	

	public static void main(String[] args) {
		
		
		Map<String,String> aaMap =new HashMap<>();
		aaMap.put("key", "aaa");
		aaMap.get("way");
		System.out.println("��1��");
		
		try{
			int[] arr = {1, 2, 3};
	        
	        
	        ceshi("ceshi");
	        int cc=arr[4];
	        System.out.println("��5��");
			
		}catch(Exception e){
			
			System.out.println("�쳣����");
			System.out.println(e);
			
		}finally {
		
		System.out.println("����ִ��");
		}
		
		System.out.println("end");

	}

}
