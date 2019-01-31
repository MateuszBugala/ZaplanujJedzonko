package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/plan/delete")
public class RecipeDelFromRecipePlan extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //na chwilę obecną nie mamy jeszcze formularza gdzie można byłoby wskazać przepis do usunięcia,więc nie możemy odebrac poniższych parametrów.
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        int recipePlanId = Integer.parseInt(request.getParameter("recipePlanId"));

        String confirm = request.getParameter("confirm");
//        dane używane do testowania
//        int recipeId =9;
//        int recipePlanId =1;

        if ("ok".equals(confirm)){
            PlanDao.deleteRecipeFromRecipePlan(recipeId,recipePlanId);
            //strona planDetails na chwilę obecną nie jest utworzona"
            response.sendRedirect("/planDetails.jsp");
        }else{
            response.sendRedirect("/planDetails.jsp");
        }





    }
}
