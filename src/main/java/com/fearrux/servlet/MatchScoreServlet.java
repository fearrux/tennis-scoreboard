package com.fearrux.servlet;

import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.exception.InvalidUuidException;
import com.fearrux.service.MatchScoreCalculationService;
import com.fearrux.service.OngoingMatchesService;
import com.fearrux.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@WebServlet(name = "match-score", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        MatchRequestDTO matchRequestDTO = ongoingMatchesService.get(UUID.fromString(uuid));

        req.setAttribute("match", matchRequestDTO);

        req.getRequestDispatcher(JSPHelper.getPath("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        String playerId = req.getParameter("player_id");

        isValidUuid(uuid);
        MatchRequestDTO matchRequestDTO = ongoingMatchesService.get(UUID.fromString(uuid));
        matchScoreCalculationService.updateScore(matchRequestDTO, Long.parseLong(playerId));

        req.setAttribute("match", matchRequestDTO);

        req.getRequestDispatcher(JSPHelper.getPath("match-score")).forward(req, resp);
    }

    private void isValidUuid(String uuid) {
        if (uuid.isEmpty()) {
            throw new InvalidUuidException("UUID cannot be empty.");
        }
        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException exception) {
            throw new InvalidUuidException("Invalid UUID format. Expected: 8-4-4-4-12.");
        }
    }
}
