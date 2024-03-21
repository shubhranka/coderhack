package com.shubhranka.coderhack.enums;

public enum Badge {
    CODE_NINJA("Code Ninja", (byte) 1),
    CODE_CHAMPION("Code Champ", (byte) 30),
    CODE_MASTER("Code Master", (byte) 60);

    private final String badgeName;
    private final byte minScore;

    Badge(String badgeName, byte minScore){
        this.badgeName = badgeName;
        this.minScore = minScore;
    }

    public String getBadgeName(){
        return badgeName;
    }

    public byte getMinScore(){
        return minScore;
    }

}