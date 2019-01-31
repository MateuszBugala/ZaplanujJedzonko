package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/plan/delete")
public class RecipeDelFromPlan extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //parametry trzena podać w URL servletu RecipeDElFromPlan
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        int planId = Integer.parseInt(request.getParameter("planId"));


        PlanDao.deleteRecipeFromPlan(planId, recipeId);
//ta stronka chyba jeszcze nie działa
        response.sendRedirect("http://localhost:8080/app/plan/details");


    }
}
