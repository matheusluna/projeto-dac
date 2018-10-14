angular.module('app.controllers', [])
  
.controller('vagasCtrl', function ($scope, $stateParams, $http, $ionicPopup, $window) {
	//dados dos alunos
	$scope.candidato = [];
	//pegando os dados no localStorage
	var dado = localStorage.getItem("candidato");
	$scope.candidato = angular.fromJson(dado);

	// var das enquetes
	$scope.vagas = [];

	//motando objeto
	var req = {
		method: 'GET',
		url: "http://localhost:8080/rhecruta-java/rest/vaga",
		headers: {
			'Authorization': 'Bearer ' + $scope.candidato
		}

	}

	$http(req).then(function (resp) {
		$scope.vagas = resp.data;
		//salvando na variavel
		console.log(resp.data);

	}, function (err) {
		//alerta de erro
		var alertPopup = $ionicPopup.alert({
			title: 'Erro!',
			template: 'Não foi possível carregar as vagas!'
		});
	});

	$scope.buscar = function (filtro) {
		
		if (filtro.escolha == "cidade") {
			var dados = 'cidade=' + filtro.busca
		}else if (filtro.escolha == "descricao") {
			var dados = 'descricao=' + filtro.busca
		}else if (filtro.escolha == "empresa") {
			var dados = 'empresa=' + filtro.busca
		}

		console.log(dados);

		var req = {
			method: 'POST',
			url: 'http://localhost:8080/rhecruta-java/rest/vaga/'+ filtro.escolha,
			data: dados,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}

		$http(req).then(function (resp) {
			$scope.vagas = resp.data;
			//salvando na variavel
			console.log(resp.data);

		}, function (err) {
			//alerta de erro
		var alertPopup = $ionicPopup.alert({
			title: 'Erro!',
			template: 'Não foi possível buscar as vagas!'
		});
		});
	}

	$scope.interesse = function (id) {

		var req = {
			method: 'POST',
			url: 'http://localhost:8080/rhecruta-java/rest/candidato/interesse',
			data: 'idVaga=' + id,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
				'Authorization': 'Bearer ' + $scope.candidato.senha
			}
		}

		$http(req).then(function (resp) {
			//alerta de sucesso
			var Popup = $ionicPopup.show({
				title: 'Sucesso!',
				template: 'Marcado como interesse!',
				buttons: [
					{
						text: '<b>Okay</b>',
						type: 'button-positive',
						onTap: function (e) {
							//atualizando pagina
							$window.location.reload(true);
						}
					}]
			});

		}, function (err) {
			//alerta de erro
			var alertPopup = $ionicPopup.alert({
				title: 'Erro!',
				template: 'Não foi possível marcar o interesse!'
			});
		});
	}

	$scope.candidatura = function (id) {

		var req = {
			method: 'POST',
			url: 'http://localhost:8080/rhecruta-java/rest/candidatura',
			data:{'vagaId': id},
			headers: {
				'Authorization': 'Bearer ' + $scope.candidato.senha
			}
		}

		console.log(req.data);

		$http(req).then(function (resp) {
			//alerta de sucesso
			var Popup = $ionicPopup.show({
				title: 'Sucesso!',
				template: 'Marcado como candidato!',
				buttons: [
					{
						text: '<b>Okay</b>',
						type: 'button-positive',
						onTap: function (e) {
							//atualizando pagina
							$window.location.reload(true);
						}
					}]
			});

		}, function (err) {
			//alerta de erro
			var alertPopup = $ionicPopup.alert({
				title: 'Erro!',
				template: 'Não foi possível marcar o interesse!'
			});
		});
	}

})
   
.controller('listaDeInteressesCtrl', function ($scope, $stateParams, $http, $ionicPopup) {
	//
	$scope.candidato = [];
	$scope.interesses = [];
	//pegando os dados no localStorage
	var dado = localStorage.getItem("candidato");
	$scope.candidato = angular.fromJson(dado); 

	//motando objeto
	var req = {
		method: 'GET',
		url: "http://localhost:8080/rhecruta-java/rest/candidato/interesse",
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
			'Authorization': 'Bearer ' + $scope.candidato.senha
		}

	}

	$http(req).then(function (resp) {
		$scope.interesses = resp.data;
		$scope.interesseVaga = [];

		for (item of $scope.interesses){
			$http.get("http://localhost:8080/rhecruta-java/rest/vaga/" + item).then(function (resp) {
				$scope.interesseVaga.push(resp.data)
				console.log($scope.interesseVaga);
			});
		}

	}, function (err) {
		//alerta de erro
		var alertPopup = $ionicPopup.alert({
			title: 'Erro!',
			template: 'Não foi possível carregar os interesses!'
		});
	});

})
   
