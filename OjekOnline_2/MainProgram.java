// import java.text.ParseException;
import java.util.ArrayList;
// import java.util.Date;
import java.lang.Math;
import java.util.Scanner;

class MainProgram {

    private static ArrayList<Pengguna> dataPengguna = new ArrayList<>(); 
    private static ArrayList<Order> dataOrder = new ArrayList<>(); 
    private static ArrayList<Makanan> dataMakanan = new ArrayList<>();
    private static boolean keluar = false;
    private static Scanner input = new Scanner(System.in);
    private static Pengguna penggunaAktif = null;
    private static Customer customerAktif = null;
    private static int idx = 6;

    // private static int idxOrder = 0; 
    
    public static void main(String[] args){    

        dataPengguna.add(new Driver(1, "Mat", "mat1", "123", "Mamatttt", "089723172", "pinggir jalan", "driver"));
        dataPengguna.add(new Driver(2, "urut", "kang", "456", "Kang urut", "0866666172", "rumah tetangga", "driver"));
        dataPengguna.add(new Driver(3, "liona", "yono", "abc", "liona Yono", "0101012223", "Mekarta de bes", "driver"));
        // Menambahkan data Pengguna(Pelanggan)
        dataPengguna.add(new Customer(4, "rocky", "tcino", "888", "tcino ganteng", "02229723172", "pinggir jalan", "customer"));
        dataPengguna.add(new Customer(5, "stepen", "dobleh", "111", "dobleh bingung", "04755172", "pinggir jalan", "customer"));
        dataPengguna.add(new Customer(6, "lone", "martin", "222", "martin lon", "081111172", "pinggir jalan", "customer"));
        
        while(keluar==false) {  
            menuUtama();
        }
    }    

    public final static void bersihkanLayar() {
        System.out.printf("\033[H\033[2J");
        System.out.flush();
    }

    private static void menuUtama(){  
        int akses;                
        bersihkanLayar();
        System.out.println("================ Selamat datang di OK JEK ================\n");
        System.out.println("1. Login");
        System.out.println("2. belum punya akun? Register!");
        
        akses = input.nextInt();
        input.nextLine();
        switch(akses){
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            default:
                System.out.println("Pilihan yang anda input tidak tersedia");
                break;   
        }                 
    }
    
    private static void login() {
        String username, password;                

        bersihkanLayar();
        System.out.print("\t\t LOGIN \n");
        System.out.print("Username : ");
        username = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();

        for(int i=0; i<dataPengguna.size(); i++) {
            if(dataPengguna.get(i).login(username, password)==true) {
                penggunaAktif = dataPengguna.get(i);                    
                break;
            }
            else{
                System.out.print("Akun ini tidak ditemukan");                    
            }               
        }
    
        if(penggunaAktif != null) {
            if(penggunaAktif.getStatus().equals("driver")) {
                menuDriver();
            } 
            else if(penggunaAktif.getStatus().equals("customer")) {
                customerAktif = (Customer) penggunaAktif;
                menuCustomer();
            }
        }
    }

    private static void register(){
        String email, username, password, nmPengguna, phone, alamat, status; 

        bersihkanLayar();
        System.out.print("\t\t REGISTER \n");
        System.out.print("email : ");
        email = input.nextLine();
        System.out.print("Username : ");
        username = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();
        System.out.print("Nama Pengguna : ");
        nmPengguna = input.nextLine();
        System.out.print("Phone : ");
        phone = input.nextLine();
        System.out.print("Alamat : ");
        alamat = input.nextLine();
        System.out.print("Status : ");
        status = input.nextLine(); 
        
        idx++;      
        dataPengguna.add(new Customer(idx, email, username, password, nmPengguna, phone, alamat, status));                                 
        login();
    }

    private static void menuCustomer(){
        boolean keluar = false;
        int pilihan;

        do{
            bersihkanLayar();
            System.out.println("Selamat datang " + penggunaAktif.getNamaPengguna() + " di Sistem OK JEK\n");
            System.out.println("1. My Account");
            System.out.println("2. Tampilkan Pelayanan");            
            System.out.println("3. Logout");
            System.out.print("Silakan masukan pilihan anda: ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    subMenuAkun();
                    break;
                case 2:
                    subMenuPelayanan();
                    break;
                case 3:                   
                    menuUtama();
                    keluar = true;
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia");
                    break;
            }

        } while(keluar==false);
    }
    
