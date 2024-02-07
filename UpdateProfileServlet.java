package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.DAO.UserDAOimpl;
import com.DB.DBconnect;
import com.entity.User;

@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id=Integer.parseInt(req.getParameter("id"));
			
			String name=req.getParameter("fname");
			String email=req.getParameter("email");
			String phoneno=req.getParameter("phoneno");
			String password=req.getParameter("password");
			
			
			User us=new User();
			us.setId(id);
			us.setName(name);
			us.setEmail(email);
			us.setPhoneno(phoneno);
			
			HttpSession session=req.getSession();
			UserDAOimpl dao=new UserDAOimpl(DBconnect.getConn());
			
		
			boolean f=dao.checkPassword(id, password);
			if(f)
			{
				boolean f2=dao.updateProfile(us);
				if(f2)
				{
					session.setAttribute("succMsg", "User Profile Update Successfully..");
					resp.sendRedirect("edit_profile.jsp");
					
				}else
				{
					session.setAttribute("failedMsg", "Something Wrong on Server");
					resp.sendRedirect("edit_profile.jsp");
				}
				
			}else
			{
				session.setAttribute("failedMsg", "Your Password Is Incorrect");
				resp.sendRedirect("edit_profile.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
