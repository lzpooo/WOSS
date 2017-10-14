package com.briup.server;

import java.util.Collection;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.briup.mybatis.mappers.Mapper;
import com.briup.mybatis.utils.WossSqlSessionFactory;
import com.briup.util.BIDR;
import com.briup.woss.server.DBStore;

/**
 * 执行入库操作
 */
public class DBStoreImpl implements DBStore {
	//private String[] str=null;
	
	@Override
	public void init(Properties prop) {
		/*str[0] ="url"+"="+prop.getProperty("url");
		str[1] ="driver"+"="+prop.getProperty("driver");
		str[2] ="userName"+"="+prop.getProperty("userName");
		str[3] ="password"+"="+prop.getProperty("password");
		File file=new File("src\\mybatis.properties");
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(file);
			for (String s:str) {
				byte[] b=s.getBytes();
				fos.write(b);
			}
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	/*
	 * 入库
	 */
	@Override
	public void saveToDB(Collection<BIDR> bidr) throws Exception {
		SqlSession sqlSession = null;
		sqlSession = WossSqlSessionFactory.openSession();
		Mapper mapper = sqlSession.getMapper(Mapper.class);
		for (BIDR b : bidr) {
			@SuppressWarnings("deprecation")
			int day = b.getLogin_date().getDate();
			String str = "t_detail_" + day;
			mapper.insertBIDR(b, str);
			sqlSession.commit();
		}
		System.out.println("入库完成！");
		if (sqlSession != null) {
			sqlSession.close();
		}

	}

}
