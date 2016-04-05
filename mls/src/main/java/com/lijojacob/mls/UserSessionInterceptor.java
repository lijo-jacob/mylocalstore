package com.lijojacob.mls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;
import com.lijojacob.mls.siteconfiguration.repository.SiteConfigurationRepository;
import com.lijojacob.mls.userprofile.entity.UserSession;
import com.lijojacob.mls.userprofile.entity.UserSessionInterface;
import com.lijojacob.mls.userprofile.repository.UserProfileRepository;
import com.lijojacob.mls.userprofile.repository.UserSessionRepository;

@Service
public class UserSessionInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionInterceptor.class);
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private UserSessionRepository userSessionRepository;

	@Autowired
	private SiteConfigurationRepository siteRepository;
	
	@Autowired
	private UserSessionInterface userSessionDTO;
	
	private Map<String, SiteConfiguration> siteMap;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.debug("Inside UserSessionInterceptor.preHandle()");
		
		initializeSiteMap();
		List<String> userSessionIds = this.getCookieValues("userSessionId", request);
		Iterable<UserSession> userSessions = userSessionRepository.findAll(userSessionIds);
		
		SiteConfiguration requestedSite = this.getSite(request);
		UserSession currentUserSession = null;
		
		for(UserSession userSession : userSessions) {
			if(null != userSession.getSite() && null != requestedSite) {
				if(userSession.getSite().getId().equals(requestedSite.getId())) {
					currentUserSession = userSession;
				}
			}
		}
		
		if(null == currentUserSession) {
			currentUserSession = new UserSession();
			currentUserSession.setSite(requestedSite);
			currentUserSession = userSessionRepository.save(currentUserSession);
			Cookie userSessionIdCookie = new Cookie("userSessionId", currentUserSession.getId());
			response.addCookie(userSessionIdCookie);
		}
		
		userSessionDTO.setUserSession(currentUserSession);
		return true;
	}
	
	private List<String> getCookieValues(String name, HttpServletRequest request) {
		List<String> cookieList = new ArrayList<String>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals(name)) {
		     cookieList.add(cookie.getValue());
		    }
		  }
		}
		return cookieList;
	}
	
	private SiteConfiguration getSite(HttpServletRequest request) {
		String domain = request.getServerName();
		SiteConfiguration site = siteMap.get(domain);
		return site;
	}
	
	private void initializeSiteMap() {
		if(siteMap == null) {
			List<SiteConfiguration> sites = siteRepository.findAll();
			if(null != sites) {
				LOGGER.debug("Initializing siteMap");
				siteMap = new HashMap<String, SiteConfiguration>();
				for(SiteConfiguration site : sites) {
					siteMap.put(site.getSiteBaseUrl(), site);
				}
			}
		}
	}

}
