package ua.com.drabchak.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
public class SongTranslation extends Translational {
    private String author;
    private String songName;

    public SongTranslation(LocalTime duration, String author, String songName) {
        super(duration, 0);
        this.author = author;
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "SONG {" +
                "author='" + author + '\'' +
                ", songName='" + songName + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
