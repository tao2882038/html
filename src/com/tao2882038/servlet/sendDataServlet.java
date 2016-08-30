package com.tao2882038.servlet;

import com.tao2882038.servers.Server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class sendDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendDataServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] sendAddress = request.getParameterValues("Address");
		String data = request.getParameter("data");
		String osid=request.getParameter("osid");
		
		if(osid.equals("send")){
			if(sendAddress != null){
				for (int i = 0; i < sendAddress.length; i++) {
					Server.sendata("Web", sendAddress[i], data);
				}
				request.getSession().setAttribute("sendFlag", "success");
//			request.setAttribute("sendFlag", "success");
			}else{
				request.getSession().setAttribute("sendFlag", "error");
//			request.setAttribute("sendFlag", "error");
			}
		}else if(osid.equals("clear")){
			Server.clear_dataLog();
		}else if(osid.equals("refresh")){
			request.getSession().removeAttribute("sendFlag");
		}
		
		response.sendRedirect("querySocketsServlet");
//		RequestDispatcher rd = request.getRequestDispatcher("displaySockets.jsp");
//		rd.forward(request, response);
	}

}
