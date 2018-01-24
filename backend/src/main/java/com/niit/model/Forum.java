package com.niit.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component

public class Forum {

		@Id
		@GeneratedValue
		int forumId;
		String forumName;
		String forumContent;
		int userId;
		Date createDate;
		String status;
		public int getForumId() {
			return forumId;
		}
		public void setForumId(int forumId) {
			this.forumId = forumId;
		}
		public String getForumName() {
			return forumName;
		}
		public void setForumName(String forumName) {
			this.forumName = forumName;
		}
		public String getForumContent() {
			return forumContent;
		}
		public void setForumContent(String forumContent) {
			this.forumContent = forumContent;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
}
