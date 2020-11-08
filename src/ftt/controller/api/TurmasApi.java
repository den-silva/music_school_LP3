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
import ftt.dao.TurmasDao;
import ftt.dao.UsuariosDao;
import ftt.enums.EnumNivelCurso;
import ftt.model.Alunos;
import ftt.model.Endereco;
import ftt.model.MetodosGerais;
import ftt.model.Turmas;
import ftt.model.Usuarios;

/**
 * Servlet implementation class AlunosApi
 */
@WebServlet("/TurmasApi")
public class TurmasApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TurmasApi() {
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
		TurmasDao dao = new TurmasDao();

		if (request.getParameter("id_turma") != null) {
			int idReq = Integer.valueOf(request.getParameter("id_turma"));

			try {
				Turmas turma = dao.findForId(idReq);

				response.getWriter().append(gson.toJson(turma));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				ArrayList<Turmas> listaTurmas = dao.findAll();

				response.getWriter().append(gson.toJson(listaTurmas));
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

		Turmas turma = new Turmas();
		TurmasDao dao = new TurmasDao();
		Gson gg = new Gson();

		turma.setId_turma(request.getParameter("id_turma"));
		turma.setId_curso(request.getParameter("id_curso"));
		turma.setNome_curso(request.getParameter("nome_curso"));
		turma.setId_professor(request.getParameter("id_professor"));
		turma.setNome_professor(request.getParameter("nome_professor"));
		String retNivel = request.getParameter("nivel");

		turma.setNivel(MetodosGerais.stringParaEnumNivel(retNivel));

//		if (retNivel == EnumNivelCurso.BASICO.getNivelCurso()) {
//			turma.setNivel(EnumNivelCurso.BASICO);
//		} else if (retNivel == EnumNivelCurso.INTERMEDIARIO.getNivelCurso()) {
//			turma.setNivel(EnumNivelCurso.INTERMEDIARIO);
//		} else {
//			turma.setNivel(EnumNivelCurso.AVANCADO);
//		}

		try {
			dao.insert(turma);
			System.out.println("Turma inserida com sucesso!! " + turma.getId_turma() + " Professor: "
					+ turma.getNome_professor() + " Curso: " + turma.getNome_curso());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = gg.toJson(turma);
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

		Turmas turma = new Turmas();
		TurmasDao dao = new TurmasDao();
		Gson gg = new Gson();

		turma.setId_turma(request.getParameter("id_turma"));
		turma.setId_curso(request.getParameter("id_curso"));
		turma.setNome_curso(request.getParameter("nome_curso"));
		turma.setId_professor(request.getParameter("id_professor"));
		turma.setNome_professor(request.getParameter("nome_professor"));
		String retNivel = request.getParameter("nivel");

		turma.setNivel(MetodosGerais.stringParaEnumNivel(retNivel));

		try {
			dao.update(turma);

			System.out.println("Turma alterada com sucesso!! " + turma.getId_turma() + " Professor: "
					+ turma.getNome_professor() + " Curso: " + turma.getNome_curso());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = gg.toJson(turma);
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
		TurmasDao dao = new TurmasDao();
		Turmas turma = new Turmas();
		turma.setId_turma(request.getParameter("id_turma"));

		try {
			dao.delete(turma);
			System.out.println("Turma excluida com sucesso!! " + turma.getId_turma() + " Professor: "
					+ turma.getNome_professor() + " Curso: " + turma.getNome_curso());

			response.getWriter().append("[{\"status\":\"Deletado ok\",\"timestemp\":\"" + new Date() + "\"}, ")
					.append(gson.toJson(turma.getId_turma())).append("]");

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
