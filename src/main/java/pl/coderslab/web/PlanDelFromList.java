package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/delete")
public class PlanDelFromList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("planId"));

        if (planNotEmpty(planId)) {
            response.sendRedirect("/app/plan/list/?blocked=true");
        } else {
            PlanDao.delete(planId);
            response.sendRedirect("/app/plan/list/?deleted=true");
        }
    }

    public static boolean planNotEmpty(int planId) {
        List<RecipePlan> list = PlanDao.findRecipesByPlanId(planId);
        if (list.size() == 0) {
            return false;
        }
        return true;
    }

}
