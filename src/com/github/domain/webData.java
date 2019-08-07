package com.github.domain;

public class webData {
    private int allPageview; // 总访问量
    private int todayPageview; // 今日访问量
    private int allArticle; // 所有文章数
    private int yestArticle; // 昨日浏览量
    private int musicReviews; // 乐评
    private int musicStory; // 音乐故事
    private int Mandarin;// 华语
    private int Western; //欧美
    private int Rock; //摇滚
    private int Pop; //流行
    private int Rap; //嘻哈

    public int getAllPageview() {
        return allPageview;
    }

    public void setAllPageview(int allPageview) {
        this.allPageview = allPageview;
    }

    public int getTodayPageview() {
        return todayPageview;
    }

    public void setTodayPageview(int todayPageview) {
        this.todayPageview = todayPageview;
    }

    public int getAllArticle() {
        return allArticle;
    }

    public void setAllArticle(int allArticle) {
        this.allArticle = allArticle;
    }

    public int getYestArticle() {
        return yestArticle;
    }

    public void setYestArticle(int yestArticle) {
        this.yestArticle = yestArticle;
    }

    public int getMusicReviews() {
        return musicReviews;
    }

    public void setMusicReviews(int musicReviews) {
        this.musicReviews = musicReviews;
    }

    public int getMusicStory() {
        return musicStory;
    }

    public void setMusicStory(int musicStory) {
        this.musicStory = musicStory;
    }

    public int getMandarin() {
        return Mandarin;
    }

    public void setMandarin(int mandarin) {
        Mandarin = mandarin;
    }

    public int getWestern() {
        return Western;
    }

    public void setWestern(int western) {
        Western = western;
    }

    public int getRock() {
        return Rock;
    }

    public void setRock(int rock) {
        Rock = rock;
    }

    public int getPop() {
        return Pop;
    }

    public void setPop(int pop) {
        Pop = pop;
    }

    public int getRap() {
        return Rap;
    }

    public void setRap(int rap) {
        Rap = rap;
    }

    @Override
    public String toString() {
        return "webData{" +
                "allPageview=" + allPageview +
                ", todayPageview=" + todayPageview +
                ", allArticle=" + allArticle +
                ", yestArticle=" + yestArticle +
                ", musicReviews=" + musicReviews +
                ", musicStory=" + musicStory +
                ", Mandarin=" + Mandarin +
                ", Western=" + Western +
                ", Rock=" + Rock +
                ", Pop=" + Pop +
                ", Rap=" + Rap +
                '}';
    }
}
