

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.mail.Session;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Departement;

/**
 * Servlet implementation class ser
 */
@WebServlet("/ser")
public class ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String nom=request.getParameter("rep");
		Departement d=new Departement();
		PrintWriter out =response.getWriter();
		
		System.out.println("1");
EntityManagerFactory emf=Persistence.createEntityManagerFactory("Archive");
System.out.println("2");
		EntityManager em=emf.createEntityManager();	
		System.out.println("3");

		 
			//liste=emf.createEntityManager().createQuery("select e from 	Departement e ").getResultList();
		 CriteriaBuilder builder = em.getCriteriaBuilder();
		 CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
		 Root<Admin> i = query.from(Admin.class);
		 query.select(i);
		 List<Admin> l2;
		 /*query.where(builder.equal(i.get("login").as(String.class), request.getParameter("log")));
		 query.where(builder.equal(i.get("password").as(String.class), request.getParameter("pass")));*/
		 query.where(builder.equal(i.get("login").as(String.class), request.getParameter("log")),builder.equal(i.get("password").as(String.class), request.getParameter("pass")));
		  l2 = em.createQuery(query).getResultList();
		 System.out.println(l2.size());
		if(l2.isEmpty()){
				out.write("nono :(");
			}
			else{System.out.println("ok :D");
			out.write("ok :D");
			System.out.println("4");
			 HttpSession session=request.getSession();
			   session.setAttribute("log1", request.getParameter("log"));
			   request.getRequestDispatcher("add_dep.jsp").forward(request, response);
			}
	   //em.getTransaction().begin();
	   
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
