package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/edit")
public class RecipeEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String recipeName = request.getParameter("recipeName");
        String recipeDescription = request.getParameter("recipeDescription");
        int preparationTime = Integer.parseInt(request.getParameter("recipePreparationTime"));
        String recipePreparation =request.getParameter("recipePreparation");
        String recipeIngredients = request.getParameter("recipeIngredients");
        int recipeId = Integer.valueOf(request.getParameter("recipeId"));

        Recipe editedRecipe = RecipeDao.read(recipeId);
        editedRecipe.setName(recipeName);
        editedRecipe.setDescription(recipeDescription);
        editedRecipe.setPreparationTime(preparationTime);
        editedRecipe.setPreparation(recipePreparation);
        editedRecipe.setIngredients(recipeIngredients);

        RecipeDao.update(editedRecipe);

        response.sendRedirect("/app/recipe/list/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int recipId = Integer.parseInt(request.getParameter("recipeId"));
        Recipe recipeFromDB = RecipeDao.read(recipId);

        request.setAttribute("recipeFromDB", recipeFromDB);

        getServletContext().getRequestDispatcher("/app/recipe/edit.jsp").forward(request, response);
    }
}
