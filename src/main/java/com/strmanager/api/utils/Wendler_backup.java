package com.strmanager.api.utils;

import com.strmanager.api.domain.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class Wendler_backup {

    private double[] intensityCalc(double rmWeight) {		// use to intensityForWaves
        RepMaxCalc rmc = new RepMaxCalc();
        double[] tmp = {0, 0.65, 0.7, 0.75, 0.8, 0.85, 0.9, 0.95, 0.4, 0.5, 0.6};
        double[] result = new double[tmp.length];
        for(int i = 0 ; i < result.length ; i++) {
            result[i] = (rmc.Mround(((rmWeight*0.9) * tmp[i]), 2.5));
        }
        return result;
    }

    private String[] waveA(Member member, double increase) {
        double sq = member.getPerformance().getSquat();
        double bp = member.getPerformance().getBenchpress();
        double dl = member.getPerformance().getDeadlift();
        double ohp = member.getPerformance().getOverheadpress();
        double[] tmp = intensityCalc(sq);
        String sq1 = "Squat\n"+(tmp[1]+increase*2) + "kg X5\n" + (tmp[3]+increase*2) + "kg X5\n" + (tmp[5]+increase*2) + "kg X5+\n";
        tmp = intensityCalc(bp);
        String bp1 = "Bench Press\n"+(tmp[1]+increase) + "kg X5\n" + (tmp[3]+increase) + "kg X5\n" + (tmp[5]+increase) + "kg X5+\n";
        tmp = intensityCalc(dl);
        String dl1 = "Deadlift\n"+(tmp[1]+increase) + "kg X5\n" + (tmp[3]+increase) + "kg X5\n" + (tmp[5]+increase) + "kg X5+\n";
        tmp = intensityCalc(ohp);
        String ohp1 = "Overhead Press\n"+(tmp[1]+increase) + "kg X5\n" + (tmp[3]+increase) + "kg X5\n" + (tmp[5]+increase) + "kg X5+\n";
        String[] result = {sq1,ohp1,"Rest and \nrecovery",dl1,bp1};
        return result;
    }

    private String[] waveB(Member member, double increase) {
        double sq = member.getPerformance().getSquat();
        double bp = member.getPerformance().getBenchpress();
        double dl = member.getPerformance().getDeadlift();
        double ohp = member.getPerformance().getOverheadpress();
        double[] tmp = intensityCalc(sq);
        String sq1 = "Squat\n"+(tmp[2]+increase*2) + "kg X3\n" + (tmp[4]+increase*2) + "kg X3\n" + (tmp[6]+increase*2) + "kg X3+\n";
        tmp = intensityCalc(bp);
        String bp1 = "Bench Press\n"+(tmp[2]+increase) + "kg X3\n" + (tmp[4]+increase) + "kg X3\n" + (tmp[6]+increase) + "kg X3+\n";
        tmp = intensityCalc(dl);
        String dl1 = "Deadlift\n"+(tmp[2]+increase) + "kg X3\n" + (tmp[4]+increase) + "kg X3\n" + (tmp[6]+increase) + "kg X3+\n";
        tmp = intensityCalc(ohp);
        String ohp1 = "Overhead Press\n"+(tmp[2]+increase) + "kg X3\n" + (tmp[4]+increase) + "kg X3\n" + (tmp[6]+increase) + "kg X3+\n";
        String[] result = {sq1,ohp1,"Rest and \nrecovery",dl1,bp1};
        return result;
    }

    private String[] waveC(Member member, double increase) {
        double sq = member.getPerformance().getSquat();
        double bp = member.getPerformance().getBenchpress();
        double dl = member.getPerformance().getDeadlift();
        double ohp = member.getPerformance().getOverheadpress();
        double[] tmp = intensityCalc(sq);
        String sq1 = "Squat\n"+(tmp[3]+increase*2) + "kg X5\n" + (tmp[5]+increase*2) + "kg X3\n" + (tmp[7]+increase*2) + "kg X1+\n";
        tmp = intensityCalc(bp);
        String bp1 = "Bench Press\n"+(tmp[3]+increase) + "kg X5\n" + (tmp[5]+increase) + "kg X3\n" + (tmp[7]+increase) + "kg X1+\n";
        tmp = intensityCalc(dl);
        String dl1 = "Deadlift\n"+(tmp[3]+increase) + "kg X5\n" + (tmp[5]+increase) + "kg X3\n" + (tmp[7]+increase) + "kg X1+\n";
        tmp = intensityCalc(ohp);
        String ohp1 = "Overhead Press\n"+(tmp[3]+increase) + "kg X5\n" + (tmp[5]+increase) + "kg X3\n" + (tmp[7]+increase) + "kg X1+\n";
        String[] result = {sq1,ohp1,"Rest and \nrecovery",dl1,bp1};
        return result;
    }

    private String[] waveD(Member member, double increase) {
        double sq = member.getPerformance().getSquat();
        double bp = member.getPerformance().getBenchpress();
        double dl = member.getPerformance().getDeadlift();
        double ohp = member.getPerformance().getOverheadpress();
        double[] tmp = intensityCalc(sq);
        String sq1 = "Squat\n"+(tmp[8]+increase*2) + "kg X5\n" + (tmp[9]+increase*2) + "kg X5\n" + (tmp[10]+increase*2) + "kg X5\n";
        tmp = intensityCalc(bp);
        String bp1 = "Bench Press\n"+(tmp[8]+increase) + "kg X5\n" + (tmp[9]+increase) + "kg X5\n" + (tmp[10]+increase) + "kg X5\n";
        tmp = intensityCalc(dl);
        String dl1 = "Deadlift\n"+(tmp[8]+increase) + "kg X5\n" + (tmp[9]+increase) + "kg X5\n" + (tmp[10]+increase) + "kg X5\n";
        tmp = intensityCalc(ohp);
        String ohp1 = "Overhead Press\n"+(tmp[8]+increase) + "kg X5\n" + (tmp[9]+increase) + "kg X5\n" + (tmp[10]+increase) + "kg X5\n";
        String[] result = {sq1,ohp1,"Rest and \nrecovery",dl1,bp1};
        return result;
    }

    public ArrayList<String[]> createRoutine(Member member, double increase) {
        ArrayList<String[]> monthSet = new ArrayList<String[]>();
        String[] waveA, waveB, waveC, waveD;
        for(int i = 0 ; i < 3 ; i++) {
            waveA = waveA(member, increase*i);
            waveB = waveB(member, increase*i);
            waveC = waveC(member, increase*i);
            waveD = waveD(member, increase*i);
            monthSet.add(waveA);
            monthSet.add(waveB);
            monthSet.add(waveC);
            monthSet.add(waveD);
        }
        return monthSet;
    }

}
