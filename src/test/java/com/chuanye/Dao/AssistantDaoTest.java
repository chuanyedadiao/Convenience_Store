package com.chuanye.Dao;

import com.chuanye.VAL.Assistant;
import org.junit.Test;

import java.util.ArrayList;

public class AssistantDaoTest {

    @Test
    public void checkAccount(){
        Assistant assistant = new Assistant();
        AssistantDao assistantDao = new AssistantDao();
        String id = "031720106";
        String password = "shuaibi";
        try {
            assistant = assistantDao.checkAccount(id, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void findWithId() {
        Assistant assistant = new Assistant();
        AssistantDao assistantDao = new AssistantDao();
        String id = "051826413";
        try {
            assistant = assistantDao.findWithId(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void findWithName() {
        ArrayList<Assistant> al = new ArrayList<>();
        AssistantDao assistantDao = new AssistantDao();
        String name = "zhouxiang";
        try {
            al = assistantDao.findWithName(name);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for(Assistant assistant : al){
            System.out.println(assistant.toString());
        }

    }

    @Test
    public void insertAssistant() {
    }

    @Test
    public void deleteAssistant() {
    }

    @Test
    public void getAssistantCount(){
        try {
            System.out.println(new AssistantDao().getAssistantCount());
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    @Test
    public void getOnePage(){

    }

    @Test
    public void updateAssistant() {
    }

    @Test
    public void updateAssistantPassword() {
    }
}