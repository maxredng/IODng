/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import com.iot.misc.Feed;
import com.iot.web.tag.Element;
import helper.Misc;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bsati
 */
@WebServlet(name = "Page", urlPatterns = {"/start/Page"})
public class Page extends HttpServlet {


    String signature;
    String scope;
    String project;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
       out.println("<!doctype html>");
                
                out.println("<html>");
            out.println("<head>");
            out.println("<title>Red-board</title>");   
            
//HTTP-EQUIV='Pragma'
            out.println("<META CONTENT='no-cache'> ");
           
            
            
            out.println("<meta property='og:url'   content='redhotpage.com'/>");
            out.println("<meta property='og:type'   content='website'/>");
            out.println("<meta http-equiv='X-UA-Compatible'   content='IE=edge'/>");
            out.println("<meta name='viewport'   content='width=device-width,initial-scale=1'/>");
            out.println("<LINK REL='stylesheet' TYPE='text/css' HREF='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
           out.println("<LINK REL='stylesheet' TYPE='text/css' HREF='/corefx/template061.css'>");
            // out.println("<LINK REL='stylesheet' TYPE='text/css' HREF='/corefx/bootstrap-3.3.7/dist/css/template061.css'>");
                out.println("<script src='https://code.jquery.com/jquery-3.2.1.slim.min.js'></script>");
    out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js'></script>");
    out.println("<script src='/corefx/bootstrap-3.3.7/dist/js/bootstrap.min.js'></script>");
            
     //       out.println("<LINK REL='stylesheet' href='/corefx/bootstrap-3.3.7/dist/css/bootstrap.min.css'>");
       
            
            out.println();
            out.println();
            out.println();
            
            out.println("<meta property='og:image'   content='/corefx/start/img?id=finger'>");
            //   out.println("<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>");
            String vrsn = "01";
            out.println("<script type='text/javascript' src='/corefx/"+vrsn+"'></script>");
            out.println("<script type='text/javascript' src='/corefx/action007.js'></script>");
            out.println("</head>"); 
            //onmousemove='moveme(event)'
              String testscope=request.getParameter("scope");    
              
              String bgo = "";
              
              
              
 
              bgo = "bgcolor='white'";
              
              
            out.println("<body onLoad='on_load()' "+bgo+">");
      //      out.println(loader.getLoader());
            HttpSession session=request.getSession();
            String exit=request.getParameter("exit");            
 
            signature = Misc.getSignature(request);
            scope = request.getParameter("scope");
            project = request.getParameter("project");
            
            String curp=System.getProperty("user.dir");         
            
       
                if(testscope.equals("proman")||testscope.equals("mapro"))
                {
                out.println("<div id='fb-root'></div>");
                out.println("<script>(function(d, s, id) {");
                out.println("var js, fjs = d.getElementsByTagName(s)[0];");
                out.println("if (d.getElementById(id)) return;");
                out.println("js = d.createElement(s); js.id = id;");
                out.println("js.src = '//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.6\';");
                out.println("fjs.parentNode.insertBefore(js, fjs);");
                out.println("}(document, 'script', 'facebook-jssdk'));</script>");
                
                }
                if(testscope.equals("addnew"))
                {
                out.println("<div class='loader' id='load@main_r0c0'></div>");
                
                }
                 if(testscope.equals("type")||testscope.equals("voice"))
                {
                out.println("<div class='lowloader' id='class@load@main_r0c0'></div>");
                
                }
                 Feed feed = (Feed)session.getAttribute("feed");
                 if(feed == null)
                 {
                    feed = new Feed();
                    feed.init();
                 }

                 String ss = "select id from config.element where scope='" + scope + "' and project = '" + project + "'";
                 List<String> els = feed.pullList("select id from config.element where scope='" + scope + "' and project = '" + project + "'");
                 feed.remove("scope");
                 feed.remove("project");
                 feed.add("scope", request.getParameter("scope"));
                 feed.add("project", request.getParameter("project"));
                 feed.remove("signature");
                 feed.add("signature", signature);
                 
                 for(String id:els)
                 {
                     Element el = new Element();
                     feed.remove("skinid");
                     feed.add("skinid", id);
                     el.set(feed);
                             
                     out.println(el.getCode());
                 }
                 
            out.println("</body>");
            out.println("</html>");
        
    }catch(Exception x)
    {}
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    }

