package com.fearrux.servlet;

import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.entity.Player;
import com.fearrux.exception.InvalidMatchException;
import com.fearrux.service.OngoingMatchesService;
import com.fearrux.service.PlayerService;
import com.fearrux.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "new-match", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPHelper.getPath("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayerName = req.getParameter("first_player");
        String secondPlayerName = req.getParameter("second_player");

        Player firstPlayer = playerService.findByName(firstPlayerName);
        Player secondPlayer = playerService.findByName(secondPlayerName);

        validateMatchPlayers(firstPlayerName, secondPlayerName);

        MatchRequestDTO match = MatchRequestDTO.builder()
                .firstPlayer(firstPlayer)
                .secondPlayer(secondPlayer)
                .build();

        ongoingMatchesService.add(match.getUuid(), match);
        resp.sendRedirect("/match-score?uuid=" + match.getUuid());
    }

    private void validateMatchPlayers(String firstPlayerName, String secondPlayerName) throws InvalidMatchException {
        if (firstPlayerName.equalsIgnoreCase(secondPlayerName)) {
            throw new InvalidMatchException("Players must have different names.");
        }
    }
}
