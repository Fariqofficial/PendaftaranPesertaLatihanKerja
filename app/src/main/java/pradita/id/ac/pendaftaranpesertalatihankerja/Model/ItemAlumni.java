package pradita.id.ac.pendaftaranpesertalatihankerja.Model;

public class ItemAlumni {

    public String nama_peserta;
    public String angkatan;
    public String kejuruan;
    public String nama_pt;
    public String jenis_pt;

    public ItemAlumni(String nama_peserta, String angkatan, String kejuruan, String nama_pt, String jenis_pt) {
        this.nama_peserta = nama_peserta;
        this.angkatan = angkatan;
        this.kejuruan = kejuruan;
        this.nama_pt = nama_pt;
        this.jenis_pt = jenis_pt;
    }

    public String getNama_peserta() {
        return nama_peserta;
    }

    public void setNama_peserta(String nama_peserta) {
        this.nama_peserta = nama_peserta;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getKejuruan() {
        return kejuruan;
    }

    public void setKejuruan(String kejuruan) {
        this.kejuruan = kejuruan;
    }

    public String getNama_pt() {
        return nama_pt;
    }

    public void setNama_pt(String nama_pt) {
        this.nama_pt = nama_pt;
    }

    public String getJenis_pt() {
        return jenis_pt;
    }

    public void setJenis_pt(String jenis_pt) {
        this.jenis_pt = jenis_pt;
    }

    public ItemAlumni() {
    }
}
