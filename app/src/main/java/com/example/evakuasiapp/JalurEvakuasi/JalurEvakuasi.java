package com.example.evakuasiapp.JalurEvakuasi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JalurEvakuasi {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"kategori_bencana":"Gempa","tempat":"Lapangan imam bonjol","alamat":"Bagindo aziz chan","kecamatan":"Padang Selatan","lat":"-0.9524403","long":"100.3608588","daya":"2000"},{"kategori_bencana":"Gempa","tempat":"GOR H. Agus Salim","alamat":"Rimbo kaluang","kecamatan":"Padang Barat","lat":"-0.9293396","long":"100.3558222","daya":"2000"},{"kategori_bencana":"Gempa","tempat":"Lapangan Gubernur","alamat":"Bandar Purus","kecamatan":"Padang Barat","lat":"-0.9356128","long":"100.3558675","daya":"1000"},{"kategori_bencana":"Gempa","tempat":"Lapangan Polda Sumbar","alamat":"Jendral Sudirman","kecamatan":"Padang Barat","lat":"-0.9360865","long":"100.3587197","daya":"1000"},{"kategori_bencana":"Gempa","tempat":"Lapangan UNP","alamat":"Air Tawar","kecamatan":"Padang Utara","lat":"-0.897397","long":"100.3485548","daya":"2000"}]
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
         * kategori_bencana : Gempa
         * tempat : Lapangan imam bonjol
         * alamat : Bagindo aziz chan
         * kecamatan : Padang Selatan
         * lat : -0.9524403
         * long : 100.3608588
         * daya : 2000
         */

        private String kategori_bencana;
        private String tempat;
        private String alamat;
        private String kecamatan;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String daya;

        public String getKategori_bencana() {
            return kategori_bencana;
        }

        public void setKategori_bencana(String kategori_bencana) {
            this.kategori_bencana = kategori_bencana;
        }

        public String getTempat() {
            return tempat;
        }

        public void setTempat(String tempat) {
            this.tempat = tempat;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getKecamatan() {
            return kecamatan;
        }

        public void setKecamatan(String kecamatan) {
            this.kecamatan = kecamatan;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLongX() {
            return longX;
        }

        public void setLongX(String longX) {
            this.longX = longX;
        }

        public String getDaya() {
            return daya;
        }

        public void setDaya(String daya) {
            this.daya = daya;
        }
    }
}
