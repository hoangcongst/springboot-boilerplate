package com.hoangcongst.sbboilerplate.auth.model;

import com.conght.common.database.interceptor.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
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
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Validated
public class User extends BaseModel implements UserDetails {
	public static final byte STATUS_ACTIVE = 0;
	public static final byte STATUS_BANNED = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "display_name")
	private String name;
	@JsonIgnore
	private String email;
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
	@Column(nullable = false, updatable = false)
	@CreatedDate
	private Timestamp created_at;

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
		return this.email;
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
