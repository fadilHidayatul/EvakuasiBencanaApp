package com.example.evakuasiapp.Admin.Model;

import java.util.List;

public class Comment {

    /**
     * success : 1
     * status : 200
     * message : Data Ada
     * DATA : [{"tanggal":"2021-03-10","jam":"15:10:07","komentar":"test","kategori":"Banjir Bandang"},{"tanggal":"2021-03-10","jam":"18:10:05","komentar":"Yesssssssss","kategori":"Gempa"}]
     */

    private int success;
    private int status;
    private String message;
    private List<DATABean> DATA;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

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

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * tanggal : 2021-03-10
         * jam : 15:10:07
         * komentar : test
         * kategori : Banjir Bandang
         */

        private String tanggal;
        private String jam;
        private String komentar;
        private String kategori;

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getJam() {
            return jam;
        }

        public void setJam(String jam) {
            this.jam = jam;
        }

        public String getKomentar() {
            return komentar;
        }

        public void setKomentar(String komentar) {
            this.komentar = komentar;
        }

        public String getKategori() {
            return kategori;
        }

        public void setKategori(String kategori) {
            this.kategori = kategori;
        }
    }
}
