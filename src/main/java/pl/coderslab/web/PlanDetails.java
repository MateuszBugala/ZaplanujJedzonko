package pl.coderslab.web;


import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecentPlan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet("/app/plan/details")
public class PlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pobranie id planu planu
        String planId = request.getParameter("planid");
        int id = Integer.parseInt(planId);

        //pobranie danych planu
        Plan plan = PlanDao.readById(id);
        //przekazanie obiektu plan do formularza
        request.setAttribute("plan", plan);

        //pobranie przepisów dla planu
        List<RecentPlan> foundRecipes = PlanDao.findRecipesByPlanId(id);

        //przekazanie obiektu przepisów do formularza
        request.setAttribute("foundRecipes", foundRecipes);

        //pobranie nazw dni tygodnia
        List<DayName> days = DayNameDao.findAll();

        //przekazanie dni tygodnia
        request.setAttribute("days", days);

        getServletContext().getRequestDispatcher("/app/plan/details.jsp").forward(request, response);
    }
}
