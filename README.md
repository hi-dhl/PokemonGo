# <p align="center"> PokemonGo <img src='http://cdn.51git.cn/2020-07-15-34.png'></p>

<p align="center"> 神奇宝贝 (PokemonGo) 基于 Jetpack + MVVM + Repository + Paging3 + Kotlin Flow 的实战项目 </p>

<p align="center">
这是一个小型的 App 项目，涉及到技术：Paging3（network + db），Dagger-Hilt，App Startup，DataBinding，Room，Motionlayout，Kotlin Flow，Coil，JProgressView 等等。
</p>

<p align="center">
<a href="https://github.com/hi-dhl"><img src="https://img.shields.io/badge/GitHub-HiDhl-4BC51D.svg?style=flat"></a> <a href="https://opensource.org/licenses/Apache-2.0"><img src="https://img.shields.io/badge/license-Apache2.0-blue.svg?style=flat"></a> <img src="https://img.shields.io/badge/language-kotlin-orange.svg"/> <img src="https://img.shields.io/badge/platform-android-lightgrey.svg"/>
</p>

<p align="center">
<img src="http://cdn.51git.cn/2020-07-23-Pokemon12.png"/> 
</p>

<p align="center"> PokemonGo 动态效果图如下所示，如果动图无法查看，请点击这里查看 <a href="http://cdn.51git.cn/2020-07-14-15946978385391.gif"> 动态效果图</a> | <a href="http://cdn.51git.cn/2020-07-14-PokemonGo2.png"> 静态图</a></p>

<p align="center">
<img src="http://cdn.51git.cn/2020-07-14-15947367840380.gif"/> 
</p>

### PokemonGo 更新记录

**2020-07-26:**

* 增加了使用 sealed 在 Flow 基础上封装成功或者失败处理
* 自定义 RemoteMediator 实现**数据库**和**网络**加载数据( RemoteMediator 是 Paging3 当中重要成员 )

以上更新对应分析文章：

