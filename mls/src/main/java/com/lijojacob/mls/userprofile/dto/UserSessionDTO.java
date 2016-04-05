package com.lijojacob.mls.userprofile.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.lijojacob.mls.order.dto.ShoppingCart;
import com.lijojacob.mls.userprofile.entity.UserSession;
import com.lijojacob.mls.userprofile.entity.UserSessionInterface;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionDTO implements UserSessionInterface {
	
	private UserSession userSession;
	
	private ShoppingCart shoppingCart;
	
	public UserSessionDTO() {
		
	}
	
	public UserSessionDTO(UserSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public UserSession getUserSession() {
		return this.userSession;
	}

	@Override
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}
	
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
