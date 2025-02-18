package com.fearrux.servlet;

import com.fearrux.dto.MatchDTO;
import com.fearrux.service.FinishedMatchesPersistenceService;
import com.fearrux.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "matches", value = "/matches")
public class MatchesServlet extends HttpServlet {
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    private static final int defaultPage = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String filterByPlayerName = req.getParameter("filter_by_player_name");

        int currentPage = page == null ? defaultPage : Integer.parseInt(page);
        int recordsPerPage = 5;

        List<MatchDTO> matches;
        if (filterByPlayerName != null && !filterByPlayerName.isEmpty()) {
            matches = finishedMatchesPersistenceService.findByName(filterByPlayerName, (currentPage - 1) * recordsPerPage, recordsPerPage);
        } else {
            matches = finishedMatchesPersistenceService.findAll((currentPage - 1) * recordsPerPage, recordsPerPage);
        }

        List<MatchDTO> allMatches;
        if (filterByPlayerName != null && !filterByPlayerName.isEmpty()) {
            allMatches = finishedMatchesPersistenceService.findByName(filterByPlayerName, 0, Integer.MAX_VALUE);
        } else {
            allMatches = finishedMatchesPersistenceService.findAll(0, Integer.MAX_VALUE);
        }

        int totalRecords = allMatches.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        req.setAttribute("pages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("matches", matches);
        req.getRequestDispatcher(JSPHelper.getPath("matches")).forward(req, resp);
    }
}
