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

import ftt.dao.ProfessoresDao;
import ftt.model.Endereco;
import ftt.model.Professores;

/**
 * Servlet implementation class ProfessoresApi
 */
@WebServlet("/ProfessoresApi")
public class ProfessoresApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Professores> userData;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfessoresApi() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");// Mime type

		Gson gson = new Gson();
		ProfessoresDao dao = new ProfessoresDao();

		if (request.getParameter("id_professor") != null) {
			int professorId = Integer.valueOf(request.getParameter("id_professor"));
			
			try {
				
				if(professorId == 0){
					System.out.println(dao.proximoId());
					String proximoId = dao.proximoId() + "}";
					response.getWriter().append('{').append('"').append("proximo").append('"').append(": ")
							.append(proximoId);				
				
			}else {
				Professores professor = dao.findForId(professorId);
				response.getWriter().append(gson.toJson(professor));
			} 
			
			}catch (SQLException e) {

				e.printStackTrace();
				response.getWriter().append(e.getMessage());
			}

		} else {
			ArrayList<Professores> profs;
			try {
				profs = dao.findAll();
				response.getWriter().append(gson.toJson(profs));
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* doGet(request, response); */

		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json"); // mime type

		Professores p = new Professores();
		ProfessoresDao dao = new ProfessoresDao();
		Gson gson = new Gson();

		p.setNome(request.getParameter("nome"));

		Endereco end = new Endereco();
		end.setRua(request.getParameter("rua"));
		end.setNumero(request.getParameter("numero"));
		end.setBairro(request.getParameter("bairro"));
		end.setCidade(request.getParameter("cidade"));
		end.setUf(request.getParameter("uf"));
		end.setComplemento(request.getParameter("complemento"));

		p.setEndereco(end);
		p.setEmail(request.getParameter("email"));
		p.setSenha(request.getParameter("senha"));

		try {
			userData.put(p.getId_professor(), p);
			dao.insert(p);
			System.out.println(p);

			response.getWriter().append("{\"status\":\"ok\",\"timestemp\":" + new Date() + "}");
			response.sendRedirect("ViewIndexProfessores.html");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// doPost

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json"); // mime type

		Professores p = new Professores();
		ProfessoresDao dao = new ProfessoresDao();
		Gson gson = new Gson();

		p.setId_professor(request.getParameter("id_professor"));
		p.setNome(request.getParameter("nome"));
		// Separando os requests na classe endereço
		Endereco end = new Endereco();
		end.setRua(request.getParameter("rua"));
		end.setNumero(request.getParameter("numero"));
		end.setBairro(request.getParameter("bairro"));
		end.setCidade(request.getParameter("cidade"));
		end.setUf(request.getParameter("uf"));
		end.setComplemento(request.getParameter("complemento"));

		p.setEndereco(end);// Adicionando endereço

		p.setEmail(request.getParameter("email"));		
		p.setSenha(request.getParameter("senha"));

		try {
			userData.put(p.getId_professor(), p);
			dao.update(p);
			System.out.println(p);

			response.getWriter().append("{\"status\":\"ok\",\"timestemp\":" + new Date() + "}");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // doPut

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Professores p = new Professores();
		ProfessoresDao dao = new ProfessoresDao();

		try {
			p.setId_professor(request.getParameter("id_professor"));
			dao.delete(p);
			System.out.println("Delete: " + p.getId_professor());
			response.getWriter().append("Professor " + p.getId_professor() + " Apagado...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// doDelete

}
