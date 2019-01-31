package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/RecipeDelFromRecipePlanConfirm")
public class RecipeDelFromRecipePlanConfirm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setCharacterEncoding("UTF-8");
        response.getWriter().append("<!DOCTYPE html>");
        response.getWriter().append("<html lang=\"pl\">");
        response.getWriter().append("<meta charset='utf-8'>");
        response.getWriter().append("<p> Czy na pewno chcesz usunąć przepis z planu? <p>");
        response.getWriter().append("<br>");
        response.getWriter().append("<a href='/app/recipe/plan/delete?confirm=ok'> OK </a> &nbsp; &nbsp;");
        response.getWriter().append("<a href='/app/recipe/plan/delete?confirm=no'> Anuluj </a> &nbsp; &nbsp;");









    }
}
