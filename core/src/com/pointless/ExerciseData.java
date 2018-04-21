package com.pointless;


public class ExerciseData {

    private int id;
    private int ex_type,priority;
    private String grade;
    private String[] image_urls;
    private String[] words;
    private String[] sentences;
    private String object_string;
    private String name;

    public ExerciseData(ExerciseData old){
        this.id = old.getID();
        this.ex_type = old.getExType();
        this.priority = old.getPriority();
        this.grade = old.getGrade();
        this.image_urls = old.image_urls;
        this.words = old.getWords();
        this.sentences = old.getSentences();
        this.object_string = old.getObject_string();
    }

    public ExerciseData() {

    }


    // getter / setter
    public int getID(){return  id; }
    public void setId(int id){ this.id = id;}

    public int getExType(){return  ex_type; }
    public void setExType(int ex_type){this.ex_type = ex_type;}

    public int getPriority(){return  priority; }

    public String getGrade(){return  grade; }
    public void setGrade(String grade){this.grade = grade; }

    public String getObject_string(){ return  object_string; }

    public String[] getImageUrls(){ return  image_urls;}

    public String[] getSentences(){ return  sentences;}

    public String[] getWords(){ return  words;}
}
