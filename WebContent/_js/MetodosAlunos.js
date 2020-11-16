function alunosPut() {

	//var form = new FormData(document.querySelector('#update-form'));
	var form =
	{
		"id_aluno": document.querySelector('#id_aluno').value,
		"nome": document.querySelector('#nome').value,
		"endereco": {
			"rua": document.querySelector('#rua').value,
			"complemento": document.querySelector('#complemento').value,
			"numero": document.querySelector('#numero').value,
			"bairro": document.querySelector('#bairro').value,
			"cidade": document.querySelector('#cidade').value,
			"uf": document.querySelector('#uf').value,
		},
		"email": document.querySelector('#email').value,
		"senha": document.querySelector('#senha').value,
	};
	console.log(form);

	const putMethod = {
		method: 'PUT', // Method itself
		headers: {
			'content-type': 'application/json; charset=UTF-8',
		},
		body: JSON.stringify(form), // We send data in JSON format
		mode: 'cors'
	}
	var URL = 'http://localhost:8080/music_school_LP3/AlunosApi';

	fetch(URL, putMethod)
		.then(function(response) {
			console.log(response);
			return response;
		})
		.then(function(teste) {
			console.log(teste)
		})
		.catch((function(error) {
			log('Falha na Requisição', error);
		}));
}

function alunosPutParams() {

	//var form = new FormData(document.querySelector('#update-form'));
	var form =
	{
		"id_aluno": document.querySelector('#id_aluno').value,
		"nome": document.querySelector('#nome').value,
		"rua": document.querySelector('#rua').value,
		"complemento": document.querySelector('#complemento').value,
		"numero": document.querySelector('#numero').value,
		"bairro": document.querySelector('#bairro').value,
		"cidade": document.querySelector('#cidade').value,
		"uf": document.querySelector('#uf').value,
		"email": document.querySelector('#email').value,
		"senha": document.querySelector('#senha').value,
	};
	var params = `id_aluno=${form.id_aluno}
	&nome=${form.nome}
	&rua=${form.rua}
	&complemento=${form.complemento}
	&numero=${form.numero}
	&bairro=${form.bairro}
	&cidade=${form.cidade}
	&uf=${form.uf}
	&email=${form.email}
	&senha=${form.senha}`;

	console.log(form);

	const putMethod = {
		method: 'PUT', // Method itself
		headers: {
			'content-type': 'application/json; charset=UTF-8',
		},
		//params: JSON.stringify(form), // We send data in JSON format
		mode: 'cors'
	}
	var URL = 'http://localhost:8080/music_school_LP3/AlunosApi?' + params;

	fetch(URL, putMethod)
		.then(function(response) {
			console.log(response);
			return response;
		})
		.then(function(teste) {
			console.log(teste)
		})
		.catch((function(error) {
			log('Falha na Requisição', error);
		}));
}

function preencheAlunosParaAlterar() {

	document.querySelector('#nome').value = '';
	document.querySelector('#rua').value = '';
	document.querySelector('#complemento').value = '';
	document.querySelector('#numero').value = '';
	document.querySelector('#bairro').value = '';
	document.querySelector('#cidade').value = '';
	document.querySelector('#uf').value = '';
	document.querySelector('#email').value = '';
	document.querySelector('#senha').value = '';

	//var id_aluno = document.querySelector('#id_aluno').value;
	var id_aluno = recebeId();
	console.log(id_aluno);
	var form = document.querySelector('#update-form');
	var url =
		`http://localhost:8080/music_school_LP3/AlunosApi?id_aluno=${id_aluno}`;


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
			if (meuJson.nome != null) {
				console.log(meuJson);
				document.querySelector('#id_aluno').value = meuJson.id_aluno;
				document.querySelector('#nome').value = meuJson.nome;
				console.log(meuJson.nome);
				document.querySelector('#rua').value = meuJson.endereco.rua;
				document.querySelector('#complemento').value = meuJson.endereco.complemento;
				document.querySelector('#numero').value = meuJson.endereco.numero;
				document.querySelector('#bairro').value = meuJson.endereco.bairro;
				document.querySelector('#cidade').value = meuJson.endereco.cidade;
				document.querySelector('#uf').value = meuJson.endereco.uf;
				document.querySelector('#email').value = meuJson.email;
				document.querySelector('#senha').value = meuJson.senha;
			}

		});

}

function getAlunos() {

	var url = 'http://localhost:8080/music_school_LP3/AlunosApi';
	var body = document.body;
	var tabela = document.getElementById("myTable");
	body.appendChild(tabela);

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
				setTabelaHead(tabela);
				//setTabelaHead(tabela,meuJson);
				geraTabela(tabela, meuJson);
				
			}

		});

}

function setTabelaHead(tabela) {
	//let dados = msg.nome;
	//let data = Object.keys(msg[0]);
	let dados = (['Id_Aluno', 'Nome', 'Email', 'Ações']);

	//let thead = tabela.createTHead();
	var row = tabela.insertRow();


	for (let key of dados) {

		let th = document.createElement('th');
		let text = document.createTextNode(key);
		th.appendChild(text);
		row.appendChild(th);
		

		
	}

}

function geraTabela(tabela, msg) {

	console.log();
	//let dados = msg.nome;
	for (let element of msg) {
		let row = tabela.insertRow();
		for (key in element) {
			if (key != 'endereco' && key != 'senha') {
				let cell = row.insertCell();
				let text = document.createTextNode(element[key]);
				cell.appendChild(text);
			}

		}
		let id = element.id_aluno;
		console.log(id);

		let cell = row.insertCell();

		let btAlterar = document.createElement("button");
		let a = document.createElement("a");
		var texto = document.createTextNode("Alterar");
		a.appendChild(texto);
		a.classList="btn btn-warning";
		a.href = "ViewFormAlterarAlunos.html";
		btAlterar.appendChild(a);		
		cell.appendChild(a);
		
		
		cell.innerHTML += ' ';


		let btExcluir = document.createElement("button");
		let e = document.createElement("a");
		var texto = document.createTextNode("Excluir");
		e.appendChild(texto);
		e.classList="btn btn-danger";
		//e.href = apagaId(1);
		btExcluir.appendChild(e);
		cell.appendChild(e);
		
		cell.innerHTML += ' ';

		let btMatricular = document.createElement("button");
		let m = document.createElement("a");
		var texto = document.createTextNode("Matricular");
		m.appendChild(texto);
		m.classList="btn btn-success";
		m.href = "ViewFormAlterarAlunos.html";
		btMatricular.appendChild(m);
		btMatricular.click("getFormAlterar()");
		cell.appendChild(m);
	}
}

function getFormAlterar() {
	//location.assign('ViewFormAlterarAlunos.html');
	console.log('Alterar');
	
}

function passaId() {
	sessionStorage.setItem('id_aluno',1);
}

function recebeId(){
	return sessionStorage.getItem('id_aluno');
}

function apagaId(id){
	 if (confirm('Tem certeza que deseja excluir esse Aluno?')){
		console.log(id + " deletado");
	}
		          

    }

