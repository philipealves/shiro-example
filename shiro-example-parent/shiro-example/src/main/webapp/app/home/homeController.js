shiroApp.controller('HomeController', function($state, $cookies, LoginService) {

	var ctrl = this;

	ctrl.logout = function() {
		LoginService.logout().then(function(response) {
			console.log(response);
			if (response.status == "200") {
				$cookies.remove("username");
				$state.go('login');
			}
		});
	}
});
