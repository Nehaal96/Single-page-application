package com.niit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDao;
import com.niit.model.Blogs;



public class BlogDaoTest {
static BlogDao blogDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
	    context.refresh();
	    
	    blogDAO=(BlogDao) context.getBean("blogDAO");
	    
	}
	@Ignore
	@Test
	public void addBlog() {
		Blogs blog = new Blogs();
		blog.setBlogContent("blog12");
		blog.setBlogId(127);
		blog.setBlogName("blog11");
		blog.setLikes(10);
		blog.setStatus("on");
		
		assertTrue("problem in adding CartItem",blogDAO.addBlog(blog));

	}
	@Ignore
	@Test
	public void deleteBlogTest()
	{
		Blogs blog=(Blogs)blogDAO.getBlog(1);
		assertTrue("blog deleted",blogDAO.deleteBlog(blog));
	}
	@Ignore
	@Test
	public void updateBlogTest()
	{
		Blogs blog=(Blogs)blogDAO.getBlog(4);
		blog.setBlogContent("java");
		blog.setBlogName("vanilla java");
		assertTrue("blog updated",blogDAO.updateBlog(blog));
	}
	@Ignore
	@Test
	public void getBlogTest()
	{
		Blogs blog=blogDAO.getBlog(1);
		assertNotNull("",blog);
		System.out.println("blog name "+blog.getBlogName());
		System.out.println("blog content"+blog.getBlogContent());
	}
	@Ignore
	@Test
	public void getAllBlogsTest()
	{
		List<Blogs>blogList=(List<Blogs>)blogDAO.getAllBlogs();
		assertNotNull("blog is null",blogList.get(0));
		for(Blogs blog:blogList)
		{
			System.out.println("blog is"+blog.getBlogId()+":::"+"blog name::"+blog.getBlogName()+"");
		}
	}

}
