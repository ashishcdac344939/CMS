package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_roles_mapping database table.
 * 
 */
@Entity
@Table(name="user_roles_mapping")
@NamedQuery(name="UserRolesMapping.findAll", query="SELECT u FROM UserRolesMapping u")
public class UserRolesMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "user_roles_mapping_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to UserCredential
	@ManyToOne
	@JoinColumn(name="user_cred_id")
	private UserCredential userCredential;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="role_id")
	private UserRole userRole;

	// constructor
	public UserRolesMapping() {
	}
	
	public UserRolesMapping(UserCredential userCredential, UserRole userRole) {
		super();
		this.userCredential = userCredential;
		this.userRole = userRole;
	}
	public Integer getId() {
		return this.id;
	}

	// getter setter
	public void setId(Integer id) {
		this.id = id;
	}

	public UserCredential getUserCredential() {
		return this.userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserRolesMapping [id=" + id + ", userCredential=" + userCredential + ", userRole=" + userRole + "]";
	}

	

	
}