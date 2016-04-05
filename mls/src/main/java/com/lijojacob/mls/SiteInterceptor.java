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
import org.springframework.web.util.WebUtils;

import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;
import com.lijojacob.mls.siteconfiguration.repository.SiteConfigurationRepository;
import com.lijojacob.mls.userprofile.entity.UserSession;
import com.lijojacob.mls.userprofile.entity.UserSessionInterface;
import com.lijojacob.mls.userprofile.repository.UserProfileRepository;
import com.lijojacob.mls.userprofile.repository.UserSessionRepository;

@Service
public class SiteInterceptor extends HandlerInterceptorAdapter {
	
private static final Logger LOGGER = LoggerFactory.getLogger(SiteInterceptor.class);
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private UserSessionRepository userSessionRepository;
	
	@Autowired
	private SiteConfigurationRepository siteRepository;
	
	@Autowired
	private UserSessionInterface userSessionDTO;
	
	private Map<String, SiteConfiguration> siteMap;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		LOGGER.debug("Inside SiteInterceptor.preHandle()");
		initializeSiteMap();
		Iterable<UserSession> userSessions = getUserSessions(request);
		SiteConfiguration requestedSite = this.getSite(request);
		
		
		UserSession currentUserSession = null;
		SiteConfiguration currentSite = requestedSite;
		UserSession userSessionWithoutSite = null;
		
		for(UserSession userSession : userSessions) {
			if(null != userSession.getSite() && null != requestedSite) {
				if(userSession.getSite().getId().equals(requestedSite.getId())) {
					currentUserSession = userSession;
				}
			} else if(null == userSession.getSite()) {
				userSessionWithoutSite = userSession;
			}
		}
		
		if(currentUserSession == null) {
			if(userSessionWithoutSite != null) {
				currentUserSession = userSessionWithoutSite;
				currentUserSession.setSite(currentSite);
				userSessionRepository.save(currentUserSession);
			}
		}
		
		Cookie currentUserSessionCookie = WebUtils.getCookie(request, "currentUserSession");
		if(null != currentUserSessionCookie) {
			currentUserSessionCookie.setValue(currentUserSession.getId());
		} else {
			currentUserSessionCookie = new Cookie("currentUserSession", currentUserSession.getId());
			response.addCookie(currentUserSessionCookie);
		}
		
		Cookie currentSiteCookie = WebUtils.getCookie(request, "currentSite");
		if(null != currentSiteCookie) {
			currentSiteCookie.setValue(currentSite.getId());
		} else {
			currentSiteCookie = new Cookie("currentSite", currentSite.getId());
			response.addCookie(currentSiteCookie);
		}
		
		userSessionDTO.setUserSession(currentUserSession);
		request.setAttribute("currentSite", currentSite);
		
		return true;
	}

	private Iterable<UserSession> getUserSessions(HttpServletRequest request) {
		List<String> userSessionIds = this.getCookieValues("userSessionId", request);
		Iterable<UserSession> userSessions = userSessionRepository.findAll(userSessionIds);
		return userSessions;
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

}
