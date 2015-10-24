package com.saintdan.framework.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Authorized resources,provide for spring security.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/25/15
 * @since JDK1.8
 */
@Entity
@Table(name = "resources")
public class Resource implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 6298843159549723556L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    /**
     * The resource path
     * <p><b>NOTE: Using ANT path mode</b></p>
     */
    @NotEmpty
    @Column(unique = true, length = 1024, nullable = false)
    private String path;

    /**
     * The priority. the smaller the value the higher the priority.
     */
    @NotNull
    @Column(nullable = false)
    private Integer priority;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, name = "last_modify_at")
    private Date lastModifyAT;

    @Column(nullable = false, name = "create_at")
    private Date createAT;

    @NotNull
    @Column(nullable = false)
    private Integer version;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "resources", cascade = {CascadeType.REFRESH})
    private Set<Group> groups = new HashSet<>();

    public Resource() {

    }

    public Resource(String name, String path, Integer priority, String description) {
        this.name = name;
        this.path = path;
        this.priority = priority;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return name;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModifyAT() {
        return lastModifyAT;
    }

    public void setLastModifyAT(Date lastModifyAT) {
        this.lastModifyAT = lastModifyAT;
    }

    public Date getCreateAT() {
        return createAT;
    }

    public void setCreateAT(Date createAT) {
        this.createAT = createAT;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
