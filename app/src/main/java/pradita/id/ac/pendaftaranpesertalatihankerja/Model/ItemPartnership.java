package pradita.id.ac.pendaftaranpesertalatihankerja.Model;

public class ItemPartnership {

    public String nama_perusahaan;
    public String website_perusahaan;
    public String alamat_perusahaan;

    public ItemPartnership(String nama_perusahaan, String website_perusahaan, String alamat_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
        this.website_perusahaan = website_perusahaan;
        this.alamat_perusahaan = alamat_perusahaan;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getWebsite_perusahaan() {
        return website_perusahaan;
    }

    public void setWebsite_perusahaan(String website_perusahaan) {
        this.website_perusahaan = website_perusahaan;
    }

    public String getAlamat_perusahaan() {
        return alamat_perusahaan;
    }

    public void setAlamat_perusahaan(String alamat_perusahaan) {
        this.alamat_perusahaan = alamat_perusahaan;
    }

    public ItemPartnership() {
    }

}
