package com.briup.common;

import java.util.Properties;

import com.briup.util.Logger;

/**
 * 日志模块
 */
public class LoggerImpl implements Logger {
	String filePath;
	@Override
	public void init(Properties prop) {
		filePath=prop.getProperty("log-properties");
	}

	@Override
	public void debug(String str) {
		org.apache.log4j.Logger.getLogger("myLogger").debug(str);
	}
	
	@Override
	public void error(String str) {
		org.apache.log4j.Logger.getLogger("myLogger").error(str);
	}

	@Override
	public void fatal(String str) {
		org.apache.log4j.Logger.getLogger("myLogger").fatal(str);
	}

	@Override
	public void info(String str) {
		org.apache.log4j.Logger.getLogger("myLogger").info(str);
	}

	@Override
	public void warn(String str) {
		org.apache.log4j.Logger.getLogger("myLogger").warn(str);
	}

}
