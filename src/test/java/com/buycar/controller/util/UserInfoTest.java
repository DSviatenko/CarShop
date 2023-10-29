package com.buycar.controller.util;

import com.buycar.entity.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserInfoTest {
    public Long id;
    public String name;
    public Role role;
    public String email;
    public String password;
}
