package ftt.controller.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ftt.dao.CursosDao;
import ftt.dao.ProfessoresDao;
import ftt.model.Cursos;
import ftt.model.Professores;

/**
 * Servlet implementation class ProfessoresApi
 */
@WebServlet("/CursosApi")
public class CursosApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Cursos> userData;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursosApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		userData = new HashMap<>();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json");// Mime type
		
		Gson gson = new Gson();		
		CursosDao dao = new CursosDao();
		
		if(request.getParameter("id_curso") !=null) {
			int cursoId = Integer.valueOf(request.getParameter("id_curso"));
			
			try {
				Cursos curso = dao.findForId(cursoId);
				response.getWriter().append(gson.toJson(curso));			
			} catch (SQLException e) {
				
				e.printStackTrace();
				response.getWriter().append(e.getMessage());
			}
			
			
		} else {
		   ArrayList<Cursos> cursos;
			try {
				cursos = dao.findAll();
				response.getWriter().append(gson.toJson(cursos));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*doGet(request, response);*/
		
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json"); //mime type
		
		Cursos c = new Cursos();
		CursosDao dao = new CursosDao();
		Gson gson = new Gson();
		
		c.setNome(request.getParameter("nome"));
		
		
		try {
			userData.put(c.getId_curso(), c);
			dao.insert(c);
			System.out.println(c);
			
			response.getWriter()
			.append("{\"status\":\"ok\",\"timestemp\":" +new Date() +"}");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}//doPost

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json"); //mime type
		
		Cursos c = new Cursos();
		CursosDao dao = new CursosDao();
		Gson gson = new Gson();
		
		c.setId_curso(request.getParameter("id_curso"));
		c.setNome(request.getParameter("nome"));

		
		try {
			userData.put(c.getId_curso(), c);
			dao.update(c);
			System.out.println(c);
			
			response.getWriter()
			.append("{\"status\":\"ok\",\"timestemp\":" +new Date() +"}");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//doPut		
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cursos c = new Cursos();
		CursosDao dao = new CursosDao();
		
		
		try {
			c.setId_curso(request.getParameter("id_curso"));
			dao.delete(c);
			System.out.println("Delete: " + c.getId_curso());
			response.getWriter().append("Curso "+c.getId_curso()+" Apagado...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	}//doDelete

}
