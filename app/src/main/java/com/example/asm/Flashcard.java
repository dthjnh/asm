package com.example.asm;

import android.os.Parcel;
import android.os.Parcelable;

public class Flashcard implements Parcelable {
    private String title;
    private String question;
    private String answer;

    public Flashcard(String title, String question, String answer) {
        this.title = title;
        this.question = question;
        this.answer = answer;
    }

    protected Flashcard(Parcel in) {
        title = in.readString();
        question = in.readString();
        answer = in.readString();
    }

    public static final Creator<Flashcard> CREATOR = new Creator<Flashcard>() {
        @Override
        public Flashcard createFromParcel(Parcel in) {
            return new Flashcard(in);
        }

        @Override
        public Flashcard[] newArray(int size) {
            return new Flashcard[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(question);
        parcel.writeString(answer);
    }
}
