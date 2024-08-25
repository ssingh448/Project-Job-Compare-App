package edu.gatech.seclass.jobcompare6300.bean;

public class ComparisonSetting {
    private Integer weightOfAYS;
    private Integer weightOfAYB;
    private Integer weightOfLT;
    private Integer weightOfCSO;
    private Integer weightOfHBP;
    private Integer weightOfWF;

    public ComparisonSetting(){};

    public ComparisonSetting(Integer weightOfAYS, Integer weightOfAYB, Integer weightOfLT, Integer weightOfCSO, Integer weightOfHBP, Integer weightOfWF){
        this.weightOfAYS = weightOfAYS;
        this.weightOfAYB = weightOfAYB;
        this.weightOfCSO = weightOfCSO;
        this.weightOfLT = weightOfLT;
        this.weightOfHBP = weightOfHBP;
        this.weightOfWF = weightOfWF;
    }

    public void setWeightOfAYS(Integer weightOfAYS) {
        this.weightOfAYS = weightOfAYS;
    }

    public void setWeightOfAYB(Integer weightOfAYB) {
        this.weightOfAYB = weightOfAYB;
    }

    public void setWeightOfLT(Integer weightOfLT) {
        this.weightOfLT = weightOfLT;
    }

    public void setWeightOfCSO(Integer weightOfCSO) {
        this.weightOfCSO = weightOfCSO;
    }

    public void setWeightOfHBP(Integer weightOfHBP) {
        this.weightOfHBP = weightOfHBP;
    }

    public void setWeightOfWF(Integer weightOfWF) {
        this.weightOfWF = weightOfWF;
    }

    public Integer getWeightOfAYS() {
        return weightOfAYS;
    }

    public Integer getWeightOfAYB() {
        return weightOfAYB;
    }

    public Integer getWeightOfLT() {
        return weightOfLT;
    }

    public Integer getWeightOfCSO() {
        return weightOfCSO;
    }

    public Integer getWeightOfHBP() {
        return weightOfHBP;
    }

    public Integer getWeightOfWF() {
        return weightOfWF;
    }
}
