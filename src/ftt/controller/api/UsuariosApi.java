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
import ftt.dao.UsuariosDao;
import ftt.model.Alunos;
import ftt.model.Endereco;
import ftt.model.Usuarios;

/**
 * Servlet implementation class AlunosApi
 */
@WebServlet("/UsuariosApi")
public class UsuariosApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuariosApi() {
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
		UsuariosDao dao = new UsuariosDao();

		if (request.getParameter("id_usuario") != null) {
			int idReq = Integer.valueOf(request.getParameter("id_usuario"));

			try {
				Usuarios usuario = dao.findForId(idReq);

				response.getWriter().append(gson.toJson(usuario));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				ArrayList<Usuarios> listaUsuarios = dao.findAll();

				response.getWriter().append(gson.toJson(listaUsuarios));
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

		Usuarios usuario = new Usuarios();
		UsuariosDao dao = new UsuariosDao();
		Gson gg = new Gson();

		usuario.setId_usuario(request.getParameter("id_usuario"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));

		try {
			dao.insert(usuario);
			System.out.println("Usuario inserido com sucesso!! " + usuario.getId_usuario() + " " + usuario.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = gg.toJson(usuario);
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

		Usuarios usuario = new Usuarios();

		UsuariosDao dao = new UsuariosDao();

		usuario.setId_usuario(request.getParameter("id_usuario"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));

		try {
			dao.update(usuario);

			System.out.println("Usuario alterado com sucesso!! " 
			+ usuario.getId_usuario() + " " + usuario.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = gg.toJson(usuario);
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
		UsuariosDao dao = new UsuariosDao();
		Usuarios usuario = new Usuarios();
		usuario.setId_usuario((request.getParameter("id_usuario")));

		try {
			dao.delete(usuario);
			System.out.println("Usuario excluido com sucesso!! " 
					+ usuario.getId_usuario() + " " + usuario.getEmail());

			response.getWriter().append("[{\"status\":\"Deletado ok\",\"timestemp\":\"" + new Date() + "\"}, ")
					.append(gson.toJson(usuario.getId_usuario())).append("]");

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
