import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class demo {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/minions_db?serverTimezone=UTC";

    private static Connection connection;
    private static PreparedStatement statement;
    private static String query;
    private static BufferedReader reader;
    public static void main(String[] args) throws IOException, SQLException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        connection = DriverManager.getConnection(CONNECTION_STRING, props);

        //2. Get Villains’ Names
        getVillainsNamesAndCountOfMinions();

        //3. Get Minion Names
        //getMinionsNameWithVillainId();

        //4. Add Minion
       //addMinionToDB();

        //5. Change Town Names Casing
        //changeTownName();

        //6. Remove Villain
        //removeVillainById();

        //7. Print All Minion Names
      //printAllMinionsNames();

        //8.Increase Minions Age
        //increaseMinionsAge();

        //9. Increase Age Stored Procedure
        //increaseAgeStoredProcedure();

    }

    private static void removeVillainById() throws IOException, SQLException {
        System.out.print("Enter Villain ID: ");
        int villainID = Integer.parseInt(reader.readLine());
        if (!checkEntityExists(villainID, "villains")){
            System.out.println("No such villain was found");
        } else {
            String villainName = getEntityNameById(villainID, "villains");
            int countRows = getCountAffectedRowsInMinionsVillains(villainID);
            releaseMinions(villainID);
            deleteVillains(villainID);
            System.out.println(String.format("%s was deleted", villainName));
            System.out.println(String.format("%d minions released", countRows));
        }

    }

    private static void deleteVillains(int villainID) throws SQLException {
        query = "DELETE FROM villains where id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,villainID);
        statement.execute();
    }

    private static void releaseMinions(int villainID) throws SQLException {
        query = "DELETE FROM minions_villains where villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,villainID);
        statement.execute();
    }

    private static int getCountAffectedRowsInMinionsVillains(int villainID) throws SQLException {
        query = "SELECT count(*) as count from minions_villains where villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,villainID);
        ResultSet resultSet = statement.executeQuery();

        resultSet.next();
        return resultSet.getInt("count");
    }

    private static void increaseAgeStoredProcedure() throws IOException, SQLException {
    System.out.print("Enter minion ID: ");
    int minionId = Integer.parseInt(reader.readLine());

    query = "CALL usp_get_older(?)";
    CallableStatement callableStatement = connection.prepareCall(query);
    callableStatement.setInt(1, minionId);
    callableStatement.execute();
        printMinionAndAgeById(minionId);
    }

    private static void printMinionAndAgeById(int minionId) throws SQLException {
        query = "SELECT name, age from minions where id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,minionId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        System.out.println(String.format("%s %d", resultSet.getString("name"), resultSet.getInt("age")));

    }

    private static void increaseMinionsAge() throws IOException, SQLException {
        System.out.print("Enter ID's: ");
        String[] inputId = reader.readLine().split("\\s+");
        String ids = String.join(", ", inputId);
        increaseAgeAndChangeNamesToLowerCase(ids);
        printAllMinionsNamesAndAge();

    }

    private static void printAllMinionsNamesAndAge() throws SQLException {
        query = "SELECT name, age from minions";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println(String.format("%s %d", resultSet.getString("name"),
                    resultSet.getInt("age")));
        }
    }

    private static void increaseAgeAndChangeNamesToLowerCase(String ids) throws SQLException {
        query = String.format("UPDATE minions SET name = LCASE(name), age = age + 1 where id in (%s)", ids);
        statement = connection.prepareStatement(query);
        statement.execute();
    }

    private static void printAllMinionsNames() throws SQLException {
        List<String> minionsBegin = new ArrayList<>();
        List<String> minionsEnd = new ArrayList<>();
        query = "SELECT name from minions";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            minionsBegin.add(resultSet.getString("name"));
        }
        while (minionsBegin.size() > 0){
            minionsEnd.add(minionsBegin.get(0));
            minionsBegin.remove(0);
            if (minionsBegin.size() > 0){
                minionsEnd.add(minionsBegin.get(minionsBegin.size() - 1));
                minionsBegin.remove(minionsBegin.get(minionsBegin.size() - 1));
            }
        }

        System.out.println(String.join("\n", minionsEnd));
    }

    private static void changeTownName() throws IOException, SQLException {
        System.out.print("Enter country name: ");
        String country = reader.readLine();
            changeTownNamesToUpperCase(country);
            printAffectedTowns(country);

    }

    private static void printAffectedTowns(String country) throws SQLException {
        query = "SELECT name FROM TOWNS WHERE country = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1 , country);
        ResultSet resultSet = statement.executeQuery();
        List<String> result = new ArrayList<>();
        int countTowns = 0;
        while (resultSet.next()){
            countTowns++;
            result.add(resultSet.getString("name"));
        }
        if (countTowns == 0){
            System.out.println("No town names were affected");
        } else {
            System.out.printf("%s town names were affected.\n", countTowns);
            System.out.println(result);
        }
    }

    private static void addMinionToDB() throws IOException, SQLException {
        String[] minionInput = reader.readLine().split("\\s+");
        String minionName = minionInput[1];
        int minionAge = Integer.parseInt(minionInput[2]);
        String minionTown = minionInput[3];

        String[] villainInput = reader.readLine().split("\\s+");
        String villainName = villainInput[1];
        if (!CheckIfEntityExistsByName(minionTown, "towns")){
            insertEntityInTowns(minionTown);
            System.out.println(String.format("Town %s was added to the database", minionTown));
        };
        if(!CheckIfEntityExistsByName(villainName, "villains")) {
            AddEntityInVillains(villainName, "villains");
            System.out.println(String.format("Villain %s was added to the database.", villainName));
        }

        int townId = getId(minionTown, "towns");
        AddEntityInMinions(minionName, minionAge, townId);
        int minionId = getId(minionName, "minions");
        int villainId = getId(villainName, "villains");
        minions_villains(minionId, villainId);
        System.out.println(String.format("Successfully added %s to be minion of %s", minionName, villainName));

    }

    private static void changeTownNamesToUpperCase(String country) throws SQLException {
        query = "update towns set name = UPPER(name) where country = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, country);
        statement.execute();

    }

    private static boolean checkIfCountryExists(String countryName) throws SQLException {
        query = "SELECT * FROM towns WHERE country = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, countryName);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static int getId(String entityName, String tableName) throws SQLException {
        query = "SELECT id from " + tableName + " where name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1 , entityName);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private static int getCountRowsAffected(String country) throws SQLException {
        query = "SELECT COUNT(*) FROM TOWNS WHERE country = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1 , country);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return  resultSet.getInt(1);
    }

    private static void AddEntityInMinions(String minionName, int minionAge, int townId) throws SQLException {
        query = "INSERT INTO minions (name, age, town_id) VALUES(?, ? , ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1 , minionName);
        statement.setInt(2 , minionAge);
        statement.setInt(3 , townId);
        statement.execute();
    }

    private static void insertEntityInTowns(String minionTown) throws SQLException {
        query = "INSERT INTO towns (name, country) values(?, NULL)";
        statement = connection.prepareStatement(query);
        statement.setString(1 , minionTown);
        statement.execute();
    }

    private static void minions_villains(int minionId, int villainId) throws SQLException {
        query = "INSERT INTO minions_villains(minion_id, villain_id)" +
                "VALUES(? , ?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1 , minionId);
        statement.setInt(2 , villainId);
        statement.execute();

    }

    private static boolean CheckIfEntityExistsByName(String entityName, String tableName) throws SQLException {
        query = "SELECT * FROM " + tableName + " WHERE name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, entityName);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void AddEntityInVillains(String name, String tableName) throws SQLException {
            query = "INSERT INTO " + tableName + " (`name`, evilness_factor) VALUES(?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1 , name);
            statement.setString(2 , "evil");
            statement.execute();
    }

    private static void getMinionsNameWithVillainId() {
        try {
            System.out.print("Enter villain id: ");
            int villain_id = Integer.parseInt(reader.readLine());
            if (!checkEntityExists(villain_id, "villains")){
                System.out.println(String.format("No villain with ID %d exists in the database.", villain_id));
                return;
            }

            System.out.printf("Villain: %s%n", getEntityNameById(villain_id, "villains"));
            getMinionsNameAndAgeByVillainId(villain_id);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getMinionsNameAndAgeByVillainId(int villain_id) throws SQLException {
        query = "SELECT m.name, m.age FROM minions AS m\n" +
                "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,villain_id);
        ResultSet resultSet = statement.executeQuery();
        int count = 1;
        while(resultSet.next()){
            System.out.println(String.format("%d. %s %d", count++, resultSet.getString("name"),
                    resultSet.getInt("age")));
        }

    }

    private static String getEntityNameById(int villain_id, String tableName) throws SQLException {
        query = "SELECT name FROM " + tableName + " WHERE id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villain_id);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;

    }

    private static boolean checkEntityExists(int villain_id, String villains) throws SQLException {
        query = "SELECT * FROM " + villains + " WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1,villain_id);
           ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getVillainsNamesAndCountOfMinions() {
        query = "SELECT v.name, count(mv.minion_id) as countMinions FROM villains AS v\n" +
                "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                "GROUP BY villain_id\n" +
                "HAVING countMinions > 15\n" +
                "ORDER BY countMinions DESC;";

        try {
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(String.format("%s %d", resultSet.getString("name"), resultSet.getInt("countMinions")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
