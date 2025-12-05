package com.beauty.pro.ia.agendamento.enums;

public enum UserPlan {
    FREE(1),
    BASIC(2),
    PREMIUM(3);

    private int code;

    private UserPlan(int code) { this.code = code; }

    public int getCode() { return code; }

    public static UserRole valueOf(int code){
        for (UserRole value : UserRole.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid UserPlan code");
    }

}
