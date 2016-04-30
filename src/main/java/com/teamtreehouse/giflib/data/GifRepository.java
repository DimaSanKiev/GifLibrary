package com.teamtreehouse.giflib.data;

import com.teamtreehouse.giflib.model.Gif;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GifRepository {
    private static final List<Gif> ALL_GIFS = Arrays.asList(
        new Gif("android-explosion", 1, LocalDate.of(2016, 4, 29), "Chris Ramacciotti", false),
        new Gif("ben-and-mike", 5, LocalDate.of(2016, 3, 15), "Ben Jakuben", true),
        new Gif("book-dominos", 1, LocalDate.of(2016, 4, 13), "Craig Dennis", false),
        new Gif("compiler-bot", 4, LocalDate.of(2016, 3, 11), "Ada Lovelace", true),
        new Gif("cowboy-coder", 4, LocalDate.of(2016, 2, 1), "Grace Hooper", false),
        new Gif("infinite-andrew", 5, LocalDate.of(2016, 1, 9), "Marissa Mayer", true),
        new Gif("evolution", 1, LocalDate.of(2016, 4, 30), "Dmytro Burdyga", true)
    );

    public Gif findByName(String name) {
        for (Gif gif : ALL_GIFS) {
            if (gif.getName().equals(name)) {
                return gif;
            }
        }
        return null;
    }

    public List<Gif> getAllGifs() {
        return ALL_GIFS;
    }

    public List<Gif> findByCategoryId(int id) {
        List<Gif> gifs = new ArrayList<>();
        for (Gif gif : ALL_GIFS) {
            if (gif.getCategoryId() == id) {
                gifs.add(gif);
            }
        }
        return gifs;
    }
}
