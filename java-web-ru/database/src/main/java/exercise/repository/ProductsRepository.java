package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product prod) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
                var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (prod.getId() == null) {
                var title = prod.getTitle();
                var price = prod.getPrice();
                preparedStatement.setString(1, title);
                preparedStatement.setInt(2, price);
                preparedStatement.executeUpdate();

                var generatedKeys = preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    prod.setId(generatedKeys.getLong("id"));
                } else {
                    throw new SQLException("DB have not returned an id after saving an entity");
                }
            }
            // else {
            //    update into this method
            //}
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");
                var product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }

    }

    public static List<Product> getEntities() throws SQLException {

        String sql = "SELECT * FROM products";
        try (var conn = dataSource.getConnection();
                var preparedStatement = conn.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");
                var prod = new Product(title, price);
                prod.setId(resultSet.getLong(1));
                products.add(prod);
            }
            return products;
        }
    }
    // END
}
