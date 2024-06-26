package com.example.enterprisecourse.models.users;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.enterprisecourse.models.comments.CommentEntity;
import com.example.enterprisecourse.models.patchnotes.PatchNotesEntity;
import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.roles.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 64)
    private String username;

    @Size(min = 4, max = 64, message = "Password must contain at least 4-64 chars")
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean accountEnabled;
    private boolean credentialsNonExpired;


    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<PostEntity> posts;
    
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<PatchNotesEntity> patchNotes;
    
    public UserEntity() {}

    public UserEntity(String username, String password, Roles roles,
                      boolean accountNonExpired, boolean accountNonLocked, boolean accountEnabled, boolean credentialsNonExpired) {
        this.username = username;
        this.password = password;
        this.role = roles;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.accountEnabled = accountEnabled;
        this.credentialsNonExpired = credentialsNonExpired;
    }

	    // yahya <33
	    public long getId() {
	    	return id;
	    }

	    // TODO - ? extends what does it do?
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {

	        // [GET, POST]
	        // From ROLES ([ROLE_ADMIN, GET, POST])
	        // return role.getAuthorities();
	        return role.getAuthorities();
	    }


	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @Override
	    public String getUsername() {
	        return username;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return accountNonExpired;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return accountNonLocked;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return credentialsNonExpired;
	    }

	    @Override
	    public boolean isEnabled() {
	        return accountEnabled;
	    }

	    public Roles getRole() {
	        return role;
	    }

	    public void setRole(Roles role) {
	        this.role = role;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setAccountNonExpired(boolean accountNonExpired) {
	        this.accountNonExpired = accountNonExpired;
	    }

	    public void setAccountNonLocked(boolean accountNonLocked) {
	        this.accountNonLocked = accountNonLocked;
	    }

	    public void setAccountEnabled(boolean accountEnabled) {
	        this.accountEnabled = accountEnabled;
	    }

	    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
	        this.credentialsNonExpired = credentialsNonExpired;
	    }

}
