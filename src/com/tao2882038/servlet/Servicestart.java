package com.tao2882038.servlet;

import com.tao2882038.servers.Server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servicestart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public static boolean serviceStartFlag = false;
    public Servicestart() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(serviceStartFlag == false){
			Server server = new Server();
			server.start();
			serviceStartFlag = true;
			request.getSession().setAttribute("startFlag", "starting");
			response.sendRedirect("main.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("startSuccess.jsp");
//			rd.forward(request, response);
		}else {
			request.getSession().setAttribute("startFlag", "started");
			response.sendRedirect("main.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("startSuccess.jsp");
//			rd.forward(request, response);
	}

}
	
	
}