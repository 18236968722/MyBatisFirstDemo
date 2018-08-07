package cn.com.mybatis.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class dateFormatDome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dd ="2018.10.21";
		String cc ="2018-10-21";
		DateFormat aa = new SimpleDateFormat("yyyy");
		Date dat = new Date();
		System.out.println(Integer.parseInt(aa.format(dat))+100  );
		System.out.println(Integer.parseInt(dd.toString().substring(0,4)));

	}

}
