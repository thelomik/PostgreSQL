import lombok.Data;

@Data
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


}
