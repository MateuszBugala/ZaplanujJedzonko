package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;

import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan (name, description,created,admin_id) VALUES (?,?,NOW(),?)";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ? WHERE id = ?";
    private static final String SHOW_PLAN_NUMBERS = "select count(*) from plan where admin_id=?;";
    private static final String SHOW_RECENT_PLAN ="SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) ORDER by day_name.order, recipe_plan.order";

    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setInt(3, plan.getAdmins().getId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Plan readById(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY);
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getTimestamp("created"));
                    Admins admin = AdminDao.read(resultSet.getInt("admin_id"));
                    plan.setAdmins(admin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY);) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY);) {
            statement.setInt(3, plan.getId());
            statement.setString(2, plan.getDescription());
            statement.setString(1, plan.getName());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getTimestamp("created"));
                Admins admin = AdminDao.read(resultSet.getInt("admin_id"));
                planToAdd.setAdmins(admin);
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }
    public static int showPlanNumbers(int adminId) {
        int planNumbers = 0;
        if (adminId == 0 || adminId < 0) {
            System.out.println("Niepoprawne id użytkownika");
        } else {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SHOW_PLAN_NUMBERS);) {
                statement.setInt(1, adminId);
                ResultSet set = statement.executeQuery();
                while(set.next()) {
                    planNumbers = set.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return planNumbers;
    }

    public static List<String> showRecentPlan(int adminId) {
        List <String> list = new ArrayList<>();
        if (adminId == 0 || adminId < 0) {
            System.out.println("Niepoprawne id użytkownika");
        } else {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SHOW_RECENT_PLAN);) {
                statement.setInt(1, adminId);
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    list.add(set.getString(1));
                    list.add(set.getString(2));
                    list.add(set.getString(3));
                    list.add(set.getString(4));
                }

            } catch (SQLException e) {
                System.out.println("Problem z bazą danych");

            }
        }
return list;
    }
}
