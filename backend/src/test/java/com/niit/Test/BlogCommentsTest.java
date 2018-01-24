package com.niit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogCommentsDao;
import com.niit.model.UserBlogComments;

public class BlogCommentsTest {
static BlogCommentsDao blogCommentsDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
	    context.refresh();
	    
	    blogCommentsDAO=(BlogCommentsDao) context.getBean("blogCommentsDAO");
	    
	}
	@Ignore
	@Test
	public void saveBlogCommentstest() {
		UserBlogComments blogComments=new UserBlogComments();
		blogComments.setBlogid(101);
		blogComments.setComments("good");
		blogComments.setUsername("hitesh");
		
		
		assertTrue("problem occured",blogCommentsDAO.saveBlogComments(blogComments));
	}
	@Ignore
	@Test
	public void deleteBlogComments(){
		UserBlogComments blogComments=(UserBlogComments)blogCommentsDAO.getBlogComments(1);
		assertTrue("",blogCommentsDAO.deleteBlogComments(blogComments));
	}@Ignore
	@Test
	public void updateCommentsBlog()
	{
		UserBlogComments blogComments=(UserBlogComments)blogCommentsDAO.getBlogComments(2);
		blogComments.setComments("nice");
		blogComments.setUsername("hitesh");
		assertTrue("forum is updated",blogCommentsDAO.updateBlogComments(blogComments));
	}
	@Ignore
	@Test
	public void getAllBlogComments(){
		List<UserBlogComments>blogCommentsList=(List<UserBlogComments>)blogCommentsDAO.getAllBlogComments();
		assertNotNull("",blogCommentsList.get(0));
		for(UserBlogComments blogComments:blogCommentsList)
		{
			System.out.println("blogcomment:::="+blogComments.getComments()); 
	}
	}
	@Ignore
	@Test
	public void getBlogComments(){
		UserBlogComments blogComments=(UserBlogComments)blogCommentsDAO.getBlogComments(2);
		assertNotNull("error",blogComments);
		System.out.println("jobs desc is "+blogComments.getBlogid());
		System.out.println("jobs profile"+blogComments.getComments());
	}
}


