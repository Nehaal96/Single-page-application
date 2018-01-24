app.controller("adminForumController", function($scope, $http, $location) {
	function fetchAllForum() {
		console.log("Fetching all forum");
		$http.get("http://localhost:8090/restController/getAllForums")

		.then(function(response) {
			$scope.forumdata = response.data;
			console.log("forum fetched");
		});
	}
	;
	fetchAllForum();
	$scope.approveForum=function(forumId) 
	{
		console.log("entered in approve forum");
		$http.get('http://localhost:8090/restController/approveForum/'+ forumId)
				.then(fetchAllForum(), function(response) 
		{
			console.log("forum is approved");
			location.path("/adminForum.html")
		}
		)
	}
	$scope.rejectForum=function(forumId)
	{
	$http.get('http://localhost:8090/restController/rejectForum/'+forumId)
	.then(fetchAllForum(),function(response){
		console.log('forum rejected');
	})
	}
});