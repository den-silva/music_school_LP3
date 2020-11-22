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
import ftt.dao.MatriculasDao;
import ftt.dao.ProfessoresDao;
import ftt.model.Cursos;
import ftt.model.Matriculas;
import ftt.model.MetodosGerais;
import ftt.model.Professores;

/**
 * Servlet implementation class ProfessoresApi
 */
@WebServlet("/MatriculasApi")
public class MatriculasApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Matriculas> userData;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatriculasApi() {
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
		MatriculasDao dao = new MatriculasDao();
		
		if(request.getParameter("id_matricula") !=null) {
			int matriculaId = Integer.valueOf(request.getParameter("id_matricula"));
			
			try {
				Matriculas matricula = dao.findForId(matriculaId);
				response.getWriter().append(gson.toJson(matricula));			
			} catch (SQLException e) {
				
				e.printStackTrace();
				response.getWriter().append(e.getMessage());
			}
			
			
		} else {
		   ArrayList<Matriculas> matriculas;
			try {
				matriculas = dao.findAll();
				response.getWriter().append(gson.toJson(matriculas));
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
		
		Matriculas m = new Matriculas();
		MatriculasDao dao = new MatriculasDao();
		Gson gson = new Gson();
		
	
		m.setId_aluno(request.getParameter("id_aluno"));
		m.setNome_aluno(request.getParameter("nome_aluno"));
		m.setId_turma(request.getParameter("id_turma"));
		m.setNome_curso(request.getParameter("nome_curso"));
		m.setMat_status(MetodosGerais
				.stringParaEnumMatStatus(request.getParameter("mat_status")));
		
		
		try {
			userData.put(m.getId_matricula(), m);
			dao.insert(m);
			System.out.println(m);
			
			String json=gson.toJson(m);
			
			response.getWriter()
			.append("[{\"status\":\"ok\",\"timestemp\":\"" + new Date() + "\"}, ").append(json)
			.append("]");			
			
			
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
		
		Matriculas m = new Matriculas();
		MatriculasDao dao = new MatriculasDao();
		Gson gson = new Gson();
		
		m.setId_aluno(request.getParameter("id_aluno"));
		m.setNome_aluno(request.getParameter("nome_aluno"));
		m.setId_turma(request.getParameter("id_turma"));
		m.setNome_curso(request.getParameter("nome_curso"));
		m.setMat_status(MetodosGerais
				.stringParaEnumMatStatus(request.getParameter("mat_status")));

		
		try {
			userData.put(m.getId_matricula(), m);
			dao.update(m);
			System.out.println(m);
			
			String json=gson.toJson(m);
			response.getWriter()
			.append("[{\"status\":\" alterado ok\",\"timestemp\":\"" + new Date() + "\"}, ").append(json)
			.append("]");	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//doPut		
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Matriculas m = new Matriculas();
		MatriculasDao dao = new MatriculasDao();
		
		
		try {
			m.setId_matricula(request.getParameter("id_matricula"));
			dao.delete(m);
			System.out.println("Delete: " + m.getId_matricula());
			response.getWriter().append("Matricula "+m.getId_matricula()+" Apagada...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	}//doDelete

}
