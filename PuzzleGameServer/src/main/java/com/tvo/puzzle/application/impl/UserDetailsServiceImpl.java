package com.tvo.puzzle.application.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.UserDao;
import com.tvo.puzzle.entity.Role;
import com.tvo.puzzle.entity.User;

/*
 * Spring-security requires an implementation of UserDetailService. 
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		try {

			User u = userDao.searchByUsername(username);
			if (u != null) {
				boolean enabled = true;
				boolean accountNonExpired = true;
				boolean credentialsNonExpired = true;
				boolean accountNonLocked = true;

				Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
				List<Role> roles = u.getLsRole();
				
				for(Role r : roles){
					// roleName = "Super Administrator" or "Administrator" 
				    grantedAuthorities.add(new GrantedAuthorityImpl(r.getRoleName()));
				}
				
				org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
						u.getUserName(), u.getPassword(), enabled,
						accountNonExpired, credentialsNonExpired,
						accountNonLocked, grantedAuthorities);

				return user;
			}

		} catch (NoResultException e) {
			throw new UsernameNotFoundException("No such user");
		}
		return null;

	}

}
