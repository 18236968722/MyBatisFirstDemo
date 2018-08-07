package cn.com.mybatis.test;

import java.lang.reflect.Constructor;

class ClassTest implements Cloneable {
	private String name;
	private int age;

	public ClassTest(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "本人是"+this.name+";今年"+this.age+"岁。";
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


}
public class ClassTestDome {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class<?> TT=Class.forName("cn.com.mybatis.test.ClassTest");
		System.out.println(TT);
		
//		ClassTest classtest=(ClassTest)TT.newInstance();
//		
//		classtest.setAge(26);
//		classtest.setName("zhanggd");
//		System.out.println(classtest);
//		
//		ClassTest classclone=(ClassTest)classtest.clone();
//		classclone.setName("HAHA");
//		System.out.println(classclone);
		
		Constructor<?> CC= TT.getConstructor(String.class,int.class);
		ClassTest classgouzao=(ClassTest) CC.newInstance("构造",20);
		System.out.println(classgouzao);
		
		

	}

}
