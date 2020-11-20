function guardaAlunoParaMatricula(id, nome) {
	sessionStorage.setItem('id_aluno', id);
	sessionStorage.setItem('nome_aluno', nome);
}

function pegaAlunoNaMatricula() {
	document.getElementById('id_aluno')
		.value = sessionStorage.getItem('id_aluno');
	document.getElementById('nome_aluno')
		.value = sessionStorage.getItem('nome_aluno');
}

function getTurmas() {

	var url = 'http://localhost:8080/music_school_LP3/TurmasApi';

	var combo = document.getElementById("turmas");
	pegaAlunoNaMatricula();

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
					for (let sub in item) {
						console.log('item: ' + item[sub]);
						var turma = "";
						turma += sub.nome_curso
							+ sub.horarios;
						combo.innerHTML += criaOption(turma);
					}
				});
			}
		});
}

function criaOption(turma) {
	var opt = `<option value="${turma}">${turma}</option>`;
}

function escreveLogado(){
	var logado = sessionStorage.getItem('logado');
	console.log(logado);
	return alert(`Bem vindo ${logado}`);
	
}

