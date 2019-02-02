package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet("/app/recipe/details")
public class RecipeDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeId = request.getParameter("recipeId");

        Recipe recipe = RecipeDao.read(Integer.parseInt(recipeId));
        String ingredients = recipe.getIngredients();
        String preparation = recipe.getPreparation();

        StringTokenizer ingredientsToken = new StringTokenizer(ingredients,"\n");
        List<String> ingredientsDisplay = new ArrayList<>();
        while (ingredientsToken.hasMoreTokens()) {
            ingredientsDisplay.add(ingredientsToken.nextToken());
        }

        StringTokenizer preparationToken = new StringTokenizer(preparation,"\n");
        List<String> preparationDisplay = new ArrayList<>();
        while (preparationToken.hasMoreTokens()) {
            preparationDisplay.add(preparationToken.nextToken());
        }

        request.setAttribute("recipe", recipe);
        request.setAttribute("ingredients", ingredientsDisplay);
        request.setAttribute("preparation", preparationDisplay);

        getServletContext().getRequestDispatcher("/app/recipe/details.jsp").forward(request, response);
    }
}
