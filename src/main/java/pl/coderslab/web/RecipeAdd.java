package pl.coderslab.web;

import com.mysql.jdbc.StringUtils;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/recipe/add")
public class RecipeAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String preparationTime = request.getParameter("preparation_time");
        if (StringUtils.isNullOrEmpty(preparationTime) || !StringUtils.isStrictlyNumeric(preparationTime)) {
            request.setAttribute("preparationTimeError", "preparationTimeError");
            doGet(request, response);
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTimeInt = Integer.parseInt(preparationTime);
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        HttpSession sess = request.getSession();
        Admins admin = (Admins)sess.getAttribute("currentUser");

        Recipe recipe = new Recipe(name,ingredients,description,preparationTimeInt,preparation,admin);

        RecipeDao.create(recipe);

        response.sendRedirect("/app/recipe/list/");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        getServletContext().getRequestDispatcher("/app/recipe/add.jsp").forward(request, response);
    }
}
