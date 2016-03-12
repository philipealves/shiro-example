shiroApp.factory('LoginService', function($http, $cookies) {
	var service = {};
	var baseUrl = 'http://localhost:8080/shiro-rest/rest/authentication';
	var sessionId;
	
	service.login = function(user) {
		return $http.post(baseUrl, user);
	}

	service.isAuthenticated = function() {
		var sessionId = $cookies.get('JSESSIONID');
		return sessionId;
	}

	service.getCurrentUser = function() {
		return currentUser;
	}

	service.logout = function() {
		return $http.post(baseUrl + "/logout");
	}

	return service;

});