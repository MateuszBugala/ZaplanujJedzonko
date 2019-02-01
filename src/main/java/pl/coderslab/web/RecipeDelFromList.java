package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/RecipeDelFromList")
public class RecipeDelFromList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeId = Integer.parseInt(request.getParameter("recipeId"));


        boolean checked = checkRecipePlan(recipeId);

        if (!checked) {
            RecipeDao.delete(recipeId);
            String warning = "Przepis został usunięty";
            request.setAttribute("warning", warning);
            getServletContext().getRequestDispatcher("/app/recipe/list/").forward(request, response);
        } else {
            String warning = "Nie można usunąć przepisu ponieważ jest dodany do planu";
            request.setAttribute("warning", warning);
            getServletContext().getRequestDispatcher("/app/recipe/list/").forward(request, response);

        }
    }

    public static boolean checkRecipePlan(int recipeId) {
        List<Integer> list = RecipeDao.selectRecipeIdFromPlan();
        for (Integer el : list) {
            if (el == recipeId) {
                return true;
            }
        }
        return false;
    }
}
