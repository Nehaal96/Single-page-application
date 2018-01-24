package com.niit.dao;

import java.util.List;

import com.niit.model.Blogs;

public interface BlogDao {
	
	public boolean addBlog(Blogs blog);
	public boolean updateBlog(Blogs blog);
	public boolean deleteBlog(Blogs blog);
	public Blogs getBlog(int blogId);
	public List<Blogs> getAllBlogs();
	public boolean approveBlog(Blogs blog);
	public boolean rejectBlog(Blogs blog);
	public boolean incrementLike(Blogs blog);
}
