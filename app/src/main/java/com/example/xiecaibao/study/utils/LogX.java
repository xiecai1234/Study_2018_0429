package com.example.xiecaibao.study.utils;

import android.util.Log;

public class LogX {
	private static String TAG = "xcb-";
	private static final int LOG_LEVEL = Log.INFO;
	/**
	 * 各类级别日志输出函数
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg) {
		writeLog(Log.INFO, tag, msg, null, LOG_LEVEL);
	}

	public static void d(String tag, String msg, Throwable e) {
		writeLog(Log.INFO, tag, msg, e, LOG_LEVEL);
	}

	public static void i(String tag, String msg) {
		writeLog(Log.INFO, tag, msg, null, LOG_LEVEL);
	}

	public static void i(String tag, String msg, Throwable e) {
		writeLog(Log.INFO, tag, msg, e, LOG_LEVEL);
	}

	public static void w(String tag, String msg) {
		writeLog(Log.WARN, tag, msg, null, LOG_LEVEL);
	}

	public static void w(String tag, String msg, Throwable e) {
		writeLog(Log.WARN, tag, msg, e, LOG_LEVEL);
	}

	public static void e(String tag, String msg) {
		writeLog(Log.ERROR, tag, msg, null, LOG_LEVEL);
	}

	public static void e(String tag, String msg, Throwable e) {
		writeLog(Log.ERROR, tag, msg, e, LOG_LEVEL);
	}

	public static void v(String tag, String msg) {
		writeLog(Log.VERBOSE, tag, msg, null, LOG_LEVEL);
	}

	public static void v(String tag, String msg, Throwable e) {
		writeLog(Log.VERBOSE, tag, msg, e, LOG_LEVEL);
	}

	/**
	 * 获取异常信息堆栈字符串
	 * 
	 * @param ex
	 * @return
	 */
	public static String getStackTraceString(Throwable ex) {
		return Log.getStackTraceString(ex);
	}

	/**
	 * 输出日志，且在日志中体现线程ID，日志内容末尾添加调用日志的源文件名、行号信息 例如：<br>
	 * 
	 * <pre>
	 * 06-27 09:08:15.708 I/Phone+  (14077): [main-1]PHONE_STATE(Phone.java:212)
	 * </pre>
	 * 
	 * @param priority
	 *            日志级别
	 * @param tag
	 *            标签
	 * @param msg
	 *            日志文本内容
	 * @param th
	 *            异常信息
	 * @param bt
	 *            堆栈深度
	 */
	private static synchronized void writeLog(int priority, String tag,
			String msg, Throwable th, int bt) {

		if (!isLoggable(priority)) {
			return;
		}

		try {
			// 拼接线程ID
			msg = "[" + Thread.currentThread().getName() + "-"
					+ Thread.currentThread().getId() + "]" + msg;

			// 拼接源文件、行号
			StackTraceElement[] st = new Throwable().getStackTrace();
			if (st.length > bt) {
				msg += "(" + "/" + st[bt].getFileName() + ":"
						+ st[bt].getLineNumber() + ")";
			} else {
				msg += "(" + "/unknown source)";
			}

			// 如有异常，拼接异常信息
			if (null != th) {
				msg += '\n' + getStackTraceString(th);
			}
			Log.println(priority, TAG + tag, msg);
		} catch (Exception we) {
			Log.e(TAG,
					"call writeLog cause:" + we.toString(), we);
		}
	}

	/**
	 * @param priority
	 * @return
	 */
	private static boolean isLoggable(int priority) {
		return true/*Log.isLoggable(TAG, priority)*/;
	}

}
