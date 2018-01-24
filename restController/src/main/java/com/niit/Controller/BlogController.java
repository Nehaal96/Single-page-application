package com.niit.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDao;
import com.niit.model.Blogs;

@RestController
public class BlogController {

	@Autowired
	BlogDao blogDAO;

	@PostMapping(value = "/insertBlog")
	public ResponseEntity<String> insertBlog(@RequestBody Blogs blog,HttpSession session)

	{	
		blog.setStatus("NA");
		blog.setCreateDate(new java.util.Date());
		blog.setUsername((String)session.getAttribute("username"));
		if (blogDAO.addBlog(blog))
			return new ResponseEntity<String>("Blog Added", HttpStatus.OK);
		else
			return new ResponseEntity<String>("exception arised", HttpStatus.METHOD_FAILURE);
	}

	@RequestMapping(value = "/getAllBlogs", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Blogs> getAllBLogs() {
		return blogDAO.getAllBlogs();
	}

	@GetMapping("/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {
		Blogs tempblog = blogDAO.getBlog(blogId);
		System.out.println("deletion in blog");
		if (blogDAO.deleteBlog(tempblog)) 
		{			
			return new ResponseEntity<String>("Blog deleted", HttpStatus.OK);
		} 
		else
		{
			return new ResponseEntity<String>("problem deleting blog", HttpStatus.METHOD_FAILURE);
		}
	}

	@PostMapping(value = "/editBlog")
	public ResponseEntity<String> updateBlog(@RequestBody Blogs blog) {
		Blogs tempBlog = blogDAO.getBlog(blog.getBlogId());

		tempBlog.setBlogName(blog.getBlogName());
		tempBlog.setBlogContent(blog.getBlogContent());

		if (blogDAO.updateBlog(tempBlog)) {
			return new ResponseEntity<String>("Blog Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("problem ipdating blog", HttpStatus.METHOD_FAILURE);
		}

	}

	@GetMapping("/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId) {
		Blogs tempblog = blogDAO.getBlog(blogId);

		if (blogDAO.approveBlog(tempblog)) {
			return new ResponseEntity<String>("Blog updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("error in Blog updation", HttpStatus.METHOD_FAILURE);
		}
	}

	@GetMapping("/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId) {
		Blogs tempblog = blogDAO.getBlog(blogId);
		if (blogDAO.rejectBlog(tempblog)) {
			return new ResponseEntity<String>("Blog updated", HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("error in Blog updation", HttpStatus.METHOD_FAILURE);

		}
	}
	@GetMapping("/incLike/{blogId}")
	public ResponseEntity<String> incrementLike(@PathVariable("blogId") int blogId)
	{
		Blogs tempblog=blogDAO.getBlog(blogId);
		if(blogDAO.incrementLike(tempblog))
		{
			return new ResponseEntity<String>("like incremented",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("error in like increment",HttpStatus.METHOD_FAILURE);
		}
	}
}
