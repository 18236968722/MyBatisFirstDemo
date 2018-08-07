package cn.com.mybatis.test;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOTestDome {
	
	//图片读取
	//图片生成
	//文本读取
	public static List txtReader(String path) throws Exception{
		BufferedReader readLine = new BufferedReader(new InputStreamReader(new FileInputStream(path),"GBK"));
		List txtlist= new List();
		while (readLine.readLine() !=null){
			txtlist.add(readLine.readLine());
		}
		readLine.close();
		return txtlist;
		

		
	}
	//文本写入
	//ftp上传
	//ftp下载
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String path="C:\\Users\\dell\\Desktop\\test\\1.txt";
		List printTxt=txtReader(path);
		int i=printTxt.getItemCount();
		while( i >0 ){
			System.out.println(printTxt.getItem(i));
			i--;
		}


	}

}
