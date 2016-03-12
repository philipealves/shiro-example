shiroApp.controller('HomeController', function($state, LoginService) {

	var ctrl = this;

	ctrl.logout = function() {
		LoginService.logout().then(function(response) {
			console.log(response);
			if (response.status == "200") {
				$state.go('login');
			}
		});
	}
});
