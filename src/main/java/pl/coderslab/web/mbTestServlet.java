package pl.coderslab.web;

import com.mysql.jdbc.StringUtils;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mbTestServlet")
public class mbTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int c = RecipeDao.countRecipeById(1);
        String cStr = Integer.toString(c);
        response.getWriter().append("recipe count ").append(cStr);

    }
}
