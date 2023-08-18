package com.strmanager.api.utils;

import com.strmanager.api.domain.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class StrongLift_backup {

    private String PartPlannng(String part, double OneRMweight, double increase) {
        RepMaxCalc rmc = new RepMaxCalc();
        double weight = rmc.Mround(OneRMweight, increase) / 2;
        String result, workOut = null;
//		String warmUp1 = ((weight * 0.7)+increase) + "kg 5X1\n";
//		String warmUp2 = ((weight * 0.8)+increase) + "kg 5X1\n";
//		String warmUp3 = ((weight * 0.9)+increase) + "kg 5X1\n";
        if(part.equals("Deadlift")) {
//			warmUp1 = ((weight * 0.7)+increase) + "kg 5X1\n";
//			warmUp2 = ((weight * 0.8)+increase) + "kg 5X1\n";
//			warmUp3 = ((weight * 0.9)+increase) + "kg 5X1\n";
            workOut = (weight+increase) + "kg 1X5\n";}
        else if(part.equals("Squat")) {
            workOut = (weight+increase) + "kg 5X5\n";
        }
        else {
            workOut = (weight+increase) + "kg 5X5\n";}
        result = part + "\n" + /* warmUp1 + warmUp2 + warmUp3 + */ workOut;
        return result;
    }

    public ArrayList<String[]> createRoutine(Member member, double increase) {
        double sq = member.getPerformance().getSquat();
        double bp = member.getPerformance().getBenchpress();
        double pr = member.getPerformance().getBarbellrow();
        double ohp = member.getPerformance().getOverheadpress();
        double dl = member.getPerformance().getDeadlift();
        ArrayList<String[]> monthSet = new ArrayList<String[]>();
        String worka1 = PartPlannng("Squat", sq, increase*0) + PartPlannng("Bench Press", bp, increase*0) + PartPlannng("Pendlay Row", pr, increase*0);
        String workb = PartPlannng("Squat", sq, increase*1) + PartPlannng("Overhead Press", ohp, increase*0) + PartPlannng("Deadlift", dl, increase*0);
        String worka2 = PartPlannng("Squat", sq, increase*2) + PartPlannng("Bench Press", bp, increase*1) + PartPlannng("Pendlay Row", pr, increase*1);
        String[] result1 = new String[] {worka1, "Rest and \nrecovery", workb, "Rest and \nrecovery", worka2};
        String workb1 =PartPlannng("Squat", sq, increase*3) + PartPlannng("Overhead Press", ohp, increase*1) + PartPlannng("Deadlift", dl, increase*2);
        String worka = PartPlannng("Squat", sq, increase*4) + PartPlannng("Bench Press", bp, increase*2) + PartPlannng("Pendlay Row", pr, increase*2);
        String workb2 =PartPlannng("Squat", sq, increase*5) + PartPlannng("Overhead Press", ohp, increase*2) + PartPlannng("Deadlift", dl, increase*4);
        String[] result2 = new String[] {workb1, "Rest and \nrecovery", worka, "Rest and \nrecovery", workb2};
        worka1 = PartPlannng("Squat", sq, increase*6) + PartPlannng("Bench Press", bp, increase*3) + PartPlannng("Pendlay Row", pr, increase*3);
        workb = PartPlannng("Squat", sq, increase*7) + PartPlannng("Overhead Press", ohp, increase*3) + PartPlannng("Deadlift", dl, increase*6);
        worka2 = PartPlannng("Squat", sq, increase*8) + PartPlannng("Bench Press", bp, increase*4) + PartPlannng("Pendlay Row", pr, increase*4);
        String[] result3 = new String[] {worka1, "Rest and \nrecovery", workb, "Rest and \nrecovery", worka2};
        workb1 = PartPlannng("Squat", sq, increase*9) + PartPlannng("Overhead Press", ohp, increase*4) + PartPlannng("Deadlift", dl, increase*8);
        worka = PartPlannng("Squat", sq, increase*10) + PartPlannng("Bench Press", bp, increase*5) + PartPlannng("Pendlay Row", pr, increase*5);
        workb2 = PartPlannng("Squat", sq, increase*11) + PartPlannng("Overhead Press", ohp, increase*5) + PartPlannng("Deadlift", dl, increase*10);
        String[] result4 = new String[] {workb1, "Rest and \nrecovery", worka, "Rest and \nrecovery", workb2};
        worka1 = PartPlannng("Squat", sq, increase*12) + PartPlannng("Bench Press", bp, increase*6) + PartPlannng("Pendlay Row", pr, increase*6);
        workb = PartPlannng("Squat", sq, increase*13) + PartPlannng("Overhead Press", ohp, increase*6) + PartPlannng("Deadlift", dl, increase*12);
        worka2 = PartPlannng("Squat", sq, increase*14) + PartPlannng("Bench Press", bp, increase*7) + PartPlannng("Pendlay Row", pr, increase*7);
        String[] result5 = new String[] {worka1, "Rest and \nrecovery", workb, "Rest and \nrecovery", worka2};
        workb1 = PartPlannng("Squat", sq, increase*15) + PartPlannng("Overhead Press", ohp, increase*7) + PartPlannng("Deadlift", dl, increase*14);
        worka = PartPlannng("Squat", sq, increase*16) + PartPlannng("Bench Press", bp, increase*8) + PartPlannng("Pendlay Row", pr, increase*8);
        workb2 = PartPlannng("Squat", sq, increase*17) + PartPlannng("Overhead Press", ohp, increase*8) + PartPlannng("Deadlift", dl, increase*16);
        String[] result6 = new String[] {workb1, "Rest and \nrecovery", worka, "Rest and \nrecovery", workb2};
        worka1 = PartPlannng("Squat", sq, increase*18) + PartPlannng("Bench Press", bp, increase*9) + PartPlannng("Pendlay Row", pr, increase*9);
        workb = PartPlannng("Squat", sq, increase*19) + PartPlannng("Overhead Press", ohp, increase*9) + PartPlannng("Deadlift", dl, increase*18);
        worka2 = PartPlannng("Squat", sq, increase*20) + PartPlannng("Bench Press", bp, increase*10) + PartPlannng("Pendlay Row", pr, increase*10);
        String[] result7 = new String[] {worka1, "Rest and \nrecovery", workb, "Rest and \nrecovery", worka2};
        workb1 = PartPlannng("Squat", sq, increase*21) + PartPlannng("Overhead Press", ohp, increase*10) + PartPlannng("Deadlift", dl, increase*20);
        worka = PartPlannng("Squat", sq, increase*22) + PartPlannng("Bench Press", bp, increase*11) + PartPlannng("Pendlay Row", pr, increase*11);
        workb2 = PartPlannng("Squat", sq, increase*23) + PartPlannng("Overhead Press", ohp, increase*11) + PartPlannng("Deadlift", dl, increase*22);
        String[] result8 = new String[] {workb1, "Rest and \nrecovery", worka, "Rest and \nrecovery", workb2};
        worka1 = PartPlannng("Squat", sq, increase*24) + PartPlannng("Bench Press", bp, increase*12) + PartPlannng("Pendlay Row", pr, increase*12);
        workb = PartPlannng("Squat", sq, increase*25) + PartPlannng("Overhead Press", ohp, increase*12) + PartPlannng("Deadlift", dl, increase*24);
        worka2 = PartPlannng("Squat", sq, increase*26) + PartPlannng("Bench Press", bp, increase*13) + PartPlannng("Pendlay Row", pr, increase*13);
        String[] result9 = new String[] {worka1, "Rest and \nrecovery", workb, "Rest and \nrecovery", worka2};
        workb1 = PartPlannng("Squat", sq, increase*27) + PartPlannng("Overhead Press", ohp, increase*13) + PartPlannng("Deadlift", dl, increase*26);
        worka = PartPlannng("Squat", sq, increase*28) + PartPlannng("Bench Press", bp, increase*14) + PartPlannng("Pendlay Row", pr, increase*14);
        workb2 = PartPlannng("Squat", sq, increase*29) + PartPlannng("Overhead Press", ohp, increase*14) + PartPlannng("Deadlift", dl, increase*28);
        String[] result10 = new String[] {workb1, "Rest and \nrecovery", worka, "Rest and \nrecovery", workb2};
        worka1 = PartPlannng("Squat", sq, increase*30) + PartPlannng("Bench Press", bp, increase*15) + PartPlannng("Pendlay Row", pr, increase*15);
        workb = PartPlannng("Squat", sq, increase*31) + PartPlannng("Overhead Press", ohp, increase*15) + PartPlannng("Deadlift", dl, increase*30);
        worka2 = PartPlannng("Squat", sq, increase*32) + PartPlannng("Bench Press", bp, increase*16) + PartPlannng("Pendlay Row", pr, increase*16);
        String[] result11 = new String[] {worka1, "Rest and \nrecovery", workb, "Rest and \nrecovery", worka2};
        workb1 = PartPlannng("Squat", sq, increase*33) + PartPlannng("Overhead Press", ohp, increase*16) + PartPlannng("Deadlift", dl, increase*32);
        worka = PartPlannng("Squat", sq, increase*34) + PartPlannng("Bench Press", bp, increase*17) + PartPlannng("Pendlay Row", pr, increase*17);
        workb2 = PartPlannng("Squat", sq, increase*35) + PartPlannng("Overhead Press", ohp, increase*17) + PartPlannng("Deadlift", dl, increase*34);
        String[] result12 = new String[] {workb1, "Rest and \nrecovery", worka, "Rest and \nrecovery", workb2};
        monthSet.add(result1);	monthSet.add(result2);	monthSet.add(result3);	monthSet.add(result4);
        monthSet.add(result5);	monthSet.add(result6);	monthSet.add(result7);	monthSet.add(result8);
        monthSet.add(result9);	monthSet.add(result10);	monthSet.add(result11);	monthSet.add(result12);
        return monthSet;
    }

}
