var mainController = angular.module('paretoModule');


mainController.controller('MainCtrl', ['$scope', '$location','$http', function($scope, $location, $http) {
	$scope.isTableVisible = false;
	$scope.isChartVisible = false;
	$scope.criteriaNumber = 0;  // columns
	$scope.solutionsNumber = 0; // rows
	$scope.controleModel = [];
	$scope.criterias = [];
	$scope.responseTable = [];
	//$scope.mapPareto = [[1,2,3],[4,5,6]];

	$scope.showTable = function(){
		setModel();
		setCriterias();
		$scope.isTableVisible = true;
	};
	$scope.showChart = function(){
		$scope.isChartVisible = true;
		var selectedColums = [];
		angular.forEach($scope.criterias, function(model) {
			if(model.selected){
				selectedColums.push(model.key);
			}
		});
		var modelChart = [];
		console.log('$scope.controleModel ',$scope.controleModel);
		console.log('selectedColums',selectedColums);
		angular.forEach($scope.controleModel, function(model) {
			var obj = {};
			obj.x = model[selectedColums[0]];
			obj.y = model[selectedColums[1]];
			obj.r = 4;
			modelChart.push(obj);
		});
		
		console.log('modelChart ', modelChart);
		var ctx = "myChart";
		var myChart = new Chart(ctx, {
			 type: 'bubble',
			    data: {
			        datasets: [{
			            label: 'Scatter Dataset',
			            data: modelChart,
			        }]
			    },
			    options: {
			        scales: {
			            xAxes: [{
			                type: 'linear',
			                position: 'bottom'
			            }]
			        }
			    }
		});
	}
	
	$scope.calculate = function () {
		var sendValue = transformRequest();
		
		$http.post('ParetoServlet', 
				{paretoSet: sendValue}
		).success(function(response) {
			console.log(response);
			showResponse(response);
		});
	}
	showResponse();
	function showResponse(response){
		$scope.responseTable = response;
		
		
	}
	
	function transformRequest() {
		var sendValue = [];
		angular.forEach($scope.controleModel, function(model) {
			var temp = [];
			for (variable in model) {
				if (variable != 'id') {
					temp.push(model[variable]);
				}
			}
			sendValue.push(temp);
		});
		return sendValue;
	}
	
	function setModel() {
		$scope.controleModel = [];
		for (var i=0; i<$scope.solutionsNumber; i++) {
			var inputObj = {
					id: i
			};
			for (var j=0; j<$scope.criteriaNumber; j++) {
				var key = 'cl'+j;
				inputObj[key] = 0;
			}
			$scope.controleModel.push(inputObj);
		}
	}
	
	function setCriterias() {
		$scope.criterias = [];
		for (var j=0; j<$scope.criteriaNumber; j++) {
			var key = 'cl'+j;
			var criteriaObject = {
					['key']: key,
					selected: false
			}
			$scope.criterias.push(criteriaObject);
		}
	}
	
}])