package ua.com.drabchak.demo.util;

import ua.com.drabchak.demo.model.Presenter;

public class PresenterBuilder {
    private static PresenterBuilder builder;

    private String name;

    private int experience;

    private String resume;


    private PresenterBuilder() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


    public void setResume(String resume) {
        this.resume = resume;
    }

    public Presenter getPresenter() {
        return new Presenter(name, experience, resume);
    }

    public static PresenterBuilder getInstance() {
        if(builder == null) builder = new PresenterBuilder();
        return builder;
    }

    @Override
    public String toString() {
        return "PresenterBuilder{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", resume='" + resume + '\'' +
                '}';
    }
}
