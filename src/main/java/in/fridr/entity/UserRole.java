package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the user_roles database table.
 * 
 */
@Entity
@Table(name="user_roles")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "user_roles_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleId;

	@Column(name="record_tracking")
	private Time recordTracking;

	@Column(name="role_name")
	private String roleName;

	// construction
	public UserRole() {
	}
	public UserRole(Integer roleId) {
		super();
		this.roleId = roleId;
	}
	// getter setter
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Time getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Time recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

	

}