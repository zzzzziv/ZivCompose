package com.example.zivcompose.ui.navcaiton;


import androidx.navigation.NavHostController;

public class MyNavHost {
    private static NavHostController nav;

    public void setNav(NavHostController nav) {
        MyNavHost.nav = nav;
    }
    public  static void toPage(String url) {
        nav.navigate(url);
    }

    public static void back(){
        nav.popBackStack();
    }

    private static class SingleNav{
        private static MyNavHost instance = new MyNavHost();
    }

    public static MyNavHost getInstance(){
        return SingleNav.instance;
    }
}
