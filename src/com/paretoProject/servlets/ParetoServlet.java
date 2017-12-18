package com.paretoProject.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.paretoProject.facade.Facade;
import com.paretoProject.utils.Utils;


/**
 * Servlet implementation class ParetoServlet
 */
@WebServlet("/ParetoServlet")
public class ParetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Facade facade  = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParetoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<List<Integer>> pareto = Utils.convertToListOfLists(request.getParameter("pareto"));
		List<List<Integer>> paretoSetResponse = facade.getPareto(pareto);
		String json = new Gson().toJson(paretoSetResponse);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    String data = buffer.toString();
	    System.out.println("data^ " + data);
		List<List<Integer>> pareto = Utils.convertToListOfLists(data);
		List<List<Integer>> paretoSetResponse = facade.getPareto(pareto);
		String json = new Gson().toJson(paretoSetResponse);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

}
