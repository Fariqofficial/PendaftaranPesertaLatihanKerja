package pradita.id.ac.pendaftaranpesertalatihankerja.Model;

public class User {

    String user_id,imageUrl,namaLengkap,tempatLahir, tanggalLahir, email_peserta,nohp, alamat_peserta, rt, rw, kelurahan, kecamatan, kotaAdministrasi, pass, nik_peserta, tahun_selesai, jurusan_sekolah, umurPeserta, jenis_kelamin, statusHubungan, peminatan_jurusan, pendidikan_terakhir, agama_peserta;

    public User(String user_id, String imageUrl, String namaLengkap, String tempatLahir, String tanggalLahir, String email_peserta, String nohp, String alamat_peserta, String rt, String rw, String kelurahan, String kecamatan, String kotaAdministrasi, String pass, String nik_peserta, String tahun_selesai, String jurusan_sekolah, String umurPeserta, String jenis_kelamin, String statusHubungan, String peminatan_jurusan, String pendidikan_terakhir, String agama_peserta) {
        this.user_id = user_id;
        this.imageUrl = imageUrl;
        this.namaLengkap = namaLengkap;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.email_peserta = email_peserta;
        this.nohp = nohp;
        this.alamat_peserta = alamat_peserta;
        this.rt = rt;
        this.rw = rw;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
        this.kotaAdministrasi = kotaAdministrasi;
        this.pass = pass;
        this.nik_peserta = nik_peserta;
        this.tahun_selesai = tahun_selesai;
        this.jurusan_sekolah = jurusan_sekolah;
        this.umurPeserta = umurPeserta;
        this.jenis_kelamin = jenis_kelamin;
        this.statusHubungan = statusHubungan;
        this.peminatan_jurusan = peminatan_jurusan;
        this.pendidikan_terakhir = pendidikan_terakhir;
        this.agama_peserta = agama_peserta;
    }

    public User() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tangalLahir) {
        this.tanggalLahir = tangalLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getEmail_peserta() {
        return email_peserta;
    }

    public void setEmail_peserta(String email_peserta) {
        this.email_peserta = email_peserta;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat_peserta() {
        return alamat_peserta;
    }

    public void setAlamat_peserta(String alamat_peserta) {
        this.alamat_peserta = alamat_peserta;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKotaAdministrasi() {
        return kotaAdministrasi;
    }

    public void setKotaAdministrasi(String kotaAdministrasi) {
        this.kotaAdministrasi = kotaAdministrasi;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNik_peserta() {
        return nik_peserta;
    }

    public void setNik_peserta(String nik_peserta) {
        this.nik_peserta = nik_peserta;
    }

    public String getTahun_selesai() {
        return tahun_selesai;
    }

    public void setTahun_selesai(String tahun_selesai) {
        this.tahun_selesai = tahun_selesai;
    }

    public String getJurusan_sekolah() {
        return jurusan_sekolah;
    }

    public void setJurusan_sekolah(String jurusan_sekolah) {
        this.jurusan_sekolah = jurusan_sekolah;
    }

    public String getUmurPeserta() {
        return umurPeserta;
    }

    public void setUmurPeserta(String umurPeserta) {
        this.umurPeserta = umurPeserta;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getStatusHubungan() {
        return statusHubungan;
    }

    public void setStatusHubungan(String statusHubungan) {
        this.statusHubungan = statusHubungan;
    }

    public String getPeminatan_jurusan() {
        return peminatan_jurusan;
    }

    public void setPeminatan_jurusan(String peminatan_jurusan) {
        this.peminatan_jurusan = peminatan_jurusan;
    }

    public String getPendidikan_terakhir() {
        return pendidikan_terakhir;
    }

    public void setPendidikan_terakhir(String pendidikan_terakhir) {
        this.pendidikan_terakhir = pendidikan_terakhir;
    }

    public String getAgama_peserta() {
        return agama_peserta;
    }

    public void setAgama_peserta(String agama_peserta) {
        this.agama_peserta = agama_peserta;
    }
}