    private static void menuDriver() {
        boolean keluar = false;
        int pilihan;
        
        do{
            bersihkanLayar();
            System.out.println("Selamat datang " + penggunaAktif.getNamaPengguna() + " di Sistem Penyewaan DVD\n");            
            System.out.println("1. My Account");
            System.out.println("2. Check Orderan Masuk");
            System.out.println("3. Saldo");
            System.out.println("4. Pencapaian");
            System.out.println("5. Pendapatan");
            System.out.println("6. Riwayat Order");
            System.out.println("7. Logout");
            System.out.print("Silakan masukan pilihan anda: ");
            pilihan = input.nextInt();
            input.nextLine();
            
            switch (pilihan) {
                case 1:
                    subMenuAkun();
                    break;
                case 2:
                    checkOrderanMasuk();
                    break;
                case 3:
                    saldo();
                    break;
                case 4:
                    pencapaian();
                    break;
                case 5:
                    pendapatan();
                    break;
                case 6:
                    tampilkanRiwayatOrder(dataOrder);
                    break;
                case 7:                 
                    keluar = true;
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia");
                    break;
            }

        } while(keluar==false);
    }

    private static void subMenuAkun() {
        boolean keluar = false;
        int pilihan;

        do{
            bersihkanLayar();
            System.out.println("Silakan pilih aksi yang ingin anda lakukan\n");
            System.out.println("1. Tampil Data Profile");            
            System.out.println("2. Update Data Pengguna");
            System.out.println("3. Hapus Akun");
            System.out.println("4. Kembali menu sebelumnya");
            System.out.print("Silakan masukan pilihan anda: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    tampilDataAkun(dataPengguna, penggunaAktif);
                    break;                
                case 2:
                    updateAkun(penggunaAktif);
                    break;
                case 3:
                    hapusAkun();
                    break;
                case 4:
                    keluar = true;
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia");
                    break;
            }

        } while(keluar==false);
    }
    
    private static void subMenuPelayanan(){
        boolean keluar = false;
        int pilihan;

        do{
            bersihkanLayar();
            System.out.println("Silakan pilih aksi yang ingin anda lakukan\n");
            System.out.println("1. Pesan OK RIDE");
            System.out.println("2. Pesan OK FOOD");
            System.out.println("3. Tampilkan Riwayat Orderan");                        
            System.out.println("4. Hapus Riwayat Orderan");
            System.out.println("5. Kembali menu sebelumnya");
            System.out.print("Silakan masukan pilihan anda: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    pesanOkride(dataPengguna);
                    break;
                case 2:
                    pesanOkFood(dataOrder , dataMakanan);
                    break;
                case 3:
                    tampilkanRiwayatOrder(dataOrder);
                    break;
                case 4:
                    hapusRiwayatOrder(dataOrder);
                    break;
                case 5:                    
                    keluar = true;
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia");
                    break;
            }
        } while(keluar==false);              
    }    

    private static void tampilDataAkun(ArrayList<Pengguna> dataPengguna, Pengguna penggunaAktif){
        int i = penggunaAktif.getIdx();
        i--;
        bersihkanLayar();
        System.out.println("============== My Account ==============");                       
        System.out.println("Username     : " + dataPengguna.get(i).getUsername());
        System.out.println("Nama         : " + dataPengguna.get(i).getNamaPengguna());
        System.out.println("Email        : " + dataPengguna.get(i).getEmail());
        System.out.println("No Phone     : " + dataPengguna.get(i).getPhone());
        System.out.println("Status       :" + dataPengguna.get(i).getStatus());
        System.out.println("Alamat       :" + dataPengguna.get(i).getAlamat());                        

        System.out.println("press OK");                       
        input.nextLine();
    }

    private static void updateAkun(Pengguna penggunaAktif){
        bersihkanLayar();
        String email, username, password, namaPengguna, phone, alamat, status;  
        int i = penggunaAktif.getIdx();

        tampilDataAkun(dataPengguna, penggunaAktif);
        System.out.print("Memperbarui Data Akun Kamu"); 
        
        System.out.println("Lengkapi data baru pengguna berikut ini");
        System.out.print("Username     : ");
        username = input.nextLine();
        System.out.print("Nama Lengkap : ");
        namaPengguna = input.nextLine();
        System.out.print("Alamat Email : ");
        email = input.nextLine();
        System.out.print("Phone        : ");
        phone = input.nextLine();
        System.out.print("Password     : ");
        password = input.nextLine();
        System.out.print("Status       : ");
        status = input.nextLine();
        System.out.print("Alamat       : ");
        alamat = input.nextLine();

        i--;
        if(status.equals("customer")){
            dataPengguna.set(i, new Pengguna(penggunaAktif.getIdx(), email, username, password, namaPengguna, phone, alamat, status));                                                     
            menuCustomer();
        } else if(status.equals("driver")){
            dataPengguna.set(i, new Pengguna(penggunaAktif.getIdx(), email, username, password, namaPengguna, phone, alamat, status));                                                     
            menuDriver();
        } else {
            System.out.println("status yang anda masukan salah");
        }                
    }

