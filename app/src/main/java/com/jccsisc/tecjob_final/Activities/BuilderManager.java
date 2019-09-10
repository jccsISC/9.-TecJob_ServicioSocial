package com.jccsisc.tecjob_final.Activities;

import com.nightonke.boommenu.BoomButtons.HamButton;

public class BuilderManager {


    static HamButton.Builder getHamButtonBuilder(String text, String subText, int img) {
        return new HamButton.Builder()
                .normalImageRes(img)
                .normalText(text)
                .subNormalText(subText);
    }

    private BuilderManager() {
    }
}
