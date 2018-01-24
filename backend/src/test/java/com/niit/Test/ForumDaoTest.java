package com.niit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ForumDao;
import com.niit.model.Forum;

public class ForumDaoTest {
static ForumDao forumDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
	    context.refresh();
	    
	    forumDAO=(ForumDao) context.getBean("forumDAO");
	    
	}
	@Ignore
	@Test
	public void addForum() {
		Forum forum=new Forum();
		forum.setForumContent("forum");
		forum.setForumId(123);
		forum.setForumName("forum");
		forum.setStatus("offline");
		forum.setUserId(123);
		assertTrue("problem in adding CartItem",forumDAO.addForum(forum));
	}
	@Ignore
	@Test
	public void deleteForumTest()
	{
		Forum forum=(Forum)forumDAO.getForum(33);
		assertTrue("forum deleted",forumDAO.deleteForum(forum));
	}
	@Ignore
	@Test
	public void updateForumTest()
	{
		Forum forum=(Forum)forumDAO.getForum(2);
		forum.setForumContent("vanilla forum");
		forum.setForumName("vanilla name");
		assertTrue("forum is updated",forumDAO.updateForum(forum));
	}
	@Test
	public void getAllForumsTest()
	{
		List<Forum>forumList=(List<Forum>)forumDAO.getAllForums();
		assertNotNull("",forumList.get(0));
		for(Forum forum:forumList)
		{
			System.out.println("forum name="+forum.getForumName()+":::"+"forum Contente::"+forum.getForumContent());
					
		}
	}

}
