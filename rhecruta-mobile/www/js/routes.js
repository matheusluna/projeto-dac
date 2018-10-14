angular.module('app.routes', [])

  .config(function ($stateProvider, $urlRouterProvider) {

    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js
    $stateProvider


      .state('menu.vagas', {
        url: '/vagas:idVaga',
        views: {
          'side-menu21': {
            templateUrl: 'templates/vagas.html',
            controller: 'vagasCtrl'
          }
        }
      })

      .state('menu.listaDeInteresses', {
        url: '/lista',
        views: {
          'side-menu21': {
            templateUrl: 'templates/listaDeInteresses.html',
            controller: 'listaDeInteressesCtrl'
          }
        }
      })

      .state('menu.candidaturas', {
        url: '/candidatura',
        views: {
          'side-menu21': {
            templateUrl: 'templates/candidaturas.html',
            controller: 'candidaturasCtrl'
          }
        }
      })

      .state('menu.entrevistas', {
        url: '/entrevistas',
        views: {
          'side-menu21': {
            templateUrl: 'templates/entrevistas.html',
            controller: 'entrevistasCtrl'
          }
        }
      })

      .state('menu', {
        url: '/side-menu21',
        templateUrl: 'templates/menu.html',
        controller: 'menuCtrl'
      })

      .state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'loginCtrl'
      })

      .state('cadastro', {
        url: '/cadastro',
        templateUrl: 'templates/cadastro.html',
        controller: 'cadastroCtrl'
      })

      .state('menu.agendar', {
        url: '/agendar',
        views: {
          'side-menu21': {
            templateUrl: 'templates/agendar.html',
            controller: 'agendarCtrl'
          }
        }
      })

      .state('menu.detalhesEntrevista', {
        url: '/detalhesEntrevista',
        views: {
          'side-menu21': {
            templateUrl: 'templates/detalhesEntrevista.html',
            controller: 'detalhesEntrevistaCtrl'
          }
        }
      })

      .state('menu.detalhesVaga', {
        url: '/detalhesVaga/:id',
        views: {
          'side-menu21': {
            templateUrl: 'templates/detalhesVaga.html',
            controller: 'detalhesVagaCtrl'
          }
        }
      })

    $urlRouterProvider.otherwise('/login')

  });