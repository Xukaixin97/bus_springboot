package com.example.blq.pojo;


import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {

    String id;
    String username;
    String password;
    String email;
    String telephone;
    String flag;

}