package com.example.zivcompose.ui.navcaiton;


import androidx.navigation.NavHostController;

public class MyNavHost {
    private NavHostController nav;
    private static MyNavHost myNavHost;

    private MyNavHost() {

    }
    public static MyNavHost getNavHost() {
        if (myNavHost == null) {
            synchronized (MyNavHost.class) {
                if (myNavHost == null) {
                    myNavHost = new MyNavHost();
                }
            }
        }
        return myNavHost;
    }
    public void setNav(NavHostController nav) {
        this.nav = nav;
    }



    public  void toPage(String url) {
        nav.navigate(url);
    }

    public void back(){
        nav.popBackStack();
    }
}
