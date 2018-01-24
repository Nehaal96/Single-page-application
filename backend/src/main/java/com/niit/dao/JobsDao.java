package com.niit.dao;

import java.util.List;


import com.niit.model.Jobs;

public interface JobsDao {
	public boolean saveJobs(Jobs jobs);

	public boolean deleteJobs(Jobs jobs);

	public boolean updateJobs(Jobs jobs);

	public Jobs getJobs(int jobId);

	public List<Jobs> getAllJobs();
	
	public boolean approveJob(Jobs jobs);
	
	public boolean rejectJob(Jobs jobs);
}
