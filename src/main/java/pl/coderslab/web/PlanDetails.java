package pl.coderslab.web;


import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/app/plan/details")
public class PlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("planId");
        int planIdInt = Integer.parseInt(planId);

        List<RecipePlan> planDetails = PlanDao.findRecipesByPlanId(planIdInt);
        List<String> days = showUniqueDays(planIdInt);
        Plan plan = PlanDao.readById(planIdInt);

        if (planDetails.size() != 0) {
            request.setAttribute("planDetails", planDetails);
        }

        request.setAttribute("days", days);
        request.setAttribute("plan", plan);

        getServletContext().getRequestDispatcher("/app/plan/details.jsp").forward(request, response);
    }


    public static List<String> showUniqueDays(int planId) {
        List<String> days = new ArrayList<>();
        if (planId == 0 || planId < 0) {
            System.out.println("Niepoprawne id użytkownika");
        } else {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT day_name.name\n" +
                         "FROM `recipe_plan`\n" +
                         "JOIN day_name on day_name.id=day_name_id\n" +
                         "WHERE plan_id=?\n" +
                         "group by day_name.name, day_name.order\n" +
                         "order by day_name.order")) {
                statement.setInt(1, planId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    days.add(rs.getString(1));
                }

            } catch (SQLException e) {
                System.out.println("Problem z bazą danych");

            }

        }
        return days;
    }


}
