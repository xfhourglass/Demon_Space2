package com.leojay.school.myschoolforrenai.function;

import java.util.ArrayList;
import java.util.List;

public class Attendances {
    private String name;
    private int absenceTimes; //缺勤
    private int lateTimes;  //迟到
    private int personalLeaveTimes; //事假
    private int sickLeaveTimes; //病假

    public Attendances() {
    }

    public Attendances(String name, int absenceTimes, int lateTimes, int personalLeaveTimes, int sickLeaveTimes) {
        this.name = name;
        this.absenceTimes = absenceTimes;
        this.lateTimes = lateTimes;
        this.personalLeaveTimes = personalLeaveTimes;
        this.sickLeaveTimes = sickLeaveTimes;
    }

    public List<Attendances> getAttendenceList(String data){
        List<Attendances> list = new ArrayList<>();
//        String str0 = data.replace("anyType{dmlist=", "");
//        String str1 = str0.replace("}", "");
//        String str2 = str1.replace(" ", "");
        String[] strings = data.split("#");
        for(String arg : strings) {
            if (!arg.equals("无")) {
                String[] str = arg.split(";");
                list.add(new Attendances(str[1],
                        Integer.parseInt(str[2]),
                        Integer.parseInt(str[3]),
                        Integer.parseInt(str[4]),
                        Integer.parseInt(str[5])
                ));
            }else{
                return null;
            }
        }
        return list;

    }

    public String[] toStrings(){
        return new String[]{this.name,
                this.absenceTimes + "",
                this.lateTimes + "",
                this.personalLeaveTimes + "",
                this.sickLeaveTimes + ""
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAbsenceTimes() {
        return absenceTimes;
    }

    public void setAbsenceTimes(int absenceTimes) {
        this.absenceTimes = absenceTimes;
    }

    public int getLateTimes() {
        return lateTimes;
    }

    public void setLateTimes(int lateTimes) {
        this.lateTimes = lateTimes;
    }

    public int getPersonalLeaveTimes() {
        return personalLeaveTimes;
    }

    public void setPersonalLeaveTimes(int personalLeaveTimes) {
        this.personalLeaveTimes = personalLeaveTimes;
    }

    public int getSickLeaveTimes() {
        return sickLeaveTimes;
    }

    public void setSickLeaveTimes(int sickLeaveTimes) {
        this.sickLeaveTimes = sickLeaveTimes;
    }
}
