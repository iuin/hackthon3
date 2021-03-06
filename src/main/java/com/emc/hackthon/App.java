package com.emc.hackthon;

import com.emc.hackthon.entity.Numeral;
import com.emc.hackthon.util.CommonUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * User: Alex
 * Date: 2017/11/11
 * Time: 21:30
 * Description:
 */
public class App {

    public static void main(String[] args) throws Exception{
        List<Numeral> numerals = CommonUtils.readTrainData("D:\\Projects\\github\\hackthon\\src\\main\\resources\\test.dat");
        BufferedWriter bufWriter = new BufferedWriter(new FileWriter("result.txt"));

        for (Numeral numeral : numerals){
            bufWriter.write(numeral.getFeature()+"\n");
        }

        bufWriter.close();
    }
}
