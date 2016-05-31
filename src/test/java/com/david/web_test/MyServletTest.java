package com.david.web_test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;

public class MyServletTest extends Mockito {
	
	@Test
	public void testServlet() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("username")).thenReturn("david");
		when(request.getParameter("password")).thenReturn("1234");
		PrintWriter writer = new PrintWriter("somefile.txt");
		when(response.getWriter()).thenReturn(writer);
		
		new TestServlet().doPost(request, response);
		
		
		verify(request,atLeast(1)).getParameter("username");
		writer.flush();
		assertTrue(FileUtils.readFileToString(new File("somefile.txt")).contains("Hello"));
	}

}
