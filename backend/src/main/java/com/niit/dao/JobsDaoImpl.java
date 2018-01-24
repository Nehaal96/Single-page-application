package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Jobs;

@Repository("jobsDAO")
public class JobsDaoImpl implements JobsDao{

	@Autowired
	SessionFactory sessionFactory;
	public JobsDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public boolean saveJobs(Jobs jobs) {
		try{
			sessionFactory.getCurrentSession().save(jobs);
			return true;
		}catch(Exception e)
		{
			System.out.println("exception arised"+e);
		}
		return false;
		
	}
	@Override
	@Transactional
	public boolean deleteJobs(Jobs jobs) {
		try{
			sessionFactory.getCurrentSession().delete(jobs);
			return true;
		}catch(Exception e)
		{
			System.out.println("exception arised"+e);
		}
		return false;
		
		
	}
	@Override
	@Transactional
	public boolean updateJobs(Jobs jobs) {
		try{
			sessionFactory.getCurrentSession().update(jobs);
			return true;
		}
		catch(Exception e){
			System.out.println("exception arised"+e);
		}
		return false;
		
	}
	@Override
	@Transactional
	public Jobs getJobs(int jobId) {
		Session session = sessionFactory.openSession();
		Jobs jobs = (Jobs) session.get(Jobs.class, new Integer(jobId));
		return jobs;
		
	}
	@Override
	@Transactional
	public List<Jobs> getAllJobs() {
		return sessionFactory.getCurrentSession().createQuery("from Jobs").list();
	}
	@Override
	public boolean approveJob(Jobs jobs) {
		try {
			jobs.setStatus("A");
			sessionFactory.getCurrentSession().update(jobs);
			return true;
		} catch (Exception e) {
			System.out.println("exception occured" + e);
			return false;
		}
	}
	@Override
	public boolean rejectJob(Jobs jobs) {
		try {
			jobs.setStatus("NA");
			sessionFactory.getCurrentSession().update(jobs);
			return true;
		} catch (Exception e) {
			System.out.println("exception occured" + e);
			return false;
		}
	}

}
