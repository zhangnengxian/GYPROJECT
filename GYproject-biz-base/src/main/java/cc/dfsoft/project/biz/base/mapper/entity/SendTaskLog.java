package cc.dfsoft.project.biz.base.mapper.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="T_SENDTASK_LOG")
public class SendTaskLog implements Serializable {

    private String id;
    private String uri;
    private String user;
    private String username;
    private String message;
    private String results;
    private Date createtime;
    private String localhostip;
    private String error;
    private String menuname;
    private String description;
    @Column(name="MENUNAME")
    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column(name="ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name="URI")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    @Column(name="USER")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    @Column(name="USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name="MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Column(name="RESULTS")
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATETIME")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    @Column(name="LOCALHOSTIP")
    public String getLocalhostip() {
        return localhostip;
    }

    public void setLocalhostip(String localhostip) {
        this.localhostip = localhostip;
    }
    @Column(name="ERROR")
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
