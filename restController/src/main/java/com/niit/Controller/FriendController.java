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
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDao;
import com.niit.model.Friend;



@RestController
public class FriendController {
	@Autowired
	FriendDao friendDAO;

	@PostMapping(value = "/createFriendRequest/{username}")
	public ResponseEntity<String> createFriendRequest(@PathVariable String username,@RequestBody Friend friend) {
		friend.setStatus("R");
		friend.setUsername(username);
		if (friendDAO.createFriend(friend)) {
			return new ResponseEntity<String>("Sucess", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value="/rejectFriendRequest/{friendId}")
	public ResponseEntity<String> rejectFriendRequest(@PathVariable("friendId") int friendId)
	{
		Friend friend=friendDAO.getFriend(friendId);
		if(friendDAO.rejectFriendRequest(friend))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}else
		{
			return new ResponseEntity<String>("Problem in reject the request",HttpStatus.METHOD_FAILURE);
		}
	}
	@GetMapping(value="/approvalFriendRequest/{friendId}")
	public ResponseEntity<String> approvalFriendRequest(@PathVariable("friendId") int friendId)
	{
		Friend friend=friendDAO.getFriend(friendId);
		if(friendDAO.approveFriendRequest(friend))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem in reject the request",HttpStatus.METHOD_FAILURE);
		}
	}
	
	@GetMapping(value="/getAllFriendRequest")
	public ResponseEntity<List<Friend>> getAllFriendRequest(HttpSession session)
	{	
	
		String currentUser=(String)session.getAttribute("currentUser");
		currentUser="username";
		System.out.println("current user="+currentUser);
		
		List<Friend> listFriendRequests=friendDAO.getAllFriendRequest(currentUser);
		if(!listFriendRequests.isEmpty())
		return new ResponseEntity<List<Friend>>(listFriendRequests,HttpStatus.OK);
		else return new ResponseEntity<List<Friend>>(listFriendRequests,HttpStatus.NO_CONTENT);
	}
	
	
}
