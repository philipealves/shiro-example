var shiroApp = angular.module('shiroApp', [ 'ui.router', 
		'ngCookies' ])

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
				templateUrl : 'app/home/homepage/partials/homepage.html',
			}
		}
	}).state('home.about', {
		parent : 'home',
		url : '/about',
		views : {
			'data' : {
				templateUrl : 'app/home/about/partials/about.html',
			}
		}
	}).state('home.contact', {
		parent : 'home',
		url : '/contact',
		controller: "ContactController as contactCtrl",
		views : {
			'data' : {
				templateUrl : 'app/home/contact/partials/contact.html',
			}
		}
	}).state('login', {
		url : '/login',
		controller : 'LoginController as loginCtrl',
		views : {
			'' : {
				templateUrl : 'app/login/partials/login.html'
			}
		}
	});
});

shiroApp.run(function($rootScope, LoginService, $state, $log) {
	$rootScope.$on('$stateChangeStart', function interceptor(event, toState,
			toParams, fromState, fromParams) {
		if (toState.authenticate && !LoginService.isAuthenticated()) {
			event.preventDefault();
			$state.go('login');
		}
	});
});