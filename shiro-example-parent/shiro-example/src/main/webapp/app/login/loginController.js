shiroApp.controller('LoginController', function($state, $cookies, LoginService) {

	var ctrl = this;

	ctrl.login = function(user) {
		LoginService.login(user).then(function(response) {
			console.log(response);
			if (response.status == "200") {
				$cookies.put('username', response.data.username);
				$state.go('home.homepage')
			} else {
				$state.go('login')
			}
		}).then(function(response) {
			ctrl.user = {}
		});
	}

});
