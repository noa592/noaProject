package com.example.noa_project;

import java.util.List;

public class YouTubeResponse {
    public List<Item> items;

    public static class Item {
        public Id id;

        public Snippet snippet;

        public static class Id {
            public String videoId;
        }

        public static class Snippet {
            public String title;

            public String description;
        }
    }
}


