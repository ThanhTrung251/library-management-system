package com.example.lib.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="member")

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String mail;

    private String major;

    private LocalDate expired;

    private LocalDate birthday;

    @ManyToMany
    @JoinTable(name = "members_roles", joinColumns = @JoinColumn(name ="memberid"),
                       inverseJoinColumns = @JoinColumn(name ="roleid"))
    List<Role> roles = new ArrayList<>();


    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
    private List<BorrowBook> memberBookList;

}