    private static void hapusAkun(){
        String pilih;

        bersihkanLayar();        
        int nomor = penggunaAktif.getIdx();        
        nomor--; 
        tampilDataAkun(dataPengguna, penggunaAktif);
        System.out.print("Apkah Anda yakin ingin menghapus akun ini? (y/n) ");
        pilih = input.nextLine();
        
        if(pilih.equals("y")){
            dataPengguna.remove(nomor);
            System.out.println("Data pengguna dihapus"); 
            menuUtama();
        }
        else {
            input.nextLine();
        }                                                     
    }

    private static void pesanOkride(ArrayList<Pengguna> dataPengguna){
        String  posisi, tujuan, pilih;
        int no;            
        boolean cancel;  
        String driver;  
    
        System.out.println("Silahkan input data orderan\n");              
        System.out.print("Posisi Anda   : ");
        posisi = input.nextLine();
        System.out.print("Tempat Tujuan : ");
        tujuan = input.nextLine();                          
        input.nextLine();       

        System.out.println("Silahkan pilih driver: \n");
        for(int i=0; i<dataPengguna.size(); i++) {
            if(dataPengguna.get(i).getStatus().equals("driver")){
                System.out.println((i+1) + ". " + dataPengguna.get(i).getNamaPengguna());
            }
        }
        no = input.nextInt();
        no--;                
        driver = dataPengguna.get(no).getUsername();
                        
        input.nextLine(); 
        System.out.print("Canceled order? (y/n): ");
        pilih = input.nextLine();
        
        input.nextLine();
        if(pilih.equals("y") || pilih.equals("ya")){ 
            cancel = true;
            System.out.println("Anda telah membatalkan orderan anda");             
        }
        else{            
            cancel = false;
            System.out.println("Order OK RIDE anda sudah diproses\n");        
        }

        dataOrder.add(new Order(customerAktif.getUsername(), driver, posisi, tujuan, cancel, null));                
        input.nextLine();       
    }

    private static void pesanOkFood(ArrayList<Order> dataOrder, ArrayList<Makanan> dataMakanan){
        String  nmMakanan, nmJenis, posisi, tujuan, keterangan, pilih;
        boolean selesai = false; 
        int jumlah, harga, id = 0, no;
        String driver;
        boolean cancel;             
                                        
        System.out.println("Silahkan input data orderan\n");
              
        System.out.print("Posisi Anda   : ");
        posisi = input.nextLine();
        System.out.print("Tempat Tujuan : ");
        tujuan = input.nextLine();
            
        input.nextLine();
        System.out.println("Input nama makanan yang ingin dipesan: ");  
        do{  
            System.out.print("Nama makanan                           : ");
            nmMakanan = input.nextLine();
            System.out.print("Jenis Makanan                          : ");
            nmJenis = input.nextLine();
            System.out.print("jumlah makanan                         : ");
            jumlah = input.nextInt();
            System.out.print("harga makanan /buah                    : ");
            harga = input.nextInt();
            System.out.print("Stock makanan (tersedia/tidak tersedia): ");
            keterangan = input.nextLine();  
            
            id = dataOrder.size() - 1;    
            Jenis jenis  = new Jenis(nmJenis, keterangan);
            dataMakanan.add(new Makanan(id, nmMakanan, jenis, jumlah, harga));            

            input.nextLine();
            System.out.print("selesai memilih? (y/n): ");
            pilih = input.nextLine();

            input.nextLine();

            if(pilih.equals("y")){
                selesai = true;
            }
            else{
                selesai = false;
            }
        }while(selesai == false);            

        System.out.println("Silahkan pilih driver: \n");
        for(int i=0; i<dataPengguna.size(); i++) {
            if(dataPengguna.get(i).getStatus().equals("driver")){
                System.out.println((i+1) + ". " + dataPengguna.get(i).getNamaPengguna());
            }
        }
        no = input.nextInt();
        no--;                
        driver = dataPengguna.get(no).getUsername();  
        
        input.nextLine(); 
        System.out.print("Canceled order? (y/n): ");
        pilih = input.nextLine();
        
        input.nextLine();
        if(pilih.equals("y") || pilih.equals("ya")){                         
            cancel = true;
            System.out.println("Anda telah membatalkan orderan anda");             
        }
        else{                                    
            cancel = false;
            System.out.println("Order OK RIDE anda sudah diproses\n");        
        }                
        
        dataOrder.add(new Order(customerAktif.getUsername(), driver, posisi, tujuan,cancel, dataMakanan));
        input.nextLine();         
    }

