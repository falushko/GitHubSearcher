package ru.hand_build.android.githubsearcher;

/**
 * Created by vladimir on 16.06.15.
 */

//model class

public class Repo {
    public String name;
    public String description;
    public int watchers;
    public int forks;
    public String owner;
    public String website;

    public Repo(String name, String description,
                int watchers, int forks,
                String owner, String website) {
        this.name = name;
        this.description = description;
        this.watchers = watchers;
        this.forks = forks;
        this.owner = owner;
        this.website = website;
    }
}
