package cn.com.mybatis.datasource;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataConnection {
	private String resource="SqlMapConfig.xml";
	private SqlSessionFactory SqlSessionFactory;
	private SqlSession SqlSession;
	public SqlSession getSqlSession() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession=SqlSessionFactory.openSession();
		return SqlSession;	
	}
}
