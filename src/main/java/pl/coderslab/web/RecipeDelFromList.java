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

@WebServlet("/RecipeDelFromList")
public class RecipeDelFromList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //na chwilę obecną nie mamy jeszcze formularza gdzie można byłoby wskazać przepis do usunięcia,więc nie możemy odebrac poniższych parametrów.
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));


        String confirm = request.getParameter("confirm");
//        dane używane do testowania
//        int recipeId = 3;

        boolean checked = checkRecipePlan(recipeId);

        if ("ok".equals(confirm) && !checked) {
            RecipeDao.delete(recipeId);
            //strona planDetails na chwilę obecną nie jest utworzona"
            response.sendRedirect("/planDetails.jsp");
        } else {
            response.sendRedirect("/planDetails.jsp");
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
