function cursosPut() {

	//var form = new FormData(document.querySelector('#update-form'));
	var form =
	{
		"id_curso": document.querySelector('#id_curso').value,
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
	var URL = 'http://localhost:8080/music_school_LP3/cursosApi';

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

function cursosPutParams() {

	//var form = new FormData(document.querySelector('#update-form'));
	var form =
	{
		"id_curso": document.querySelector('#id_curso').value,
		"nome": document.querySelector('#nome').value,
		"nivel": document.querySelector('#sel-nivel').value,
		"periodo": document.querySelector('#sel-periodo').value,
		
	};
	var params = `id_curso=${form.id_curso}
	&nome=${form.nome}
	&nivel=${form.nivel}
	&periodo=${form.periodo}`;

	console.log(form);

	const putMethod = {
		method: 'PUT', // Method itself
		headers: {
			'content-type': 'application/json; charset=UTF-8',
		},
		//params: JSON.stringify(form), // We send data in JSON format
		mode: 'cors'
	}
	var URL = 'http://localhost:8080/music_school_LP3/CursosApi?' + params;

	fetch(URL, putMethod)
		.then(function(response) {
			console.log(response);
			return response;
		})
		.then(function(teste) {
			console.log(teste);
			window.location
				.assign('http://localhost:8080/music_school_LP3/ViewIndexCursos.html')
		})
		.catch((function(error) {
			log('Falha na Requisição', error);
		}));
}

function preencheCursosParaAlterar() {

	document.querySelector('#nome').value = '';
	var selNivel = document.getElementById('sel-nivel');

	var selPeriodo = document.getElementById('sel-periodo');

	var id_curso = recebeId();
	console.log(id_curso);
	var form = document.querySelector('#update-form');
	var url =
		`http://localhost:8080/music_school_LP3/CursosApi?id_curso=${id_curso}`;


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
				document.querySelector('#id_curso').value = meuJson.id_curso;
				document.querySelector('#nome').value = meuJson.nome;
				console.log(meuJson.nome);
				selNivel.value = meuJson.nivel;
				console.log(meuJson.nivel);
				var p=meuJson.periodo.toString();
				selPeriodo.value = p.replace(/_/g," ").trim().toLowerCase();
				console.log(p.replace(/_/g," "));
				
			}

		});

}

function deletecursos(id) {

	var nomeApi = document.getElementById('nomeApi').value;
	var idApi = document.getElementById('idApi').value;
	console.log(nomeApi);
	console.log(idApi);

	const putMethod = {
		method: 'DELETE', // Method itself
		/*headers: {
			'content-type': 'application/json; charset=UTF-8',
		},
		//params: JSON.stringify(form), // We send data in JSON format
		mode: 'cors' */
	}
	//var URL = 'http://localhost:8080/music_school_LP3/cursosApi?id_curso=' + id;
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

function getCursos() {

	var url = 'http://localhost:8080/music_school_LP3/CursosApi';
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
	let dados = (['Id_curso', 'Nome', 'Nível', 'Duração', 'Ações']);

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

			let cell = row.insertCell();
			let text = document.createTextNode(element[key]);
			cell.appendChild(text);


		}
		let id = element.id_curso;
		console.log(id);

		let cell = row.insertCell();
		cell.setAttribute('style', 'text-align: center; padding: 3px');

		//let btAlterar = document.createElement("button");
		//		let a = document.createElement("a");
		//		var texto = document.createTextNode(`Alterar ${id}`);
		//		a.appendChild(texto);		
		//		a.onclick=`passaId(${id})`;
		//		a.classList = "btn btn-warning";
		//		a.href = "ViewFormAlterarcursos.html";
		//		cell.appendChild(a);
		//btAlterar.appendChild(a);
		var tagA = `<a href="ViewFormAlterarCursos.html" 
		class="btn btn-warning"
		onclick="passaId(${id})">Alterar ${id}</a>`
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
		onclick="apagaId(${id})">Excluir ${id}</a>`;
		cell.innerHTML += tagA2;

		cell.innerHTML += ' ';

		/*let btMatricular = document.createElement("button");
		let m = document.createElement("a");
		var texto = document.createTextNode("Matricular");
		m.appendChild(texto);
		m.classList = "btn btn-success";
		m.href = "ViewFormAlterarcursos.html";
		btMatricular.appendChild(m);
		btMatricular.click("getFormAlterar()");
		cell.appendChild(m);*/
	}
}

function getFormAlterar() {
	//location.assign('ViewFormAlterarcursos.html');
	console.log('Alterar');

}

function passaId(id) {
	sessionStorage.setItem('id_curso', id);
}

function recebeId() {
	return sessionStorage.getItem('id_curso');
}

function apagaId(id) {
	if (confirm(`Tem certeza que deseja excluir esse curso id: ${id}?`)) {
		deletecursos(id);
		console.log(id + " deletado");
	}
}

function proximoId() {

	var nomeApi = document.getElementById('nomeApi').value;
	var idApi = document.getElementById('idApi').value;
	console.log(idApi);
	console.log(idApi);
	//console.log(nomeApi);

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

function guardaParaCursos() {
	//	var sel = document.querySelector('input[name="status"]:checked').value;
	//	console.log(sel);
	//	document.getElementById('mat_status').value=sel;

	var sel = document.getElementById('sel-nivel');
	var nivelSel = sel.options[sel.selectedIndex].value.trim();

	var sel2 = document.getElementById('sel-periodo');
	var periodoSel = sel2.options[sel2.selectedIndex].value.trim();

	document.getElementById('nivel').value = nivelSel;
	document.getElementById('periodo').value = periodoSel;
	console.log(document.getElementById('nivel').value);
	console.log(document.getElementById('periodo').value);
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

