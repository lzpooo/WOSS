package com.briup.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.woss.server.Server;

/**
 * 中央服务器
 */
public class ServerImpl implements Server {
	private Collection<BIDR> bidr = null;
	private ServerSocket ss = null;
	private int port;

	@Override
	public void init(Properties prop) {
		port = Integer.parseInt(prop.getProperty("port"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<BIDR> revicer() throws Exception {
		ObjectInputStream ois=null;
		//boolean flag = true;
		Socket client = null;
		try {
			ss = new ServerSocket(port);
			System.out.println("等待连接...");
			client = ss.accept();
			ois=new ObjectInputStream(client.getInputStream());
			bidr = (Collection<BIDR>) ois.readObject();
			//new Configuration().getDBStore().saveToDB(bidr);
			//new ServerThread(client).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bidr;
	}

	@Override
	public void shutdown() {
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class ServerThread extends Thread {
	Socket s = null;
	Collection<BIDR> bidr = null;

	public ServerThread(Socket s) {
		this.s = s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			bidr = (Collection<BIDR>) ois.readObject();
			DBStoreImpl dbStoreImpl = new DBStoreImpl();
			dbStoreImpl.saveToDB(bidr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
