var bookModule = 
	angular.module('paretoModule', ['ngRoute'])
	.config(['$routeProvider','$locationProvider', function($routeProvider, $locationProvider) {
		$locationProvider.html5Mode(true).hashPrefix('!');
		$routeProvider
			.when('/', {
				templateUrl: 'views/main.html',
				controller: 'MainCtrl',
				controllerAs: 'ctrl'
			})
			.otherwise({
				redirectTo: '/'
			});
		
	}])
	.run(['$rootScope', function($rootScope){
		$rootScope.listB = [];
    }])
	.controller("ParetoCtrl", ['$scope','$location', function($scope, $location) {
    	$scope.go = function ( path ) {
            $location.path( path );
        };
    }]);
	
