package edu.gatech.seclass.jobcompare6300.bean;

public class Job {
    private String title;
    private String company;
    private String location;
    private Integer yearlySalary;
    private Integer yearlyBonus;
    private Integer leaveTime;
    private Integer numberofSharesOffered;
    private Integer homeBuyingProgramFund;
    private Integer wellnessFund;
    private Double jobScore;
    private String jobType;
    private Integer id;

    public Job(){
    }

    public Job(String title, String company, String location, Integer yearlySalary, Integer yearlyBonus, Integer leaveTime, Integer numberofSharesOffered, Integer homeBuyingProgramFund, Integer wellnessFund){
        this.title = title;
        this.company = company;
        this.location = location;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.leaveTime = leaveTime;
        this.numberofSharesOffered = numberofSharesOffered;
        this.homeBuyingProgramFund = homeBuyingProgramFund;
        this.wellnessFund = wellnessFund;
    }
    public Job(String title, String company, String location, Integer yearlySalary, Integer yearlyBonus, Integer leaveTime, Integer numberofSharesOffered, Integer homeBuyingProgramFund, Integer wellnessFund, Double jobScore, String jobType) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.leaveTime = leaveTime;
        this.numberofSharesOffered = numberofSharesOffered;
        this.homeBuyingProgramFund = homeBuyingProgramFund;
        this.wellnessFund = wellnessFund;
        this.jobScore = jobScore;
        this.jobType = jobType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(Integer yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public Integer getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(Integer yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public Integer getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Integer leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getNumberofSharesOffered() {
        return numberofSharesOffered;
    }

    public void setNumberofSharesOffered(Integer numberofSharesOffered) {
        this.numberofSharesOffered = numberofSharesOffered;
    }

    public Integer getHomeBuyingProgramFund() {
        return homeBuyingProgramFund;
    }

    public void setHomeBuyingProgramFund(Integer homeBuyingProgramFund) {
        this.homeBuyingProgramFund = homeBuyingProgramFund;
    }

    public Integer getWellnessFund() {
        return wellnessFund;
    }

    public void setWellnessFund(Integer wellnessFund) {
        this.wellnessFund = wellnessFund;
    }

    public Double getJobScore() {
        return jobScore;
    }

    public void setJobScore(Double jobScore) {
        this.jobScore = jobScore;
    }

    public String getJobType(){
        return jobType;
    }
    public void setJobType(String jobType){
        this.jobType = jobType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
