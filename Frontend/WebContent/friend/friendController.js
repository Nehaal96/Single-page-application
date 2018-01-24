app.controller("friendController", function($scope, $http, $location,$rootScope) 
	{
		$scope.friend={friendId:'',username:'',friendname:'',status:''};
		$scope.allFriendRequest;
	
		function fetchAllFriendRequests() 
		{
		console.log("fetched all friendRequests");
		$http.get("http://localhost:8090/restController/getAllFriendRequest")
		.then(function(response) 
			{
			$scope.allFriendRequest = response.data;
			console.log($scope.currentUser.username);
			console.log($scope.allFriendRequest);
			});
		}
		fetchAllFriendRequests();
		
		$scope.approve=function(friendId)
		{
			console.log("approving friend request");
			$http.get('http://localhost:8090/restController/approvalFriendRequest/'+friendId)
			.success(function(response)
			{
				console.log("successfully approved");
				location.path("/showFriendRequest");
			});
			
		}
		$scope.reject=function(friendId)
		{
			console.log("rejecting the friend request");
			$http.get('http://localhost:8090/restController/rejectFriendRequest/'+friendId)
			.success(function(response){
				console.log("successfully rejected");
				location.path("/showFriendRequest");
			});
		};
		$scope.addFriend=function()
		{
			console.log("sending friend request");
			$http.post('http://localhost:8090/restController/createFriendRequest',$scope.friend)
			.then(function(response){
				console.log("request send");
			});
			};
	});