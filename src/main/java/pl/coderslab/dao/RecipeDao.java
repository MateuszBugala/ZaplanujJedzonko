package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description, created, updated, preparation_time, preparation, admin_id) VALUES (?,?,?,NOW(),NOW(),?,?,?)";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, updated = NOW(), preparation_time = ?, preparation = ?, admin_id =? WHERE	id = ?";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe";



    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getIngredients());
            preparedStatement.setString(3, recipe.getDescription());
            preparedStatement.setInt(4, recipe.getPreparationTime());
            preparedStatement.setString(5, recipe.getPreparation());
            preparedStatement.setInt(6, recipe.getAdmins().getId()); /*sprawdzić po utworzeniu klasy Admin czy prawidłowe*/
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getTimestamp("created"));
                    recipe.setUpdated(resultSet.getTimestamp("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    Admins admins = Admins.read(resultSet.getInt("admin_id")); /*sprawdzić po utworzeniu klasy Admin czy prawidłowe*/
                    recipe.setAdmins(admins);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }


    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            preparedStatement.setInt(7, recipe.getId());
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getIngredients());
            preparedStatement.setString(3, recipe.getDescription());
            preparedStatement.setInt(4, recipe.getPreparationTime());
            preparedStatement.setString(5, recipe.getPreparation());
            preparedStatement.setInt(6, recipe.getAdmins().getId()); /*sprawdzić po utworzeniu klasy Admin czy prawidłowe*/
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();
// czy tutaj nie powinno być coś jakby this.id = 0;
            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getTimestamp("created"));
                recipe.setUpdated(resultSet.getTimestamp("updated"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                Admins admins = Admins.read(resultSet.getInt("admin_id")); /*sprawdzić po utworzeniu klasy Admin czy prawidłowe*/
                recipe.setAdmins(admins);

                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

//    private int id;
//    private String name;
//    private String ingredients;
//    private String description;
//    private Timestamp created;
//    private Timestamp updated;
//    private int preparationTime;
//    private String preparation;
//    private Admins admins;

}
