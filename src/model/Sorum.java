package model;

// Getter AND Setter halaman SORUM
public class Sorum {
    private String id;
    private String namapem;
    private String alamat;
    private String jenismot;
    private Namot namot;
    private Mermo mermo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamapem() {
        return namapem;
    }

    public void setNamapem(String namapem) {
        this.namapem = namapem;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenismot() {
        return jenismot;
    }

    public void setJenismot(String jenismot) {
        this.jenismot = jenismot;
    }

    public Namot getNamot() {
        return namot;
    }

    public void setNamot(Namot namot) {
        this.namot = namot;
    }

    public Mermo getMermo() {
        return mermo;
    }

    public void setMermo(Mermo mermo) {
        this.mermo = mermo;
    }

}
