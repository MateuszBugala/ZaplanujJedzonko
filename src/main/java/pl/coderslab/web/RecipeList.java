package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.TimeComparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/app/recipe/list/")
public class RecipeList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List <Recipe> recipes = RecipeDao.findAll();

        Collections.sort(recipes, new TimeComparator());

        request.setAttribute("recipes", recipes);
        getServletContext().getRequestDispatcher("/app/recipe/list.jsp").forward(request, response);

    }


}
