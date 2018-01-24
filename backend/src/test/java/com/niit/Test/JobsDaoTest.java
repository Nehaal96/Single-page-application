package com.niit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.JobsDao;
import com.niit.model.Jobs;

public class JobsDaoTest {
	static JobsDao jobsDAO;

	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
	    context.refresh();
	    
	    jobsDAO=(JobsDao) context.getBean("jobsDAO");
	    
	}
	@Ignore
	@Test
	public void saveJobs() {
		Jobs jobs = new Jobs();
		jobs.setJobDesc("jobs");
		jobs.setJobId(123);
		jobs.setJobProfile("jobs");
		jobs.setQualification("jobs");
		jobs.setStatus("A");
		assertTrue("problem in adding CartItem", jobsDAO.saveJobs(jobs));

	}

	@Ignore
	@Test
	public void deleteJobs() {
		Jobs jobs = (Jobs) jobsDAO.getJobs(1);
		assertTrue("problem in deleting", jobsDAO.deleteJobs(jobs));
	}

	@Ignore
	@Test
	public void updateJobs() {
		Jobs jobs = (Jobs) jobsDAO.getJobs(2);
		jobs.setJobDesc("done");
		jobs.setJobProfile("hired");
		assertTrue("problem in updating", jobsDAO.updateJobs(jobs));
	}

	@Ignore
	@Test
	public void getAllJobs() {
		List<Jobs> jobsList = (List<Jobs>) jobsDAO.getAllJobs();
		assertNotNull("blog is null", jobsList.get(0));
		for (Jobs jobs : jobsList) {
			System.out.println("jobs is" + jobs.getJobDesc() + ":::" + "job name::" + jobs.getJobProfile() + "");
		}
	}
	@Test
	public void getJobs()
	{
		Jobs jobs=(Jobs)jobsDAO.getJobs(2);
		assertNotNull("error",jobs);
		System.out.println("jobs desc is "+jobs.getJobDesc());
		System.out.println("jobs profile"+jobs.getJobProfile());
	
	}
}
