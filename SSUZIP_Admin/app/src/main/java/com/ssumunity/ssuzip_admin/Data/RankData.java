package com.ssumunity.ssuzip_admin.Data;

/**
 * Created by Ulnamsong on 2016. 11. 27..
 */

public class RankData {
    public String rankName = "";
    public String rankMajor = "";
    public String rankID = "";

    public RankData(String name, String major, String id) {
        this.rankMajor = major;
        this.rankName = name;
        this.rankID = id;
    }
}
