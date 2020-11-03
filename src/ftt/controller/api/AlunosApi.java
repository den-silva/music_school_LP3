package ftt.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ftt.dao.AlunosDao;
import ftt.model.Alunos;
import ftt.model.Endereco;

/**
 * Servlet implementation class AlunosApi
 */
@WebServlet("/AlunosApi")
public class AlunosApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlunosApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		AlunosDao dao = new AlunosDao();

		if (request.getParameter("id_aluno") != null) {
			int idReq = Integer.valueOf(request.getParameter("id_aluno"));

			try {
				Alunos aluno = dao.findForId(idReq);

				response.getWriter().append(gson.toJson(aluno));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				ArrayList<Alunos> listaAlunos = dao.findAll();

				response.getWriter().append(gson.toJson(listaAlunos));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json");// Mime type

		Alunos aluno = new Alunos();
		AlunosDao dao = new AlunosDao();
		Gson gg = new Gson();

		aluno.setId_aluno(request.getParameter("id_aluno"));
		aluno.setNome(request.getParameter("nome"));
		Endereco end = new Endereco();
		end.setRua(request.getParameter("rua"));
		end.setNumero(request.getParameter("numero"));
		end.setBairro(request.getParameter("bairro"));
		end.setCidade(request.getParameter("cidade"));
		end.setUf(request.getParameter("uf"));
		end.setComplemento(request.getParameter("complemento"));

		aluno.setEndereco(end);
		aluno.setEmail(request.getParameter("email"));
		aluno.setSenha(request.getParameter("senha"));

		try {
			dao.insert(aluno);
			System.out.println("Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = gg.toJson(aluno);
		response.getWriter().append("[{\"status\":\"ok\",\"timestemp\":\"" + new Date() + "\"}, ").append(json)
				.append("]");

		// doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*
		 * Métodos do Servlet Request getReader() -> java.io.BufferedReader Recupera o
		 * corpo da solicitação como dados de caractere usando um BufferedReader.
		 */
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json");// Mime type

		Gson gg = new Gson();

		Alunos aluno = new Alunos();

		AlunosDao dao = new AlunosDao();

		aluno.setId_aluno(request.getParameter("id_aluno"));
		aluno.setNome(request.getParameter("nome"));
		// Separando os requests na classe endereço
		Endereco end = new Endereco();
		end.setRua(request.getParameter("rua"));
		end.setNumero(request.getParameter("numero"));
		end.setBairro(request.getParameter("bairro"));
		end.setCidade(request.getParameter("cidade"));
		end.setUf(request.getParameter("uf"));
		end.setComplemento(request.getParameter("complemento"));

		aluno.setEndereco(end);// Adicionando endereço
		aluno.setEmail(request.getParameter("email"));
		aluno.setSenha(request.getParameter("senha"));
		System.out.println(request.getParameter("nome"));
		try {
			dao.update(aluno);
			System.out.println("Dados alterados com sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = gg.toJson(aluno);
		response.getWriter().append("[{\"status\":\"Atualizado ok\",\"timestemp\":\"" + new Date() + "\"}, ")
				.append(json).append("]");

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json");// Mime type

		Gson gson = new Gson();
		AlunosDao dao = new AlunosDao();
		Alunos aluno = new Alunos();
		aluno.setId_aluno(request.getParameter("id_aluno"));

		try {
			dao.delete(aluno);

			response.getWriter().append("[{\"status\":\"Deletado ok\",\"timestemp\":\"" + new Date() + "\"}, ")
					.append(gson.toJson(aluno.getId_aluno())).append("]");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

//Metodo doPut com menos código
/*
 * Gson gg = new Gson();
 * 
 * StringBuilder stringJson = new StringBuilder(); BufferedReader reader =
 * request.getReader(); String linha;
 * 
 * while((linha = reader.readLine()) != null) { stringJson.append(linha); }
 * reader.close();
 * 
 * String requisicaoJson=stringJson.toString();;
 * 
 * Alunos aluno=gg.fromJson(requisicaoJson, Alunos.class);
 * System.out.println(requisicaoJson); //Alunos aluno = new Alunos(); AlunosDao
 * dao = new AlunosDao();
 * 
 * try { dao.update(aluno); System.out.println("Dados alterados com sucesso!!");
 * } catch (SQLException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * String json = gg.toJson(aluno);
 * response.getWriter().append("[{\"status\":\"Atualizado ok\",\"timestemp\":\""
 * + new Date() + "\"}, ") .append(json).append("]");
 */
