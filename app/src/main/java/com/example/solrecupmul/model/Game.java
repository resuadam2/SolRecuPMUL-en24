package com.example.solrecupmul.model;

public class Game {
    private String name, company, platform;
    private int id, year;

    public static final String [] platforms = {
            "All","PlayStation 1","PlayStation 2","PlayStation 3","PlayStation4","PlayStation 5",
            "PC", "XBOX","XBOX 360", "Nintendo Switch", "Nintendo Wii", "XBOX SERIES X",
            "Android","IOs", "Nintendo DS", "GameBoy", "GameBoy Advance"
    };

    public static final String [] platformsWithoutAll = {
            "PlayStation 1","PlayStation 2","PlayStation 3","PlayStation4","PlayStation 5",
            "PC", "XBOX","XBOX 360", "Nintendo Switch", "Nintendo Wii", "XBOX SERIES X",
            "Android","IOs", "Nintendo DS", "GameBoy", "GameBoy Advance"
    };

    public static int getPlatformIndex(String platform)
    {
        for (int i = 0; i < platforms.length; i++)
            if (platforms[i].equals(platform))
                return i;
        return 0;
    }

    public Game(int id, String name, String company, String platform, int year) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.platform = platform;
        this.year = year;
    }

    public Game() {
        this.id = 0;
        this.name = "";
        this.company = "";
        this.platform = "";
        this.year = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getPlatform() {
        return platform;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", platform='" + platform + '\'' +
                ", year=" + year +
                '}';
    }
}
