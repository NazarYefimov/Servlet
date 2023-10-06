package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String timezone = request.getParameter("timezone");
        java.util.TimeZone timeZone;

        if (timezone != null && !timezone.isEmpty()) {
            timeZone = java.util.TimeZone.getTimeZone(timezone);
        } else {
            timeZone = java.util.TimeZone.getTimeZone("UTC");
        }

        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(timeZone);
        String formattedTime = sdf.format(currentTime);

        String htmlResponse = "<html><body>";
        htmlResponse += "<h2>Поточний час (" + timeZone.getID() + ")</h2>";
        htmlResponse += "<p>" + formattedTime + "</p>";
        htmlResponse += "</body></html>";

        PrintWriter out = response.getWriter();
        out.println(htmlResponse);
    }
}
