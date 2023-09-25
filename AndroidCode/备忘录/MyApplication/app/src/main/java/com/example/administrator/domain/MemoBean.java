package com.example.administrator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemoBean {
    private String title;
    private String content;
    private String imgPath;
    private String time;
}
