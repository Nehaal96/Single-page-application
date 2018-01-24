package com.niit.Config;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.BlogCommentsDao;
import com.niit.dao.BlogCommentsDaoImpl;
import com.niit.dao.BlogDao;
import com.niit.dao.BlogDaoImpl;
import com.niit.dao.ForumCommentsDao;
import com.niit.dao.ForumCommentsDaoImpl;
import com.niit.dao.ForumDao;
import com.niit.dao.ForumDaoImpl;
import com.niit.dao.FriendDao;
import com.niit.dao.FriendDaoImpl;
import com.niit.dao.JobsDao;
import com.niit.dao.JobsDaoImpl;
import com.niit.dao.ProfilePictureDao;
import com.niit.dao.ProfilePictureDaoImpl;
import com.niit.dao.UserDao;
import com.niit.dao.UserDaoImpl;
import com.niit.model.Blogs;
import com.niit.model.Forum;
import com.niit.model.Friend;
import com.niit.model.Jobs;
import com.niit.model.ProfilePicture;
import com.niit.model.UserBlogComments;
import com.niit.model.UserDetail;
import com.niit.model.UserForumComments;



@Configuration
@EnableTransactionManagement
@ComponentScan("com.niit")
public class DBConfig {


     
	public DataSource getDataSource()
	{
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:tcp://localhost/~/nehaal");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
		
	}
	
	public Properties getHibernateProperties()
	{
		Properties prop = new Properties();
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.hbm2ddl.auto", "update");
		return prop;
		
	}
	@Bean
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(getDataSource());
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		localSessionFactoryBuilder.addAnnotatedClass(Blogs.class);
		localSessionFactoryBuilder.addAnnotatedClass(UserForumComments.class);
		localSessionFactoryBuilder.addAnnotatedClass(Forum.class);
		localSessionFactoryBuilder.addAnnotatedClass(UserBlogComments.class);
		localSessionFactoryBuilder.addAnnotatedClass(Friend.class);
		localSessionFactoryBuilder.addAnnotatedClass(Jobs.class);
		localSessionFactoryBuilder.addAnnotatedClass(UserDetail.class);
		localSessionFactoryBuilder.addAnnotatedClass(UserDetail.class);
		localSessionFactoryBuilder.addAnnotatedClass(ProfilePicture.class);


		System.out.println("SessionFactory Bean Created");
		return localSessionFactoryBuilder.buildSessionFactory();
	}

	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}


