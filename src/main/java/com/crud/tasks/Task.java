package com.crud.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Task {
    private long id;
    private String title;
    private String content;
}

