package com.example.glife.common;

import com.example.glife.entity.Admin;

public class AdminHolder {
    private static final ThreadLocal<Admin> tl = new ThreadLocal<>();

    public static void saveAdmin(Admin admin){
        tl.set(admin);
    }

    public static Admin getAdmin(){
        return tl.get();
    }

    public static void removeAdmin(){
        tl.remove();
    }

}
