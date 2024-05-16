package com.crucigramax.model;


import java.util.List;

public class Usuario {
    private String nickname;
    private List<Score> scores; 

    public Usuario(String nickname, List<Score> scores) { 
        this.nickname = nickname;
        this.scores = scores;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Score> getScore() { 
        return scores;
    }

    public void setScores(List<Score> scores) { 
        this.scores = scores;
    }
}
