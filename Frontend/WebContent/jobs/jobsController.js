app.controller("jobsController", function($scope, $http, $location) {
	function fetchAllJobs() {
		console.log("fetched all jobs")
		$http.get("http://localhost:8090/restController/getAllJobs")

		.then(function(response) {
			$scope.jobsdata = response.data;
			console.log("all jobs fetched")
		});
	}
	;
	fetchAllJobs();
	$scope.insertJobs = function() {
		console.log('entered insertJobs');
		$http.post('http://localhost:8090/restController/insertJobs',
				$scope.jobs).then(fetchAllJobs(), function(response) {
			console.log("successful jobs entered");
			$location.path("/jobs")
		});
	}
});
fetchAllJobs();
$scope.approveJob=function(jobId) 
{
	console.log("entered in approve job");
	$http.get('http://localhost:8090/restController/approveJobs/'+ jobId)
			.then(fetchAllJobs(), function(response) 
	{
		console.log("Job is approved");
	}
	)
}
$scope.rejectJob=function(jobId)
{
$http.get('http://localhost:8090/restController/rejectJobs/'+jobId)
.then(fetchAllJobs(),function(response){
	console.log('Job rejected');
})
}