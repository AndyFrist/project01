package com.hh.gridview_recyclerview.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public class loginBean {

    /**
     * status : 1
     * resultInfos : {"user_avatar":"","shop_id":"0","user_phone":"13507819609","user_type":"1","sessionid":"f6d9511993c3da707b59af6bf0c27468","user_nick":"","user_level":"1","user_name":"13507819609","email":"","user_code":"UWJ2017112800001","user_login_name":"13507819609","role_name":"","user_id":"21616","role_id":"0","role_code":"","ent_id":"1","applyStatus":[]}
     * sessionid : f6d9511993c3da707b59af6bf0c27468
     * desc : 成功
     */

    private String status;
    private ResultInfosBean resultInfos;
    private String sessionid;
    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultInfosBean getResultInfos() {
        return resultInfos;
    }

    public void setResultInfos(ResultInfosBean resultInfos) {
        this.resultInfos = resultInfos;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class ResultInfosBean {
        /**
         * user_avatar :
         * shop_id : 0
         * user_phone : 13507819609
         * user_type : 1
         * sessionid : f6d9511993c3da707b59af6bf0c27468
         * user_nick :
         * user_level : 1
         * user_name : 13507819609
         * email :
         * user_code : UWJ2017112800001
         * user_login_name : 13507819609
         * role_name :
         * user_id : 21616
         * role_id : 0
         * role_code :
         * ent_id : 1
         * applyStatus : []
         */

        private String user_avatar;
        private String shop_id;
        private String user_phone;
        private String user_type;
        private String sessionid;
        private String user_nick;
        private String user_level;
        private String user_name;
        private String email;
        private String user_code;
        private String user_login_name;
        private String role_name;
        private String user_id;
        private String role_id;
        private String role_code;
        private String ent_id;
        private List<?> applyStatus;

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }

        public String getUser_level() {
            return user_level;
        }

        public void setUser_level(String user_level) {
            this.user_level = user_level;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getUser_login_name() {
            return user_login_name;
        }

        public void setUser_login_name(String user_login_name) {
            this.user_login_name = user_login_name;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getRole_code() {
            return role_code;
        }

        public void setRole_code(String role_code) {
            this.role_code = role_code;
        }

        public String getEnt_id() {
            return ent_id;
        }

        public void setEnt_id(String ent_id) {
            this.ent_id = ent_id;
        }

        public List<?> getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(List<?> applyStatus) {
            this.applyStatus = applyStatus;
        }
    }
}
