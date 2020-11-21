// import ('MetodosAlunos.js');

function getAlunoParaMatricula() {
	var id = sessionStorage.getItem('id_aluno');
	console.log(id);

	document.getElementById('id_aluno')
		.value = id;
	document.getElementById('nome_aluno')
		.value = sessionStorage.getItem(id);
}

function getTurmas() {

	var url = 'http://localhost:8080/music_school_LP3/TurmasApi';

	var combo = document.getElementById("turmas");

	var id = sessionStorage.getItem('id_aluno');
	console.log(id);


	document.getElementById('id_aluno')
		.value = id;
	document.getElementById('nome_aluno')
		.value = sessionStorage.getItem(id);

	var myInit = {
		method: 'GET',
		headers: {
			'content-type': 'application/json;',
		},
		mode: 'cors',
		// cache: 'default'
	};

	fetch(url, myInit)
		.then(function(response) {
			//console.log(response);
			return response.json();
		})
		.then(function(meuJson) {

			if (meuJson != null) {
				console.log(meuJson);
				meuJson.map((item) => {
					var strCombo = "";
					var nome_curso = item.nome_curso;
					var listaHorarios = "";
					item.horarios.map((h) => {
						listaHorarios += `${h.diaSemana} - ${h.faixaHorario} | `;
					});
					console.log(sessionStorage);
					console.log(listaHorarios.substring(0, listaHorarios.length - 2));
					var aux = listaHorarios.substring(0, listaHorarios.length - 2);
					strCombo += `${nome_curso} ${aux}`;
					//combo.innerHTML += criaOption(strCombo);

					//					for (let sub in item) {
					//						console.log('item: ' + sub.value);
					//						var turma = "";
					//						turma += sub.nome_curso
					//							+ sub.horarios;
					//						combo.innerHTML += criaOption(turma);
					//					}
				});
			}
		});
}

function criaOption(turma) {
	var opt = `<option value="${turma}">${turma}</option>`;
	return opt;
}

function escreveLogado() {
	var logado = sessionStorage.getItem('logado');
	console.log(logado);
	return alert(`Bem vindo ${logado}`);

}

function pegaSelecionadoNoCombo() {
	var sel = document.getElementById('turmas');
	var selecionado = sel.options[sel.selectedIndex].value.trim();
	console.log(selecionado);
	 var teste = selecionado.substring(0, 2);
//document.getElementById('id_turma').value
	console.log(teste);
}

function pegaStatus() {
	var sel = document.getElementsByName('status');
	for (var i in sel) {
		if(i.checked){
			console.log(i.value);
		}		
	}
}

