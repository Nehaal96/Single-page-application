package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobsDao;
import com.niit.model.Blogs;
import com.niit.model.Jobs;



@RestController
public class JobController {

	@Autowired
	JobsDao jobsDAO;
	
	@PostMapping(value="/insertJobs")
	public ResponseEntity<String> insertJobs(@RequestBody Jobs jobs)
	{
		jobs.setPostDate(new java.util.Date());
		if(jobsDAO.saveJobs(jobs))
			return new ResponseEntity<String>("JObs added",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Exception arised",HttpStatus.METHOD_FAILURE);
	}
	@RequestMapping(value="/getAllJobs",method=RequestMethod.GET,headers="Accept=application/json")
	public List<Jobs> getAllJobs(){
		return jobsDAO.getAllJobs();
	}
	@DeleteMapping("/deleteJobs/{jobId}")
	public ResponseEntity<Jobs> deleteJobs(@PathVariable int jobId)
	{
		Jobs jobs=jobsDAO.getJobs(jobId);
		jobsDAO.deleteJobs(jobs);
		return new ResponseEntity<Jobs>(HttpStatus.OK);
	}
	@PostMapping(value="/updateJob")
	public ResponseEntity<String> updateJobs(@RequestBody Jobs jobs)
	{
		Jobs tempJobs=jobsDAO.getJobs(jobs.getJobId());
		
		tempJobs.setJobDesc(jobs.getJobDesc());
		tempJobs.setJobProfile(jobs.getJobProfile());
		
		if(jobsDAO.updateJobs(tempJobs))
		{
			return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("problem ipdating blog",HttpStatus.METHOD_FAILURE);
		}
		
	}
	@GetMapping("/approvejobs/{jobID}")
	public ResponseEntity<String> approveBlog(@PathVariable("jobId") int jobId) {
		Jobs tempjob = jobsDAO.getJobs(jobId);

		if (jobsDAO.approveJob(tempjob)) {
			return new ResponseEntity<String>("Job updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("error in Job updation", HttpStatus.METHOD_FAILURE);
		}
	}

	@GetMapping("/rejectjobs/{jobId}")
	public ResponseEntity<String> rejectJob(@PathVariable("jobId") int jobId) {
		Jobs tempjob = jobsDAO.getJobs(jobId);
		if (jobsDAO.rejectJob(tempjob)) {
			return new ResponseEntity<String>("Blog updated", HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("error in Blog updation", HttpStatus.METHOD_FAILURE);

		}
	}

	
}
