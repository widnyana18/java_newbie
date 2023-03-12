public class Customer extends Pengguna{
    private int orderCount;    

    public Customer(int idx, String email, String username, String password, String namaPengguna, String phone, String alamat, String status) {
        super(idx, email, username, password, namaPengguna, phone, alamat, status);        
        this.orderCount = 0;
    }    
    
    public int getOrderCount() {
        return this.orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }         
}     