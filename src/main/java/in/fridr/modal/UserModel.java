package in.fridr.modal;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;

import in.fridr.entity.CenterDetails;
import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;

public class UserModel {
    
	private String userName;
	private String userId;
	private String passwrord;
	private String userMobile;
	private String userDescription;
	private String investiGationDate;
	private String address;
	private int userRoleId;
	private String userUniqueId;
	private int centerDetailsId;
	private String newPwd;
	private String oldPwd;
	private String retypeNewPwd;
	private String token;
	
	
	
	
	public UserModel(String userName, String userUniqueId) {
		super();
		this.userName = userName;
		this.userUniqueId = userUniqueId;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserModel(String userName, String userId, String investiGationDate) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.investiGationDate = investiGationDate;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswrord() {
		return passwrord;
	}
	public void setPasswrord(String passwrord) {
		this.passwrord = passwrord;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserUniqueId() {
		return userUniqueId;
	}
	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}
	
	
	
	
	//method to retrun the 
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getRetypeNewPwd() {
		return retypeNewPwd;
	}

	public void setRetypeNewPwd(String retypeNewPwd) {
		this.retypeNewPwd = retypeNewPwd;
	}

	public String getInvestiGationDate() {
		return investiGationDate;
	}

	public void setInvestiGationDate(String investiGationDate) {
		this.investiGationDate = investiGationDate;
	}

	// get userDetails to save into database
     public int getCenterDetailsId() {
		return centerDetailsId;
	}
	public void setCenterDetailsId(int centerDetailsId) {
		this.centerDetailsId = centerDetailsId;
	}
	public UserCredential getUserCredentialToSave() {
		
		UserDetail userDetails= new UserDetail(Timestamp.valueOf(LocalDateTime.now())
				,userDescription,
				userMobile, 
				userName,
				true,
				userUniqueId,
				address,new CenterDetails(centerDetailsId));
		return new UserCredential(userId, passwrord, Timestamp.valueOf(LocalDateTime.now()), userDetails);
	 }
	@Override
	public String toString() {
		return "UserModel [userName=" + userName + ", userId=" + userId + ", passwrord=" + passwrord + ", userMobile="
				+ userMobile + ", userDescription=" + userDescription + ", address=" + address + ", userRoleId="
				+ userRoleId + ", userUniqueId=" + userUniqueId + "]";
	}
	
     
	
}
