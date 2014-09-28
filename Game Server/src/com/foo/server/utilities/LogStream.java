package com.foo.server.utilities;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogStream extends PrintStream {

	private SimpleDateFormat dateFormat = new SimpleDateFormat();;

	public LogStream(OutputStream outputStream) {
		super(outputStream);
	}

	@Override
	public void println(String input) {
		super.println("["
				+ getDate()
				+ "]\t"
				+ new Throwable().getStackTrace()[1].getFileName().replace(
						".java", "") + ": " + input);
	}

	private String getDate() {
		Date date = new Date();
		return dateFormat.format(date);
	}

}