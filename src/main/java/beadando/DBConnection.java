package beadando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static boolean initialized = false;
    private static DBConnection dbc;

    private Connection connection;

    public DBConnection() {
        Initialize();
    }

    public static DBConnection getInstance() {
        if (!initialized)
            dbc = new DBConnection();
        return dbc;
    }

    private boolean Initialize() {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Properties properties = new Properties();
            properties.put("user", "SYSDBA");
            properties.put("password", "admin");
            properties.put("useUnicode", "yes");
            properties.put("characterEncoding", "utf-8");
            properties.put("charSet", "utf-8");
            connection = DriverManager
                    .getConnection(
                            "jdbc:firebirdsql://localhost:3050/G:/db/db.FDB", properties);
        } catch (Exception e) {

        }
        initialized = connection != null;
        return initialized;
    }
}
