package cn.com.mybatis.test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.com.mybatis.datasource.DataConnection;
import cn.com.mybatis.po.BatchCustomer;
import cn.com.mybatis.po.User;

public class MyBatisTest {
	public DataConnection dataConn = new DataConnection();
	@Test
	public void TestSelect() throws Exception{
		SqlSession sqlSession = dataConn.getSqlSession();
		User user1=sqlSession.selectOne("test.findUserById",1);
		System.out.println(user1.getUsername());
		
		TimeUnit.SECONDS.sleep(30);
		sqlSession.commit();
		User user2=sqlSession.selectOne("test.findUserById",1);
		System.out.println(user2.getUsername());
		sqlSession.close();
	}
	
	
	public void TestSelectLike() throws Exception{
		SqlSession sqlSession = dataConn.getSqlSession();
		List<User> userlist=sqlSession.selectList("test.findUserByUsername","丽");
		for (int i=0;i<userlist.size();i++){
			User u=userlist.get(i);
			System.out.println(u.getUsername());
		}
		sqlSession.close();
	}
	
	public void InsertTest() throws Exception{
		SqlSession sqlSession = dataConn.getSqlSession();
		User user=new User();
		SimpleDateFormat DateFormat= new SimpleDateFormat("yyyy-mm-dd");
		user.setBirthday(DateFormat.parse("1996-10-01"));
		user.setUsername("果果");
		user.setPassword("234");
		user.setCity("信阳");;
		user.setGender("男");;
		user.setProvince("河南");;
		user.setEmail("234@126.com");
		sqlSession.insert("test.insertuser", user);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	public void testBatchCustomer() throws Exception{
		SqlSession sqlSession = dataConn.getSqlSession();
		List<BatchCustomer> bcList=sqlSession.selectList("test.findBatchCustomer");
		if (bcList!=null){
			BatchCustomer batchCustomer =null;
			SimpleDateFormat def = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i =0;i<bcList.size();i++){
				batchCustomer=bcList.get(i);
				System.out.println("姓名:"+batchCustomer.getUsername()+" 卡号:"+batchCustomer.getAcno()+" 时间："
				+def.format(batchCustomer.getCreatetime()));
				
			}
		}
		sqlSession.close();
	}
	
	public static void main(String[] args) throws Exception{
		Date date= new Date();
		//MyBatisTest myBatisTest =new MyBatisTest();
		System.out.println(date.getClass());
		Class <?>T= java.util.Date.class;
		System.out.println(T);
		Class <?>C= Class.forName("ClassTest");
		System.out.println(C);
	}
}
