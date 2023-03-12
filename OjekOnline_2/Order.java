import java.lang.Math;
import java.util.ArrayList;

public class Order{
    private String customer, driver, posisi, tujuan;    
    private int waktu, ongkir, totalOkRide, jarak, totalOkFood; 
    private boolean canceled;  
    private ArrayList<Makanan> makanan = new ArrayList<>();             

    public Order(String customer, String driver, String posisi, String tujuan, boolean canceled, ArrayList<Makanan> makanan) {              
        this.customer = customer;
        this.driver = driver;
        this.posisi = posisi;
        this.tujuan = tujuan;
        this.ongkir = 10000;           
        this.jarak = setJarak();
        this.waktu = this.jarak  * 10;
        this.totalOkRide = totalHarga();
        this.totalOkFood = totalHargaMakanan(); 
        this.makanan = makanan;    
        this.canceled = canceled;       
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPosisi() {
        return this.posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
        
    public String getTujuan() {
        return this.tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
        
    public int getJarak() {
        return this.jarak;
    }

    public int setJarak() {
        int batasBwh = 1;
        int batasAts = 10;
        int jarak;

        jarak = (int) (Math.random() * (batasAts + batasBwh)/2)  + batasBwh;
        return jarak;
    }
    
    public int getWaktu() {
        return this.waktu;
    }
    
    public int getOngkir() {
        return this.ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public ArrayList<Makanan> getMakanan() {
        return this.makanan;
    }

    public void setMakanan(ArrayList<Makanan> makanan) {
        this.makanan = makanan;
    }

    private int totalHarga(){
        int total = 0;    
        if(this.canceled == false){                      
            total += (int)(this.jarak * this.ongkir);                     
            return total;
        }else {
            return total;
        }
    } 

    private int totalHargaMakanan(){
        int total = 0;           

        if(this.canceled == false){  
            for(int i=0; i<this.makanan.size(); i++){
                total += this.makanan.get(i).getJumlah() * this.makanan.get(i).getHarga();
             }                    
            total += this.ongkir;             
            return total;
        }else {
            return total;
        }
    }

    public boolean getCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }          

    public int getBiaya(String tipe){
        if(tipe.equals("ride")){
            return this.totalOkRide;
        }
        else{
            return this.totalOkFood;    
        }
    } 

    // public void keteranganOrder(){
        // if(pengguna.getUsername().equals(this.customer)){
        //     if(this.makanan == null){

            //    System.out.println("Anda sedang mengorder pelayanan Ok Ride");
        //     }
        //     else{
        //        System.out.println("Anda sedang mengorder pelayanan Ok Food");
        //     }
        // }
        // System.out.println("");
    // }

    // @Override
    // public void keteranganOrder(){
        // if(pengguna.getUsername().equals(this.driver)){
        //     if(this.makanan == null){
            //    System.out.println("Anda sedang menerima orderan Ok Ride");
        //     }
        //     else{
        //        System.out.println("Anda sedang menerima orderan Ok Food");
        //     }
        // }
        // System.out.println("");
    // }
}