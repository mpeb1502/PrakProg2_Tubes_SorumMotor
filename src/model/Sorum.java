package model;

// Getter And Setter
public class Sorum {
    private String id;
    private String nama;
    private String umur;
    private String jenisKelamin;
    private Namot jenisHewan;
    private Merek habitat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Namot getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(Namot jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public Merek getHabitat() {
        return habitat;
    }

    public void setHabitat(Merek habitat) {
        this.habitat = habitat;
    }

}
