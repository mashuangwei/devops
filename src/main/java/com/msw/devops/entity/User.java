package com.msw.devops.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5440372534300871944L;

    private Integer id;
    private String username;
    private String password;
    private Date createTime;
    private String status;
}
