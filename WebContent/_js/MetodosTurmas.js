
function pegaSelecionadoNoComboCursos() {
	var sel = document.getElementById('cursos');
	var selecionado = sel.options[sel.selectedIndex].value.trim();
	console.log(selecionado);
	var teste = selecionado.substring(0, 1);
	document.getElementById('id_curso').value = teste;
	console.log(document.getElementById('id_curso').value);
}

function recebeIdNoInput() {
	var idPass = sessionStorage.getItem('id_professor');

	document
		.querySelector('#id_professor').value = idPass;

	console.log("Id passado: " + document.querySelector('#id_professor').value);
	document
		.querySelector('#nome_professor').value = sessionStorage.getItem(idPass);
	console.log("Nome passado: " + document
		.querySelector('#nome_professor').value);
}



function guardaParaSalvarTurma(){
	var cursoSel=document.getElementById('cursos').value;
	var idCurso = cursoSel.substring(0, 1);
	document.getElementById('id_curso').value=idCurso;
	console.log("id_curso: "+document.getElementById('id_curso').value);
	
	var checados = document
		.querySelector('#dias-semana')
		.getElementsByTagName("input");
		
	console.log("checados: "+ checados.value);
	var faixa = document.getElementById('faixas-horario').value;
	console.log("faixa: "+faixa);
	var horario = document.getElementById('horario');
	horario.value = "";
	var cont = 0;

	for (var i = 0; i < checados.length; i++) {
		var elemento = checados[i];

		if (elemento.checked) {
			//console.log(elemento.value);
			horario.value += elemento.value + "-" + faixa + ">";
		}
	}
	var teste = horario.value;
	var posMaior = teste.lastIndexOf(">");
	horario.value = teste.substring(0, posMaior);
	console.log("string horario: "+  horario.value);	
}

/*
function verCheckboxSelecionado() {
	var checados = document
		.querySelector('#dias-semana')
		.getElementsByTagName("input");
	console.log(checados);
	var faixa = document.getElementById('faixas-horario');
	var horario = document.getElementById('horario');
	horario.value = "";
	var cont = 0;

	for (var i = 0; i < checados.length; i++) {
		var elemento = checados[i];

		if (elemento.checked) {
			//console.log(elemento.value);
			horario.value += elemento.value + "-" + faixa + ">";
		}
	}
	var teste = horario.value;
	var posMaior = teste.lastIndexOf(">");
	horario.value = teste.substring(0, posMaior);
	console.log(horario.value);
}
 */
