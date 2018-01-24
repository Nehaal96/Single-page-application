app.controller("adminBlogController", function($scope, $http, $location) {
	$scope.blog={blogId:0,blogNName:"",blogContent:"",creationDate:"",likes:0,username:"",status:"NA"};
	$scope.blogdata;
	function fetchAllBlog() {
		console.log("Fetching all blogs");
		$http.get("http://localhost:8090/restController/getAllBlogs")

		.then(function(response) {
			$scope.blogdata = response.data;
			console.log("Blog fetched");
		});
	}
	;
	fetchAllBlog();
	$scope.approveBlog=function(blogId) 
	{
		console.log("entered in approve blog");
		$http.get('http://localhost:8090/restController/approveBlog/'+ blogId)
				.then(fetchAllBlog(), function(response) 
		{
			console.log("Blog is approved");
		}
		)
	}
	$scope.rejectBlog=function(blogId)
	{
	$http.get('http://localhost:8090/restController/rejectBlog/'+blogId)
	.then(fetchAllBlog(),function(response){
		console.log('blog rejected');
	})
	}
});