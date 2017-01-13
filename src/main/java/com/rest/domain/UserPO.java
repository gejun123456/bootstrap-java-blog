package com.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

    private Integer id;

    private Integer auth;

    private String username;

    private String mobile;

    private String email;

    private String cryptpasswod;

    private String passwordcookie;
}
