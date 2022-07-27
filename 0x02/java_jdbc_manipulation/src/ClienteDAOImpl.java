import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO, AutoCloseable {
    private static Connection connection;

    public ClienteDAOImpl() {
    }

    @Override
    public Connection connect(String urlConexao) {
        try {
            return DriverManager.getConnection(urlConexao);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void createTable(String urlConexao) {
        try {
            connection = connect(urlConexao);
            Statement statement = connection.createStatement();
            statement.executeUpdate(createClientTable());
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        try {
            connection = connect(url_conexao);
            String sql = "INSERT INTO Cliente (nome, idade, cpf, rg) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        try {
            connection = connect(urlConexao);
            String sql = "SELECT * FROM Cliente";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Cliente client = resultSetToEntity(resultSet);
                System.out.println(client);
            }
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        try {
            connection = connect(urlConexao);
            String sql = "UPDATE Cliente SET nome = ?, idade = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setInt(2, idade);

            stmt.setInt(3, id);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        try {
            connection = connect(urlConexao);
            String sql = "DELETE FROM Cliente WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            System.out.println("Connection to SQLite has been closed.");
        }
    }

    private String createClientTable() {
        return "CREATE TABLE Cliente (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "nome TEXT NOT NULL, \n" +
                "idade INTEGER NOT NULL, \n" +
                "cpf TEXT NOT NULL, \n" +
                "rg TEXT NOT NULL \n" +
                "); \n";
    }

    private Cliente resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Cliente(
                resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getInt("idade"),
                resultSet.getString("cpf"),
                resultSet.getString("rg")
        );
    }
}
