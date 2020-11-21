<%@page import="ftt.model.Horario"%>
<%@page import="ftt.model.Cursos"%>
<%@page import="ftt.dao.CursosDao"%>
<%@page import="ftt.model.Turmas"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ftt.dao.TurmasDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<!-- <meta charset="ISO-8859-1"> -->
<title>Cadastro de Alunos</title>
<!-- 
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<script src="_js/cep.js"></script>
<script src="_js/MetodosAlunos.js"></script>
<script src="_js/MetodosMatriculas.js"></script>
<link rel="stylesheet" href="_css/style.css">
<link rel="stylesheet" href="_css/bootstrap.css">
<link rel="stylesheet" href="_css/bootstrap-minty-bootswatch.css">
<style>
.fundo {
	background-image: url(_img/musical1.jpg);
	background-repeat: no-repeat;
	background-size: cover;
	position: relative;
	width: 100%;
	min-height: 820px;
	height: 50%;
}

.card {
	background-color: #FFFAF0;
}
</style>


</head>
<body onload="getTurmas()">
	<input type="hidden" id="nomeApi" value="MatriculasApi">
	<input type="hidden" id="idApi" value="id_matricula">
	<input type="hidden" id="id_turma" name="id_turma">
	<% 
		TurmasDao tDao=new TurmasDao();
		List<Turmas> turmas=tDao.findAll();
		
	%>

	<main class="fundo">
		<br> <br> <br>
		<div class="row">
			<div class="card" style="width: 40rem;" id="login">
				<div class="card-body">
					<form method="post" action="/music_school_LP3/MatriculasApi">
						<fieldset>
							<legend>Nova Matricula:</legend>

							<div class="form-group">
								<label>Id Aluno </label><br> <input type="number"
									id="id_aluno" class="form-control" name="id_aluno" size="2"
									required readonly="readonly">
							</div>

							<div class="form-group">
								<label>Nome do Aluno </label><br> <input type="text"
									id="nome_aluno" readonly="readonly" class="form-control"
									name="nome_aluno" size="40" required>
							</div>
							<div class="" onchange="pegaSelecionadoNoCombo()" >
								<label>Lista de Turmas </label><br> <select id="turmas"
									class="form-control" name="nome">
									<%
									for(Turmas t : turmas){
									String str="";
									String horas="";
									for(Horario h:t.getHorarios()){
										horas+=" >"+h.getDiaSemana()+" - "+h.getFaixaHorario();
									}
									CursosDao cDao=new CursosDao();
									Cursos cr=cDao.findForId(t.getId_curso());
									str+=t.getId_turma()+"-"+cr.getNome()
											+" - "
											+cr.getNivel().getNivelCurso()
											+horas;
										
									%>										
										<option value="<%=str%>"><%=str%></option><%
									}
									//String selecionado=request.getAttribute("");
									%>
								</select>
							</div>
							
							
							<div onchange="pegaStatus()" class="status">
							<label>Status </label><br> <input type="radio" class="radio"
								name="status" id="ativa" checked="checked" > ATIVA</input> <br><br><input type="radio"
								class="radio" name="status" id="inativa"> INATIVA</input> <br>
							<br>
							</div>
							

							<button type="submit" class="btn btn-outline-success">Matricular
								Aluno</button>

						</fieldset>

					</form>
					<br> <br>
				</div>
			</div>
		</div>
		<br> <br> <br>
	</main>

	<footer class="mt-auto py-3">
		<div id="contact-area">
			<div class="container">
				<div class="row">
					<div class="col-md-12">copyright @2020</div>
				</div>
			</div>
		</div>
	</footer>


</body>
</html>