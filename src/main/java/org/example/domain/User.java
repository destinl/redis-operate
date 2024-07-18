package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1716:02
 */
@AllArgsConstructor
//@NoArgsConstructor
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    // 省略构造函数、getter和setter方法

    private double longitude;
    private double latitude;
}
