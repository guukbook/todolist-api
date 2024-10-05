package com.example.todolist.commons;

import lombok.Getter;

@Getter
public final class Statics {


    private static final String API = "/api";
    private static final String V1 = "/v1";

    public static final String TO_DO_CONTROLLER = API + V1 + "/todos";

    private Statics() {

    }
}
