angular.module('app.controllers', [])
  
.controller('vagasCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])
   
.controller('listaDeInteressesCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])
   
.controller('candidaturasCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])
   
.controller('menuCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])
   
.controller('loginCtrl', function ($scope, $http, $stateParams, $ionicPopup, $state) {

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

			console.log(resp.data);
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

.controller('detalhesEntrevistaCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])

.controller('detalhesVagaCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])