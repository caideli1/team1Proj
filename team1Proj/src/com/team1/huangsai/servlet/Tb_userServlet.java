package com.team1.huangsai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.huangsai.bean.Tb_user;
import com.team1.huangsai.dao.Tb_userDao;

/**
 * Servlet implementation class PropertySer
 */
public class Tb_userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Tb_userDao userdao=new Tb_userDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tb_userServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ֹ����
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String  method=request.getParameter("method");
		try {
			Method  m=this.getClass().getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this,request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//�����û����Ƿ��Ѿ�����
		protected void checkName(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			//��ȡ��ӡ��
			PrintWriter   pw=response.getWriter();
			//��ȡ�ϵĲ���
			String userName=request.getParameter("username");
			String userPwd=request.getParameter("uesrpwd");
			System.out.println(userName);
			System.out.println(userPwd);
			
			
		    if(userName==""||userName==null){
		    	//pw.print("<script language='javascript'>alert(\"�û�������Ϊ��");"+"location.herf('${pageContext.request.contextPath }/login.jsp');"+"</script>");
		    	//pw.print("<script language='javascript'>alert(\"�û�������Ϊ��");location.href(' ${pageContext.request.contextPath }/login.jsp ');</script>");
		    	/*pw.print("<script language='javascript'>");
		    	pw.print("alert('�û�������Ϊ��')");*/
		    	//request.setAttribute("r1", "0");
		    	pw.print("0");
		    	//response.sendRedirect("login.jsp?error=0");
		    	//ת��
		    	/*RequestDispatcher   red=request.getRequestDispatcher("/login.jsp");
		    	red.forward(request, response);*/
		    	
		    	//response.sendRedirect(this.getServletContext().getRealPath("/")+"login.jsp");
//		    	String  path=getServletContext().getRealPath("/");
//		    	System.out.println(path);
//		    	pw.print(location.href=path+"login.jsp");
//		    	System.out.println("location.href('"+path+"login.jsp');");
		    	
		    }else {
		    	if(userPwd==""||userPwd==null){
			    	
			    	pw.print("1");
			    }else{
			    	if(userdao.CheckExsit(userName)){
			    		if(userdao.CheckPwd(userPwd,userName)){
			    			pw.print("4");	
			    			Tb_user tb_user=userdao.CheckUser(userPwd, userName);
			    			HttpSession session=request.getSession();
				  			  
				  			 session.setAttribute("userinfo", tb_user);
				  		/*//ת��
			    			RequestDispatcher   red=request.getRequestDispatcher(request.getContextPath()+"login.jsp");
			    			red.forward(request, response);*/
			    			System.out.println(request.getContextPath()+"/public/index.jsp");
			    			//response.sendRedirect(request.getContextPath() + "/public/index.html");
			    		
			  			 // session.setAttribute("uPwd", userPwd);
			    			//ת��
			  			/*//ת��
			    			RequestDispatcher   red=request.getRequestDispatcher(request.getContextPath()+"login.jsp");
			    			red.forward(request, response);*/
                         
			    		}else{
			    			pw.print("3");	
			    		}
			    	}else{
			    		pw.print("2");
			    	}
			    	
			    }
		    	
		    	
		    }
		    
		    //else if(userPwd==""||userPwd==null){
//		    	response.sendRedirect("login.jsp?error=1");
//			}
//		    else{
//				if(userdao.CheckExsit(userName)){
//					if(userdao.CheckPwd(userPwd,userName)){
//						response.sendRedirect(this.getServletContext().getRealPath("/")+"index.jsp");
//						//response.sendRedirect("${pageContext.request.contextPath }/");
//					}else{
//						response.sendRedirect("login.jsp?error=3");
//					}
//					
//				}else{
//					
//					response.sendRedirect("login.jsp?error=4");
//				}
//				
//			
//			
//			}
			/*if(userName=="") { 
				pw.print("0");
			}else if(userPwd==""){
				pw.print("2");
			}else{
				if(userdao.CheckExsit(userName)){
					if(userdao.CheckPwd(userPwd,userName)){
						pw.print("5");
						//response.sendRedirect("${pageContext.request.contextPath }/");
					}else{
						pw.print("3");
					}
					
				}else{
					
					pw.print("1");
				}
				
			
			
			}*/
			pw.flush();
			pw.close();
		
		}
		//�޸�����
		protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int userId=Integer.parseInt(request.getParameter("userId"));
			//��ȡ������
			//String  userPwd1=request.getParameter("userPwd");
			String  userPwd=request.getParameter("tb_user.userPwd");
			System.out.println(userId);
			System.out.println(userPwd);
			//System.out.println(userPwd1);
			userdao.updatePassword(userPwd, userId)	;
		  // response.sendRedirect("login.jsp");
			//request.getRequestDispatcher("login.jsp").forward(request, response);
		
			/*request.setAttribute("para","0");
			System.out.println(request.getAttribute("para"));*/
			
			  PrintWriter out = response.getWriter();

		    out.println("<html>");  

		      out.println("<script type='text/javascript'>");  

		      out.println("window.open ('login.jsp','_top')");  

		      out.println("</script>");  

		      out.println("</html>");
			
		}
		
		
		
		protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			  
 			 session.removeAttribute("userinfo");
 			response.sendRedirect("login.jsp");
		}
	
}
