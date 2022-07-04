package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");
        
        try {
            if(action != null) {
                String email = request.getParameter("email");
                
                if(action.equals("edit")) {
                    request.setAttribute("edit_email", email);
                }
                else if(action.equals("delete")) {
                    us.delete(email);
                    request.setAttribute("message", "User " + request.getParameter("firstName") + " deleted");
                }
            }
            
            List<User> users = us.getAll();
            request.setAttribute("users", users);
            
            List<Role> roles = rs.getAll();
            request.setAttribute("roles", roles);
        }
        catch(Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("action", null);
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        RoleService rs = new RoleService();
        
        String action = request.getParameter("action");
        String email = null;
        String roleName = null;
        int roleID = 0;
        
        try {
            switch(action) {
                case "add":
                    roleName = request.getParameter("addRole");
                    
                    switch(roleName) {
                        case "system admin":
                            roleID = 1;
                            break;
                            
                        case "regular user":
                            roleID = 2;
                            break;
                            
                        case "company admin":
                            roleID = 3;
                            break;
                    }
                    
                    email = request.getParameter("addEmail");
                    
                    us.insert(email, 
                            Boolean.parseBoolean(request.getParameter("addActive")), 
                            request.getParameter("addFirstName"), 
                            request.getParameter("addLastName"), 
                            request.getParameter("addPassword"), 
                            roleID);
                    
                    request.setAttribute("message", "User " + request.getParameter("addFirstName") + " added");
                    break;
                    
                case "edit":
                    roleName = request.getParameter("editEmail");
                    
                    switch(roleName) {
                        case "system admin":
                            roleID = 1;
                            break;
                            
                        case "regular user":
                            roleID = 2;
                            break;
                        
                        case "company admin":
                            roleID = 3;
                            break;
                    }
                    
                    email = request.getParameter("editEmail");
                    
                    us.update(email, 
                            Boolean.parseBoolean(request.getParameter("editActive")), 
                            request.getParameter("editFirstName"),
                            request.getParameter("editLastName"), 
                            request.getParameter("editPassword"), 
                            roleID);
                    
                    request.setAttribute("message", "User " + request.getParameter("addFirstName") + " editied");
                    break;
            }
            
            List<User> users = us.getAll();
            request.setAttribute("users", users);
            
            List<Role> roles = rs.getAll();
            request.setAttribute("roles", roles);
        }
        catch(Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("action", null);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}