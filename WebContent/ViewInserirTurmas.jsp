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
<title>Cadastro de Turmas</title>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<script src="_js/cep.js"></script>
<script src="_js/MetodosAlunos.js"></script>
<script src="_js/MetodosCursos.js"></script>
<script src="_js/MetodosProfessores.js"></script>
<script src="_js/MetodosTurmas.js"></script>
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
<body onload="recebeIdNoInput()">
	<input type="hidden" id="aux" name="aux"
		value="document
		.querySelector('#id_professor').value">
	<%
		CursosDao cDao = new CursosDao();
	List<Cursos> cursos = cDao.findAll();

	TurmasDao tDao = new TurmasDao();
	int idPass = 0;

	//Turmas tr = tDao.findForId(idPass);
	%>

	<main class="fundo">
		<br> <br> <br>
		<div class="row">
			<div class="card" style="width: 40rem;" id="login">
				<div class="card-body">
					<form method="post" action="/music_school_LP3/TurmasApi">


						<fieldset onchange="guardaParaSalvarTurma()" >
							<legend>Nova Turma</legend>
							<input type="hidden" id="nomeApi" value="MatriculasApi">
							<input type="hidden" id="idApi" value="id_matricula"> 
							
							<input type="hidden" id="id_curso" name="id_curso"> <input
								type="hidden" id="horario" name="horario">

							<div class="form-group">
								<label>Id Professor </label><br> <input type="number"
									id="id_professor" class="form-control" name="id_professor"
									size="2" required readonly="readonly">
							</div>


							<div class="form-group">
								<label>Nome do Professor </label><br> <input type="text"
									id="nome_professor" readonly="readonly" class="form-control"
									name="nome_professor" size="40" required value="">
							</div>
							<div class="form-group" onchange="pegaSelecionadoNoComboCursos()">
								<label>Lista de Cursos </label><br> <select id="cursos"
									class="custom-select" name="cursos">
									<%
										for (Cursos c : cursos) {
										String str = "";

										str += c.getId_curso() + " - " + c.getNome() + " - " + c.getNivel().getNivelCurso() + " - "
										+ c.getPeriodo().getPeriodoCurso();
									%>
									<option value="<%=str%>"><%=str%></option>
									<%
										}
									//String selecionado=request.getAttribute("");
									%>
								</select>
							</div>


							<span> <!-- itens HTML para montar o horário -->
								<div class="form-group" id="dias-semana">
									<label class="control-label" for="disabledInput">Dias
										da Semana</label>
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" value="Segunda"
											id="customCheck1" checked=""> <label
											class="custom-control-label" for="customCheck1">Segunda</label>
									</div>
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" value="Terça"
											id="customCheck2"> <label
											class="custom-control-label" for="customCheck2">Terça</label>
									</div>
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" value="Quarta"
											id="customCheck3"> <label
											class="custom-control-label" for="customCheck3">Quarta</label>
									</div>
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" value="Quinta"
											id="customCheck4"> <label
											class="custom-control-label" for="customCheck4">Quinta</label>
									</div>
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" value="Sexta"
											id="customCheck5"> <label
											class="custom-control-label" for="customCheck5">Sexta</label>
									</div>
								</div>
								<div class="form-group" >
									<label class="control-label" for="disabledInput">Faixas
										de Horário</label> <select class="custom-select" id="faixas-horario">
										<!-- 										<option selected="">Escolha as faixas de horário</option> -->
										<option value="10h as 12h">10h as 12h</option>
										<option value="13h as 15h">13h as 15h</option>
										<option value="15h as 17h">15h as 17h</option>
										<option value="17h as 19h">17h as 19h</option>
									</select>
								</div>
							</span>

							<button onclick="guardaParaSalvarTurma()" type="submit" class="btn btn-outline-success">Cadastrar
								Turma</button>

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