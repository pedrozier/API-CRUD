package com.standart.crudjwt.model;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class MyUser extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    public String toString() {
        return "ID=" + this.getId() + " ,Name= " + this.getUserName() + " ,Password= ***";
    }

}
