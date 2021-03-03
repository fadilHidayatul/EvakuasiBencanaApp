package com.example.evakuasiapp.Tsunami;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TitikTsunami {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"kategori_bencana":"Tsunami","alamat":"Jl. Samudra Belakang Tangsi","kecamatan":"Padang Barat","lat":"-0.9570834","long":"100.3512016"},{"kategori_bencana":"Tsunami","alamat":"Jl. Muara, Berok Nipah","kecamatan":"Padang Barat","lat":"-0.9620401","long":"100.3526489"},{"kategori_bencana":"Tsunami","alamat":"Purus","kecamatan":"Padang Barat","lat":"-0.9452322","long":"100.3514743"},{"kategori_bencana":"Tsunami","alamat":"Pantai Muaro Lasak","kecamatan":"Padang Barat","lat":"-0.9286721","long":"100.3482688"}]
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
         * kategori_bencana : Tsunami
         * alamat : Jl. Samudra Belakang Tangsi
         * kecamatan : Padang Barat
         * lat : -0.9570834
         * long : 100.3512016
         */

        private String kategori_bencana;
        private String alamat;
        private String kecamatan;
        private String lat;
        @SerializedName("long")
        private String longX;

        public String getKategori_bencana() {
            return kategori_bencana;
        }

        public void setKategori_bencana(String kategori_bencana) {
            this.kategori_bencana = kategori_bencana;
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
    }
}
