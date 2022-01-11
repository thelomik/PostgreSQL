public class Contacts {

    private int id_con;
    private String first_name;



    private String last_name;
    private String email;

    public Contacts(int id_con, String first_name, String last_name, String email) {
        this.id_con = id_con;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Contacts(){

    }
    public int getId_con() {
        return id_con;
    }

    public void setId_con(int id) {
        this.id_con = id_con;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
