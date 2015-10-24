package com.saintdan.framework.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Authorized roles, provide for spring security.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/23/15
 * @since JDK1.8
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = -5193344128221526323L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
    @Column(unique = true, nullable = false, length = 20)
	private String name;

    @Column(length = 500)
    private String description;

    @LastModifiedDate
    private Date lastModifyTime;

    @LastModifiedBy
    private User lastModifyUser;

    @CreatedDate
    private Date createTime;

    @NotNull
    @Column(nullable = false)
    private Integer version;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = {CascadeType.REFRESH})
	private Set<User> users = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "roles_has_groups",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private Set<Group> groups = new HashSet<>();

	@Override
	public String getAuthority() {
		return name;
	}

    public Role() {

    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public User getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(User lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
