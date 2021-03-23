package com.example.evakuasiapp.JalurEvakuasi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JalurEvakuasi {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"id":"12","kategori_bencana":"Tsunami","tempat":"Bappeda Prov. Sumbar","alamat":"Jl. Khatib Sulaiman","kecamatan":"Padang Barat","lat":"-0.9257988","long":"100.3588886","daya":"2000","jarak":"4516.512133421126"},{"id":"13","kategori_bencana":"Tsunami","tempat":"Bukit di Air Manis","alamat":"Air Manis","kecamatan":"Padang Selatan","lat":"-0.9897317","long":"100.3638474","daya":"3000","jarak":"4991.960531470164"},{"id":"9","kategori_bencana":"Tsunami","tempat":"AMIK Indonesia","alamat":"Jl.Khatib Sulaiman","kecamatan":"Padang Utara","lat":"-0.9137146","long":"100.3559253","daya":"2000","jarak":"5708.857652264278"},{"id":"16","kategori_bencana":"Tsunami","tempat":"Bukit di Lantamal II","alamat":"Lantamal II Teluk Bayur","kecamatan":"Padang Selatan","lat":"-1.0036799","long":"100.3645023","daya":"2000","jarak":"6299.011135644493"}]
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
         * id : 12
         * kategori_bencana : Tsunami
         * tempat : Bappeda Prov. Sumbar
         * alamat : Jl. Khatib Sulaiman
         * kecamatan : Padang Barat
         * lat : -0.9257988
         * long : 100.3588886
         * daya : 2000
         * jarak : 4516.512133421126
         */

        private String id;
        private String kategori_bencana;
        private String tempat;
        private String alamat;
        private String kecamatan;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String daya;
        private String jarak;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getJarak() {
            return jarak;
        }

        public void setJarak(String jarak) {
            this.jarak = jarak;
        }
    }
}
