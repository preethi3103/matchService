package com.dxc.matchService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CMatch {

	@Id
	private int matchId;
	private String team1;
	private String team2;
	private String matchType;
	private String matchDate;
	private String tossWinner;
	private String winner;
	private String score;

	public CMatch() {
		super();
	}

	public CMatch(int matchId, String team1, String team2, String matchType, String matchDate, String tossWinner,
			String winner, String score) {
		super();
		this.matchId = matchId;
		this.team1 = team1;
		this.team2 = team2;
		this.matchType = matchType;
		this.matchDate = matchDate;
		this.tossWinner = tossWinner;
		this.winner = winner;
		this.score = score;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getTossWinner() {
		return tossWinner;
	}

	public void setTossWinner(String tossWinner) {
		this.tossWinner = tossWinner;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}