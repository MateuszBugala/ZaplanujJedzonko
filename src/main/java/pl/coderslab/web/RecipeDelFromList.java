package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/delete")
public class RecipeDelFromList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        boolean used = recipeUsedInPlan(recipeId);

        if (!used) {
            RecipeDao.delete(recipeId);
            response.sendRedirect("/app/recipe/list/?deleted=true");

        } else {
            response.sendRedirect("/app/recipe/list/?blocked=true");

        }
    }

    public static boolean recipeUsedInPlan(int recipeId) {
        List<Integer> list = RecipeDao.selectRecipeIdFromPlan();
        for (Integer el : list) {
            if (el == recipeId) {
                return true;
            }
        }
        return false;
    }
}