    private static void tampilkanRiwayatOrder(ArrayList<Order> dataOrder){               

        System.out.println(customerAktif.getNamaPengguna().toUpperCase());
        System.out.println("Data Orderan Anda\n"); 
                
        for(int i=0; i<dataOrder.size(); i++) {
            if(dataOrder.get(i).getCustomer() == customerAktif.getUsername()){
                System.out.println("NO Order: " + (i+1) + "\n");                            
                System.out.println("Nama Driver      : " + dataOrder.get(i).getDriver());
                System.out.println("Posisi Anda      : " + dataOrder.get(i).getPosisi());        
                System.out.println("Tempat dituju    : " + dataOrder.get(i).getTujuan());      
                
                if(dataOrder.get(i).getMakanan() == null){                 
                    System.out.println("Jarak tempuh     : " + dataOrder.get(i).getJarak() + " km");        
                    System.out.println("Waktu tempuh     : " + dataOrder.get(i).getWaktu() + " menit");        
                    System.out.println("Total Pembayaran : " + dataOrder.get(i).getBiaya("ride") + "\n");                                       
                }
                
                else{     
                    System.out.println("Berikut daftar makanan yang Anda pesan: ");                   
                    for(int j=0; j<dataOrder.get(i).getMakanan().size(); i++){
                        System.out.println("Nama makanan        :" + dataOrder.get(i).getMakanan().get(j).getNmMakanan());             
                        System.out.println("jumlah Makanan      :" + dataOrder.get(i).getMakanan().get(j).getJumlah());
                        System.out.println("harga makanan /buah :" + dataOrder.get(i).getMakanan().get(j).getHarga());             
                    }                                                
                    System.out.println("Total Pembayaran    :" + dataOrder.get(i).getBiaya("food") + "\n");
                }
                System.out.println("Canceled         : " + dataOrder.get(i).getCanceled());             
            }
            // else{
            //     System.out.println("Anda belum mempunyai data orderan");             
            // }
        }         
        input.nextLine();   
    }

    private static void hapusRiwayatOrder(ArrayList<Order> dataOrder){
        System.out.println("Hapus data Orderan\n");                
        dataOrder.removeAll(dataOrder);        

        input.nextLine(); 
        System.out.println("Data order anda telah dihapus\n");
        input.nextLine();        
    }

    private static void checkOrderanMasuk(){
        boolean keluar = false;
        int pilihan;

        do{
            bersihkanLayar();
            System.out.println("Silakan pilih aksi yang ingin anda lakukan\n");
            System.out.println("1. check OK RIDE");
            System.out.println("2. check OK FOOD");                                    
            System.out.println("3. Kembali menu sebelumnya");
            System.out.print("Silakan masukan pilihan anda: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    checkOkRide(dataOrder, dataPengguna);
                    break;
                case 2:
                    checkOkFood();
                    break;
                case 3:                                     
                    keluar = true;
                    break;
                default:
                    System.out.println("Pilihan yang anda input tidak tersedia");
                    break;
            }
        } while(keluar==false);              
    }

    private static void saldo(){

    }

    private static void pencapaian(){

    }

    private static void pendapatan(){

    }

    private static void checkOkRide(ArrayList<Order> dataOrder, ArrayList<Pengguna> dataPengguna){
        int no, random = (int) Math.random() * 10;
        String pilih;        
        String targetCustomer = "";
        
            System.out.println("GORIDE");
            System.out.println("Silahkan pilih driver berdasarkan id customer: \n");
            for(int i=0; i<dataOrder.size(); i++) {
                if(dataOrder.get(i).getDriver().equals(penggunaAktif.getUsername())){
                    System.out.println((i+1) + ". " + dataOrder.get(i).getCustomer());                             
                }
                else{
                    if(random < dataOrder.size()){
                        System.out.println(random + ". " + dataOrder.get(random).getCustomer());
                    }
                    else{
                        System.out.println("Tidak ada orderan masuk");
                    }
                }
            }

            System.out.println("\n");                  
            no = input.nextInt();         
            no--;
            
            for(int j=0; j<dataPengguna.size(); j++) {
                if(dataPengguna.get(j).getUsername().equals(dataOrder.get(no).getCustomer())){
                    targetCustomer = dataPengguna.get(j).getNamaPengguna();                               
                }            
            }

            System.out.println("============= Data customer " + targetCustomer + " =============");                          
            System.out.println("posisi Customer: " + dataOrder.get(no).getPosisi());                          
            System.out.println("Alamat dituju Customer: " + dataOrder.get(no).getTujuan());
            System.out.println("panjang rute: " + dataOrder.get(no).getJarak() + " km");
            System.out.println("Tarif: Rp " + dataOrder.get(no).getOngkir());
            
            input.nextLine(); 
            System.out.print("Terima orderan? (y/n): ");
            pilih = input.nextLine();            

            input.nextLine(); 
            if(pilih.equals("y")){                         
                System.out.println("Anda menerima orderan dari " + targetCustomer);                               
            }
            else {
                input.nextLine();                
            }          
        input.nextLine(); 
    }
    
    private static void checkOkFood(){

    }
}