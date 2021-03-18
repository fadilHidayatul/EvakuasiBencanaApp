package com.example.evakuasiapp.Admin.Model;

public class Admin {

    /**
     * status : 200
     * message : Login Berhasil
     * DATA : {"id_admin":"1","username":"admin","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2RiX2V2YWt1YXNpIiwiYXVkIjoiaHR0cDpcL1wvbG9jYWxob3N0XC9kYl9ldmFrdWFzaSIsImlhdCI6MTYxMzk4NjEzNSwiZXhwIjoxNjEzOTg5NzM1LCJkYXRhIjp7InVzZXJfaWQiOiIxIn19.cTUn6ULgssxQu8jT7EF8XHRMDBr6B19gLrlufTSgTpQ"}
     */

    private int status;
    private String message;
    private DATABean DATA;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DATABean getDATA() {
        return DATA;
    }

    public void setDATA(DATABean DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * id_admin : 1
         * username : admin
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2RiX2V2YWt1YXNpIiwiYXVkIjoiaHR0cDpcL1wvbG9jYWxob3N0XC9kYl9ldmFrdWFzaSIsImlhdCI6MTYxMzk4NjEzNSwiZXhwIjoxNjEzOTg5NzM1LCJkYXRhIjp7InVzZXJfaWQiOiIxIn19.cTUn6ULgssxQu8jT7EF8XHRMDBr6B19gLrlufTSgTpQ
         */

        private String id_admin;
        private String username;
        private String token;

        public String getId_admin() {
            return id_admin;
        }

        public void setId_admin(String id_admin) {
            this.id_admin = id_admin;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
