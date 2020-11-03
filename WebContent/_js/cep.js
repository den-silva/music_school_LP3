function apiCep() {

	var cepUsu = document.getElementById('cep').value;
	document.getElementById('rua').value = "";
	document.getElementById('bairro').value = "";
	document.getElementById('cidade').value = "";
	document.getElementById('uf').value = "";
	document.getElementById('complemento').value = "";

	//console.log(URL);
	// Caso o CEP não esteja nesse formato ele é inválido!
	var objER = /^[0-9]{5}-[0-9]{3}$/;

	//AJAX

	//fetch(URL, { mode: "cors" })
	if (objER.test(cepUsu)) {
		var URL = 'http://viacep.com.br/ws/' + cepUsu + '/json/';
		fetch(URL, { mode: "cors" })
			.then(function(response) {
				console.log(response);
				return response.json();
			})
			.then(function(text) {
				console.log('Requisição feita com sucesso\n', text);
				document.getElementById('rua').value = text.logradouro;
				document.getElementById('bairro').value = text.bairro;
				document.getElementById('cidade').value = text.localidade;
				document.getElementById('uf').value = text.uf;
				document.getElementById('complemento').value = text.complemento;

			})
			.catch((function(error) {
				log('Falha na Requisição', error);
			}));
	}
	else {
		cepUsu.value = "";
		alert("Preencha o cep corretamente!!");


	}

}