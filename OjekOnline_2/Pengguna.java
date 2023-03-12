public class Pengguna{
    private String email;
    private String username;
    private String password;
    private String namaPengguna;
    private String phone;
    private String alamat;    
    private String status;
    private int idx;

    public Pengguna(int idx, String email, String username, String password, String namaPengguna, String phone, String alamat, String status) {
        this.idx = idx;
        this.email = email;
        this.username = username;
        this.password = password;
        this.namaPengguna = namaPengguna;
        this.phone = phone;
        this.alamat = alamat;
        this.status = status;
    }

    public int getIdx() {
        return this.idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaPengguna() {
        return this.namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean login(String username, String password){
        if((username.equals(this.username) || username.equals(this.email)) && password.equals(this.password)) {
            return true;
        } else {
            return false;
        }
    }

    // public boolean dataStatus(){
    //     if(this.status.equals("customer")) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
}