package com.github.domain;

public class rankinglist {
    private String wherefrom;
    private String contents;

    public String getWherefrom() {
        return wherefrom;
    }

    public void setWherefrom(String wherefrom) {
        this.wherefrom = wherefrom;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "rankinglist{" +
                "wherefrom='" + wherefrom + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
