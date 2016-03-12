shiroApp.controller('LoginController', function($state, LoginService) {

	var ctrl = this;

	ctrl.login = function(user) {
		LoginService.login(user).then(function(response) {
			console.log(response);
			if (response.status == "200") {
				$state.go('home.homepage')
			} else {
				$state.go('login')
			}
		}).then(function(response) {
			ctrl.user = {}
		});
	}

});
