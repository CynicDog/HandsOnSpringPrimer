package com.example.springbootsample.domain.user.model;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Data
@Entity
@Table(name = "m_user")
public class MUser {
    @Id
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;

    @ManyToOne(optional = true)
    @JoinColumn(insertable = false, updatable = false, name = "departmentId")
    private Department department;

    @OneToMany
    @JoinColumn(insertable = false, updatable = false, name = "userId")
    private List<Salary> salaryList;

    public String toCSV() {
        String genderStr = null;

        if (gender == 1) {
            genderStr = "Male";
        }  else {
            genderStr = "Female";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String csv = userId + "," + userName + "," + simpleDateFormat.format(birthday) +
                "," + age + "," + genderStr + "\r\n";

        return csv;
    }
}
