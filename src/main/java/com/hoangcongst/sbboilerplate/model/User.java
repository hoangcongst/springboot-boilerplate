package com.hoangcongst.sbboilerplate.model;

import com.hoangcongst.sbboilerplate.util.MysqlSHAUtil;
import com.conght.common.database.interceptor.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "tb_admin_info")
@EntityListeners(AuditingEntityListener.class)
@Data
@Validated
public class User extends BaseModel implements UserDetails {
	public static final byte STATUS_ACTIVE = 0;
	public static final byte STATUS_BANNED = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_seq")
	private long id;

	public String getName() {
		return MysqlSHAUtil.decrypt(name);
	}

	public String getPhone() {
		return MysqlSHAUtil.decrypt(phone);
	}

	@Column(name = "admin_name")
	private String name;
	@Column(name = "admin_email")
	@JsonIgnore
	private String email;
	@Column(name = "admin_pw")
	@JsonIgnore
	private String password;
	@Column(name = "status")
	@JsonIgnore
	private int status;
	@JsonIgnore
	@Transient
	@OneToOne
	@JoinColumn(name="ins_admin_id")
	private User insertUser;
	@JsonIgnore
	@Column(name = "admin_phone")
	private String phone;
	@Column(name = "ins_dtm", nullable = false, updatable = false)
	@CreatedDate
	private Timestamp created_at;

	@Column(name = "update_dtm")
	@LastModifiedDate
	private Timestamp updated_at;

	public User() {}

	public User(long id) {this.id = id;}

	public User(String name,
                String email, String password, int status) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return MysqlSHAUtil.decrypt(this.email);
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return this.status == User.STATUS_ACTIVE;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
