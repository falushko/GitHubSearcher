package ru.hand_build.android.githubsearcher;

/**
 * Created by vladimir on 16.06.15.
 */
public class Repo {
    public String name;
    public String description;
    public int watchers;
    public int forks;
    public String owner;
    public String officialSite;

    public Repo(String name, String description, int watchers, int forks, String owner, String officialSite) {
        this.name = name;
        this.description = description;
        this.watchers = watchers;
        this.forks = forks;
        this.owner = owner;
        this.officialSite = officialSite;
    }
}