* [Google 推荐在项目中使用 sealed 和 RemoteMediator](https://juejin.im/post/5f1e61726fb9a07e594f2f63)

**2020-07-23:**

* Flow 在 MVVM 当中的使用
* Activity 、Fragment、ViewModel 结合 Flow 三种使用方式
* Flow 的异常处理
* 增加 Fragment 1.2.0 上重要的更新： 通过 Fragment 的构造函数传递参数，以及 FragmentFactory 和 FragmentContainerView 的使用
* ......

以上更新对应分析文章：

* [Google 推荐在 MVVM 架构中使用 Kotlin Flow](https://juejin.im/post/5f153adff265da22fb287e6e)
* [[译][Google工程师] 详解 FragmentFactory 如何优雅使用 Koin 以及部分源码分析](https://juejin.im/post/5ecb16f1f265da76fb0c3967)

**2020-07-14:**

1. 自定义 RemoteMediator 实现 network + db 的混合使用 ( RemoteMediator 是 Paging3 当中重要成员 )
2. 使用 Data Mapper 分离数据源 和 UI
3. Kotlin Flow 结合  Retrofit2 + Room 的混合使用
4. Kotlin Flow 与 LiveData 的使用
5. 使用 Coil 加载图片
6. 使用 ViewModel、LiveData、DataBinding 协同工作
7. 使用 Motionlayout 做动画
8. App Startup 与 Hilt 的使用
9. ......

关于 PokemonGo 项目分析的文章请查看 [神奇宝贝 眼前一亮的 Jetpack + MVVM 极简实战](https://juejin.im/post/5f0d303e6fb9a07e76550d4c)

**如果这个仓库对你有帮助，请仓库右上角帮我 star 一下，非常感谢。**

### 项目 PokemonGo 涉及到的技术

* [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)：检查依赖库是否存在最新版本
* [Kotlin Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)：flow 是对 Kotlin 协程的扩展，让我们可以像运行同步代码一样运行异步代码
* JetPack
    * Paging3（network + db）：用到了 Paging3 中的  `RemoteMediator` 用来实现 network + db
    * Dagger-Hilt (2.28-alpha)：依赖注入框架
    * App Startup：设置组件初始化顺序
    * DataBinding：以声明方式将可观察数据绑定到界面上
    * Room：在 SQLite 上提供了一个抽象层，流畅地访问 SQLite 数据库
    * LiveData：在底层数据库更改时通知视图
    * ViewModel：以注重生命周期的方式管理界面相关的数据
    * Andriod KTX：编写更简洁、惯用的 Kotlin 代码
* 项目架构
    * MVVM 架构
    * Repository 设计模式
    * Data Mapper 数据映射 
* [Retrofit2 & OkHttp3](https://github.com/square/retrofit)：用于请求网路数据
* [Coil](https://github.com/coil-kt/coil/)：基于 Kotlin 开发的首个图片加载库
* [material-components-android](https://github.com/material-components/material-components-android)：模块化和可定制的材料设计 UI 组件
* [Motionlayout](https://developer.android.com/training/constraint-layout/motionlayout) ：MotionLayout 是一种布局类型，可帮助您管理应用中的动画
* [Timber](https://github.com/JakeWharton/timber): 日志打印
* [JProgressView](https://github.com/hi-dhl/JProgressView) ：一个小巧灵活可定制的进度条，支持图形：圆形、圆角矩形、矩形等等

**以上技术栈对应之前写的技术文章：**

* [Jetpack 最新成员 AndroidX App Startup 实践以及原理分析](https://juejin.im/post/5ee4bbe4f265da76b559bdfe)
* [Jetpack 成员 Paging3 实践以及源码分析（一）](https://juejin.im/post/5ee998e8e51d4573d65df02b)
* [Jetpack 新成员 Paging3 网络实践及原理分析（二）](https://juejin.im/post/5eeefbf4e51d45742c53ddce)
* [Jetpack 新成员 Hilt 实践（一）启程过坑记](https://juejin.im/post/5ef2f31951882565a94e06a5?utm_source=gold_browser_extension) 
* [Jetpack 新成员 Hilt 实践之 App Startup（二）进阶篇](https://juejin.im/post/5ef7638c5188252e6a532db3)
* [Jetpack 新成员 Hilt 与 Dagger 大不同（三）落地篇](https://juejin.im/post/5efca0c1e51d4534a40d972f)
* [全方面分析 Hilt 和 Koin 性能](https://juejin.im/post/5f02114d5188252e8a081afb)
* [[译][2.4K Star] 放弃 Dagger 拥抱 Koin](https://juejin.im/post/5ebc1eb8e51d454dcf45744e)
* [项目中封装 Kotlin + Android Databinding](https://juejin.im/post/5e9c434a51882573663f6cc6)
* [为数不多的人知道的 Kotlin 技巧以及 原理解析(一)](https://juejin.im/post/5edfd7c9e51d45789a7f206d)
* [为数不多的人知道的 Kotlin 技巧以及 原理解析(二)](https://juejin.im/post/5f0747486fb9a07ea86dc881)


## 如何检查依赖库的版本更新

在项目的根目录下执行以下命令。

```
./gradlew dependencyUpdates
```
    
会在当前目录下生成 build/dependencyUpdates/report.txt 文件，内容如下所示：

```
The following dependencies have later release versions:
 - androidx.swiperefreshlayout:swiperefreshlayout [1.0.0 -> 1.1.0]
     https://developer.android.com/jetpack/androidx
 - com.squareup.okhttp3:logging-interceptor [3.9.0 -> 4.7.2]
     https://square.github.io/okhttp/
 - junit:junit [4.12 -> 4.13]
     http://junit.org
 - org.koin:koin-android [2.1.5 -> 2.1.6]
 - org.koin:koin-androidx-viewmodel [2.1.5 -> 2.1.6]
 - org.koin:koin-core [2.1.5 -> 2.1.6]

Gradle release-candidate updates:
 - Gradle: [6.1.1 -> 6.5.1]
```

会列出所有需要更新的依赖库的最新版本，并且 Gradle Versions Plugin 比 AndroidStudio 所支持的更加全面：

* 支持手动方式管理依赖库最新版本检查
* 支持 ext 的方式管理依赖库最新版本检查
* 支持 buildSrc 方式管理依赖库最新版本检查
* 支持 gradle-wrapper 最新版本检查
* 支持多模块的依赖库最新版本检查
* 支持多项目的依赖库最新版本检查
    
## MVVM 架构

PokemonGo 基于  MVVM 架构和 Repository 设计模式，如今几乎所有的 Android 开发者至少都听过 MVVM 架构，在谷歌 Android 团队宣布了 Jetpack 的视图模型之后，它已经成为了现代 Android 开发模式最流行的架构之一。

<p align="center">
<img src="http://cdn.51git.cn/2020-07-12-159453363449491.jpg"/> 
</p>

Jetpack 的视图模型的 MVVM 架构由 View + DataBinding + ViewModel + Model 组成。

## TODO

* 加入更多 Jetpack 成员
* 设计更多的实用场景
* 增加单元测试
* 增加 Motionlayout 的使用
* ......

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack 源码相关的文章，正在努力写出更好的文章，如果这篇文章对你有帮助给个 star，文章中有什么没有写明白的地方，或者有什么更好的建议欢迎留言，欢迎一起来学习，在技术的道路上一起前进。

> 计划建立一个最全、最新的 AndroidX Jetpack 相关组件的实战项目 以及 相关组件原理分析文章，正在逐渐增加 Jetpack 新成员，仓库持续更新，可以前去查看：[AndroidX-Jetpack-Practice](https://github.com/hi-dhl/AndroidX-Jetpack-Practice), 如果这个仓库对你有帮助，请帮我点个赞，我会陆续完成更多 Jetpack 新成员的项目实践。

### Android10 源码分析

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### 算法题库的归纳和总结

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### 精选国外的技术文章

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。

## License

```
Copyright 2020 hi-dhl (Jack Deng)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


