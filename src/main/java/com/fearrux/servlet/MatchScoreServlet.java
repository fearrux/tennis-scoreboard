package com.fearrux.servlet;

import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.exception.InvalidUuidException;
import com.fearrux.service.FinishedMatchesPersistenceService;
import com.fearrux.service.MatchScoreCalculationService;
import com.fearrux.service.OngoingMatchesService;
import com.fearrux.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "match-score", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

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

        UUID validUuid = isValidUuid(uuid);
        MatchRequestDTO matchRequestDTO = ongoingMatchesService.get(validUuid);
        matchScoreCalculationService.updateScore(matchRequestDTO, Long.parseLong(playerId));

        if (matchScoreCalculationService.isMatchOver(matchRequestDTO.getMatchState())) {
            ongoingMatchesService.remove(validUuid);
            finishedMatchesPersistenceService.save(matchRequestDTO, matchScoreCalculationService.getWinner(matchRequestDTO));
        }
        req.setAttribute("match", matchRequestDTO);
        req.getRequestDispatcher(JSPHelper.getPath("match-score")).forward(req, resp);
    }

    private UUID isValidUuid(String uuid) {
        if (uuid.isEmpty()) {
            throw new InvalidUuidException("UUID cannot be empty.");
        }
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException exception) {
            throw new InvalidUuidException("Invalid UUID format. Expected: 8-4-4-4-12.");
        }
    }

}
