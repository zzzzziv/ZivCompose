package com.example.zivcompose.ui.navcaiton

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.zivcompose.ui.navcaiton.MyNavHost
import com.example.zivcompose.ui.navcaiton.RouterConst
import com.example.zivcompose.ui.view.FirstView
import com.example.zivcompose.ui.view.MainView
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {

    val navController = rememberAnimatedNavController()
    MyNavHost.getNavHost().setNav(navController)
            AnimatedNavHost(
                navController = navController,
                startDestination = RouterConst.mainView //设置首页
            ) {
                //登录页
                composable(
                    RouterConst.mainView,//如果不这么写，就是写一个字符串 比如 “mainView”
                    enterTransition = {//进入动画，可以点进去看源码，有多种类型
                        slideInHorizontally(animationSpec = tween(800),initialOffsetX = { -it })
                    },
                    exitTransition = {//退出动画，tween里面的参数是时间，单位毫秒
                        slideOutHorizontally(animationSpec = tween(800), targetOffsetX = { it })
                    },
                ) {
                    MainView()
                }
//---------------------------------------------以上为一段导航--------------------------------
                composable(
                    RouterConst.firstView,
                    enterTransition = {//进入动画，可以点进去看源码，有多种类型
                        slideInHorizontally(animationSpec = tween(800),initialOffsetX = { -it })
                    },
                    exitTransition = {//退出动画，tween里面的参数是时间，单位毫秒
                        slideOutHorizontally(animationSpec = tween(800), targetOffsetX = { it })
                    },
                ) {
                    FirstView()
                }

            }
        }

