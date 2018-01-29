package backstage.mybatis;
import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/** 
  * @description
  * @author   stone
  * @date     2017年8月24日
  */
public class MybatisTest {
	
	public static void main(String[] args){
		SqlSessionFactory sessionFactory = null;  
		String resource = "mybatis2.xml";  
		try {
		     //SqlSessionFactoryBuilder读取配置文件
		    sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
		              .getResourceAsReader(resource));
		} catch (IOException e) {  
		    e.printStackTrace();  
		}    
		//通过SqlSessionFactory获取SqlSession
		SqlSession sqlSession = sessionFactory.openSession();
		
		sqlSession.selectList("selectByExample");
	}

}
