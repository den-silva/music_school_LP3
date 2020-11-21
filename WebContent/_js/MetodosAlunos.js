


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

	//sessionStorage.setItem('operacao', 'A');

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
		mode: 'cors',
		redirect: 'follow'
	}
	var URL = 'http://localhost:8080/music_school_LP3/AlunosApi?' + params;

	fetch(URL, putMethod)
		.then(function(response) {
			console.log(response);
			return response;
		})
		.then(function(teste) {
			console.log(teste);
			window.location
				.assign('http://localhost:8080/music_school_LP3/ViewIndexAlunos.html');

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

function deleteAlunos(id) {

	var nomeApi = document.getElementById('nomeApi').value;
	var idApi = document.getElementById('idApi').value;

	const putMethod = {
		method: 'DELETE', // Method itself
		/*headers: {
			'content-type': 'application/json; charset=UTF-8',
		},
		//params: JSON.stringify(form), // We send data in JSON format
		mode: 'cors' */
	}
	//var URL = 'http://localhost:8080/music_school_LP3/AlunosApi?id_aluno=' + id;
	var URL = `http://localhost:8080/music_school_LP3/${nomeApi}?${idApi}=${id}`

	fetch(URL, putMethod)
		.then(function(response) {
			console.log(response);
			alert(response.json());
			return response;
		})
		.catch((function(error) {
			log('Falha na Requisição', error);
		}));
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

//function guardaParaMatricula(_id, _nome) {
//	sessionStorage.setItem('id_aluno', _id);
//	sessionStorage.setItem('nome_aluno', _nome);
//}

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

	//console.log();
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
		var nome = element['nome'].trim();
		console.log(id);
		console.log(nome);

		let cell = row.insertCell();
		cell.setAttribute('style', 'text-align: center');

		//let btAlterar = document.createElement("button");
		//		let a = document.createElement("a");
		//		var texto = document.createTextNode(`Alterar ${id}`);
		//		a.appendChild(texto);		
		//		a.onclick=`passaId(${id})`;
		//		a.classList = "btn btn-warning";
		//		a.href = "ViewFormAlterarAlunos.html";
		//		cell.appendChild(a);
		//btAlterar.appendChild(a);
		var tagA = `<a href="ViewFormAlterarAlunos.html" 
		class="btn btn-warning"		
		onclick="passaId(${id})">Alterar</a>`
		cell.innerHTML += tagA;


		cell.innerHTML += ' ';


		//		let btExcluir = document.createElement("button");
		//		let e = document.createElement("a");
		//		var texto = document.createTextNode("Excluir");
		//		e.appendChild(texto);
		//		e.classList = "btn btn-danger";
		//		//e.href = apagaId(1);
		//		btExcluir.appendChild(e);
		//		cell.appendChild(e);
		var tagA2 = `<a href="" 
		class="btn btn-danger" 
		onclick="apagaId(${id})">Excluir</a>`;
		cell.innerHTML += tagA2;

		cell.innerHTML += ' ';

		sessionStorage.setItem(id, nome);
		//var tagA3 = `<a href="ViewFormInserirMatriculas.html" 
		var tagA3 = `<a href="CadMatriculaTeste.jsp" 
		class="btn btn-primary" 		
		onclick="passaId(${id});">Matricular</a>`;
		cell.innerHTML += tagA3;


//				//let btMatricular = document.createElement("button");
//				let m = document.createElement("a");
//				var texto = document.createTextNode("Matricular");
//				m.appendChild(texto);
//				m.classList = "btn btn-success";
//				m.href = "ViewFormInserirMatriculas.html";		
//				sessionStorage.setItem(id, nome);		
//				m.onclick=`passaId(${id});`;
//				console.log(`'${nome}'`);
//				//m.click(`passaObjeto(${element})`);
//				console.log(element.email);
//				//btMatricular.appendChild(m);
//				//btMatricular.click("getFormAlterar()");
//				cell.appendChild(m);
	}
	console.log(sessionStorage);
}

function guardaAlunoParaMatricula(nome) {
	console.log("Guardando na nome do aluno sessao");	
	console.log("mostra o nome?: " + nome);	
	sessionStorage.setItem("nome_aluno", nome);

}

function passaObjeto(element){
	console.log("Guardando na objeto sessao");
	sessionStorage.setItem('objeto', element.toString());
}

function retornaObjeto(){
	console.log("Pegando objeto na sessao");
	return sessionStorage.getItem('objeto');
}

function pegaAlunoNaMatricula() {
	console.log("Pegando nome do aluno sessao");
	
	return sessionStorage.getItem('nome_aluno');
}

function getFormAlterar() {
	//location.assign('ViewFormAlterarAlunos.html');
	console.log('Alterar');

}

function passaId(id) {
	sessionStorage.setItem('id_aluno', id);
}

function recebeId() {
	return sessionStorage.getItem('id_aluno');
}

function apagaId(id) {
	if (confirm(`Tem certeza que deseja excluir esse Aluno id: ${id}?`)) {
		deleteAlunos(id);
		console.log(id + " deletado");
	}
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

function redirecionar(pagina) {
	return location.href(pagina);
}


/*
function criaTabela() {
            var dadosDiv = document.body
            var linhas = ['linhaA', 'linhaB', 'linhaC', 'linhaD'];

            var tableTag = document.getElementById('minha-tabela');
            tableTag.innerHTML += `<th>Coluna1</th>
                                    <th>Coluna2</th>
                                    <th>Coluna3</th>
                                    <th>Coluna4</th>
                                    <th>Ações</th>`;

            for (var n = 0; n < 4; n++) {
                tableTag.innerHTML += `<tr>

            <td>${linhas[0] + n}</td>
            <td>${linhas[1] + n}</td>
            <td>${linhas[2] + n}</td>
            <td>${linhas[3] + n}</td>
            <td>
                <a href="./index2.html" onclick="passaId(${n+1})" >Ir para index2</a>
                <button class="btn btn-warning" onclick="alert('ok')">Editar</button>
                <button class="btn btn-danger" onclick="alerta()" >Apagar</a>
            </td>
        </tr>`
            }
        }

 */