.controller('candidaturasCtrl', function ($scope, $stateParams, $http, $ionicPopup) {
	//dados dos alunos
	$scope.candidato = [];
	//pegando os dados no localStorage
	var dado = localStorage.getItem("candidato");
	$scope.candidato = angular.fromJson(dado);

	$scope.candidatura = [];

	//motando objeto
	var req = {
		method: 'GET',
		url: "http://localhost:8080/rhecruta-java/rest/candidatura",
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
			'Authorization': 'Bearer ' + $scope.candidato.senha
		}

	}

	$http(req).then(function (resp) {
		$scope.candidaturaVagas = [];
		//salvando na variavel
		$scope.candidatura = resp.data; 
		console.log(resp.data);

		for (item of $scope.candidatura){
			$http.get("http://localhost:8080/rhecruta-java/rest/vaga/" + item.vagaId).then(function (resp) {
				$scope.candidaturaVagas.push(resp.data)
				console.log($scope.candidaturaVagas);
			});
		}

	}, function (err) {
		//alerta de erro
		var alertPopup = $ionicPopup.alert({
			title: 'Erro!',
			template: 'Não foi possível carregar a vaga!'
		});
	});

})
   
.controller('menuCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])
   
.controller('loginCtrl', function ($scope, $http, $stateParams, $ionicPopup, $state) {
	$scope.candidato = [];

	$scope.loginCandidato = function (login) {

		var req = {
			method: 'POST',
			url: 'http://localhost:8080/rhecruta-java/rest/login',
			data: 'email=' + login.email + '&senha=' + login.senha,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}

		$http(req).then(function (resp) {
			//salvando no localStorage
			$scope.candidato = resp.data;
			var dados = angular.toJson($scope.candidato);
			localStorage.setItem("candidato", dados);

			console.log(dados);
			//indo pra pagina de enquetes
			$state.go('menu.vagas');

		}, function (err) {
			console.log(err.data);
			
			//alerta de erro
			var alertPopup = $ionicPopup.alert({
				title: 'Erro!',
				template: 'Não foi possível efetuar o login!'
			});
		});
	}

})
   
.controller('cadastroCtrl', function ($scope, $stateParams, $http, $ionicPopup, $state) {
	//candidato
	//$scope.candidato = [];

	$scope.cadastro = function (candidato) {
		console.log(candidato);

		var req = {
			method: 'POST',
			url: 'http://localhost:8080/rhecruta-java/rest/candidato',
			data: candidato
		}

		$http(req).then(function (resp) {

			//alerta de sucesso
			var Popup = $ionicPopup.show({
				title: 'Sucesso!',
				template: 'Cadastro feito com sucesso!',
				buttons: [
					{
						text: '<b>Okay</b>',
						type: 'button-positive',
						onTap: function (e) {

							//levando pra pagina de login
							$state.go('login');
						}
					}]
			});

		}, function (err) {
			console.log(err.data);
			
			//alerta de erro
			var alertPopup = $ionicPopup.alert({
				title: 'Erro!',
				template: 'Não foi possível efetuar o login!'
			});
		});
	}


})
   
.controller('agendarCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])

.controller('entrevistasCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {

}])

.controller('detalhesEntrevistaCtrl', function ($scope, $stateParams) {


})

.controller('detalhesVagaCtrl', function ($scope, $stateParams, $http, $ionicPopup,) {
	//dados dos alunos
	$scope.candidato = [];
	//pegando os dados no localStorage
	var dado = localStorage.getItem("candidato");
	$scope.candidato = angular.fromJson(dado);

	// var das enquetes
	$scope.vaga = [];

	//motando objeto
	var req = {
		method: 'GET',
		url: "http://localhost:8080/rhecruta-java/rest/vaga/" + $stateParams.id,
		headers: {
			'Authorization': 'Bearer ' + $scope.candidato
		}

	}

	$http(req).then(function (resp) {
		//salvando na variavel
		$scope.vaga = resp.data; 
		console.log(resp.data);

	}, function (err) {
		//alerta de erro
		var alertPopup = $ionicPopup.alert({
			title: 'Erro!',
			template: 'Não foi possível carregar a vaga!'
		});
	});

})