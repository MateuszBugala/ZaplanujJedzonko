package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.DayName;
import pl.coderslab.model.RecentPlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/app/dashboard/")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sess = request.getSession();
        //Admins admin = (Admins)sess.getAttribute("currentUser");
        Admins admin = new Admins();
        admin.setId(1);

        int numberOfRecipes = RecipeDao.countRecipeById(admin.getId());
        int numberOfPlans = PlanDao.showPlanNumbers(admin.getId());

        List<RecentPlan> recentPlan = PlanDao.showRecentPlan(admin.getId());
        List<DayName> days = DayNameDao.findAll();


        request.setAttribute("recipeNumber", numberOfRecipes);
        request.setAttribute("planNumber", numberOfPlans);
        request.setAttribute("recentPlan", recentPlan);
        request.setAttribute("days", days);


        getServletContext().getRequestDispatcher("/app/dashboard.jsp").forward(request,response);
    }
}
