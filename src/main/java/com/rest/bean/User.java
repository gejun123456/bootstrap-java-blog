package com.rest.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private boolean login = false;

    private int userId;

    private String userName;

    private boolean admin = false;
}
