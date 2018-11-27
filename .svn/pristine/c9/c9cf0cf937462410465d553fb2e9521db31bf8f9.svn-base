package com.mqtt.bean.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class PlanMac {
    private List<String> macs;
    private List<UpgradeOrder> upgradeOrderList;
    public List<String> getMacs() {
        return macs;
    }
    public void setMacs(List<String> macs) {
        this.macs = macs;
    }
    public List<UpgradeOrder> getUpgradeOrderList() {
        return upgradeOrderList;
    }
    public void setUpgradeOrderList(List<UpgradeOrder> upgradeOrderList) {
        this.upgradeOrderList = upgradeOrderList;
    }
    
    public static void main(String[] args) throws Exception
    {
        
        File file=new File("D:\\aaaa.txt");
        

        String strContent="";
        InputStreamReader insReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader bufferedReader=new BufferedReader(insReader);
        String line;
        while((line=bufferedReader.readLine())!=null)
        {
            strContent+=line;
        }
        if(insReader != null){
            insReader.close();
        }
        if(bufferedReader != null){
            bufferedReader.close();
        }
       System.out.println(strContent);
    
        
    }
    
    }
