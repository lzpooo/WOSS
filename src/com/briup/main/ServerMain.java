package com.briup.main;

import java.util.Collection;

import com.briup.common.Configuration;
import com.briup.util.BIDR;

/**
 * 服务器端
 */
public class ServerMain {
	public static void main(String[] args) {
		System.out.println("这是服务器：  ");
		try {
			//接收客户端发送过来的信息
			Collection<BIDR> bidr=new Configuration().getServer().revicer();
			//入库
			new Configuration().getDBStore().saveToDB(bidr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
