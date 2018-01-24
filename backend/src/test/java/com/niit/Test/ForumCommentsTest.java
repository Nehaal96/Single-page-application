package com.niit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ForumCommentsDao;
import com.niit.model.UserForumComments;

public class ForumCommentsTest {	
	
	static ForumCommentsDao forumCommentsDAO;

@BeforeClass
public static void initialize()
{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
    context.refresh();
    
    forumCommentsDAO=(ForumCommentsDao) context.getBean("forumCommentsDAO");
    
}
	@Ignore
	@Test
	public void saveForumCommentstest() {
		UserForumComments forumComments = new UserForumComments();
		forumComments.setComments("liked");
		forumComments.setForumid(102);
		forumComments.setUsername("comments");
		assertTrue("", forumCommentsDAO.saveForumComments(forumComments));
	}

	@Ignore
	@Test
	public void deleteForumComments() {
		UserForumComments forumComments = (UserForumComments) forumCommentsDAO.getForumComments(1);
		assertTrue("", forumCommentsDAO.deleteForumComments(forumComments));

	}

	@Ignore
	@Test
	public void updatesForumComment() {
		UserForumComments forumComments = (UserForumComments) forumCommentsDAO.getForumComments(2);
		forumComments.setComments("nice");
	
		assertTrue("forum is updated", forumCommentsDAO.updateForumComments(forumComments));
	}

	@Ignore
	@Test
	public void getAllForumComments() {
		List<UserForumComments> forumCommentsList = (List<UserForumComments>) forumCommentsDAO.getAllForumComments();
		assertNotNull("", forumCommentsList.get(0));
		for (UserForumComments forumComments : forumCommentsList) {
			System.out.println("forumcomment:::=" + forumComments.getComments());
		}
	}

	@Test
	public void getForumComments() {
		UserForumComments forumComments = (UserForumComments) forumCommentsDAO.getForumComments(2);
		assertNotNull("error", forumComments);
		System.out.println("forum usernameis:: " + forumComments.getUsername());
		System.out.println("forumcomment::" + forumComments.getComments());
	}
}
