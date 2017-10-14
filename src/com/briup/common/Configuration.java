package com.briup.common;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.util.BackUP;
import com.briup.util.Logger;
import com.briup.woss.WossModule;
import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;

/**
 * 配置模块
 */
public class Configuration implements com.briup.util.Configuration {
	private static Properties properties1 = null;
	private static Properties properties2 = null;

	/*
	 * 使用dom4j解析配置的xml文件并保存在properties中
	 */
	 static {
		properties1 = new Properties();
		properties2 = new Properties();

		try {
			SAXReader reader = new SAXReader();
			File filePath = new File("src\\com\\briup\\file\\conf.xml");
			Document document = reader.read(filePath);
			// 获取根元素(woss)
			Element rootElement = document.getRootElement();
			// 获取一级节点
			@SuppressWarnings("unchecked")
			List<Element> element1 = rootElement.elements();
			for (Element e1 : element1) {
				// 一级子元素标签名
				String name = e1.getName();
				// 一级子元素的属性
				@SuppressWarnings("unchecked")
				List<Attribute> attributes = e1.attributes();
				for (Attribute a : attributes) {
					String attributeValue = a.getValue();
					properties1.setProperty(name, attributeValue);
				}
				@SuppressWarnings("unchecked")
				List<Element> element2 = e1.elements();
				for (Element e2 : element2) {
					String element2Name = e2.getName();
					String element2Value = e2.getText();
					properties2.setProperty(element2Name, element2Value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WossModule getWossModule(String str) {
		try {
			String className = properties1.getProperty(str);
			WossModule wossModule = (WossModule) Class.forName(className)
					.newInstance();
			wossModule.init(properties2);
			return wossModule;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BackUP getBackup() throws Exception {
		return (BackUP) (getWossModule("backup"));
	}

	@Override
	public Client getClient() throws Exception {
		return (Client) (getWossModule("client"));
	}

	@Override
	public DBStore getDBStore() throws Exception {
		return (DBStore) (getWossModule("dbstore"));
	}

	@Override
	public Gather getGather() throws Exception {
		return (Gather) (getWossModule("gather"));
	}

	@Override
	public Logger getLogger() throws Exception {
		return (Logger) (getWossModule("logger"));
	}

	@Override
	public Server getServer() throws Exception {
		return (Server) (getWossModule("server"));
	}

}
