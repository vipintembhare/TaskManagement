package com.mindtree.yorbit.taskmanagement.converters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.mindtree.yorbit.taskmanagement.model.Task;

public class TaskConverter extends AbstractHttpMessageConverter<Task>{

	@Override
	protected boolean supports(Class<?> clazz) {
	return Task.class.isAssignableFrom(clazz);
	}

	@Override
	protected Task readInternal(Class<? extends Task> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		String requestBody = toString(inputMessage.getBody());
		int i = requestBody.indexOf("\n");
		if (i == -1) {
		    throw new HttpMessageNotReadableException("No first line found");
		}

		String reportName = requestBody.substring(0, i).trim();
		String content = requestBody.substring(i).trim();

		Task task = new Task();
	return task;
	}

	@Override
	protected void writeInternal(Task t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}
	private static String toString(InputStream inputStream) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		return scanner.useDelimiter("\\A").next();
		}

}
/*public class ReportConverter
extends AbstractHttpMessageConverter<Report> {

public ReportConverter() {
super(new MediaType("text", "report"));
}

@Override
protected boolean supports(Class<?> clazz) {
return Report.class.isAssignableFrom(clazz);
}

@Override
protected Report readInternal(Class<? extends Report> clazz, HttpInputMessage inputMessage)
    throws IOException, HttpMessageNotReadableException {
String requestBody = toString(inputMessage.getBody());
int i = requestBody.indexOf("\n");
if (i == -1) {
    throw new HttpMessageNotReadableException("No first line found");
}

String reportName = requestBody.substring(0, i).trim();
String content = requestBody.substring(i).trim();

Report report = new Report();
report.setReportName(reportName);
report.setContent(content);
return report;
}

@Override
protected void writeInternal(Report report, HttpOutputMessage outputMessage)
    throws IOException, HttpMessageNotWritableException {
try {
    OutputStream outputStream = outputMessage.getBody();
    String body = report.getReportName() + "\n" +
            report.getContent();
    outputStream.write(body.getBytes());
    outputStream.close();
} catch (Exception e) {
}
}

private static String toString(InputStream inputStream) {
Scanner scanner = new Scanner(inputStream, "UTF-8");
return scanner.useDelimiter("\\A").next();
}
}*/