package controller;

import dao.CarDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Operations.*;

public class CarController extends HttpServlet {
    private CarDB carDB;

    private static final String CREATE_CAR = "/createCar.jsp";
    private static final String EDIT_CAR = "/editCar.jsp";
    private static final String LIST_CARS = "/listCars.jsp";
    private static final String LIST_CARS_BY_OWNER = "/listCarsByOwnerId.jsp";

    public CarController() {
        this.carDB = new CarDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionValue = req.getParameter("action");
        String view = "";

        if (CREATE.name().equalsIgnoreCase(actionValue)) {
            view = CREATE_CAR;
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            String carNumber = req.getParameter("carNumber");
            req.setAttribute("car", carDB.getCarByNumber(carNumber));
            view = EDIT_CAR;
        } else if (DELETE.name().equalsIgnoreCase(actionValue)) {
            String carNumber = req.getParameter("carNumber");
            carDB.deleteCarByNumber(carNumber);
            req.setAttribute("cars", carDB.getAllCars());
            view = LIST_CARS;
        } else if (LIST.name().equalsIgnoreCase(actionValue)) {
            req.setAttribute("cars", carDB.getAllCars());
            view = LIST_CARS;
        } else if (LIST_CARS_BY_OWNER_ID.name().equalsIgnoreCase(actionValue)) {
            int ownerId = Integer.parseInt(req.getParameter("ownerId"));
            req.setAttribute("cars", carDB.getCarsByOwnerId(ownerId));
            view = LIST_CARS_BY_OWNER;
        } else {

        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
