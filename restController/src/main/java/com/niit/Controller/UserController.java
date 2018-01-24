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

import com.niit.dao.UserDao;
import com.niit.model.UserDetail;



@RestController
public class UserController {

	@Autowired
	private UserDao userDAO;

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<UserDetail> getAllUsers() {
		return userDAO.getAllUsers(); 
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail user) {
		user.setIsOnline("N");
		user.setRole("USER");
		boolean isSaved = userDAO.saveUser(user);
		if (isSaved)
			return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
		else
			return new ResponseEntity<UserDetail>(user, HttpStatus.METHOD_FAILURE);
	}

	@PostMapping("/login")
	public ResponseEntity <UserDetail> checkLogin(@RequestBody UserDetail user, HttpSession session) {		
		if (userDAO.checkLogin(user)) {
			System.out.println("logging"); 
			UserDetail tempUser = userDAO.getUser(user.getUsername());
			userDAO.updateOnlineStatus("Y", tempUser);
			session.setAttribute("username:", user.getUsername());
			System.out.println("User Controller::"+user.getUsername());			
			return new ResponseEntity<UserDetail>(tempUser, HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<UserDetail>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value = "/logout/{username}")
	public ResponseEntity<String> loggingout(@PathVariable("username") String username) {
		UserDetail user = userDAO.getUser(username);
		if (userDAO.updateOnlineStatus("N", user)) {
			return new ResponseEntity<String>("Successful logout", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("error in logout", HttpStatus.METHOD_FAILURE);
		}
	}

}
