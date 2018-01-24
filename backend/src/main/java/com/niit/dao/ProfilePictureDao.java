package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao 
{
	public boolean save(ProfilePicture profilePicture);
	public ProfilePicture getProfilePicture(String username);
}
