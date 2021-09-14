package com.mobai.community.dto;

import lombok.Data;

@Data
public class GiteeUser {
    private String name;
    private String login;
    private Long id;
    private String bio;//描述
}
