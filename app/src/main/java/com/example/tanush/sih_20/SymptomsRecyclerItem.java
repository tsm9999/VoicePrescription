package com.example.tanush.sih_20;

public class SymptomsRecyclerItem {

    String symptom;
    String head;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public SymptomsRecyclerItem(String symptom, String head) {
        this.symptom = symptom;
        this.head = head;
    }

    public String getSymptom() {
        return symptom;
    }

    public void changeSymptom(String text) {
        symptom = text;
    }

    public void changeHead(String text1) {
        head = text1;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public SymptomsRecyclerItem(String symptom) {
        this.symptom = symptom;
    }


}
