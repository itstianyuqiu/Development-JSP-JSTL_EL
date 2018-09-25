package ictgradschool.web.simplewebapp.Gallery;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gallery extends HttpServlet {

    //TODO setup the photo directory as an instance variable

    public void init() {
        //TODO get the photo directory and store it in the instance variable you created
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside Gallery Servlet doGet method...");

        //TODO use the getFileDataList method to get all images

        //TODO create a session attribute to store all images

        //TODO call the displayGallery method to display the image gallery

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private List<File> getFileDataList(File folder) {
        //TODO create a file list to store all image files

        //TODO use a loop to loop through all image files and add them to the list

        //TODO return the list of images
        return null;
    }

    private void displayGallery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TODO use dispatcher.forward to forward to 'image-gallery.jsp'

    }

}
