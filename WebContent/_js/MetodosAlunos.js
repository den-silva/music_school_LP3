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

	var id_aluno = document.querySelector('#id_aluno').value;	
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

function proximoId() {	

	var nomeApi = document.getElementById('nomeApi').value;
	var idApi = document.getElementById('idApi').value;
	
	var url =
		`http://localhost:8080/music_school_LP3/${nomeApi}?${idApi}=0`;


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
			console.log(response);
			return response.json();
		})
		.then(function(meuJson) {			
				console.log(meuJson.proximo);
				document.getElementById(idApi).value = meuJson.proximo;				
			
		});

}

