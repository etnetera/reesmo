package com.etnetera.tremapp.user;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.etnetera.tremapp.http.exception.UnauthorizedException;
import com.etnetera.tremapp.model.mongodb.user.Permission;
import com.etnetera.tremapp.model.mongodb.user.User;
import com.etnetera.tremapp.model.mongodb.view.View;

public class UserHelper {

	public static List<String> getAllowedProjectIds(Permission permission) {
		return requireAppUser().getUser().getAllowedProjectsIds(permission);
	}
	
	public static void checkProjectPermission(String projectId, Permission permission) {
		requireAppUser().checkProjectPermission(projectId, permission);
	}
	
	public static void checkViewPermission(View view, Permission permission) {
		view.checkUserPermission(requireAppUser().getUser(), permission);
	}
	
	public static boolean isAllowedForProject(String projectId, Permission permission) {
		return requireAppUser().isAllowedForProject(projectId, permission);
	}
	
	public static String getUserId() {
		AppUser appUser = getAppUser();
		if (appUser != null) {
			return appUser.getId();
		}
		return null;
	}
	
	public static User requireUser() {
		return requireAppUser().getUser();
	}
	
	public static User getUser() {
		AppUser appUser = getAppUser();
		if (appUser != null) {
			return appUser.getUser();
		}
		return null;
	}
	
	public static AppUser requireAppUser() {
		AppUser appUser = getAppUser();
		if (appUser == null) {
			throw new UnauthorizedException("App user is empty");
		}
		return appUser;
	}
	
	public static AppUser getAppUser() {
		Authentication auth = getAuthentication();
		if (auth != null) {
			return (AppUser) auth.getPrincipal();
		}
		return null;
	}
	
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
