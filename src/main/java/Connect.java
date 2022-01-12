


import lombok.extern.slf4j.Slf4j;

import java.sql.*;


public class Connect {

    @Slf4j
    public static class Main {

        private String url = "jdbc:postgresql://localhost/test";
        private String user = "postgres";
        private String password = "admin";


        public Connection connect() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }

        public void getContacts() {

            String SQL = "SELECT * FROM contacts";


            try (Connection conn = this.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL);) {

                while (rs.next()) {
                    //Display values
                    log.info(", ID: " + rs.getInt("id_con"));
                    log.info(", First Name: " + rs.getString("first_name"));
                    log.info(", Last Name: " + rs.getString("last_name"));
                    log.info(", Email: " + rs.getString("email"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        public long insertCon(Contacts contacts) {

            String SQL = "INSERT INTO contacts(first_name,last_name,email) "
                    + "VALUES(?,?,?)";

            long id = 0;
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(SQL,
                         Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, contacts.getFirst_name());
                pstmt.setString(2, contacts.getLast_name());
                pstmt.setString(3, contacts.getEmail());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getLong(1);
                        }
                    } catch (SQLException ex) {
                        log.info(ex.getMessage());
                    }
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            return id;
        }

        public int deleteContacts(int id_con) {
            String SQL = "DELETE FROM contacts WHERE id_con =?";
            int affectedrows = 0;

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(SQL)) {

                pstmt.setInt(1, id_con);

                affectedrows = pstmt.executeUpdate();

            } catch (SQLException ex) {
                log.info(ex.getMessage());
            }
            return affectedrows;
        }


        public static void main(String[] args) {
            Thread thread = new Thread(() -> {
                Main main = new Main();
                while (true) {
                    Contacts contacts = new Contacts(1, "Adam", "Słony", "@wp.pl");
                    long id = main.insertCon(contacts);
                    main.getContacts();
                }


            });
            Thread thread2 = new Thread(() -> {
                Main main = new Main();
                while (true) {
                    Contacts contacts = new Contacts(1, "Adam", "Słony", "@wp.pl");
                    long id = main.insertCon(contacts);

                }


            });

            Thread thread3 = new Thread(() -> {
                Main main = new Main();
                while (true) {
                    Contacts contacts = new Contacts(1, "Adam", "Słony", "@wp.pl");
                    long id = main.insertCon(contacts);

                }


            });
            thread.start();
            thread2.start();
            thread3.start();
        }
    }
}

