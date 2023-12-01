package org.example.domain;

import java.util.Date;

public class Score {
    private Integer id;
    private Date createTime;
    private int Score;

    public Score(Integer id, Date createTime, int score) {
        this.id = id;
        this.createTime = createTime;
        Score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", Score=" + Score +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
