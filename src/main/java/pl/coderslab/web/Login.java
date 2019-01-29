package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sess = request.getSession();

        String emailCandidate = request.getParameter("email");
        String passwordCandidate = request.getParameter("password");

        List<Admins> adminsList = AdminDao.findAll();
        for (Admins admin : adminsList) {
            if (emailCandidate.equals(admin.getEmail())) {
                if (BCrypt.checkpw(passwordCandidate,admin.getPassword())) {
//                    dodanie użytkownika do sesji:
                    sess.setAttribute("currentUser",admin);
//                    przekierowanie na stronę główną:
                    response.sendRedirect("/");
                    return;
                }
            }
        }
        request.setAttribute("error", "error");
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
