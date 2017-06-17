package com.cricketerlk.models.user;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Supun on 5/11/2017.
 */

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @Column(name = "UID", unique = true, nullable = false)
    @NotBlank
    private String userId;

    @Column(name = "USER_NAME", unique = true, nullable = false)
    @NotBlank
    private String userName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "EMAIL", unique = true, nullable = false)
    @NotBlank
    private String email;

    @Column(name = "USER_ROLE")
    private int userRole;

    @Column(name = "USER_IMAGE")
    private String userImage;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @Column(name = "TEAM_IMAGE")
    private String teamImage;

    @Column(name = "TEAM_MOTTO")
    private String teamMotto;

    @Column(name = "SUPPORTING_COUNTRY")
    private int supportingCountry;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String teamImage) {
        this.teamImage = teamImage;
    }

    public String getTeamMotto() {
        return teamMotto;
    }

    public void setTeamMotto(String teamMotto) {
        this.teamMotto = teamMotto;
    }

    public int getSupportingTeam() {
        return supportingCountry;
    }

    public void setSupportingTeam(int supportingTeam) {
        this.supportingCountry = supportingTeam;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getSupportingCountry() {
        return supportingCountry;
    }

    public void setSupportingCountry(int supportingCountry) {
        this.supportingCountry = supportingCountry;
    }
}
