package otokatari.com.otokatari.Model.s.RequestInfo;

public class CreatePlaylist {
    private boolean Favourite;
    private String Name;

    public String getName() {
        return Name;
    }

   public boolean getFavourite()
   {
       return Favourite;
   }

    public void setFavourite(boolean favourite) {
        Favourite = favourite;
    }

    public void setName(String name) {
        Name = name;
    }
}
