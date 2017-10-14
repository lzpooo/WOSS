package com.briup.main;

import java.util.Collection;

import com.briup.client.GatherImpl;
import com.briup.common.Configuration;
import com.briup.util.BIDR;

/**
 * 客户端
 */
public class ClientMain {
	public static void main(String[] args) {
		System.out.println("这是客户端。。。");
		try {
			//采集
			Collection<BIDR> bidr = new Configuration().getGather().gather();
			//备份
			new Configuration().getBackup().store("2", new GatherImpl().getMap(), true);
			//向服务器发送
			new Configuration().getClient().send(bidr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
