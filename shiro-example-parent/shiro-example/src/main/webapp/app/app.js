var shiroApp = angular.module('shiroApp', [ 'ui.router', 'ngCookies' ])

shiroApp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/login');
	$stateProvider.state('home', {
		abstract : true,
		controller : 'HomeController as homeCtrl',
		templateUrl : 'app/home/home.html',
		authenticate : false
	}).state('home.homepage', {
		parent : 'home',
		url : '/home',
		views : {
			'data' : {
				templateUrl : 'app/home/homepage/homepage.html',
			}
		},
		authenticate : true
	}).state('home.about', {
		parent : 'home',
		url : '/about',
		views : {
			'data' : {
				templateUrl : 'app/home/about/about.html',
			}
		},
		authenticate : true
	}).state('home.contact', {
		parent : 'home',
		url : '/contact',
		views : {
			'data' : {
				templateUrl : 'app/home/contact/contact.html',
			}
		},
		authenticate : true
	}).state('login', {
		url : '/login',
		controller : 'LoginController as loginCtrl',
		views : {
			'' : {
				templateUrl : 'app/login/partials/login.html'
			}
		},
		authenticate : false
	});
});

shiroApp.run(function($rootScope, LoginService, $state, $log) {
	$rootScope.$on("$stateChangeStart", function(event, toState, toParams,
			fromState, fromParams) {
		if (toState.authenticate && !LoginService.isAuthenticated()) {
			// User isn’t authenticated
			$state.transitionTo("login");
			event.preventDefault();
		}
	});
});