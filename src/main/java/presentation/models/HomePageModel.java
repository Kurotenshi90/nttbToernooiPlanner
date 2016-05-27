package presentation.models;

import domain.HomePageToernooi;
import domain.Toernooi;
import services.ToernooiService;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 11/05/2016.
 */
public class HomePageModel {
    private ArrayList<HomePageToernooi> homePageToernoois;
    private ToernooiService toernooiService;

    public HomePageModel(){
        homePageToernoois = new ArrayList<>();
        toernooiService = new ToernooiService();
        homePageToernoois = toernooiService.getHomePageToernoois();
    }

    public ArrayList<HomePageToernooi> getHomepagetoernooi(){
        return homePageToernoois;
    }

}
