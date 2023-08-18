package com.strmanager.api.utils;

import com.strmanager.api.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class WilksRank {
    WilksPoint wp = new WilksPoint();
    final private double[][] rankArr = new double [][]{
            {52,116,193,227,321,416},
            {56,116,193,230,320,415},
            {60,117,195,231,321,414},
            {67,118,197,236,326,416},
            {75,119,198,236,326,415},
            {82,120,201,239,329,418},
            {90,121,201,241,329,416},
            {100,121,202,243,330,415},
            {110,123,204,242,329,412},
            {125,122,203,241,326,408},
            {145,121,202,240,324,405},
            {146,124,206,245,330,413}
            //	{WeightClass, Beginner, Novice, Intermediate, Advanced, Elite}
    };
    private double[] rankCatch(double weight) {
        double [] result = rankArr[0];
        for(int i = 0 ; i < rankArr.length ; i++) {
            if(weight >= rankArr[i][0]) result = rankArr[i];
        }
        return result;
    }
    public String weightClass(Member member) {
        String result = "52+";
        for(int i = 0 ; i < rankArr.length ; i++) {
            if(member.getWeight() >= rankArr[i][0]) result = rankArr[i][0] + "+";
        }
        return result;
    }
    public String getRank(Member member) {					// 수정필요
        double wilkspoint = wp.Coeff(member);
        double [] standard = rankCatch(member.getWeight());
        String rank = "Beginner";
        if(wilkspoint > standard[2]) rank = "Novice";
        if(wilkspoint > standard[3]) rank = "Intermediate";
        if(wilkspoint > standard[4]) rank = "Advanced";
        if(wilkspoint > standard[5]) rank = "Elite";
        return rank;
    }
}
