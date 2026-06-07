package com.railway.service;

import com.railway.dao.RouteDAO;
import com.railway.models.Route;
import com.railway.util.InputValidator;
import java.time.LocalDate;
import java.util.List;

public class SearchService {
    private RouteDAO routeDAO = new RouteDAO();

    public List<Route> searchTrains(String source, String destination, String dateStr) {

        LocalDate date;
        try {
            date = InputValidator.parseDate(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format! Use yyyy-MM-dd");
            return null;
        }

        if (!InputValidator.isFutureDate(date)) {
            System.out.println("Travel date must be in the future!");
            return null;
        }

        List<Route> routes = routeDAO.searchRoutes(source, destination, date);

        if (routes.isEmpty()) {
            System.out.println("No trains found for your search!");
        }

        return routes;
    }
}
