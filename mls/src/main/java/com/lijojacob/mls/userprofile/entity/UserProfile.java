package com.lijojacob.mls.userprofile.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.social.linkedin.api.Location;

import com.lijojacob.mls.common.annotation.ItemListingProperties;
import com.lijojacob.mls.common.annotation.PropertyOrder;
import com.lijojacob.mls.common.entity.BaseEntity;
import com.lijojacob.mls.common.entity.VerificationStatus;
import com.lijojacob.mls.priceLists.entity.PriceList;
import com.lijojacob.mls.productcatalog.entity.Catalog;
import com.lijojacob.mls.promotions.entity.PromotionStatus;

@Document
@Description("User Profile")
@PropertyOrder({"login", "firstName", "middleName", "lastName", "dateOfBirth", "gender"})
@ItemListingProperties({"login", "firstName", "middleName", "lastName"})
public @Data class UserProfile extends BaseEntity {
	
	@Description("User login ID")
	@Indexed(name="USER_PROFILE_LOGIN_IDX", dropDups = false, unique = true)
	@TextIndexed
	@NotNull
	private String login;

	@Description("Password")
	@NotNull
	private String password;
	
	@Description("First Name")
	@TextIndexed
	@NotNull
	private String firstName;
	
	@Description("Last Name")
	@TextIndexed
	private String lastName;
	
	@Description("Middle Name")
	@TextIndexed
	private String middleName;
	
	@Description("Email Address")
	@TextIndexed
	@NotNull
	@Indexed(name="USER_PROFILE_EMAIL_IDX", dropDups = false, unique = true, sparse = true)
	private String email;
	
	@Description("Date of Birth")
	private Date dateOfBirth;
	
	private Gender gender;
	
	@Description("Mobile number")
	private String mobileNumber;
	
	@TextScore
	private Float score;
	
	@Description("Abandoned Order Count")
	private int abandonedOrderCount;
	
	@Description("Abandoned Orders")
	private Set<Order> abandonedOrders;

	@Description("Active Promotions")
	private List<PromotionStatus> activePromotions;
	
	@Description("Auto-Login")
	private boolean autoLogin;
	
	@Description("Credit Cards")
	private Map<String, CreditCard> creditCards;
	
	@Description("Daytime telephone number")
	private String daytimeTelephoneNumber;
	
	@Description("Default Credit Card")
	private CreditCard defaultCreditCard;
	
	@Description("Email Status")
	private VerificationStatus emailStatus;
	
	@Description("Express Checkout")
	private boolean expressCheckout;
	
	@Description("Favorite Stores")
	private List<Location> favoriteStores;
	
	@Description("Generated Password")
	private boolean generatedPassword;
	
	@Description("Home Address")
	private ContactInfo homeAddress;
	
	@Description("Last activity")
	private Date lastActivity;
	
	@Description("My Catalog")
	private Catalog myCatalog;
	
	@Description("My Price List")
	private PriceList myPriceList;
	
	@Description("My sale price list")
	private PriceList mySalePriceList;
	
	@Description("Registration Date")
	private Date registrationDate;
	
	public String getDisplayName() {
		StringBuilder displayName = new StringBuilder();
		if(StringUtils.isNotEmpty(firstName)) {
			displayName.append(firstName).append(" ");
		}
		if(StringUtils.isNotEmpty(middleName)) {
			displayName.append(middleName).append(" ");
		}
		if(StringUtils.isNotEmpty(lastName)) {
			displayName.append(lastName);
		}
		return displayName.toString();
	}

}
