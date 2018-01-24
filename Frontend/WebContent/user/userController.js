app.controller("userController", function($scope, $http, $location,$rootScope,$cookieStore) 
	{
	$scope.user={username:'',password:'',role:'',firstname:'',lastname:'',email:'',phone:'',isOnline:''};
	$scope.allusers;
	$scope.register=function()
	{
		console.log("enter register");
		$http.post("http://localhost:8090/restController/register",$scope.user)
		.then(function(response)
				{
					console.log("successfully registered");
					$location.path("/login");
				});
	}
	$rootScope.loginuser=function()
	{
		console.log("login function");
		
		$http.post("http://localhost:8090/restController/login",$scope.user)
		.then(function(response){
			$scope.user=response.data;
			$rootScope.currentUser=response.data;
			$cookieStore.put('user',response.data);
			console.log($rootScope.currentUser.role);
			$location.path("/UserHome")
		});
	}	
	function fetchAllUsers()
	{
		console.log("fetching all users");
		$http.get("http://localhost:8090/restController/getAllUsers")
		.then(function(response)
				{
					$scope.allusers=response.data;
				});
				};
				fetchAllUsers();
			
	$rootScope.logout=function()
	{
		console.log('logout function');
		delete $rootScope.currentUser;
		$cookieStore.remove('user');
		$location.path("/UserHome")
	}
	});








